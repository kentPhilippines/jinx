package com.ruoyi.alipay.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
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
        return alipayUserInfoMapper.selectAlipayUserInfoList(alipayUserInfo);
    }

    /**
     * 新增用户详情
     *
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    @Override
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
}
