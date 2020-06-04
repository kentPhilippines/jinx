package com.ruoyi.alipay.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.mapper.AlipayDealOrderAppMapper;
import com.ruoyi.alipay.mapper.MerchantInfoEntityMapper;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商户订单登记Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayDealOrderAppServiceImpl implements IAlipayDealOrderAppService {
    @Autowired
    private AlipayDealOrderAppMapper alipayDealOrderAppMapper;

    @Autowired
    private MerchantInfoEntityMapper merchantInfoEntityMapper;

    /**
     * 查询商户订单登记
     *
     * @param id 商户订单登记ID
     * @return 商户订单登记
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayDealOrderApp selectAlipayDealOrderAppById(Long id) {
        return alipayDealOrderAppMapper.selectAlipayDealOrderAppById(id);
    }

    /**
     * 查询商户订单登记列表
     *
     * @param alipayDealOrderApp 商户订单登记
     * @return 商户订单登记
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayDealOrderApp> selectAlipayDealOrderAppList(AlipayDealOrderApp alipayDealOrderApp) {
        return alipayDealOrderAppMapper.selectAlipayDealOrderAppList(alipayDealOrderApp);
    }

    /**
     * 修改商户订单登记
     *
     * @param alipayDealOrderApp 商户订单登记
     * @return 结果
     */
    @Override
    public int updateAlipayDealOrderApp(AlipayDealOrderApp alipayDealOrderApp) {
        return alipayDealOrderAppMapper.updateAlipayDealOrderApp(alipayDealOrderApp);
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public List<AlipayDealOrderApp> selectSubMembersOrderList( 
			List<String> list) {
        if (list.size()>0) {
            List<AlipayDealOrderApp> orderList = alipayDealOrderAppMapper.selectSubAgentMembersOrderList(  list);
            return orderList;
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public List<StatisticsEntity> selectMerchantStatisticsDataByDay(StatisticsEntity statisticsEntity, String dayStart, String dayEnd) {
        return alipayDealOrderAppMapper.selectOrderAppStatDateByDay(statisticsEntity, dayStart, dayEnd);
    }


}
