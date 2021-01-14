package com.ruoyi.web.controller.api;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.ruoyi.alipay.service.Reconciliation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class FundTask {
    private static final Log log = LogFactory.get();
    @Autowired
    Reconciliation reconciliationImpl;

    @Scheduled(cron = "0 0 1 * * ?")
    public void fundTask() {
        reconciliationImpl.fundTask("");
        String today = DateUtil.today();
        String format = DatePattern.NORM_DATE_FORMAT.format(DateUtil.offsetDay(DateUtil.parseDateTime(today).toJdkDate(), 1).toJdkDate());
        HttpUtil.get("http://starpay77.com/accountAppFundApi/orderAccount?starTime=" + format + "&endTime=" + format);
        HttpUtil.get("http://starpay77.com/accountAppFundApi/fundAccount?starTime=" + format + " 01:00:00");
    }
}
