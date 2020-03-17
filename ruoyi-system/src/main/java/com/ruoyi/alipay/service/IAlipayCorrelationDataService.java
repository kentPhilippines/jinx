package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayCorrelationData;
import java.util.List;

/**
 * 订单记录表Service接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayCorrelationDataService 
{
    /**
     * 查询订单记录表
     * 
     * @param id 订单记录表ID
     * @return 订单记录表
     */
    public AlipayCorrelationData selectAlipayCorrelationDataById(Long id);

    /**
     * 查询订单记录表列表
     * 
     * @param alipayCorrelationData 订单记录表
     * @return 订单记录表集合
     */
    public List<AlipayCorrelationData> selectAlipayCorrelationDataList(AlipayCorrelationData alipayCorrelationData);

    /**
     * 新增订单记录表
     * 
     * @param alipayCorrelationData 订单记录表
     * @return 结果
     */
    public int insertAlipayCorrelationData(AlipayCorrelationData alipayCorrelationData);

    /**
     * 修改订单记录表
     * 
     * @param alipayCorrelationData 订单记录表
     * @return 结果
     */
    public int updateAlipayCorrelationData(AlipayCorrelationData alipayCorrelationData);

    /**
     * 批量删除订单记录表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayCorrelationDataByIds(String ids);

    /**
     * 删除订单记录表信息
     * 
     * @param id 订单记录表ID
     * @return 结果
     */
    public int deleteAlipayCorrelationDataById(Long id);
}
