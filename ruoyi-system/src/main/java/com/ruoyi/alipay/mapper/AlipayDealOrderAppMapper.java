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
            "select orderAccount userId, " +
            "coalesce(sum(orderAmount),0.00) totalAmount," +
            "coalesce(sum(case orderStatus when 2 then orderAmount else 0 end),0) successAmount," +
            "count(*) totalCount," +
            "count(case orderStatus when 2 then id else null end) successCount ," +
            "coalesce(sum(retain3),0) fee ," +
            "coalesce(sum(case orderStatus when 2 then retain3 else 0 end),0) successFee ," +
            "DATE_FORMAT(createTime,'%Y%m%d%H') time " +
            "from alipay_deal_order_app " +
            "where createTime between #{statisticsEntity.params.dayStart} and #{statisticsEntity.params.dayEnd} and orderType = 1 " +
            "<if test = \"statisticsEntity.userId != null and statisticsEntity.userId != ''\">" +
            "and orderAccount = #{statisticsEntity.userId} " +
            "</if>" +
            "<if test = \"statisticsEntity.userAgent != null and statisticsEntity.userAgent != ''\">" +
            "and orderAccount in (select userId from alipay_user_info where agent = #{statisticsEntity.userAgent})" +
            "</if>" +
            "<if test = \"statisticsEntity.currency != null and statisticsEntity.currency != ''\">" +
            "and currency = #{statisticsEntity.currency} " +
            "</if>" +
            "group by orderAccount , time " +
            "</script>")
    List<StatisticsEntity> selectOrderAppStatDateByHours(@Param("statisticsEntity") StatisticsEntity statisticsEntity, @Param("dayStart") String dayStart, @Param("dayEnd") String dayEnd);

