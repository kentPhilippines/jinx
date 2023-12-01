package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.mapper.AlipayRunOrderEntityMapper;
import com.ruoyi.alipay.service.IAlipayRunOrderEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流水订单记录Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-18
 */
@Service
public class AlipayRunOrderEntityServiceImpl implements IAlipayRunOrderEntityService {
	@Resource
	private AlipayRunOrderEntityMapper alipayRunOrderEntityMapper;

	/**
	 * 查询流水订单记录列表
	 *
	 * @param alipayRunOrderEntity 流水订单记录
	 * @return 流水订单记录
	 */
	@Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayRunOrderEntity> selectAlipayRunOrderEntityList(AlipayRunOrderEntity alipayRunOrderEntity) {
        return alipayRunOrderEntityMapper.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
    }

    @Override
    public List<AlipayRunOrderEntity> findAssociatedId(String orderId, String strTime, String endTime) {
        return null;
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayRunOrderEntity> findAssocidOrder(String orderId) {
        return alipayRunOrderEntityMapper.findAssocidOrder(orderId);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayRunOrderEntity> findUserOrderLimit(String userId, String str, String end) {
        return alipayRunOrderEntityMapper.findUserOrderLimit(userId, str, end);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public Double sumDealOrderAgentFee(String yesToday, String today) {
        return alipayRunOrderEntityMapper.sumDealOrderAgentFee(yesToday, today);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public Double sumWitAppFee(String yesToday, String today) {
        return alipayRunOrderEntityMapper.sumWitAppFee(yesToday, today);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public Double witAgentFee(String yesToday, String today) {
        return alipayRunOrderEntityMapper.witAgentFee(yesToday, today);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void deleteUserId(String userId) {
        alipayRunOrderEntityMapper.deleteUserId(userId);
    }

}
