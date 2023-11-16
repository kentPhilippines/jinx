package com.ruoyi.web.controller.alipay.bean;


import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 代付请求对象
 */
@Data
public class WithdrawRequestVO {

    /**
     *商户号，请向开户人员索取
     */
    @NotEmpty(message = "商户号appid不能为空")
    @Size(min = 1, max = 24,message = "商户号appid长度在{min}-{max}")
    String  appid;
    /**
     *订单号，必须唯一
     * appid+orderid【验证唯一性】
     */
    @NotEmpty(message = "订单号apporderid不能为空")
    @Size(min = 1, max = 128,message = "订单号apporderid长度在{min}-{max}")
    String  apporderid = DateTime.now().getTime()+ RandomStringUtils.randomNumeric(4);

    /**
     * 请求时间，时间格式：yyyyMMddHHmmss
     * 时间格式错误会产生异常
     */
    @NotEmpty(message = "请求时间ordertime不能为空,yyyyMMddHHmmss")
    String  ordertime = DateTime.now().toString("yyyyMMddHHmmss");

    /**
     * 代付金额
     * 以元为单位，可以2位小数
     */
    @NotEmpty(message = "代付金额amount不能为空")
    String  amount;


    /**
     * 银行卡号
     *
     */
    @NotEmpty(message = "银行卡号acctno不能为空")
    String  acctno;
    /**
     * 银行卡开户人姓名
     *
     */
    @NotEmpty(message = "银行卡开户人姓名acctname不能为空")
    String  acctname;


    /**
     * 银行标识（银行名称）
     * 如：ICBC 工商银行，如果贵公司 无法传递按照规则的 银行编号，该字段直接传递银行名称
     */
    @NotEmpty(message = "银行标识bankcode不能为空")
    String  bankcode;

    /**
     * 银行卡绑定手机号
     */
    String  mobile;

    /**
     * 回调地址
     * 异步通知
     */
    @NotEmpty(message = "回调地址notifyurl不能为空")
    String  notifyurl= "www.google.com";

    /**
     * 签名
     * 签名字符串加密，加密方式参考平台方Demo
     * 签名规则如下：
     * 1.将请求参数以k-v形式串接成QueryString，并依ASCII由小至大排序
     * 2.于句尾加上平台钥后密进行MD5运算(以密钥123456为例，于句尾加上”123456”)
     * 3.将此字串作为sign字段带在请求参数中
     * 提供范例：
     * 签名前请求串：amount=100&appId=AsgRTDFY&applyDate=20200429220201&notifyUrl=www.baidu.com&orderId=5ea988d94133d4c8bd6dc40b&passCode=ALIPAY_SCAN&subject=订单交易&userid=asdas123456
     * 取得结果：
     * 949a96e061e61b76dc300bb21b65403c
     */
    @NotEmpty(message = "签名sign不能为空")
    String  sign;

}
