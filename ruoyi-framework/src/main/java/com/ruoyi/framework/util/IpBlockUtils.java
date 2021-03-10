package com.ruoyi.framework.util;

import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.system.domain.SysIpWhite;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysIpWhiteService;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class IpBlockUtils {

    private static final Logger log = LoggerFactory.getLogger(IpBlockUtils.class);

    @Autowired
    private ISysIpWhiteService sysIpWhiteService;

    @Autowired
    private ISysUserService sysUserService;

    public boolean verifyIpBlock(HttpServletRequest request) {
        SysUser sysUser = sysUserService.selectUserByLoginName(request.getParameter("username"));
        if (null != sysUser && sysUser.isAdmin()) { //超级管理员不做限制
            return true;
        }
        String ip = IpUtils.getIpAddr(request);
        if ("unknown".equals(ip)) {
            return false;
        }
        SysIpWhite sysIpWhite = new SysIpWhite();
        sysIpWhite.setIpAddree(ip);
//        List<String> strList = sysIpWhiteService.selectIndexInIpList();
        List<SysIpWhite> list = sysIpWhiteService.selectSysIpWhiteList(sysIpWhite);
        if (list.size() == 0) {
            log.info("登陆IP未添加白名单：{}", ip);
            return false;
        }
        return true;
    }
}
