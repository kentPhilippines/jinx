package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 用户详情对象 alipay_user_info
 *
 * @author kent
 * @date 2020-03-21
 */
public class AlipayUserInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 数据id
     */
    private Long id;
    /**
     * 用户id【登录账号】
     */
    private String userId;
    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    private String userName;
    /**
     * shiro加密秘钥【登录】
     */
    @Excel(name = "shiro加密秘钥【登录】")
    private String password;
    /**
     * shiro加密秘钥【资金】【商户则为交易秘钥】
     */
    @Excel(name = "shiro加密秘钥【资金】【商户则为交易秘钥】")
    private String payPasword;
    /**
     * 秘钥加密盐值【加密算法】
     */
    @Excel(name = "秘钥加密盐值【加密算法】")
    private String salt;
    /**
     * 用户类型,商户1 码商2
     */
    @Excel(name = "用户类型,商户1 码商2")
    private Integer userType;
    /**
     * 当前用户总开关 1开启0关闭【码商商户后台控制,该值只能在后台显示】
     */
    @Excel(name = "当前用户总开关 1开启0关闭【码商商户后台控制,该值只能在后台显示】")
    private Integer switchs;
    /**
     * 组群备注
     */
    @Excel(name = "组群备注")
    private String userNode;
    /**
     * 邮箱【修改账号秘钥邮件发送地址】
     */
    @Excel(name = "邮箱【修改账号秘钥邮件发送地址】")
    private String email;
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
     * 信用等级为默认为100,掉单一次,或者出现故障一次减分0.001分,可人为加分
     */
    @Excel(name = "信用等级为默认为100,掉单一次,或者出现故障一次减分0.001分,可人为加分")
    private Double credit;

    /**
     * 是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以交易】
     */
    @Excel(name = "是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以交易】")
    private Integer receiveOrderState;

    /**
     * 是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以代付】
     */
    @Excel(name = "是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以代付】")
    private Integer remitOrderState;

    /**
     * QQ联系方式
     */
    @Excel(name = "QQ联系方式")
    private String QQ;

    /**
     * 小飞机
     */
    @Excel(name = "小飞机")
    private String telegram;

    /**
     * skype
     */
    @Excel(name = "skype")
    private String skype;

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
     * 商户私钥
     */
    @Excel(name = "商户私钥")
    private String privateKey;

    /**
     * 商户公钥
     */
    @Excel(name = "商户公钥")
    private String publicKey;

    /**
     * 最小金额
     */
    @Excel(name = "最小金额")
    private Double minAmount;

    /**
     * 最大金额
     */
    @Excel(name = "最大金额")
    private Double maxAmount;

    /**
     * 最大金额
     */
    @Excel(name = "总金额限制")
    private Double totalAmount;

    /**
     * 下单次数
     */
    @Excel(name = "下单次数")
    private Integer timesTotal;

    /**
     * 限制时间-开始时间
     */
    @Excel(name = "限制时间-开始时间")
    private String startTime;

    /**
     * 限制时间-结束时间
     */
    @Excel(name = "限制时间-结束时间")
    private String endTime;

    /** 代付ip */
    @Excel(name = "代付ip")
    private String witip;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPayPasword(String payPasword) {
        this.payPasword = payPasword;
    }

    public String getPayPasword() {
        return payPasword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
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

    public void setUserNode(String userNode) {
        this.userNode = userNode;
    }

    public String getUserNode() {
        return userNode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setReceiveOrderState(Integer receiveOrderState) {
        this.receiveOrderState = receiveOrderState;
    }

    public Integer getReceiveOrderState() {
        return receiveOrderState;
    }

    public void setRemitOrderState(Integer remitOrderState) {
        this.remitOrderState = remitOrderState;
    }

    public Integer getRemitOrderState() {
        return remitOrderState;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getQQ() {
        return QQ;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getSkype() {
        return skype;
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

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setTimesTotal(Integer timesTotal) {
        this.timesTotal = timesTotal;
    }

    public Integer getTimesTotal() {
        return timesTotal;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getWitip() {
        return witip;
    }

    public void setWitip(String witip) {
        this.witip = witip;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("password", getPassword())
                .append("payPasword", getPayPasword())
                .append("salt", getSalt())
                .append("userType", getUserType())
                .append("switchs", getSwitchs())
                .append("userNode", getUserNode())
                .append("email", getEmail())
                .append("agent", getAgent())
                .append("isAgent", getIsAgent())
                .append("credit", getCredit())
                .append("receiveOrderState", getReceiveOrderState())
                .append("remitOrderState", getRemitOrderState())
                .append("QQ", getQQ())
                .append("telegram", getTelegram())
                .append("skype", getSkype())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("status", getStatus())
                .append("privateKey", getPrivateKey())
                .append("publicKey", getPublicKey())
                .append("minAmount", getMinAmount())
                .append("maxAmount", getMaxAmount())
                .append("totalAmount", getTotalAmount())
                .append("timesTotal", getTimesTotal())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("witip", getWitip())
                .toString();
    }
}