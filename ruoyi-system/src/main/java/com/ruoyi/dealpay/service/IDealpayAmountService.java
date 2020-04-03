package com.ruoyi.dealpay.service;

import com.ruoyi.dealpay.domain.DealpayAmountEntity;

import java.util.List;

/**
 * 手动加扣款记录Service接口
 *
 * @author ruoyi
 * @date 2020-04-03
 */
public interface IDealpayAmountService {
    /**
     * 查询手动加扣款记录
     *
     * @param id 手动加扣款记录ID
     * @return 手动加扣款记录
     */
    public DealpayAmountEntity selectDealpayAmountById(Long id);

    /**
     * 查询手动加扣款记录列表
     *
     * @param dealpayAmount 手动加扣款记录
     * @return 手动加扣款记录集合
     */
    public List<DealpayAmountEntity> selectDealpayAmountList(DealpayAmountEntity dealpayAmount);

    /**
     * 新增手动加扣款记录
     *
     * @param dealpayAmount 手动加扣款记录
     * @return 结果
     */
    public int insertDealpayAmount(DealpayAmountEntity dealpayAmount);

    /**
     * 修改手动加扣款记录
     *
     * @param dealpayAmount 手动加扣款记录
     * @return 结果
     */
    public int updateDealpayAmount(DealpayAmountEntity dealpayAmount);

    /**
     * 批量删除手动加扣款记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayAmountByIds(String ids);

    /**
     * 删除手动加扣款记录信息
     *
     * @param id 手动加扣款记录ID
     * @return 结果
     */
    public int deleteDealpayAmountById(Long id);
}
