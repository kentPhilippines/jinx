package com.ruoyi.web.controller.dealpay;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.dealpay.domain.DealpayDealOrderEntity;
import com.ruoyi.dealpay.service.IDealpayDealOrderService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    private String finance_prefix = "dealpay/finance";

    @Autowired
    private IDealpayDealOrderService dealpayDealOrderService;

    @Autowired
    private DictionaryUtils dictionaryUtils;

    @GetMapping()
    public String dealOrder() {
        return prefix + "/dealOrder";
    }

    /**
     * 查询交易订单列表
     */
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
        return finance_prefix + "/orderDetail";
    }

    /**
     * 修改保存交易订单
     */
    @Log(title = "交易订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayDealOrderEntity dealpayDealOrder) {
        return toAjax(dealpayDealOrderService.updateDealpayDealOrder(dealpayDealOrder));
    }


    @PostMapping("/updataOrder")
    @ResponseBody
    public AjaxResult updataOrder(String id) {
        DealpayDealOrderEntity order = dealpayDealOrderService.selectDealpayDealOrderById(Long.valueOf(id));
        order.setOrderStatus("7");//人工处理
        int i = dealpayDealOrderService.updateDealpayDealOrder(order);
        return toAjax(i);
    }


    /*财务手动处理订单操作逻辑*/

    @GetMapping("/finance/check")
    public String orderDeal() {
        return finance_prefix + "/manual";
    }

    /**
     * 查询交易订单列表
     */
    @PostMapping("/finance/show")
    @ResponseBody
    public TableDataInfo financeCheck(DealpayDealOrderEntity dealpayDealOrderEntity) {
        startPage();
        List<DealpayDealOrderEntity> list = dealpayDealOrderService.selectDealpayDealOrderList(dealpayDealOrderEntity);
        return getDataTable(list);
    }

    /**
     * <p>卡商交易订单状态确认</p>
     */
    @PostMapping("/finance/updataOrder")
    @ResponseBody
    @Log(title = "人工补单", businessType = BusinessType.UPDATE)
    public AjaxResult enterOrder(Long id, String orderStatus) {
        DealpayDealOrderEntity order = dealpayDealOrderService.selectDealpayDealOrderById(id);
        if (StrUtil.isBlank(orderStatus)) {
            return AjaxResult.error("订单状态出错");
        }
        String status = order.getOrderStatus();
        SysUser currentUser = ShiroUtils.getSysUser();
        Long userId = currentUser.getUserId();
        @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符") String userName = currentUser.getUserName();
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        if ("2".equals(status) || "4".equals(status)) {
            return AjaxResult.error("当前订单状态不允许修改");
        }
        mapParam.put("orderId", order.getOrderId());
        mapParam.put("userName", userName);
        if ("SU".equals(orderStatus)) {
            mapParam.put("orderStatus", "2");
        } else if ("ER".equals(orderStatus)) {
            mapParam.put("orderStatus", "4");
        }
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_IP_URL_KEY, StaticConstants.DealPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_IP_URL_VALUE, StaticConstants.DealPAY_SERVICE_API_VALUE_2);
        AjaxResult post = post(ipPort + urlPath, mapParam);
        return post;
    }

}
