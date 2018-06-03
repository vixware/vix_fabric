package com.vix.sales.refund.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;

/** 返款规则明细 */
public class ReturnRuleItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 从 */
	private Double from;
	/** 到 */
	private Double to;
	/** 计量单位 */
	private String unit;
	/** 返款比率 */
	private Double ratio;
	/** 币种 */
	private CurrencyType currencyType;
	/** 返款规则 */
	private ReturnRule returnRule;
	
	public ReturnRuleItem(){}

	public Double getFrom() {
		return from;
	}

	public void setFrom(Double from) {
		this.from = from;
	}

	public Double getTo() {
		return to;
	}

	public void setTo(Double to) {
		this.to = to;
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

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public ReturnRule getReturnRule() {
		return returnRule;
	}

	public void setReturnRule(ReturnRule returnRule) {
		this.returnRule = returnRule;
	}
}
