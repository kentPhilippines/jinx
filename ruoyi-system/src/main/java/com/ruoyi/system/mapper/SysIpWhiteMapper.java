package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysIpWhite;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ip白名单Mapper接口
 *
 * @author otc
 * @date 2020-05-13
 */
public interface SysIpWhiteMapper {
    /**
     * 查询ip白名单
     *
     * @param id ip白名单ID
     * @return ip白名单
     */
    public SysIpWhite selectSysIpWhiteById(Long id);

    /**
     * 查询ip白名单列表
     *
     * @param sysIpWhite ip白名单
     * @return ip白名单集合
     */
    public List<SysIpWhite> selectSysIpWhiteList(SysIpWhite sysIpWhite);

    /**
     * 新增ip白名单
     *
     * @param sysIpWhite ip白名单
     * @return 结果
     */
    public int insertSysIpWhite(SysIpWhite sysIpWhite);

    /**
     * 修改ip白名单
     *
     * @param sysIpWhite ip白名单
     * @return 结果
     */
    public int updateSysIpWhite(SysIpWhite sysIpWhite);

    /**
     * 删除ip白名单
     *
     * @param id ip白名单ID
     * @return 结果
     */
    public int deleteSysIpWhiteById(Long id);

    /**
     * 批量删除ip白名单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysIpWhiteByIds(String[] ids);

    @Select("select group_concat(ip_addree) ipAddree from sys_ip_white GROUP BY ip_addree")
    List<String> selectIndexInIpList();
}
