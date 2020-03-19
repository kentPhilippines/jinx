package com.ruoyi.alipay.service.impl;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.MerchantInfoEntityMapper;
import com.ruoyi.alipay.domain.MerchantInfoEntity;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.core.text.Convert;

import javax.management.ValueExp;

/**
 * 商户信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-18
 */
@Service
public class MerchantInfoEntityServiceImpl implements IMerchantInfoEntityService {
    @Autowired
    private MerchantInfoEntityMapper merchantInfoEntityMapper;

    /**
     * 查询商户信息
     *
     * @param id 商户信息ID
     * @return 商户信息
     */
    @Override
    public MerchantInfoEntity selectMerchantInfoEntityById(Long id) {
        return merchantInfoEntityMapper.selectMerchantInfoEntityById(id);
    }

    /**
     * 查询商户信息列表
     *
     * @param merchantInfoEntity 商户信息
     * @return 商户信息
     */
    @Override
    @DataSource(value= DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserInfo> selectMerchantInfoEntityList(AlipayUserInfo merchantInfoEntity) {
        return merchantInfoEntityMapper.selectMerchantInfoEntityList(merchantInfoEntity);
    }

    /**
     * 新增商户信息
     *
     * @param merchantInfoEntity 商户信息
     * @return 结果
     */
    @Override
    public int insertMerchantInfoEntity(MerchantInfoEntity merchantInfoEntity) {
        merchantInfoEntity.setCreateTime(DateUtils.getNowDate());
        return merchantInfoEntityMapper.insertMerchantInfoEntity(merchantInfoEntity);
    }

    /**
     * 修改商户信息
     *
     * @param merchantInfoEntity 商户信息
     * @return 结果
     */
    @Override
    public int updateMerchantInfoEntity(MerchantInfoEntity merchantInfoEntity) {
        merchantInfoEntity.setUpdateTime(DateUtils.getNowDate());
        return merchantInfoEntityMapper.updateMerchantInfoEntity(merchantInfoEntity);
    }

    /**
     * 删除商户信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerchantInfoEntityByIds(String ids) {
        return merchantInfoEntityMapper.deleteMerchantInfoEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户信息信息
     *
     * @param id 商户信息ID
     * @return 结果
     */
    @Override
    public int deleteMerchantInfoEntityById(Long id) {
        return merchantInfoEntityMapper.deleteMerchantInfoEntityById(id);
    }
}
