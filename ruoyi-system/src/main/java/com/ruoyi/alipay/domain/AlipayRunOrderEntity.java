package com.ruoyi.alipay.domain;

import cn.hutool.core.date.DatePattern;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 流水订单记录对象 alipay_run_order
 *
 * @author kiwi
 * @date 2020-03-18
 */
public class AlipayRunOrderEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private Long id;

    /**
     * 流水订单id(全局唯一索引)
     */
    @Excel(name = "流水订单id")
    private String orderId;

    /**
     * 关联订单号(普通索引)
     */
    @Excel(name = "关联订单号")
    private String associatedId;

    /**
     * 关联账户
     */
    @Excel(name = "关联账户")
    private String orderAccount;

    /**
     * 流水类型(1充值交易,2系统加款,3交易手续费,4系统扣款,5代付,6代付手续费,7冻结,8解冻,9代付手手续费冻结,10代付冻结,11增加交易点数,12点数扣除,13代理商分润，14码商分润，17人工加点数，18人工减点数，19 卡商交易加钱)
     */
    @Excel(name = "流水类型", readConverterExp = "1=充值交易,2=系统加款," +
            "3=交易手续费,4=系统扣款,5=代付,6=代付手续费,7=冻结,8=解冻,9=代付手手续费冻结,10=代付冻结,11=增加交易点数," +
            "12=点数扣除,13=代理商分润," +
            "14=码商分润，17=人工加点数,18=人工减点数,19=卡商交易加钱,22=代付失败手续费解冻,20=商户交易加款,21=商户交易手续费扣款")
    private Integer runOrderType;

    /**
     * 流水金额
     */
    @Excel(name = "流水金额")
    private Double amount;

    /**
     * 流水关联ip
     */
    @Excel(name = "流水关联ip")
    private String generationIp;

    /**
     * 流水描述
     */
    @Excel(name = "流水描述")
    private String dealDescribe;

    /**
     * 入款记录账户
     */
    @Excel(name = "入款记录账户")
    private String acountR;

    /**
     * 出款记录账户
     */
    @Excel(name = "出款记录账户")
    private String accountW;

    /**
     * 数据最近一次修改时间
     */
    @Excel(name = "数据最近一次修改时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    private Date submitTime;

    /**
     * 状态:1可使用；0不可使用
     */
    private Integer status;

    /**
     * 流水状态  1.自然状态 2.人工操作
     */
    @Excel(name = "流水状态", readConverterExp = " 1=自然状态,2=人工操作")
    private String runType;

    /**
     * 1支出0收入
     */
    @Excel(name = "财务类型", readConverterExp = "1=支出,0=收入")
    private String amountType;

    /**
     * 当前账户余额
     */
    @Excel(name = "当前账户余额")
    private Double amountNow;

    /**
     * 备用字段添加业务使用
     */
    @Excel(name = "代理商分润账户来源")
    private String retain4;

    /**
     * 备用字段添加业务使用
     */
    private String retain5;

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

    public String getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(String associatedId) {
        this.associatedId = associatedId;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount;
    }

    public Integer getRunOrderType() {
        return runOrderType;
    }

    public void setRunOrderType(Integer runOrderType) {
        this.runOrderType = runOrderType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getGenerationIp() {
        return generationIp;
    }

    public void setGenerationIp(String generationIp) {
        this.generationIp = generationIp;
    }

    public String getDealDescribe() {
        return dealDescribe;
    }

    public void setDealDescribe(String dealDescribe) {
        this.dealDescribe = dealDescribe;
    }

    public String getAcountR() {
        return acountR;
    }

    public void setAcountR(String acountR) {
        this.acountR = acountR;
    }

    public String getAccountW() {
        return accountW;
    }

    public void setAccountW(String accountW) {
        this.accountW = accountW;
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

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public Double getAmountNow() {
        return amountNow;
    }

    public void setAmountNow(Double amountNow) {
        this.amountNow = amountNow;
    }

    public String getRetain4() {
        return retain4;
    }

    public void setRetain4(String retain4) {
        this.retain4 = retain4;
    }

    public String getRetain5() {
        return retain5;
    }

    public void setRetain5(String retain5) {
        this.retain5 = retain5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("associatedId", getAssociatedId())
                .append("orderAccount", getOrderAccount())
                .append("runOrderType", getRunOrderType())
                .append("amount", getAmount())
                .append("generationIp", getGenerationIp())
                .append("dealDescribe", getDealDescribe())
                .append("acountR", getAcountR())
                .append("accountW", getAccountW())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("status", getStatus())
                .append("runType", getRunType())
                .append("amountType", getAmountType())
                .append("amountNow", getAmountNow())
                .append("retain4", getRetain4())
                .append("retain5", getRetain5())
                .toString();
    }
}
