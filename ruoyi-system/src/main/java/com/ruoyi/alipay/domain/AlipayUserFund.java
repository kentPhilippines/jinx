package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 用户资金账户对象 alipay_user_fund
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public class AlipayUserFund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 账户id【登录账户】 */
    @Excel(name = "账户id【登录账户】")
    private String userid;

    /** 账户昵称【登录账户名】 */
    @Excel(name = "账户昵称【登录账户名】")
    private String username;

    /** 现金账户【当前可取现】 */
    @Excel(name = "现金账户【当前可取现】")
    private Double cashbalance;

    /** 充值点数 */
    @Excel(name = "充值点数")
    private Double rechargenumber;

    /** 冻结账户 */
    @Excel(name = "冻结账户")
    private Double freezebalance;

    /** 可取现账户金额【码商账户余额=冻结金额+现金账户+充值点数】 */
    @Excel(name = "可取现账户金额【码商账户余额=冻结金额+现金账户+充值点数】")
    private Double accountbalance;

    /** 累计交易额 */
    @Excel(name = "累计交易额")
    private Double sumdealamount;

    /** 累计充值金额【充值成功时统计记录】 */
    @Excel(name = "累计充值金额【充值成功时统计记录】")
    private Double sumrechargeamount;

    /** 累计利润金额 */
    @Excel(name = "累计利润金额")
    private Double sumprofit;

    /** 累计代理商利润【如果当前账户为商户则该数据为0】 */
    @Excel(name = "累计代理商利润【如果当前账户为商户则该数据为0】")
    private Double sumagentprofit;

    /** 累计接单笔数 */
    @Excel(name = "累计接单笔数")
    private Long sumordercount;

    /** 当日接单金额 */
    @Excel(name = "当日接单金额")
    private Double todaydealamount;

    /** 当日接单利润【代理利润+接单利润=当日利润】 */
    @Excel(name = "当日接单利润【代理利润+接单利润=当日利润】")
    private Double todayprofit;

    /** 当日接单笔数 */
    @Excel(name = "当日接单笔数")
    private Long todayordercount;

    /** 当日代理商利润【如果当前账户为商户则该数据为0】 */
    @Excel(name = "当日代理商利润【如果当前账户为商户则该数据为0】")
    private Double todayagentprofit;

    /** 用户类型,商户1 码商2 */
    @Excel(name = "用户类型,商户1 码商2")
    private String usertype;

    /** 代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】 */
    @Excel(name = "代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】")
    private String agent;

    /** 是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】 */
    @Excel(name = "是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】")
    private String isagent;

    /** 最后一次数据修改时间 */
    @Excel(name = "最后一次数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    /** 数据是否可用:1数据可用2数据无用 */
    @Excel(name = "数据是否可用:1数据可用2数据无用")
    private Integer status;

    /** 数据版本号【数据修改锁】 */
    @Excel(name = "数据版本号【数据修改锁】")
    private Long version;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setCashbalance(Double cashbalance) 
    {
        this.cashbalance = cashbalance;
    }

    public Double getCashbalance() 
    {
        return cashbalance;
    }
    public void setRechargenumber(Double rechargenumber) 
    {
        this.rechargenumber = rechargenumber;
    }

    public Double getRechargenumber() 
    {
        return rechargenumber;
    }
    public void setFreezebalance(Double freezebalance) 
    {
        this.freezebalance = freezebalance;
    }

    public Double getFreezebalance() 
    {
        return freezebalance;
    }
    public void setAccountbalance(Double accountbalance) 
    {
        this.accountbalance = accountbalance;
    }

    public Double getAccountbalance() 
    {
        return accountbalance;
    }
    public void setSumdealamount(Double sumdealamount) 
    {
        this.sumdealamount = sumdealamount;
    }

    public Double getSumdealamount() 
    {
        return sumdealamount;
    }
    public void setSumrechargeamount(Double sumrechargeamount) 
    {
        this.sumrechargeamount = sumrechargeamount;
    }

    public Double getSumrechargeamount() 
    {
        return sumrechargeamount;
    }
    public void setSumprofit(Double sumprofit) 
    {
        this.sumprofit = sumprofit;
    }

    public Double getSumprofit() 
    {
        return sumprofit;
    }
    public void setSumagentprofit(Double sumagentprofit) 
    {
        this.sumagentprofit = sumagentprofit;
    }

    public Double getSumagentprofit() 
    {
        return sumagentprofit;
    }
    public void setSumordercount(Long sumordercount) 
    {
        this.sumordercount = sumordercount;
    }

    public Long getSumordercount() 
    {
        return sumordercount;
    }
    public void setTodaydealamount(Double todaydealamount) 
    {
        this.todaydealamount = todaydealamount;
    }

    public Double getTodaydealamount() 
    {
        return todaydealamount;
    }
    public void setTodayprofit(Double todayprofit) 
    {
        this.todayprofit = todayprofit;
    }

    public Double getTodayprofit() 
    {
        return todayprofit;
    }
    public void setTodayordercount(Long todayordercount) 
    {
        this.todayordercount = todayordercount;
    }

    public Long getTodayordercount() 
    {
        return todayordercount;
    }
    public void setTodayagentprofit(Double todayagentprofit) 
    {
        this.todayagentprofit = todayagentprofit;
    }

    public Double getTodayagentprofit() 
    {
        return todayagentprofit;
    }
    public void setUsertype(String usertype) 
    {
        this.usertype = usertype;
    }

    public String getUsertype() 
    {
        return usertype;
    }
    public void setAgent(String agent) 
    {
        this.agent = agent;
    }

    public String getAgent() 
    {
        return agent;
    }
    public void setIsagent(String isagent) 
    {
        this.isagent = isagent;
    }

    public String getIsagent() 
    {
        return isagent;
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
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userid", getUserid())
            .append("username", getUsername())
            .append("cashbalance", getCashbalance())
            .append("rechargenumber", getRechargenumber())
            .append("freezebalance", getFreezebalance())
            .append("accountbalance", getAccountbalance())
            .append("sumdealamount", getSumdealamount())
            .append("sumrechargeamount", getSumrechargeamount())
            .append("sumprofit", getSumprofit())
            .append("sumagentprofit", getSumagentprofit())
            .append("sumordercount", getSumordercount())
            .append("todaydealamount", getTodaydealamount())
            .append("todayprofit", getTodayprofit())
            .append("todayordercount", getTodayordercount())
            .append("todayagentprofit", getTodayagentprofit())
            .append("usertype", getUsertype())
            .append("agent", getAgent())
            .append("isagent", getIsagent())
            .append("createtime", getCreateTime())
            .append("submittime", getSubmittime())
            .append("status", getStatus())
            .append("version", getVersion())
            .toString();
    }
}
