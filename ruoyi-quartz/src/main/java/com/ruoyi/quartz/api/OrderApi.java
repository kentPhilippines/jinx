package com.ruoyi.quartz.api;

import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderApi {
    private static final String MARK = "-";
    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    private IAlipayDealOrderEntityService alipayDealOrderEntityService;
    @Autowired
    private IAlipayWithdrawEntityService alipayWithdrawEntityService;
    @Autowired
    private IAlipayRunOrderEntityService alipayRunOrderEntityService;

    /**
     * 订单核对
     * 核对1小时内订单是否正确
     */
    public void checkOrder() {
        int lock = 1;
        String strTime = "";
        String endTime = "";
        /**
         * 1，充值成功订单检查流水
         * 2，代付成功订单检查流水
         * 3，代付失败订单检查流水
         */

        //1，充值成功订单检查流水
        if (lock == 0)
            return;
        lock = 0;
        List<AlipayDealOrderApp> orderAppList = alipayDealOrderAppService.findOneHoursOrdeBySuccess(strTime, endTime);
        List<AlipayDealOrderEntity> orderEntityList = alipayDealOrderEntityService.findOneHoursOrderBySuccess(strTime, endTime);
        ConcurrentHashMap errorMap = new ConcurrentHashMap();
        ConcurrentHashMap<String, AlipayDealOrderApp> orderAppMap = orderAppList.stream().collect(Collectors.toConcurrentMap(AlipayDealOrderApp::getOrderId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        ConcurrentHashMap<String, AlipayDealOrderEntity> orderMap = orderEntityList.stream().collect(Collectors.toConcurrentMap(AlipayDealOrderEntity::getAssociatedId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        ConcurrentHashMap.KeySetView<String, AlipayDealOrderApp> strings = orderAppMap.keySet();
        for (String key : strings) {
            /**
             * 1,金额是否一致
             * 2,渠道是否一致
             * 3,账单流水是否一致
             */
            AlipayDealOrderApp alipayDealOrderApp = orderAppMap.get(key);
            AlipayDealOrderEntity alipayDealOrderEntity = orderMap.get(alipayDealOrderApp.getAppOrderId());

            Double orderAmount = alipayDealOrderApp.getOrderAmount();
            Double dealAmount = alipayDealOrderEntity.getDealAmount();
            if (orderAmount != dealAmount) {
                errorMap.put(alipayDealOrderEntity.getOrderId() + MARK + alipayDealOrderApp.getOrderId(), "订单金额异常：" + alipayDealOrderEntity.getOrderId() + "："
                        + alipayDealOrderEntity.getDealAmount() + "，" + alipayDealOrderApp.getOrderId() + "：" + alipayDealOrderApp.getOrderAmount() + "");
            }
            if (!alipayDealOrderApp.getRetain1().equals(alipayDealOrderEntity.getRetain1())) {
                errorMap.put(alipayDealOrderEntity.getOrderId() + MARK + alipayDealOrderApp.getOrderId(), "订单产品异常：" + alipayDealOrderEntity.getOrderId() + "："
                        + alipayDealOrderEntity.getRetain1() + "，" + alipayDealOrderApp.getOrderId() + "：" + alipayDealOrderApp.getRetain1() + "");
            }
            List<AlipayRunOrderEntity> runOrderList = alipayRunOrderEntityService.findAssociatedId(alipayDealOrderApp.getOrderId(), strTime, endTime);
            //充值手续费
            //充值加款
            int b = 2;
            for (AlipayRunOrderEntity runOrderEntity : runOrderList) {
                Integer runOrderType = runOrderEntity.getRunOrderType();
                if (runOrderType.equals(20)) {
                    b -= 1;
                }
                ;
                if (runOrderType.equals(21)) {
                    b -= 1;
                }
                ;
            }


        }


    }
}
