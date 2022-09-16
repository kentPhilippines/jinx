package com.ruoyi.alipay.domain.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptHexUtil {
    private static final String UTF_8 = "utf-8";
    private static final String ENCODE_TYPE = "md5";
    static Logger log = LoggerFactory.getLogger(EncryptHexUtil.class);
    public static void main(String[] args) {
        String bankNo = "6222621060004847957452";
        String bankName = "中国工商银行";
        String account = "张三";
        String amount = "452";
        System.out.println(DesUtil2.encryptHex(bankNo));
        String sgin = sgin(bankNo, bankName, account, amount);
        System.out.println(sgin);
        Boolean click = click(bankNo, bankName, account, amount, sgin);
        System.out.println(click);
    }
  public static Boolean  click(String bankNo, String bankName, String account, String amount, String sgin){
        String bankNo1 = bankNo;
        String bankName1 = bankName;
        String account1 = account;
        String amount1 = amount;




      bankNo = DesUtil2.encryptHex(bankNo);
      bankName = DesUtil2.encryptHex(bankName);
      account = DesUtil2.encryptHex(account);
      amount = DesUtil2.encryptHex(amount);

      String s = forE(number(bankNo), bankNo, bankNo);
      String s1 = forE(number(bankName), bankName, bankName);
      String s2 = forE(number(account), account, account);
      String s4 = forE(number(s), s1, s2);
      String s5 = forE(number(s2), s4, s4);
      if(bankNo.equals("6c968bac83f5cf86ece145388ba08c99378a2f7548df3a14")){
          System.out.println(s5);
          System.out.println(sgin);
          System.out.println(bankNo1);
          System.out.println(bankName1);
          System.out.println(account1);
          System.out.println(amount1);
      }
      return sgin.equals(s5);
    }

    public static String  sgin(String bankNo, String bankName, String account, String amount){
        bankNo = DesUtil2.encryptHex(bankNo);
        bankName = DesUtil2.encryptHex(bankName);
        account = DesUtil2.encryptHex(account);
        String s = forE(number(bankNo), bankNo, bankNo);
        String s1 = forE(number(bankName), bankName, bankName);
        String s2 = forE(number(account), account, account);
        String s4 = forE(number(s), s1, s2);
        return forE(number(s2), s4, s4);
    }
    static int number(String c) {
        char[] charArray = c.toCharArray();
        char a = charArray[0];
        char d = charArray[charArray.length - 1];
        int o = a - d;
        int p = (o < 0) ? -o : o;
        boolean flag = true;
        if (p > 15) {
            while (flag) {
                p -= 2;
                if (p < 15) {
                    flag = false;
                }
            }
        }
        return p;
    }
    /**
     * <p>生成秘钥</p>
     * @param c			加密次数
     * @param a			加密值
     * @param b			加密盐
     * @return
     */
    static String forE(int c , String a, String b ) {
        c--;
        String createPassword = createPassword(a, b);
        if (c <= 0) {
            return createPassword;
        }
        return forE(c, createPassword, b);
    }
    /**
     * <p>MD5加密</p>
     * @param a
     * @param c
     * @return
     */
    static String createPassword(String a,String c){
        MessageDigest md5;
        String result="";
        try {
            md5 = MessageDigest.getInstance(ENCODE_TYPE);
            md5.update(a.getBytes(UTF_8));
            byte[] temp;
            temp = md5.digest(c.getBytes(UTF_8));
            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.info("获取秘钥失败");
        }
        return result;
    }








}
