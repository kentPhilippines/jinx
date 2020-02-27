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
    private String userid;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String username;

    /** shiro加密秘钥【登录】 */
    @Excel(name = "shiro加密秘钥【登录】")
    private String password;

    /** shiro加密秘钥【资金】 */
    @Excel(name = "shiro加密秘钥【资金】")
    private String paypasword;

    /** 秘钥加密盐值【加密算法】 */
    @Excel(name = "秘钥加密盐值【加密算法】")
    private String salt;

    /** 用户类型,商户1 码商2 */
    @Excel(name = "用户类型,商户1 码商2")
    private Integer usertype;

    /** 当前用户总开关 1开启0关闭【码商商户后台控制,该咋宏泰只能在后台显示】 */
    @Excel(name = "当前用户总开关 1开启0关闭【码商商户后台控制,该咋宏泰只能在后台显示】")
    private Integer switchs;

    /** 组群备注 */
    @Excel(name = "组群备注")
    private String usernode;

    /** 邮箱【修改账号秘钥邮件发送地址】 */
    @Excel(name = "邮箱【修改账号秘钥邮件发送地址】")
    private String email;

    /** 代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】 */
    @Excel(name = "代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】")
    private String agent;

    /** 是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】 */
    @Excel(name = "是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】")
    private String isagent;

    /** 信用等级为默认为100,掉单一次,或者出现故障一次减分0.001分,可人为加分 */
    @Excel(name = "信用等级为默认为100,掉单一次,或者出现故障一次减分0.001分,可人为加分")
    private Double credit;

    /** 是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以交易】 */
    @Excel(name = "是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以交易】")
    private Integer receiveorderstate;

    /** 是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以代付】 */
    @Excel(name = "是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以代付】")
    private Integer remitorderstate;

    /** QQ联系方式 */
    @Excel(name = "QQ联系方式")
    private String qq;

    /** 小飞机 */
    @Excel(name = "小飞机")
    private String telegram;

    /** skype */
    @Excel(name = "skype")
    private String skype;

    /** 最后一次数据修改时间 */
    @Excel(name = "最后一次数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    /** 数据是否可用:1数据可用2数据无用 */
    @Excel(name = "数据是否可用:1数据可用2数据无用")
    private Integer status;

    /** 预留，添加业务使用 */
    @Excel(name = "预留，添加业务使用")
    private String retain1;

    /** 预留，添加业务使用 */
    @Excel(name = "预留，添加业务使用")
    private String retain2;

    /** 预留，添加业务使用 */
    @Excel(name = "预留，添加业务使用")
    private String retain3;

    /** 预留，添加业务使用 */
    @Excel(name = "预留，添加业务使用")
    private String retain4;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setPaypasword(String paypasword) 
    {
        this.paypasword = paypasword;
    }

    public String getPaypasword() 
    {
        return paypasword;
    }
    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }
    public void setUsertype(Integer usertype) 
    {
        this.usertype = usertype;
    }

    public Integer getUsertype() 
    {
        return usertype;
    }
    public void setSwitchs(Integer switchs) 
    {
        this.switchs = switchs;
    }

    public Integer getSwitchs() 
    {
        return switchs;
    }
    public void setUsernode(String usernode) 
    {
        this.usernode = usernode;
    }

    public String getUsernode() 
    {
        return usernode;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAgent(String agent) 
    {
        this.agent = agent;
    }

    public String getAgent() 
    {
        return agent;
    }
    public void setIsagent(String isagent) 
    {
        this.isagent = isagent;
    }

    public String getIsagent() 
    {
        return isagent;
    }
    public void setCredit(Double credit) 
    {
        this.credit = credit;
    }

    public Double getCredit() 
    {
        return credit;
    }
    public void setReceiveorderstate(Integer receiveorderstate) 
    {
        this.receiveorderstate = receiveorderstate;
    }

    public Integer getReceiveorderstate() 
    {
        return receiveorderstate;
    }
    public void setRemitorderstate(Integer remitorderstate) 
    {
        this.remitorderstate = remitorderstate;
    }

    public Integer getRemitorderstate() 
    {
        return remitorderstate;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }
    public void setTelegram(String telegram) 
    {
        this.telegram = telegram;
    }

    public String getTelegram() 
    {
        return telegram;
    }
    public void setSkype(String skype) 
    {
        this.skype = skype;
    }

    public String getSkype() 
    {
        return skype;
    }
    public void setSubmittime(Date submittime) 
    {
        this.submittime = submittime;
    }

    public Date getSubmittime() 
    {
        return submittime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setRetain1(String retain1) 
    {
        this.retain1 = retain1;
    }

    public String getRetain1() 
    {
        return retain1;
    }
    public void setRetain2(String retain2) 
    {
        this.retain2 = retain2;
    }

    public String getRetain2() 
    {
        return retain2;
    }
    public void setRetain3(String retain3) 
    {
        this.retain3 = retain3;
    }

    public String getRetain3() 
    {
        return retain3;
    }
    public void setRetain4(String retain4) 
    {
        this.retain4 = retain4;
    }

    public String getRetain4() 
    {
        return retain4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userid", getUserid())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("paypasword", getPaypasword())
            .append("salt", getSalt())
            .append("usertype", getUsertype())
            .append("switchs", getSwitchs())
            .append("usernode", getUsernode())
            .append("email", getEmail())
            .append("agent", getAgent())
            .append("isagent", getIsagent())
            .append("credit", getCredit())
            .append("receiveorderstate", getReceiveorderstate())
            .append("remitorderstate", getRemitorderstate())
            .append("qq", getQq())
            .append("telegram", getTelegram())
            .append("skype", getSkype())
            .append("createtime", getCreateTime())
            .append("submittime", getSubmittime())
            .append("status", getStatus())
            .append("retain1", getRetain1())
            .append("retain2", getRetain2())
            .append("retain3", getRetain3())
            .append("retain4", getRetain4())
            .toString();
    }
}
