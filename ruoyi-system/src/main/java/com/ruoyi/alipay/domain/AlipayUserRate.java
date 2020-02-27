package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 用户产品费率对象 alipay_user_rate
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public class AlipayUserRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 用户id【登录账号】 */
    @Excel(name = "用户id【登录账号】")
    private String userid;

    /** 用户类型,商户1 码商2 */
    @Excel(name = "用户类型,商户1 码商2")
    private Integer usertype;

    /** 当前用户总开关 1开启0关闭【码商商户后台控制,该数据只能在后台显示】 */
    @Excel(name = "当前用户总开关 1开启0关闭【码商商户后台控制,该数据只能在后台显示】")
    private Integer switchs;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String paytypr;

    /** 费率数值 */
    @Excel(name = "费率数值")
    private Double fee;

    /** 费率类型:1交易费率，2代付费率 */
    @Excel(name = "费率类型:1交易费率，2代付费率")
    private Integer feetype;

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
    public void setPaytypr(String paytypr) 
    {
        this.paytypr = paytypr;
    }

    public String getPaytypr() 
    {
        return paytypr;
    }
    public void setFee(Double fee) 
    {
        this.fee = fee;
    }

    public Double getFee() 
    {
        return fee;
    }
    public void setFeetype(Integer feetype) 
    {
        this.feetype = feetype;
    }

    public Integer getFeetype() 
    {
        return feetype;
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
            .append("usertype", getUsertype())
            .append("switchs", getSwitchs())
            .append("paytypr", getPaytypr())
            .append("fee", getFee())
            .append("feetype", getFeetype())
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
