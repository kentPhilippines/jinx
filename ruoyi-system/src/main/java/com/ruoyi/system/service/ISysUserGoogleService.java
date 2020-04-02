package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysUserGoogle;
import java.util.List;

/**
 * 谷歌验证器Service接口
 * 
 * @author kiwi
 * @date 2020-04-01
 */
public interface ISysUserGoogleService 
{
    /**
     * 查询谷歌验证器
     * 
     * @param id 谷歌验证器ID
     * @return 谷歌验证器
     */
    public SysUserGoogle selectSysUserGoogleById(Long id);

    /**
     * 查询谷歌验证器列表
     * 
     * @param sysUserGoogle 谷歌验证器
     * @return 谷歌验证器集合
     */
    public List<SysUserGoogle> selectSysUserGoogleList(SysUserGoogle sysUserGoogle);

    /**
     * 新增谷歌验证器
     * 
     * @param sysUserGoogle 谷歌验证器
     * @return 结果
     */
    public int insertSysUserGoogle(SysUserGoogle sysUserGoogle);

    /**
     * 修改谷歌验证器
     * 
     * @param sysUserGoogle 谷歌验证器
     * @return 结果
     */
    public int updateSysUserGoogle(SysUserGoogle sysUserGoogle);

    /**
     * 批量删除谷歌验证器
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserGoogleByIds(String ids);

    /**
     * 删除谷歌验证器信息
     * 
     * @param id 谷歌验证器ID
     * @return 结果
     */
    public int deleteSysUserGoogleById(Long id);

    /**
     * 根据用户名查询信息
     * @param username 登陆名
     * @return 结果
     */
    SysUserGoogle selectSysUserGoogleByUsername(String username);

    /**
     * 根据登陆名更新
     * @param loginName
     * @return
     */
    int deleteSysUserGoogleByLoginName(String loginName);
}
