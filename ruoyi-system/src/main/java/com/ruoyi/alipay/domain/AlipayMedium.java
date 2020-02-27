package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 收款媒介列对象 alipay_medium
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public class AlipayMedium extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 收款媒介id(本地编号) */
    private String mediumid;

    /** 收款媒介登录账号 */
    private String mediumnumber;

    /** 收款媒介持有人 */
    @Excel(name = "收款媒介持有人")
    private String mediumholder;

    /** 备注 */
    @Excel(name = "备注")
    private String mediumnote;

    /** 收款媒介绑定手机号 */
    @Excel(name = "收款媒介绑定手机号")
    private String mediumphone;

    /** 收款媒介所属商户 */
    @Excel(name = "收款媒介所属商户")
    private String qrcodeid;

    /** 收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放) */
    @Excel(name = "收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放)")
    private String code;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 是否逻辑删除：1删除2可用 */
    @Excel(name = "是否逻辑删除：1删除2可用")
    private String isdeal;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMediumid(String mediumid) 
    {
        this.mediumid = mediumid;
    }

    public String getMediumid() 
    {
        return mediumid;
    }
    public void setMediumnumber(String mediumnumber) 
    {
        this.mediumnumber = mediumnumber;
    }

    public String getMediumnumber() 
    {
        return mediumnumber;
    }
    public void setMediumholder(String mediumholder) 
    {
        this.mediumholder = mediumholder;
    }

    public String getMediumholder() 
    {
        return mediumholder;
    }
    public void setMediumnote(String mediumnote) 
    {
        this.mediumnote = mediumnote;
    }

    public String getMediumnote() 
    {
        return mediumnote;
    }
    public void setMediumphone(String mediumphone) 
    {
        this.mediumphone = mediumphone;
    }

    public String getMediumphone() 
    {
        return mediumphone;
    }
    public void setQrcodeid(String qrcodeid) 
    {
        this.qrcodeid = qrcodeid;
    }

    public String getQrcodeid() 
    {
        return qrcodeid;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
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
    public void setIsdeal(String isdeal) 
    {
        this.isdeal = isdeal;
    }

    public String getIsdeal() 
    {
        return isdeal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mediumid", getMediumid())
            .append("mediumnumber", getMediumnumber())
            .append("mediumholder", getMediumholder())
            .append("mediumnote", getMediumnote())
            .append("mediumphone", getMediumphone())
            .append("qrcodeid", getQrcodeid())
            .append("code", getCode())
            .append("createtime", getCreateTime())
            .append("submittime", getSubmittime())
            .append("status", getStatus())
            .append("isdeal", getIsdeal())
            .toString();
    }
}
