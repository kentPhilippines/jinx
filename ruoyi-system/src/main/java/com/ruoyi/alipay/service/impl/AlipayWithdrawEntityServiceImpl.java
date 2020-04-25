package com.ruoyi.alipay.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayWithdrawEntityMapper;
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.alipay.service.IAlipayWithdrawEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;

/**
 * 会员提现记录Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayWithdrawEntityServiceImpl implements IAlipayWithdrawEntityService {
    @Autowired
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
}
