package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import java.util.List;

/**
 * 用户产品费率Mapper接口
 * 
 * @author kiwi
 * @date 2020-03-18
 */
public interface AlipayUserRateEntityMapper 
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
     * 删除用户产品费率
     * 
     * @param id 用户产品费率ID
     * @return 结果
     */
    public int deleteAlipayUserRateEntityById(Long id);

    /**
     * 批量删除用户产品费率
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayUserRateEntityByIds(String[] ids);
}
