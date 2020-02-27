package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayUserFund;
import java.util.List;

/**
 * 用户资金账户Service接口
 * 
 * @author otc
 * @date 2020-02-27
 */
public interface IAlipayUserFundService 
{
    /**
     * 查询用户资金账户
     * 
     * @param id 用户资金账户ID
     * @return 用户资金账户
     */
    public AlipayUserFund selectAlipayUserFundById(Long id);

    /**
     * 查询用户资金账户列表
     * 
     * @param alipayUserFund 用户资金账户
     * @return 用户资金账户集合
     */
    public List<AlipayUserFund> selectAlipayUserFundList(AlipayUserFund alipayUserFund);

    /**
     * 新增用户资金账户
     * 
     * @param alipayUserFund 用户资金账户
     * @return 结果
     */
    public int insertAlipayUserFund(AlipayUserFund alipayUserFund);

    /**
     * 修改用户资金账户
     * 
     * @param alipayUserFund 用户资金账户
     * @return 结果
     */
    public int updateAlipayUserFund(AlipayUserFund alipayUserFund);

    /**
     * 批量删除用户资金账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayUserFundByIds(String ids);

    /**
     * 删除用户资金账户信息
     * 
     * @param id 用户资金账户ID
     * @return 结果
     */
    public int deleteAlipayUserFundById(Long id);
}
