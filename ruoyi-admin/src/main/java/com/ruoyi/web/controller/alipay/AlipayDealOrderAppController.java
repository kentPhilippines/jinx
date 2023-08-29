package com.ruoyi.web.controller.alipay;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;
    @Autowired
    private IAlipayProductService iAlipayProductService;

    @GetMapping()
    public String orderApp(ModelMap mmap) {
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        alipayProductEntity.setProductCode("2");
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        return prefix + "/orderApp";
    }

    /**
     * 查询商户订单登记列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayDealOrderApp alipayDealOrderApp) {
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        alipayProductEntity.setProductCode("2");
        //查询产品类型下拉菜单
        List<AlipayProductEntity> productList = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        ConcurrentHashMap<String, AlipayProductEntity> prCollect = productList.stream().collect(Collectors.toConcurrentMap(AlipayProductEntity::getProductId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        list.stream().forEach(
                o -> o.setRetain1(prCollect.get(o.getRetain1()).getProductName())
        );
        return getDataTable(list);
    }

    @GetMapping("/orderAppAgent")
    public String orderAppAgent() {
        return prefix + "/orderAppAgent";
    }

    /**
     * 查询代理商分润
     */
    @PostMapping("/listAgent")
    @ResponseBody
    public TableDataInfo listAgent(AlipayDealOrderApp alipayDealOrderApp) {
        startPage();
        List<AlipayDealOrderApp> list = alipayDealOrderAppService.listAgent(alipayDealOrderApp);
        return getDataTable(list);
    }

    /**
     * 导出商户订单登记列表
     */
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
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayDealOrderApp alipayDealOrderApp = alipayDealOrderAppService.selectAlipayDealOrderAppById(id);
        mmap.put("alipayDealOrderApp", alipayDealOrderApp);
        return prefix + "/edit";
    }

    /**
     * 转发财务
     */
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
    public String showTable(ModelMap mmap) {
        //查询产品类型下拉菜单
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        alipayProductEntity.setProductCode("2");
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        return prefix + "/currentTable";
    }

    /**
     * 商户交易订单统计（仅当天数据）
     */
    @PostMapping("/statistics/merchant/orderApp")
    @ResponseBody
    public TableDataInfo dayStat(StatisticsEntity statisticsEntity) {
        startPage();
        List<StatisticsEntity> list = alipayDealOrderAppService.selectMerchantStatisticsDataByDay(statisticsEntity, DateUtils.dayStart(), DateUtils.dayEnd());
        List<AlipayUserFundEntity> listFund = alipayUserFundEntityService.findUserFundAll();
        ConcurrentHashMap<String, AlipayUserFundEntity> userCollect = listFund.stream().collect(Collectors.toConcurrentMap(AlipayUserFundEntity::getUserId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        BigDecimal amount = new BigDecimal("0");
        ConcurrentHashMap.KeySetView<String, AlipayUserFundEntity> strings = userCollect.keySet();
        for (String key : strings) {
            AlipayUserFundEntity alipayUserFundEntity = userCollect.get(key);
            if (alipayUserFundEntity.getUserType().equals("1")) {
                amount = amount.add(new BigDecimal(alipayUserFundEntity.getAccountBalance()));
            }
        }
        BigDecimal finalAmount = amount;
        list.stream().forEach(o -> {
            if (ObjectUtil.isNotNull(userCollect.get(o.getUserId()))) {
                o.setUserName(userCollect.get(o.getUserId()).getUserName());
            }
            if (ObjectUtil.isNotNull(userCollect.get(o.getUserId()))) {
                o.setAccountAmount(userCollect.get(o.getUserId()).getAccountBalance().toString());
            }
            if ("所有".equals(o.getUserId())) {
                o.setAccountAmount(finalAmount.doubleValue() + "");
            }
            o.setTodayAmount(o.getSuccessAmount().subtract(new BigDecimal(o.getSuccessFee())).toString());
        });
        return getDataTable(list);
    }


}
