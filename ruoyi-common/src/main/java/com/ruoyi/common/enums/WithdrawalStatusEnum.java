package com.ruoyi.common.enums;

import com.google.common.collect.Maps;
import com.ruoyi.common.exception.BusinessException;

import java.util.Map;

/**
 * 加减款状态
 */
public enum WithdrawalStatusEnum {

    WITHDRAWAL_STATUS_APPLY(0, "申请"),
    WITHDRAWAL_STATUS_PROCESS(1, "审核中"),
    WITHDRAWAL_STATUS_SUCCESS(2, "成功"),
    WITHDRAWAL_STATUS_FAIL(3, "失败"),
    WITHDRAWAL_STATUS_HANG(4, "挂起");

    private Integer code;
    private String desc;

    WithdrawalStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private static final Map<Integer, WithdrawalStatusEnum> HOLDER = Maps.newHashMap();

    static {
        for (WithdrawalStatusEnum value : WithdrawalStatusEnum.values()) {
            HOLDER.put(value.code, value);
        }
    }

    public static WithdrawalStatusEnum resolve(Integer code) {
        if (HOLDER.containsKey(code)) {
            return HOLDER.get(code);
        }
        throw new BusinessException("参数错误");
    }

    public boolean matches(Integer code) {
        return this.getCode() == code;
    }
}
