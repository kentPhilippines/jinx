package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 会员提现记录对象 alipay_withdraw
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public class AlipayWithdrawEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id(主键索引) */
    private Long id;

    /** 会员提现单号 */
    @Excel(name = "会员提现单号")
    private String orderId;

    /** 会员id(唯一识别号)(index索引) */
    @Excel(name = "会员id(唯一识别号)(index索引)")
    private String userId;

    /** 商户提现1，码商提现2 */
    @Excel(name = "商户提现1，码商提现2")
    private String withdrawType;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String bankNo;

    /** 提现人姓名 */
    @Excel(name = "提现人姓名")
    private String accname;

    /** 0预下单1处理中2成功3失败 */
    @Excel(name = "0预下单1处理中2成功3失败")
    private String orderStatus;

    /** 开户行姓名 */
    @Excel(name = "开户行姓名")
    private String bankName;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private Double amount;

    /** 手续费 */
    @Excel(name = "手续费")
    private Double fee;

    /** 真实到账金额 */
    @Excel(name = "真实到账金额")
    private Double actualAmount;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 提现成功回调地址 */
    @Excel(name = "提现成功回调地址")
    private String notify;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 1数据可用2数据无用 */
    @Excel(name = "1数据可用2数据无用")
    private Integer status;

    /** 备用字段添加业务使用 */
    @Excel(name = "代付来源")
    private String retain1;

    /** 备用字段添加业务使用 */
    @Excel(name = "备用字段添加业务使用")
    private String retain2;

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
    public void setWithdrawType(String withdrawType) 
    {
        this.withdrawType = withdrawType;
    }

    public String getWithdrawType() 
    {
        return withdrawType;
    }
    public void setBankNo(String bankNo) 
    {
        this.bankNo = bankNo;
    }

    public String getBankNo() 
    {
        return bankNo;
    }
    public void setAccname(String accname) 
    {
        this.accname = accname;
    }

    public String getAccname() 
    {
        return accname;
    }
    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }

    public Double getAmount() 
    {
        return amount;
    }
    public void setFee(Double fee) 
    {
        this.fee = fee;
    }

    public Double getFee() 
    {
        return fee;
    }
    public void setActualAmount(Double actualAmount) 
    {
        this.actualAmount = actualAmount;
    }

    public Double getActualAmount() 
    {
        return actualAmount;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setNotify(String notify) 
    {
        this.notify = notify;
    }

    public String getNotify() 
    {
        return notify;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("withdrawType", getWithdrawType())
            .append("bankNo", getBankNo())
            .append("accname", getAccname())
            .append("orderStatus", getOrderStatus())
            .append("bankName", getBankName())
            .append("amount", getAmount())
            .append("fee", getFee())
            .append("actualAmount", getActualAmount())
            .append("mobile", getMobile())
            .append("notify", getNotify())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("retain1", getRetain1())
            .append("retain2", getRetain2())
            .toString();
    }
}
