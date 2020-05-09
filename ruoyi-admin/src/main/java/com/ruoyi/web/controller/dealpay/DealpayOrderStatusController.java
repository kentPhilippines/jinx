package com.ruoyi.web.controller.dealpay;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.dealpay.domain.DealpayOrderStatusEntity;
import com.ruoyi.dealpay.service.IDealpayOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 双方确认订单状态Controller
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/confirm")
public class DealpayOrderStatusController extends BaseController {
    private String prefix = "dealpay/confirm";

    @Autowired
    private IDealpayOrderStatusService dealpayOrderStatusService;

    @GetMapping()
    public String confirm() {
        return prefix + "/confirm";
    }

    /**
     * 查询双方确认订单状态列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayOrderStatusEntity dealpayOrderStatus) {
        startPage();
        List<DealpayOrderStatusEntity> list = dealpayOrderStatusService.selectDealpayOrderStatusList(dealpayOrderStatus);
        return getDataTable(list);
    }

    /**
     * 导出双方确认订单状态列表
     */
    @Log(title = "双方确认订单状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayOrderStatusEntity dealpayOrderStatus) {
        List<DealpayOrderStatusEntity> list = dealpayOrderStatusService.selectDealpayOrderStatusList(dealpayOrderStatus);
        ExcelUtil<DealpayOrderStatusEntity> util = new ExcelUtil<DealpayOrderStatusEntity>(DealpayOrderStatusEntity.class);
        return util.exportExcel(list, "confirm");
    }

    /**
     * 新增双方确认订单状态
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存双方确认订单状态
     */
    @Log(title = "双方确认订单状态", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayOrderStatusEntity dealpayOrderStatus) {
        return toAjax(dealpayOrderStatusService.insertDealpayOrderStatus(dealpayOrderStatus));
    }

    /**
     * 修改双方确认订单状态
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayOrderStatusEntity dealpayOrderStatus = dealpayOrderStatusService.selectDealpayOrderStatusById(id);
        mmap.put("dealpayOrderStatus", dealpayOrderStatus);
        return prefix + "/edit";
    }

    /**
     * 修改保存双方确认订单状态
     */
    @Log(title = "双方确认订单状态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayOrderStatusEntity dealpayOrderStatus) {
        return toAjax(dealpayOrderStatusService.updateDealpayOrderStatus(dealpayOrderStatus));
    }

    /**
     * 删除双方确认订单状态
     */
    @Log(title = "双方确认订单状态", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayOrderStatusService.deleteDealpayOrderStatusByIds(ids));
    }
}
