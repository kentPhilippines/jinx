package com.ruoyi.web.controller.alipay;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayAmountEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.enums.DeductStatusEnum;
import com.ruoyi.common.enums.RefundDeductType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.GenerateOrderNo;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.RSAUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户资金账户Controller
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/fund")
public class AlipayUserFundEntityController extends BaseController {
    private String prefix = "alipay/fund";

    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    private DictionaryUtils dictionaryUtils;
    @Autowired
    private IAlipayAmountEntityService alipayAmountEntityService;
    @Autowired
    private SysPasswordService passwordService;


    @RequiresPermissions("alipay:fund:view")
    @GetMapping()
    public String fund() {
        return prefix + "/fund";
    }

    /**
     * 查询用户资金账户列表
     */
    @RequiresPermissions("alipay:fund:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserFundEntity alipayUserFundEntity) {
        startPage();
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .selectAlipayUserFundEntityList(alipayUserFundEntity);
        return getDataTable(list);
    }

    /**
     * 导出用户资金账户列表
     */
    @RequiresPermissions("alipay:fund:export")
    @Log(title = "用户资金账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayUserFundEntity alipayUserFundEntity) {
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .selectAlipayUserFundEntityList(alipayUserFundEntity);
        ExcelUtil<AlipayUserFundEntity> util = new ExcelUtil<AlipayUserFundEntity>(AlipayUserFundEntity.class);
        return util.exportExcel(list, "fund");
    }

    /**
     * 新增用户资金账户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户资金账户
     */
    @RequiresPermissions("alipay:fund:add")
    @Log(title = "用户资金账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserFundEntity alipayUserFundEntity) {
        return toAjax(alipayUserFundEntityService.insertAlipayUserFundEntity(alipayUserFundEntity));
    }

    /**
     * 修改用户资金账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserFundEntity alipayUserFundEntity = alipayUserFundEntityService.selectAlipayUserFundEntityById(id);
        mmap.put("alipayUserFundEntity", alipayUserFundEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户资金账户
     */
    @RequiresPermissions("alipay:fund:edit")
    @Log(title = "用户资金账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserFundEntity alipayUserFundEntity) {
        return toAjax(alipayUserFundEntityService.updateAlipayUserFundEntity(alipayUserFundEntity));
    }

    /**
     * 删除用户资金账户
     */
    @RequiresPermissions("alipay:fund:remove")
    @Log(title = "用户资金账户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayUserFundEntityService.deleteAlipayUserFundEntityByIds(ids));
    }


    @GetMapping("/fundInfo/{id}")
    public String fundInfo(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserFundEntity alipayUserFundEntity = alipayUserFundEntityService.selectAlipayUserFundEntityById(id);
        mmap.put("alipayUserFundEntity", alipayUserFundEntity);
        return prefix + "/fundInfo";
    }


    /*最新的资金账户处理逻辑*/

    /**
     * 新增加款页面显示
     */
    @GetMapping("/refund/{userId}")
    public String refund(@PathVariable("userId") String userId, ModelMap mmap) {
        AlipayUserFundEntity userFundEntity = new AlipayUserFundEntity();
        userFundEntity.setUserId(userId);
        mmap.put("userFund", userFundEntity);
        return prefix + "/add";
    }

    /**
     * 加款保存用户加款记录
     */
    @RequiresPermissions("alipay:fund:refund")
    @Log(title = "用户资金账户", businessType = BusinessType.UPDATE)
    @PostMapping("/refund")
    @ResponseBody
    public AjaxResult refundSave(AlipayAmountEntity alipayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        alipayAmountEntity.setAccname(currentUser.getLoginName());
        String password = alipayAmountEntity.getParams().get("password").toString();
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), password, currentUser.getSalt());
        if (!currentUser.getPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        return toAjax(alipayAmountEntityService.insertAlipayAmountEntity(alipayAmountEntity));
    }


    /**
     * 新增减款页面显示
     */
    @GetMapping("/deduct/{userId}")
    public String deduct(@PathVariable("userId") String userId, ModelMap mmap) {
        AlipayUserFundEntity userFundEntity = new AlipayUserFundEntity();
        userFundEntity.setUserId(userId);
        mmap.put("userFund", userFundEntity);
        return prefix + "/deduct";
    }


    /**
     * 减款保存用户减款记录
     */
    @RequiresPermissions("alipay:fund:deduct")
    @Log(title = "加减款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/deduct")
    @ResponseBody
    public AjaxResult deductSave(AlipayAmountEntity alipayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        alipayAmountEntity.setAccname(currentUser.getLoginName());
        String password = alipayAmountEntity.getParams().get("password").toString();
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), password, currentUser.getSalt());
        if (!currentUser.getPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_5);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("userId", alipayAmountEntity.getUserId());
        mapParam.put("amount", alipayAmountEntity.getAmount());
        mapParam.put("dealDescribe", alipayAmountEntity.getDealDescribe());
        mapParam.put("amountType", RefundDeductType.DEDUCT_TYPE.getCode());//减款
        mapParam.put("accname", currentUser.getLoginName());//申请人
        mapParam.put("orderStatus", DeductStatusEnum.DEDUCT_STATUS_PROCESS.getCode());
        mapParam.put("orderId", GenerateOrderNo.getInstance().Generate(StaticConstants.PERFIX_DEDUCT));
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }

}
