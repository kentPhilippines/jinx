package com.ruoyi.alipay.domain;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 商户订单登记对象 alipay_deal_order_app
 *
 * @author kiwi
 * @date 2020-03-17
 */
public class AlipayDealOrderApp extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String userName;
    /**
     * 数据id
     */
    private Long id;
    /**
     * 订单号
     */
    @Excel(name = "商户订单号")
    private String orderId;
    /**
     * 订单类型:1交易,5代付
     */
    private Integer orderType;
    /**
     * 订单关联商户账号
     */
    @Excel(name = "订单关联商户账号")
    private String orderAccount;
    /**
     * 订单状态:1处理中2成功3未收到回调4失败5超时6订单申述7人工处理
     */
    @Excel(name = "订单状态", readConverterExp = "0=预下单,1=处理中,2=成功,3=未收到回调,4=失败,5=超时,6=订单申述,7=人工处理")
    private String orderStatus;
    /**
     * 交易备注
     */
    @Excel(name = "交易备注")
    private String dealDescribe;
    /**
     * 订单金额(原始金额)
     */
    @Excel(name = "订单金额")
    private Double orderAmount;
    /**
     * 订单生成IP(源头ip)
     */
    private String orderIp;
    /**
     * 交易外部订单号
     */
    @Excel(name = "交易外部订单号")
    private String appOrderId;
    /**
     * 使用费率id
     */
    private Integer feeId;
    /**
     * 订单异步回调地址
     */
    private String notify;
    /**
     * 订单同步回调地址
     */
    private String back;
    /**
     * 数据修改时间
     */
    @Excel(name = "数据修改时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;
    /**
     * 状态:1可使用；0不可使用
     */
    private Integer status;
    /**
     * 备用字段添加业务使用
     */
    @Excel(name = "产品类型")
    private String retain1;
    /**
     * 备用字段添加业务使用
     */
    private String retain2;
    /**
     * 备用字段添加业务使用
     */
    @Excel(name = "订单结算手续费")
    private String retain3;


    @Excel(name = "货币类型")
    private String currency;


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDealDescribe() {
        return dealDescribe;
    }

    public void setDealDescribe(String dealDescribe) {
        this.dealDescribe = dealDescribe;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    public String getAppOrderId() {
        return appOrderId;
    }

    public void setAppOrderId(String appOrderId) {
        this.appOrderId = appOrderId;
    }

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRetain1() {
        return retain1;
    }

    public void setRetain1(String retain1) {
        this.retain1 = retain1;
    }

    public String getRetain2() {
        return retain2;
    }

    public void setRetain2(String retain2) {
        this.retain2 = retain2;
    }

    public String getRetain3() {
        return retain3;
    }

    public void setRetain3(String retain3) {
        this.retain3 = retain3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("orderType", getOrderType())
                .append("orderAccount", getOrderAccount())
                .append("orderStatus", getOrderStatus())
                .append("dealDescribe", getDealDescribe())
                .append("orderAmount", getOrderAmount())
                .append("orderIp", getOrderIp())
                .append("appOrderId", getAppOrderId())
                .append("feeId", getFeeId())
                .append("notify", getNotify())
                .append("back", getBack())
                .append("submitTime", getSubmitTime())
                .append("createTime", getCreateTime())
                .append("status", getStatus())
                .append("retain1", getRetain1())
                .append("retain2", getRetain2())
                .append("retain3", getRetain3())
                .toString();
    }
}
