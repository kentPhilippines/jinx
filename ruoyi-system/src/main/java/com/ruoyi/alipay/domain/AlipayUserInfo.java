package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 用户详情对象 alipay_user_info
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public class AlipayUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 用户id【登录账号】 */
    private String userId;

    /** 用户昵称 */
    private String userName;

    /** shiro加密秘钥【登录】 */
    private String password;

    /** shiro加密秘钥【资金】 */
    private String payPasword;

    /** 秘钥加密盐值【加密算法】 */
    private String salt;

    /** 用户类型,商户1 码商2 */
    private Integer userType;

    /** 当前用户总开关 1开启0关闭【码商商户后台控制,该咋宏泰只能在后台显示】 */
    private Integer switchs;

    /** 组群备注 */
    private String userNode;

    /** 邮箱【修改账号秘钥邮件发送地址】 */
    private String email;

    /** 代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】 */
    private String agent;

    /** 是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】 */
    private String isAgent;

    /** 信用等级为默认为100,掉单一次,或者出现故障一次减分0.001分,可人为加分 */
    private Double credit;

    /** 是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以交易】 */
    private Integer receiveOrderState;

    /** 是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以代付】 */
    private Integer remitOrderState;

    /** QQ联系方式 */
    private String QQ;

    /** 小飞机 */
    private String telegram;

    /** skype */
    private String skype;

    /** 最后一次数据修改时间 */
    private Date submitTime;

    /** 数据是否可用:1数据可用2数据无用 */
    private Integer status;

    /** 预留，添加业务使用 */
    private String retain1;

    /** 预留，添加业务使用 */
    private String retain2;

    /** 预留，添加业务使用 */
    private String retain3;

    /** 预留，添加业务使用 */
    private String retain4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPasword() {
        return payPasword;
    }

    public void setPayPasword(String payPasword) {
        this.payPasword = payPasword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getSwitchs() {
        return switchs;
    }

    public void setSwitchs(Integer switchs) {
        this.switchs = switchs;
    }

    public String getUserNode() {
        return userNode;
    }

    public void setUserNode(String userNode) {
        this.userNode = userNode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Integer getReceiveOrderState() {
        return receiveOrderState;
    }

    public void setReceiveOrderState(Integer receiveOrderState) {
        this.receiveOrderState = receiveOrderState;
    }

    public Integer getRemitOrderState() {
        return remitOrderState;
    }

    public void setRemitOrderState(Integer remitOrderState) {
        this.remitOrderState = remitOrderState;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
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

    public String getRetain4() {
        return retain4;
    }

    public void setRetain4(String retain4) {
        this.retain4 = retain4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("password", getPassword())
            .append("payPassword", getPayPasword())
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
            .append("retain1", getRetain1())
            .append("retain2", getRetain2())
            .append("retain3", getRetain3())
            .append("retain4", getRetain4())
            .toString();
    }
}
