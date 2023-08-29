package com.ruoyi.common.core.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class StatisticsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5547968516186055805L;
    private String fee;
    private String time;
    private String agentAmount;
    private String userAgent;
    private String retain1;
    @Excel(name = "货币类型")
    private String currency;
    private String successFee;
    private String accountAmount;
    private String todayAmount;
     public String getTodayAmount(){return this.todayAmount;}
    private String   profit;
    private String userName;
    /**
     * 用户账户
     */
    private String userId;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 交易订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 成功订单总金额
     */
    private BigDecimal successAmount;

    /**
     * 交易订单总数
     */
    private Integer totalCount;

    /**
     * 交易订单成功总数
     */
    private Integer successCount;

    /**
     * 交易订单的成功率
     */
    private Double successPercent;

}