//    @Select("<script>" +
//            "select '所有' userId,p.productName productName," +
//            "coalesce(sum(app.orderAmount),0) totalAmount," +
//            "coalesce(sum(case app.orderStatus when 2 then app.orderAmount else 0 end),0) successAmount," +
//            "count(1) totalCount," +
//            "count(case app.orderStatus when 2 then app.id else null end) successCount ," +
//            "coalesce(sum(app.retain3),0) fee ," +
//            "coalesce(sum(case app.orderStatus when 2 then app.retain3 else 0 end),0) successFee " +
//            "from alipay_deal_order_app app left join alipay_product p on app.retain1 = p.productId " +
//            "where " +
//            "  app.createTime between #{statisticsEntity.params.dayStart} " +
//            "<if test = \"statisticsEntity.currency != null and statisticsEntity.currency != ''\">" +
//            "and app.currency = #{statisticsEntity.currency} " +
//            "</if>" +
//            " and #{statisticsEntity.params.dayEnd} and app.orderType = 1 " +
//                    "group by app.orderAccount,p.productName" +
//            "<if test = \"statisticsEntity.retain1 != null and statisticsEntity.retain1 != ''\">" +
//            "having p.productName = #{statisticsEntity.retain1} " +
//            "</if>" +
//            " union all " +
//            "select app.orderAccount userId,p.productName productName," +
//            "coalesce(sum(app.orderAmount),0.00) totalAmount," +
//            "coalesce(sum(case app.orderStatus when 2 then app.orderAmount else 0 end),0) successAmount," +
//            "count(1) totalCount," +
//            "count(case app.orderStatus when 2 then app.id else null end) successCount ," +
//            "coalesce(sum(app.retain3),0) fee ," +
//            "coalesce(sum(case app.orderStatus when 2 then app.retain3 else 0 end),0) successFee " +
//            "from alipay_deal_order_app app left join alipay_product p on app.retain1 = p.productId " +
//            "where " +
//            "  app.createTime between #{statisticsEntity.params.dayStart} " +
//            "and #{statisticsEntity.params.dayEnd} and app.orderType = 1 " +
//            "<if test = \"statisticsEntity.userId != null and statisticsEntity.userId != ''\">" +
//            "and app.orderAccount = #{statisticsEntity.userId} " +
//            "</if>" +
//            "<if test = \"statisticsEntity.userAgent != null and statisticsEntity.userAgent != ''\">" +
//            "and app.orderAccount in (select userId from alipay_user_info where agent = #{statisticsEntity.userAgent}) " +
//            "</if>" +
//            "<if test = \"statisticsEntity.currency != null and statisticsEntity.currency != ''\">" +
//            "and app.currency = #{statisticsEntity.currency} " +
//            "</if>" +
//            "group by app.orderAccount,p.productName" +
//            "<if test = \"statisticsEntity.retain1 != null and statisticsEntity.retain1 != ''\">" +
//            "having p.productName = #{statisticsEntity.retain1} " +
//            "</if>" +
//            "</script>")
    List<StatisticsEntity> selectOrderAppStatDateByDay(@Param("statisticsEntity") StatisticsEntity statisticsEntity, @Param("dayStart") String dayStart, @Param("dayEnd") String dayEnd);

    @Select("<script>" +
            "select orderAccount, appOrderId, orderType, orderStatus, createTime, orderAmount , retain1 ,"
            + " retain3 from alipay_deal_order_app where  1=1 " +
            "<if test = \"order.orderStatus != null and order.orderStatus != ''\">" +
            "and orderStatus = #{order.orderStatus} " +
            "</if>" +
            "  and  orderAccount in " +
            "<foreach item='item' index='index' collection='userIds' separator=',' open='(' close=')'>" +
            "#{item}" +
            "</foreach>" +
            " order by id desc </script>")
    List<AlipayDealOrderApp> selectSubAgentMembersOrderList(@Param("userIds") List<String> userIds, @Param("order") AlipayDealOrderApp alipayDealOrderApp);


    /**
     * 统计代理商分润情况，包括代付代理商分润和  交易代理商分润
     *
     * @param alipayDealOrderApp
     * @return
     */
    @Select("<script>" +
            "select * from (" +
            "                  select  sum(amount) as orderAmount , '充值代理分润' as userName ,  '当日汇总'  as orderAccount ,createTime as createTime " +
            "                  from   alipay_run_order  where runOrderType = 13  " +
            " and  createTime between #{order.params.dayStart}  and #{order.params.dayEnd} " +
            "                  union  all " +
            "                  select  sum(amount) as orderAmount, '代付代理分润'  as userName ," +
            "                         '当日汇总' as orderAccount , createTime as createTime" +
            "                  from   alipay_run_order  where runOrderType = 26 " +
            "and  createTime between #{order.params.dayStart}  and #{order.params.dayEnd} " +
            "   ) b" +
            "                  union  all " +
            "select * from (" +
            "                  select  sum(amount) as orderAmount , '充值代理分润' as userName , orderAccount  as orderAccount ,createTime as createTime " +
            "                  from   alipay_run_order  where runOrderType = 13  " +
            "<if test = \"order.orderAccount != null and order.orderAccount != ''\">" +
            "and orderAccount = #{order.orderAccount} " +
            "</if>" +
            " and  createTime between #{order.params.dayStart} and #{order.params.dayEnd} " +
            "                   group by  orderAccount  " +
            "                  union  all " +
            "                  select  sum(amount) as orderAmount, '代付代理分润'  as userName ," +
            "                         orderAccount as orderAccount , createTime as createTime" +
            "                  from   alipay_run_order  where runOrderType = 26 " +
            "<if test = \"order.orderAccount != null and order.orderAccount != ''\">" +
            "and orderAccount = #{order.orderAccount} " +
            "</if>" +
            "and   createTime between #{order.params.dayStart}  and #{order.params.dayEnd} " +
            " group by  orderAccount   ) a" +
            "</script>")
    List<AlipayDealOrderApp> listAgent(@Param("order") AlipayDealOrderApp alipayDealOrderApp);


    @Select(" select sum(retain3) from alipay_deal_order where orderStatus = 2 " +
            " and createTime between #{yesToday}  and #{today} ")
    Double sumDealorderProfit(@Param("yesToday") String yesToday, @Param("today") String today);

    /**
     * 查询商户订单登记列表
     *
     * @param alipayDealOrderApp 商户订单登记
     * @return 商户订单登记集合
     */
    AlipayDealOrderApp selectAlipayDealOrderApp(AlipayDealOrderApp alipayDealOrderApp);

}
