package com.ruoyi.dealpay.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dealpay.domain.DealpayUserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dealpay.mapper.DealpayUserInfoMapper;
import com.ruoyi.dealpay.service.IDealpayUserInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户详情Service业务层处理
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Service
public class DealpayUserInfoServiceImpl implements IDealpayUserInfoService {
    @Autowired
    private DealpayUserInfoMapper dealpayUserInfoMapper;

    /**
     * 查询用户详情
     *
     * @param id 用户详情ID
     * @return 用户详情
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public DealpayUserInfoEntity selectDealpayUserInfoById(Long id) {
        return dealpayUserInfoMapper.selectDealpayUserInfoById(id);
    }

    /**
     * 查询用户详情列表
     *
     * @param dealpayUserInfo 用户详情
     * @return 用户详情
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public List<DealpayUserInfoEntity> selectDealpayUserInfoList(DealpayUserInfoEntity dealpayUserInfo) {
        return dealpayUserInfoMapper.selectDealpayUserInfoList(dealpayUserInfo);
    }

    /**
     * 新增用户详情
     *
     * @param dealpayUserInfo 用户详情
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayUserInfo(DealpayUserInfoEntity dealpayUserInfo) {
        dealpayUserInfo.setCreateTime(DateUtils.getNowDate());
        return dealpayUserInfoMapper.insertDealpayUserInfo(dealpayUserInfo);
    }

    /**
     * 修改用户详情
     *
     * @param dealpayUserInfo 用户详情
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayUserInfo(DealpayUserInfoEntity dealpayUserInfo) {
        return dealpayUserInfoMapper.updateDealpayUserInfo(dealpayUserInfo);
    }

    /**
     * 删除用户详情对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayUserInfoByIds(String ids) {
        return dealpayUserInfoMapper.deleteDealpayUserInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户详情信息
     *
     * @param id 用户详情ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayUserInfoById(Long id) {
        return dealpayUserInfoMapper.deleteDealpayUserInfoById(id);
    }
}
