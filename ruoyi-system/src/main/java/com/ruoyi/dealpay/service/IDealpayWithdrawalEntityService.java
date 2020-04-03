package com.ruoyi.dealpay.service;

import com.ruoyi.dealpay.domain.DealpayWithdrawalEntity;

import java.util.List;

/**
 * 卡商出款记录表Service接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface IDealpayWithdrawalEntityService {
    /**
     * 查询卡商出款记录表
     *
     * @param id 卡商出款记录表ID
     * @return 卡商出款记录表
     */
    public DealpayWithdrawalEntity selectDealpayWithdrawalEntityById(Long id);

    /**
     * 查询卡商出款记录表列表
     *
     * @param dealpayWithdrawalEntity 卡商出款记录表
     * @return 卡商出款记录表集合
     */
    public List<DealpayWithdrawalEntity> selectDealpayWithdrawalEntityList(DealpayWithdrawalEntity dealpayWithdrawalEntity);

    /**
     * 新增卡商出款记录表
     *
     * @param dealpayWithdrawalEntity 卡商出款记录表
     * @return 结果
     */
    public int insertDealpayWithdrawalEntity(DealpayWithdrawalEntity dealpayWithdrawalEntity);

    /**
     * 修改卡商出款记录表
     *
     * @param dealpayWithdrawalEntity 卡商出款记录表
     * @return 结果
     */
    public int updateDealpayWithdrawalEntity(DealpayWithdrawalEntity dealpayWithdrawalEntity);

    /**
     * 批量删除卡商出款记录表
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayWithdrawalEntityByIds(String ids);

    /**
     * 删除卡商出款记录表信息
     *
     * @param id 卡商出款记录表ID
     * @return 结果
     */
    public int deleteDealpayWithdrawalEntityById(Long id);
}
