package com.ruoyi.alipay.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户对象 user_info
 *
 * @author kkkk
 * @date 2024-03-06
 */
@Data
public class UserInfo   implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private String name  ;

    /**
     * 余额
     */
    @Excel(name = "余额")
    private BigDecimal amount = new BigDecimal(0);

    /**
     * 货币类型
     */
    @Excel(name = "货币类型")
    private String currency  ;

    /**
     * 版本
     */
    @Excel(name = "版本")
    private Long version;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer userStatus   ;
    @Excel(name = "代理商")
    private String agent  ;


}
