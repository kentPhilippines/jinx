package com.ruoyi.common.enums;

import com.google.common.collect.Maps;
import com.ruoyi.common.exception.BusinessException;

import java.util.Map;

public enum RefundDeductType {
    REFUND_TYPE(1, "加款申请"),
    DEDUCT_TYPE(2, "减款申请"),
    DEDUCT_FREEZE_TYPE(3, "冻结申请"),
    REFUND_FREEZE_TYPE(4, "解冻申请"),
    REFUND_QUOTA_TYPE(5, "增加授权额度"),
    DELETE_QUOTA_TYPE(6, "减少授权额度");

    private Integer code;
    private String desc;

    public static Map<Integer, RefundDeductType> getHOLDER() {
        return HOLDER;
    }

    RefundDeductType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
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
