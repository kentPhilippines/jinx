package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayFileListEntity;
import java.util.List;

/**
 * 文件列Mapper接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayFileListEntityMapper 
{
    /**
     * 查询文件列
     * 
     * @param id 文件列ID
     * @return 文件列
     */
    public AlipayFileListEntity selectAlipayFileListEntityById(Long id);

    /**
     * 查询文件列列表
     * 
     * @param alipayFileListEntity 文件列
     * @return 文件列集合
     */
    public List<AlipayFileListEntity> selectAlipayFileListEntityList(AlipayFileListEntity alipayFileListEntity);

    /**
     * 新增文件列
     * 
     * @param alipayFileListEntity 文件列
     * @return 结果
     */
    public int insertAlipayFileListEntity(AlipayFileListEntity alipayFileListEntity);

    /**
     * 修改文件列
     * 
     * @param alipayFileListEntity 文件列
     * @return 结果
     */
    public int updateAlipayFileListEntity(AlipayFileListEntity alipayFileListEntity);

    /**
     * 删除文件列
     * 
     * @param id 文件列ID
     * @return 结果
     */
    public int deleteAlipayFileListEntityById(Long id);

    /**
     * 批量删除文件列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayFileListEntityByIds(String[] ids);
}
