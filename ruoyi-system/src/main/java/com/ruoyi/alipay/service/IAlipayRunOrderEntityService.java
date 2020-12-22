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
}
