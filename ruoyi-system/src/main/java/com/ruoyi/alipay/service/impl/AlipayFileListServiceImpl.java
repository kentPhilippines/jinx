package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayFileListMapper;
import com.ruoyi.alipay.domain.AlipayFileList;
import com.ruoyi.alipay.service.IAlipayFileListService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件信息表Service业务层处理
 * 
 * @author otc
 * @date 2020-02-27
 */
@Service
public class AlipayFileListServiceImpl implements IAlipayFileListService 
{
    @Autowired
    private AlipayFileListMapper alipayFileListMapper;

    /**
     * 查询文件信息表
     * 
     * @param id 文件信息表ID
     * @return 文件信息表
     */
    @Override
    public AlipayFileList selectAlipayFileListById(Long id)
    {
        return alipayFileListMapper.selectAlipayFileListById(id);
    }

    /**
     * 查询文件信息表列表
     * 
     * @param alipayFileList 文件信息表
     * @return 文件信息表
     */
    @Override
    public List<AlipayFileList> selectAlipayFileListList(AlipayFileList alipayFileList)
    {
        return alipayFileListMapper.selectAlipayFileListList(alipayFileList);
    }

    /**
     * 新增文件信息表
     * 
     * @param alipayFileList 文件信息表
     * @return 结果
     */
    @Override
    public int insertAlipayFileList(AlipayFileList alipayFileList)
    {
        return alipayFileListMapper.insertAlipayFileList(alipayFileList);
    }

    /**
     * 修改文件信息表
     * 
     * @param alipayFileList 文件信息表
     * @return 结果
     */
    @Override
    public int updateAlipayFileList(AlipayFileList alipayFileList)
    {
        return alipayFileListMapper.updateAlipayFileList(alipayFileList);
    }

    /**
     * 删除文件信息表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayFileListByIds(String ids)
    {
        return alipayFileListMapper.deleteAlipayFileListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件信息表信息
     * 
     * @param id 文件信息表ID
     * @return 结果
     */
    @Override
    public int deleteAlipayFileListById(Long id)
    {
        return alipayFileListMapper.deleteAlipayFileListById(id);
    }
}
