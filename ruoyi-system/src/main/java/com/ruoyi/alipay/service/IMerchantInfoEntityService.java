package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.domain.MerchantInfoEntity;
import java.util.List;

/**
 * 商户信息Service接口
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
public interface IMerchantInfoEntityService 
{
    /**
     * 查询商户信息
     * 
     * @param id 商户信息ID
     * @return 商户信息
     */
    AlipayUserInfo selectMerchantInfoEntityById(Long id);

    /**
     * 查询商户信息列表
     * 
     * @param merchantInfoEntity 商户信息
     * @return 商户信息集合
     */
     List<AlipayUserInfo> selectMerchantInfoEntityList(AlipayUserInfo merchantInfoEntity);

    /**
     * 新增商户信息
     * 
     * @param merchantInfoEntity 商户信息
     * @return 结果
     */
     int insertMerchantInfoEntity(AlipayUserInfo merchantInfoEntity);

    /**
     * 修改商户信息
     * 
     * @param merchantInfoEntity 商户信息
     * @return 结果
     */
     int updateMerchantInfoEntity(MerchantInfoEntity merchantInfoEntity);

    /**
     * 批量删除商户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteMerchantInfoEntityByIds(String ids);

    /**
     * 删除商户信息信息
     * 
     * @param id 商户信息ID
     * @return 结果
     */
     int deleteMerchantInfoEntityById(Long id);
}
