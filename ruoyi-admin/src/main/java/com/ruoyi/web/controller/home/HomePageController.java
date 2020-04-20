package com.ruoyi.web.controller.home;

import com.google.common.collect.Lists;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home/page")
public class HomePageController extends BaseController {

    private String prefix = "system/notice";
    @Autowired
    private ISysNoticeService noticeService;


    /**
     * 显示通知公告
     *
     * @return
     */
    @GetMapping("/rolling")
    public String menu(ModelMap mmap) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (sysUser.getRoles().size() == 0) {
            mmap.put("noticeList", Lists.newArrayList());
        } else {
            Long roleId = sysUser.getRoles().get(0).getRoleId();
            List<SysNotice> noticeList = noticeService.selectNoticeListByRoleId(roleId);
            mmap.put("noticeList", noticeList);
        }
        return "home";
    }

    @GetMapping("/more")
    public String more() {
        return prefix + "/more";
    }

    @PostMapping("/more/notice")
    @ResponseBody
    public TableDataInfo moreNotice(SysNotice sysNotice) {
        SysUser sysUser = ShiroUtils.getSysUser();
        sysNotice.setRemark(sysUser.getRoles().get(0).getRoleId().toString());
        startPage();
        List<SysNotice> list = noticeService.selectNoticeListMoreByRoleId(sysNotice);
        return getDataTable(list);
    }

    @GetMapping("/more/notice/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        SysNotice sysNotice = noticeService.selectNoticeById(id);
        mmap.put("sysNotice", sysNotice);
        return prefix + "/detail";
    }


}
