package com.ruoyi.alipay.domain.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.DES;

public class DesUtil {
    private static final String PASSWORD = "HGSJ7^2I";
    private static final String SALT = "HGSJ7^2I";
    public static void main(String[] args) {
        String msg1 = "219386181265192362193";
        DES des = new DES(Mode.CFB, Padding.PKCS5Padding, PASSWORD.getBytes(), SALT.getBytes());
        String s = des.encryptHex(msg1);
        System.out.println(s);
        String s1 = des.decryptStr(s);
        System.out.println(
                s1
        );
    }

    private static DES des;

    public static DES getDes() {
        return des;
    }
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
