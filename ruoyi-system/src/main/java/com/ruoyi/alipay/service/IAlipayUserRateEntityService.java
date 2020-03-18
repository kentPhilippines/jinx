package com.ruoyi.alipay.service;

import com.ruoyi.system.domain.AlipayUserRateEntity;
import java.util.List;

/**
 * 用户产品费率Service接口
 * 
 * @author kiwi
 * @date 2020-03-18
 */
public interface IAlipayUserRateEntityService 
{
    /**
     * 查询用户产品费率
     * 
     * @param id 用户产品费率ID
     * @return 用户产品费率
     */
    public AlipayUserRateEntity selectAlipayUserRateEntityById(Long id);

    /**
     * 查询用户产品费率列表
     * 
     * @param alipayUserRateEntity 用户产品费率
     * @return 用户产品费率集合
     */
    public List<AlipayUserRateEntity> selectAlipayUserRateEntityList(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 新增用户产品费率
     * 
     * @param alipayUserRateEntity 用户产品费率
     * @return 结果
     */
    public int insertAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 修改用户产品费率
     * 
     * @param alipayUserRateEntity 用户产品费率
     * @return 结果
     */
    public int updateAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 批量删除用户产品费率
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayUserRateEntityByIds(String ids);

    /**
     * 删除用户产品费率信息
     * 
     * @param id 用户产品费率ID
     * @return 结果
     */
    public int deleteAlipayUserRateEntityById(Long id);
}
