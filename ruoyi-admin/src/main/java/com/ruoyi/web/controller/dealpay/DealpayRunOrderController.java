package com.ruoyi.web.controller.dealpay;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.dealpay.domain.DealpayRunOrderEntity;
import com.ruoyi.dealpay.service.IDealpayRunOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流水订单记录Controller
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/runOrder")
public class DealpayRunOrderController extends BaseController {
    private String prefix = "dealpay/runOrder";

    @Autowired
    private IDealpayRunOrderService dealpayRunOrderService;

    @GetMapping()
    public String runOrder() {
        return prefix + "/runOrder";
    }

    /**
     * 查询流水订单记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayRunOrderEntity dealpayRunOrder) {
        startPage();
        List<DealpayRunOrderEntity> list = dealpayRunOrderService.selectDealpayRunOrderList(dealpayRunOrder);
        return getDataTable(list);
    }

    /**
     * 导出流水订单记录列表
     */
    @Log(title = "流水订单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayRunOrderEntity dealpayRunOrder) {
        List<DealpayRunOrderEntity> list = dealpayRunOrderService.selectDealpayRunOrderList(dealpayRunOrder);
        ExcelUtil<DealpayRunOrderEntity> util = new ExcelUtil<DealpayRunOrderEntity>(DealpayRunOrderEntity.class);
        return util.exportExcel(list, "runOrder");
    }

    /**
     * 新增流水订单记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存流水订单记录
     */
    @Log(title = "流水订单记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayRunOrderEntity dealpayRunOrder) {
        return toAjax(dealpayRunOrderService.insertDealpayRunOrder(dealpayRunOrder));
    }

    /**
     * 修改流水订单记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayRunOrderEntity dealpayRunOrder = dealpayRunOrderService.selectDealpayRunOrderById(id);
        mmap.put("dealpayRunOrder", dealpayRunOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存流水订单记录
     */
    @Log(title = "流水订单记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayRunOrderEntity dealpayRunOrder, @PathVariable("id") Long id) {
        dealpayRunOrder.setAcountR("平账");
        dealpayRunOrder.setId(id);
        return toAjax(dealpayRunOrderService.updateDealpayRunOrder(dealpayRunOrder));
    }

    /**
     * 删除流水订单记录
     */
    @Log(title = "流水订单记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayRunOrderService.deleteDealpayRunOrderByIds(ids));
    }
}
