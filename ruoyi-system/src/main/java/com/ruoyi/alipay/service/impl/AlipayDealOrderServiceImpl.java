package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayDealOrderMapper;
import com.ruoyi.alipay.domain.AlipayDealOrder;
import com.ruoyi.alipay.service.IAlipayDealOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 交易订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
@Service
public class AlipayDealOrderServiceImpl implements IAlipayDealOrderService 
{
    @Autowired
    private AlipayDealOrderMapper alipayDealOrderMapper;

    /**
     * 查询交易订单
     * 
     * @param id 交易订单ID
     * @return 交易订单
     */
    @Override
    public AlipayDealOrder selectAlipayDealOrderById(Long id)
    {
        return alipayDealOrderMapper.selectAlipayDealOrderById(id);
    }

    /**
     * 查询交易订单列表
     * 
     * @param alipayDealOrder 交易订单
     * @return 交易订单
     */
    @Override
    public List<AlipayDealOrder> selectAlipayDealOrderList(AlipayDealOrder alipayDealOrder)
    {
        return alipayDealOrderMapper.selectAlipayDealOrderList(alipayDealOrder);
    }

    /**
     * 新增交易订单
     * 
     * @param alipayDealOrder 交易订单
     * @return 结果
     */
    @Override
    public int insertAlipayDealOrder(AlipayDealOrder alipayDealOrder)
    {
        return alipayDealOrderMapper.insertAlipayDealOrder(alipayDealOrder);
    }

    /**
     * 修改交易订单
     * 
     * @param alipayDealOrder 交易订单
     * @return 结果
     */
    @Override
    public int updateAlipayDealOrder(AlipayDealOrder alipayDealOrder)
    {
        return alipayDealOrderMapper.updateAlipayDealOrder(alipayDealOrder);
    }

    /**
     * 删除交易订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayDealOrderByIds(String ids)
    {
        return alipayDealOrderMapper.deleteAlipayDealOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除交易订单信息
     * 
     * @param id 交易订单ID
     * @return 结果
     */
    @Override
    public int deleteAlipayDealOrderById(Long id)
    {
        return alipayDealOrderMapper.deleteAlipayDealOrderById(id);
    }
}
