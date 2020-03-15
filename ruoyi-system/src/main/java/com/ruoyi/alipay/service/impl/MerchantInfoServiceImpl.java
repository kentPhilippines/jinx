package com.ruoyi.alipay.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.MerchantInfoMapper;
import com.ruoyi.alipay.domain.MerchantInfoEntity;
import com.ruoyi.alipay.service.IMerchantInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商户详情Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-14
 */
@Service
public class MerchantInfoServiceImpl implements IMerchantInfoService {
    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    /**
     * 查询商户详情
     *
     * @param id 商户详情ID
     * @return 商户详情
     */
    @Override
    public MerchantInfoEntity selectMerchantInfoEntityById(Long id) {
        return merchantInfoMapper.selectMerchantInfoEntityById(id);
    }

    /**
     * 查询商户详情列表
     *
     * @param merchantInfoEntity 商户详情
     * @return 商户详情
     */
    @Override
    public List<MerchantInfoEntity> selectMerchantInfoEntityList(MerchantInfoEntity merchantInfoEntity) {
        return merchantInfoMapper.selectMerchantInfoEntityList(merchantInfoEntity);
    }

    /**
     * 新增商户详情
     *
     * @param merchantInfoEntity 商户详情
     * @return 结果
     */
    @Override
    public int insertMerchantInfoEntity(MerchantInfoEntity merchantInfoEntity) {
        merchantInfoEntity.setCreateTime(DateUtils.getNowDate());
        return merchantInfoMapper.insertMerchantInfoEntity(merchantInfoEntity);
    }

    /**
     * 修改商户详情
     *
     * @param merchantInfoEntity 商户详情
     * @return 结果
     */
    @Override
    public int updateMerchantInfoEntity(MerchantInfoEntity merchantInfoEntity) {
        merchantInfoEntity.setUpdateTime(DateUtils.getNowDate());
        return merchantInfoMapper.updateMerchantInfoEntity(merchantInfoEntity);
    }

    /**
     * 删除商户详情对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerchantInfoEntityByIds(String ids) {
        return merchantInfoMapper.deleteMerchantInfoEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户详情信息
     *
     * @param id 商户详情ID
     * @return 结果
     */
    @Override
    public int deleteMerchantInfoEntityById(Long id) {
        return merchantInfoMapper.deleteMerchantInfoEntityById(id);
    }
}
