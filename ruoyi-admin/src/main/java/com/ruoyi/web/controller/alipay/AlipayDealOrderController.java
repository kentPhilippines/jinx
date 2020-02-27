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
import com.ruoyi.alipay.domain.AlipayDealOrder;
import com.ruoyi.alipay.service.IAlipayDealOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交易订单Controller
 *
 * @author otc
 * @date 2020-02-27
 */
@Controller
@RequestMapping("/system/order")
public class AlipayDealOrderController extends BaseController {
    private String prefix = "system/order";

    @Autowired
    private IAlipayDealOrderService alipayDealOrderService;

    @RequiresPermissions("system:order:view")
    @GetMapping()
    public String order() {
        return prefix + "/order";
    }

    /**
     * 查询交易订单列表
     */
    @RequiresPermissions("system:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayDealOrder alipayDealOrder) {
        startPage();
        List<AlipayDealOrder> list = alipayDealOrderService.selectAlipayDealOrderList(alipayDealOrder);
        return getDataTable(list);
    }

    /**
     * 导出交易订单列表
     */
    @RequiresPermissions("system:order:export")
    @Log(title = "交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayDealOrder alipayDealOrder) {
        List<AlipayDealOrder> list = alipayDealOrderService.selectAlipayDealOrderList(alipayDealOrder);
        ExcelUtil<AlipayDealOrder> util = new ExcelUtil<AlipayDealOrder>(AlipayDealOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增交易订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存交易订单
     */
    @RequiresPermissions("system:order:add")
    @Log(title = "交易订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayDealOrder alipayDealOrder) {
        return toAjax(alipayDealOrderService.insertAlipayDealOrder(alipayDealOrder));
    }

    /**
     * 修改交易订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrder alipayDealOrder = alipayDealOrderService.selectAlipayDealOrderById(id);
        mmap.put("alipayDealOrder", alipayDealOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存交易订单
     */
    @RequiresPermissions("system:order:edit")
    @Log(title = "交易订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayDealOrder alipayDealOrder) {
        return toAjax(alipayDealOrderService.updateAlipayDealOrder(alipayDealOrder));
    }

    /**
     * 删除交易订单
     */
    @RequiresPermissions("system:order:remove")
    @Log(title = "交易订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayDealOrderService.deleteAlipayDealOrderByIds(ids));
    }
}
