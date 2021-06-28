package com.ruoyi.common.enums;

/**
 * @author water
 */
public enum BankNameEnum {
    ICBC("工商银行"),
    CCB("建设银行"),
    PSBC("邮储银行"),
    CMB("招商银行"),
    GRC("广东农信"),
    CIB("兴业银行"),
    BC("中国银行"),
    SPDB("浦发银行"),
    CRBC("珠海华润银行"),
    CITIC("中信银行"),
    PAB("平安银行"),
    CMSB("民生银行"),
    GUILINBANK("桂林银行"),
    ABC("中国农业银行");

    /**
     * 业务类型名称
     */
    public String bankName;

    BankNameEnum(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return this.name();
    }

    /**
     * 根据模式类型查询枚举名称对象
     *
     * @param bankName
     * @return
     */
    public static BankNameEnum getEnum(String bankName) {
        for (BankNameEnum bankBusinessTypeEnum : BankNameEnum.values()) {
            if (bankBusinessTypeEnum.bankName.equalsIgnoreCase(bankName)) {
                return bankBusinessTypeEnum;
            }
        }
        return null;
    }
}
