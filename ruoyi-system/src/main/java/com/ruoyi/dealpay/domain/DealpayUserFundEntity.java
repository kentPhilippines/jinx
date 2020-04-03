package com.ruoyi.dealpay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 用户资金账户对象 dealpay_user_fund
 *
 * @author kiwi
 * @date 2020-04-03
 */
public class DealpayUserFundEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private Long id;

    /**
     * 账户id【登录账户】
     */
    @Excel(name = "账户id【登录账户】")
    private String userId;

    /**
     * 账户昵称【登录账户名】
     */
    @Excel(name = "账户昵称【登录账户名】")
    private String userName;

    /**
     * 现金账户【当前可取现】
     */
    @Excel(name = "现金账户【当前可取现】")
    private Double cashBalance;

    /**
     * 充值点数
     */
    @Excel(name = "充值点数")
    private Double rechargeNumber;

    /**
     * 冻结账户
     */
    @Excel(name = "冻结账户")
    private Double freezeBalance;

    /**
     * 可取现账户金额【账户余额=现金账户+充值点数-冻结金额】
     */
    @Excel(name = "可取现账户金额【账户余额=现金账户+充值点数-冻结金额】")
    private Double accountBalance;

    /**
     * 累计入款
     */
    @Excel(name = "累计入款")
    private Double sumDealAmountR;

    /**
     * 累计出款
     */
    @Excel(name = "累计出款")
    private Double sumDealAmountC;

    /**
     * 累计利润金额
     */
    @Excel(name = "累计利润金额")
    private Double sumProfit;

    /**
     * 累计代理商利润【如果当前账户为商户则该数据为0】
     */
    @Excel(name = "累计代理商利润【如果当前账户为商户则该数据为0】")
    private Double sumAgentProfit;

    /**
     * 累计入款笔数
     */
    @Excel(name = "累计入款笔数")
    private Long sumOrderCountR;

    /**
     * 累计出款笔数
     */
    @Excel(name = "累计出款笔数")
    private Long sumOrderCountC;

    /**
     * 当日入款
     */
    @Excel(name = "当日入款")
    private Double todayDealAmountR;

    /**
     * 当日出款
     */
    @Excel(name = "当日出款")
    private Double todayDealAmountC;

    /**
     * 当日利润金额
     */
    @Excel(name = "当日利润金额")
    private Double todayProfit;

    /**
     * 当日代理商利润【如果当前账户为商户则该数据为0】
     */
    @Excel(name = "当日代理商利润【如果当前账户为商户则该数据为0】")
    private Double todayAgentProfit;

    /**
     * 当日入款笔数
     */
    @Excel(name = "当日入款笔数")
    private Long todayOrderCountR;

    /**
     * 当日出款笔数
     */
    @Excel(name = "当日出款笔数")
    private Long todayOrderCountC;

    /**
     * 用户类型,商户1 卡商2
     */
    @Excel(name = "用户类型,商户1 卡商2")
    private String userType;

    /**
     * 代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】
     */
    @Excel(name = "代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】")
    private String agent;

    /**
     * 是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】
     */
    @Excel(name = "是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】")
    private String isAgent;

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
     * 数据版本号【数据修改锁】
     */
    @Excel(name = "数据版本号【数据修改锁】")
    private Long version;

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setCashBalance(Double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public Double getCashBalance() {
        return cashBalance;
    }

    public void setRechargeNumber(Double rechargeNumber) {
        this.rechargeNumber = rechargeNumber;
    }

    public Double getRechargeNumber() {
        return rechargeNumber;
    }

    public void setFreezeBalance(Double freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public Double getFreezeBalance() {
        return freezeBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setSumDealAmountR(Double sumDealAmountR) {
        this.sumDealAmountR = sumDealAmountR;
    }

    public Double getSumDealAmountR() {
        return sumDealAmountR;
    }

    public void setSumDealAmountC(Double sumDealAmountC) {
        this.sumDealAmountC = sumDealAmountC;
    }

    public Double getSumDealAmountC() {
        return sumDealAmountC;
    }

    public void setSumProfit(Double sumProfit) {
        this.sumProfit = sumProfit;
    }

    public Double getSumProfit() {
        return sumProfit;
    }

    public void setSumAgentProfit(Double sumAgentProfit) {
        this.sumAgentProfit = sumAgentProfit;
    }

    public Double getSumAgentProfit() {
        return sumAgentProfit;
    }

    public void setSumOrderCountR(Long sumOrderCountR) {
        this.sumOrderCountR = sumOrderCountR;
    }

    public Long getSumOrderCountR() {
        return sumOrderCountR;
    }

    public void setSumOrderCountC(Long sumOrderCountC) {
        this.sumOrderCountC = sumOrderCountC;
    }

    public Long getSumOrderCountC() {
        return sumOrderCountC;
    }

    public void setTodayDealAmountR(Double todayDealAmountR) {
        this.todayDealAmountR = todayDealAmountR;
    }

    public Double getTodayDealAmountR() {
        return todayDealAmountR;
    }

    public void setTodayDealAmountC(Double todayDealAmountC) {
        this.todayDealAmountC = todayDealAmountC;
    }

    public Double getTodayDealAmountC() {
        return todayDealAmountC;
    }

    public void setTodayProfit(Double todayProfit) {
        this.todayProfit = todayProfit;
    }

    public Double getTodayProfit() {
        return todayProfit;
    }

    public void setTodayAgentProfit(Double todayAgentProfit) {
        this.todayAgentProfit = todayAgentProfit;
    }

    public Double getTodayAgentProfit() {
        return todayAgentProfit;
    }

    public void setTodayOrderCountR(Long todayOrderCountR) {
        this.todayOrderCountR = todayOrderCountR;
    }

    public Long getTodayOrderCountR() {
        return todayOrderCountR;
    }

    public void setTodayOrderCountC(Long todayOrderCountC) {
        this.todayOrderCountC = todayOrderCountC;
    }

    public Long getTodayOrderCountC() {
        return todayOrderCountC;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public String getIsAgent() {
        return isAgent;
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

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("cashBalance", getCashBalance())
                .append("rechargeNumber", getRechargeNumber())
                .append("freezeBalance", getFreezeBalance())
                .append("accountBalance", getAccountBalance())
                .append("sumDealAmountR", getSumDealAmountR())
                .append("sumDealAmountC", getSumDealAmountC())
                .append("sumProfit", getSumProfit())
                .append("sumAgentProfit", getSumAgentProfit())
                .append("sumOrderCountR", getSumOrderCountR())
                .append("sumOrderCountC", getSumOrderCountC())
                .append("todayDealAmountR", getTodayDealAmountR())
                .append("todayDealAmountC", getTodayDealAmountC())
                .append("todayProfit", getTodayProfit())
                .append("todayAgentProfit", getTodayAgentProfit())
                .append("todayOrderCountR", getTodayOrderCountR())
                .append("todayOrderCountC", getTodayOrderCountC())
                .append("userType", getUserType())
                .append("agent", getAgent())
                .append("isAgent", getIsAgent())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("status", getStatus())
                .append("version", getVersion())
                .toString();
    }
}
