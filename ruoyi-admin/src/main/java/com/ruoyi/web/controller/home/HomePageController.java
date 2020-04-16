package com.ruoyi.web.controller.home;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home/page")
public class HomePageController extends BaseController {

    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 显示通知公告
     * @return
     */
    @RequiresPermissions("home:page:view")
    @GetMapping()
    public String menu(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        Long roleId = sysUser.getRoleId();
        List<SysNotice> noticeList = noticeService.selectNoticeListByRoleId(roleId);
        mmap.put("notice",noticeList);
        return "home";
    }



}
