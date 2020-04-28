package com.ruoyi.web.controller.alipay;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.utils.DateUtils;
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
import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户订单登记Controller
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/orderApp")
public class AlipayDealOrderAppController extends BaseController {
    private String prefix = "alipay/orderApp";
    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;

    @RequiresPermissions("orderApp:merchant:view")
    @GetMapping()
    public String orderApp() {
        return prefix + "/orderApp";
    }

    /**
     * 查询商户订单登记列表
     */
    @RequiresPermissions("orderApp:merchant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayDealOrderApp alipayDealOrderApp) {
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
        return getDataTable(list);
    }

    /**
     * 导出商户订单登记列表
     */
    @RequiresPermissions("orderApp:merchant:export")
    @Log(title = "商户交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayDealOrderApp alipayDealOrderApp) {
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
        ExcelUtil<AlipayDealOrderApp> util = new ExcelUtil<AlipayDealOrderApp>(AlipayDealOrderApp.class);
        return util.exportExcel(list, "orderApp");
    }

    /**
     * 显示商户订单详情
     */
    @RequiresPermissions("orderApp:merchant:detail")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrderApp alipayDealOrderApp = alipayDealOrderAppService.selectAlipayDealOrderAppById(id);
        mmap.put("alipayDealOrderApp", alipayDealOrderApp);
        return prefix + "/edit";
    }

    /**
     * 转发财务
     */
    @RequiresPermissions("orderApp:merchant:toFiance")
    @Log(title = "商户交易订单", businessType = BusinessType.INSERT)
    @PostMapping("/updateOrder")
    @ResponseBody
    public AjaxResult updateOrder(String id) {
        AlipayDealOrderApp order = alipayDealOrderAppService.selectAlipayDealOrderAppById(Long.valueOf(id));
        order.setOrderStatus("7");//人工处理
        int i = alipayDealOrderAppService.updateAlipayDealOrderApp(order);
        return toAjax(i);
    }

    /**
     * 显示统计table
     */
    @GetMapping("/statistics/merchant/table")
    public String showTable() {
        return prefix + "/currentTable";
    }

    /**
     * 商户交易订单统计（仅当天数据）
     */
    @RequiresPermissions("orderApp:merchant:statistics")
    @PostMapping("/statistics/merchant/orderApp")
    @ResponseBody
    public TableDataInfo dayStat(StatisticsEntity statisticsEntity) {
        startPage();
        List<StatisticsEntity> list = alipayDealOrderAppService.selectMerchantStatisticsDataByDay(statisticsEntity, DateUtils.dayStart(), DateUtils.dayEnd());
//        if(statisticsEntity.getTotalCount() == 0){
//            statisticsEntity.setSuccessPercent(0.00);
//        }else{
//            BigDecimal percent = BigDecimal.valueOf((float) statisticsEntity.getSuccessCount() / statisticsEntity.getTotalCount());
//            Double successPercent = percent.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//            statisticsEntity.setSuccessPercent(successPercent);
//        }
//        mmap.put("statisticsEntity",statisticsEntity);
        return getDataTable(list);
    }


}
