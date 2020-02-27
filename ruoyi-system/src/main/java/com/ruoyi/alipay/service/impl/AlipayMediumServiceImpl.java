package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayMediumMapper;
import com.ruoyi.alipay.domain.AlipayMedium;
import com.ruoyi.alipay.service.IAlipayMediumService;
import com.ruoyi.common.core.text.Convert;

/**
 * 收款媒介列Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
@Service
public class AlipayMediumServiceImpl implements IAlipayMediumService 
{
    @Autowired
    private AlipayMediumMapper alipayMediumMapper;

    /**
     * 查询收款媒介列
     * 
     * @param id 收款媒介列ID
     * @return 收款媒介列
     */
    @Override
    public AlipayMedium selectAlipayMediumById(Long id)
    {
        return alipayMediumMapper.selectAlipayMediumById(id);
    }

    /**
     * 查询收款媒介列列表
     * 
     * @param alipayMedium 收款媒介列
     * @return 收款媒介列
     */
    @Override
    public List<AlipayMedium> selectAlipayMediumList(AlipayMedium alipayMedium)
    {
        return alipayMediumMapper.selectAlipayMediumList(alipayMedium);
    }

    /**
     * 新增收款媒介列
     * 
     * @param alipayMedium 收款媒介列
     * @return 结果
     */
    @Override
    public int insertAlipayMedium(AlipayMedium alipayMedium)
    {
        return alipayMediumMapper.insertAlipayMedium(alipayMedium);
    }

    /**
     * 修改收款媒介列
     * 
     * @param alipayMedium 收款媒介列
     * @return 结果
     */
    @Override
    public int updateAlipayMedium(AlipayMedium alipayMedium)
    {
        return alipayMediumMapper.updateAlipayMedium(alipayMedium);
    }

    /**
     * 删除收款媒介列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayMediumByIds(String ids)
    {
        return alipayMediumMapper.deleteAlipayMediumByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收款媒介列信息
     * 
     * @param id 收款媒介列ID
     * @return 结果
     */
    @Override
    public int deleteAlipayMediumById(Long id)
    {
        return alipayMediumMapper.deleteAlipayMediumById(id);
    }
}
