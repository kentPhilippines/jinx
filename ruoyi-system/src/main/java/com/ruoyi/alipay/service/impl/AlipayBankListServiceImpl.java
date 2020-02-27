package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayBankListMapper;
import com.ruoyi.alipay.domain.AlipayBankList;
import com.ruoyi.alipay.service.IAlipayBankListService;
import com.ruoyi.common.core.text.Convert;

/**
 * 银行卡列Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-02-26
 */
@Service
public class AlipayBankListServiceImpl implements IAlipayBankListService 
{
    @Autowired
    private AlipayBankListMapper alipayBankListMapper;

    /**
     * 查询银行卡列
     * 
     * @param id 银行卡列ID
     * @return 银行卡列
     */
    @Override
    public AlipayBankList selectAlipayBankListById(Long id)
    {
        return alipayBankListMapper.selectAlipayBankListById(id);
    }

    /**
     * 查询银行卡列列表
     * 
     * @param alipayBankList 银行卡列
     * @return 银行卡列
     */
    @Override
    public List<AlipayBankList> selectAlipayBankListList(AlipayBankList alipayBankList)
    {
        return alipayBankListMapper.selectAlipayBankListList(alipayBankList);
    }

    /**
     * 新增银行卡列
     * 
     * @param alipayBankList 银行卡列
     * @return 结果
     */
    @Override
    public int insertAlipayBankList(AlipayBankList alipayBankList)
    {
        return alipayBankListMapper.insertAlipayBankList(alipayBankList);
    }

    /**
     * 修改银行卡列
     * 
     * @param alipayBankList 银行卡列
     * @return 结果
     */
    @Override
    public int updateAlipayBankList(AlipayBankList alipayBankList)
    {
        return alipayBankListMapper.updateAlipayBankList(alipayBankList);
    }

    /**
     * 删除银行卡列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayBankListByIds(String ids)
    {
        return alipayBankListMapper.deleteAlipayBankListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除银行卡列信息
     * 
     * @param id 银行卡列ID
     * @return 结果
     */
    @Override
    public int deleteAlipayBankListById(Long id)
    {
        return alipayBankListMapper.deleteAlipayBankListById(id);
    }
}
