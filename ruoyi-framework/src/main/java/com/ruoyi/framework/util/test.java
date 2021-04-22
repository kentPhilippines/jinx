package com.ruoyi.framework.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class test {
    public static void main(String[] args) {
        String s = new Md5Hash("coco" + "123456" + "4a9224").toHex().toString();

        System.out.println(s);
    }
}
