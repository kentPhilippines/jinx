package com.ruoyi.web.controller.dealpay;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
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
import com.ruoyi.dealpay.domain.DealpayDealOrderEntity;
import com.ruoyi.dealpay.service.IDealpayDealOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交易订单Controller
 *
 * @author k
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/dealOrder")
public class DealpayDealOrderController extends BaseController {
    private String prefix = "dealpay/dealOrder";

    @Autowired
    private IDealpayDealOrderService dealpayDealOrderService;

    @RequiresPermissions("dealpay:dealOrder:view")
    @GetMapping()
    public String dealOrder() {
        return prefix + "/dealOrder";
    }

    /**
     * 查询交易订单列表
     */
    @RequiresPermissions("dealpay:dealOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayDealOrderEntity dealpayDealOrder) {
        startPage();
        List<DealpayDealOrderEntity> list = dealpayDealOrderService.selectDealpayDealOrderList(dealpayDealOrder);
        return getDataTable(list);
    }

    /**
     * 导出交易订单列表
     */
    @RequiresPermissions("dealpay:dealOrder:export")
    @Log(title = "交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayDealOrderEntity dealpayDealOrder) {
        List<DealpayDealOrderEntity> list = dealpayDealOrderService.selectDealpayDealOrderList(dealpayDealOrder);
        ExcelUtil<DealpayDealOrderEntity> util = new ExcelUtil<DealpayDealOrderEntity>(DealpayDealOrderEntity.class);
        return util.exportExcel(list, "dealOrder");
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
    @RequiresPermissions("dealpay:dealOrder:add")
    @Log(title = "交易订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayDealOrderEntity dealpayDealOrder) {
        return toAjax(dealpayDealOrderService.insertDealpayDealOrder(dealpayDealOrder));
    }

    /**
     * 修改交易订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayDealOrderEntity dealpayDealOrder = dealpayDealOrderService.selectDealpayDealOrderById(id);
        mmap.put("dealpayDealOrder", dealpayDealOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存交易订单
     */
    @RequiresPermissions("dealpay:dealOrder:edit")
    @Log(title = "交易订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayDealOrderEntity dealpayDealOrder) {
        return toAjax(dealpayDealOrderService.updateDealpayDealOrder(dealpayDealOrder));
    }


    @RequiresPermissions("dealpay:orderDeal:updataOrder")
    @PostMapping("/updataOrder")
    @ResponseBody
    public  AjaxResult updataOrder(String id){
        DealpayDealOrderEntity order = dealpayDealOrderService.selectDealpayDealOrderById(Long.valueOf(id));
        order.setOrderStatus("7");//人工处理
        int i = dealpayDealOrderService.updateDealpayDealOrder(order);
        return toAjax(i);
    }

}
