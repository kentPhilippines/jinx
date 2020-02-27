package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayBankList;
import com.ruoyi.alipay.domain.AlipayBankList;
import java.util.List;

/**
 * 银行卡列Mapper接口
 * 
 * @author ruoyi
 * @date 2020-02-26
 */
public interface AlipayBankListMapper 
{
    /**
     * 查询银行卡列
     * 
     * @param id 银行卡列ID
     * @return 银行卡列
     */
    public AlipayBankList selectAlipayBankListById(Long id);

    /**
     * 查询银行卡列列表
     * 
     * @param alipayBankList 银行卡列
     * @return 银行卡列集合
     */
    public List<AlipayBankList> selectAlipayBankListList(AlipayBankList alipayBankList);

    /**
     * 新增银行卡列
     * 
     * @param alipayBankList 银行卡列
     * @return 结果
     */
    public int insertAlipayBankList(AlipayBankList alipayBankList);

    /**
     * 修改银行卡列
     * 
     * @param alipayBankList 银行卡列
     * @return 结果
     */
    public int updateAlipayBankList(AlipayBankList alipayBankList);

    /**
     * 删除银行卡列
     * 
     * @param id 银行卡列ID
     * @return 结果
     */
    public int deleteAlipayBankListById(Long id);

    /**
     * 批量删除银行卡列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayBankListByIds(String[] ids);
}
