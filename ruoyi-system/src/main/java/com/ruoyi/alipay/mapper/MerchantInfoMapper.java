package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.MerchantInfoEntity;

import java.util.List;

/**
 * 商户详情Mapper接口
 *
 * @author kiwi
 * @date 2020-03-14
 */
public interface MerchantInfoMapper {
    /**
     * 查询商户详情
     *
     * @param id 商户详情ID
     * @return 商户详情
     */
    MerchantInfoEntity selectMerchantInfoEntityById(Long id);

    /**
     * 查询商户详情列表
     *
     * @param merchantInfoEntity 商户详情
     * @return 商户详情集合
     */
    List<MerchantInfoEntity> selectMerchantInfoEntityList(MerchantInfoEntity merchantInfoEntity);

    /**
     * 新增商户详情
     *
     * @param merchantInfoEntity 商户详情
     * @return 结果
     */
    int insertMerchantInfoEntity(MerchantInfoEntity merchantInfoEntity);

    /**
     * 修改商户详情
     *
     * @param merchantInfoEntity 商户详情
     * @return 结果
     */
    int updateMerchantInfoEntity(MerchantInfoEntity merchantInfoEntity);

    /**
     * 删除商户详情
     *
     * @param id 商户详情ID
     * @return 结果
     */
    int deleteMerchantInfoEntityById(Long id);

    /**
     * 批量删除商户详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteMerchantInfoEntityByIds(String[] ids);
}
