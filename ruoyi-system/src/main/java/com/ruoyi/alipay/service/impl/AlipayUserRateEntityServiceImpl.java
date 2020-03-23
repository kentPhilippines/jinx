package com.ruoyi.alipay.service.impl;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.mapper.AlipayUserInfoMapper;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayUserRateEntityMapper;
import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import com.ruoyi.alipay.service.IAlipayUserRateEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;

/**
 * 用户产品费率Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-18
 */
@Service
public class AlipayUserRateEntityServiceImpl implements IAlipayUserRateEntityService {
    @Autowired
    private AlipayUserRateEntityMapper alipayUserRateEntityMapper;

    @Autowired
    private AlipayUserInfoMapper alipayUserInfoMapper;

    /**
     * 查询用户产品费率
     *
     * @param id 用户产品费率ID
     * @return 用户产品费率
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayUserRateEntity selectAlipayUserRateEntityById(Long id) {
        return alipayUserRateEntityMapper.selectAlipayUserRateEntityById(id);
    }

    /**
     * 查询用户产品费率列表
     *
     * @param alipayUserRateEntity 用户产品费率
     * @return 用户产品费率
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserRateEntity> selectAlipayUserRateEntityList(AlipayUserRateEntity alipayUserRateEntity) {
        return alipayUserRateEntityMapper.selectAlipayUserRateEntityList(alipayUserRateEntity);
    }

    /**
     * 新增用户产品费率
     *
     * @param alipayUserRateEntity 用户产品费率
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity) {
        alipayUserRateEntity.setCreateTime(DateUtils.getNowDate());
        alipayUserRateEntity.setUserType(1);
        if (alipayUserRateEntity.getFeeType() == 2) { //判断是否为代付
            alipayUserRateEntity.setSwitchs(0);
        }
        AlipayUserInfo result = alipayUserInfoMapper.checkAlipayUserIdUnique(alipayUserRateEntity.getUserId());
        if (result == null) {
            throw new BusinessException("商户用户不存在");
        }
        if (result.getSwitchs() == 2) {
            throw new BusinessException("此商户已被停用");
        }

        return alipayUserRateEntityMapper.insertAlipayUserRateEntity(alipayUserRateEntity);
    }

    /**
     * 修改用户产品费率
     *
     * @param alipayUserRateEntity 用户产品费率
     * @return 结果
     */
    @Override
    public int updateAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity) {
        return alipayUserRateEntityMapper.updateAlipayUserRateEntity(alipayUserRateEntity);
    }

    /**
     * 删除用户产品费率对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int deleteAlipayUserRateEntityByIds(String ids) {
        AlipayUserRateEntity alipayUserRateEntity = alipayUserRateEntityMapper.selectAlipayUserRateEntityById(Long.valueOf(ids));
        if (alipayUserRateEntity.getSwitchs() == 1) {
            throw new BusinessException("开启状态下，不允许删除操作");
        }
        return alipayUserRateEntityMapper.deleteAlipayUserRateEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户产品费率信息
     *
     * @param id 用户产品费率ID
     * @return 结果
     */
    @Override
    public int deleteAlipayUserRateEntityById(Long id) {
        return alipayUserRateEntityMapper.deleteAlipayUserRateEntityById(id);
    }


    //下面是码商的费率的逻辑处理

    /**
     * 码商的费率
     *
     * @param alipayUserRateEntity 用户产品费率
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserRateEntity> selectUserRateEntityList_qr(AlipayUserRateEntity alipayUserRateEntity) {
        return alipayUserRateEntityMapper.selectUserRateEntityList_qr(alipayUserRateEntity);
    }


    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayUserRateEntity_qr(AlipayUserRateEntity alipayUserRateEntity) {
        alipayUserRateEntity.setCreateTime(DateUtils.getNowDate());
        alipayUserRateEntity.setUserType(2);
        if (alipayUserRateEntity.getFeeType() == 2) { //判断是否为代付
            alipayUserRateEntity.setSwitchs(0);
        }
        AlipayUserInfo result = alipayUserInfoMapper.checkAlipayUserIdUnique(alipayUserRateEntity.getUserId());
        if (result == null) {
            throw new BusinessException("码商用户不存在");
        }
        if (result.getSwitchs() == 2) {
            throw new BusinessException("此码商已被停用");
        }
        return alipayUserRateEntityMapper.insertAlipayUserRateEntity_qr(alipayUserRateEntity);
    }

    @Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int changeStatus(String id, String userId, String feeType, String switchs) {
        if ("2".equals(feeType) && "1".equals(switchs)) {
            List<AlipayUserRateEntity> list = alipayUserRateEntityMapper.selectListObjectEntityByUserId(userId,feeType);
            if (list.size() > 0){
                throw new BusinessException("操作失败，用户不能同时开启两种代付费率");
            }
        }
        AlipayUserInfo result = alipayUserInfoMapper.checkAlipayUserIdUnique(userId);
        if (result == null) {
            throw new BusinessException("用户不存在");
        }
        if (result.getSwitchs() == 2) {
            throw new BusinessException("此用户已被停用");
        }
        return alipayUserRateEntityMapper.updateStatus(id, switchs);
    }

}
