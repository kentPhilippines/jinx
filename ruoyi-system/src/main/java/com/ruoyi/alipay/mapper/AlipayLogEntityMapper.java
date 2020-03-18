package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayLogEntity;
import java.util.List;

/**
 * 日志表Mapper接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayLogEntityMapper 
{
    /**
     * 查询日志表
     * 
     * @param id 日志表ID
     * @return 日志表
     */
    public AlipayLogEntity selectAlipayLogEntityById(Long id);

    /**
     * 查询日志表列表
     * 
     * @param alipayLogEntity 日志表
     * @return 日志表集合
     */
    public List<AlipayLogEntity> selectAlipayLogEntityList(AlipayLogEntity alipayLogEntity);

    /**
     * 新增日志表
     * 
     * @param alipayLogEntity 日志表
     * @return 结果
     */
    public int insertAlipayLogEntity(AlipayLogEntity alipayLogEntity);

    /**
     * 修改日志表
     * 
     * @param alipayLogEntity 日志表
     * @return 结果
     */
    public int updateAlipayLogEntity(AlipayLogEntity alipayLogEntity);

    /**
     * 删除日志表
     * 
     * @param id 日志表ID
     * @return 结果
     */
    public int deleteAlipayLogEntityById(Long id);

    /**
     * 批量删除日志表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayLogEntityByIds(String[] ids);
}
