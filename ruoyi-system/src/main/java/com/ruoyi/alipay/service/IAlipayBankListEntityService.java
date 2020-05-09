package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayBankListEntity;
import java.util.List;

/**
 * 银行卡列表Service接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayBankListEntityService 
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
     * 批量删除银行卡列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayBankListEntityByIds(String ids);

    /**
     *
     * @param alipayBankListEntity
     * @return
     */
    int updateBankCardStatusById(AlipayBankListEntity alipayBankListEntity);

    /**
     * 根据账号和银行卡生成银行卡黑名单
     * @param alipayBankListEntity
     * @return
     */
    int updateAlipayBankCardBlackList(AlipayBankListEntity alipayBankListEntity);

    /**
     * 删除银行卡黑名单
     * @param ids
     * @return
     */
    int deleteAlipayBankBlackListById(String ids);

    /**
     * <p>根据银行卡卡号和银行卡所属人查询银行卡信息</p>
     * @param s
     * @param merchantId
     * @return
     */
    AlipayBankListEntity selectAlipayBankListEntityByAcc(String s, String merchantId);
}
