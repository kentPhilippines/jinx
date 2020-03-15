package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户详情对象 t_merchant_info
 * 
 * @author kiwi
 * @date 2020-03-14
 */
public class MerchantInfoEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据ID */
    private Long id;

    /** 商户ID(登陆账号) */
    @Excel(name = "商户ID")
    private String merchantId;

    /** 商户名称 */
    @Excel(name = "商户名称")
    private String merchantName;

    /** 商家私钥 */
    @Excel(name = "商户私钥")
    private String privateKey;

    /** 商家公钥 */
    @Excel(name = "商户公钥")
    private String publicKey;

    /** 状态 */
    @Excel(name = "状态")
    private Integer switches;

    /** 删除标记 */
    private Integer delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMerchantId(String merchantId) 
    {
        this.merchantId = merchantId;
    }

    public String getMerchantId() 
    {
        return merchantId;
    }
    public void setMerchantName(String merchantName) 
    {
        this.merchantName = merchantName;
    }

    public String getMerchantName() 
    {
        return merchantName;
    }
    public void setPrivateKey(String privateKey) 
    {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() 
    {
        return privateKey;
    }
    public void setPublicKey(String publicKey) 
    {
        this.publicKey = publicKey;
    }

    public String getPublicKey() 
    {
        return publicKey;
    }
    public void setSwitches(Integer switches) 
    {
        this.switches = switches;
    }

    public Integer getSwitches() 
    {
        return switches;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantId", getMerchantId())
            .append("merchantName", getMerchantName())
            .append("privateKey", getPrivateKey())
            .append("publicKey", getPublicKey())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("switches", getSwitches())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}
