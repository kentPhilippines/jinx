package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.mapper.AlipayDealOrderEntityMapper;
import com.ruoyi.alipay.service.IAlipayDealOrderEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交易订单Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayDealOrderEntityServiceImpl implements IAlipayDealOrderEntityService {
    @Autowired
    private AlipayDealOrderEntityMapper alipayDealOrderEntityMapper;

    /**
     * 查询交易订单
     *
     * @param id 交易订单ID
     * @return 交易订单
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayDealOrderEntity selectAlipayDealOrderEntityById(Long id) {
        return alipayDealOrderEntityMapper.selectAlipayDealOrderEntityById(id);
    }

    /**
     * 查询交易订单列表
     *
     * @param alipayDealOrderEntity 交易订单
     * @return 交易订单
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayDealOrderEntity> selectAlipayDealOrderEntityList(AlipayDealOrderEntity alipayDealOrderEntity) {
        return alipayDealOrderEntityMapper.selectAlipayDealOrderEntityList(alipayDealOrderEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayDealOrderEntity> selectAlipayOrderList(AlipayDealOrderEntity alipayDealOrderEntity) {
        return alipayDealOrderEntityMapper.selectAlipayOrderList(alipayDealOrderEntity);
    }

    /**
     * 修改交易订单
     *
     * @param alipayDealOrderEntity 交易订单
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int updateAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity) {
        return alipayDealOrderEntityMapper.updateAlipayDealOrderEntity(alipayDealOrderEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<StatisticsEntity> selectStatisticsDataByDate(StatisticsEntity statisticsEntity, String dayStart, String dayEnd) {
        return alipayDealOrderEntityMapper.selectStatDateByDay(statisticsEntity, dayStart, dayEnd);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<StatisticsEntity> selectStatisticsDataByHours(StatisticsEntity statisticsEntity, String dayStart, String dayEnd) {
        return alipayDealOrderEntityMapper.selectStatDateByHours(statisticsEntity, dayStart, dayEnd);
    }

    @Override
    public List<AlipayDealOrderEntity> findOneHoursOrderBySuccess(String strTime, String endTime) {
        return null;
    }

}
