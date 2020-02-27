package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserRate;
import java.util.List;

/**
 * 用户产品费率Mapper接口
 * 
 * @author otc
 * @date 2020-02-27
 */
public interface AlipayUserRateMapper 
{
    /**
     * 查询用户产品费率
     * 
     * @param id 用户产品费率ID
     * @return 用户产品费率
     */
    public AlipayUserRate selectAlipayUserRateById(Long id);

    /**
     * 查询用户产品费率列表
     * 
     * @param alipayUserRate 用户产品费率
     * @return 用户产品费率集合
     */
    public List<AlipayUserRate> selectAlipayUserRateList(AlipayUserRate alipayUserRate);

    /**
     * 新增用户产品费率
     * 
     * @param alipayUserRate 用户产品费率
     * @return 结果
     */
    public int insertAlipayUserRate(AlipayUserRate alipayUserRate);

    /**
     * 修改用户产品费率
     * 
     * @param alipayUserRate 用户产品费率
     * @return 结果
     */
    public int updateAlipayUserRate(AlipayUserRate alipayUserRate);

    /**
     * 删除用户产品费率
     * 
     * @param id 用户产品费率ID
     * @return 结果
     */
    public int deleteAlipayUserRateById(Long id);

    /**
     * 批量删除用户产品费率
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayUserRateByIds(String[] ids);
}
