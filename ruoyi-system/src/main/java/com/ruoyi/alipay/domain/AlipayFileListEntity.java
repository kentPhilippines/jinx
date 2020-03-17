package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 文件列对象 alipay_file_list
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public class AlipayFileListEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 二维码编号(本地编号) */
    @Excel(name = "二维码编号(本地编号)")
    private String fileId;

    /** 二维码持有人 */
    @Excel(name = "二维码持有人")
    private String fileholder;

    /** 备注 */
    @Excel(name = "备注")
    private String fileNote;

    /** 是否为固码  1为固定码  2不为固定码 */
    @Excel(name = "是否为固码  1为固定码  2不为固定码")
    private String isFixation;

    /** 若为固码,固码金额 */
    @Excel(name = "若为固码,固码金额")
    private Double fixationAmount;

    /** 收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放) */
    @Excel(name = "收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放)")
    private String code;

    /** 关联收款媒介ID */
    @Excel(name = "关联收款媒介ID")
    private String concealId;

    /** 数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 是否逻辑删除：1删除2可用 */
    @Excel(name = "是否逻辑删除：1删除2可用")
    private String isDeal;

    /** 图片是否以剪裁 Y 剪裁过   字段未空则未剪裁 */
    @Excel(name = "图片是否以剪裁 Y 剪裁过   字段未空则未剪裁")
    private String isCut;

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
    public void setFileId(String fileId) 
    {
        this.fileId = fileId;
    }

    public String getFileId() 
    {
        return fileId;
    }
    public void setFileholder(String fileholder) 
    {
        this.fileholder = fileholder;
    }

    public String getFileholder() 
    {
        return fileholder;
    }
    public void setFileNote(String fileNote) 
    {
        this.fileNote = fileNote;
    }

    public String getFileNote() 
    {
        return fileNote;
    }
    public void setIsFixation(String isFixation) 
    {
        this.isFixation = isFixation;
    }

    public String getIsFixation() 
    {
        return isFixation;
    }
    public void setFixationAmount(Double fixationAmount) 
    {
        this.fixationAmount = fixationAmount;
    }

    public Double getFixationAmount() 
    {
        return fixationAmount;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setConcealId(String concealId) 
    {
        this.concealId = concealId;
    }

    public String getConcealId() 
    {
        return concealId;
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
    public void setIsCut(String isCut) 
    {
        this.isCut = isCut;
    }

    public String getIsCut() 
    {
        return isCut;
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
            .append("fileId", getFileId())
            .append("fileholder", getFileholder())
            .append("fileNote", getFileNote())
            .append("isFixation", getIsFixation())
            .append("fixationAmount", getFixationAmount())
            .append("code", getCode())
            .append("concealId", getConcealId())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("isDeal", getIsDeal())
            .append("isCut", getIsCut())
            .append("retain1", getRetain1())
            .append("retain2", getRetain2())
            .toString();
    }
}
