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
import com.ruoyi.alipay.domain.AlipayCorrelation;
import com.ruoyi.alipay.service.IAlipayCorrelationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 代理关系表Controller
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/correlation")
public class AlipayCorrelationController extends BaseController
{
    private String prefix = "alipay/correlation";

    @Autowired
    private IAlipayCorrelationService alipayCorrelationService;

    @RequiresPermissions("alipay:correlation:view")
    @GetMapping()
    public String correlation()
    {
        return prefix + "/correlation";
    }

    /**
     * 查询代理关系表列表
     */
    @RequiresPermissions("alipay:correlation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayCorrelation alipayCorrelation)
    {
        startPage();
        List<AlipayCorrelation> list = alipayCorrelationService.selectAlipayCorrelationList(alipayCorrelation);
        return getDataTable(list);
    }

    /**
     * 导出代理关系表列表
     */
    @RequiresPermissions("alipay:correlation:export")
    @Log(title = "代理关系表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayCorrelation alipayCorrelation)
    {
        List<AlipayCorrelation> list = alipayCorrelationService.selectAlipayCorrelationList(alipayCorrelation);
        ExcelUtil<AlipayCorrelation> util = new ExcelUtil<AlipayCorrelation>(AlipayCorrelation.class);
        return util.exportExcel(list, "correlation");
    }

    /**
     * 新增代理关系表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存代理关系表
     */
    @RequiresPermissions("alipay:correlation:add")
    @Log(title = "代理关系表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayCorrelation alipayCorrelation)
    {
        return toAjax(alipayCorrelationService.insertAlipayCorrelation(alipayCorrelation));
    }

    /**
     * 修改代理关系表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AlipayCorrelation alipayCorrelation = alipayCorrelationService.selectAlipayCorrelationById(id);
        mmap.put("alipayCorrelation", alipayCorrelation);
        return prefix + "/edit";
    }

    /**
     * 修改保存代理关系表
     */
    @RequiresPermissions("alipay:correlation:edit")
    @Log(title = "代理关系表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayCorrelation alipayCorrelation)
    {
        return toAjax(alipayCorrelationService.updateAlipayCorrelation(alipayCorrelation));
    }

    /**
     * 删除代理关系表
     */
    @RequiresPermissions("alipay:correlation:remove")
    @Log(title = "代理关系表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(alipayCorrelationService.deleteAlipayCorrelationByIds(ids));
    }
}
