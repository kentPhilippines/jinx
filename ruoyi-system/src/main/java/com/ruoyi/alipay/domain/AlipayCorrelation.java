package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 代理关系表对象 alipay_correlation
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
public class AlipayCorrelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 父节点id */
    @Excel(name = "父节点id")
    private Long parentId;

    /** 父节点名字 */
    @Excel(name = "父节点名字")
    private String parentName;

    /** 孩子节点的id */
    @Excel(name = "孩子节点的id")
    private Long childrenId;

    /** 孩子节点的名字 */
    @Excel(name = "孩子节点的名字")
    private String childrenName;

    /** 节点距离 */
    @Excel(name = "节点距离")
    private Integer distance;

    /** 支付宝内容 */
    @Excel(name = "支付宝内容")
    private String medium;

    /** 状态:1可使用；0不可使用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 数据最后一次修改时间 */
    @Excel(name = "数据最后一次修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 1代理商 2会员 */
    @Excel(name = "1代理商 2会员")
    private Integer parentType;

    /** 1代理商 2会员 */
    @Excel(name = "1代理商 2会员")
    private Integer childrenType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setParentName(String parentName) 
    {
        this.parentName = parentName;
    }

    public String getParentName() 
    {
        return parentName;
    }
    public void setChildrenId(Long childrenId) 
    {
        this.childrenId = childrenId;
    }

    public Long getChildrenId() 
    {
        return childrenId;
    }
    public void setChildrenName(String childrenName) 
    {
        this.childrenName = childrenName;
    }

    public String getChildrenName() 
    {
        return childrenName;
    }
    public void setDistance(Integer distance) 
    {
        this.distance = distance;
    }

    public Integer getDistance() 
    {
        return distance;
    }
    public void setMedium(String medium) 
    {
        this.medium = medium;
    }

    public String getMedium() 
    {
        return medium;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setParentType(Integer parentType) 
    {
        this.parentType = parentType;
    }

    public Integer getParentType() 
    {
        return parentType;
    }
    public void setChildrenType(Integer childrenType) 
    {
        this.childrenType = childrenType;
    }

    public Integer getChildrenType() 
    {
        return childrenType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("parentName", getParentName())
            .append("childrenId", getChildrenId())
            .append("childrenName", getChildrenName())
            .append("distance", getDistance())
            .append("medium", getMedium())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .append("parentType", getParentType())
            .append("childrenType", getChildrenType())
            .toString();
    }
}
