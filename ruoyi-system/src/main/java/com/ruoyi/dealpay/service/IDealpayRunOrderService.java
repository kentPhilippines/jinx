package com.ruoyi.dealpay.service;

import com.ruoyi.dealpay.domain.DealpayRunOrderEntity;

import java.util.List;

/**
 * 流水订单记录Service接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface IDealpayRunOrderService {
    /**
     * 查询流水订单记录
     *
     * @param id 流水订单记录ID
     * @return 流水订单记录
     */
    public DealpayRunOrderEntity selectDealpayRunOrderById(Long id);

    /**
     * 查询流水订单记录列表
     *
     * @param dealpayRunOrder 流水订单记录
     * @return 流水订单记录集合
     */
    public List<DealpayRunOrderEntity> selectDealpayRunOrderList(DealpayRunOrderEntity dealpayRunOrder);

    /**
     * 新增流水订单记录
     *
     * @param dealpayRunOrder 流水订单记录
     * @return 结果
     */
    public int insertDealpayRunOrder(DealpayRunOrderEntity dealpayRunOrder);

    /**
     * 修改流水订单记录
     *
     * @param dealpayRunOrder 流水订单记录
     * @return 结果
     */
    public int updateDealpayRunOrder(DealpayRunOrderEntity dealpayRunOrder);

    /**
     * 批量删除流水订单记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayRunOrderByIds(String ids);

    /**
     * 删除流水订单记录信息
     *
     * @param id 流水订单记录ID
     * @return 结果
     */
    public int deleteDealpayRunOrderById(Long id);
}
