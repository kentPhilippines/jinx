package com.ruoyi.dealpay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dealpay.mapper.DealpayRechargeMapper;
import com.ruoyi.dealpay.domain.DealpayRechargeEntity;
import com.ruoyi.dealpay.service.IDealpayRechargeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 充值记录Service业务层处理
 * 
 * @author k
 * @date 2020-04-03
 */
@Service
public class DealpayRechargeServiceImpl implements IDealpayRechargeService 
{
    @Autowired
    private DealpayRechargeMapper dealpayRechargeMapper;

    /**
     * 查询充值记录
     * 
     * @param id 充值记录ID
     * @return 充值记录
     */
    @Override
    public DealpayRechargeEntity selectDealpayRechargeById(Long id)
    {
        return dealpayRechargeMapper.selectDealpayRechargeById(id);
    }

    /**
     * 查询充值记录列表
     * 
     * @param dealpayRecharge 充值记录
     * @return 充值记录
     */
    @Override
    public List<DealpayRechargeEntity> selectDealpayRechargeList(DealpayRechargeEntity dealpayRecharge)
    {
        return dealpayRechargeMapper.selectDealpayRechargeList(dealpayRecharge);
    }

    /**
     * 新增充值记录
     * 
     * @param dealpayRecharge 充值记录
     * @return 结果
     */
    @Override
    public int insertDealpayRecharge(DealpayRechargeEntity dealpayRecharge)
    {
        dealpayRecharge.setCreateTime(DateUtils.getNowDate());
        return dealpayRechargeMapper.insertDealpayRecharge(dealpayRecharge);
    }

    /**
     * 修改充值记录
     * 
     * @param dealpayRecharge 充值记录
     * @return 结果
     */
    @Override
    public int updateDealpayRecharge(DealpayRechargeEntity dealpayRecharge)
    {
        return dealpayRechargeMapper.updateDealpayRecharge(dealpayRecharge);
    }

    /**
     * 删除充值记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDealpayRechargeByIds(String ids)
    {
        return dealpayRechargeMapper.deleteDealpayRechargeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除充值记录信息
     * 
     * @param id 充值记录ID
     * @return 结果
     */
    @Override
    public int deleteDealpayRechargeById(Long id)
    {
        return dealpayRechargeMapper.deleteDealpayRechargeById(id);
    }
}
