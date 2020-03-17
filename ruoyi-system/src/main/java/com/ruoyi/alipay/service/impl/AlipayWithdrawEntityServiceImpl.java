package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayWithdrawEntityMapper;
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.alipay.service.IAlipayWithdrawEntityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 会员提现记录Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayWithdrawEntityServiceImpl implements IAlipayWithdrawEntityService 
{
    @Autowired
    private AlipayWithdrawEntityMapper alipayWithdrawEntityMapper;

    /**
     * 查询会员提现记录
     * 
     * @param id 会员提现记录ID
     * @return 会员提现记录
     */
    @Override
    public AlipayWithdrawEntity selectAlipayWithdrawEntityById(Long id)
    {
        return alipayWithdrawEntityMapper.selectAlipayWithdrawEntityById(id);
    }

    /**
     * 查询会员提现记录列表
     * 
     * @param alipayWithdrawEntity 会员提现记录
     * @return 会员提现记录
     */
    @Override
    public List<AlipayWithdrawEntity> selectAlipayWithdrawEntityList(AlipayWithdrawEntity alipayWithdrawEntity)
    {
        return alipayWithdrawEntityMapper.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
    }

    /**
     * 新增会员提现记录
     * 
     * @param alipayWithdrawEntity 会员提现记录
     * @return 结果
     */
    @Override
    public int insertAlipayWithdrawEntity(AlipayWithdrawEntity alipayWithdrawEntity)
    {
        alipayWithdrawEntity.setCreateTime(DateUtils.getNowDate());
        return alipayWithdrawEntityMapper.insertAlipayWithdrawEntity(alipayWithdrawEntity);
    }

    /**
     * 修改会员提现记录
     * 
     * @param alipayWithdrawEntity 会员提现记录
     * @return 结果
     */
    @Override
    public int updateAlipayWithdrawEntity(AlipayWithdrawEntity alipayWithdrawEntity)
    {
        return alipayWithdrawEntityMapper.updateAlipayWithdrawEntity(alipayWithdrawEntity);
    }

    /**
     * 删除会员提现记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayWithdrawEntityByIds(String ids)
    {
        return alipayWithdrawEntityMapper.deleteAlipayWithdrawEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员提现记录信息
     * 
     * @param id 会员提现记录ID
     * @return 结果
     */
    @Override
    public int deleteAlipayWithdrawEntityById(Long id)
    {
        return alipayWithdrawEntityMapper.deleteAlipayWithdrawEntityById(id);
    }
}
