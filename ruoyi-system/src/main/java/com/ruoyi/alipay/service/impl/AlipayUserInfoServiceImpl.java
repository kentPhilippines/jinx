package com.ruoyi.alipay.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.HashKit;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayUserInfoMapper;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.core.text.Convert;


/**
 * 用户详情Service业务层处理
 *
 * @author OTC
 * @date 2020-02-27
 */
@Service
public class AlipayUserInfoServiceImpl implements IAlipayUserInfoService {
    @Autowired
    private AlipayUserInfoMapper alipayUserInfoMapper;

    /**
     * 查询用户详情
     *
     * @param id 用户详情ID
     * @return 用户详情
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayUserInfo selectAlipayUserInfoById(Long id) {
        return alipayUserInfoMapper.selectAlipayUserInfoById(id);
    }

    /**
     * 查询用户详情列表
     *
     * @param alipayUserInfo 用户详情
     * @return 用户详情
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserInfo> selectAlipayUserInfoList(AlipayUserInfo alipayUserInfo) {
        return alipayUserInfoMapper.selectAliaUserInfoList(alipayUserInfo);
    }

    /**
     * 新增用户详情
     *
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    @Override
    public int insertAlipayUserInfo(AlipayUserInfo alipayUserInfo) {
        return alipayUserInfoMapper.insertAliaUserInfo(alipayUserInfo);
    }

    /**
     * 修改用户详情
     *
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    @Override
    public int updateAlipayUserInfo(AlipayUserInfo alipayUserInfo) {
        return alipayUserInfoMapper.updateAlipayUserInfo(alipayUserInfo);
    }

    /**
     * 删除用户详情对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayUserInfoByIds(String ids) {
        return alipayUserInfoMapper.deleteAlipayUserInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户详情信息
     *
     * @param id 用户详情ID
     * @return 结果
     */
    @Override
    public int deleteAlipayUserInfoById(Long id) {
        return alipayUserInfoMapper.deleteAlipayUserInfoById(id);
    }

    /**
     * 验证用户名的唯一
     *
     * @param alipayUserInfo
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public String checkAlipayUserIdUnique(AlipayUserInfo alipayUserInfo) {
        AlipayUserInfo isExist = alipayUserInfoMapper.checkAlipayUserIdUnique(alipayUserInfo.getUserId());
        if (StringUtils.isNotNull(isExist)) {
            return UserConstants.QRUSER_NAME_NOT_UNIQUE;
        }
        return UserConstants.QRUSER_NAME_UNIQUE;
    }

    /**
     * 重置用户登陆密码
     *
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public String resetLoginPwd(Long id) {
        AlipayUserInfo alipayUserInfo = alipayUserInfoMapper.selectAlipayUserInfoById(id);
        if (alipayUserInfo == null) {
            throw new BusinessException("ID不能为空或此用户不存在");
        }
        String resetPwd = HashKit.resetPassword();
        String md5 = HashKit.encodePassword(alipayUserInfo.getUserId(), resetPwd, alipayUserInfo.getSalt());
        alipayUserInfo.setPassword(md5);
        int i = alipayUserInfoMapper.updateUserLoginPwd(alipayUserInfo);
        return i == 1 ? resetPwd : "false";
    }

    /**
     * 重置用户提现密码
     *
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public String resetWithdrawalPwd(Long id) {
        AlipayUserInfo alipayUserInfo = alipayUserInfoMapper.selectAlipayUserInfoById(id);
        if (alipayUserInfo == null) {
            throw new BusinessException("ID不能为空或此用户不存在");
        }
        String resetPwd = HashKit.resetPassword();
        String md5 = HashKit.encodePassword(alipayUserInfo.getUserId(), resetPwd, alipayUserInfo.getSalt());
        alipayUserInfo.setPayPasword(md5);
        int j = alipayUserInfoMapper.updateWithdrawalPwd(alipayUserInfo);
        return j == 1 ? resetPwd : "false";
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayUserInfo findMerchantInfoByUserId(String userId) {
        return alipayUserInfoMapper.selectMerhantInfoByUserId(userId);
    }


    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserInfo> selectAlipayUserInfoByControl(AlipayUserInfo alipayUserInfo) {
        return alipayUserInfoMapper.selectAlipayUserInfoListByControl(alipayUserInfo);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int toSaveQrChargeList(AlipayUserInfo alipayUserInfo) {
        String[] str = alipayUserInfo.getParamStr();
        if (StringUtils.isNotEmpty(str)) {
            String qrCharge = StringUtils.join(str,",");
            alipayUserInfo.setQrRechargeList(qrCharge);
            alipayUserInfo.setSubmitTime(new Date());
        }else{
            return alipayUserInfoMapper.clearAlipayQrChargeListById(alipayUserInfo.getId());
        }
        return alipayUserInfoMapper.updateAlipayUserInfo(alipayUserInfo);
    }
}
