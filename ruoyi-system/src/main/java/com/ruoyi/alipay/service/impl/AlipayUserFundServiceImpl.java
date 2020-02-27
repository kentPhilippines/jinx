package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayUserFundMapper;
import com.ruoyi.alipay.domain.AlipayUserFund;
import com.ruoyi.alipay.service.IAlipayUserFundService;
import com.ruoyi.common.core.text.Convert;


/**
 * 用户资金账户Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
@Service
public class AlipayUserFundServiceImpl implements IAlipayUserFundService
{
    @Autowired
    private AlipayUserFundMapper alipayUserFundMapper;

    /**
     * 查询用户资金账户
     * 
     * @param id 用户资金账户ID
     * @return 用户资金账户
     */
    @Override
    public AlipayUserFund selectAlipayUserFundById(Long id)
    {
        return alipayUserFundMapper.selectAlipayUserFundById(id);
    }

    /**
     * 查询用户资金账户列表
     * 
     * @param alipayUserFund 用户资金账户
     * @return 用户资金账户
     */
    @Override
    public List<AlipayUserFund> selectAlipayUserFundList(AlipayUserFund alipayUserFund)
    {
        return alipayUserFundMapper.selectAlipayUserFundList(alipayUserFund);
    }

    /**
     * 新增用户资金账户
     * 
     * @param alipayUserFund 用户资金账户
     * @return 结果
     */
    @Override
    public int insertAlipayUserFund(AlipayUserFund alipayUserFund)
    {
        return alipayUserFundMapper.insertAlipayUserFund(alipayUserFund);
    }

    /**
     * 修改用户资金账户
     * 
     * @param alipayUserFund 用户资金账户
     * @return 结果
     */
    @Override
    public int updateAlipayUserFund(AlipayUserFund alipayUserFund)
    {
        return alipayUserFundMapper.updateAlipayUserFund(alipayUserFund);
    }

    /**
     * 删除用户资金账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayUserFundByIds(String ids)
    {
        return alipayUserFundMapper.deleteAlipayUserFundByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户资金账户信息
     * 
     * @param id 用户资金账户ID
     * @return 结果
     */
    @Override
    public int deleteAlipayUserFundById(Long id)
    {
        return alipayUserFundMapper.deleteAlipayUserFundById(id);
    }
}
