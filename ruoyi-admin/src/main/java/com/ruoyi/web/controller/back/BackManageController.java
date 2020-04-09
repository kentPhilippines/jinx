package com.ruoyi.web.controller.back;

import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.*;
import com.ruoyi.alipay.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.WithdrawalStatusEnum;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.GenerateOrderNo;
import com.ruoyi.common.utils.HashKit;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.GoogleUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/back/merchant/admin")
@Controller
public class BackManageController extends BaseController {
    private String prefix = "/merchant/info";

    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;

    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;

    @Autowired
    private IAlipayRunOrderEntityService alipayRunOrderEntityService;

    @Autowired
    private IAlipayWithdrawEntityService alipayWithdrawEntityService;

    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private DictionaryUtils dictionaryUtils;

    @Autowired
    private IAlipayBankListEntityService alipayBankListEntityService;

    @Autowired
    private GoogleUtils googleUtils;

    /**
     * 商户后台用户登陆显示详细信息
     */
    @RequiresPermissions("back:merchant:view")
    @GetMapping("/view")
    public String detail(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        AlipayUserInfo userInfo = merchantInfoEntityService.selectBackUserByUserId(sysUser.getMerchantId());
        if (userInfo == null)
            throw new BusinessException("此商户不存在");
        mmap.put("userInfo", userInfo);
        return prefix + "/detail";
    }


    /**
     * 商户保存修改信息
     *
     * @param alipayUserInfo
     * @return
     */
    @RequiresPermissions("back:merchant:edit")
    @Log(title = "商户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult toSave(AlipayUserInfo alipayUserInfo) {
        return toAjax(merchantInfoEntityService.updateMerchantByBackAdmin(alipayUserInfo));
    }


    //商户查询交易订单
    @RequiresPermissions("back:order:view")
    @GetMapping("/order/view")
    public String orderShow() {
        return prefix + "/order";
    }


