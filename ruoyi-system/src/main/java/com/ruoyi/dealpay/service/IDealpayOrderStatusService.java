package com.ruoyi.dealpay.service;

import com.ruoyi.dealpay.domain.DealpayOrderStatusEntity;

import java.util.List;

/**
 * 双方确认订单状态Service接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface IDealpayOrderStatusService {
    /**
     * 查询双方确认订单状态
     *
     * @param id 双方确认订单状态ID
     * @return 双方确认订单状态
     */
    public DealpayOrderStatusEntity selectDealpayOrderStatusById(Long id);

    /**
     * 查询双方确认订单状态列表
     *
     * @param dealpayOrderStatus 双方确认订单状态
     * @return 双方确认订单状态集合
     */
    public List<DealpayOrderStatusEntity> selectDealpayOrderStatusList(DealpayOrderStatusEntity dealpayOrderStatus);

    /**
     * 新增双方确认订单状态
     *
     * @param dealpayOrderStatus 双方确认订单状态
     * @return 结果
     */
    public int insertDealpayOrderStatus(DealpayOrderStatusEntity dealpayOrderStatus);

    /**
     * 修改双方确认订单状态
     *
     * @param dealpayOrderStatus 双方确认订单状态
     * @return 结果
     */
    public int updateDealpayOrderStatus(DealpayOrderStatusEntity dealpayOrderStatus);

    /**
     * 批量删除双方确认订单状态
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayOrderStatusByIds(String ids);

    /**
     * 删除双方确认订单状态信息
     *
     * @param id 双方确认订单状态ID
     * @return 结果
     */
    public int deleteDealpayOrderStatusById(Long id);
}
