package com.ruoyi.common.enums;

/**
 * 操作状态
 *
 * @author ruoyi
 */
public enum TransactionTypeEnum {
    income("收入"),
    expenditure("支出");

    private String val;

    TransactionTypeEnum(String val) {
        this.val = val;
    }
}


