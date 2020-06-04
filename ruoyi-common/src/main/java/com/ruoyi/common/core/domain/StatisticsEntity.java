package com.ruoyi.common.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5547968516186055805L;
    private String fee;
    public String getFee(){return this.fee;}
    public  void setFee(String fee){this.fee = fee;}
    private String successFee;
    public String getSuccessFee(){return this.successFee;}
    public  void setSuccessFee(String successFee){this.successFee = successFee;}
    private String accountAmount;
    public  String getAccountAmount (){return this.accountAmount;}
    public  void  setAccountAmount(String accountAmount){this.accountAmount = accountAmount;}
    private  String todayAmount;
    public void setTodayAmount(String todayAmount){this.todayAmount = todayAmount;}
    public String getTodayAmount(){return this.todayAmount;}
    /**
     * 用户账户
     */
    private String userId;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 产品名称
     */
    private String productName;

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("productType", getProductType())
                .append("productName", getProductName())
                .append("totalAmount", getTotalAmount())
                .append("successAmount", getSuccessAmount())
                .append("totalCount", getTotalCount())
                .append("successCount", getSuccessCount())
                .append("successPercent", getSuccessPercent())
                .toString();
    }
}
