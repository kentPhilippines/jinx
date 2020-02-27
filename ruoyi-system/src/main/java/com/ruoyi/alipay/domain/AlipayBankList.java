package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 银行卡列对象 alipay_bank_list
 * 
 * @author ruoyi
 * @date 2020-02-26
 */
public class AlipayBankList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 银行卡(本地编号) */
    private String bankcardid;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String bankcardaccount;

    /** 银行卡开户人 */
    @Excel(name = "银行卡开户人")
    private String accountholder;

    /** 银行卡开户行 */
    @Excel(name = "银行卡开户行")
    private String openaccountbank;

    /** 银行英文简写 */
    @Excel(name = "银行英文简写")
    private String banktype;

    /** 银行卡类型:R收款W出款 */
    @Excel(name = "银行卡类型:R收款W出款")
    private String bankcode;

    /** 系统状态 1黑卡  2 可正常使用  */
    @Excel(name = "系统状态 1黑卡  2 可正常使用 ")
    private Integer systype;

    /** 关联账户 */
    @Excel(name = "关联账户")
    private String account;

    /** 银行卡手机号 */
    @Excel(name = "银行卡手机号")
    private String phone;

    /** 当日交易限制金额 */
    @Excel(name = "当日交易限制金额")
    private Double limitamount;

    /** 银行卡余额 */
    @Excel(name = "银行卡余额")
    private Double bankamount;

    /** 系统的卡1，码商的卡2，商户的卡3 */
    @Excel(name = "系统的卡1，码商的卡2，商户的卡3")
    private Integer cardtype;

    /** 备注 */
    @Excel(name = "备注")
    private String qrcodenote;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 是否逻辑删除：1删除2可用 */
    @Excel(name = "是否逻辑删除：1删除2可用")
    private Integer isdeal;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBankcardid(String bankcardid) 
    {
        this.bankcardid = bankcardid;
    }

    public String getBankcardid() 
    {
        return bankcardid;
    }
    public void setBankcardaccount(String bankcardaccount) 
    {
        this.bankcardaccount = bankcardaccount;
    }

    public String getBankcardaccount() 
    {
        return bankcardaccount;
    }
    public void setAccountholder(String accountholder) 
    {
        this.accountholder = accountholder;
    }

    public String getAccountholder() 
    {
        return accountholder;
    }
    public void setOpenaccountbank(String openaccountbank) 
    {
        this.openaccountbank = openaccountbank;
    }

    public String getOpenaccountbank() 
    {
        return openaccountbank;
    }
    public void setBanktype(String banktype) 
    {
        this.banktype = banktype;
    }

    public String getBanktype() 
    {
        return banktype;
    }
    public void setBankcode(String bankcode) 
    {
        this.bankcode = bankcode;
    }

    public String getBankcode() 
    {
        return bankcode;
    }
    public void setSystype(Integer systype) 
    {
        this.systype = systype;
    }

    public Integer getSystype() 
    {
        return systype;
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
    public void setLimitamount(Double limitamount) 
    {
        this.limitamount = limitamount;
    }

    public Double getLimitamount() 
    {
        return limitamount;
    }
    public void setBankamount(Double bankamount) 
    {
        this.bankamount = bankamount;
    }

    public Double getBankamount() 
    {
        return bankamount;
    }
    public void setCardtype(Integer cardtype) 
    {
        this.cardtype = cardtype;
    }

    public Integer getCardtype() 
    {
        return cardtype;
    }
    public void setQrcodenote(String qrcodenote) 
    {
        this.qrcodenote = qrcodenote;
    }

    public String getQrcodenote() 
    {
        return qrcodenote;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsdeal(Integer isdeal) 
    {
        this.isdeal = isdeal;
    }

    public Integer getIsdeal() 
    {
        return isdeal;
    }
    public void setSubmittime(Date submittime) 
    {
        this.submittime = submittime;
    }

    public Date getSubmittime() 
    {
        return submittime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bankcardid", getBankcardid())
            .append("bankcardaccount", getBankcardaccount())
            .append("accountholder", getAccountholder())
            .append("openaccountbank", getOpenaccountbank())
            .append("banktype", getBanktype())
            .append("bankcode", getBankcode())
            .append("systype", getSystype())
            .append("account", getAccount())
            .append("phone", getPhone())
            .append("limitamount", getLimitamount())
            .append("bankamount", getBankamount())
            .append("cardtype", getCardtype())
            .append("qrcodenote", getQrcodenote())
            .append("status", getStatus())
            .append("isdeal", getIsdeal())
            .append("createtime", getCreateTime())
            .append("submittime", getSubmittime())
            .toString();
    }

}
