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
import com.ruoyi.alipay.domain.AlipayUserFund;
import com.ruoyi.alipay.service.IAlipayUserFundService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户资金账户Controller
 *
 * @author ruoyi
 * @date 2020-02-27
 */
@Controller
@RequestMapping("/alipay/fund")
public class AlipayUserFundController extends BaseController {
    private String prefix = "alipay/fund";

    @Autowired
    private IAlipayUserFundService alipayUserFundService;

    @RequiresPermissions("alipay:fund:view")
    @GetMapping()
    public String fund() {
        return prefix + "/fund";
    }

    /**
     * 查询用户资金账户列表
     */
    @RequiresPermissions("alipay:fund:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserFund alipayUserFund) {
        startPage();
        List<AlipayUserFund> list = alipayUserFundService.selectAlipayUserFundList(alipayUserFund);
        return getDataTable(list);
    }

    /**
     * 导出用户资金账户列表
     */
    @RequiresPermissions("alipay:fund:export")
    @Log(title = "用户资金账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayUserFund alipayUserFund) {
        List<AlipayUserFund> list = alipayUserFundService.selectAlipayUserFundList(alipayUserFund);
        ExcelUtil<AlipayUserFund> util = new ExcelUtil<AlipayUserFund>(AlipayUserFund.class);
        return util.exportExcel(list, "fund");
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
    @RequiresPermissions("alipay:fund:add")
    @Log(title = "用户资金账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserFund alipayUserFund) {
        return toAjax(alipayUserFundService.insertAlipayUserFund(alipayUserFund));
    }

    /**
     * 修改用户资金账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserFund alipayUserFund = alipayUserFundService.selectAlipayUserFundById(id);
        mmap.put("alipayUserFund", alipayUserFund);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户资金账户
     */
    @RequiresPermissions("alipay:fund:edit")
    @Log(title = "用户资金账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserFund alipayUserFund) {
        return toAjax(alipayUserFundService.updateAlipayUserFund(alipayUserFund));
    }

    /**
     * 删除用户资金账户
     */
    @RequiresPermissions("alipay:fund:remove")
    @Log(title = "用户资金账户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayUserFundService.deleteAlipayUserFundByIds(ids));
    }
}
