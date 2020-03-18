package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayCorrelationMapper;
import com.ruoyi.alipay.domain.AlipayCorrelation;
import com.ruoyi.alipay.service.IAlipayCorrelationService;
import com.ruoyi.common.core.text.Convert;

/**
 * 代理关系表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
@Service
public class AlipayCorrelationServiceImpl implements IAlipayCorrelationService 
{
    @Autowired
    private AlipayCorrelationMapper alipayCorrelationMapper;

    /**
     * 查询代理关系表
     * 
     * @param id 代理关系表ID
     * @return 代理关系表
     */
    @Override
    public AlipayCorrelation selectAlipayCorrelationById(Long id)
    {
        return alipayCorrelationMapper.selectAlipayCorrelationById(id);
    }

    /**
     * 查询代理关系表列表
     * 
     * @param alipayCorrelation 代理关系表
     * @return 代理关系表
     */
    @Override
    public List<AlipayCorrelation> selectAlipayCorrelationList(AlipayCorrelation alipayCorrelation)
    {
        return alipayCorrelationMapper.selectAlipayCorrelationList(alipayCorrelation);
    }

    /**
     * 新增代理关系表
     * 
     * @param alipayCorrelation 代理关系表
     * @return 结果
     */
    @Override
    public int insertAlipayCorrelation(AlipayCorrelation alipayCorrelation)
    {
        alipayCorrelation.setCreateTime(DateUtils.getNowDate());
        return alipayCorrelationMapper.insertAlipayCorrelation(alipayCorrelation);
    }

    /**
     * 修改代理关系表
     * 
     * @param alipayCorrelation 代理关系表
     * @return 结果
     */
    @Override
    public int updateAlipayCorrelation(AlipayCorrelation alipayCorrelation)
    {
        return alipayCorrelationMapper.updateAlipayCorrelation(alipayCorrelation);
    }

    /**
     * 删除代理关系表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayCorrelationByIds(String ids)
    {
        return alipayCorrelationMapper.deleteAlipayCorrelationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除代理关系表信息
     * 
     * @param id 代理关系表ID
     * @return 结果
     */
    @Override
    public int deleteAlipayCorrelationById(Long id)
    {
        return alipayCorrelationMapper.deleteAlipayCorrelationById(id);
    }
}
