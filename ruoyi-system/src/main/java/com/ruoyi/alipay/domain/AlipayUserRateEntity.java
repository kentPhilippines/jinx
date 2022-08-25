package com.ruoyi.alipay.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 用户产品费率对象 alipay_user_rate
 *
 * @author kiwi
 * @date 2020-03-18
 */
public class AlipayUserRateEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private Long id;

    /**
     * 用户id【登录账号】
     */
    @Excel(name = "用户id【登录账号】")
    private String userId;
    private String agentUserId;
    private String queueList;
    private List<String> agentList;

    public String getQueueList() {
        return queueList;
    }

    public void setQueueList(String queueList) {
        this.queueList = queueList;
    }

    public List<String> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<String> agentList) {
        this.agentList = agentList;
    }

    public String getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(String agentUserId) {
        this.agentUserId = agentUserId;
    }


    /**
     * 用户类型,商户1 码商2
     */
    @Excel(name = "用户类型,商户1 码商2")
    private Integer userType;

    private String channelId;
    private String channelFee;
    private String profit;

    public String getChannelFee() {
        return channelFee;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 当前用户总开关 1开启0关闭【码商商户后台控制,该数据只能在后台显示】
     */
    @Excel(name = "当前用户总开关 1开启0关闭【码商商户后台控制,该数据只能在后台显示】")
    private Integer switchs;

    public void setChannelFee(String channelFee) {
        this.channelFee = channelFee;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    /**
     * 产品类型
     */
    @Excel(name = "产品类型")
    private String payTypr;

    /**
     * 费率数值
     */
    @Excel(name = "费率数值")
    private Double fee;

    /**
     * 费率类型:1交易费率，2代付费率
     */
    @Excel(name = "费率类型:1交易费率，2代付费率")
    private Integer feeType;

    /**
     * 最后一次数据修改时间
     */
    @Excel(name = "最后一次数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 数据是否可用:1数据可用2数据无用
     */
    @Excel(name = "数据是否可用:1数据可用2数据无用")
    private Integer status;

    /**
     * 预留，添加业务使用
     */
    @Excel(name = "优先级")
    private String retain1;

    /**
     * 预留，添加业务使用
     */
    @Excel(name = "优先级金额")
    private String retain2;

    /**
     * 预留，添加业务使用
     */
    @Excel(name = "额外抽点")
    private String retain3;

    /**
     * 预留，添加业务使用
     */
    @Excel(name = "预留，添加业务使用")
    private String retain4;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setSwitchs(Integer switchs) {
        this.switchs = switchs;
    }

    public Integer getSwitchs() {
        return switchs;
    }

    public void setPayTypr(String payTypr) {
        this.payTypr = payTypr;
    }

    public String getPayTypr() {
        return payTypr;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getFee() {
        return fee;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Integer getFeeType() {
        return feeType;
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

    public void setRetain3(String retain3) {
        this.retain3 = retain3;
    }

    public String getRetain3() {
        return retain3;
    }

    public void setRetain4(String retain4) {
        this.retain4 = retain4;
    }

    public String getRetain4() {
        return retain4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("userType", getUserType())
                .append("switchs", getSwitchs())
                .append("payTypr", getPayTypr())
                .append("fee", getFee())
                .append("feeType", getFeeType())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("status", getStatus())
                .append("retain1", getRetain1())
                .append("retain2", getRetain2())
                .append("retain3", getRetain3())
                .append("retain4", getRetain4())
                .toString();
    }
}
