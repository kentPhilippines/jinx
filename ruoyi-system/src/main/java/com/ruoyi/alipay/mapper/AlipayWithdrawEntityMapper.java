package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.common.core.domain.StatisticsEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 会员提现记录Mapper接口
 *
 * @author kent
 * @date 2020-03-17
 */
public interface AlipayWithdrawEntityMapper {
    int updateByPrimaryKeySelective(AlipayWithdrawEntity record);
    /**
     * 查询会员提现记录
     *
     * @param id 会员提现记录ID
     * @return 会员提现记录
     */
    AlipayWithdrawEntity selectAlipayWithdrawEntityById(Long id);

    /**
     * 查询会员提现记录
     *
     * @param ids 会员提现记录ID
     * @return 会员提现记录
     */
    List<AlipayWithdrawEntity> selectAlipayWithdrawEntityByIds(String[] ids);

    /**
     * 查询会员提现记录列表
     *
     * @param alipayWithdrawEntity 会员提现记录
     * @return 会员提现记录集合
     */
    List<AlipayWithdrawEntity> selectAlipayWithdrawEntityList(AlipayWithdrawEntity alipayWithdrawEntity);


    @Select("select " +
            "COALESCE(SUM(amount),0) totalAmount," +
            "COALESCE(SUM(CASE orderStatus WHEN 2 THEN amount ELSE 0 END),0) successAmount," +
            "COUNT(1) totalCount," +
            "COUNT(CASE orderStatus WHEN 2 THEN orderId ELSE null END) successCount " +
            "from " +
            "alipay_withdraw where createTime BETWEEN #{dayStart} AND #{dayEnd} and withdrawType = 1 and status = 1")
    StatisticsEntity selectPayforStatDataByDay(@Param("dayStart") String dayStart, @Param("dayEnd") String dayEnd);


    @Select("<script>" +

            "select " +
            " '所有' as userId ," +
            "COALESCE(SUM(amount),0) totalAmount," +
            "COALESCE(SUM(CASE orderStatus WHEN 2 THEN amount ELSE 0 END),0) successAmount," +
            "COALESCE(SUM(CASE orderStatus WHEN 2 THEN fee ELSE 0 END),0) successFee," +
            "COUNT(1) totalCount," +
            "COUNT(CASE orderStatus WHEN 2 THEN orderId ELSE null END) successCount " +
            "from " +
            "alipay_withdraw where " +
            "createTime BETWEEN #{statisticsEntity.params.dayStart} AND #{statisticsEntity.params.dayEnd} " +
            "and withdrawType = 1 and status = 1 " +
             " union all " +
            "select " +
            " userId as userId ," +
            "COALESCE(SUM(amount),0) totalAmount," +
            "COALESCE(SUM(CASE orderStatus WHEN 2 THEN amount ELSE 0 END),0) successAmount," +
            "COALESCE(SUM(CASE orderStatus WHEN 2 THEN fee ELSE 0 END),0) successFee," +
            "COUNT(1) totalCount," +
            "COUNT(CASE orderStatus WHEN 2 THEN orderId ELSE null END) successCount " +
            "from " +
            "alipay_withdraw where " +
            "createTime BETWEEN #{statisticsEntity.params.dayStart} AND #{statisticsEntity.params.dayEnd} " +
            "and withdrawType = 1 and status = 1 " +
            "<if test = \"statisticsEntity.userId != null and statisticsEntity.userId != ''\"> " +
            "and userId = #{statisticsEntity.userId} " +
            "</if>" +
            "group by userId " +
            "</script>")
    List<StatisticsEntity> statisticsWit(@Param("statisticsEntity") StatisticsEntity statisticsEntity);


    @Update("update alipay_withdraw set orderStatus = 4 where id = #{id}")
    void updateWitStatus(@Param("id") Long id);


    @Select("select * from alipay_withdraw where  createTime between #{starTime} and #{endTime} limit #{page} , #{size} ")
    List<AlipayWithdrawEntity> findWitLimit(@Param("starTime") String starTime, @Param("endTime") String endTime, @Param("page") Integer page, @Param("size") Integer size);

    AlipayWithdrawEntity selectAlipayWithdrawEntityListSum(AlipayWithdrawEntity alipayWithdrawEntity);
    @Update("update alipay_withdraw set moreMacth = #{moreMacth} where orderId = #{orderId}")
    int updateMacthMore(@Param("orderId")String orderId, @Param("moreMacth") Integer moreMacth);

    @Update("update alipay_withdraw set moreMacth = #{moreMacth}   where id = #{id}")
    int updateMacthMoreById(@Param("id") String id, @Param("moreMacth") Integer moreMacth);
    @Update("update alipay_withdraw set watingTime = #{watingTime}   where id = #{id}")
    void batchUpdateMacthMoreWatingTime(@Param("id") String id,@Param("watingTime") Integer watingTime);

    @Delete("  delete from alipay_withdraw where userId = #{userId} ")
    void deleteUserId(@Param("userId") String userId);

}
