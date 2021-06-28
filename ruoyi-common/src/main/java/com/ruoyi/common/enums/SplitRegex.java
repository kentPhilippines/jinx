package com.ruoyi.common.enums;

/**
 * @author water
 */

public enum SplitRegex {
    BankNameRegex("((建设银行)|(邮储银行)|(工商银行)|(中国农业银行)|(招商银行)|(广东农信)|(兴业银行)|(中国银行)|" +
            "(浦发银行)|(珠海华润银行)|(中信银行)|(平安银行)|(民生银行)|(桂林银行))", null),
    TypeRegex("(支付支出|存款|电子汇入存入|他行来账|他行汇入|转存交易|工时交易|跨行转出支出|转至他行|账户汇款|汇款汇入|手机银行支取\\(跨行支付\\)|互联汇入|入账|跨行转出|跨行转入存入|跨行转账转入|收入\\（代付\\）|收入\\（转账\\）)",
            null),
    money("(\\d{0,}\\.\\d{2}元)|(人民币-{0,1}\\d{0,}\\.\\d{2})|(余额\\d{0,}\\.\\d{2})|(\\d{0,}(,\\d\\d\\d){0,}.\\d{2}元)|(\\d{1,}(,\\d\\d\\d){0,}元)", "(-{0,1}\\d{0,}\\.\\d{2})|(\\d{1,})"),
    TransactionDateRegex("(((\\d{1,2}年){0,}(\\d{1,2}月){0,}(\\d{1,2}日){0,}\\d{1,2}(:|时)\\d{1,2}分{0,})" +
            "|(\\d{1,2}月\\d{1,2}日))", null),
    myselfTailNumberRegex("((您尾号|账户|您存款账户|您尾号为|您尾数)\\*{0,1}\\d{3,}\\*{0,1}" +
            "(账户|卡|的储蓄卡|的中信卡|的账户|的卡号|于|汇款汇入收入|))", "(\\*{0,1}\\d{3,4}\\*{0,1})"),
    counterpartyTailNumberRegex("((账户|对方账户尾号：)\\d{3,4}(向您|。))|(向\\D{2,}尾号\\d{3,4}账户)",
            "(\\*{0,1}\\d{3,4}\\*{0,1})"),
    counterpartyAccountNameRegex("(】[\\u4e00-\\u9fa5]{2,5}\\d{1,2}月)|(\\d{2}:\\d{2}\\D{1,}账户)|((对方户名|收款人|\\[互联汇入\\])(((：){0,1}|(:)))[\\u4e00-\\u9fa5]{2,5})|(向[\\u4e00-\\u9fa5]{2,5}(尾号|跨行|完成))",
            "[\\u4e00-\\u9fa5]{2,}"),
    ;
    /**
     * 第一次正则截取
     */
    private String firstRegex;
    /**
     * 第二次正则截取
     */
    private String secondRegex;


    SplitRegex(String firstRegex, String secondRegex) {
        this.firstRegex = firstRegex;
        this.secondRegex = secondRegex;
    }

    public String getFirstRegex() {
        return firstRegex;
    }

    public void setFirstRegex(String firstRegex) {
        this.firstRegex = firstRegex;
    }

    public String getSecondRegex() {
        return secondRegex;
    }

    public void setSecondRegex(String secondRegex) {
        this.secondRegex = secondRegex;
    }

    public static void main(String[] args) {

    }
}
