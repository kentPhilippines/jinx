package com.ruoyi.alipay.domain;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.alipay.domain.util.DesUtil;
import com.ruoyi.alipay.domain.util.DesUtil2;
import com.ruoyi.alipay.domain.util.EncryptHexUtil;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Calendar;
import java.util.Date;

/**
 * 会员提现记录对象 alipay_withdraw
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Data
public class AlipayWithdrawEntity extends BaseEntity {
    /* BigDecimal usdt ,   //花费usdt
           price  ,    //汽油价格
           used,       //使用汽油数
           eth,        //花费eth
           priceUsdt;  //eth - usdt 汇率
   String hash;        //订单hash* */
    private String usdt;
    private String price;
    private String used;
    private String eth;
    private String priceUsdt;
    private String hash;
    @Excel(name = "是否结算eth手续费", readConverterExp = "0=未结算,1=已结算")
    private Integer ethFee;
    @Excel(name = "USDT-hash")
    private String txhash;
    private String USDTamount;//usdt 充值金额
    private String USDTRate;//usdt 充值金额
    private String sunCountAmount = "0";
    private String sunCountAmountFee = "0";
    private String sunCountActualAmount = "0";

    private String pushOrder;//推送字段  1 默认       3 上游驳回

    private String macthMsg;  ///撮合订单 解释
    private Integer macthStatus;  ///撮合订单 状态   1已撮合 未支付     2 已撮合 已支付
    private Integer payStatus;  ///结算状态  1 已扣款结算   2 未扣款结算
    private Integer macthLock;  /// 撮合锁定当前不可以进行任何操作，  默认不锁定 0    1 锁定
    private Integer moreMacth;  /// 是否可以多次撮合[是否挂起]， 0 不可以  1 可以      可以就是挂起
    private Integer macthCount;  ///  撮合次数
    private Integer watingTime;  ///  撮合次数

    private static final long serialVersionUID = 1L;
    private String bankcode;
    private String appOrderId;


    /**
     * 数据id(主键索引)
     */
    private Long id;
    /**
     * 会员提现单号
     */
    @Excel(name = "会员提现单号")
    private String orderId;
    /**
     * 会员id(唯一识别号)(index索引)
     */
    @Excel(name = "会员id(唯一识别号)(index索引)")
    private String userId;
    /**
     * 商户提现1，码商提现2
     */
    @Excel(name = "商户提现1，码商提现2")
    private String withdrawType;
    /**
     * 银行卡号
     */
    @Excel(name = "银行卡号")
    private String bankNo;
    /**
     * 提现人姓名
     */
    @Excel(name = "提现人姓名")
    private String accname;
    /**
     * 0预下单1处理中2成功3失败
     */
    @Excel(name = "订单状态", readConverterExp = "0=预下单,1=处理中,2=成功,3=失败")
    private String orderStatus;
    /**
     * 开户行姓名
     */
    @Excel(name = "开户行姓名")
    private String bankName;
    /**
     * 提现金额
     */
    @Excel(name = "提现金额")
    private Double amount;
    private String amount1;
    /**
     * 手续费
     */
    @Excel(name = "手续费")
    private Double fee;
    private String fee1;
    /**
     * 真实到账金额
     */
    @Excel(name = "真实到账金额")
    private Double actualAmount;
    private String actualAmount1;
    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String mobile;
    /**
     * 提现成功回调地址
     */
    @Excel(name = "提现成功回调地址")
    private String notify;
    /**
     * 数据修改时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 数据修改时间
     */
    @Excel(name = "数据修改时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;
    @Excel(name = "撮合时间", width = 30, dateFormat = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date macthTime;

    /**
     * 1数据可用2数据无用
     */
    @Excel(name = "1数据可用2数据无用")
    private Integer status;

    /**
     * 备用字段添加业务使用
     */
    @Excel(name = "代付来源")
    private String retain1;

    /**
     * 备用字段添加业务使用
     */
    @Excel(name = "备用字段添加业务使用")
    private String retain2;

    /**
     * 审核人
     */
    @Excel(name = "后台申请人")
    private String apply;

    /**
     * 审核人
     */
    @Excel(name = "审核人")
    private String approval;
    @Excel(name = "代付产品")
    private String witType;
    //代付渠道
    private String witChannel;
    @Excel(name = "实际代付渠道")
    private String channelId;//实际代付渠道

    private String sgin;

    @Excel(name = "货币类型")
    private String currency;

    /**
     * 审核意见
     */
    @Excel(name = "审核意见")
    private String comment;



    public String getBankNo() {
        if(null != createTime){
            if( ! getDate (createTime)){
                return  DesUtil.decryptStr(bankNo);
            }
        }
        return DesUtil2.decryptStr( bankNo);
    }



    public String getAccname() {
        return   DesUtil2.decryptStr(accname);
    }




    public String getBankName() {
        if(StrUtil.isNotBlank(bankNo) && getDate (createTime) && StrUtil.isNotEmpty(sgin) ){
            Boolean click = EncryptHexUtil.click(DesUtil2.decryptStr(bankNo), DesUtil2.decryptStr(bankName), DesUtil2.decryptStr(accname), getAmount() + "", sgin);
            if(!click){
                return DesUtil2.decryptStr(bankName) + "  当前存在入侵  ";
            }
        }
        return DesUtil2.decryptStr(bankName);
    }



    public Double getFee() {
        try {
            return Double.valueOf(DesUtil2.decryptStr(fee1));
        }catch (Exception s ){
            return fee;

        }

    }


    public Double getActualAmount() {
        try {
            return Double.valueOf(DesUtil2.decryptStr(actualAmount1));
        }catch (Exception e ){
            return actualAmount;
        }
    }


    @Override
    public String toString() {
        return "AlipayWithdrawEntity{" +
                "usdt='" + usdt + '\'' +
                ", price='" + price + '\'' +
                ", used='" + used + '\'' +
                ", eth='" + eth + '\'' +
                ", priceUsdt='" + priceUsdt + '\'' +
                ", hash='" + hash + '\'' +
                ", ethFee=" + ethFee +
                ", txhash='" + txhash + '\'' +
                ", USDTamount='" + USDTamount + '\'' +
                ", USDTRate='" + USDTRate + '\'' +
                ", sunCountAmount='" + sunCountAmount + '\'' +
                ", sunCountAmountFee='" + sunCountAmountFee + '\'' +
                ", sunCountActualAmount='" + sunCountActualAmount + '\'' +
                ", pushOrder='" + pushOrder + '\'' +
                ", macthMsg='" + macthMsg + '\'' +
                ", macthStatus=" + macthStatus +
                ", payStatus=" + payStatus +
                ", macthLock=" + macthLock +
                ", moreMacth=" + moreMacth +
                ", macthCount=" + macthCount +
                ", bankcode='" + bankcode + '\'' +
                ", appOrderId='" + appOrderId + '\'' +
                ", id=" + id +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", withdrawType='" + withdrawType + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", accname='" + accname + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", bankName='" + bankName + '\'' +
                ", amount=" + amount +
                ", fee=" + fee +
                ", actualAmount=" + actualAmount +
                ", mobile='" + mobile + '\'' +
                ", notify='" + notify + '\'' +
                ", createTime=" + createTime +
                ", submitTime=" + submitTime +
                ", status=" + status +
                ", retain1='" + retain1 + '\'' +
                ", retain2='" + retain2 + '\'' +
                ", apply='" + apply + '\'' +
                ", approval='" + approval + '\'' +
                ", witType='" + witType + '\'' +
                ", witChannel='" + witChannel + '\'' +
                ", channelId='" + channelId + '\'' +
                ", currency='" + currency + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    private static  Boolean getDate(Date date1){
        //获取当前时间
        Calendar date = Calendar.getInstance();
        date.setTime(date1);
        //获取开始时间
        Calendar begin = Calendar.getInstance();
        begin.setTime(date1);
        //获取结束时间
        Calendar end = Calendar.getInstance();
        end.setTime(DateUtil.parseDate("2022-09-14 00:00:01"));
        if (date.after(end)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }
}
