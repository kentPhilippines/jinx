package com.ruoyi.alipay.domain.util;


/**
 * 商户每日对账数据
 */
public class AccountToDayFund {
    private Long id;
    private String username;//交易账户名
    private String todayAmount;//当日账户资金    昨日北京时间凌晨1点至今日北京时间凌晨1点
    private String sumAmountDealOrder;//商户交易订单 汇总交易金额
    private String sumAmountChanelOrder;//商户关联渠道交易订单 汇总交易金额
    private String sumAmountDealRun;//资金流水产生交易资金汇总
    private String sumWitOrder;//商户代付订单  汇总交易金额
    private String sumWitRun;//资金流水产品代付资金汇总
    private String sumAddAmount;//人工加款订单金额
    private String sumAddRunAmount;//人工加款流水订单金额
    private String sumDeleteAmount;//人工减款订单金额
    private String SumDeletRunAmount;//人工减款流水订单金额
    private String time;//对账日
    private Integer flatAccount;//是否平帐
    private String createTime;//对账时间

}
