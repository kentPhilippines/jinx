package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import java.util.List;

/**
 * 用户详情Service接口
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public interface IAlipayUserInfoService 
{
    /**
     * 查询用户详情
     * 
     * @param id 用户详情ID
     * @return 用户详情
     */
    public AlipayUserInfo selectAlipayUserInfoById(Long id);

    /**
     * 查询用户详情列表
     * 
     * @param alipayUserInfo 用户详情
     * @return 用户详情集合
     */
    public List<AlipayUserInfo> selectAlipayUserInfoList(AlipayUserInfo alipayUserInfo);

    /**
     * 新增用户详情
     * 
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    public int insertAlipayUserInfo(AlipayUserInfo alipayUserInfo);

    /**
     * 修改用户详情
     * 
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    public int updateAlipayUserInfo(AlipayUserInfo alipayUserInfo);

    /**
     * 批量删除用户详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayUserInfoByIds(String ids);

    /**
     * 删除用户详情信息
     * 
     * @param id 用户详情ID
     * @return 结果
     */
    public int deleteAlipayUserInfoById(Long id);
}
