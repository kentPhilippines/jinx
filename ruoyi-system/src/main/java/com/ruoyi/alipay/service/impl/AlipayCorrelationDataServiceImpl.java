package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayCorrelationDataMapper;
import com.ruoyi.alipay.domain.AlipayCorrelationData;
import com.ruoyi.alipay.service.IAlipayCorrelationDataService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;

/**
 * 订单记录表Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayCorrelationDataServiceImpl implements IAlipayCorrelationDataService {
	@Autowired
	private AlipayCorrelationDataMapper alipayCorrelationDataMapper;
	/**
	 * 查询订单记录表
	 * @param id 订单记录表ID
	 * @return 订单记录表
	 */
	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public AlipayCorrelationData selectAlipayCorrelationDataById(Long id) {
		return alipayCorrelationDataMapper.selectAlipayCorrelationDataById(id);
	}

	/**
	 * 查询订单记录表列表
	 * 
	 * @param alipayCorrelationData 订单记录表
	 * @return 订单记录表
	 */
	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public List<AlipayCorrelationData> selectAlipayCorrelationDataList(AlipayCorrelationData alipayCorrelationData) {
		return alipayCorrelationDataMapper.selectAlipayCorrelationDataList(alipayCorrelationData);
	}

	/**
	 * 新增订单记录表
	 * 
	 * @param alipayCorrelationData 订单记录表
	 * @return 结果
	 */
	@Override
	public int insertAlipayCorrelationData(AlipayCorrelationData alipayCorrelationData) {
		alipayCorrelationData.setCreateTime(DateUtils.getNowDate());
		return alipayCorrelationDataMapper.insertAlipayCorrelationData(alipayCorrelationData);
	}

	/**
	 * 修改订单记录表
	 * 
	 * @param alipayCorrelationData 订单记录表
	 * @return 结果
	 */
	@Override
	public int updateAlipayCorrelationData(AlipayCorrelationData alipayCorrelationData) {
		return alipayCorrelationDataMapper.updateAlipayCorrelationData(alipayCorrelationData);
	}

	/**
	 * 删除订单记录表对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayCorrelationDataByIds(String ids) {
		return alipayCorrelationDataMapper.deleteAlipayCorrelationDataByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除订单记录表信息
	 * 
	 * @param id 订单记录表ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayCorrelationDataById(Long id) {
		return alipayCorrelationDataMapper.deleteAlipayCorrelationDataById(id);
	}
}
