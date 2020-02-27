package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 文件信息表对象 alipay_file_list
 * 
 * @author otc
 * @date 2020-02-27
 */
public class AlipayFileList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 二维码编号(本地编号) */
    @Excel(name = "二维码编号(本地编号)")
    private String fileid;

    /** 二维码持有人 */
    @Excel(name = "二维码持有人")
    private String fileholder;

    /** 备注 */
    @Excel(name = "备注")
    private String filenote;

    /** 是否为固码  1为固定码  2不为固定码 */
    @Excel(name = "是否为固码  1为固定码  2不为固定码")
    private String isfixation;

    /** 若为固码,固码金额 */
    @Excel(name = "若为固码,固码金额")
    private Double fixationamount;

    /** 收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放) */
    @Excel(name = "收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放)")
    private String code;

    /** 关联收款媒介ID */
    @Excel(name = "关联收款媒介ID")
    private String concealid;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 是否逻辑删除：1删除2可用 */
    @Excel(name = "是否逻辑删除：1删除2可用")
    private String isdeal;

    /** 图片是否以剪裁 Y 剪裁过   字段未空则未剪裁 */
    @Excel(name = "图片是否以剪裁 Y 剪裁过   字段未空则未剪裁")
    private String iscut;

    /** 预留，添加业务使用 */
    @Excel(name = "预留，添加业务使用")
    private String retain1;

    /** 预留，添加业务使用 */
    @Excel(name = "预留，添加业务使用")
    private String retain2;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFileid(String fileid) 
    {
        this.fileid = fileid;
    }

    public String getFileid() 
    {
        return fileid;
    }
    public void setFileholder(String fileholder) 
    {
        this.fileholder = fileholder;
    }

    public String getFileholder() 
    {
        return fileholder;
    }
    public void setFilenote(String filenote) 
    {
        this.filenote = filenote;
    }

    public String getFilenote() 
    {
        return filenote;
    }
    public void setIsfixation(String isfixation) 
    {
        this.isfixation = isfixation;
    }

    public String getIsfixation() 
    {
        return isfixation;
    }
    public void setFixationamount(Double fixationamount) 
    {
        this.fixationamount = fixationamount;
    }

    public Double getFixationamount() 
    {
        return fixationamount;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setConcealid(String concealid) 
    {
        this.concealid = concealid;
    }

    public String getConcealid() 
    {
        return concealid;
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
    public void setIsdeal(String isdeal) 
    {
        this.isdeal = isdeal;
    }

    public String getIsdeal() 
    {
        return isdeal;
    }
    public void setIscut(String iscut) 
    {
        this.iscut = iscut;
    }

    public String getIscut() 
    {
        return iscut;
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
            .append("fileid", getFileid())
            .append("fileholder", getFileholder())
            .append("filenote", getFilenote())
            .append("isfixation", getIsfixation())
            .append("fixationamount", getFixationamount())
            .append("code", getCode())
            .append("concealid", getConcealid())
            .append("createtime", getCreateTime())
            .append("submittime", getSubmittime())
            .append("status", getStatus())
            .append("isdeal", getIsdeal())
            .append("iscut", getIscut())
            .append("retain1", getRetain1())
            .append("retain2", getRetain2())
            .toString();
    }
}
