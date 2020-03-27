package com.ruoyi.common.enums;

import com.google.common.collect.Maps;
import com.ruoyi.common.exception.BusinessException;

import java.util.Map;

/**
 * 接单状态，代付状态
 */
public enum DeductStatusEnum {

    DEDUCT_STATUS_APPLY(1, "申请"),
    DEDUCT_STATUS_PROCESS(2, "审核中"),
    DEDUCT_STATUS_SUCCESS(3, "成功"),
    DEDUCT_STATUS_FAIL(4, "失败"),
    DEDUCT_STATUS_HANG(5, "挂起");

    private Integer code;
    private String desc;

    DeductStatusEnum(Integer code, String desc) {
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

    private static final Map<Integer, DeductStatusEnum> HOLDER = Maps.newHashMap();

    static {
        for (DeductStatusEnum value : DeductStatusEnum.values()) {
            HOLDER.put(value.code, value);
        }
    }

    public static DeductStatusEnum resolve(Integer code) {
        if (HOLDER.containsKey(code)) {
            return HOLDER.get(code);
        }
        throw new BusinessException("参数错误");
    }

    public boolean matches(Integer code) {
        return this.getCode() == code;
    }
}
