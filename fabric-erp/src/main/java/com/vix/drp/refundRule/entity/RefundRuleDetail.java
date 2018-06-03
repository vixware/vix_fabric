package com.vix.drp.refundRule.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 返款规则明细
 */

public class RefundRuleDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6979309143910389710L;
	/** 从 */
	private Double fieldFrom;
	/** 到 */
	private Double fieldTo;
	/** 计量单位 */
	private String unit;
	/** 返款比率 */
	private Double ratio;
	/** 币种 */
	private String currency;
	/** 返款比率类型 */
	private String ratioType;
	/**
	 * 返款规则
	 */
	private RefundRule refundRule;

	public Double getFieldFrom() {
		return fieldFrom;
	}

	public void setFieldFrom(Double fieldFrom) {
		this.fieldFrom = fieldFrom;
	}

	public Double getFieldTo() {
		return fieldTo;
	}

	public void setFieldTo(Double fieldTo) {
		this.fieldTo = fieldTo;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRatioType() {
		return ratioType;
	}

	public void setRatioType(String ratioType) {
		this.ratioType = ratioType;
	}

	public RefundRule getRefundRule() {
		return refundRule;
	}

	public void setRefundRule(RefundRule refundRule) {
		this.refundRule = refundRule;
	}

}