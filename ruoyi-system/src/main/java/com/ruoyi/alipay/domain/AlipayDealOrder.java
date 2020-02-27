package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 交易订单对象 alipay_deal_order
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public class AlipayDealOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderid;

    /** 关联订单号 */
    @Excel(name = "关联订单号")
    private String associatedid;

    /** 订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理 */
    @Excel(name = "订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理")
    private Integer orderstatus;

    /** 订单交易金额 */
    @Excel(name = "订单交易金额")
    private Double dealamount;

    /** 订单交易手续费 */
    @Excel(name = "订单交易手续费")
    private Double dealfee;

    /** 实际到账金额 */
    @Excel(name = "实际到账金额")
    private Double actualamount;

    /** 订单类型:1交易,2系统加款 */
    @Excel(name = "订单类型:1交易,2系统加款")
    private String ordertype;

    /** 订单关联商户账号 */
    @Excel(name = "订单关联商户账号")
    private String orderaccount;

    /** 关联码商账户 */
    @Excel(name = "关联码商账户")
    private String orderqruser;

    /** 关联二维码 */
    @Excel(name = "关联二维码")
    private String orderqr;

    /** 外部订单号(下游商户请求参数,用户数据回调) */
    @Excel(name = "外部订单号(下游商户请求参数,用户数据回调)")
    private String externalorderid;

    /** 订单生成IP(客户端ip或者是下游商户id) */
    @Excel(name = "订单生成IP(客户端ip或者是下游商户id)")
    private String generationip;

    /** 交易备注 */
    @Excel(name = "交易备注")
    private String dealdescribe;

    /** 订单异步回调地址 */
    @Excel(name = "订单异步回调地址")
    private String notify;

    /** 订单同步回调地址 */
    @Excel(name = "订单同步回调地址")
    private String back;

    /** 是否發送通知 //  YES 已發送    NO 未發送 */
    @Excel(name = "是否發送通知 //  YES 已發送    NO 未發送")
    private String isnotify;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    /** 使用费率id */
    @Excel(name = "使用费率id")
    private Integer feeid;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 备用字段添加业务使用 */
    @Excel(name = "备用字段添加业务使用")
    private String retain1;

    /** 备用字段添加业务使用 */
    @Excel(name = "备用字段添加业务使用")
    private String retain2;

    /** 备用字段添加业务使用 */
    @Excel(name = "备用字段添加业务使用")
    private String retain3;

    /** 备用字段添加业务使用 */
    @Excel(name = "备用字段添加业务使用")
    private String retain4;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderid(String orderid) 
    {
        this.orderid = orderid;
    }

    public String getOrderid() 
    {
        return orderid;
    }
    public void setAssociatedid(String associatedid) 
    {
        this.associatedid = associatedid;
    }

    public String getAssociatedid() 
    {
        return associatedid;
    }
    public void setOrderstatus(Integer orderstatus) 
    {
        this.orderstatus = orderstatus;
    }

    public Integer getOrderstatus() 
    {
        return orderstatus;
    }
    public void setDealamount(Double dealamount) 
    {
        this.dealamount = dealamount;
    }

    public Double getDealamount() 
    {
        return dealamount;
    }
    public void setDealfee(Double dealfee) 
    {
        this.dealfee = dealfee;
    }

    public Double getDealfee() 
    {
        return dealfee;
    }
    public void setActualamount(Double actualamount) 
    {
        this.actualamount = actualamount;
    }

    public Double getActualamount() 
    {
        return actualamount;
    }
    public void setOrdertype(String ordertype) 
    {
        this.ordertype = ordertype;
    }

    public String getOrdertype() 
    {
        return ordertype;
    }
    public void setOrderaccount(String orderaccount) 
    {
        this.orderaccount = orderaccount;
    }

    public String getOrderaccount() 
    {
        return orderaccount;
    }
    public void setOrderqruser(String orderqruser) 
    {
        this.orderqruser = orderqruser;
    }

    public String getOrderqruser() 
    {
        return orderqruser;
    }
    public void setOrderqr(String orderqr) 
    {
        this.orderqr = orderqr;
    }

    public String getOrderqr() 
    {
        return orderqr;
    }
    public void setExternalorderid(String externalorderid) 
    {
        this.externalorderid = externalorderid;
    }

    public String getExternalorderid() 
    {
        return externalorderid;
    }
    public void setGenerationip(String generationip) 
    {
        this.generationip = generationip;
    }

    public String getGenerationip() 
    {
        return generationip;
    }
    public void setDealdescribe(String dealdescribe) 
    {
        this.dealdescribe = dealdescribe;
    }

    public String getDealdescribe() 
    {
        return dealdescribe;
    }
    public void setNotify(String notify) 
    {
        this.notify = notify;
    }

    public String getNotify() 
    {
        return notify;
    }
    public void setBack(String back) 
    {
        this.back = back;
    }

    public String getBack() 
    {
        return back;
    }
    public void setIsnotify(String isnotify) 
    {
        this.isnotify = isnotify;
    }

    public String getIsnotify() 
    {
        return isnotify;
    }
    public void setSubmittime(Date submittime) 
    {
        this.submittime = submittime;
    }

    public Date getSubmittime() 
    {
        return submittime;
    }
    public void setFeeid(Integer feeid) 
    {
        this.feeid = feeid;
    }

    public Integer getFeeid() 
    {
        return feeid;
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
            .append("orderid", getOrderid())
            .append("associatedid", getAssociatedid())
            .append("orderstatus", getOrderstatus())
            .append("dealamount", getDealamount())
            .append("dealfee", getDealfee())
            .append("actualamount", getActualamount())
            .append("ordertype", getOrdertype())
            .append("orderaccount", getOrderaccount())
            .append("orderqruser", getOrderqruser())
            .append("orderqr", getOrderqr())
            .append("externalorderid", getExternalorderid())
            .append("generationip", getGenerationip())
            .append("dealdescribe", getDealdescribe())
            .append("notify", getNotify())
            .append("back", getBack())
            .append("isnotify", getIsnotify())
            .append("createtime", getCreateTime())
            .append("submittime", getSubmittime())
            .append("feeid", getFeeid())
            .append("status", getStatus())
            .append("retain1", getRetain1())
            .append("retain2", getRetain2())
            .append("retain3", getRetain3())
            .append("retain4", getRetain4())
            .toString();
    }
}
