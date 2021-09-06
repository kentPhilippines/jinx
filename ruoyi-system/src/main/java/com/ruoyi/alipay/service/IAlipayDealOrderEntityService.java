package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.core.domain.StatisticsMerchantEntity;

import java.util.List;

/**
 * 交易订单Service接口
 *
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayDealOrderEntityService {
    /**
     * 查询交易订单
     *
     * @param id 交易订单ID
     * @return 交易订单
     */
    AlipayDealOrderEntity selectAlipayDealOrderEntityById(Long id);

    /**
     * 查询交易订单列表
     *
     * @param alipayDealOrderEntity 交易订单
     * @return 交易订单集合
     */
    List<AlipayDealOrderEntity> selectAlipayDealOrderEntityList(AlipayDealOrderEntity alipayDealOrderEntity);

    /**
     * 根据财务角色查看财务角色特定的  交易订单
     *
     * @param alipayDealOrderEntity 交易订单
     * @return 交易订单集合
     */
    List<AlipayDealOrderEntity> selectAlipayOrderList(AlipayDealOrderEntity alipayDealOrderEntity);

    /**
     * 修改交易订单
     *
     * @param alipayDealOrderEntity 交易订单
     * @return 结果
     */
    int updateAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity);

    /**
     * 根据时间查询统计数据
     *
     * @param dayStart 开始时间
     * @param dayEnd   结束时间
     * @return 返回结果
     */
    List<StatisticsEntity> selectStatisticsDataByDate(StatisticsEntity statisticsEntity, String dayStart, String dayEnd);
    /**
     * 根据时间查询商户统计数据
     *
     * @return 返回结果
     */
    List<StatisticsMerchantEntity> selectStatisticsMerchantDataByDate(StatisticsMerchantEntity statisticsMerchantEntity);

    List<StatisticsEntity> selectStatisticsDataByHours(StatisticsEntity statisticsEntity, String dayStart, String dayEnd);


    List<AlipayDealOrderEntity> findOneHoursOrderBySuccess(String strTime, String endTime);

    List<AlipayDealOrderEntity> findOrderLimit(String starTime, String endTime, Integer page, Integer size);


    /**
     * 修改订单渠道方，并将当前渠道方结算方式做出修改
     *
     * @param orderId
     * @param userId
     * @param orderQr
     * @param id
     * @param fee
     * @param profit
     * @return
     */
    int updateOrderQr(String orderId, String userId, String orderQr, Long id, Double fee, Double profit);


    AlipayDealOrderEntity findOrderByOrderId(String order);

    AlipayDealOrderEntity selectAlipayDealOrderEntityListSum(AlipayDealOrderEntity alipayDealOrderEntity);

    int  insertAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity, AlipayDealOrderApp alipayDealOrderApp);


}
