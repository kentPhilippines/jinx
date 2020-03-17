package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayFileListEntityMapper;
import com.ruoyi.alipay.domain.AlipayFileListEntity;
import com.ruoyi.alipay.service.IAlipayFileListEntityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件列Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayFileListEntityServiceImpl implements IAlipayFileListEntityService 
{
    @Autowired
    private AlipayFileListEntityMapper alipayFileListEntityMapper;

    /**
     * 查询文件列
     * 
     * @param id 文件列ID
     * @return 文件列
     */
    @Override
    public AlipayFileListEntity selectAlipayFileListEntityById(Long id)
    {
        return alipayFileListEntityMapper.selectAlipayFileListEntityById(id);
    }

    /**
     * 查询文件列列表
     * 
     * @param alipayFileListEntity 文件列
     * @return 文件列
     */
    @Override
    public List<AlipayFileListEntity> selectAlipayFileListEntityList(AlipayFileListEntity alipayFileListEntity)
    {
        return alipayFileListEntityMapper.selectAlipayFileListEntityList(alipayFileListEntity);
    }

    /**
     * 新增文件列
     * 
     * @param alipayFileListEntity 文件列
     * @return 结果
     */
    @Override
    public int insertAlipayFileListEntity(AlipayFileListEntity alipayFileListEntity)
    {
        alipayFileListEntity.setCreateTime(DateUtils.getNowDate());
        return alipayFileListEntityMapper.insertAlipayFileListEntity(alipayFileListEntity);
    }

    /**
     * 修改文件列
     * 
     * @param alipayFileListEntity 文件列
     * @return 结果
     */
    @Override
    public int updateAlipayFileListEntity(AlipayFileListEntity alipayFileListEntity)
    {
        return alipayFileListEntityMapper.updateAlipayFileListEntity(alipayFileListEntity);
    }

    /**
     * 删除文件列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayFileListEntityByIds(String ids)
    {
        return alipayFileListEntityMapper.deleteAlipayFileListEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件列信息
     * 
     * @param id 文件列ID
     * @return 结果
     */
    @Override
    public int deleteAlipayFileListEntityById(Long id)
    {
        return alipayFileListEntityMapper.deleteAlipayFileListEntityById(id);
    }
}
