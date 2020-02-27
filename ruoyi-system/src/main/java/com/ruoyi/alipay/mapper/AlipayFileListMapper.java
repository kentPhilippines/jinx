package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayFileList;
import java.util.List;

/**
 * 文件信息表Mapper接口
 * 
 * @author otc
 * @date 2020-02-27
 */
public interface AlipayFileListMapper 
{
    /**
     * 查询文件信息表
     * 
     * @param id 文件信息表ID
     * @return 文件信息表
     */
    public AlipayFileList selectAlipayFileListById(Long id);

    /**
     * 查询文件信息表列表
     * 
     * @param alipayFileList 文件信息表
     * @return 文件信息表集合
     */
    public List<AlipayFileList> selectAlipayFileListList(AlipayFileList alipayFileList);

    /**
     * 新增文件信息表
     * 
     * @param alipayFileList 文件信息表
     * @return 结果
     */
    public int insertAlipayFileList(AlipayFileList alipayFileList);

    /**
     * 修改文件信息表
     * 
     * @param alipayFileList 文件信息表
     * @return 结果
     */
    public int updateAlipayFileList(AlipayFileList alipayFileList);

    /**
     * 删除文件信息表
     * 
     * @param id 文件信息表ID
     * @return 结果
     */
    public int deleteAlipayFileListById(Long id);

    /**
     * 批量删除文件信息表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayFileListByIds(String[] ids);
}
