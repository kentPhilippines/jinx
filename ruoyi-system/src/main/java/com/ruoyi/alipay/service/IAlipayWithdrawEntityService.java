package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.enums.DataSourceType;

import java.util.List;

/**
 * 会员提现记录Service接口
 *
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayWithdrawEntityService {

    /**
     * 批量修改渠道
     * @param list
     */
    void batchUpdateChannel(List<AlipayWithdrawEntity> list);
    /**
     * 查询会员提现记录
     *
     * @param id 会员提现记录ID
     * @return 会员提现记录
     */
    AlipayWithdrawEntity selectAlipayWithdrawEntityById(Long id);

    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    void updateWithdrawEntityById(AlipayWithdrawEntity alipayWithdrawEntity);

    List<AlipayWithdrawEntity> selectAlipayWithdrawEntityByIds(String ids);

    /**
     * 查询会员提现记录列表
     *
     * @param alipayWithdrawEntity 会员提现记录
     * @return 会员提现记录集合
     */
    List<AlipayWithdrawEntity> selectAlipayWithdrawEntityList(AlipayWithdrawEntity alipayWithdrawEntity);

    /**
     * 查询商户提现统计数据
     *
     * @param dayStart
     * @param dayEnd
     * @return
     */
    StatisticsEntity selectPayforStatisticsDataByDay(String dayStart, String dayEnd);


    /**
     * 代付数据统计
     *
     * @param statisticsEntity
     * @return
     */
    List<StatisticsEntity> statisticsWit(StatisticsEntity statisticsEntity);

    /**
     * 修改代付订单为已推送状态
     *
     * @param id
     */
    void updateWitStatus(Long id);

    List<AlipayWithdrawEntity> findWitLimit(String starTime, String endTime, Integer page, Integer size);

    AlipayWithdrawEntity selectAlipayWithdrawEntityListSum(AlipayWithdrawEntity alipayWithdrawEntity);


    /**
     * 修改代付订单是否处于挂起状态
     *
     * @param orderId
     * @param moreMacth
     * @return
     */
    int updateMacthMore(String orderId, Integer moreMacth);


    void batchUpdateMacthMore(String orderIds, Integer moreMacth, String watingTime);
    void batchUpdateMacthMoreWatingTime(String ids, String watingTime);

}
