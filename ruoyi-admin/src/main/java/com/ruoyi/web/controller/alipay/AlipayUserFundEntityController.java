package com.ruoyi.web.controller.alipay;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import com.ruoyi.UserInfoUtil;
import com.ruoyi.alipay.domain.*;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.DeductStatusEnum;
import com.ruoyi.common.enums.RefundDeductType;
import com.ruoyi.common.utils.GenerateOrderNo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;


    @GetMapping()
//    @RequiresPermissions("alipay:fund:view")
    public String fund() {
        return prefix + "/fund";
    }

    @GetMapping("/bak")
//    @RequiresPermissions("alipay:fund:view")
    public String fundBak() {
        return prefix + "/fundBak";
    }

    /**
     * 查询用户资金账户列表
     */
    @PostMapping("/list")
//    @RequiresPermissions("fund:alipay:list")
    @ResponseBody
    public TableDataInfo list(AlipayUserFundEntity alipayUserFundEntity) {
        startPage1();
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .selectAlipayUserFundEntityList(alipayUserFundEntity);
        AlipayUserFundEntity userFundCardEntity = null;


        if (null != alipayUserFundEntity.getUserType() && "2".equals(alipayUserFundEntity.getUserType().toString())) {
            userFundCardEntity = alipayUserFundEntityService.findSumFundC(alipayUserFundEntity.getCurrency());
            list.add(0, userFundCardEntity);
        }
        AlipayUserFundEntity userFundEntity = alipayUserFundEntityService.findSumFundM(alipayUserFundEntity.getCurrency());
        for (AlipayUserFundEntity fund : list) {
            UserInfo userInfo = UserInfoUtil.selectUserInfoByName(fund.getUserId());
            if (ObjectUtil.isNotNull(userInfo)) {
                fund.setWitAccount(userInfo.getAmount().toString());
            }
        }
        list.add(0, userFundEntity);
        return getDataTable(list);
    }

    @PostMapping("/childrenList")
//    @RequiresPermissions("fund:alipay:list")
    @ResponseBody
    public TableDataInfo childrenList(AlipayUserFundEntity alipayUserFundEntity) {
        String currencyFilter = alipayUserFundEntity.getCurrency();
        if (StringUtils.isBlank(currencyFilter)) {
            currencyFilter = "USDT-CNY";
        }
        Integer colorIndex = alipayUserFundEntity.getColorIndex() == null ? 0 : alipayUserFundEntity.getColorIndex() + 1;
        List<AlipayUserFundEntity> list = new ArrayList<>();
        List<AlipayUserInfo> alipayUserInfoList = merchantInfoEntityService.selectChildrenByUserId(alipayUserFundEntity.getAgent());
        String str = "";
        if (alipayUserInfoList.size() > 0 && null != alipayUserInfoList) {
            List<String> idList = alipayUserInfoList.stream().map(AlipayUserInfo::getUserId).collect(Collectors.toList());
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < idList.size(); i++) {
                if (i == idList.size() - 1) {
                    stringBuffer.append("'" + idList.get(i) + "'");
                } else {
                    stringBuffer.append("'" + idList.get(i) + "'" + ",");
                }
                str = stringBuffer.toString();
            }
            list = alipayUserFundEntityService.selectAlipayUserFundEntityListByUserId(idList);
            String finalCurrencyFilter = currencyFilter;
            list = list.stream().
                    filter(tmp -> finalCurrencyFilter.contains(alipayUserFundEntity.getCurrency())).collect(Collectors.toList());

            List<AlipayUserFundEntity> sumFundByUserId = alipayUserFundEntityService.findSumFundByUserId(str);
            List<AlipayUserFundEntity> collect1 = sumFundByUserId.stream().
                    filter(tmp -> finalCurrencyFilter.
                            contains(alipayUserFundEntity.getCurrency()) &&
                            (null != tmp.getTodayDealAmount() || null != tmp.getFreezeBalance()
                                    || null != tmp.getAccountBalance() || null != tmp.getQuota()
                                    || null != tmp.getTodayProfit() || null != tmp.getTodayWitAmount())).collect(Collectors.toList());

            if (list.size() > 0 && collect1.size() > 0) {
                for (AlipayUserFundEntity tmp : collect1) {
                    if ("USDT".equals(tmp.getCurrency())) {
                        tmp.setUserId("所有-USDT");
                        tmp.setUserName("商户余额-USDT");

                    } else if ("CNY".equals(tmp.getCurrency())) {
                        tmp.setUserId("所有-CNY");
                        tmp.setUserName("商户余额-CNY");
                    }
                    list.add(0, tmp);
                }
            }
        }
        list.stream().forEach(tmp2 -> {
            tmp2.setColorIndex(colorIndex);
        });
        return getDataTable(list);
    }

    /**
     * 查询用户资金账户列表
     */
    @PostMapping("/listBak")
