package com.ruoyi.common.core.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5547968516186055805L;
    private String fee;
    private String time;
    private String agentAmount;
    private String userAgent;

    public String getRetain1() {
        return retain1;
    }

    public void setRetain1(String retain1) {
        this.retain1 = retain1;
    }

    private String retain1;

    @Excel(name = "货币类型")
    private String currency;


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getAgentAmount() {
        return agentAmount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFee() {
        return this.fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    private String successFee;

    public String getSuccessFee() {
        return this.successFee;
    }

    public void setSuccessFee(String successFee) {
        this.successFee = successFee;
    }

    private String accountAmount;

    public String getAccountAmount() {
        return this.accountAmount;
    }

    public void setAccountAmount(String accountAmount) {
        this.accountAmount = accountAmount;
    }

    private String todayAmount;

    public void setTodayAmount(String todayAmount) {
        this.todayAmount = todayAmount;
    }
    public String getTodayAmount(){return this.todayAmount;}
    private String   profit;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getProfit() {
        return profit;
    }

    /**
     * 用户账户
     */
    private String userId;

    /**
     * 产品类型
     */
    private String productType;

    public void setAgentAmount(String agentAmount) {
        this.agentAmount = agentAmount;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

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

    public static void main(String[] args) {
        String str = "select '所有' userId,p.productName productName," +
                "coalesce(sum(app.orderAmount),0) totalAmount," +
                "coalesce(sum(case app.orderStatus when 2 then app.orderAmount else 0 end),0) successAmount," +
                "count(1) totalCount," +
                "count(case app.orderStatus when 2 then app.id else null end) successCount ," +
                "coalesce(sum(app.retain3),0) fee ," +
                "coalesce(sum(case app.orderStatus when 2 then app.retain3 else 0 end),0) successFee " +
                "from alipay_deal_order_app app left join alipay_product p on app.retain1 = p.productId " +
                "where " +
                "  app.createTime between #{statisticsEntity.params.dayStart} " +
                "<if test = \"statisticsEntity.currency != null and statisticsEntity.currency != ''\">" +
                "and app.currency = #{statisticsEntity.currency} " +
                "</if>" +
                " and #{statisticsEntity.params.dayEnd} and app.orderType = 1 " +
                "group by app.orderAccount,p.productName" +
                "<if test = \"statisticsEntity.retain1 != null and statisticsEntity.retain1 != ''\">" +
                "having p.productName = #{statisticsEntity.retain1} " +
                "</if>" +
                " union all " +
                "select app.orderAccount userId,p.productName productName," +
                "coalesce(sum(app.orderAmount),0.00) totalAmount," +
                "coalesce(sum(case app.orderStatus when 2 then app.orderAmount else 0 end),0) successAmount," +
                "count(1) totalCount," +
                "count(case app.orderStatus when 2 then app.id else null end) successCount ," +
                "coalesce(sum(app.retain3),0) fee ," +
                "coalesce(sum(case app.orderStatus when 2 then app.retain3 else 0 end),0) successFee " +
                "from alipay_deal_order_app app left join alipay_product p on app.retain1 = p.productId " +
                "where " +
                "  app.createTime between #{statisticsEntity.params.dayStart} " +
                "and #{statisticsEntity.params.dayEnd} and app.orderType = 1 " +
                "<if test = \"statisticsEntity.userId != null and statisticsEntity.userId != ''\">" +
                "and app.orderAccount = #{statisticsEntity.userId} " +
                "</if>" +
                "<if test = \"statisticsEntity.userAgent != null and statisticsEntity.userAgent != ''\">" +
                "and app.orderAccount in (select userId from alipay_user_info where agent = #{statisticsEntity.userAgent}) " +
                "</if>" +
                "<if test = \"statisticsEntity.currency != null and statisticsEntity.currency != ''\">" +
                "and app.currency = #{statisticsEntity.currency} " +
                "</if>" +
                "group by app.orderAccount,p.productName" +
                "<if test = \"statisticsEntity.retain1 != null and statisticsEntity.retain1 != ''\">" +
                "having p.productName = #{statisticsEntity.retain1} " +
                "</if>" ;
        System.out.println(str);
    }
}
