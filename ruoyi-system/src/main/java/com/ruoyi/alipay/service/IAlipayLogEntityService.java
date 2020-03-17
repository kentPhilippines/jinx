package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayLogEntity;
import java.util.List;

/**
 * 日志表Service接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayLogEntityService 
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
     * 批量删除日志表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayLogEntityByIds(String ids);

    /**
     * 删除日志表信息
     * 
     * @param id 日志表ID
     * @return 结果
     */
    public int deleteAlipayLogEntityById(Long id);
}
