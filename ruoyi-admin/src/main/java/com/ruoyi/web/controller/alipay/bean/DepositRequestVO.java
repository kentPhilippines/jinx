package com.ruoyi.web.controller.alipay.bean;


import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 代收请求对象
 */
@Data
public class DepositRequestVO {

    /**
     *商户号，请向开户人员索取
     */
    @NotEmpty(message = "商户号appId不能为空")
    @Size(min = 1, max = 24,message = "商户号appId长度在{min}-{max}")
    private String appId;
    /**
     *订单号，必须唯一
     */
    @NotEmpty(message = "订单号orderId不能为空")
    @Size(min = 1, max = 128,message = "订单号orderId长度在{min}-{max}")
    private String orderId = DateTime.now().getTime()+ RandomStringUtils.randomNumeric(4);

    /**
     * 回调地址
     * 异步通知
     */
    @NotEmpty(message = "回调地址notifyUrl不能为空")
    @Size(min = 1, max = 72,message = "回调地址notifyUrl长度在{min}-{max}")
    private String notifyUrl = "www.google.com";

    /**
     * 同步返回地址
     * 支付成功后跳转的地址
     */
    @NotEmpty(message = "同步返回地址pageUrl不能为空")
    @Size(min = 1, max = 124,message = "同步返回地址pageUrl长度在{min}-{max}")
    private String pageUrl= "www.google.com";

    /**
     * 代收金额
     * 以元为单位，可以2位小数
     */
    @NotEmpty(message = "代收金额amount不能为空")
    private String amount;

    /**
     * 通道编码：ALIPAY_SCAN【支付宝扫码】 ，请向开户人员索要
     */
    @NotEmpty(message = "通道编码passCode不能为空")
    @Size(min = 1, max = 24,message = "通道编码长度在{min}-{max}")
    private String passCode;

    /**
     * 请求时间，时间格式：yyyyMMddHHmmss
     * 时间格式错误会产生异常
     */
    @NotEmpty(message = "请求时间applyDate不能为空,yyyyMMddHHmmss")
    private String applyDate = DateTime.now().toString("yyyyMMddHHmmss");


    /**
     * 商品名称
     */
    @Size(min = 0, max = 124,message = "商品名称长度在{min}-{max}")
    private String subject="test";


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
    private String sign;

    /**
     * 请传付款人姓名
     * 该数据为商户的玩家标识（用于提高成功率）
     */
    @NotEmpty(message = "付款人姓名userId不能为空")
    private String mcPayName;





}
