package com.ruoyi.web.controller.alipay;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.*;
import com.ruoyi.alipay.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.core.domain.StatisticsMerchantEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 交易订单Controller
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/orderDeal")
public class AlipayDealOrderEntityController extends BaseController {
    private String prefix = "alipay/orderDeal";
    private String code_prefix = "alipay/file";
    @Autowired
    private DictionaryUtils dictionaryUtils;
    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;
    @Autowired
    private IAlipayDealOrderEntityService alipayDealOrderEntityService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IAlipayProductService iAlipayProductService;
    @Autowired
    private IAlipayUserRateEntityService iAlipayUserRateEntityService;
    @Autowired
    private IAlipayChanelFeeService alipayChanelFeeService;
    @Autowired
    private IAlipayUserRateEntityService alipayUserRateEntityService;
    @Autowired
    private IAlipayWithdrawEntityService alipayWithdrawEntityService;

    @GetMapping()
    @RequiresPermissions("orderDeal:qr:view")
    public String orderDeal(ModelMap mmap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        alipayProductEntity.setProductCode("2");
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        List<AlipayUserFundEntity> rateList = alipayUserFundEntityService.findUserFundRate();
        mmap.put("rateList", rateList);
        return prefix + "/orderDeal";
    }

    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;

    /**
     * 查询交易订单列表
     */
    @PostMapping("/list")
    @RequiresPermissions("orderDeal:qr:list")
    @ResponseBody
    public TableDataInfo list(AlipayDealOrderEntity alipayDealOrderEntity) {
        startPage();
        List<AlipayDealOrderEntity> list = alipayDealOrderEntityService
                .selectAlipayDealOrderEntityList(alipayDealOrderEntity);
        SysUser user = new SysUser();
        List<SysUser> sysUsers = userService.selectUserList(user);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        List<AlipayProductEntity> productlist = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        List<AlipayUserFundEntity> listFund = alipayUserFundEntityService.findUserFundAll();
        ConcurrentHashMap<String, AlipayUserFundEntity> userCollect1 = listFund.stream().collect(Collectors.toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productlist.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        ConcurrentHashMap<String, SysUser> userCollect = sysUsers.stream().filter(e -> StrUtil.isNotEmpty(e.getMerchantId())).collect(Collectors.toConcurrentMap(SysUser::getMerchantId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        list.stream().forEach(
                o -> {
                    try {
                        o.setRetain1(prCollect.get(o.getRetain1()).getProductName());
                        o.setChannelName(userCollect1.get(o.getOrderQrUser()).getUserName());
                        o.setUserName(userCollect.get(o.getOrderAccount()).getUserName());
                    }catch ( Throwable e  ){
                        logger.error("错误 ：",e);
                    }
                }
        );
        AlipayDealOrderEntity deal = alipayDealOrderEntityService.selectAlipayDealOrderEntityListSum(alipayDealOrderEntity);
        if (null != deal && CollUtil.isNotEmpty(list)) {
            for (int mark = 0; mark < 1; mark++) {
                list.get(mark).setSunCountAmountFee(deal.getSunCountAmountFee());
                list.get(mark).setSunCountAmount(deal.getSunCountAmount());
                list.get(mark).setSunCountActualAmount(deal.getSunCountActualAmount());
            }
        }
        return getDataTable(list);
    }

    /**
     * 交由财务处理
     */
    @PostMapping("/updataOrder")
    @RequiresPermissions("orderDeal:qr:status")
    @ResponseBody
    public AjaxResult updataOrder(String id) {
        AlipayDealOrderEntity order = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(Long.valueOf(id));
        order.setOrderStatus("7");//人工处理
        int i = alipayDealOrderEntityService.updateAlipayDealOrderEntity(order);
        return toAjax(i);
    }


    /**
     * 代付主交易订单修改卡商账户
     *
     * @param
     * @return
     */
    @GetMapping("/updateBankCardShow/{userId}")
    @RequiresPermissions("orderDeal:qr:status:updateBankCardShow")
    public String updateBankCardShow(ModelMap mmap, @PathVariable("userId") String orderId) {
        List<AlipayUserFundEntity> listFund = alipayUserFundEntityService.findUserFundAllToBank();
        mmap.put("listFund", listFund);
        mmap.put("orderId", orderId);
        return prefix + "/updateBankCardEdit";
    }


    /**
     * 代付主交易订单修改卡商账户
     */
    @PostMapping("/updateBankCard")
    @RequiresPermissions("orderDeal:qr:status:updateBankCard")
    @ResponseBody
    public AjaxResult updateBankCard(String orderId, String userId) {
        AlipayDealOrderEntity orderEntityList = alipayDealOrderEntityService.findOrderByOrderId(orderId);
        AlipayUserRateEntity rate = iAlipayUserRateEntityService.findWitRate(userId);
        Double fee = rate.getFee();
        Double dealAmount = orderEntityList.getDealAmount();
        fee = fee * dealAmount;
        Double profit = Double.valueOf(orderEntityList.getRetain3());
        profit = Double.valueOf(orderEntityList.getDealFee()) - fee;
        return toAjax(alipayDealOrderEntityService.updateOrderQr(orderId, userId, "", rate.getId(), fee, profit));
    }


    /**
     * 导出交易订单列表
     */
    @RequiresPermissions("orderDeal:qr:export")
    @Log(title = "码商交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayDealOrderEntity alipayDealOrderEntity) {
        List<AlipayDealOrderEntity> list = alipayDealOrderEntityService
                .selectAlipayDealOrderEntityList(alipayDealOrderEntity);
        //0xfaaaf2673d5117d05656b244578ac7c74026c5b1:汇率:6.19: 数量:8078.03
        for (AlipayDealOrderEntity orderEntity : list) {
            if (!orderEntity.getExternalOrderId().contains("USDT")) {
                continue;
            }
            String orderQr = orderEntity.getOrderQr();
            String[] split = orderQr.split(":");
            String rate = split[1] + ":" + split[2];
            String count = split[3] + ":" + split[4];
            orderEntity.setCount(count);
            orderEntity.setRate(rate);
            orderEntity.setAddress(split[0]);
        }
        ExcelUtil<AlipayDealOrderEntity> util = new ExcelUtil<AlipayDealOrderEntity>(AlipayDealOrderEntity.class);
        return util.exportExcel(list, "orderDeal");
    }

    /**
     * 显示交易订单详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrderEntity alipayDealOrderEntity = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(id);
        mmap.put("alipayDealOrderEntity", alipayDealOrderEntity);
        return prefix + "/edit";
    }

    /**
     * 显示二维码
     *
     * @param imgId
     * @param mmap
     * @return
     */
    @GetMapping("/showCode/{imgId}")
    public String showCode(@PathVariable("imgId") String imgId, ModelMap mmap) {
        //获取二维码服务器地址
        String qrServerAddr = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_QR_CODE_SERVER_ADDR_KEY, StaticConstants.ALIPAY_QR_CODE_SERVER_ADDR_VALUE);
        String qrServerPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_QR_CODE_SERVER_ADDR_KEY, StaticConstants.ALIPAY_QR_CODE_SERVER_ADDR_PATH);
        mmap.put("imgUrl", qrServerAddr + qrServerPath + imgId);
        return code_prefix + "/view_code";
    }


    @Log(title = "交易订单", businessType = BusinessType.UPDATE)
    @PostMapping("/renotify")
    @ResponseBody
    public AjaxResult renotify(AlipayDealOrderEntity alipayDealOrderEntity) {
        //调用通知方法
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_8);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("orderId", alipayDealOrderEntity.getOrderId());
        return HttpUtils.adminGet2Gateway(mapParam, ipPort + urlPath);
    }

