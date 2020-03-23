package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayDealOrderAppMapper;
import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;

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
	 * 新增商户订单登记
	 * 
	 * @param alipayDealOrderApp 商户订单登记
	 * @return 结果
	 */
	@Override
	public int insertAlipayDealOrderApp(AlipayDealOrderApp alipayDealOrderApp) {
		alipayDealOrderApp.setCreateTime(DateUtils.getNowDate());
		return alipayDealOrderAppMapper.insertAlipayDealOrderApp(alipayDealOrderApp);
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

}
