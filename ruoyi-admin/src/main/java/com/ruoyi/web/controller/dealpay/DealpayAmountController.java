package com.ruoyi.web.controller.dealpay;

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
import com.ruoyi.dealpay.domain.DealpayAmountEntity;
import com.ruoyi.dealpay.service.IDealpayAmountService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 手动加扣款记录Controller
 *
 * @author ruoyi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/dealDeduct")
public class DealpayAmountController extends BaseController {
    private String prefix = "dealpay/dealDeduct";

    @Autowired
    private IDealpayAmountService dealpayAmountService;

    @RequiresPermissions("dealpay:dealDeduct:view")
    @GetMapping()
    public String dealDeduct() {
        return prefix + "/dealDeduct";
    }

    /**
     * 查询手动加扣款记录列表
     */
    @RequiresPermissions("dealpay:dealDeduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayAmountEntity dealpayAmount) {
        startPage();
        List<DealpayAmountEntity> list = dealpayAmountService.selectDealpayAmountList(dealpayAmount);
        return getDataTable(list);
    }

    /**
     * 导出手动加扣款记录列表
     */
    @RequiresPermissions("dealpay:dealDeduct:export")
    @Log(title = "手动加扣款记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayAmountEntity dealpayAmount) {
        List<DealpayAmountEntity> list = dealpayAmountService.selectDealpayAmountList(dealpayAmount);
        ExcelUtil<DealpayAmountEntity> util = new ExcelUtil<DealpayAmountEntity>(DealpayAmountEntity.class);
        return util.exportExcel(list, "dealDeduct");
    }

    /**
     * 新增手动加扣款记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存手动加扣款记录
     */
    @RequiresPermissions("dealpay:dealDeduct:add")
    @Log(title = "手动加扣款记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayAmountEntity dealpayAmount) {
        return toAjax(dealpayAmountService.insertDealpayAmount(dealpayAmount));
    }

    /**
     * 修改手动加扣款记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayAmountEntity dealpayAmount = dealpayAmountService.selectDealpayAmountById(id);
        mmap.put("dealpayAmount", dealpayAmount);
        return prefix + "/edit";
    }

    /**
     * 修改保存手动加扣款记录
     */
    @RequiresPermissions("dealpay:dealDeduct:edit")
    @Log(title = "手动加扣款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayAmountEntity dealpayAmount) {
        return toAjax(dealpayAmountService.updateDealpayAmount(dealpayAmount));
    }

    /**
     * 删除手动加扣款记录
     */
    @RequiresPermissions("dealpay:dealDeduct:remove")
    @Log(title = "手动加扣款记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayAmountService.deleteDealpayAmountByIds(ids));
    }
}
