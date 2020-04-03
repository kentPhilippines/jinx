package com.ruoyi.dealpay.mapper;

import com.ruoyi.dealpay.domain.DealpayUserFundEntity;

import java.util.List;

/**
 * 用户资金账户Mapper接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface DealpayUserFundMapper {
    /**
     * 查询用户资金账户
     *
     * @param id 用户资金账户ID
     * @return 用户资金账户
     */
    DealpayUserFundEntity selectDealpayUserFundById(Long id);

    /**
     * 查询用户资金账户列表
     *
     * @param dealpayUserFund 用户资金账户
     * @return 用户资金账户集合
     */
    public List<DealpayUserFundEntity> selectDealpayUserFundList(DealpayUserFundEntity dealpayUserFund);

    /**
     * 新增用户资金账户
     *
     * @param dealpayUserFund 用户资金账户
     * @return 结果
     */
    public int insertDealpayUserFund(DealpayUserFundEntity dealpayUserFund);

    /**
     * 修改用户资金账户
     *
     * @param dealpayUserFund 用户资金账户
     * @return 结果
     */
    public int updateDealpayUserFund(DealpayUserFundEntity dealpayUserFund);

    /**
     * 删除用户资金账户
     *
     * @param id 用户资金账户ID
     * @return 结果
     */
    public int deleteDealpayUserFundById(Long id);

    /**
     * 批量删除用户资金账户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayUserFundByIds(String[] ids);
}
