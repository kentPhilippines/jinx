package com.ruoyi.common.enums;

import com.google.common.collect.Maps;
import com.ruoyi.common.exception.BusinessException;

import java.util.Map;

public enum RefundDeductType {
    REFUND_TYPE(1, "加款申请"),
    DEDUCT_TYPE(2, "减款申请");

    private Integer code;
    private String desc;

    RefundDeductType(Integer code, String desc) {
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

    private static final Map<Integer, RefundDeductType> HOLDER = Maps.newHashMap();

    static {
        for (RefundDeductType value : RefundDeductType.values()) {
            HOLDER.put(value.code, value);
        }
    }

    public static RefundDeductType resolve(Integer code) {
        if (HOLDER.containsKey(code)) {
            return HOLDER.get(code);
        }
        throw new BusinessException("参数错误");
    }

    public boolean matches(Integer code) {
        return this.getCode() == code;
    }
}
