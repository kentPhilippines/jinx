package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.alipay.mapper.AlipayWithdrawEntityMapper;
import com.ruoyi.alipay.service.IAlipayWithdrawEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员提现记录Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayWithdrawEntityServiceImpl implements IAlipayWithdrawEntityService {
    @Resource
    private AlipayWithdrawEntityMapper alipayWithdrawEntityMapper;
    /**
     * 查询会员提现记录
     *
     * @param id 会员提现记录ID
     * @return 会员提现记录
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayWithdrawEntity selectAlipayWithdrawEntityById(Long id) {
        return alipayWithdrawEntityMapper.selectAlipayWithdrawEntityById(id);
    }

    /**
     * 查询会员提现记录列表
     *
     * @param alipayWithdrawEntity 会员提现记录
     * @return 会员提现记录
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayWithdrawEntity> selectAlipayWithdrawEntityList(AlipayWithdrawEntity alipayWithdrawEntity) {
        return alipayWithdrawEntityMapper.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public StatisticsEntity selectPayforStatisticsDataByDay(String dayStart, String dayEnd) {
        return alipayWithdrawEntityMapper.selectPayforStatDataByDay(dayStart, dayEnd);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<StatisticsEntity> statisticsWit(StatisticsEntity statisticsEntity) {
        /**
         * #########  查询类型包括
         * 1,账户类型
         * 2,代付金额累计
         * 3,代付手续费累计
         * 4,api 和后台代付划分
         * 5,不同渠道代付划分
         * ############以上是商·户维度数据
         */


        /**
         * ##############
         * 渠道维度数据
         * 1,账户类型
         * 2,代付金额累计
         * 3,代付手续费累计
         * 4,api 和后台代付划分
         */


        List<StatisticsEntity> staList = alipayWithdrawEntityMapper.statisticsWit(statisticsEntity);


        return staList;
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void updateWitStatus(Long id) {
        alipayWithdrawEntityMapper.updateWitStatus(id);
    }


    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayWithdrawEntity> findWitLimit(String starTime, String endTime, Integer page, Integer size) {
        return alipayWithdrawEntityMapper.findWitLimit(starTime, endTime, page, size);
    }
}
