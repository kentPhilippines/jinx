package com.ruoyi.common.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticsEntity implements Serializable {
    private static final long serialVersionUID = 5547968516186055805L;

    /**
     * 用户账户
     */
    private String userId;

    /**
     * 交易订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 成功订单总金额
     */
    private BigDecimal successAmount;

    /**
     * 交易订单总数
     */
    private Integer totalCount;

    /**
     * 交易订单成功总数
     */
    private Integer successCount;

    /**
     * 交易订单的成功率
     */
    private Double successPercent;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(BigDecimal successAmount) {
        this.successAmount = successAmount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Double getSuccessPercent() {
        return successPercent;
    }

    public void setSuccessPercent(Double successPercent) {
        this.successPercent = successPercent;
    }
}
