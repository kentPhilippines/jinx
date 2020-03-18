package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayRechargeEntity;
import java.util.List;

/**
 * 充值记录Service接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayRechargeEntityService 
{
    /**
     * 查询充值记录
     * 
     * @param id 充值记录ID
     * @return 充值记录
     */
    public AlipayRechargeEntity selectAlipayRechargeEntityById(Long id);

    /**
     * 查询充值记录列表
     * 
     * @param alipayRechargeEntity 充值记录
     * @return 充值记录集合
     */
    public List<AlipayRechargeEntity> selectAlipayRechargeEntityList(AlipayRechargeEntity alipayRechargeEntity);

    /**
     * 新增充值记录
     * 
     * @param alipayRechargeEntity 充值记录
     * @return 结果
     */
    public int insertAlipayRechargeEntity(AlipayRechargeEntity alipayRechargeEntity);

    /**
     * 修改充值记录
     * 
     * @param alipayRechargeEntity 充值记录
     * @return 结果
     */
    public int updateAlipayRechargeEntity(AlipayRechargeEntity alipayRechargeEntity);

    /**
     * 批量删除充值记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayRechargeEntityByIds(String ids);

    /**
     * 删除充值记录信息
     * 
     * @param id 充值记录ID
     * @return 结果
     */
    public int deleteAlipayRechargeEntityById(Long id);
}