//    @RequiresPermissions("fund:alipay:list")
    @ResponseBody
    public TableDataInfo listBak(AlipayUserFundEntity alipayUserFundEntity) {
        startPage1();
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .findFundBakList(alipayUserFundEntity);
        return getDataTable(list);
    }

    /**
     * 导出用户资金账户列表
     */
    @Log(title = "用户资金账户", businessType = BusinessType.EXPORT)
//    @RequiresPermissions("alipay:fund:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayUserFundEntity alipayUserFundEntity) {
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .selectAlipayUserFundEntityList(alipayUserFundEntity);
        ExcelUtil<AlipayUserFundEntity> util = new ExcelUtil<AlipayUserFundEntity>(AlipayUserFundEntity.class);
        return util.exportExcel(list, "fund");
    }

    /**
     * 导出用户资金账户列表
     */
    @Log(title = "备份资金账户", businessType = BusinessType.EXPORT)
    @PostMapping("/exportBak")
//    @RequiresPermissions("alipay:fund:export")
    @ResponseBody
    public AjaxResult exportBak(AlipayUserFundEntity alipayUserFundEntity) {
        List<AlipayUserFundEntity> list = alipayUserFundEntityService
                .findFundBakList(alipayUserFundEntity);
        ExcelUtil<AlipayUserFundEntity> util = new ExcelUtil<AlipayUserFundEntity>(AlipayUserFundEntity.class);
        return util.exportExcel(list, "bak");
    }

    /*最新的资金账户处理逻辑*/

    /**
     * 新增加款页面显示
     */
    @GetMapping("/refund/{userId}")
//    @RequiresPermissions("fund:refund:add")
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
//    @RequiresPermissions("fund:refund:add")
    public String addFreezeUrl(@PathVariable("userId") String userId, ModelMap mmap) {
        AlipayUserFundEntity userFundEntity = new AlipayUserFundEntity();
        userFundEntity.setUserId(userId);
        List<AlipayUserFundEntity> alipayUserFundEntities = alipayUserFundEntityService.selectAlipayUserFundEntityList(userFundEntity);
        mmap.put("userFund", alipayUserFundEntities.get(0));
        return prefix + "/addFreeze";
    }

    @GetMapping("/deleteFreezeUrl/{userId}")
//    @RequiresPermissions("fund:refund:deduct")
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
     *
     * @param userId
     * @param mmap
     * @return
     */
    @GetMapping("/deleteQuotaUrl/{userId}")
//    @RequiresPermissions("fund:refund:deduct")
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
//    @RequiresPermissions("fund:refund:deleteFreezeFlag")
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
    @Log(title = "添加代付账号", businessType = BusinessType.UPDATE)
    @PostMapping("/addWirAccount")
    @ResponseBody
    public AjaxResult updateStatus(String userId) {
        logger.info("[当前添加代付账号的管理员账号为：" + ShiroUtils.getSysUser().getLoginName() + "]");
        logger.info("[添加的账号为：" + userId + "]");
        return toAjax(UserInfoUtil.add(userId));
    }
    @RequiresPermissions("system:userInfo:edit")
    @GetMapping("/editAccount/{id}")
    public String editAccount(@PathVariable("id") String id, ModelMap mmap) {
        UserInfo userInfo = UserInfoUtil.selectUserInfoByName(id);
        mmap.put("userInfo", userInfo);
        return prefix + "/witAmount";
    }
    @RequiresPermissions("system:userInfo:amount")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PostMapping("/amount")
    @ResponseBody
    public AjaxResult amount(AdminApiVo.UserInfoAmount amount) {
        SysUser sysUser = ShiroUtils.getSysUser();
        amount.setApply(sysUser.getUserName());
        return toAjax(UserInfoUtil.amount(amount));
    }

}
