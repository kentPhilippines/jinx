package test.demo.bean;

/**
 * <p>交易实体</p>
 * @author K
 */
public class Deal {
	/**
	 * ######################
	 * 	字段名		是否加入签名   是否必填
	 * 	appid			是			是		24		商户号，例如：11396	商户交易号
		orderid			是			是		128		订单号，必须唯一	系统验证方式 appid+orderid【验证唯一性】
		notifyurl		是			是		96		回调地址	回调地址不超过72个字符
		pageUrl			否			否		124		同步返回地址【H5类产品必传】	支付成功后跳转的地址
		amount			是			是		-		支付金额, 单位  ： 元
		passcode		是			是		24		通道编码：PAY1005【支付宝扫码】	产品类型编号【咨询运营】
		rsasign			否			是		-		签名字符串加密，加密方式参考平台方Demo	签名
		userid			否			否		124		交易用户唯一标识	该数据为商户的玩家标识（用于提高成功率）
		bankCode 		否			否		24		银行编码	如： bankCode=ICBC，网银支付时必填
		subject 		否			否		124		商品名称	
		applydate		是			是		-		请求时间，时间格式：yyyyMMddHHmmss	【时间格式错误会产生异常】
	 */
	private String appId;
	private String orderId;
	private String notifyUrl;
	private String pageUrl;
	private String amount;
	private String passCode;
	private String sign ; 
	private String applyDate;
	private String subject;
	private String userid;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPassCode() {
		return passCode;
	}
	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
}
