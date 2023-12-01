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
     * 银行编码【内充产品】
     * 如： bankCode=ICBC，网银支付时必填
     * 支持代付银行
     *
     * 中国建设银行  CCB
     * 中国农业银行  ABC
     * 中国工商银行  ICBC
     * 中国银行  BOC
     * 中国民生银行  CMBC
     * 招商银行  CMB
     * 兴业银行  CIB
     * 北京银行  BJBANK
     * 交通银行  COMM
     * 中国光大银行  CEB
     * 平安银行  SPABANK
     * 中国邮政储蓄银行  PSBC
     * 中信银行  CITIC
     * 华夏银行  HXBANK
     * 广州银行  GCB
     * 上海浦东发展银行  SPDB
     * 广东发展银行   GDB
     * 深圳农村商业银行  SRCB
     * 三门峡银行  SCCB
     * 广西北部湾银行  BGB
     * 阳泉银行  YQCCB
     * 上海银行  SHBANK
     * 吉林银行  JLBANK
     * 上海农村商业银行  SHRCB
     * 威海市商业银行   WHCCB
     * 潍坊银行  BANKWF
     * 周口银行  BOZK
     * 常熟农村商业银行  CSRCB
     * 库尔勒市商业银行  KORLABANK
     * 顺德农商银行  SDEB
     * 湖北省农村信用社  HURCB
     * 无锡农村商业银行  WRCB
     * 朝阳银行  BOCY
     * 浙商银行  CZBANK
     * 邯郸银行  HDBANK
     * 泰安市商业银行  TACCB
     * 东莞银行  BOD
     * 辽阳市商业银行  LYCB
     * 广东省农村信用社联合社  GDRCC
     * 兰州银行   LZYH
     * 绍兴银行   SXCB
     * 渤海银行   BOHAIB
     * 浙江稠州商业银行  CZCB
     * 贵州省农村信用社  GZRCU
     * 张家口市商业银行  ZJKCCB
     * 锦州银行  BOJZ
     * 吉林农信   JLRCU
     * 平顶山银行  BOP
     * 上饶银行  SRBANK
     * 山东农信  SDRCU
     * 盛京银行  SJBANK
     * 汉口银行   HKB
     * 广西省农村信用  GXRCU
     * 宁夏黄河农村商业银行   NXRCU
     * 包商银行  BSB
     * 江苏银行   JSBANK
     * 广东南粤银行  NYNB
     * 广州农商银行  GRCB
     * 苏州银行  BOSZ
     * 杭州银行  HZCB
     * 德州银行  DZBANK
     * 鄂尔多斯银行  ORBANK
     * 湖北银行  HBC
     * 嘉兴银行  JXBANK
     * 遵义市商业银行  ZYCBANK
     * 丹东银行  BODD
     * 湖南省农村信用社  HNRCC
     * 安阳银行  AYCB
     * 东营市商业银行  DYCCB
     * 常州农村信用联社  CZRCB
     * 恒丰银行   EGBANK
     * 国家开发银行  CDB
     * 衡水银行  HSBK
     * 自贡市商业银行  ZGCCB
     * 成都银行   CDCB
     * 济宁银行  JNBANK
     * 江苏太仓农村商业银行  TCRCB
     * 南京银行  NJCB
     * 郑州银行   ZZBANK
     * 洛阳银行  LYBANK
     * 德阳商业银行  DYCB
     * 齐商银行  ZBCB
     * 抚顺银行  FSCB
     * 四川省农村信用  SCRCU
     * 河北省农村信用社  HBRCU
     * 乐山市商业银行  LSCCB
     * 莱商银行  LSBANK
     * 开封市商业银行  CBKF
     * 尧都农商行  YDRCB
     * 河南省农村信用  HNRCU
     * 云南省农村信用社  YNRCC
     * 内蒙古银行  H3CB
     * 玉溪市商业银行  YXCCB
     * 富滇银行  FDB
     * 江苏省农村信用联合社  JSRCU
     * 信阳银行  XYBANK
     * 韩亚银行  HANABANK
     * 石嘴山银行  SZSBK
     * 晋城银行  JINCHB
     * 阜新银行  FXCB
     * 武汉农村商业银行  WHRCB
     * 湖北银行宜昌分行  HBYCBANK
     * 台州银行  TZCB
     * 江西省农村信用  JXRCU
     * 张家港农村商业银行  ZRCBANK
     * 晋商银行  JSB
     * 晋中市商业银行  JZBANK
     * 福建海峡银行  FJHXBC
     * 许昌银行  XCYH
     * 宁夏银行  NXBANK
     * 南海农村信用联社  NHB
     * 新乡银行  XXBANK
     * 徽商银行  HSBANK
     * 九江银行  JJBANK
     * 农信银清算中心  NHQS
     * 江苏江阴农村商业银行  JRCB
     * 湖州市商业银行  HZCCB
     * 浙江民泰商业银行  MTBANK
     * 廊坊银行  LANGFB
     * 鞍山银行  ASCB
     * 陕西信合  SXRCCU
     * 重庆三峡银行  CCQTGB
     * 大连银行  DLB
     * 东莞农村商业银行  DRCBCL
     * 宁波银行  NBBANK
     * 西安银行  XABANK
     * 昆仑银行  KLB
     * 重庆农村商业银行  CRCBANK
     * 营口银行  BOYK
     * 昆山农村商业银行  KSRB
     * 华融湘江银行  HRXJB
     * 桂林银行  GLBANK
     * 安徽省农村信用社  ARCU
     * 青海银行  BOQH
     * 成都农商银行  CDRCB
     * 青岛银行  QDCCB
     * 东亚银行  HKBEA
     * 甘肃省农村信用  GSRCU
     * 浙江省农村信用社联合社  ZJNX
     * 湖北银行黄石分行  HBHSBANK
     * 温州银行  WZCB
     * 天津农商银行  TRCB
     * 乌鲁木齐市商业银行  URMQCCB
     * 中山小榄村镇银行  XLBANK
     * 长沙银行  CSCB
     * 吴江农商银行  WJRCB
     * 齐鲁银行  QLBANK
     * 宜宾市商业银行  YBCCB
     * 浙江泰隆商业银行  ZJTLCB
     * 金华银行  JHBANK
     * 河北银行  BHB
     * 赣州银行  GZB
     * 驻马店银行  BZMD
     * 鄞州银行  NBYZ
     * 临商银行  LSBC
     * 贵阳市商业银行   GYCB
     * 重庆银行   CQBANK
     * 承德银行  BJRCB
     * 北京农村商业银行  BJRCB
     * 南昌银行  NCB
     * 龙江银行  DAQINGB
     * 天津银行  TCCB
     * 南充市商业银行  CGNB
     * 城市商业银行资金清算中心  CBBQS
     * 邢台银行  XTB
     * 厦门银行  XMBANK
     * 福建农村信用社  FJNX
     * 厦门国际银行  XIB
     * 汇丰银行  HSBC
     * 长安银行  CCAB
     * 苏州农村商业银行  SZSNZ
     */
    @Size(min = 0, max = 24,message = "银行编码长度在{min}-{max}")
    private String bankCode ="ICBC";
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
    private String userId;





}
