package com.ruoyi.web.controller.dealpay;

import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayAmountEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.DeductStatusEnum;
import com.ruoyi.common.enums.RefundDeductType;
import com.ruoyi.common.utils.GenerateOrderNo;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.dealpay.domain.DealpayAmountEntity;
import com.ruoyi.dealpay.domain.DealpayUserFundEntity;
import com.ruoyi.dealpay.service.IDealpayAmountService;
import com.ruoyi.dealpay.service.IDealpayUserFundService;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 用户资金账户Controller
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/cardFund")
public class DealpayUserFundController extends BaseController {
    private String prefix = "dealpay/cardFund";

    @Autowired
    private IDealpayUserFundService dealpayUserFundService;
    @Autowired
    private DictionaryUtils dictionaryUtils;
    @Autowired
    private IDealpayAmountService dealpayAmountService;
    @Autowired
    private SysPasswordService passwordService;


    @RequiresPermissions("dealpay:cardFund:view")
    @GetMapping()
    public String cardFund() {
        return prefix + "/cardFund";
    }

    /**
     * 查询卡商资金账户列表
     */
    @RequiresPermissions("dealpay:cardFund:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayUserFundEntity dealpayUserFundEntity) {
        startPage();
        List<DealpayUserFundEntity> list = dealpayUserFundService
                .selectDealpayUserFundList(dealpayUserFundEntity);
        return getDataTable(list);
    }

    /**
     * 导出卡商资金账户列表
     */
    @RequiresPermissions("dealpay:cardFund:export")
    @Log(title = "用户资金账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayUserFundEntity dealpayUserFundEntity) {
        List<DealpayUserFundEntity> list = dealpayUserFundService
                .selectDealpayUserFundList(dealpayUserFundEntity);
        ExcelUtil<DealpayUserFundEntity> util = new ExcelUtil<DealpayUserFundEntity>(DealpayUserFundEntity.class);
        return util.exportExcel(list, "fund");
    }

    /**
     * 新增卡商资金账户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存卡商资金账户
     */
    @RequiresPermissions("dealpay:cardFund:add")
    @Log(title = "用户资金账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayUserFundEntity dealpayUserFund) {
        return toAjax(dealpayUserFundService.insertDealpayUserFund(dealpayUserFund));
    }

    /**
     * 修改卡商资金账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayUserFundEntity dealpayUserFund = dealpayUserFundService.selectDealpayUserFundById(id);
        mmap.put("dealpayUserFund", dealpayUserFund);
        return prefix + "/edit";
    }

    /**
     * 修改保存卡商资金账户
     */
    @RequiresPermissions("dealpay:cardFund:edit")
    @Log(title = "用户资金账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayUserFundEntity dealpayUserFund) {
        return toAjax(dealpayUserFundService.updateDealpayUserFund(dealpayUserFund));
    }

    /**
     * 删除卡商资金账户
     */
    @RequiresPermissions("dealpay:cardFund:remove")
    @Log(title = "用户资金账户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayUserFundService.deleteDealpayUserFundByIds(ids));
    }


    @GetMapping("/fundInfo/{id}")
    public String fundInfo(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayUserFundEntity dealpayUserFundEntity = dealpayUserFundService.selectDealpayUserFundById(id);
        mmap.put("alipayUserFundEntity", dealpayUserFundEntity);
        return prefix + "/fundInfo";
    }

    /**
     * 新增加款页面显示
     */
    @GetMapping("/refund/{userId}")
    public String refund(@PathVariable("userId") String userId, ModelMap mmap) {
        DealpayUserFundEntity dealpayUserFundEntity = new DealpayUserFundEntity();
        dealpayUserFundEntity.setUserId(userId);
        mmap.put("userFund", dealpayUserFundEntity);
        return prefix + "/cardrefund";
    }

    /**
     * 加款保存用户加款记录
     */
    @RequiresPermissions("dealpay:cardFund:refund")
    @Log(title = "用户资金账户", businessType = BusinessType.UPDATE)
    @PostMapping("/refund")
    @ResponseBody
    public AjaxResult refundSave(DealpayAmountEntity dealpayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        dealpayAmountEntity.setAccname(currentUser.getLoginName());
        String password = dealpayAmountEntity.getParams().get("password").toString();
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), password, currentUser.getSalt());
        if (!currentUser.getPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        return toAjax(dealpayAmountService.insertDealpayAmount(dealpayAmountEntity));
    }


    /**
     * 新增减款页面显示
     */
    @GetMapping("/deduct/{userId}")
    public String deduct(@PathVariable("userId") String userId, ModelMap mmap) {
        DealpayUserFundEntity dealpayUserFundEntity = new DealpayUserFundEntity();
        dealpayUserFundEntity.setUserId(userId);
        mmap.put("userFund", dealpayUserFundEntity);
        return prefix + "/carddeduct";
    }

    /**
     * 减款保存卡商减款记录
     */
    @RequiresPermissions("dealpay:cardFund:deduct")
    @Log(title = "加减款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/deduct")
    @ResponseBody
    public AjaxResult deductSave(DealpayAmountEntity dealpayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        dealpayAmountEntity.setAccname(currentUser.getLoginName());
        String password = dealpayAmountEntity.getParams().get("password").toString();
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), password, currentUser.getSalt());
        if (!currentUser.getPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_5);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("userId", dealpayAmountEntity.getUserId());
        mapParam.put("amount", dealpayAmountEntity.getAmount());
        mapParam.put("dealDescribe", dealpayAmountEntity.getDealDescribe());
        mapParam.put("amountType", RefundDeductType.DEDUCT_TYPE.getCode());//减款
        mapParam.put("accname", currentUser.getLoginName());//申请人
        mapParam.put("orderStatus", DeductStatusEnum.DEDUCT_STATUS_PROCESS.getCode());
        mapParam.put("orderId", GenerateOrderNo.getInstance().Generate(StaticConstants.PERFIX_DEDUCT));
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }

}
