package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 银行卡列表对象 alipay_bank_list
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public class AlipayBankListEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 银行卡(本地编号) */
    private String bankcardId;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String bankcardAccount;

    /** 银行卡开户人 */
    @Excel(name = "银行卡开户人")
    private String accountHolder;

    /** 银行卡开户行 */
    @Excel(name = "银行卡开户行")
    private String openAccountBank;

    /** 银行英文简写 */
    @Excel(name = "银行英文简写")
    private String bankType;

    /** 银行卡类型:R收款W出款 */
    @Excel(name = "银行卡类型:R收款W出款")
    private String bankcode;

    /** 系统状态 1黑卡  2 可正常使用 */
    @Excel(name = "系统状态 1黑卡  2 可正常使用")
    private Integer sysTYpe;

    /** 关联账户 */
    @Excel(name = "关联账户")
    private String account;

    /** 银行卡手机号 */
    @Excel(name = "银行卡手机号")
    private String phone;

    /** 当日交易限制金额 */
    @Excel(name = "当日交易限制金额")
    private Double limitAmount;

    /** 银行卡余额 */
    @Excel(name = "银行卡余额")
    private Double bankAmount;

    /** 系统的卡1，码商的卡2，商户的卡3 */
    @Excel(name = "系统的卡1，码商的卡2，商户的卡3")
    private Integer cardType;

    /** 备注 */
    @Excel(name = "备注")
    private String qrcodeNote;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 是否逻辑删除：1删除2可用 */
    @Excel(name = "是否逻辑删除：1删除2可用")
    private Integer isDeal;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBankcardId(String bankcardId) 
    {
        this.bankcardId = bankcardId;
    }

    public String getBankcardId() 
    {
        return bankcardId;
    }
    public void setBankcardAccount(String bankcardAccount) 
    {
        this.bankcardAccount = bankcardAccount;
    }

    public String getBankcardAccount() 
    {
        return bankcardAccount;
    }
    public void setAccountHolder(String accountHolder) 
    {
        this.accountHolder = accountHolder;
    }

    public String getAccountHolder() 
    {
        return accountHolder;
    }
    public void setOpenAccountBank(String openAccountBank) 
    {
        this.openAccountBank = openAccountBank;
    }

    public String getOpenAccountBank() 
    {
        return openAccountBank;
    }
    public void setBankType(String bankType) 
    {
        this.bankType = bankType;
    }

    public String getBankType() 
    {
        return bankType;
    }
    public void setBankcode(String bankcode) 
    {
        this.bankcode = bankcode;
    }

    public String getBankcode() 
    {
        return bankcode;
    }
    public void setSysTYpe(Integer sysTYpe) 
    {
        this.sysTYpe = sysTYpe;
    }

    public Integer getSysTYpe() 
    {
        return sysTYpe;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setLimitAmount(Double limitAmount) 
    {
        this.limitAmount = limitAmount;
    }

    public Double getLimitAmount() 
    {
        return limitAmount;
    }
    public void setBankAmount(Double bankAmount) 
    {
        this.bankAmount = bankAmount;
    }

    public Double getBankAmount() 
    {
        return bankAmount;
    }
    public void setCardType(Integer cardType) 
    {
        this.cardType = cardType;
    }

    public Integer getCardType() 
    {
        return cardType;
    }
    public void setQrcodeNote(String qrcodeNote) 
    {
        this.qrcodeNote = qrcodeNote;
    }

    public String getQrcodeNote() 
    {
        return qrcodeNote;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsDeal(Integer isDeal) 
    {
        this.isDeal = isDeal;
    }

    public Integer getIsDeal() 
    {
        return isDeal;
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
            .append("bankcardId", getBankcardId())
            .append("bankcardAccount", getBankcardAccount())
            .append("accountHolder", getAccountHolder())
            .append("openAccountBank", getOpenAccountBank())
            .append("bankType", getBankType())
            .append("bankcode", getBankcode())
            .append("sysTYpe", getSysTYpe())
            .append("account", getAccount())
            .append("phone", getPhone())
            .append("limitAmount", getLimitAmount())
            .append("bankAmount", getBankAmount())
            .append("cardType", getCardType())
            .append("qrcodeNote", getQrcodeNote())
            .append("status", getStatus())
            .append("isDeal", getIsDeal())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .toString();
    }
}
