package com.ruoyi.alipay.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.ruoyi.alipay.domain.*;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.alipay.service.IAlipayRunOrderEntityService;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.alipay.service.Reconciliation;
import com.ruoyi.dealpay.domain.DealpayRunOrderEntity;
import com.ruoyi.dealpay.service.IDealpayRunOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReconciliationImpl implements Reconciliation {
    @Autowired
    IDealpayRunOrderService dealpayRunOrderServiceImpl;
    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;
    @Autowired
    private IAlipayRunOrderEntityService alipayRunOrderEntityService;
    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    @Override
    public Map<String, String> orderReconciliation(String orderId, AlipayDealOrderEntity orderEntity) {
        Map<String, String> map = new HashMap<>();
        StringBuffer str = new StringBuffer();
        /**
         * 交易订单核对
         * 交易订单成功   1，商户交易加款 20  2，商户交易手续费扣款 21 3，商户交易代理商分润 13
         */
        Integer addOrderAmount = 20; //商户交易加款
        Integer addOrderFeeAmount = 21; //商户交易手续费扣款
        Integer addOrderFeeAgentAmount = 13; //商户交易代理商分润
        List<AlipayRunOrderEntity> runList = alipayRunOrderEntityService.findAssocidOrder(orderId);
        ConcurrentHashMap<Integer, AlipayRunOrderEntity> userCollect = runList.stream().collect(Collectors.toConcurrentMap(AlipayRunOrderEntity::getRunOrderType, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        AlipayRunOrderEntity runOrderEntity20 = userCollect.get(addOrderAmount);
        AlipayRunOrderEntity runOrderEntity21 = userCollect.get(addOrderFeeAmount);
        AlipayRunOrderEntity runOrderEntity13 = userCollect.get(addOrderFeeAgentAmount);
        if (ObjectUtil.isNull(runOrderEntity13)) {
            AlipayUserInfo merchantInfoByUserId = alipayUserInfoService.findMerchantInfoByUserId(orderEntity.getOrderAccount());
            if (StrUtil.isNotEmpty(merchantInfoByUserId.getAgent())) {
                str.append("商代理商分润不存在");
            }
        }
        if (ObjectUtil.isNull(runOrderEntity21)) {
            str.append("商户交易手续费扣款流水不存在");
        }
        if (ObjectUtil.isNull(runOrderEntity20)) {
            str.append("商户交易加款流水不存在");
        }
        if (StrUtil.isNotEmpty(str.toString())) {
            map.put(ERROR_RECONCILIATION, "【当前订单：" + orderEntity.getAssociatedId() + " 异常：" + str.toString() + "】");
            return map;
        } else {
            map.put(SUCCESS_RECONCILIATION, "【当前订单：" + orderEntity.getAssociatedId() + ",流水正常】");
            return map;
        }
    }

    /**
     * 代付订单核对
     * 1，当前订单为成功 ------>  1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26
     * 2，当前订单为失败 ------>  1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26 4，商户代付失败解冻 8 5，丧户代付失败手续费解冻 22
     */
    @Override
    public Map<String, String> witReconciliation(String orderId, AlipayWithdrawEntity wit) {
        Map<String, String> map = new HashMap<>();
        StringBuffer str = new StringBuffer();
        Integer witSu = 10;
        Integer witSuFee = 9;
        Integer witSuPro = 26;
        Integer witEr = 8;
        Integer witErFee = 22;
        List<AlipayRunOrderEntity> runList = alipayRunOrderEntityService.findAssocidOrder(orderId);
        ConcurrentHashMap<Integer, AlipayRunOrderEntity> runCollect = runList.stream().collect(Collectors.toConcurrentMap(AlipayRunOrderEntity::getRunOrderType, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        if (wit.getOrderStatus().toString().equals("2")) {
            //1，当前订单为成功 ------>  1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26
            AlipayRunOrderEntity runOrderEntity10 = runCollect.get(witSu);
            AlipayRunOrderEntity runOrderEntity9 = runCollect.get(witSuFee);
            AlipayRunOrderEntity runOrderEntity26 = runCollect.get(witSuPro);
            if (ObjectUtil.isNull(runOrderEntity9)) {
                str.append("商户代付手续费不存在");
            }
            if (ObjectUtil.isNull(runOrderEntity10)) {
                str.append("商户代付扣款不存在");
            }
            if (ObjectUtil.isNull(runOrderEntity26)) {
                AlipayUserInfo merchantInfoByUserId = alipayUserInfoService.findMerchantInfoByUserId(wit.getUserId());
                if (StrUtil.isNotEmpty(merchantInfoByUserId.getAgent())) {
                    str.append("商户代付代理分润不存在");
                }
            }
            if (StrUtil.isNotEmpty(str.toString())) {
                map.put(ERROR_RECONCILIATION, "【当前订单：" + wit.getOrderId() + " 异常：" + str.toString() + "】");
                return map;
            } else {
                map.put(SUCCESS_RECONCILIATION, "【当前订单：" + wit.getOrderId() + ",流水正常】");
                return map;
            }
        } else if (wit.getOrderStatus().toString().equals("3")) {
            //2，当前订单为失败 ------> 1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26
            // 4，商户代付失败解冻 8 5，丧户代付失败手续费解冻 22
            AlipayRunOrderEntity runOrderEntity10 = runCollect.get(witSu);
            AlipayRunOrderEntity runOrderEntity9 = runCollect.get(witSuFee);
            AlipayRunOrderEntity runOrderEntity26 = runCollect.get(witSuPro);
            AlipayRunOrderEntity runOrderEntity8 = runCollect.get(witEr);
            AlipayRunOrderEntity runOrderEntity22 = runCollect.get(witErFee);
            if (ObjectUtil.isNull(runOrderEntity9)) {
                str.append("商户代付手续费不存在");
            }
            if (ObjectUtil.isNull(runOrderEntity10)) {
                str.append("商户代付扣款不存在");
            }
            if (ObjectUtil.isNull(runOrderEntity26)) {
                AlipayUserInfo merchantInfoByUserId = alipayUserInfoService.findMerchantInfoByUserId(wit.getUserId());
                if (StrUtil.isNotEmpty(merchantInfoByUserId.getAgent())) {
                    str.append("商户代付代理分润不存在");
                }
            }
            if (ObjectUtil.isNull(runOrderEntity8)) {
                str.append("商户代付失败解冻流水不存在");
            }
            if (ObjectUtil.isNull(runOrderEntity22)) {
                str.append("商户代付失败手续费解冻流水不存在");
            }
            if (StrUtil.isNotEmpty(str.toString())) {
                map.put(ERROR_RECONCILIATION, "【当前订单：" + wit.getOrderId() + " 异常：" + str.toString() + "】");
                return map;
            } else {
                map.put(SUCCESS_RECONCILIATION, "【当前订单：" + wit.getOrderId() + ",流水正常】");
                return map;
            }
        }
        return map;
    }


    /**
     * 商户余额流水对账
     *
     * @param fundBak  当日商户余额，交易量等情况
     * @param runList  当日商户所有流水情况
     * @param fundBak1 核对完账户详情
     * @return
     */
    @Override
    public Map<String, String> fund(AlipayUserFundEntity fundBak, List<AlipayRunOrderEntity> runList, AlipayUserFundEntity fundBak1) {
        Map<String, String> map = new HashMap<>();
        Double a = 0.0;
        for (AlipayRunOrderEntity run : runList) {
            if (run.getAmountType().equals("1")) {//当前账户支出
                a -= run.getAmount();
            } else {//当前账户收入
                a += run.getAmount();
            }
        }
        Double b = (fundBak.getAccountBalance() + a) - fundBak1.getAccountBalance();
        double abs = Math.abs(b);
        if ((fundBak.getAccountBalance() + a) == fundBak1.getAccountBalance() && abs > 1) {
            map.put(SUCCESS_RECONCILIATION, "【当前用户：" + fundBak.getUserId() + ",流水正常】");
            return map;
        } else {
            map.put(ERROR_RECONCILIATION, "【当前用户：" + fundBak.getUserId() + " 异常差额：" + abs + "，差额时间：" + DatePattern.NORM_DATETIME_FORMAT.format(fundBak.getCreateTime()) + "】");
            return map;
        }
    }


    /**
     * 当日账目汇总 所核对账目为 当日凌晨一点至前一日凌晨一点
     */
    @Override
    public void fundTask(String strTime) {
        /**
         * 1,确认对账时间
         * 2,获取对账时间内的所有流水数据
         * 3,核对当日利润
         * 4,记录利润及推送各负责人
         */
        String today = "";
        String format = "";
        if (StrUtil.isNotEmpty(strTime)) {
            today = strTime;
            format = DatePattern.PURE_DATE_FORMAT.format(DateUtil.parse(strTime));
        } else {
            today = DateUtil.today();
            format = DatePattern.PURE_DATE_FORMAT.format(new Date());
        }
        today = StrUtil.trim(today);
        today += " 01:00:00";
        String yesToday = DatePattern.NORM_DATETIME_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(today).toJdkDate(), -1));//2020-12-07 01:00:00
        /**
         * 【该计算模型随时调整】
         *  总利润为[profit]   =      交易利润[dealProfit]  +   代付利润[witProfit]
         *                  交易利润【根据交易订单金额核算】 =   商户交易订单渠道利润[sumProfit] -  商户交易订单代理手续费[sumDealAgentFee]
         *                  代付利润【根据交易流水核算】 =   商户代付手续费[wirAppFee] - 商户代付代理手续费[witAgentFee]
         */
        Double dealProfit = 0.0;
        Double sumProfit = alipayDealOrderAppService.sumDealorderProfit(yesToday, today);// 渠道手续费 - 商户手续费差值
        Double sumDealAgentFee = alipayRunOrderEntityService.sumDealOrderAgentFee(yesToday, today);//交易代理手续费
        dealProfit = NumberUtil.sub(sumProfit, sumDealAgentFee);//交易利润
        Double witProfit = 0.0;
        Double sumWitAppFee = alipayRunOrderEntityService.sumWitAppFee(yesToday, today);//商户代付利润汇总
        if (sumProfit < 0) {
            sumProfit *= -1;
        }
        if (sumDealAgentFee < 0) {
            sumDealAgentFee *= -1;
        }
        if (sumWitAppFee < 0) {
            sumWitAppFee *= -1;
        }
        Double witAgentFee = alipayRunOrderEntityService.witAgentFee(yesToday, today);//商户代付代理商抽成汇总
        if (witAgentFee < 0) {
            witAgentFee *= -1;
        }
        witProfit = NumberUtil.sub(sumWitAppFee, witAgentFee);//代付利润
        Double profit = 0.0;
        profit = dealProfit + witProfit;
        String msg = "核账日：" + yesToday + " - " + today + "，对账日商户交易毛利：" + sumProfit
                + "，对账日商户交易代理分润：" + sumDealAgentFee + "，对账日商户代付毛利：" + sumWitAppFee + "，当日商户代付手续费代理抽成：" + witAgentFee + "；综上，当前对账日纯利为：" + profit;
        String dealDescribe = "以上数据若存在实际偏差请重点检查对账日凌晨【1时左右【北京时间】】商户交易情况";
        try {
            insertFund(msg, profit, format);
            //  msg(msg);
            //    msg(dealDescribe);
        } catch (Exception e) {

        }

    }

    void insertFund(String deal, Double profit, String orderId) throws Exception {
        DealpayRunOrderEntity run = new DealpayRunOrderEntity();

        run.setOrderId(orderId);
        run.setAccountW("利润核对");
        run.setAcountR("未平");
        run.setAmount(profit);
        run.setAssociatedId(orderId);
        run.setAmountType("1");
        run.setGenerationIp("127.0.0.1");
        run.setDealDescribe(deal);
        run.setRunOrderType(8);//利润核对
        run.setOrderAccount("kent");
        run.setRunType("1");
        run.setSubmitTime(new Date());
        dealpayRunOrderServiceImpl.insertDealpayRunOrder(run);
    }

    void msg(String text1) {
        ThreadUtil.execute(() -> {
            String url = "http://172.29.17.155:8889/api/send?text=";
            String text = HttpUtil.encodeParams(text1, Charset.defaultCharset());
            String id = "&id=";
            String ids = "-1001464340513";
            id += ids;
            String s = url + text + id;
            HttpUtil.get(s);
        });
    }


}
