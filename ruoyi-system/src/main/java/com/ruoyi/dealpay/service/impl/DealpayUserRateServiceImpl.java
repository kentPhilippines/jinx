package com.ruoyi.dealpay.service.impl;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dealpay.mapper.DealpayUserRateMapper;
import com.ruoyi.dealpay.domain.DealpayUserRateEntity;
import com.ruoyi.dealpay.service.IDealpayUserRateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 费率Service业务层处理
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Service
public class DealpayUserRateServiceImpl implements IDealpayUserRateService {
    @Autowired
    private DealpayUserRateMapper dealpayUserRateMapper;

    /**
     * 查询费率
     *
     * @param id 费率ID
     * @return 费率
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public DealpayUserRateEntity selectDealpayUserRateEntityById(Long id) {
        return dealpayUserRateMapper.selectDealpayUserRateEntityById(id);
    }

    /**
     * 查询费率列表
     *
     * @param dealpayUserRateEntity 费率
     * @return 费率
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public List<DealpayUserRateEntity> selectDealpayUserRateEntityList(DealpayUserRateEntity dealpayUserRateEntity) {
        return dealpayUserRateMapper.selectDealpayUserRateEntityList(dealpayUserRateEntity);
    }

    /**
     * 新增费率
     *
     * @param dealpayUserRateEntity 费率
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayUserRateEntity(DealpayUserRateEntity dealpayUserRateEntity) {
        dealpayUserRateEntity.setCreateTime(DateUtils.getNowDate());
        dealpayUserRateEntity.setUserType(3);
        return dealpayUserRateMapper.insertDealpayUserRateEntity(dealpayUserRateEntity);
    }

    /**
     * 修改费率
     *
     * @param dealpayUserRateEntity 费率
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayUserRateEntity(DealpayUserRateEntity dealpayUserRateEntity) {
        return dealpayUserRateMapper.updateDealpayUserRateEntity(dealpayUserRateEntity);
    }

    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int changeStatus(String id, String userId, String feeType, String switchs) {
        if ("2".equals(feeType) && "1".equals(switchs)) {
            List<DealpayUserRateEntity> list = dealpayUserRateMapper.selectRateEntityByUserId(userId,feeType);
            if (list.size() > 0){
                throw new BusinessException("操作失败，用户不能同时开启两种代付费率");
            }
        }
        DealpayUserRateEntity result = dealpayUserRateMapper.checkDealpayUserIdUnique(userId);
        if (result == null) {
            throw new BusinessException("用户不存在");
        }
        if (result.getSwitchs() == 0) {
            throw new BusinessException("此用户已被停用");
        }
        return dealpayUserRateMapper.updateStatus(id, switchs);
    }

}
