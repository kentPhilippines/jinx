package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayLogMapper;
import com.ruoyi.alipay.domain.AlipayLog;
import com.ruoyi.alipay.service.IAlipayLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 获取IP地址记录表Service业务层处理
 * 
 * @author otc
 * @date 2020-02-27
 */
@Service
public class AlipayLogServiceImpl implements IAlipayLogService 
{
    @Autowired
    private AlipayLogMapper alipayLogMapper;

    /**
     * 查询获取IP地址记录表
     * 
     * @param id 获取IP地址记录表ID
     * @return 获取IP地址记录表
     */
    @Override
    public AlipayLog selectAlipayLogById(Long id)
    {
        return alipayLogMapper.selectAlipayLogById(id);
    }

    /**
     * 查询获取IP地址记录表列表
     * 
     * @param alipayLog 获取IP地址记录表
     * @return 获取IP地址记录表
     */
    @Override
    public List<AlipayLog> selectAlipayLogList(AlipayLog alipayLog)
    {
        return alipayLogMapper.selectAlipayLogList(alipayLog);
    }

    /**
     * 新增获取IP地址记录表
     * 
     * @param alipayLog 获取IP地址记录表
     * @return 结果
     */
    @Override
    public int insertAlipayLog(AlipayLog alipayLog)
    {
        return alipayLogMapper.insertAlipayLog(alipayLog);
    }

    /**
     * 修改获取IP地址记录表
     * 
     * @param alipayLog 获取IP地址记录表
     * @return 结果
     */
    @Override
    public int updateAlipayLog(AlipayLog alipayLog)
    {
        return alipayLogMapper.updateAlipayLog(alipayLog);
    }

    /**
     * 删除获取IP地址记录表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayLogByIds(String ids)
    {
        return alipayLogMapper.deleteAlipayLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除获取IP地址记录表信息
     * 
     * @param id 获取IP地址记录表ID
     * @return 结果
     */
    @Override
    public int deleteAlipayLogById(Long id)
    {
        return alipayLogMapper.deleteAlipayLogById(id);
    }
}
