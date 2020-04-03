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
import com.ruoyi.dealpay.domain.DealpayWithdrawalEntity;
import com.ruoyi.dealpay.service.IDealpayWithdrawalEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 卡商出款记录表Controller
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/cardWithdrawal")
public class DealpayWithdrawalController extends BaseController {
    private String prefix = "dealpay/cardWithdrawal";

    @Autowired
    private IDealpayWithdrawalEntityService dealpayWithdrawalEntityService;

    @RequiresPermissions("dealpay:cardWithdrawal:view")
    @GetMapping()
    public String cardWithdrawal() {
        return prefix + "/cardWithdrawal";
    }

    /**
     * 查询卡商出款记录表列表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        startPage();
        List<DealpayWithdrawalEntity> list = dealpayWithdrawalEntityService.selectDealpayWithdrawalEntityList(dealpayWithdrawalEntity);
        return getDataTable(list);
    }

    /**
     * 导出卡商出款记录表列表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:export")
    @Log(title = "卡商出款记录表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        List<DealpayWithdrawalEntity> list = dealpayWithdrawalEntityService.selectDealpayWithdrawalEntityList(dealpayWithdrawalEntity);
        ExcelUtil<DealpayWithdrawalEntity> util = new ExcelUtil<DealpayWithdrawalEntity>(DealpayWithdrawalEntity.class);
        return util.exportExcel(list, "cardWithdrawal");
    }

    /**
     * 新增卡商出款记录表
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存卡商出款记录表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:add")
    @Log(title = "卡商出款记录表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        return toAjax(dealpayWithdrawalEntityService.insertDealpayWithdrawalEntity(dealpayWithdrawalEntity));
    }

    /**
     * 修改卡商出款记录表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayWithdrawalEntity dealpayWithdrawalEntity = dealpayWithdrawalEntityService.selectDealpayWithdrawalEntityById(id);
        mmap.put("dealpayWithdrawalEntity", dealpayWithdrawalEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存卡商出款记录表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:edit")
    @Log(title = "卡商出款记录表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        return toAjax(dealpayWithdrawalEntityService.updateDealpayWithdrawalEntity(dealpayWithdrawalEntity));
    }

    /**
     * 删除卡商出款记录表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:remove")
    @Log(title = "卡商出款记录表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayWithdrawalEntityService.deleteDealpayWithdrawalEntityByIds(ids));
    }
}
