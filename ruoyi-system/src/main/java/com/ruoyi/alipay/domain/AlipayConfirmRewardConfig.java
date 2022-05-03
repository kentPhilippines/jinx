package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 代付确认奖励配置对象 alipay_confirm_reward_config
 * 
 * @author ruoyi
 * @date 2022-05-03
 */
public class AlipayConfirmRewardConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商户用户名 */
    @Excel(name = "商户用户名")
    private String userId;

    /** 反水比例 */
    @Excel(name = "反水比例")
    private Long rebateRate;

    /** 最低金额要求 */
    @Excel(name = "最低金额要求")
    private Long minimum;

    /** 超时时间(秒) */
    @Excel(name = "超时时间(秒)")
    private Integer timeOut;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setRebateRate(Long rebateRate) 
    {
        this.rebateRate = rebateRate;
    }

    public Long getRebateRate() 
    {
        return rebateRate;
    }
    public void setMinimum(Long minimum) 
    {
        this.minimum = minimum;
    }

    public Long getMinimum() 
    {
        return minimum;
    }
    public void setTimeOut(Integer timeOut) 
    {
        this.timeOut = timeOut;
    }

    public Integer getTimeOut() 
    {
        return timeOut;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("rebateRate", getRebateRate())
            .append("minimum", getMinimum())
            .append("timeOut", getTimeOut())
            .append("remark", getRemark())
            .append("createDate", getCreateDate())
            .toString();
    }
}
