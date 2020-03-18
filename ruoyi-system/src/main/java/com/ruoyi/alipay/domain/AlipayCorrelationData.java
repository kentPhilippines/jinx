package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 订单记录表对象 alipay_correlation_data
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public class AlipayCorrelationData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 媒介数据id */
    @Excel(name = "媒介数据id")
    private Long mediumId;

    /** 二维码数据id */
    @Excel(name = "二维码数据id")
    private String qrId;

    /** 流动金额 */
    @Excel(name = "流动金额")
    private Double amount;

    /** 订单状态  与交易订单 一样 */
    @Excel(name = "订单状态  与交易订单 一样")
    private Long orderStatus;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 数据最后一次修改时间 */
    @Excel(name = "数据最后一次修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

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
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setMediumId(Long mediumId) 
    {
        this.mediumId = mediumId;
    }

    public Long getMediumId() 
    {
        return mediumId;
    }
    public void setQrId(String qrId) 
    {
        this.qrId = qrId;
    }

    public String getQrId() 
    {
        return qrId;
    }
    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }

    public Double getAmount() 
    {
        return amount;
    }
    public void setOrderStatus(Long orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Long getOrderStatus() 
    {
        return orderStatus;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("mediumId", getMediumId())
            .append("qrId", getQrId())
            .append("amount", getAmount())
            .append("orderStatus", getOrderStatus())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .toString();
    }
}
