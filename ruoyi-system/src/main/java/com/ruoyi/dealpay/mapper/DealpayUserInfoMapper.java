package com.ruoyi.dealpay.mapper;

import com.ruoyi.dealpay.domain.DealpayUserInfoEntity;

import java.util.List;

/**
 * 用户详情Mapper接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface DealpayUserInfoMapper {
    /**
     * 查询用户详情
     *
     * @param id 用户详情ID
     * @return 用户详情
     */
    DealpayUserInfoEntity selectDealpayUserInfoById(Long id);

    /**
     * 查询用户详情列表
     *
     * @param dealpayUserInfo 用户详情
     * @return 用户详情集合
     */
    List<DealpayUserInfoEntity> selectDealpayUserInfoList(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 新增用户详情
     *
     * @param dealpayUserInfo 用户详情
     * @return 结果
     */
    int insertDealpayUserInfo(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 修改用户详情
     *
     * @param dealpayUserInfo 用户详情
     * @return 结果
     */
    int updateDealpayUserInfo(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 删除用户详情
     *
     * @param id 用户详情ID
     * @return 结果
     */
    int deleteDealpayUserInfoById(Long id);

    /**
     * 批量删除用户详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteDealpayUserInfoByIds(String[] ids);
}
