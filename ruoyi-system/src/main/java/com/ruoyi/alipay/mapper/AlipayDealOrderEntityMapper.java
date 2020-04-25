package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.common.core.domain.StatisticsEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 交易订单Mapper接口
 *
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayDealOrderEntityMapper {
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

    List<AlipayDealOrderEntity> selectAlipayOrderList(AlipayDealOrderEntity alipayDealOrderEntity);

    /**
     * 修改交易订单
     *
     * @param alipayDealOrderEntity 交易订单
     * @return 结果
     */
    int updateAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity);

    @Select("select " +
            "COALESCE(SUM(dealAmount),0) totalAmount," +
            "COALESCE(SUM(CASE orderStatus WHEN 2 THEN dealAmount ELSE 0 END),0) successAmount," +
            "COUNT(1) totalCount," +
            "COUNT(CASE orderStatus WHEN 2 THEN orderId ELSE null END) successCount " +
            "from " +
            "alipay_deal_order where createTime BETWEEN #{dayStart} AND #{dayEnd} and orderType = 1")
    StatisticsEntity selectStatDateByDay(@Param("dayStart") String dayStart, @Param("dayEnd") String dayEnd);
}
