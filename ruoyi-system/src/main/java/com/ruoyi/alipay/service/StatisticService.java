package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.echarts.StackedAreaChart;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    public static  final String DATE = "DATE";
    public static  final String CHART = "CHART";
    public static  final String USERS = "USERS";
    public static  final String MARK = "-";
    /**
     * <p>根据自己账号查询自己下线的数据，只查看一个月之类的数据</p>
     * @param merchantId
     * @param baseEntity
     * @return
     */
    Map<String,Object> getStackedAreaChart(String merchantId, BaseEntity baseEntity);
}
