package com.ruoyi.alipay.service.impl;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.mapper.AlipayUserFundEntityMapper;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.HashKit;
import com.ruoyi.common.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.MerchantInfoEntityMapper;
import com.ruoyi.alipay.domain.MerchantInfoEntity;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AlipayUserFundEntityMapper alipayUserFundEntityMapper;

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
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
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
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    @Transactional(rollbackFor = Exception.class)
    public int insertMerchantInfoEntity(AlipayUserInfo merchantInfoEntity) {
        List<String> keys = RSAUtils.genKeyPair();
        if (keys == null) {
            throw new BusinessException("获取密钥对错误，操作失败");
        }
        String salt = HashKit.randomSalt();
        String md5 = HashKit.encodePassword(merchantInfoEntity.getUserId(), merchantInfoEntity.getPassword(), salt);//登陆密码
        String cash = HashKit.encodePassword(merchantInfoEntity.getUserId(), merchantInfoEntity.getPayPasword(), salt);//提现密码
        merchantInfoEntity.setPassword(md5);
        merchantInfoEntity.setSalt(salt);
        merchantInfoEntity.setPayPasword(cash);
        merchantInfoEntity.setUserType(1);
        merchantInfoEntity.setIsAgent(String.valueOf(1));
        merchantInfoEntity.setPublicKey(keys.get(0));
        merchantInfoEntity.setPrivateKey(keys.get(1));
        //新增商户用户
        int i = merchantInfoEntityMapper.insertMerchantInfoEntity(merchantInfoEntity);
        if (i > 0) {//成功后增加资金信息
            int j = alipayUserFundEntityMapper.insertAlipayUserFundInfo(merchantInfoEntity);
        }
        return i > 0 ? 1 : 0;
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
