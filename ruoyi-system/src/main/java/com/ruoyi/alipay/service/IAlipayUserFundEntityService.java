package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import java.util.List;

/**
 * 用户资金账户Service接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayUserFundEntityService 
{
    /**
     * 查询用户资金账户
     * 
     * @param id 用户资金账户ID
     * @return 用户资金账户
     */
    public AlipayUserFundEntity selectAlipayUserFundEntityById(Long id);

    /**
     * 查询用户资金账户列表
     * 
     * @param alipayUserFundEntity 用户资金账户
     * @return 用户资金账户集合
     */
    public List<AlipayUserFundEntity> selectAlipayUserFundEntityList(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 新增用户资金账户
     * 
     * @param alipayUserFundEntity 用户资金账户
     * @return 结果
     */
    public int insertAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 修改用户资金账户
     * 
     * @param alipayUserFundEntity 用户资金账户
     * @return 结果
     */
    public int updateAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 批量删除用户资金账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayUserFundEntityByIds(String ids);

    /**
     * 删除用户资金账户信息
     * 
     * @param id 用户资金账户ID
     * @return 结果
     */
    public int deleteAlipayUserFundEntityById(Long id);
}
