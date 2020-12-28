package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayRunOrderEntity;

import java.util.List;

/**
 * 流水订单记录Service接口
 *
 * @author kiwi
 * @date 2020-03-18
 */
public interface IAlipayRunOrderEntityService {
    /**
     * 查询流水订单记录列表
     *
     * @param alipayRunOrderEntity 流水订单记录
     * @return 流水订单记录集合
     */
    List<AlipayRunOrderEntity> selectAlipayRunOrderEntityList(AlipayRunOrderEntity alipayRunOrderEntity);

    /**
     * 根据关联订单和时间查询数据
     *
     * @param orderId
     * @param strTime
     * @param endTime
     * @return
     */
    List<AlipayRunOrderEntity> findAssociatedId(String orderId, String strTime, String endTime);

    List<AlipayRunOrderEntity> findAssocidOrder(String orderId);

    List<AlipayRunOrderEntity> findUserOrderLimit(String userId, String str, String end);

    /**
     * 交易代理手续费
     *
     * @param yesToday
     * @param today
     * @return
     */
    Double sumDealOrderAgentFee(String yesToday, String today);

    /**
     * 商户代付手续费
     *
     * @param yesToday
     * @param today
     * @return
     */
    Double sumWitAppFee(String yesToday, String today);

    /**
     * 商户代付代理商分润
     *
     * @param yesToday
     * @param today
     * @return
     */
    Double witAgentFee(String yesToday, String today);
}