    /**
     * 显示统计table
     */
    @GetMapping("/statistics/qr/table")
    public String showTable() {
        return prefix + "/currentTable";
    }

    /**
     * 显示统计table
     */
    @GetMapping("/statistics/merchant/table")
    public String showMerchantTable() {
        return prefix + "/merchant";
    }

    /**
     * 显示具体统计内容
     */
    @PostMapping("/statistics/qr/order")
    @RequiresPermissions("orderDeal:qr:statistics")
    @ResponseBody
    public TableDataInfo dayStat(StatisticsEntity statisticsEntity) {
        startPage();
        List<StatisticsEntity> list = alipayDealOrderEntityService.selectStatisticsDataByDate(statisticsEntity, DateUtils.dayStart(), DateUtils.dayEnd());
        List<AlipayUserFundEntity> listFund = alipayUserFundEntityService.findUserFundAll();
        ConcurrentHashMap<String, AlipayUserFundEntity> userCollect = listFund.stream().collect(Collectors.toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        for (StatisticsEntity sta : list) {
            if (ObjectUtil.isNotNull(userCollect.get(sta.getUserId()))) {
                sta.setUserName(userCollect.get(sta.getUserId()).getUserName());
            }
        }
        return getDataTable(list);
    }


    /**
     * 显示具体统计内容
     */
    @PostMapping("/statistics/merchant/order")
    @RequiresPermissions("orderDeal:merchant:statistics")
    @ResponseBody
    public TableDataInfo merchantdayStat(StatisticsMerchantEntity statisticsMerchantEntity) {
        startPage();
        List<StatisticsMerchantEntity> statisticsMerchantEntities = alipayDealOrderEntityService.selectStatisticsMerchantDataByDate(statisticsMerchantEntity);
        return getDataTable(statisticsMerchantEntities);
    }

    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    /**
     * 显示补单编辑页
     */
    @GetMapping("/backOrder/{id}")
    public String backOrder(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrderEntity alipayDealOrderEntity = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(id);
        mmap.put("alipayDealOrderEntity", alipayDealOrderEntity);
        return prefix + "/backOrder";
    }

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/onlineCardUrl/{id}")
    public String onlineCardUrl(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrderEntity alipayDealOrderEntity = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(id);
        mmap.put("alipayDealOrderEntity", alipayDealOrderEntity);

        //获取大于该笔订单金额的所有未选取的出款订单
        // //要求  无法匹配到 当前代理下面所有商户的出款订单  1， 获取当前代理  2 非当前代理下的出款订单
        String orderAccount = alipayDealOrderEntity.getOrderAccount();
        AlipayUserInfo agent = findAgent(orderAccount);
        List<String> sonUser = alipayUserInfoService.findSonUser(agent.getUserId());
        //  List<AlipayWithdrawEntity> witList =     alipayWithdrawEntityService.findwitInNotThis(sonUser,"MyChannel",4);
        //获取符合要求的出款订单


        return prefix + "/onlineCard";
    }

    AlipayUserInfo findAgent(String userId) {
        AlipayUserInfo userInfo = alipayUserInfoService.findUserByUserId(userId);
        if (StrUtil.isEmpty(userInfo.getAgent())) {
            return userInfo;
        } else {
            return findAgent(userInfo.getAgent());
        }
    }


    /**
     * 补单数据保存
     */
    @Log(title = "补单信息", businessType = BusinessType.INSERT)
    @PostMapping("/backOrderSave")
    @ResponseBody
    public AjaxResult backOrderSave(AlipayDealOrderEntity alipayDealOrderEntity) {
        int i = 0;
        try {
            AlipayDealOrderEntity dataOrigin = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(alipayDealOrderEntity.getId());
            if (dataOrigin == null) {
                AjaxResult.error();
            }
            logger.info("【当前操作为补单操作，操作备注为：" + alipayDealOrderEntity.getDealDescribe() + "，操作订单号为：" + dataOrigin.getOrderId() + "】");
            AlipayDealOrderApp alipayDealOrderApp = new AlipayDealOrderApp();
            alipayDealOrderApp.setOrderId(dataOrigin.getAssociatedId());
            AlipayDealOrderApp data = alipayDealOrderAppService.selectAlipayDealOrderApp(alipayDealOrderApp);
            if (data == null) {
                return AjaxResult.error();
            }
            data.setSubmitTime(new Date());
            data.setCreateTime(new Date());
            data.setOrderId(data.getOrderId() + "_1");
            dataOrigin.setExternalOrderId(dataOrigin.getOrderId());
            data.setAppOrderId(data.getAppOrderId() + "_1");
            dataOrigin.setOrderId(dataOrigin.getOrderId() + "_1");
            dataOrigin.setAssociatedId(dataOrigin.getAssociatedId() + "_1");
            dataOrigin.setSubmitTime(new Date());
            dataOrigin.setCreateTime(new Date());
            //更新金额
            dataOrigin.setDealAmount(alipayDealOrderEntity.getDealAmount());
            dataOrigin.setActualAmount(alipayDealOrderEntity.getDealAmount());
            //获取费率
            String product = dataOrigin.getRetain1();//产品类型
            String orderQrUser = dataOrigin.getOrderQrUser();//渠道类型
            AlipayUserRateEntity userRate = alipayUserRateEntityService.findUserByChannel(dataOrigin.getOrderAccount(), product, orderQrUser);
            AlipayChanelFee channelFee = alipayChanelFeeService.findChannelBy(orderQrUser, product);
            String channel = channelFee.getChannelRFee();
            Double channelDFee = Double.valueOf(channel);
            Double fee = userRate.getFee();//当前用户交易费率
            //用户当前手续费为
            double dealFee = fee * dataOrigin.getDealAmount();
            double feec = channelDFee * dataOrigin.getDealAmount();
            dataOrigin.setDealFee(dealFee);
            dataOrigin.setRetain3(feec + "");
            data.setRetain3(dealFee + "");
            String orderQr = dataOrigin.getOrderQr();
            if (StrUtil.isEmpty(orderQr)) {
                orderQr = "";
            }
            dataOrigin.setOrderStatus("7");
            data.setOrderStatus("1");
            dataOrigin.setRetain4("1");//结算标记
            data.setOrderAmount(dataOrigin.getDealAmount());
            dataOrigin.setActualAmount(dataOrigin.getDealAmount() - dataOrigin.getDealFee());
            dataOrigin.setOrderQr(orderQr + "【操作备注：】" + alipayDealOrderEntity.getDealDescribe());
            return toAjax(alipayDealOrderEntityService.insertAlipayDealOrderEntity(dataOrigin, data));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

}
