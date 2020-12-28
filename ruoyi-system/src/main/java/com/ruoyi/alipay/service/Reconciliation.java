package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;

import java.util.List;
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


    /**
     * 商户余额对账
     *
     * @param fundBak  当日商户余额，交易量等情况
     * @param runList  当日商户所有流水情况
     * @param fundBak1 核对完账户详情
     * @return
     */
    Map<String, String> fund(AlipayUserFundEntity fundBak, List<AlipayRunOrderEntity> runList, AlipayUserFundEntity fundBak1);

    /**
     * 当日账目汇总
     */
    void fundTask(String strTime);
}
