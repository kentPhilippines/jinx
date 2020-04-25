package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.common.core.domain.StatisticsEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商户订单登记Mapper接口
 *
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayDealOrderAppMapper {
    /**
     * 查询商户订单登记
     *
     * @param id 商户订单登记ID
     * @return 商户订单登记
     */
    AlipayDealOrderApp selectAlipayDealOrderAppById(Long id);

    /**
     * 查询商户订单登记列表
     *
     * @param alipayDealOrderApp 商户订单登记
     * @return 商户订单登记集合
     */
    List<AlipayDealOrderApp> selectAlipayDealOrderAppList(AlipayDealOrderApp alipayDealOrderApp);

    /**
     * 新增商户订单登记
     *
     * @param alipayDealOrderApp 商户订单登记
     * @return 结果
     */
    int insertAlipayDealOrderApp(AlipayDealOrderApp alipayDealOrderApp);

    /**
     * 修改商户订单登记
     *
     * @param alipayDealOrderApp 商户订单登记
     * @return 结果
     */
    int updateAlipayDealOrderApp(AlipayDealOrderApp alipayDealOrderApp);

    @Select("<script>" +
            "select orderAccount, appOrderId, orderType, orderStatus, createTime, orderAmount from alipay_deal_order_app where orderAccount in " +
            "<foreach item='item' index='index' collection='userIds' separator=',' open='(' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<AlipayDealOrderApp> selectSubAgentMembersOrderList(@Param("userIds") List<String> userIds);

    @Select("select " +
            "COALESCE(SUM(orderAmount),0) totalAmount," +
            "COALESCE(SUM(CASE orderStatus WHEN 2 THEN orderAmount ELSE 0 END),0) successAmount," +
            "COUNT(1) totalCount," +
            "COUNT(CASE orderStatus WHEN 2 THEN orderId ELSE null END) successCount " +
            "from " +
            "alipay_deal_order_app where createTime BETWEEN #{dayStart} AND #{dayEnd} and orderType = 1")
    StatisticsEntity selectOrderAppStatDateByDay(@Param("dayStart") String dayStart, @Param("dayEnd") String dayEnd);
}
