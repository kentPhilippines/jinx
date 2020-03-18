package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayLogEntityMapper;
import com.ruoyi.alipay.domain.AlipayLogEntity;
import com.ruoyi.alipay.service.IAlipayLogEntityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 日志表Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayLogEntityServiceImpl implements IAlipayLogEntityService 
{
    @Autowired
    private AlipayLogEntityMapper alipayLogEntityMapper;

    /**
     * 查询日志表
     * 
     * @param id 日志表ID
     * @return 日志表
     */
    @Override
    public AlipayLogEntity selectAlipayLogEntityById(Long id)
    {
        return alipayLogEntityMapper.selectAlipayLogEntityById(id);
    }

    /**
     * 查询日志表列表
     * 
     * @param alipayLogEntity 日志表
     * @return 日志表
     */
    @Override
    public List<AlipayLogEntity> selectAlipayLogEntityList(AlipayLogEntity alipayLogEntity)
    {
        return alipayLogEntityMapper.selectAlipayLogEntityList(alipayLogEntity);
    }

    /**
     * 新增日志表
     * 
     * @param alipayLogEntity 日志表
     * @return 结果
     */
    @Override
    public int insertAlipayLogEntity(AlipayLogEntity alipayLogEntity)
    {
        alipayLogEntity.setCreateTime(DateUtils.getNowDate());
        return alipayLogEntityMapper.insertAlipayLogEntity(alipayLogEntity);
    }

    /**
     * 修改日志表
     * 
     * @param alipayLogEntity 日志表
     * @return 结果
     */
    @Override
    public int updateAlipayLogEntity(AlipayLogEntity alipayLogEntity)
    {
        return alipayLogEntityMapper.updateAlipayLogEntity(alipayLogEntity);
    }

    /**
     * 删除日志表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayLogEntityByIds(String ids)
    {
        return alipayLogEntityMapper.deleteAlipayLogEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除日志表信息
     * 
     * @param id 日志表ID
     * @return 结果
     */
    @Override
    public int deleteAlipayLogEntityById(Long id)
    {
        return alipayLogEntityMapper.deleteAlipayLogEntityById(id);
    }
}
