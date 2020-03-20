package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayExceptionOrderMapper;
import com.ruoyi.alipay.domain.AlipayExceptionOrder;
import com.ruoyi.alipay.service.IAlipayExceptionOrderService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;

/**
 * 拦截订单Service业务层处理
 * @author ruoyi
 * @date 2020-03-19
 */
@Service
public class AlipayExceptionOrderServiceImpl implements IAlipayExceptionOrderService {
	@Autowired
	private AlipayExceptionOrderMapper alipayExceptionOrderMapper;
	/**
	 * 查询拦截订单
	 * @param id 拦截订单ID
	 * @return 拦截订单
	 */
	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public AlipayExceptionOrder selectAlipayExceptionOrderById(Long id) {
		return alipayExceptionOrderMapper.selectAlipayExceptionOrderById(id);
	}
	/**
	 * 查询拦截订单列表
	 * @param alipayExceptionOrder 拦截订单
	 * @return 拦截订单
	 */
	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public List<AlipayExceptionOrder> selectAlipayExceptionOrderList(AlipayExceptionOrder alipayExceptionOrder) {
		return alipayExceptionOrderMapper.selectAlipayExceptionOrderList(alipayExceptionOrder);
	}
	/**
	 * 新增拦截订单
	 * @param alipayExceptionOrder 拦截订单
	 * @return 结果
	 */
	@Override
	public int insertAlipayExceptionOrder(AlipayExceptionOrder alipayExceptionOrder) {
		return alipayExceptionOrderMapper.insertAlipayExceptionOrder(alipayExceptionOrder);
	}
	/**
	 * 修改拦截订单
	 * @param alipayExceptionOrder 拦截订单
	 * @return 结果
	 */
	@Override
	public int updateAlipayExceptionOrder(AlipayExceptionOrder alipayExceptionOrder) {
		return alipayExceptionOrderMapper.updateAlipayExceptionOrder(alipayExceptionOrder);
	}

	/**
	 * 删除拦截订单对象
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayExceptionOrderByIds(String ids) {
		return alipayExceptionOrderMapper.deleteAlipayExceptionOrderByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除拦截订单信息
	 * @param id 拦截订单ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayExceptionOrderById(Long id) {
		return alipayExceptionOrderMapper.deleteAlipayExceptionOrderById(id);
	}
}
