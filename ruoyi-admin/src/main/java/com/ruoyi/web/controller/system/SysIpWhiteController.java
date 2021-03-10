package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysIpWhite;
import com.ruoyi.system.service.ISysIpWhiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ip白名单Controller
 *
 * @author otc
 * @date 2020-05-13
 */
@Controller
@RequestMapping("/system/ip")
public class SysIpWhiteController extends BaseController {
    private String prefix = "system/ip";

    @Autowired
    private ISysIpWhiteService sysIpWhiteService;

    @GetMapping()
    public String ip() {
        return prefix + "/ip";
    }

    /**
     * 查询ip白名单列表
     */
    @RequiresPermissions("system:ip:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysIpWhite sysIpWhite) {
        startPage();
        List<SysIpWhite> list = sysIpWhiteService.selectSysIpWhiteList(sysIpWhite);
        return getDataTable(list);
    }

    /**
     * 导出ip白名单列表
     */
    @RequiresPermissions("system:ip:export")
    @Log(title = "ip白名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysIpWhite sysIpWhite) {
        List<SysIpWhite> list = sysIpWhiteService.selectSysIpWhiteList(sysIpWhite);
        ExcelUtil<SysIpWhite> util = new ExcelUtil<SysIpWhite>(SysIpWhite.class);
        return util.exportExcel(list, "ip");
    }

    /**
     * 新增ip白名单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存ip白名单
     */
    @RequiresPermissions("system:ip:add")
    @Log(title = "ip白名单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysIpWhite sysIpWhite) {
        sysIpWhite.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(sysIpWhiteService.insertSysIpWhite(sysIpWhite));
    }

    /**
     * 删除ip白名单
     */
    @RequiresPermissions("system:ip:remove")
    @Log(title = "ip白名单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysIpWhiteService.deleteSysIpWhiteByIds(ids));
    }
}
