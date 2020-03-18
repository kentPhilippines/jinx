package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayBankListEntity;
import java.util.List;

/**
 * 银行卡列表Mapper接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayBankListEntityMapper 
{
    /**
     * 查询银行卡列表
     * 
     * @param id 银行卡列表ID
     * @return 银行卡列表
     */
    public AlipayBankListEntity selectAlipayBankListEntityById(Long id);

    /**
     * 查询银行卡列表列表
     * 
     * @param alipayBankListEntity 银行卡列表
     * @return 银行卡列表集合
     */
    public List<AlipayBankListEntity> selectAlipayBankListEntityList(AlipayBankListEntity alipayBankListEntity);

    /**
     * 新增银行卡列表
     * 
     * @param alipayBankListEntity 银行卡列表
     * @return 结果
     */
    public int insertAlipayBankListEntity(AlipayBankListEntity alipayBankListEntity);

    /**
     * 修改银行卡列表
     * 
     * @param alipayBankListEntity 银行卡列表
     * @return 结果
     */
    public int updateAlipayBankListEntity(AlipayBankListEntity alipayBankListEntity);

    /**
     * 删除银行卡列表
     * 
     * @param id 银行卡列表ID
     * @return 结果
     */
    public int deleteAlipayBankListEntityById(Long id);

    /**
     * 批量删除银行卡列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayBankListEntityByIds(String[] ids);
}
