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
    AlipayUserFundEntity selectAlipayUserFundEntityById(Long id);

    /**
     * 查询用户资金账户列表
     * 
     * @param alipayUserFundEntity 用户资金账户
     * @return 用户资金账户集合
     */
    List<AlipayUserFundEntity> selectAlipayUserFundEntityList(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 新增用户资金账户
     * 
     * @param alipayUserFundEntity 用户资金账户
     * @return 结果
     */
    int insertAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 修改用户资金账户
     * 
     * @param alipayUserFundEntity 用户资金账户
     * @return 结果
     */
    int updateAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 根据userId查询用户的资金账户对象
     * @param merchantId
     * @return
     */
    AlipayUserFundEntity findAlipayUserFundByUserId(String merchantId);
}
