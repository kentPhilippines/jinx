package com.ruoyi.alipay.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 收款媒介列对象 alipay_medium
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public class AlipayMediumEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 收款媒介id(本地编号) */
    private String mediumId;

    /** 收款媒介登录账号 */
    private String mediumNumber;

    /** 收款媒介持有人 */
    @Excel(name = "收款媒介持有人")
    private String mediumHolder;

    /** 备注 */
    @Excel(name = "备注")
    private String mediumNote;

    /** 收款媒介绑定手机号 */
    @Excel(name = "收款媒介绑定手机号")
    private String mediumPhone;

    /** 收款媒介所属商户 */
    @Excel(name = "收款媒介所属商户")
    private String qrcodeId;

    /** 收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放) */
    @Excel(name = "收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放)")
    private String code;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 状态:1可使用；0不可使用
     */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /**
     * 是否逻辑删除：1删除2可用
     */
    @Excel(name = "是否逻辑删除：1删除2可用")
    private String isDeal;


    private String bankcode;                    //R 为 入款    W  为出款
    private String account;                     //银行账户    如中国工商银行
    private String mountNow;                    // 当前媒介实际金额
    private String mountSystem;                 //当前媒介系统金额
    private String mountLimit;                  //当前媒介限制金额   系统默认一万
    private String attr;                        //收款媒介供应链标识
    private String notfiyMask;                  //回调标识


    public String getNotfiyMask() {
        return notfiyMask;
    }

    public void setNotfiyMask(String notfiyMask) {
        this.notfiyMask = notfiyMask;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getMountLimit() {
        return mountLimit;
    }

    public void setMountLimit(String mountLimit) {
        this.mountLimit = mountLimit;
    }

    public String getMountSystem() {
        return mountSystem;
    }

    public void setMountSystem(String mountSystem) {
        this.mountSystem = mountSystem;
    }

    public String getMountNow() {
        return mountNow;
    }

    public void setMountNow(String mountNow) {
        this.mountNow = mountNow;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMediumId(String mediumId) {
        this.mediumId = mediumId;
    }

    public String getMediumId() 
    {
        return mediumId;
    }
    public void setMediumNumber(String mediumNumber) 
    {
        this.mediumNumber = mediumNumber;
    }

    public String getMediumNumber() 
    {
        return mediumNumber;
    }
    public void setMediumHolder(String mediumHolder) 
    {
        this.mediumHolder = mediumHolder;
    }

    public String getMediumHolder() 
    {
        return mediumHolder;
    }
    public void setMediumNote(String mediumNote) 
    {
        this.mediumNote = mediumNote;
    }

    public String getMediumNote() 
    {
        return mediumNote;
    }
    public void setMediumPhone(String mediumPhone) 
    {
        this.mediumPhone = mediumPhone;
    }

    public String getMediumPhone() 
    {
        return mediumPhone;
    }
    public void setQrcodeId(String qrcodeId) 
    {
        this.qrcodeId = qrcodeId;
    }

    public String getQrcodeId() 
    {
        return qrcodeId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
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
    public void setIsDeal(String isDeal) 
    {
        this.isDeal = isDeal;
    }

    public String getIsDeal() 
    {
        return isDeal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mediumId", getMediumId())
            .append("mediumNumber", getMediumNumber())
            .append("mediumHolder", getMediumHolder())
            .append("mediumNote", getMediumNote())
            .append("mediumPhone", getMediumPhone())
            .append("qrcodeId", getQrcodeId())
            .append("code", getCode())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("isDeal", getIsDeal())
            .toString();
    }
}
