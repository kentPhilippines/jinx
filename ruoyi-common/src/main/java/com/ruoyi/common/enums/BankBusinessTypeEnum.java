package com.ruoyi.common.enums;

/**
 * @author water
 */
public enum BankBusinessTypeEnum {
    Type_1("电子汇入存入"),
    Type_2("他行来账"),
    Type_3("他行汇入"),
    Type_4("转存交易"),
    Type_5("工时交易"),
    Type_6("跨行转出支出"),
    Type_7("转至他行"),
    Type_8("账户汇款"),
    Type_9("汇款汇入"),
    Type_10("支付支出"),
    Type_11("手机银行支取(跨行支付)"),
    Type_12("互联汇入"),
    Type_13("入账"),
    Type_14("跨行转出"),
    Type_15("跨行转入存入"),
    Type_16("跨行转账转入"),
    Type_17("存款"),
    Type_18("收入（代付）"),
    Type_19("收入（转账）");


    /**
     * 业务类型名称
     */
    private String typeName;

    BankBusinessTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return this.name();
    }

    /**
     * 根据模式类型查询枚举名称对象
     *
     * @param typeName
     * @return
     */
    public static BankBusinessTypeEnum getEnum(String typeName) {
        for (BankBusinessTypeEnum bankBusinessTypeEnum : BankBusinessTypeEnum.values()) {
            if (bankBusinessTypeEnum.typeName.equalsIgnoreCase(typeName)) {
                return bankBusinessTypeEnum;
            }
        }
        return null;
    }
}
