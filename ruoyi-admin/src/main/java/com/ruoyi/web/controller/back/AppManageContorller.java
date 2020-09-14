package com.ruoyi.web.controller.back;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.alipay.domain.AlipayAmountEntity;
import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.alipay.service.IAlipayUserRateEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.GoogleUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequestMapping("/appManage")
@Controller
public class AppManageContorller extends BaseController {
    private static final String AMOUNT_TYPE_APP = "3";
    private String prefix = "merchant/other";
    private String prefixRate = "merchant/rate";
    @Autowired
    private IAlipayAmountEntityService alipayAmountEntityService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    private IAlipayUserRateEntityService alipayUserRateEntityService;
    @Autowired
    private IAlipayProductService alipayProductService;

    @GetMapping("/order/add")
    public String appAddOrder() {
        return prefix + "/addOrderList";
    }

    /**
     * 新增补单
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("user", ShiroUtils.getSysUser());
        return prefix + "/add";
    }

    /**
     * <p>补单申请接口</p>
     *
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addOrder(AlipayAmountEntity alipayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        String payPassword = (String) alipayAmountEntity.getParams().get("payPassword");
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), payPassword, currentUser.getSalt());
        if (!currentUser.getFundPassword().equals(verify))
            return AjaxResult.error("密码验证失败");
        String googleCode = alipayAmountEntity.getParams().get("googleCode").toString();
        int is = googleUtils.verifyGoogleCode(currentUser.getLoginName(), googleCode);
        if (is == 0)
            return AjaxResult.error("未绑定谷歌验证器");
        else if (is - 1 > 0)
            return AjaxResult.error("谷歌验证码验证失败");
        alipayAmountEntity.setAmountType(AMOUNT_TYPE_APP);//商户补单
        return toAjax(alipayAmountEntityService.addAppOrder(alipayAmountEntity));
    }

    /**
     * 查询补单记录
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayAmountEntity alipayAmountEntity) {
        alipayAmountEntity.setUserId(ShiroUtils.getSysUser().getMerchantId());//商户交易账号
        alipayAmountEntity.setAmountType(AMOUNT_TYPE_APP);//商户补单
        startPage();
        List<AlipayAmountEntity> list = alipayAmountEntityService.selectAlipayAmountEntityList(alipayAmountEntity);
        return getDataTable(list);
    }

    @GetMapping("/appRate")
    public String appRate(ModelMap modelMap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = alipayProductService.selectAlipayProductList(alipayProductEntity);
        modelMap.put("productList", productlist);
        return prefixRate + "/agentRate";
    }

    @PostMapping("/appRateList")
    @ResponseBody
    public TableDataInfo appRate(AlipayUserRateEntity rate) {
        SysUser currentUser = ShiroUtils.getSysUser();
        String merchantId = "5IXQkxUu";//currentUser.getMerchantId();
        startPage();
        List<AlipayUserRateEntity> rateList = alipayUserRateEntityService.findAgentRateLiat(merchantId, rate);
        AlipayUserRateEntity userRate = new AlipayUserRateEntity();
        userRate.setUserId(merchantId);
        userRate.setSwitchs(1);
        List<AlipayUserRateEntity> alipayUserRateEntities = alipayUserRateEntityService.selectAlipayUserRateEntityList(userRate);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = alipayProductService.selectAlipayProductList(alipayProductEntity);
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        Map<String, AlipayUserRateEntity> agentFeeList = new ConcurrentHashMap<>();
        for (AlipayUserRateEntity fee : alipayUserRateEntities)
            agentFeeList.put(fee.getChannelId() + fee.getPayTypr(), fee);
        for (AlipayUserRateEntity appFee : rateList) {
            String key = appFee.getChannelId() + appFee.getPayTypr();
            AlipayUserRateEntity myRate = agentFeeList.get(key);
            if (ObjectUtil.isNotNull(myRate)) {
                appFee.setProfit((new BigDecimal("" + appFee.getFee()).subtract(
                        new BigDecimal("" + myRate.getFee()))).toString());
                if (null != myRate.getFee())
                    appFee.setChannelFee(myRate.getFee().toString());
            }
            AlipayProductEntity product = prCollect.get(appFee.getPayTypr());
            if (ObjectUtil.isNotNull(product))
                appFee.setPayTypr(product.getProductName());
        }
        return getDataTable(rateList);
    }

    @PostMapping("/myRateList")
    @ResponseBody
    public TableDataInfo myRateList() {
        SysUser currentUser = ShiroUtils.getSysUser();
        String merchantId = currentUser.getMerchantId();
        AlipayUserRateEntity userRate = new AlipayUserRateEntity();
        userRate.setUserId(merchantId);
        userRate.setSwitchs(1);
        startPage();
        List<AlipayUserRateEntity> alipayUserRateEntities = alipayUserRateEntityService.selectAlipayUserRateEntityList(userRate);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = alipayProductService.selectAlipayProductList(alipayProductEntity);
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        for (AlipayUserRateEntity appFee : alipayUserRateEntities) {
            AlipayProductEntity product = prCollect.get(appFee.getPayTypr());
            if (ObjectUtil.isNotNull(product))
                appFee.setPayTypr(product.getProductName());
        }
        return getDataTable(alipayUserRateEntities);
    }
}
