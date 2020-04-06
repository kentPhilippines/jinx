package com.ruoyi.web.controller.dealpay;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayBankListEntity;
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
import com.ruoyi.dealpay.domain.DealpayBankListEntity;
import com.ruoyi.dealpay.service.IDealpayBankListService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 银行卡列Controller
 *
 * @author k
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/bankCard")
public class DealpayBankListController extends BaseController {
    private String prefix = "dealpay/bankCard";

    @Autowired
    private IDealpayBankListService dealpayBankListService;

    @RequiresPermissions("dealpay:bankCard:view")
    @GetMapping()
    public String bankCard() {
        return prefix + "/bankCard";
    }

    /**
     * 查询银行卡列列表
     */
    @RequiresPermissions("dealpay:bankCard:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayBankListEntity dealpayBankList) {
        startPage();
        List<DealpayBankListEntity> list = dealpayBankListService.selectDealpayBankListList(dealpayBankList);
        return getDataTable(list);
    }

    /**
     * 导出银行卡列列表
     */
    @RequiresPermissions("dealpay:bankCard:export")
    @Log(title = "银行卡列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayBankListEntity dealpayBankList) {
        List<DealpayBankListEntity> list = dealpayBankListService.selectDealpayBankListList(dealpayBankList);
        ExcelUtil<DealpayBankListEntity> util = new ExcelUtil<DealpayBankListEntity>(DealpayBankListEntity.class);
        return util.exportExcel(list, "bankCard");
    }

    /**
     * 新增银行卡列
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存银行卡列
     */
    @RequiresPermissions("dealpay:bankCard:add")
    @Log(title = "银行卡列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayBankListEntity dealpayBankList) {
        return toAjax(dealpayBankListService.insertDealpayBankList(dealpayBankList));
    }

    /**
     * 修改银行卡列
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayBankListEntity dealpayBankList = dealpayBankListService.selectDealpayBankListById(id);
        mmap.put("dealpayBankList", dealpayBankList);
        return prefix + "/edit";
    }

    /**
     * 修改保存银行卡列
     */
    @RequiresPermissions("dealpay:bankCard:edit")
    @Log(title = "银行卡列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayBankListEntity dealpayBankList) {
        return toAjax(dealpayBankListService.updateDealpayBankList(dealpayBankList));
    }

    /**
     * 删除银行卡列
     */
    @RequiresPermissions("dealpay:bankCard:remove")
    @Log(title = "银行卡列", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayBankListService.deleteDealpayBankListByIds(ids));
    }

    /**
     * 码商状态修改（调用api）
     */
    @Log(title = "码商查询", businessType = BusinessType.UPDATE)
    @RequiresPermissions("dealpay:bankCard:status")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(DealpayBankListEntity dealpayBankListEntity) {
        return toAjax(dealpayBankListService.updateDealpayBankCardStatusById(dealpayBankListEntity));
    }
}
