package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.system.domain.SysIpWhite;
import com.ruoyi.system.mapper.SysIpWhiteMapper;
import com.ruoyi.system.service.ISysIpWhiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ip白名单Service业务层处理
 *
 * @author otc
 * @date 2020-05-13
 */
@Service
public class SysIpWhiteServiceImpl implements ISysIpWhiteService {
    @Autowired
    private SysIpWhiteMapper sysIpWhiteMapper;

    /**
     * 查询ip白名单
     *
     * @param id ip白名单ID
     * @return ip白名单
     */
    @Override
    public SysIpWhite selectSysIpWhiteById(Long id) {
        return sysIpWhiteMapper.selectSysIpWhiteById(id);
    }

    /**
     * 查询ip白名单列表
     *
     * @param sysIpWhite ip白名单
     * @return ip白名单
     */
    @Override
    public List<SysIpWhite> selectSysIpWhiteList(SysIpWhite sysIpWhite) {
        return sysIpWhiteMapper.selectSysIpWhiteList(sysIpWhite);
    }

    /**
     * 新增ip白名单
     *
     * @param sysIpWhite ip白名单
     * @return 结果
     */
    @Override
    public int insertSysIpWhite(SysIpWhite sysIpWhite) {
        sysIpWhite.setCreateTime(DateUtils.getNowDate());
        if (!IpUtils.isIpValid(sysIpWhite.getIpAddree())) {
            throw new BusinessException("请输入合法的IP地址");
        }
        if (sysIpWhiteMapper.selectSysIpWhiteList(sysIpWhite).size() > 0) {
            throw new BusinessException("不允许重复添加");
        }
        return sysIpWhiteMapper.insertSysIpWhite(sysIpWhite);
    }

    /**
     * 修改ip白名单
     *
     * @param sysIpWhite ip白名单
     * @return 结果
     */
    @Override
    public int updateSysIpWhite(SysIpWhite sysIpWhite) {
        sysIpWhite.setUpdateTime(DateUtils.getNowDate());
        return sysIpWhiteMapper.updateSysIpWhite(sysIpWhite);
    }

    /**
     * 删除ip白名单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysIpWhiteByIds(String ids) {
        return sysIpWhiteMapper.deleteSysIpWhiteByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除ip白名单信息
     *
     * @param id ip白名单ID
     * @return 结果
     */
    @Override
    public int deleteSysIpWhiteById(Long id) {
        return sysIpWhiteMapper.deleteSysIpWhiteById(id);
    }

    @Override
    public List<String> selectIndexInIpList() {
        return sysIpWhiteMapper.selectIndexInIpList();
    }
}
