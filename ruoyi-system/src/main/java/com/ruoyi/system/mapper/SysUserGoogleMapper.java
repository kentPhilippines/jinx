package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysUserGoogle;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 谷歌验证器Mapper接口
 *
 * @author kiwi
 * @date 2020-04-01
 */
public interface SysUserGoogleMapper {
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
     * 删除谷歌验证器
     *
     * @param id 谷歌验证器ID
     * @return 结果
     */
    public int deleteSysUserGoogleById(Long id);

    /**
     * 批量删除谷歌验证器
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserGoogleByIds(String[] ids);

    @Select("select id, login_name loginName, google_url googleUrl, secret_key secretKey, host, expire_time expireTime," +
            " create_by createBy, create_time createTime, remark from sys_user_google where login_name = #{username} ")
    SysUserGoogle selectSysUserGoogleByUsername(@Param("username") String username);

    @Delete("delete from sys_user_google where login_name = #{loginName}")
    int deleteSysUserGoogleByLoginName(@Param("loginName") String loginName);
}
