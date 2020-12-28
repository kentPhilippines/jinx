package com.ruoyi.web.controller.api;

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
    }
}
