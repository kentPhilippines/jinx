package com.ruoyi.web.controller.alipay;

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
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
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


    @GetMapping()
    public String fund() {
        return prefix + "/fund";
    }

    /**
     * 查询用户资金账户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserFundEntity alipayUserFundEntity) {
        startPage();
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .selectAlipayUserFundEntityList(alipayUserFundEntity);
        AlipayUserFundEntity userFundEntity = alipayUserFundEntityService.findSumFundM();
        list.add(0, userFundEntity);
        return getDataTable(list);
    }

    /**
     * 导出用户资金账户列表
     */
    @Log(title = "用户资金账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayUserFundEntity alipayUserFundEntity) {
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .selectAlipayUserFundEntityList(alipayUserFundEntity);
        ExcelUtil<AlipayUserFundEntity> util = new ExcelUtil<AlipayUserFundEntity>(AlipayUserFundEntity.class);
        return util.exportExcel(list, "fund");
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
     * 新增加款页面显示
     */
    @GetMapping("/addFreezeUrl/{userId}")
    public String addFreezeUrl(@PathVariable("userId") String userId, ModelMap mmap) {
        AlipayUserFundEntity userFundEntity = new AlipayUserFundEntity();
        userFundEntity.setUserId(userId);
        List<AlipayUserFundEntity> alipayUserFundEntities = alipayUserFundEntityService.selectAlipayUserFundEntityList(userFundEntity);
        mmap.put("userFund", alipayUserFundEntities.get(0));
        return prefix + "/addFreeze";
    }

    @GetMapping("/deleteFreezeUrl/{userId}")
    public String deleteFreezeUrl(@PathVariable("userId") String userId, ModelMap mmap) {
        AlipayUserFundEntity userFundEntity = new AlipayUserFundEntity();
        userFundEntity.setUserId(userId);
        List<AlipayUserFundEntity> alipayUserFundEntities = alipayUserFundEntityService.selectAlipayUserFundEntityList(userFundEntity);
        mmap.put("userFund", alipayUserFundEntities.get(0));
        return prefix + "/deleteFreeze";
    }

    /**
     * 增加授权页面
     */
    @GetMapping("/addQuotaUrl/{userId}")
    public String addQuotaUrl(@PathVariable("userId") String userId, ModelMap mmap) {
        AlipayUserFundEntity userFundEntity = new AlipayUserFundEntity();
        userFundEntity.setUserId(userId);
        List<AlipayUserFundEntity> alipayUserFundEntities = alipayUserFundEntityService.selectAlipayUserFundEntityList(userFundEntity);
        mmap.put("userFund", alipayUserFundEntities.get(0));
        return prefix + "/addQuota";
    }

    /**
     * 减少授权页面
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/deleteQuotaUrl/{userId}")
    public String deleteQuotaUrl(@PathVariable("userId") String userId, ModelMap mmap) {
        AlipayUserFundEntity userFundEntity = new AlipayUserFundEntity();
        userFundEntity.setUserId(userId);
        List<AlipayUserFundEntity> alipayUserFundEntities = alipayUserFundEntityService.selectAlipayUserFundEntityList(userFundEntity);
        mmap.put("userFund", alipayUserFundEntities.get(0));
        return prefix + "/deleteQuota";
    }

    /**
     * 加款保存用户加款记录
     */
    @Log(title = "用户资金账户加款", businessType = BusinessType.UPDATE)
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
     * 加款保存用户加款记录
     */
    @Log(title = "用户资金账户解冻", businessType = BusinessType.UPDATE)
    @PostMapping("/addFreeze")
    @ResponseBody
    public AjaxResult addFreeze(AlipayAmountEntity alipayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        alipayAmountEntity.setAccname(currentUser.getLoginName());
        String password = alipayAmountEntity.getParams().get("password").toString();
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), password, currentUser.getSalt());
        if (!currentUser.getPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        AlipayUserFundEntity alipayUserFundByUserId = alipayUserFundEntityService.findAlipayUserFundByUserId(alipayAmountEntity.getUserId());
        Double freezeBalance = alipayUserFundByUserId.getFreezeBalance();
        if (freezeBalance == 0) {
            return error("当前账户冻结资金不足，请确认好后提交");
        }
        return toAjax(alipayAmountEntityService.insertAlipayAmountFreeze(alipayAmountEntity));
    }
    /**
     * 加款保存用户加款记录
     */
    @Log(title = "增加用户授权额度", businessType = BusinessType.UPDATE)
    @PostMapping("/addQuota")
    @ResponseBody
    public AjaxResult addQuota(AlipayAmountEntity alipayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        alipayAmountEntity.setAccname(currentUser.getLoginName());
        String password = alipayAmountEntity.getParams().get("password").toString();
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), password, currentUser.getSalt());
        if (!currentUser.getPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        return toAjax(alipayAmountEntityService.insertAlipayAmountQuota(alipayAmountEntity));
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
        if (alipayAmountEntity.getAmountType().toString().equals(RefundDeductType.DEDUCT_FREEZE_TYPE.getCode().toString())) {
            mapParam.put("amountType", RefundDeductType.DEDUCT_FREEZE_TYPE.getCode());//账户冻结
            AlipayUserFundEntity alipayUserFundByUserId = alipayUserFundEntityService.findAlipayUserFundByUserId(alipayAmountEntity.getUserId());
            Double accountBalance = alipayUserFundByUserId.getAccountBalance();
            if (accountBalance < alipayAmountEntity.getAmount()) {
                return error("当前账户余额不足，不支持账户冻结");
            }
        } else if (alipayAmountEntity.getAmountType().toString().equals(RefundDeductType.DELETE_QUOTA_TYPE.getCode().toString())) {//减少授权额度
            mapParam.put("amountType", RefundDeductType.DELETE_QUOTA_TYPE.getCode());//账户冻结
        } else {
            mapParam.put("amountType", RefundDeductType.DEDUCT_TYPE.getCode());//减款
        }
        mapParam.put("accname", currentUser.getLoginName());//申请人
        mapParam.put("orderStatus", DeductStatusEnum.DEDUCT_STATUS_PROCESS.getCode());
        mapParam.put("orderId", GenerateOrderNo.getInstance().Generate(StaticConstants.PERFIX_DEDUCT));
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }

}
