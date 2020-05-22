package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 渠道费率对象 alipay_chanel_fee
 *
 * @author ruoyi
 * @date 2020-05-15
 */
public class AlipayChanelFee extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    private Long id;

    /**
     * 渠道ID
     */
    @Excel(name = "渠道ID")
    private String channelId;

    /**
     * 产品id
     */
    @Excel(name = "产品id")
    private String productId;

    /**
     * 实体类对应关系
     */
    @Excel(name = "实体类对应关系")
    private String impl;
    private String channelRFee;
    private String channelDFee;

    public void setChannelRFee(String channelRFee) {
        this.channelRFee = channelRFee;
    }

    public String getChannelRFee() {
        return this.channelRFee;
    }

    ;

    public void setChannelDFee(String channelDFee) {
        this.channelDFee = channelDFee;
    }

    public String getChannelDFee() {
        return this.channelDFee;
    }

    /**
     * null
     */
    @Excel(name = "null", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 1为可用  2为不可用
     */
    @Excel(name = "1为可用  2为不可用")
    private Integer status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setImpl(String impl) {
        this.impl = impl;
    }

    public String getImpl() {
        return impl;
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
                .append("channelId", getChannelId())
                .append("productId", getProductId())
                .append("impl", getImpl())
                .append("createTime", getCreateTime())
                .append("submitTime", getSubmitTime())
                .append("status", getStatus())
                .toString();
    }
}
