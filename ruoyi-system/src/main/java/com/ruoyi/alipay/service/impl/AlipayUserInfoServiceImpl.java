package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.mapper.AlipayUserInfoMapper;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.HashKit;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 用户详情Service业务层处理
 *
 * @author OTC
 * @date 2020-02-27
 */
@Service
public class AlipayUserInfoServiceImpl implements IAlipayUserInfoService {
    @Resource
    private AlipayUserInfoMapper alipayUserInfoMapper;
    private final String LOGIN_RETRY_KEY="jinxinmerchant:shiro:cache:loginRecordCache:";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public String resetLoginErrorCount(Long id) {
        AlipayUserInfo userInfo=alipayUserInfoMapper.selectAlipayUserInfoById(id);
        if (userInfo == null) {
            throw new BusinessException("ID不能为空或此卡商不存在");
        }
        redisTemplate.delete(LOGIN_RETRY_KEY+userInfo.getUserId());
        return "";
    }
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
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayUserInfo(AlipayUserInfo alipayUserInfo) {
        return alipayUserInfoMapper.insertAlipayUserInfo(alipayUserInfo);
    }

    /**
     * 修改用户详情
     *
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
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
        String[] str1 = alipayUserInfo.getParamStr2();
        String qrCharge = StringUtils.join(str, ",");
        String queueList = StringUtils.join(str1, ",");
        if (StringUtils.isNotEmpty(str)) {
            alipayUserInfo.setQrRechargeList(qrCharge);
        } else {
            alipayUserInfo.setQrRechargeList(null);
        }
        if (StringUtils.isNotEmpty(str1)) {
            alipayUserInfo.setQueueList(queueList);
        } else {
            alipayUserInfo.setQueueList(null);
        }
        alipayUserInfo.setSubmitTime(new Date());
        return alipayUserInfoMapper.updateAlipayUserInfo2Control(alipayUserInfo);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserInfo> selectdealpayUserInfoByAgent() {
        return alipayUserInfoMapper.selectdealpayUserInfoByAgent();
    }

	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public boolean updatePaypassword(String userId, String password,String salt) {
        String md5 = HashKit.encodePassword(userId, password, salt);
        int i = alipayUserInfoMapper.updatePaypassword(userId, md5);
        return i > 0 && i < 2;
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserInfo> selectAllUserInfoList(AlipayUserInfo alipayUserInfo) {
        return alipayUserInfoMapper.selectAllUserInfoList(alipayUserInfo);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int updateAutoWit(String id, String autoWitStatus) {
        return alipayUserInfoMapper.updateAutoWit(id, autoWitStatus);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int updateStatus(String userId, Integer status) {
        return alipayUserInfoMapper.updateStatus(userId, status);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<String> findSonUser(String userId) {
        return alipayUserInfoMapper.findSonUser(userId);

    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayUserInfo> findAgenByUser(String userId) {
        AlipayUserInfo alipayUserInfo = new AlipayUserInfo();
        alipayUserInfo.setAgent(userId);
        return alipayUserInfoMapper.selectAliaUserInfoList(alipayUserInfo);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int upUserAgents(Long id) {
        return alipayUserInfoMapper.upUserAgents(id);
    }

    @Override
    public AlipayUserInfo findUserByUserId(String orderAccount) {
        return null;
    }
}
