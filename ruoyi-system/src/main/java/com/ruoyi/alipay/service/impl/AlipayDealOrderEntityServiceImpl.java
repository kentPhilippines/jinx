package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayDealOrderEntityMapper;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.service.IAlipayDealOrderEntityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 交易订单Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayDealOrderEntityServiceImpl implements IAlipayDealOrderEntityService 
{
    @Autowired
    private AlipayDealOrderEntityMapper alipayDealOrderEntityMapper;

    /**
     * 查询交易订单
     * 
     * @param id 交易订单ID
     * @return 交易订单
     */
    @Override
    public AlipayDealOrderEntity selectAlipayDealOrderEntityById(Long id)
    {
        return alipayDealOrderEntityMapper.selectAlipayDealOrderEntityById(id);
    }

    /**
     * 查询交易订单列表
     * 
     * @param alipayDealOrderEntity 交易订单
     * @return 交易订单
     */
    @Override
    public List<AlipayDealOrderEntity> selectAlipayDealOrderEntityList(AlipayDealOrderEntity alipayDealOrderEntity)
    {
        return alipayDealOrderEntityMapper.selectAlipayDealOrderEntityList(alipayDealOrderEntity);
    }

    /**
     * 新增交易订单
     * 
     * @param alipayDealOrderEntity 交易订单
     * @return 结果
     */
    @Override
    public int insertAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity)
    {
        alipayDealOrderEntity.setCreateTime(DateUtils.getNowDate());
        return alipayDealOrderEntityMapper.insertAlipayDealOrderEntity(alipayDealOrderEntity);
    }

    /**
     * 修改交易订单
     * 
     * @param alipayDealOrderEntity 交易订单
     * @return 结果
     */
    @Override
    public int updateAlipayDealOrderEntity(AlipayDealOrderEntity alipayDealOrderEntity)
    {
        return alipayDealOrderEntityMapper.updateAlipayDealOrderEntity(alipayDealOrderEntity);
    }

    /**
     * 删除交易订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayDealOrderEntityByIds(String ids)
    {
        return alipayDealOrderEntityMapper.deleteAlipayDealOrderEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除交易订单信息
     * 
     * @param id 交易订单ID
     * @return 结果
     */
    @Override
    public int deleteAlipayDealOrderEntityById(Long id)
    {
        return alipayDealOrderEntityMapper.deleteAlipayDealOrderEntityById(id);
    }
}
