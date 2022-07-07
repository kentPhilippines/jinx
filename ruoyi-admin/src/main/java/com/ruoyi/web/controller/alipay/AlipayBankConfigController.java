package com.ruoyi.web.controller.alipay;

import com.ruoyi.alipay.domain.AlipayBankConfig;
import com.ruoyi.alipay.service.IAlipayBankConfigService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * bankconfigController
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
@Controller
@RequestMapping("/system/bankConfig")
public class AlipayBankConfigController extends BaseController
{
    private String prefix = "system/bankconfig";

    @Autowired
    private IAlipayBankConfigService alipayBankConfigService;

    @GetMapping()
    public String config()
    {
        return prefix + "/config";
    }

    /**
     * 查询bankconfig列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayBankConfig alipayBankConfig)
    {
        startPage();
        List<AlipayBankConfig> list = alipayBankConfigService.selectAlipayBankConfigList(alipayBankConfig);
        return getDataTable(list);
    }

    /**
     * 导出bankconfig列表
     */
    @Log(title = "bankconfig", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayBankConfig alipayBankConfig)
    {
        List<AlipayBankConfig> list = alipayBankConfigService.selectAlipayBankConfigList(alipayBankConfig);
        ExcelUtil<AlipayBankConfig> util = new ExcelUtil<AlipayBankConfig>(AlipayBankConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增bankconfig
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存bankconfig
     */
    @Log(title = "bankconfig", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayBankConfig alipayBankConfig)
    {
        return toAjax(alipayBankConfigService.insertAlipayBankConfig(alipayBankConfig));
    }

    /**
     * 修改bankconfig
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        AlipayBankConfig alipayBankConfig = alipayBankConfigService.selectAlipayBankConfigById(id);
        mmap.put("alipayBankConfig", alipayBankConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存bankconfig
     */
    @Log(title = "bankconfig", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayBankConfig alipayBankConfig)
    {
        return toAjax(alipayBankConfigService.updateAlipayBankConfig(alipayBankConfig));
    }

    /**
     * 删除bankconfig
     */
    @Log(title = "bankconfig", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(alipayBankConfigService.deleteAlipayBankConfigByIds(ids));
    }
}
