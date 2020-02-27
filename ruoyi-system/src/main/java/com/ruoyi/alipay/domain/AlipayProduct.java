package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 用户产品对象 alipay_product
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public class AlipayProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 系统id【产品系统id】 */
    private String productid;

    /** 产品code */
    private String productcode;

    /** 产品名 */
    private String productname;

    /** 产品备注 */
    private String describe;

    /** 最后一次数据修改时间 */
    private Date submittime;

    /** 数据是否可用:1数据可用2数据无用 */
    private Integer status;

    /** 预留，添加业务使用 */
    private String retain1;

    /** 预留，添加业务使用 */
    private String retain2;

    /** 预留，添加业务使用 */
    private String retain3;

    /** 预留，添加业务使用 */
    private String retain4;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductid(String productid) 
    {
        this.productid = productid;
    }

    public String getProductid() 
    {
        return productid;
    }
    public void setProductcode(String productcode) 
    {
        this.productcode = productcode;
    }

    public String getProductcode() 
    {
        return productcode;
    }
    public void setProductname(String productname) 
    {
        this.productname = productname;
    }

    public String getProductname() 
    {
        return productname;
    }
    public void setDescribe(String describe) 
    {
        this.describe = describe;
    }

    public String getDescribe() 
    {
        return describe;
    }
    public void setSubmittime(Date submittime) 
    {
        this.submittime = submittime;
    }

    public Date getSubmittime() 
    {
        return submittime;
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
    public void setRetain3(String retain3) 
    {
        this.retain3 = retain3;
    }

    public String getRetain3() 
    {
        return retain3;
    }
    public void setRetain4(String retain4) 
    {
        this.retain4 = retain4;
    }

    public String getRetain4() 
    {
        return retain4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productid", getProductid())
            .append("productcode", getProductcode())
            .append("productname", getProductname())
            .append("describe", getDescribe())
            .append("createtime", getCreateTime())
            .append("submittime", getSubmittime())
            .append("status", getStatus())
            .append("retain1", getRetain1())
            .append("retain2", getRetain2())
            .append("retain3", getRetain3())
            .append("retain4", getRetain4())
            .toString();
    }
}
