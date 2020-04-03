package com.ruoyi.web.controller.dealpay;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.dealpay.domain.DealpayUserFundEntity;
import com.ruoyi.dealpay.service.IDealpayUserFundService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户资金账户Controller
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/cardFund")
public class DealpayUserFundController extends BaseController {
    private String prefix = "dealpay/cardFund";

    @Autowired
    private IDealpayUserFundService dealpayUserFundService;

    @RequiresPermissions("dealpay:cardFund:view")
    @GetMapping()
    public String cardFund() {
        return prefix + "/cardFund";
    }

    /**
     * 查询用户资金账户列表
     */
    @RequiresPermissions("dealpay:cardFund:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayUserFundEntity dealpayUserFund) {
        startPage();
        List<DealpayUserFundEntity> list = dealpayUserFundService.selectDealpayUserFundList(dealpayUserFund);
        return getDataTable(list);
    }

    /**
     * 导出用户资金账户列表
     */
    @RequiresPermissions("dealpay:cardFund:export")
    @Log(title = "用户资金账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayUserFundEntity dealpayUserFund) {
        List<DealpayUserFundEntity> list = dealpayUserFundService.selectDealpayUserFundList(dealpayUserFund);
        ExcelUtil<DealpayUserFundEntity> util = new ExcelUtil<DealpayUserFundEntity>(DealpayUserFundEntity.class);
        return util.exportExcel(list, "cardFund");
    }

    /**
     * 新增用户资金账户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户资金账户
     */
    @RequiresPermissions("dealpay:cardFund:add")
    @Log(title = "用户资金账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayUserFundEntity dealpayUserFund) {
        return toAjax(dealpayUserFundService.insertDealpayUserFund(dealpayUserFund));
    }

    /**
     * 修改用户资金账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayUserFundEntity dealpayUserFund = dealpayUserFundService.selectDealpayUserFundById(id);
        mmap.put("dealpayUserFund", dealpayUserFund);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户资金账户
     */
    @RequiresPermissions("dealpay:cardFund:edit")
    @Log(title = "用户资金账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayUserFundEntity dealpayUserFund) {
        return toAjax(dealpayUserFundService.updateDealpayUserFund(dealpayUserFund));
    }

    /**
     * 删除用户资金账户
     */
    @RequiresPermissions("dealpay:cardFund:remove")
    @Log(title = "用户资金账户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayUserFundService.deleteDealpayUserFundByIds(ids));
    }
}
