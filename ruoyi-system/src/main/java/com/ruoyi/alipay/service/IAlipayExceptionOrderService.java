package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayExceptionOrder;
import java.util.List;

/**
 * 拦截订单Service接口
 * 
 * @author ruoyi
 * @date 2020-03-19
 */
public interface IAlipayExceptionOrderService {
	/**
	 * 查询拦截订单
	 * 
	 * @param id 拦截订单ID
	 * @return 拦截订单
	 */
	public AlipayExceptionOrder selectAlipayExceptionOrderById(Long id);

	/**
	 * 查询拦截订单列表
	 * 
	 * @param alipayExceptionOrder 拦截订单
	 * @return 拦截订单集合
	 */
	public List<AlipayExceptionOrder> selectAlipayExceptionOrderList(AlipayExceptionOrder alipayExceptionOrder);

	/**
	 * 新增拦截订单
	 * 
	 * @param alipayExceptionOrder 拦截订单
	 * @return 结果
	 */
	public int insertAlipayExceptionOrder(AlipayExceptionOrder alipayExceptionOrder);

	/**
	 * 修改拦截订单
	 * 
	 * @param alipayExceptionOrder 拦截订单
	 * @return 结果
	 */
	public int updateAlipayExceptionOrder(AlipayExceptionOrder alipayExceptionOrder);

	/**
	 * 批量删除拦截订单
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteAlipayExceptionOrderByIds(String ids);

	/**
	 * 删除拦截订单信息
	 * 
	 * @param id 拦截订单ID
	 * @return 结果
	 */
	public int deleteAlipayExceptionOrderById(Long id);
}
