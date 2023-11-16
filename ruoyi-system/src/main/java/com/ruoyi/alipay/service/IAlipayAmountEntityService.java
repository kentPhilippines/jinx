package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayAmountEntity;

import java.util.List;

/**
 * 手动加扣款记录Service接口
 *
 * @author kiwi
 * @date 2020-03-24
 */
public interface IAlipayAmountEntityService {
    /**
     * 查询手动加扣款记录
     *
     * @param id 手动加扣款记录ID
     * @return 手动加扣款记录
     */
    public AlipayAmountEntity selectAlipayAmountEntityById(Long id);

    /**
     * 查询手动加扣款记录列表
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 手动加扣款记录集合
     */
    public List<AlipayAmountEntity> selectAlipayAmountEntityList(AlipayAmountEntity alipayAmountEntity);

    public List<AlipayAmountEntity> selectTransferList(AlipayAmountEntity alipayAmountEntity);

    /**
     * 新增手动加扣款记录
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 结果
     */
    int insertAlipayAmountEntity(AlipayAmountEntity alipayAmountEntity);

    int insertAlipayAmountFreeze(AlipayAmountEntity alipayAmountEntity);

    /**
     * 修改手动加扣款记录
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 结果
     */
    public int updateAlipayAmountEntity(AlipayAmountEntity alipayAmountEntity);

    /**
     * 批量删除手动加扣款记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayAmountEntityByIds(String ids);

    /**
     * 删除手动加扣款记录信息
     *
     * @param id 手动加扣款记录ID
     * @return 结果
     */
    public int deleteAlipayAmountEntityById(Long id);


    /**
     * <p>新增商户补单申请</p>
     *
     * @param alipayAmountEntity
     * @return
     */
    int addAppOrder(AlipayAmountEntity alipayAmountEntity);

    /**
     * 审核商户补单申请确认
     *
     * @param amountEntity
     * @return
     */
    int additionaEditEnter(AlipayAmountEntity amountEntity);

    int insertAlipayAmountQuota(AlipayAmountEntity alipayAmountEntity);

    int addTransfer(AlipayAmountEntity amount);

    void deleteUserId(String userId);
}
