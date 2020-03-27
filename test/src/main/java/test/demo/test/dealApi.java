package test.demo.test;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import test.demo.bean.Deal;
import test.demo.util.MapUtil;
import test.demo.util.XRsa;

public class dealApi {
	public static void main(String[] args) throws Exception {
		for(int a = 0 ; a <= 0 ; a++) {
			ThreadUtil.execute(()->{
				try {
					test();
				} catch (Exception e) {
				}
			});
		}
		
	}
	static SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
	 static void test() throws Exception {
		 
		 String key = "2593114D4AE7469FB0089F434B94AC6F";//交易密钥
		 String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCe772h6hRLDhNsAbkUHMJHiEBezSaBqwoRRiC5slEM7AWEsNo3/RooO9wGRSqrRj9yCOdD0NDvLtTt9MMcZ3Z+fS54h18OI8dKCu+VW8rkzo1BN+Bj4uJh2KUEbx6/F6bwCb2OHTBjh9Pn1bw27cdbjSuiSKlNOQEgGnCbtTCKxwIDAQAB";
		 Deal deal = new Deal();
		 deal.setAmount("100");//金额
		 deal.setAppId("AsgRTDFY");//商户号
		 deal.setApplyDate(d.format(new Date()));
		 deal.setNotifyUrl("www.baidu.com");
		 deal.setOrderId(IdUtil.objectId());
		 deal.setPassCode("ALIPAY_SCAN");
		 deal.setSubject("订单交易");
		 deal.setUserid("asdas");
		 Map<String, Object> objectToMap = MapUtil.objectToMap(deal);
		 String createParam = MapUtil.createParam(objectToMap);
		 System.out.println("签名前请求串："+createParam);
		 String md5 = md5(createParam+key);
		 System.out.println("签名："+md5);
		 deal.setSign(md5);
		 Map<String, Object> objectToMap2 = MapUtil.objectToMap(deal);
		 String createParam2 = MapUtil.createParam(objectToMap2);
		 System.out.println("加密前字符串："+createParam2);
		 XRsa rsa = new XRsa(publicKey);
		 String publicEncrypt = rsa.publicEncrypt(createParam2);
		 System.out.println("加密后字符串："+publicEncrypt);
		 Map<String, Object> postMap = new HashMap<String, Object>();
		 postMap.put("cipherText", publicEncrypt);
		 postMap.put("userId","AsgRTDFY");
		 String post = HttpUtil.post("127.0.0.1:9010/deal/pay", postMap);
		 System.out.println("相应结果集："+post);
		 
	 }
	 public static String md5(String str) {
	        MessageDigest md5 = null;
	        try {
	            md5 = MessageDigest.getInstance("MD5");
	        } catch (Exception e) {
	            System.out.println(e.toString());
	            e.printStackTrace();
	            return "";
	        }
	        char[] charArray = str.toCharArray();
	        byte[] byteArray = new byte[charArray.length];
	        for (int i = 0; i < charArray.length; i++)
	            byteArray[i] = (byte) charArray[i];
	        byte[] md5Bytes = md5.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16)
	                hexValue.append("0");
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
	    }

}
