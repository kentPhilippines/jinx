package com.ruoyi.dealpay.mapper;

import com.ruoyi.dealpay.domain.DealpayDealOrderEntity;
import java.util.List;

/**
 * 交易订单Mapper接口
 * 
 * @author k
 * @date 2020-04-03
 */
public interface DealpayDealOrderMapper 
{
    /**
     * 查询交易订单
     * 
     * @param id 交易订单ID
     * @return 交易订单
     */
    public DealpayDealOrderEntity selectDealpayDealOrderById(Long id);

    /**
     * 查询交易订单列表
     * 
     * @param dealpayDealOrder 交易订单
     * @return 交易订单集合
     */
    public List<DealpayDealOrderEntity> selectDealpayDealOrderList(DealpayDealOrderEntity dealpayDealOrder);

    /**
     * 新增交易订单
     * 
     * @param dealpayDealOrder 交易订单
     * @return 结果
     */
    public int insertDealpayDealOrder(DealpayDealOrderEntity dealpayDealOrder);

    /**
     * 修改交易订单
     * 
     * @param dealpayDealOrder 交易订单
     * @return 结果
     */
    public int updateDealpayDealOrder(DealpayDealOrderEntity dealpayDealOrder);

    /**
     * 删除交易订单
     * 
     * @param id 交易订单ID
     * @return 结果
     */
    public int deleteDealpayDealOrderById(Long id);

    /**
     * 批量删除交易订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayDealOrderByIds(String[] ids);
}
