package com.ruoyi.common.core.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author jack
 */
public class StatisticsMerchantEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 统计日期
     */
    private String findDay;
    /**
     * 收入
     */
    private String income;
    /**
     * 支出
     */
    private String expenditure;
    /**
     * 净收入
     */
    private String totalIncome;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getFindDay() {
        return findDay;
    }

    public void setFindDay(String findDay) {
        this.findDay = findDay;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

}
