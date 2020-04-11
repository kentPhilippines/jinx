package com.ruoyi.web.controller.dealpay;

import java.util.List;

import com.ruoyi.dealpay.domain.DealpayDealOrderEntity;
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
import com.ruoyi.dealpay.domain.DealpayRechargeEntity;
import com.ruoyi.dealpay.service.IDealpayRechargeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充值记录Controller
 *
 * @author k
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/recharge")
public class DealpayRechargeController extends BaseController {
    private String prefix = "dealpay/recharge";
    private String finance_prefix = "dealpay/finance";

    @Autowired
    private IDealpayRechargeService dealpayRechargeService;

    @RequiresPermissions("dealpay:recharge:view")
    @GetMapping()
    public String recharge() {
        return prefix + "/recharge";
    }

    /**
     * 查询充值记录列表
     */
    @RequiresPermissions("dealpay:recharge:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayRechargeEntity dealpayRecharge) {
        startPage();
        List<DealpayRechargeEntity> list = dealpayRechargeService.selectDealpayRechargeList(dealpayRecharge);
        return getDataTable(list);
    }

    /**
     * 导出充值记录列表
     */
    @RequiresPermissions("dealpay:recharge:export")
    @Log(title = "充值记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayRechargeEntity dealpayRecharge) {
        List<DealpayRechargeEntity> list = dealpayRechargeService.selectDealpayRechargeList(dealpayRecharge);
        ExcelUtil<DealpayRechargeEntity> util = new ExcelUtil<DealpayRechargeEntity>(DealpayRechargeEntity.class);
        return util.exportExcel(list, "recharge");
    }

    /**
     * 新增充值记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存充值记录
     */
    @RequiresPermissions("dealpay:recharge:add")
    @Log(title = "充值记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayRechargeEntity dealpayRecharge) {
        return toAjax(dealpayRechargeService.insertDealpayRecharge(dealpayRecharge));
    }

    /**
     * 修改充值记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayRechargeEntity dealpayRecharge = dealpayRechargeService.selectDealpayRechargeById(id);
        mmap.put("dealpayRecharge", dealpayRecharge);
        return prefix + "/edit";
    }

    /**
     * 修改保存充值记录
     */
    @RequiresPermissions("dealpay:recharge:edit")
    @Log(title = "充值记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayRechargeEntity dealpayRecharge) {
        return toAjax(dealpayRechargeService.updateDealpayRecharge(dealpayRecharge));
    }

    /**
     * 删除充值记录
     */
    @RequiresPermissions("dealpay:recharge:remove")
    @Log(title = "充值记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayRechargeService.deleteDealpayRechargeByIds(ids));
    }


    /*财务管理-充值管理逻辑处理*/

    @RequiresPermissions("finance:deposit:view")
    @GetMapping("/deposit/view")
    public String depositView() {
        return finance_prefix + "/deposit";
    }

    /**
     * <p>充值管理</p>
     */
    @RequiresPermissions("finance:deposit:manage")
    @PostMapping("/finance/manage/deposit")
    @Log(title = "代付管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public TableDataInfo deposit(DealpayRechargeEntity dealpayRechargeEntity) {
        startPage();
        dealpayRechargeEntity.setRechargeType(2);
        List<DealpayRechargeEntity> list = dealpayRechargeService.selectDealpayRechargeList(dealpayRechargeEntity);
        return getDataTable(list);
    }

    /**
     * 修改充值记录
     */
    @GetMapping("/finance/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayRechargeEntity dealpayRecharge = dealpayRechargeService.selectDealpayRechargeById(id);
        mmap.put("dealpayRecharge", dealpayRecharge);
        return finance_prefix + "/rDetail";
    }
}
