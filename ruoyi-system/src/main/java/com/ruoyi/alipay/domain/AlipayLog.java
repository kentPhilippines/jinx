package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 获取IP地址记录表对象 alipay_log
 * 
 * @author otc
 * @date 2020-02-27
 */
public class AlipayLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ipaddr;

    /** 定位 */
    @Excel(name = "定位")
    private String loginlocation;

    /** 登录时间 */
    @Excel(name = "登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logintime;

    /** 消息 */
    @Excel(name = "消息")
    private String msg;

    /** 用户名 */
    @Excel(name = "用户名")
    private String usename;

    /** 数据最近一次修改时间 */
    @Excel(name = "数据最近一次修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

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
    public void setIpaddr(String ipaddr) 
    {
        this.ipaddr = ipaddr;
    }

    public String getIpaddr() 
    {
        return ipaddr;
    }
    public void setLoginlocation(String loginlocation) 
    {
        this.loginlocation = loginlocation;
    }

    public String getLoginlocation() 
    {
        return loginlocation;
    }
    public void setLogintime(Date logintime) 
    {
        this.logintime = logintime;
    }

    public Date getLogintime() 
    {
        return logintime;
    }
    public void setMsg(String msg) 
    {
        this.msg = msg;
    }

    public String getMsg() 
    {
        return msg;
    }
    public void setUsename(String usename) 
    {
        this.usename = usename;
    }

    public String getUsename() 
    {
        return usename;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ipaddr", getIpaddr())
            .append("loginlocation", getLoginlocation())
            .append("logintime", getLogintime())
            .append("msg", getMsg())
            .append("usename", getUsename())
            .append("createtime", getCreateTime())
            .append("submittime", getSubmittime())
            .append("status", getStatus())
            .toString();
    }
}
