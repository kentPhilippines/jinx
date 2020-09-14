package com.ruoyi.alipay.domain.util;

import cn.hutool.core.date.DatePattern;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class WitAppExport extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    @Excel(name = "会员提现单号")
    private String orderId;
    @Excel(name = "会员id")
    private String userId;
    @Excel(name = "银行卡号")
    private String bankNo;
    @Excel(name = "提现人姓名")
    private String accname;
    @Excel(name = "订单状态", readConverterExp = "0=预下单,1=处理中,2=成功,3=失败")
    private String orderStatus;
    @Excel(name = "开户行姓名")
    private String bankName;
    @Excel(name = "提现金额")
    private Double amount;
    @Excel(name = "手续费")
    private Double fee;
    @Excel(name = "真实到账金额")
    private Double actualAmount;
    @Excel(name = "手机号")
    private String mobile;
    // @Excel(name = "提现成功回调地址")
    private String notify;
    @Excel(name = "创建时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    private Date createTime;
    @Excel(name = "数据修改时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    private Date submitTime;
    @Excel(name = "代付产品")
    private String witType;
    @Excel(name = "审核意见")
    private String comment;

    public String getWitType() {
        return witType;
    }

    public void setWitType(String witType) {
        this.witType = witType;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("userId", getUserId())
                .append("bankNo", getBankNo())
                .append("accname", getAccname())
                .append("orderStatus", getOrderStatus())
                .append("bankName", getBankName())
                .append("amount", getAmount())
                .append("fee", getFee())
                .append("actualAmount", getActualAmount())
                .append("mobile", getMobile())
                .append("notify", getNotify())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("comment", getComment())
                .toString();
    }
}
