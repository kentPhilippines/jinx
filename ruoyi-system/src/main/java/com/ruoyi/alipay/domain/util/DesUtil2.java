package com.ruoyi.alipay.domain.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.DES;

public class DesUtil2 {
    private static final String PASSWORD = "~~7s8^2IUis&8*;^%26Gys->;t24%$+`_Tys52*(";
    private static final String SALT = "&ys1_^2I";
    public static void main(String[] args) {
        String msg1 = "55555555555555555555";
        String msg2 = "6e908db085f7ce86aa36a659bce84d5d";
        String s = encryptHex(msg1);
        System.out.println(s);
        String s1 = decryptStr(msg2);
        System.out.println(s1);

    }

    private static DES des;

    static {
        des = new DES(Mode.CFB, Padding.PKCS5Padding, PASSWORD.getBytes(), SALT.getBytes());
    }

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encryptHex(String str) {
        if(StrUtil.isEmpty(str)){
            return "";
        }
        String s = des.encryptHex(str);
        return s;
    }

    /**
     * 解密
     * @param str
     * @return
     */
    public static String decryptStr(String str) {
        String s = "";
        try {
              s = des.decryptStr(str);
        } catch (Exception e) {
         //   System.exit(1);//敏感信息解密失败 直接服务器死机
            return str;
        }
        return s;
    }


}
