package com.ruoyi.web.controller.back;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.*;
import com.ruoyi.alipay.domain.util.WitAppExport;
import com.ruoyi.alipay.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.WithdrawalStatusEnum;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.GoogleUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequestMapping("/back/merchant/admin")
@Controller
public class BackManageController extends BaseController {
    private final String prefix = "merchant/info";
    @Value("${otc.usdt.rate:http://172.16.32.225:32437/http/rate}")
    private String otcRate;
    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;
    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;
    @Autowired
    private IAlipayRunOrderEntityService alipayRunOrderEntityService;
    @Autowired
    private IAlipayWithdrawEntityService alipayWithdrawEntityService;
    @Autowired
    private IAlipayUserRateEntityService alipayUserRateEntityService;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    private IAlipayChanelFeeService alipayChanelFeeService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private DictionaryUtils dictionaryUtils;
    @Autowired
    private IAlipayBankListEntityService alipayBankListEntityService;
    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private IAlipayProductService alipayProductService;

    /**
     * 商户后台用户登陆显示详细信息
     */
    @GetMapping("/view")
    public String detail(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        AlipayUserInfo userInfo = merchantInfoEntityService.selectBackUserByUserId(sysUser.getMerchantId());
        if (userInfo == null) {
            throw new BusinessException("此商户不存在");
        }
        mmap.put("userInfo", userInfo);
        return prefix + "/detail";
    }


    /**
     * 商户保存修改信息
     *
     * @param alipayUserInfo
     * @return
     */
    @Log(title = "商户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult toSave(AlipayUserInfo alipayUserInfo) {
        return toAjax(merchantInfoEntityService.updateMerchantByBackAdmin(alipayUserInfo));
    }

    //商户查询交易订单
    @GetMapping("/order/view")
    public String orderShow(ModelMap modelMap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        //查询产品类型下拉菜单
        //  List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        List<AlipayProductEntity> list = alipayChanelFeeService.findProductByName(sysUser.getMerchantId());
        modelMap.put("productList", list);
        return prefix + "/order";
    }

