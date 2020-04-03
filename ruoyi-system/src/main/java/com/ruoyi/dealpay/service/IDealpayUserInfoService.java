package com.ruoyi.dealpay.service;

import com.ruoyi.dealpay.domain.DealpayUserInfoEntity;

import java.util.List;

/**
 * 用户详情Service接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface IDealpayUserInfoService {
    /**
     * 查询用户详情
     *
     * @param id 用户详情ID
     * @return 用户详情
     */
    public DealpayUserInfoEntity selectDealpayUserInfoById(Long id);

    /**
     * 查询用户详情列表
     *
     * @param dealpayUserInfo 用户详情
     * @return 用户详情集合
     */
    public List<DealpayUserInfoEntity> selectDealpayUserInfoList(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 新增用户详情
     *
     * @param dealpayUserInfo 用户详情
     * @return 结果
     */
    public int insertDealpayUserInfo(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 修改用户详情
     *
     * @param dealpayUserInfo 用户详情
     * @return 结果
     */
    public int updateDealpayUserInfo(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 批量删除用户详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayUserInfoByIds(String ids);

    /**
     * 删除用户详情信息
     *
     * @param id 用户详情ID
     * @return 结果
     */
    public int deleteDealpayUserInfoById(Long id);
}
