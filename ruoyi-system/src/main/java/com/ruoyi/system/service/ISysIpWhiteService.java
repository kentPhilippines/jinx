package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysIpWhite;

import java.util.List;

/**
 * ip白名单Service接口
 *
 * @author otc
 * @date 2020-05-13
 */
public interface ISysIpWhiteService {
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
     * 批量删除ip白名单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysIpWhiteByIds(String ids);

    /**
     * 删除ip白名单信息
     *
     * @param id ip白名单ID
     * @return 结果
     */
    public int deleteSysIpWhiteById(Long id);


    List<String> selectIndexInIpList();
}
