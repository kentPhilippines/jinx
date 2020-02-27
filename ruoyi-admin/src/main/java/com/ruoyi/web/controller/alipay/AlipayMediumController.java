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
import com.ruoyi.alipay.domain.AlipayMedium;
import com.ruoyi.alipay.service.IAlipayMediumService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收款媒介列Controller
 *
 * @author otc
 * @date 2020-02-27
 */
@Controller
@RequestMapping("/system/medium")
public class AlipayMediumController extends BaseController {
    private String prefix = "system/medium";

    @Autowired
    private IAlipayMediumService alipayMediumService;

    @RequiresPermissions("system:medium:view")
    @GetMapping()
    public String medium() {
        return prefix + "/medium";
    }

    /**
     * 查询收款媒介列列表
     */
    @RequiresPermissions("system:medium:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayMedium alipayMedium) {
        startPage();
        List<AlipayMedium> list = alipayMediumService.selectAlipayMediumList(alipayMedium);
        return getDataTable(list);
    }

    /**
     * 导出收款媒介列列表
     */
    @RequiresPermissions("system:medium:export")
    @Log(title = "收款媒介列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayMedium alipayMedium) {
        List<AlipayMedium> list = alipayMediumService.selectAlipayMediumList(alipayMedium);
        ExcelUtil<AlipayMedium> util = new ExcelUtil<AlipayMedium>(AlipayMedium.class);
        return util.exportExcel(list, "medium");
    }

    /**
     * 新增收款媒介列
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存收款媒介列
     */
    @RequiresPermissions("system:medium:add")
    @Log(title = "收款媒介列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayMedium alipayMedium) {
        return toAjax(alipayMediumService.insertAlipayMedium(alipayMedium));
    }

    /**
     * 修改收款媒介列
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayMedium alipayMedium = alipayMediumService.selectAlipayMediumById(id);
        mmap.put("alipayMedium", alipayMedium);
        return prefix + "/edit";
    }

    /**
     * 修改保存收款媒介列
     */
    @RequiresPermissions("system:medium:edit")
    @Log(title = "收款媒介列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayMedium alipayMedium) {
        return toAjax(alipayMediumService.updateAlipayMedium(alipayMedium));
    }

    /**
     * 删除收款媒介列
     */
    @RequiresPermissions("system:medium:remove")
    @Log(title = "收款媒介列", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayMediumService.deleteAlipayMediumByIds(ids));
    }
}
