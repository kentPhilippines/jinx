package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 日志表对象 alipay_log
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public class AlipayLogEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ipAddr;

    /** 定位 */
    @Excel(name = "定位")
    private String loginLocation;

    /** 登录时间 */
    @Excel(name = "登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginTime;

    /** 消息 */
    @Excel(name = "消息")
    private String msg;

    /** 用户名 */
    @Excel(name = "用户名")
    private String useName;

    /** 数据最近一次修改时间 */
    @Excel(name = "数据最近一次修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 1数据可用2数据无用 */
    @Excel(name = "1数据可用2数据无用")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIpAddr(String ipAddr) 
    {
        this.ipAddr = ipAddr;
    }

    public String getIpAddr() 
    {
        return ipAddr;
    }
    public void setLoginLocation(String loginLocation) 
    {
        this.loginLocation = loginLocation;
    }

    public String getLoginLocation() 
    {
        return loginLocation;
    }
    public void setLoginTime(Date loginTime) 
    {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() 
    {
        return loginTime;
    }
    public void setMsg(String msg) 
    {
        this.msg = msg;
    }

    public String getMsg() 
    {
        return msg;
    }
    public void setUseName(String useName) 
    {
        this.useName = useName;
    }

    public String getUseName() 
    {
        return useName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ipAddr", getIpAddr())
            .append("loginLocation", getLoginLocation())
            .append("loginTime", getLoginTime())
            .append("msg", getMsg())
            .append("useName", getUseName())
            .append("createTime", getCreateTime())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .toString();
    }
}
