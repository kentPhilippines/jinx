package com.ruoyi.alipay.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 产品列表对象 alipay_product_list
 */
@Data
public class AlipayProductEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 数据id */
    private Integer id;

    /** 系统Id【产品系统Id】*/
    @Excel(name = "产品系统Id")
    private String productId;

    /** 产品code */
    @Excel(name = "产品code")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品备注 */
    @Excel(name = "产品备注")
    private String describe;

    /** 数据创建时间 */
    @Excel(name = "数据创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 最后一次数据修改时间 */
    @Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 数据是否可用：1 可用 2 无用 */
    @Excel(name = "状态:1可使用；0不可使用")
    private Integer status;

    /** 预留1 */
    @Excel(name = "预留1")
    private String retain1;

    /** 预留2 */
    @Excel(name = "预留2")
    private String retain2;

    /** 预留3 */
    @Excel(name = "预留3")
    private String retain3;

    /** 预留4 */
    @Excel(name = "预留4")
    private String retain4;
}
