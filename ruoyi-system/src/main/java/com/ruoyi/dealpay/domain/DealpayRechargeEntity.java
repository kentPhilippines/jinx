package com.ruoyi.dealpay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 充值记录对象 dealpay_recharge
 * 
 * @author k
 * @date 2020-04-03
 */
public class DealpayRechargeEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id(主键索引) */
    private Long id;

    /** 充值订单id订单号长度最多为32位(全文唯一索引) */
    @Excel(name = "充值订单id订单号长度最多为32位(全文唯一索引)")
    private String orderId;

    /** 充值关联用户 */
    @Excel(name = "充值关联用户")
    private String userId;

    /** 充值订单类型   1 商户充值     2 卡商充值 */
    @Excel(name = "充值订单类型   1 商户充值     2 卡商充值")
    private Integer rechargeType;

    /** 充值理由 */
    @Excel(name = "充值理由")
    private String chargeReason;

    /** 0预下单1处理中2成功3失败 */
    @Excel(name = "0预下单1处理中2成功3失败")
    private String orderStatus;

    /** 充值人姓名 */
    @Excel(name = "充值人姓名")
    private String depositor;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private Double amount;

    /** 手续费 */
    @Excel(name = "手续费")
    private Double fee;

    /** 真实到账金额 */
    @Excel(name = "真实到账金额")
    private Double actualAmount;

    /** 充值银行卡 */
    @Excel(name = "充值银行卡")
    private String chargeBankcard;

    /** 充值手机号 */
    @Excel(name = "充值手机号")
    private String phone;

    /** 充值回调地址 */
    @Excel(name = "充值回调地址")
    private String notfiy;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String chargeCard;

    /** 充值银行卡对应的账户人 */
    @Excel(name = "充值银行卡对应的账户人")
    private String chargePerson;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 1数据可用2数据无用 */
    @Excel(name = "1数据可用2数据无用")
    private Integer status;

    /** 备用字段添加业务使用 */
    @Excel(name = "备用字段添加业务使用")
    private String retain1;

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
    public void setRechargeType(Integer rechargeType) 
    {
        this.rechargeType = rechargeType;
    }

    public Integer getRechargeType() 
    {
        return rechargeType;
    }
    public void setChargeReason(String chargeReason) 
    {
        this.chargeReason = chargeReason;
    }

    public String getChargeReason() 
    {
        return chargeReason;
    }
    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }
    public void setDepositor(String depositor) 
    {
        this.depositor = depositor;
    }

    public String getDepositor() 
    {
        return depositor;
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
    public void setChargeBankcard(String chargeBankcard) 
    {
        this.chargeBankcard = chargeBankcard;
    }

    public String getChargeBankcard() 
    {
        return chargeBankcard;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setNotfiy(String notfiy) 
    {
        this.notfiy = notfiy;
    }

    public String getNotfiy() 
    {
        return notfiy;
    }
    public void setChargeCard(String chargeCard) 
    {
        this.chargeCard = chargeCard;
    }

    public String getChargeCard() 
    {
        return chargeCard;
    }
    public void setChargePerson(String chargePerson) 
    {
        this.chargePerson = chargePerson;
    }

    public String getChargePerson() 
    {
        return chargePerson;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("rechargeType", getRechargeType())
            .append("chargeReason", getChargeReason())
            .append("orderStatus", getOrderStatus())
            .append("depositor", getDepositor())
            .append("amount", getAmount())
            .append("fee", getFee())
            .append("actualAmount", getActualAmount())
            .append("chargeBankcard", getChargeBankcard())
            .append("phone", getPhone())
            .append("notfiy", getNotfiy())
            .append("chargeCard", getChargeCard())
            .append("chargePerson", getChargePerson())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("retain1", getRetain1())
            .toString();
    }
}
