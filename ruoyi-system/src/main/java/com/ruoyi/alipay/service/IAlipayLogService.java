package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayLog;
import java.util.List;

/**
 * 获取IP地址记录表Service接口
 * 
 * @author otc
 * @date 2020-02-27
 */
public interface IAlipayLogService 
{
    /**
     * 查询获取IP地址记录表
     * 
     * @param id 获取IP地址记录表ID
     * @return 获取IP地址记录表
     */
    public AlipayLog selectAlipayLogById(Long id);

    /**
     * 查询获取IP地址记录表列表
     * 
     * @param alipayLog 获取IP地址记录表
     * @return 获取IP地址记录表集合
     */
    public List<AlipayLog> selectAlipayLogList(AlipayLog alipayLog);

    /**
     * 新增获取IP地址记录表
     * 
     * @param alipayLog 获取IP地址记录表
     * @return 结果
     */
    public int insertAlipayLog(AlipayLog alipayLog);

    /**
     * 修改获取IP地址记录表
     * 
     * @param alipayLog 获取IP地址记录表
     * @return 结果
     */
    public int updateAlipayLog(AlipayLog alipayLog);

    /**
     * 批量删除获取IP地址记录表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayLogByIds(String ids);

    /**
     * 删除获取IP地址记录表信息
     * 
     * @param id 获取IP地址记录表ID
     * @return 结果
     */
    public int deleteAlipayLogById(Long id);
}
