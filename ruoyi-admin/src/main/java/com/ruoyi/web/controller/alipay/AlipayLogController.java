package com.ruoyi.web.controller.alipay;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.alipay.domain.AlipayLog;
import com.ruoyi.alipay.service.IAlipayLogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 获取IP地址记录表Controller
 *
 * @author otc
 * @date 2020-02-27
 */
@Controller
@RequestMapping("/alipay/log")
public class AlipayLogController extends BaseController {
    private String prefix = "alipay/log";

    @Autowired
    private IAlipayLogService alipayLogService;

    @RequiresPermissions("alipay:log:view")
    @GetMapping()
    public String log() {
        return prefix + "/log";
    }

    /**
     * 查询获取IP地址记录表列表
     */
    @RequiresPermissions("alipay:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayLog alipayLog) {
        startPage();
        List<AlipayLog> list = alipayLogService.selectAlipayLogList(alipayLog);
        return getDataTable(list);
    }

    /**
     * 导出获取IP地址记录表列表
     */
    @RequiresPermissions("alipay:log:export")
    @Log(title = "获取IP地址记录表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayLog alipayLog) {
        List<AlipayLog> list = alipayLogService.selectAlipayLogList(alipayLog);
        ExcelUtil<AlipayLog> util = new ExcelUtil<AlipayLog>(AlipayLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 新增获取IP地址记录表
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存获取IP地址记录表
     */
    @RequiresPermissions("alipay:log:add")
    @Log(title = "获取IP地址记录表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayLog alipayLog) {
        return toAjax(alipayLogService.insertAlipayLog(alipayLog));
    }

    /**
     * 修改获取IP地址记录表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayLog alipayLog = alipayLogService.selectAlipayLogById(id);
        mmap.put("alipayLog", alipayLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存获取IP地址记录表
     */
    @RequiresPermissions("alipay:log:edit")
    @Log(title = "获取IP地址记录表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayLog alipayLog) {
        return toAjax(alipayLogService.updateAlipayLog(alipayLog));
    }

    /**
     * 删除获取IP地址记录表
     */
    @RequiresPermissions("alipay:log:remove")
    @Log(title = "获取IP地址记录表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayLogService.deleteAlipayLogByIds(ids));
    }
}
