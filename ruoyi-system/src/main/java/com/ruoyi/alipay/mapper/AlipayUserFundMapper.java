package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserFund;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户资金账户Mapper接口
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
@Service
public interface AlipayUserFundMapper 
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
     * 删除用户资金账户
     * 
     * @param id 用户资金账户ID
     * @return 结果
     */
    public int deleteAlipayUserFundById(Long id);

    /**
     * 批量删除用户资金账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayUserFundByIds(String[] ids);
}
