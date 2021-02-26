package com.ruoyi.alipay.domain;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 会员提现记录对象 alipay_withdraw
 *
 * @author kiwi
 * @date 2020-03-17
 */
public class AlipayWithdrawEntity extends BaseEntity {
    /* BigDecimal usdt ,   //花费usdt
           price  ,    //汽油价格
           used,       //使用汽油数
           eth,        //花费eth
           priceUsdt;  //eth - usdt 汇率
   String hash;        //订单hash* */
    private String usdt;
    private String price;
    private String used;
    private String eth;
    private String priceUsdt;
    private String hash;
    @Excel(name = "是否结算eth手续费", readConverterExp = "0=未结算,1=已结算")
    private Integer ethFee;
    @Excel(name = "USDT-hash")
    private String txhash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPriceUsdt() {
        return priceUsdt;
    }

    public void setPriceUsdt(String priceUsdt) {
        this.priceUsdt = priceUsdt;
    }

    public String getEth() {
        return eth;
    }

    public void setEth(String eth) {
        this.eth = eth;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private static final long serialVersionUID = 1L;
    private String bankcode;
    private String appOrderId;

    public String getAppOrderId() {
        return appOrderId;
    }

    public void setAppOrderId(String appOrderId) {
        this.appOrderId = appOrderId;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBankcode() {
        return this.bankcode;
    }

    /**
     * 数据id(主键索引)
     */
    private Long id;
    /**
     * 会员提现单号
     */
    @Excel(name = "会员提现单号")
    private String orderId;
    /**
     * 会员id(唯一识别号)(index索引)
     */
    @Excel(name = "会员id(唯一识别号)(index索引)")
    private String userId;
    /**
     * 商户提现1，码商提现2
     */
    @Excel(name = "商户提现1，码商提现2")
    private String withdrawType;
    /**
     * 银行卡号
     */
    @Excel(name = "银行卡号")
    private String bankNo;
    /**
     * 提现人姓名
     */
    @Excel(name = "提现人姓名")
    private String accname;
    /**
     * 0预下单1处理中2成功3失败
     */
    @Excel(name = "订单状态", readConverterExp = "0=预下单,1=处理中,2=成功,3=失败")
    private String orderStatus;
    /**
     * 开户行姓名
     */
    @Excel(name = "开户行姓名")
    private String bankName;
    /**
     * 提现金额
     */
    @Excel(name = "提现金额")
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
     * 手机号
     */
    @Excel(name = "手机号")
    private String mobile;
    /**
     * 提现成功回调地址
     */
    @Excel(name = "提现成功回调地址")
    private String notify;
    /**
     * 数据修改时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 数据修改时间
     */
    @Excel(name = "数据修改时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    /**
     * 1数据可用2数据无用
     */
    @Excel(name = "1数据可用2数据无用")
    private Integer status;

    /**
     * 备用字段添加业务使用
     */
    @Excel(name = "代付来源")
    private String retain1;

    /**
     * 备用字段添加业务使用
     */
    @Excel(name = "备用字段添加业务使用")
    private String retain2;

    /**
     * 审核人
     */
    @Excel(name = "后台申请人")
    private String apply;

    /**
     * 审核人
     */
    @Excel(name = "审核人")
    private String approval;
    @Excel(name = "代付产品")
    private String witType;
    //代付渠道
    private String witChannel;
    @Excel(name = "实际代付渠道")
    private String channelId;//实际代付渠道

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getWitType() {
        return witType;
    }

    public void setWitType(String witType) {
        this.witType = witType;
    }

    @Excel(name = "货币类型")
    private String currency;

    public String getUsdt() {
        return usdt;
    }

    public void setUsdt(String usdt) {
        this.usdt = usdt;
    }

    public Integer getEthFee() {
        return ethFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setEthFee(Integer ethFee) {
        this.ethFee = ethFee;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    /**
     * 审核意见
     */
    @Excel(name = "审核意见")
    private String comment;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public String getWitChannel() {
        return witChannel;
    }

    public void setWitChannel(String witChannel) {
        this.witChannel = witChannel;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getAccname() {
        return accname;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getFee() {
        return fee;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getNotify() {
        return notify;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setRetain1(String retain1) {
        this.retain1 = retain1;
    }

    public String getRetain1() {
        return retain1;
    }

    public void setRetain2(String retain2) {
        this.retain2 = retain2;
    }

    public String getRetain2() {
        return retain2;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
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
                .append("withdrawType", getWithdrawType())
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
                .append("status", getStatus())
                .append("retain1", getRetain1())
                .append("retain2", getRetain2())
                .append("apply",getApply())
                .append("approval", getApproval())
                .append("comment", getComment())
                .toString();
    }
}
