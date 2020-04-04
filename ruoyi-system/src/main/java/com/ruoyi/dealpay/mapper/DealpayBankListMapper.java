package com.ruoyi.dealpay.mapper;

import com.ruoyi.dealpay.domain.DealpayBankListEntity;

import java.util.List;

/**
 * 银行卡列Mapper接口
 *
 * @author k
 * @date 2020-04-03
 */
public interface DealpayBankListMapper {
    /**
     * 查询银行卡列
     *
     * @param id 银行卡列ID
     * @return 银行卡列
     */
    public DealpayBankListEntity selectDealpayBankListById(Long id);

    /**
     * 查询银行卡列列表
     *
     * @param dealpayBankList 银行卡列
     * @return 银行卡列集合
     */
    public List<DealpayBankListEntity> selectDealpayBankListList(DealpayBankListEntity dealpayBankList);

    /**
     * 新增银行卡列
     *
     * @param dealpayBankList 银行卡列
     * @return 结果
     */
    public int insertDealpayBankList(DealpayBankListEntity dealpayBankList);

    /**
     * 修改银行卡列
     *
     * @param dealpayBankList 银行卡列
     * @return 结果
     */
    public int updateDealpayBankList(DealpayBankListEntity dealpayBankList);

    /**
     * 删除银行卡列
     *
     * @param id 银行卡列ID
     * @return 结果
     */
    public int deleteDealpayBankListById(Long id);

    /**
     * 批量删除银行卡列
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayBankListByIds(String[] ids);
}