package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayDealOrder;
import java.util.List;

/**
 * 交易订单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public interface AlipayDealOrderMapper 
{
    /**
     * 查询交易订单
     * 
     * @param id 交易订单ID
     * @return 交易订单
     */
    public AlipayDealOrder selectAlipayDealOrderById(Long id);

    /**
     * 查询交易订单列表
     * 
     * @param alipayDealOrder 交易订单
     * @return 交易订单集合
     */
    public List<AlipayDealOrder> selectAlipayDealOrderList(AlipayDealOrder alipayDealOrder);

    /**
     * 新增交易订单
     * 
     * @param alipayDealOrder 交易订单
     * @return 结果
     */
    public int insertAlipayDealOrder(AlipayDealOrder alipayDealOrder);

    /**
     * 修改交易订单
     * 
     * @param alipayDealOrder 交易订单
     * @return 结果
     */
    public int updateAlipayDealOrder(AlipayDealOrder alipayDealOrder);

    /**
     * 删除交易订单
     * 
     * @param id 交易订单ID
     * @return 结果
     */
    public int deleteAlipayDealOrderById(Long id);

    /**
     * 批量删除交易订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayDealOrderByIds(String[] ids);
}
