package com.ruoyi.web.controller.back;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.DatePrinter;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.domain.echarts.StackedAreaChart;
import com.ruoyi.alipay.service.StatisticService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 客户数据可视化模块
 * @author  kent
 */
@RequestMapping("/back/merchant/statistics")
@Controller
public class StatisticsMerchantContorller extends BaseController {
    private String prefix = "merchant/statistics";
    @Autowired
    StatisticService statisticServiceImpl;
    private static  final String DATE = "DATE";
    private static  final String CHART = "CHART";
    @GetMapping("/view")
    public String detail(ModelMap mmap) {
        BaseEntity baseEntity = new BaseEntity();
        Map<String, Object> params = new HashMap<>();
       // DateUtil.format(DateUtil.offsetMonth(DateUtil.parseDate(dayStart.toString(s)).toJdkDate(),-1).toJdkDate(), DatePattern.NORM_DATETIME_PATTERN);
        params.put("dayStart", DateUtil.format(DateUtil.offsetMonth(new Date(),-1).toJdkDate(), DatePattern.NORM_DATETIME_PATTERN));
        params.put("dayEnd", DateUtil.format( new Date()  , DatePattern.NORM_DATETIME_PATTERN));
        baseEntity.setParams(params);
        SysUser sysUser = ShiroUtils.getSysUser();
        String merchantId = sysUser.getMerchantId();//交易账号
        Map<String,Object> stackedAreaChartMap  = statisticServiceImpl.getStackedAreaChart(merchantId,baseEntity);
        mmap.put("data",stackedAreaChartMap);
        return prefix + "/agentStatistics";
    }


    /**
     * <p>查看代理数据</p>
     * @return
     */
    @PostMapping("/getStackedAreaChart")
    @ResponseBody
    public AjaxResult getStackedAreaChart(BaseEntity baseEntity){
        SysUser sysUser = ShiroUtils.getSysUser();
        String merchantId = sysUser.getMerchantId();//交易账号
        Map<String,Object> stackedAreaChartMap  = statisticServiceImpl.getStackedAreaChart(merchantId,baseEntity);
        return AjaxResult.success(stackedAreaChartMap);
     }



}
