package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import java.util.List;

/**
 * 会员提现记录Mapper接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayWithdrawEntityMapper 
{
    /**
     * 查询会员提现记录
     * 
     * @param id 会员提现记录ID
     * @return 会员提现记录
     */
    public AlipayWithdrawEntity selectAlipayWithdrawEntityById(Long id);

    /**
     * 查询会员提现记录列表
     * 
     * @param alipayWithdrawEntity 会员提现记录
     * @return 会员提现记录集合
     */
    public List<AlipayWithdrawEntity> selectAlipayWithdrawEntityList(AlipayWithdrawEntity alipayWithdrawEntity);

    /**
     * 新增会员提现记录
     * 
     * @param alipayWithdrawEntity 会员提现记录
     * @return 结果
     */
    public int insertAlipayWithdrawEntity(AlipayWithdrawEntity alipayWithdrawEntity);

    /**
     * 修改会员提现记录
     * 
     * @param alipayWithdrawEntity 会员提现记录
     * @return 结果
     */
    public int updateAlipayWithdrawEntity(AlipayWithdrawEntity alipayWithdrawEntity);

    /**
     * 删除会员提现记录
     * 
     * @param id 会员提现记录ID
     * @return 结果
     */
    public int deleteAlipayWithdrawEntityById(Long id);

    /**
     * 批量删除会员提现记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayWithdrawEntityByIds(String[] ids);
}
