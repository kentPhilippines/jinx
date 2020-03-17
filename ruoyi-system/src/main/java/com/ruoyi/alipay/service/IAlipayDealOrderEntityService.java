package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import java.util.List;

/**
 * 交易订单Service接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayDealOrderEntityService 
{
    /**
     * 查询交易订单
     * 
     * @param id 交易订单ID
     * @return 交易订单
     */
    public AlipayDealOrderEntity selectAlipayDealOrderEntityById(Long id);

    /**
     * 查询交易订单列表
     * 
     * @param alipayDealOrderEntity 交易订单
     * @return 交易订单集合
     */
    public List<AlipayDealOrderEntity> selectAlipayDealOrderEntityList(AlipayDealOrderEntity alipayDealOrderEntity);

    /**
     * 新增交易订单
     * 
     * @param alipayDealOrderEntity 交易订单
     * @return 结果
     */
    public int insertAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity);

    /**
     * 修改交易订单
     * 
     * @param alipayDealOrderEntity 交易订单
     * @return 结果
     */
    public int updateAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity);

    /**
     * 批量删除交易订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayDealOrderEntityByIds(String ids);

    /**
     * 删除交易订单信息
     * 
     * @param id 交易订单ID
     * @return 结果
     */
    public int deleteAlipayDealOrderEntityById(Long id);
}
