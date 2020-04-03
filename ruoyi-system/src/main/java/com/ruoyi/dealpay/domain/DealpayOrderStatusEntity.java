package com.ruoyi.dealpay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 双方确认订单状态对象 dealpay_order_status
 *
 * @author kiwi
 * @date 2020-04-03
 */
public class DealpayOrderStatusEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private Long id;

    /**
     * 需要确认的订单号
     */
    @Excel(name = "需要确认的订单号")
    private String orderId;

    /**
     * 下游确认的订单状态
     */
    @Excel(name = "下游确认的订单状态")
    private String orderStatusApp;

    /**
     * 卡商确认的订单状态
     */
    @Excel(name = "卡商确认的订单状态")
    private String orderStatusCard;

    /**
     * 数据修改时间
     */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 1数据可用2数据无用
     */
    @Excel(name = "1数据可用2数据无用")
    private Integer status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderStatusApp(String orderStatusApp) {
        this.orderStatusApp = orderStatusApp;
    }

    public String getOrderStatusApp() {
        return orderStatusApp;
    }

    public void setOrderStatusCard(String orderStatusCard) {
        this.orderStatusCard = orderStatusCard;
    }

    public String getOrderStatusCard() {
        return orderStatusCard;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("orderStatusApp", getOrderStatusApp())
                .append("orderStatusCard", getOrderStatusCard())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("status", getStatus())
                .toString();
    }
}
