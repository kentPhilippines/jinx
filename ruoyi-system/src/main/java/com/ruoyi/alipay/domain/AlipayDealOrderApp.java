package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 商户订单登记对象 alipay_deal_order_app
 * 
 * @author otc
 * @date 2020-02-27
 */
public class AlipayDealOrderApp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 订单号 */
    private String orderid;

    /** 订单类型:1交易,5代付 */
    @Excel(name = "订单类型:1交易,5代付")
    private Integer ordertype;

    /** 订单关联商户账号 */
    @Excel(name = "订单关联商户账号")
    private String orderaccount;

    /** 订单状态:1处理中2成功3未收到回调4失败5超时6订单申述7人工处理 */
    @Excel(name = "订单状态:1处理中2成功3未收到回调4失败5超时6订单申述7人工处理")
    private Integer orderstatus;

    /** 交易备注 */
    @Excel(name = "交易备注")
    private String dealdescribe;

    /** 订单金额(原始金额) */
    @Excel(name = "订单金额(原始金额)")
    private Double orderamount;

    /** 订单生成IP(源头ip) */
    @Excel(name = "订单生成IP(源头ip)")
    private String orderip;

    /** 交易外部订单号 */
    @Excel(name = "交易外部订单号")
    private String apporderid;

    /** 使用费率id */
    @Excel(name = "使用费率id")
    private Integer feeid;

    /** 订单异步回调地址 */
    @Excel(name = "订单异步回调地址")
    private String notify;

    /** 订单同步回调地址 */
    @Excel(name = "订单同步回调地址")
    private String back;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    /** 数据提交系统 */
    @Excel(name = "数据提交系统")
    private String submitsystem;

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
    public void setOrdertype(Integer ordertype) 
    {
        this.ordertype = ordertype;
    }

    public Integer getOrdertype() 
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
    public void setOrderstatus(Integer orderstatus) 
    {
        this.orderstatus = orderstatus;
    }

    public Integer getOrderstatus() 
    {
        return orderstatus;
    }
    public void setDealdescribe(String dealdescribe) 
    {
        this.dealdescribe = dealdescribe;
    }

    public String getDealdescribe() 
    {
        return dealdescribe;
    }
    public void setOrderamount(Double orderamount) 
    {
        this.orderamount = orderamount;
    }

    public Double getOrderamount() 
    {
        return orderamount;
    }
    public void setOrderip(String orderip) 
    {
        this.orderip = orderip;
    }

    public String getOrderip() 
    {
        return orderip;
    }
    public void setApporderid(String apporderid) 
    {
        this.apporderid = apporderid;
    }

    public String getApporderid() 
    {
        return apporderid;
    }
    public void setFeeid(Integer feeid) 
    {
        this.feeid = feeid;
    }

    public Integer getFeeid() 
    {
        return feeid;
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
    public void setSubmittime(Date submittime) 
    {
        this.submittime = submittime;
    }

    public Date getSubmittime() 
    {
        return submittime;
    }
    public void setSubmitsystem(String submitsystem) 
    {
        this.submitsystem = submitsystem;
    }

    public String getSubmitsystem() 
    {
        return submitsystem;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderid", getOrderid())
            .append("ordertype", getOrdertype())
            .append("orderaccount", getOrderaccount())
            .append("orderstatus", getOrderstatus())
            .append("dealdescribe", getDealdescribe())
            .append("orderamount", getOrderamount())
            .append("orderip", getOrderip())
            .append("apporderid", getApporderid())
            .append("createtime", getCreateTime())
            .append("feeid", getFeeid())
            .append("notify", getNotify())
            .append("back", getBack())
            .append("submittime", getSubmittime())
            .append("submitsystem", getSubmitsystem())
            .append("status", getStatus())
            .append("retain1", getRetain1())
            .append("retain2", getRetain2())
            .append("retain3", getRetain3())
            .toString();
    }
}