    /**
     * 查询商户订单
     */
    @RequiresPermissions("back:order:list")
    @PostMapping("/order/list")
    @ResponseBody
    public TableDataInfo orderList(AlipayDealOrderApp alipayDealOrderApp) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayDealOrderApp.setOrderAccount(sysUser.getMerchantId());
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
        return getDataTable(list);
    }


    //商户查询交易流水
    @RequiresPermissions("back:running:view")
    @GetMapping("/running/view")
    public String runningShow() {
        return prefix + "/running";
    }

    /**
     * 查询商户的交易流水
     */
    @RequiresPermissions("back:running:list")
    @PostMapping("/running/list")
    @ResponseBody
    public TableDataInfo list(AlipayRunOrderEntity alipayRunOrderEntity) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayRunOrderEntity.setOrderAccount(sysUser.getMerchantId());
        startPage();
        List<AlipayRunOrderEntity> list = alipayRunOrderEntityService.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
        return getDataTable(list);
    }


    //商户提现申请
    @RequiresPermissions("back:withdrawal:view")
    @GetMapping("/withdrawal/view")
    public String withdrawalShow() {
        return prefix + "/withdrawal";
    }

    /**
     * 查询商户提现记录列表
     */
    @RequiresPermissions("back:withdrawal:list")
    @PostMapping("/withdrawal/list")
    @ResponseBody
    public TableDataInfo list(AlipayWithdrawEntity alipayWithdrawEntity) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayWithdrawEntity.setUserId(sysUser.getMerchantId());
        startPage();
        List<AlipayWithdrawEntity> list = alipayWithdrawEntityService.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
        return getDataTable(list);
    }

    /**
     * 商户发起申请提现
     */
    @RequiresPermissions("back:withdrawal:apply")
    @GetMapping("/withdrawal/apply")
    public String apply(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        AlipayUserFundEntity alipayUserFundEntity = alipayUserFundEntityService.findAlipayUserFundByUserId(sysUser.getMerchantId());
        AlipayBankListEntity alipayBankListEntity = new AlipayBankListEntity();
        alipayBankListEntity.setAccount(sysUser.getMerchantId());
        List<AlipayBankListEntity> list = alipayBankListEntityService.selectAlipayBankListEntityList(alipayBankListEntity);
        mmap.put("bankList", list);
        mmap.put("userFund", alipayUserFundEntity);
        return prefix + "/apply";
    }

    /**
     * 保存提现提案
     */
    @RequiresPermissions("back:withdrawal:save")
    @Log(title = "加减款记录", businessType = BusinessType.INSERT)
    @PostMapping("/withdrawal/save")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult witSave(AlipayWithdrawEntity alipayWithdrawEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        String payPassword = (String) alipayWithdrawEntity.getParams().get("payPassword");
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), payPassword, currentUser.getSalt());
        if (!currentUser.getPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        //正式环境解注
//        //验证谷歌验证码
//        String googleCode = alipayWithdrawEntity.getParams().get("googleCode").toString();
//        int is = googleUtils.verifyGoogleCode(currentUser.getLoginName(), googleCode);
//        if (is == 0) {
//            return AjaxResult.error("未绑定谷歌验证器");
//        } else if (is - 1 > 0) {
//            return AjaxResult.error("谷歌验证码验证失败");
//        }
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_6);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("manage", "manage");
        mapParam.put("appid", currentUser.getMerchantId());
        mapParam.put("ordertime", new Date());
        mapParam.put("amount", alipayWithdrawEntity.getAmount());
        mapParam.put("acctno", alipayWithdrawEntity.getBankNo());
        mapParam.put("accname", currentUser.getLoginName());
        mapParam.put("mobile", alipayWithdrawEntity.getMobile());
        mapParam.put("bankcode", "R");//入款
        mapParam.put("orderStatus", WithdrawalStatusEnum.WITHDRAWAL_STATUS_PROCESS.getCode());
        mapParam.put("orderId", GenerateOrderNo.getInstance().Generate(StaticConstants.MERCHANT_WITHDRAWAL));
        mapParam.put("rsasign", HashKit.md5(MapDataUtil.createParam(mapParam)));
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }

    //商户查询银行卡
    @RequiresPermissions("bank:bank:view")
    @GetMapping("/bank/view")
    public String bankCard() {
        return prefix + "/bank";
    }

    /**
     * 查询银行卡列表列表
     */
    @RequiresPermissions("back:bank:list")
    @PostMapping("/bank/list")
    @ResponseBody
    public TableDataInfo list(AlipayBankListEntity alipayBankListEntity) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayBankListEntity.setAccount(sysUser.getMerchantId());
        alipayBankListEntity.setStatus(1);
        startPage();
        List<AlipayBankListEntity> list = alipayBankListEntityService.selectAlipayBankListEntityList(alipayBankListEntity);
        return getDataTable(list);
    }

    /**
     * 新增银行卡列表
     */
    @GetMapping("/bank/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存银行卡列表
     */
    @RequiresPermissions("backew:bank:add")
    @Log(title = "银行卡列表", businessType = BusinessType.INSERT)
    @PostMapping("/bank/toSave")
    @ResponseBody
    public AjaxResult addSave(AlipayBankListEntity alipayBankListEntity) {
        alipayBankListEntity.setAccount(ShiroUtils.getSysUser().getMerchantId());
        return toAjax(alipayBankListEntityService.insertAlipayBankListEntity(alipayBankListEntity));
    }

    /**
     * 删除银行卡列表
     */
    @RequiresPermissions("back:bank:remove")
    @Log(title = "银行卡列表", businessType = BusinessType.DELETE)
    @PostMapping("/bank/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayBankListEntityService.deleteAlipayBankListEntityByIds(ids));
    }



    //下线数据
    @RequiresPermissions("back:data:view")
    @GetMapping("/data/view")
    public String agent() {
        return prefix + "/agent";
    }

    /**
     * 查询下线代理商户
     */
    @RequiresPermissions("back:data:list")
    @PostMapping("/data/list")
    @ResponseBody
    public TableDataInfo agentList(AlipayUserInfo alipayUserInfo) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayUserInfo.setUserId(sysUser.getMerchantId());
        startPage();
        List<AlipayUserInfo> list = merchantInfoEntityService.selectAgentByMerchantId(alipayUserInfo);
        return getDataTable(list);
    }

    //下线订单
    @RequiresPermissions("agent:order:view")
    @GetMapping("/agent/order/view")
    public String agentOrder() {
        return prefix + "/agent_order";
    }


    /**
     * 查询商户订单
     */
    @RequiresPermissions("agent:order:list")
    @PostMapping("/agent/order/list")
    @ResponseBody
    public TableDataInfo agentOrder(AlipayDealOrderApp alipayDealOrderApp) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayDealOrderApp.setOrderAccount(sysUser.getMerchantId());
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectSubMembersOrderList(alipayDealOrderApp);
        return getDataTable(list);
    }
}
