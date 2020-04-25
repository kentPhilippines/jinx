package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.common.core.domain.StatisticsEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 会员提现记录Mapper接口
 *
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayWithdrawEntityMapper {
    /**
     * 查询会员提现记录
     *
     * @param id 会员提现记录ID
     * @return 会员提现记录
     */
    AlipayWithdrawEntity selectAlipayWithdrawEntityById(Long id);

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
}
