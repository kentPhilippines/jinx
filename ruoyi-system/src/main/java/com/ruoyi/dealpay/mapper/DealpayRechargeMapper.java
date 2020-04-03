package com.ruoyi.dealpay.mapper;

import com.ruoyi.dealpay.domain.DealpayRechargeEntity;
import java.util.List;

/**
 * 充值记录Mapper接口
 * 
 * @author k
 * @date 2020-04-03
 */
public interface DealpayRechargeMapper 
{
    /**
     * 查询充值记录
     * 
     * @param id 充值记录ID
     * @return 充值记录
     */
    public DealpayRechargeEntity selectDealpayRechargeById(Long id);

    /**
     * 查询充值记录列表
     * 
     * @param dealpayRecharge 充值记录
     * @return 充值记录集合
     */
    public List<DealpayRechargeEntity> selectDealpayRechargeList(DealpayRechargeEntity dealpayRecharge);

    /**
     * 新增充值记录
     * 
     * @param dealpayRecharge 充值记录
     * @return 结果
     */
    public int insertDealpayRecharge(DealpayRechargeEntity dealpayRecharge);

    /**
     * 修改充值记录
     * 
     * @param dealpayRecharge 充值记录
     * @return 结果
     */
    public int updateDealpayRecharge(DealpayRechargeEntity dealpayRecharge);

    /**
     * 删除充值记录
     * 
     * @param id 充值记录ID
     * @return 结果
     */
    public int deleteDealpayRechargeById(Long id);

    /**
     * 批量删除充值记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayRechargeByIds(String[] ids);
}
