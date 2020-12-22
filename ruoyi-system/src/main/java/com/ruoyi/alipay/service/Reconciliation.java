package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;

import java.util.Map;

public interface Reconciliation {
    final static String SUCCESS_RECONCILIATION = "SUCCESS_RECONCILIATION";
    final static String ERROR_RECONCILIATION = "ERROR_RECONCILIATION";

    /**
     * 交易订单核对
     *
     * @param orderId
     * @return
     */
    Map<String, String> orderReconciliation(String orderId, AlipayDealOrderEntity orderEntity);

    /**
     * 代付订单核对
     *
     * @param orderId
     * @param wit
     * @return
     */
    Map<String, String> witReconciliation(String orderId, AlipayWithdrawEntity wit);

}
