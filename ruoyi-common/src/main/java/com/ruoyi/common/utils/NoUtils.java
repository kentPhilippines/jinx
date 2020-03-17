package com.ruoyi.common.utils;

import java.util.UUID;

public class NoUtils {

    public static final String[] CHARS = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    /**
     * 生成8位的uuid
     *
     * @return 8位的uuid
     */
    public static String shortUUID() {
        StringBuffer sb = new StringBuffer(8);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        for (int i = 0; i < 8; i++) {
            String tmp = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(tmp, 16);
            sb.append(CHARS[x % 0x3E]);
        }
        return sb.toString();
    }

}
