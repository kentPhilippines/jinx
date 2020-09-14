package com.ruoyi.alipay.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 手动加扣款记录对象 alipay_amount
 *
 * @author kiwi
 * @date 2020-03-24
 */
public class AlipayAmountEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String merchantId;
    private String loginName;
    /**
     * 数据id(主键索引)
     */
    private Long id;
    /**
     * 手动加扣款订单号
     */
    @Excel(name = "手动加扣款订单号")
    private String orderId;
    /**
     * 会员id(唯一识别号)(index索引)
     */
    @Excel(name = "会员id(唯一识别号)(index索引)")
    private String userId;
    /**
     * 1 加款类型  2 扣款类型
     */
    @Excel(name = "1 加款类型  2 扣款类型")
    private String amountType;
    /**
     * 申请人姓名【后台管理人员】
     */
    @Excel(name = "申请人姓名【后台管理人员】")
    private String accname;
    /**
     * 1申请 2审批 3成功  4失败
     */
    @Excel(name = "1申请 2审批 3成功  4失败")
    private String orderStatus;
    /**
     * 金额
     */
    @Excel(name = "金额")
    private Double amount;
    /**
     * 手续费
     */
    @Excel(name = "手续费")
    private Double fee;
    /**
     * 真实到账金额
     */
    @Excel(name = "真实到账金额")
    private Double actualAmount;
    /**
     * 加扣款描述
     */
    @Excel(name = "加扣款描述")
    private String dealDescribe;
    /**
     * 数据修改时间
     */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;
    /**
     * 1数据可用2数据无用
     */
    @Excel(name = "1数据可用2数据无用")
    private Integer status;
    /**
     * 审核人
     */
    @Excel(name = "审核人")
    private String approval;
    /**
     * 审核意见
     */
    @Excel(name = "审核意见")
    private String comment;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.setAccname(loginName);
        this.loginName = loginName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.setUserId(merchantId);
        this.merchantId = merchantId;
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

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
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

    public String getDealDescribe() {
        return dealDescribe;
    }

    public void setDealDescribe(String dealDescribe) {
        this.dealDescribe = dealDescribe;
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

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("userId", getUserId())
                .append("amountType", getAmountType())
                .append("accname", getAccname())
                .append("orderStatus", getOrderStatus())
                .append("amount", getAmount())
                .append("fee", getFee())
                .append("actualAmount", getActualAmount())
                .append("dealDescribe", getDealDescribe())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("status", getStatus())
                .append("approval", getApproval())
                .append("comment", getComment())
                .toString();
    }
}
