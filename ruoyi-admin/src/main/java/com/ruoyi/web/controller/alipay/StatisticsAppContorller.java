package com.ruoyi.web.controller.alipay;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.alipay.service.StatisticService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 运营数据可视化模块
 *
 * @author kent
 */
@RequestMapping("/back/app/statistics")
@Controller
public class StatisticsAppContorller
        extends BaseController {
    @Autowired
    StatisticService statisticServiceImpl;
    private String prefix = "alipay/statistics";

    @GetMapping("/view")
    public String getStackedAreaChart(ModelMap mmap, String userId) {
        BaseEntity baseEntity = new BaseEntity();
        logger.info("【查看商户月度交易数据，商户账号：" + userId + "】");
        Map<String, Object> params = new HashMap<>();
        params.put("dayStart", DateUtil.format(DateUtil.offsetMonth(new Date(), -2).toJdkDate(), DatePattern.NORM_DATETIME_PATTERN));
        params.put("dayEnd", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN));
        baseEntity.setParams(params);
        Map<String, Object> stackedAreaChartMap = statisticServiceImpl.getStackedAreaChart(userId, baseEntity, true);
        mmap.put("data", stackedAreaChartMap);
        return prefix + "/appStatistics";
    }
}
