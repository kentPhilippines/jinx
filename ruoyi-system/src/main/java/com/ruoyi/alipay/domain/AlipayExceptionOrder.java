package com.ruoyi.alipay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 拦截订单对象 alipay_exception_order
 * 
 * @author ruoyi
 * @date 2020-03-19
 */
public class AlipayExceptionOrder extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 数据id */
	private Long id;

	/** 流水单号 */
	@Excel(name = "流水单号")
	private String orderexceptId;

	/** 关联订单单号 */
	@Excel(name = "关联订单单号")
	private String orderId;

	/** 异常订单状态 */
	@Excel(name = "异常订单状态")
	private Integer exceptStatus;

	/** 异常类型 */
	@Excel(name = "异常类型")
	private Integer exceptType;

	/** 订单关联商户账号 */
	@Excel(name = "订单关联商户账号")
	private String orderAccount;

	/** 异常订单金额 */
	@Excel(name = "异常订单金额")
	private String exceptOrderAmount;

	/** 异常订单生成IP(源头ip) */
	@Excel(name = "异常订单生成IP(源头ip)")
	private String orderGenerationip;

	/** 异常说明 */
	@Excel(name = "异常说明")
	private String explains;

	/** 操作人 */
	@Excel(name = "操作人")
	private String operation;

	/** 数据修改时间 */
	@Excel(name = "数据修改时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date submittime;

	/** 数据提交系统 */
	@Excel(name = "数据提交系统")
	private String submitsystem;

	/** 状态:1可使用；0不可使用 */
	@Excel(name = "状态:1可使用；0不可使用")
	private Integer status;

	/** 保留字段(添加业务使用) */
	@Excel(name = "保留字段(添加业务使用)")
	private String retain1;

	/** 保留字段(添加业务使用) */
	@Excel(name = "保留字段(添加业务使用)")
	private String retain2;

	/** 保留字段(添加业务使用) */
	@Excel(name = "保留字段(添加业务使用)")
	private String retain3;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setOrderexceptId(String orderexceptId) {
		this.orderexceptId = orderexceptId;
	}

	public String getOrderexceptId() {
		return orderexceptId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setExceptStatus(Integer exceptStatus) {
		this.exceptStatus = exceptStatus;
	}

	public Integer getExceptStatus() {
		return exceptStatus;
	}

	public void setExceptType(Integer exceptType) {
		this.exceptType = exceptType;
	}

	public Integer getExceptType() {
		return exceptType;
	}

	public void setOrderAccount(String orderAccount) {
		this.orderAccount = orderAccount;
	}

	public String getOrderAccount() {
		return orderAccount;
	}

	public void setExceptOrderAmount(String exceptOrderAmount) {
		this.exceptOrderAmount = exceptOrderAmount;
	}

	public String getExceptOrderAmount() {
		return exceptOrderAmount;
	}

	public void setOrderGenerationip(String orderGenerationip) {
		this.orderGenerationip = orderGenerationip;
	}

	public String getOrderGenerationip() {
		return orderGenerationip;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public String getExplains() {
		return explains;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}

	public Date getSubmittime() {
		return submittime;
	}

	public void setSubmitsystem(String submitsystem) {
		this.submitsystem = submitsystem;
	}

	public String getSubmitsystem() {
		return submitsystem;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setRetain1(String retain1) {
		this.retain1 = retain1;
	}

	public String getRetain1() {
		return retain1;
	}

	public void setRetain2(String retain2) {
		this.retain2 = retain2;
	}

	public String getRetain2() {
		return retain2;
	}

	public void setRetain3(String retain3) {
		this.retain3 = retain3;
	}

	public String getRetain3() {
		return retain3;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
				.append("orderexceptId", getOrderexceptId()).append("orderId", getOrderId())
				.append("exceptStatus", getExceptStatus()).append("exceptType", getExceptType())
				.append("orderAccount", getOrderAccount()).append("exceptOrderAmount", getExceptOrderAmount())
				.append("orderGenerationip", getOrderGenerationip()).append("explains", getExplains())
				.append("operation", getOperation()) 
				.append("submittime", getSubmittime()).append("submitsystem", getSubmitsystem())
				.append("status", getStatus()).append("retain1", getRetain1()).append("retain2", getRetain2())
				.append("retain3", getRetain3()).toString();
	}
}
