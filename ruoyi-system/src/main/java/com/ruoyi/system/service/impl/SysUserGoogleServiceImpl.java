package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserGoogleMapper;
import com.ruoyi.system.domain.SysUserGoogle;
import com.ruoyi.system.service.ISysUserGoogleService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 谷歌验证器Service业务层处理
 *
 * @author kiwi
 * @date 2020-04-01
 */
@Service
public class SysUserGoogleServiceImpl implements ISysUserGoogleService {
    @Autowired
    private SysUserGoogleMapper sysUserGoogleMapper;

    /**
     * 查询谷歌验证器
     *
     * @param id 谷歌验证器ID
     * @return 谷歌验证器
     */
    @Override
    public SysUserGoogle selectSysUserGoogleById(Long id) {
        return sysUserGoogleMapper.selectSysUserGoogleById(id);
    }

    /**
     * 查询谷歌验证器列表
     *
     * @param sysUserGoogle 谷歌验证器
     * @return 谷歌验证器
     */
    @Override
    public List<SysUserGoogle> selectSysUserGoogleList(SysUserGoogle sysUserGoogle) {
        return sysUserGoogleMapper.selectSysUserGoogleList(sysUserGoogle);
    }

    /**
     * 新增谷歌验证器
     *
     * @param sysUserGoogle 谷歌验证器
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysUserGoogle(SysUserGoogle sysUserGoogle) {
        sysUserGoogle.setCreateTime(DateUtils.getNowDate());
        return sysUserGoogleMapper.insertSysUserGoogle(sysUserGoogle);
    }

    /**
     * 修改谷歌验证器
     *
     * @param sysUserGoogle 谷歌验证器
     * @return 结果
     */
    @Override
    public int updateSysUserGoogle(SysUserGoogle sysUserGoogle) {
        return sysUserGoogleMapper.updateSysUserGoogle(sysUserGoogle);
    }

    /**
     * 删除谷歌验证器对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysUserGoogleByIds(String ids) {
        return sysUserGoogleMapper.deleteSysUserGoogleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除谷歌验证器信息
     *
     * @param id 谷歌验证器ID
     * @return 结果
     */
    @Override
    public int deleteSysUserGoogleById(Long id) {
        return sysUserGoogleMapper.deleteSysUserGoogleById(id);
    }

    @Override
    public SysUserGoogle selectSysUserGoogleByUsername(String username) {
        return sysUserGoogleMapper.selectSysUserGoogleByUsername(username);
    }

    @Override
    public int deleteSysUserGoogleByLoginName(String loginName) {
        return sysUserGoogleMapper.deleteSysUserGoogleByLoginName(loginName);
    }
}