    /**
     * 查询商户订单
     */
    @PostMapping("/order/list")
    @ResponseBody
    public TableDataInfo orderList(AlipayDealOrderApp alipayDealOrderApp) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayDealOrderApp.setOrderAccount(sysUser.getMerchantId());
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);

        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        for (AlipayDealOrderApp order : list) {
            AlipayProductEntity product = prCollect.get(order.getRetain1());
            if (ObjectUtil.isNotNull(product)) {
                order.setRetain1(product.getProductName());
            }
        }
        return getDataTable(list);
    }


    /**
     * 商户订单导出
     */
    @PostMapping("/order/export")
    @ResponseBody
    public AjaxResult exportOrderApp(AlipayDealOrderApp alipayDealOrderApp) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayDealOrderApp.setOrderAccount(sysUser.getMerchantId());
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
        for (AlipayDealOrderApp orderApp : list) {
            orderApp.setFeeId(null);
        }
        ExcelUtil<AlipayDealOrderApp> util = new ExcelUtil<AlipayDealOrderApp>(AlipayDealOrderApp.class);
        return util.exportExcel(list, "orderApp");
    }


    //商户查询交易流水
    @GetMapping("/running/view")
    public String runningShow() {
        return prefix + "/running";
    }

    /**
     * 查询商户的交易流水
     */
    @PostMapping("/running/list")
    @ResponseBody
    public TableDataInfo list(AlipayRunOrderEntity alipayRunOrderEntity) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayRunOrderEntity.setOrderAccount(sysUser.getMerchantId());
        startPage();
        List<AlipayRunOrderEntity> list = alipayRunOrderEntityService.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
        return getDataTable(list);
    }

    /**
     * 导出流水订单记录列表
     */
    @Log(title = "商户资金流水导出", businessType = BusinessType.EXPORT)
    @PostMapping("/running/export")
    @ResponseBody
    public AjaxResult export(AlipayRunOrderEntity alipayRunOrderEntity) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayRunOrderEntity.setOrderAccount(sysUser.getMerchantId());
        startPage();
        List<AlipayRunOrderEntity> list = alipayRunOrderEntityService
                .selectAlipayRunOrderEntityList(alipayRunOrderEntity);
        for (AlipayRunOrderEntity runorder : list) {
            runorder.setAccountW(null);
            runorder.setAcountR(null);
        }
        ExcelUtil<AlipayRunOrderEntity> util = new ExcelUtil<AlipayRunOrderEntity>(AlipayRunOrderEntity.class);
        return util.exportExcel(list, "running");
    }

    @Autowired
    IAlipayProductService iAlipayProductService;

    //商户提现申请
    @GetMapping("/withdrawal/view")
    public String withdrawalShow(ModelMap modelMap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setProductId("RECHARGE");
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        if (CollUtil.isNotEmpty(list)) {
            AlipayProductEntity first = CollUtil.getFirst(list);
            modelMap.put("isLocation", first.getStatus());
        }
        return prefix + "/withdrawal";
    }

    /**
     * 查询商户提现记录列表
     */
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
     * 导出流水订单记录列表
     */
    @Log(title = "商户代付详情导出", businessType = BusinessType.EXPORT)
    @PostMapping("/withdrawal/export")
    @ResponseBody
    public AjaxResult withdrawalExport(AlipayWithdrawEntity alipayWithdrawEntity) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayWithdrawEntity.setUserId(sysUser.getMerchantId());
        startPage();
        List<AlipayWithdrawEntity> list = alipayWithdrawEntityService.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
        List<WitAppExport> exportList = new ArrayList<>();
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = alipayProductService.selectAlipayProductList(alipayProductEntity);
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        for (AlipayWithdrawEntity wit : list) {
            WitAppExport export = new WitAppExport();
            export.setUserId(wit.getUserId());
            export.setAccname(wit.getAccname());
            export.setActualAmount(wit.getActualAmount());
            export.setAmount(wit.getAmount());
            export.setBankName(wit.getBankName());
            export.setBankNo(wit.getBankNo());
            export.setComment(wit.getComment());
            export.setFee(wit.getFee());
            export.setMobile(wit.getMobile());
            export.setNotify(wit.getNotify());
            export.setOrderId(wit.getOrderId());
            export.setAppOrderId(wit.getAppOrderId());
            export.setOrderStatus(wit.getOrderStatus());
            AlipayProductEntity product = prCollect.get(wit.getWitType());
            if (ObjectUtil.isNotNull(product)) {
                wit.setWitType(product.getProductName());
            }
            export.setWitType(wit.getWitType());
            export.setSubmitTime(wit.getSubmitTime());
            export.setCreateTime(wit.getCreateTime());
            String apply = wit.getApply();
            if (StrUtil.isEmpty(apply)) {
                apply = "否";
            } else {
                apply = "是";
            }
            export.setAuto(apply);
            exportList.add(export);
            export = null;
        }
        ExcelUtil<WitAppExport> util = new ExcelUtil<WitAppExport>(WitAppExport.class);
        return util.exportExcel(exportList, "wit");
    }


    /**
     * 商户发起申请提现
     */
    @GetMapping("/withdrawal/apply")
    public String apply(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        AlipayUserFundEntity alipayUserFundEntity = alipayUserFundEntityService.findAlipayUserFundByUserId(sysUser.getMerchantId());
        AlipayBankListEntity alipayBankListEntity = new AlipayBankListEntity();
        alipayBankListEntity.setAccount(sysUser.getMerchantId());
        List<AlipayBankListEntity> list = alipayBankListEntityService.selectAlipayBankListEntityList(alipayBankListEntity);
        mmap.put("bankList", list);
        mmap.put("userFund", alipayUserFundEntity);
        SysDictData dictData = new SysDictData();
        dictData.setDictType("system_bankcode");
        List<SysDictData> bankcode = dictDataService.selectDictDataList(dictData);
        mmap.put("bankcode", bankcode);
        return prefix + "/apply";
    }

    /**
     * 保存提现提案
     */
    @Log(title = "提现申请", businessType = BusinessType.INSERT)
    @PostMapping("/withdrawal/save")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult witSave(AlipayWithdrawEntity alipayWithdrawEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        String payPassword = (String) alipayWithdrawEntity.getParams().get("payPassword");
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), payPassword, currentUser.getSalt());
        if (!currentUser.getFundPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        SysDictData dictData = new SysDictData();
        dictData.setDictType("system_bankcode");
        List<SysDictData> bankcode = dictDataService.selectDictDataList(dictData);
        ConcurrentHashMap<String, SysDictData> bankcodeCollect = bankcode.stream().collect(Collectors.toConcurrentMap(SysDictData::getDictValue, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        SysDictData sysDictData = bankcodeCollect.get(alipayWithdrawEntity.getBankcode());
        //正式环境解注
        //验证谷歌验证码
        String googleCode = alipayWithdrawEntity.getParams().get("googleCode").toString();
        int is = googleUtils.verifyGoogleCode(currentUser.getLoginName(), googleCode);
        if (is == 0) {
            return AjaxResult.error("未绑定谷歌验证器");
        } else if (is - 1 > 0) {
            return AjaxResult.error("谷歌验证码验证失败");
        }
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_6);
        AlipayUserInfo alipayUserInfo = merchantInfoEntityService.selectBackUserByUserId(currentUser.getMerchantId());
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("appid", currentUser.getMerchantId());
        mapParam.put("ordertime", new Date());
        mapParam.put("amount", alipayWithdrawEntity.getAmount());
        mapParam.put("acctno", alipayWithdrawEntity.getBankNo());
        mapParam.put("acctname", alipayWithdrawEntity.getAccname());
        mapParam.put("apply", currentUser.getLoginName());
        mapParam.put("mobile", alipayWithdrawEntity.getMobile());
        mapParam.put("bankcode", alipayWithdrawEntity.getBankcode());//后台代付
        mapParam.put("bankName", sysDictData.getDictLabel());//后台代付
        mapParam.put("dpaytype", "Bankcard");//银行卡代付类型
        mapParam.put("orderStatus", WithdrawalStatusEnum.WITHDRAWAL_STATUS_PROCESS.getCode());
        mapParam.put("notifyurl", "http://localhost/iiiii");
        mapParam.put("apporderid", GenerateOrderNo.getInstance().Generate(StaticConstants.MERCHANT_WITHDRAWAL));
        mapParam.put("sign", HashKit.md5(MapDataUtil.createParam(mapParam) + alipayUserInfo.getPayPasword()));
        Map<String, String> extraParam = Maps.newHashMap();
        extraParam.put("userId", currentUser.getMerchantId());
        extraParam.put("publicKey", alipayUserInfo.getPublicKey());
        extraParam.put("manage", "manage");
        return HttpUtils.adminMap2Gateway(mapParam, ipPort + urlPath, extraParam);
    }


    /**
     * 商户发起申请提现
     */
    @GetMapping("/recharge")
    public String recharge(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        AlipayUserFundEntity alipayUserFundEntity = alipayUserFundEntityService.findAlipayUserFundByUserId(sysUser.getMerchantId());
        mmap.put("userFund", alipayUserFundEntity);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setProductId("RECHARGE");
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        if (CollUtil.isNotEmpty(list)) {
            AlipayProductEntity first = CollUtil.getFirst(list);
            if ("0".equals(first.getRetain1())) {
                return prefix + "/rechargeLocation";
            }
        }
        return prefix + "/recharge";
    }

    @GetMapping("/withdrawal/getRateUsdtFee")
    @ResponseBody
    public AjaxResult getRateUsdtFee(HttpServletRequest request) {
        String amountCNY = request.getParameter("amountCNY");//人民币充值金额
        if (StrUtil.isEmpty(amountCNY)) {
            return AjaxResult.error("数据错误");
        }
        SysDictData dictData = new SysDictData();
        dictData.setDictType("CNY_USDT");//usdt 汇率字典 type
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        Integer aDouble = Integer.valueOf(amountCNY);
        for (SysDictData date : list) {
            String dictLabel = date.getDictLabel();
            String[] split = dictLabel.split("-");
            Double min = Double.valueOf(split[0]);//兑换区间最小
            Double max = Double.valueOf(split[1]);//兑换区间最大  包括
            if (aDouble > min) {
                if (aDouble <= max) {
                    Random ram = new Random();
                    int i = ram.nextInt(5);
                    while (i == 0) {
                        i = ram.nextInt(5);
                    }
                    aDouble = aDouble + i;
                    double v = 0;
                    try {
                        Double onlineRate = 0.0;
                        Double locatuonRate = Double.valueOf(date.getDictValue());
                        String rateBull = getRate("buy");
                        String rateSell = getRate("sell");
                        BigDecimal bigDecimal = new BigDecimal(rateBull);
                        BigDecimal bigDecimal1 = new BigDecimal(rateSell);
                        if (bigDecimal.compareTo(bigDecimal1) == -1) {
                            onlineRate = Double.valueOf(rateBull);
                        } else {
                            onlineRate = Double.valueOf(rateSell);
                        }
                        v = locatuonRate + onlineRate;
                    } catch (Exception e) {
                        return AjaxResult.error("汇率错误");
                    }
                    double div = Arith.div(aDouble, v, 2);//当前需要转入的 usdt 个数
                    Map map = new HashMap();
                    map.put("amount", div);
                    map.put("rate", v);
                    logger.info("【当前取汇率为：" + map.toString() + "】");
                    return AjaxResult.success(map);
                }
            }
        }
        return AjaxResult.error("汇率错误");
    }

    /**
     * 保存提现提案
     */
    @Log(title = "内充申请-自动回调", businessType = BusinessType.INSERT)
    @PostMapping("/withdrawal/recharge")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult recharge(AlipayWithdrawEntity alipayWithdrawEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        String payPassword = (String) alipayWithdrawEntity.getParams().get("payPassword");
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), payPassword, currentUser.getSalt());
        if (!currentUser.getFundPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        String userId = currentUser.getMerchantId();
        AlipayUserRateEntity rateEntity = alipayUserRateEntityService.findRateByType(userId, "RECHARGE");
        AlipayUserInfo userInfo = new AlipayUserInfo();
        userInfo.setUserId(userId);
        List<AlipayUserInfo> alipayUserInfos = merchantInfoEntityService.selectMerchantInfoEntityList(userInfo);
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        String userid = rateEntity.getUserId();
        String key = alipayUserInfos.get(0).getPayPasword();//交易密钥
        String publicKey = alipayUserInfos.get(0).getPublicKey();
        String amount = alipayWithdrawEntity.getAmount().toString();
        String orderId = GenerateOrderNo.getInstance().Generate("USDT");
        String post = postWit(amount, userid, rateEntity.getPayTypr(), orderId, key, publicKey, alipayWithdrawEntity.getUSDTRate());
        JSONObject json = JSONObject.parseObject(post);
        String result = json.getString("success");
        if (StrUtil.isNotEmpty(result) && result.equals("true")) {
            //新建常规订单已经成功
            //下面新建usdt充值订单   使用固定usdt  转人民币内充账号
            String usdTamount = alipayWithdrawEntity.getUSDTamount();//usdt充值金额
            AlipayUserInfo userInfokent = new AlipayUserInfo();
            userInfokent.setUserId("KENTUSDTMANAGE");
            List<AlipayUserInfo> alipayUserInfokent = merchantInfoEntityService.selectMerchantInfoEntityList(userInfokent);
            String keykent = alipayUserInfokent.get(0).getPayPasword();//交易密钥
            String publicKeykent = alipayUserInfokent.get(0).getPublicKey();
            AlipayUserRateEntity usdt = alipayUserRateEntityService.findRateByType("KENTUSDTMANAGE", "USDT");
            String kentusdtmanage = postWit(usdTamount, "KENTUSDTMANAGE", usdt.getPayTypr(), orderId, keykent, publicKeykent, alipayWithdrawEntity.getUSDTRate());
            JSONObject json1 = JSONObject.parseObject(kentusdtmanage);
            String result1 = json1.getString("success");
            if (StrUtil.isNotEmpty(result1) && result1.equals("true")) {
                String result2 = json1.getString("result");
                JSONObject json2 = JSONObject.parseObject(result2);
                return AjaxResult.success(json2.getString("returnUrl"));
            }
            return AjaxResult.success();
        } else {
            return AjaxResult.success();
        }
    }

    @Log(title = "内充申请-不走自动回调", businessType = BusinessType.INSERT)
    @PostMapping("/withdrawal/rechargeLocation")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult rechargeLocation(AlipayWithdrawEntity alipayWithdrawEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        String payPassword = (String) alipayWithdrawEntity.getParams().get("payPassword");
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), payPassword, currentUser.getSalt());
        if (!currentUser.getFundPassword().equals(verify)) {
            return AjaxResult.error("密码验证失败");
        }
        String userId = currentUser.getMerchantId();
        AlipayUserRateEntity rateEntity = alipayUserRateEntityService.findRateByType(userId, "RECHARGE");
        AlipayUserInfo userInfo = new AlipayUserInfo();
        userInfo.setUserId(userId);
        List<AlipayUserInfo> alipayUserInfos = merchantInfoEntityService.selectMerchantInfoEntityList(userInfo);
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        String userid = rateEntity.getUserId();
        String key = alipayUserInfos.get(0).getPayPasword();//交易密钥
        String publicKey = alipayUserInfos.get(0).getPublicKey();
        String amount = alipayWithdrawEntity.getAmount().toString();
        String orderId = GenerateOrderNo.getInstance().Generate("USDT");
        String post = postWit(amount, userid, rateEntity.getPayTypr(), orderId, key, publicKey);
        return AjaxResult.success();
    }


    String postWit(String amount, String userId, String payType, String orderId, String key, String publicKey) {
        return postWit(amount, userId, payType, orderId, key, publicKey, null);
    }

    String postWit(String amount, String userId, String payType, String orderId, String key, String publicKey, String url) {
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("amount", amount);
        parMap.put("appId", userId);
        parMap.put("applyDate", DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN));
        parMap.put("notifyUrl", "http://starpay168.com:5055");
        if (StrUtil.isNotEmpty(url)) {
            parMap.put("notifyUrl", url);
        }
        parMap.put("pageUrl", "http://starpay168.com:5055");
        parMap.put("orderId", orderId);
        parMap.put("passCode", payType);
        parMap.put("subject", amount);
        String createParam = MapDataUtil.createParam(parMap);
        logger.info("签名前请求串：" + createParam);
        String md5 = HashKit.md5((createParam + key));
        logger.info("签名：" + md5);
        parMap.put("sign", md5);
        String createParam2 = MapDataUtil.createParam(parMap);
        logger.info("加密前字符串：" + createParam2);
        String publicEncrypt = RSAUtils.publicEncrypt(createParam2, publicKey);
        logger.info("加密后字符串：" + publicEncrypt);
        Map<String, Object> postMap = new HashMap<String, Object>();
        postMap.put("cipherText", publicEncrypt);
        postMap.put("userId", userId);
        logger.info("请求参数：" + postMap.toString());
        String post = HttpUtil.post("http://starpay888.org:35426/deal/pay", postMap);
        logger.info("相应结果集：" + post);
        return post;
    }


    //商户查询银行卡
    @GetMapping("/bank/view")
    public String bankCard() {
        return prefix + "/bank";
    }

    /**
     * 查询银行卡列表列表
     */
    @PostMapping("/bank/list")
    @ResponseBody
    public TableDataInfo list(AlipayBankListEntity alipayBankListEntity) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayBankListEntity.setAccount(sysUser.getMerchantId());
        alipayBankListEntity.setCardType(2);
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
    @Log(title = "银行卡列表", businessType = BusinessType.DELETE)
    @PostMapping("/bank/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayBankListEntityService.deleteAlipayBankListEntityByIds(ids));
    }


    //下线数据
    @GetMapping("/data/view")
    public String agent() {
        return prefix + "/agent";
    }

    /**
     * 查询下线代理商户
     */
    @PostMapping("/data/list")
    @ResponseBody
    public TableDataInfo agentList(AlipayUserInfo alipayUserInfo) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayUserInfo.setUserId(sysUser.getMerchantId());
        List<String> agentList = merchantInfoEntityServiceImpl.selectNextAgentByParentId(alipayUserInfo.getUserId());
        String str = CollUtil.getFirst(agentList);
        List list1 = new ArrayList(Arrays.asList(str.split(",")));
        if (str.split(",").length > 2) {
            list1.remove(0);
            list1.remove(0);
        }
        if (StrUtil.isNotBlank(alipayUserInfo.getUserId())) {
            if (list1.contains(alipayUserInfo.getUserId())) {
                list1.clear();
                list1 = new ArrayList();
                list1.add(alipayUserInfo.getUserId());
            }
        }
        startPage();
        List<AlipayUserInfo> list = merchantInfoEntityService.selectAgentByMerchantId(list1);
        return getDataTable(list);
    }

    //下线订单
    @GetMapping("/agent/order/view")
    public String agentOrder() {
        return prefix + "/agent_order";
    }

    @Autowired
    private ISysUserService userService;
    /**
     * 查询商户订单
     */
    @Autowired
    IMerchantInfoEntityService merchantInfoEntityServiceImpl;

    @PostMapping("/agent/order/list")
    @ResponseBody
    public TableDataInfo agentOrder(AlipayDealOrderApp alipayDealOrderApp) {
        SysUser sysUser = ShiroUtils.getSysUser();
        alipayDealOrderApp.setOrderAccount(sysUser.getMerchantId());
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayProductEntity> productlist = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        //查询商户所有的下级用户
        List<String> agentList = merchantInfoEntityServiceImpl.selectNextAgentByParentId(alipayDealOrderApp.getOrderAccount());
        String str = CollUtil.getFirst(agentList);
        List list1 = new ArrayList(Arrays.asList(str.split(",")));
        if (str.split(",").length > 2) {
            list1.remove(0);
            list1.remove(0);
        }
        if (StrUtil.isNotBlank(alipayDealOrderApp.getUserName())) {
            if (list1.contains(alipayDealOrderApp.getUserName())) {
                list1.clear();
                list1 = new ArrayList();
                list1.add(alipayDealOrderApp.getUserName());
            }
        }
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectSubMembersOrderList(list1, alipayDealOrderApp);
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        SysUser user = new SysUser();
        List<SysUser> sysUsers = userService.selectUserList(user);
        ConcurrentHashMap<String, SysUser> userCollect = new ConcurrentHashMap<String, SysUser>();
        for (SysUser user1 : sysUsers) {
            if (StrUtil.isNotBlank(user1.getMerchantId())) {
                userCollect.put(user1.getMerchantId(), user1);
            }
        }
        for (AlipayDealOrderApp order : list) {
            AlipayProductEntity product = prCollect.get(order.getRetain1());
            order.setUserName(userCollect.get(order.getOrderAccount()).getUserName());
            if (ObjectUtil.isNotNull(product)) {
                order.setRetain1(product.getProductName());
            }
        }
        prCollect = null;
        userCollect = null;
        return getDataTable(list);
    }

    /**
     * 显示统计table
     */
    @GetMapping("/statistics/merchant/admin/table")
    public String showTable() {
        return prefix + "/currentTable";
    }

    /**
     * 后台管理员商户交易订单统计（仅当天数据）
     */
    @PostMapping("/statistics/merchant/admin/order")
    @ResponseBody
    public TableDataInfo dayStat(StatisticsEntity statisticsEntity) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (StringUtils.isEmpty(sysUser.getMerchantId())) {
            throw new BusinessException("获取商户账户异常，请联系管理员");
        }
        statisticsEntity.setUserId(sysUser.getMerchantId());
        startPage();
        List<StatisticsEntity> list = alipayDealOrderAppService.selectMerchantStatisticsDataByDay(statisticsEntity, DateUtils.dayStart(), DateUtils.dayEnd());
        List<StatisticsEntity> dataList = new ArrayList();
        for (StatisticsEntity statis : list) {
            if (!statis.getUserId().equals("所有")) {
                dataList.add(statis);
            }
        }
        return getDataTable(dataList);
    }

    String getRate(String type) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", type);
        String params = JSON.toJSONString(data);
        String post = null;
        try {
            post = HttpUtil.get(otcRate, data);
        } catch (Exception e) {
            logger.error("获取汇率失败", e);
            return null;
        }
        return post;
    }


    /**
     * 产品状态修改（调用api）
     */
    @Log(title = "产品热冷修改", businessType = BusinessType.UPDATE)
    @PostMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(AlipayProductEntity alipayProductEntity) {
        String retain1 = alipayProductEntity.getRetain1();
        if (retain1.contains("trc")||retain1.contains("erc")){
            SysDictData sysDictData = new SysDictData();
            sysDictData.setDictCode(alipayProductEntity.getId().longValue());
            sysDictData.setStatus(String.valueOf(alipayProductEntity.getRetain1()
                    .replace("trc","").replace("erc","")));
            return toAjax(dictDataService.updateDictData(sysDictData));
        }
        return toAjax(iAlipayProductService.updateProductStatusNotifyById(alipayProductEntity));
    }

    /**
     * 查询产品列表列表
     */
    @PostMapping("/listProduct")
    @ResponseBody
    public TableDataInfo list(AlipayProductEntity alipayProductEntity) {
        startPage();
        SysDictData dictData = new SysDictData();
        dictData.setDictType("virtual_address_switch");
        List<SysDictData> sysDictData = dictDataService.selectDictDataList(dictData);
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        for (SysDictData sys : sysDictData) {
            AlipayProductEntity data = new AlipayProductEntity();
            data.setCreateTime(sys.getCreateTime());
            data.setStatus(Integer.valueOf(sys.getStatus()));
            data.setId(sys.getDictCode().intValue());
            if ("erc_switch".equals(sys.getDictValue())) {
                data.setRetain1("0".equals(sys.getStatus())?"3":"4");
                data.setProductCode("3");
                data.setProductId(sys.getDictValue());
                data.setProductName("erc开关");
                data.setDescribe(sys.getRemark());
            }
            if ("trc_switch".equals(sys.getDictValue())) {
                data.setRetain1("0".equals(sys.getStatus())?"5":"6");
                data.setProductCode("4");
                data.setProductId(sys.getDictValue());
                data.setProductName("trc开关");
                data.setDescribe(sys.getRemark());
            }
            data.setUpdateTime(sys.getUpdateTime());
            list.add(data);
        }
        return getDataTable(list);
    }


}

