package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 交易订单对象 admin_aplipay_deal_order
 * 
 * @author kiwi
 * @date 2020-04-15
 */
public class AdminAplipayDealOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据ID */
    private Long id;

    /** 平台订单 */
    @Excel(name = "平台订单")
    private String orderId;

    /** 关联订单 */
    @Excel(name = "关联订单")
    private String associatedId;

    /** 订单关联商户账号 */
    @Excel(name = "订单关联商户账号")
    private String orderAccount;

    /** 订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理 */
    @Excel(name = "订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理")
    private String orderStatus;

    /** 交易金额 */
    @Excel(name = "交易金额")
    private Double dealAmount;

    /** 手续费 */
    @Excel(name = "手续费")
    private Double dealFee;

    /** 到账金额 */
    @Excel(name = "到账金额")
    private Double actualAmount;

    /** 订单类型:1交易,2系统加款 */
    @Excel(name = "订单类型:1交易,2系统加款")
    private String orderType;

    /** 关联码商账户 */
    @Excel(name = "关联码商账户")
    private String orderQrUser;

    /** 关联二维码 */
    @Excel(name = "关联二维码")
    private String orderQr;

    /** 创建时间 */
    private Date ceateTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 费率 */
    @Excel(name = "费率")
    private Double fee;

    /** 订单交易产品类型 */
    @Excel(name = "订单交易产品类型")
    private String productType;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setAssociatedId(String associatedId) 
    {
        this.associatedId = associatedId;
    }

    public String getAssociatedId() 
    {
        return associatedId;
    }
    public void setOrderAccount(String orderAccount) 
    {
        this.orderAccount = orderAccount;
    }

    public String getOrderAccount() 
    {
        return orderAccount;
    }
    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }
    public void setDealAmount(Double dealAmount) 
    {
        this.dealAmount = dealAmount;
    }

    public Double getDealAmount() 
    {
        return dealAmount;
    }
    public void setDealFee(Double dealFee) 
    {
        this.dealFee = dealFee;
    }

    public Double getDealFee() 
    {
        return dealFee;
    }
    public void setActualAmount(Double actualAmount) 
    {
        this.actualAmount = actualAmount;
    }

    public Double getActualAmount() 
    {
        return actualAmount;
    }
    public void setOrderType(String orderType) 
    {
        this.orderType = orderType;
    }

    public String getOrderType() 
    {
        return orderType;
    }
    public void setOrderQrUser(String orderQrUser) 
    {
        this.orderQrUser = orderQrUser;
    }

    public String getOrderQrUser() 
    {
        return orderQrUser;
    }
    public void setOrderQr(String orderQr) 
    {
        this.orderQr = orderQr;
    }

    public String getOrderQr() 
    {
        return orderQr;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setFee(Double fee) 
    {
        this.fee = fee;
    }

    public Double getFee() 
    {
        return fee;
    }
    public void setProductType(String productType) 
    {
        this.productType = productType;
    }

    public String getProductType() 
    {
        return productType;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public Date getCeateTime() {
        return ceateTime;
    }

    public void setCeateTime(Date ceateTime) {
        this.ceateTime = ceateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("associatedId", getAssociatedId())
            .append("orderAccount", getOrderAccount())
            .append("orderStatus", getOrderStatus())
            .append("dealAmount", getDealAmount())
            .append("dealFee", getDealFee())
            .append("actualAmount", getActualAmount())
            .append("orderType", getOrderType())
            .append("orderQrUser", getOrderQrUser())
            .append("orderQr", getOrderQr())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .append("fee", getFee())
            .append("productType", getProductType())
            .append("status", getStatus())
            .toString();
    }
}
