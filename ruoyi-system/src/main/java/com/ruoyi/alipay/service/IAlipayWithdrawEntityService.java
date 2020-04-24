package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import java.util.List;

/**
 * 会员提现记录Service接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayWithdrawEntityService 
{
    /**
     * 查询会员提现记录
     * 
     * @param id 会员提现记录ID
     * @return 会员提现记录
     */
    AlipayWithdrawEntity selectAlipayWithdrawEntityById(Long id);

    /**
     * 查询会员提现记录列表
     * 
     * @param alipayWithdrawEntity 会员提现记录
     * @return 会员提现记录集合
     */
    List<AlipayWithdrawEntity> selectAlipayWithdrawEntityList(AlipayWithdrawEntity alipayWithdrawEntity);

}
