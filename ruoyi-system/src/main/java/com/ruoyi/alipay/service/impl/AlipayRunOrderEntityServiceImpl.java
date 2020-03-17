package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayRunOrderEntityMapper;
import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.service.IAlipayRunOrderEntityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 流水订单记录Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayRunOrderEntityServiceImpl implements IAlipayRunOrderEntityService 
{
    @Autowired
    private AlipayRunOrderEntityMapper alipayRunOrderEntityMapper;

    /**
     * 查询流水订单记录
     * 
     * @param id 流水订单记录ID
     * @return 流水订单记录
     */
    @Override
    public AlipayRunOrderEntity selectAlipayRunOrderEntityById(Long id)
    {
        return alipayRunOrderEntityMapper.selectAlipayRunOrderEntityById(id);
    }

    /**
     * 查询流水订单记录列表
     * 
     * @param alipayRunOrderEntity 流水订单记录
     * @return 流水订单记录
     */
    @Override
    public List<AlipayRunOrderEntity> selectAlipayRunOrderEntityList(AlipayRunOrderEntity alipayRunOrderEntity)
    {
        return alipayRunOrderEntityMapper.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
    }

    /**
     * 新增流水订单记录
     * 
     * @param alipayRunOrderEntity 流水订单记录
     * @return 结果
     */
    @Override
    public int insertAlipayRunOrderEntity(AlipayRunOrderEntity alipayRunOrderEntity)
    {
        alipayRunOrderEntity.setCreateTime(DateUtils.getNowDate());
        return alipayRunOrderEntityMapper.insertAlipayRunOrderEntity(alipayRunOrderEntity);
    }

    /**
     * 修改流水订单记录
     * 
     * @param alipayRunOrderEntity 流水订单记录
     * @return 结果
     */
    @Override
    public int updateAlipayRunOrderEntity(AlipayRunOrderEntity alipayRunOrderEntity)
    {
        return alipayRunOrderEntityMapper.updateAlipayRunOrderEntity(alipayRunOrderEntity);
    }

    /**
     * 删除流水订单记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayRunOrderEntityByIds(String ids)
    {
        return alipayRunOrderEntityMapper.deleteAlipayRunOrderEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流水订单记录信息
     * 
     * @param id 流水订单记录ID
     * @return 结果
     */
    @Override
    public int deleteAlipayRunOrderEntityById(Long id)
    {
        return alipayRunOrderEntityMapper.deleteAlipayRunOrderEntityById(id);
    }
}
