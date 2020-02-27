package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayMedium;
import java.util.List;

/**
 * 收款媒介列Service接口
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public interface IAlipayMediumService 
{
    /**
     * 查询收款媒介列
     * 
     * @param id 收款媒介列ID
     * @return 收款媒介列
     */
    public AlipayMedium selectAlipayMediumById(Long id);

    /**
     * 查询收款媒介列列表
     * 
     * @param alipayMedium 收款媒介列
     * @return 收款媒介列集合
     */
    public List<AlipayMedium> selectAlipayMediumList(AlipayMedium alipayMedium);

    /**
     * 新增收款媒介列
     * 
     * @param alipayMedium 收款媒介列
     * @return 结果
     */
    public int insertAlipayMedium(AlipayMedium alipayMedium);

    /**
     * 修改收款媒介列
     * 
     * @param alipayMedium 收款媒介列
     * @return 结果
     */
    public int updateAlipayMedium(AlipayMedium alipayMedium);

    /**
     * 批量删除收款媒介列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayMediumByIds(String ids);

    /**
     * 删除收款媒介列信息
     * 
     * @param id 收款媒介列ID
     * @return 结果
     */
    public int deleteAlipayMediumById(Long id);
}
