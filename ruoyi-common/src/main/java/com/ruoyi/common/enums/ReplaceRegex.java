package com.ruoyi.common.enums;

/**
 * @author water
 */

public enum ReplaceRegex {
    word_1("账户"),
    word_2("对方户名"),
    word_3("于"),
    word_4("向"),
    word_5("完成"),
    word_6("跨行"),
    word_7("收款人"),
    word_8("尾号"),
    word_9("互联汇入");
    /**
     * 第一次正则截取
     */
    private String word;

    ReplaceRegex(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }


    public static void main(String[] args) {

    }
}
