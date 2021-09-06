package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.mapper.AlipayDealOrderAppMapper;
import com.ruoyi.alipay.mapper.AlipayDealOrderEntityMapper;
import com.ruoyi.alipay.service.IAlipayDealOrderEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.core.domain.StatisticsMerchantEntity;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 交易订单Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayDealOrderEntityServiceImpl implements IAlipayDealOrderEntityService {
    @Resource
    private AlipayDealOrderEntityMapper alipayDealOrderEntityMapper;
    @Resource
    private AlipayDealOrderAppMapper alipayDealOrderAppMapper;

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
    public List<StatisticsMerchantEntity> selectStatisticsMerchantDataByDate(StatisticsMerchantEntity statisticsMerchantEntity) {
        return alipayDealOrderEntityMapper.selectStatMerchantDateByDay(statisticsMerchantEntity);
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

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayDealOrderEntity> findOrderLimit(String starTime, String endTime, Integer page, Integer size) {
        return alipayDealOrderEntityMapper.findOrderLimit(starTime, endTime, page, size);
    }

    /**
     * 临时修改订单渠道方，以及渠道方结算
     *
     * @param orderId
     * @param userId
     * @param orderQr
     * @param id
     * @param fee
     * @param profit
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int updateOrderQr(String orderId, String userId, String orderQr, Long feeId, Double fee, Double profit) {
        return alipayDealOrderEntityMapper.updateOrderQr(orderId, userId, orderQr, feeId, fee, profit);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayDealOrderEntity findOrderByOrderId(String order) {
        return alipayDealOrderEntityMapper.findOrderByOrderId(order);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayDealOrderEntity selectAlipayDealOrderEntityListSum(AlipayDealOrderEntity alipayDealOrderEntity) {
        return alipayDealOrderEntityMapper.selectAlipayDealOrderEntityListSum(alipayDealOrderEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    @Transactional
    public int insertAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity, AlipayDealOrderApp alipayDealOrderApp) {
        int i = alipayDealOrderAppMapper.insertAlipayDealOrderApp(alipayDealOrderApp);
        if (i < 1) {
            throw new RuntimeException("商户数据插入失败");
        }
        int i1 = alipayDealOrderEntityMapper.insertAlipayDealOrderEntity(alipayDealOrderEntity);
        if (i < 1) {
            throw new RuntimeException("主订单数据插入失败");
        }
        return i1;
    }



}
