package com.vix.sales.commission.entity;

import com.vix.common.share.entity.BaseEntity;

/** 佣金比率 用于给销售人员设定销售目标金额，类似销售完成的任务金额。 */
public class CommissionTerm extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 佣金比率编码 */
	private String crCode;
	/** 佣金比率名称 */
	private String crName;
	/** 类型 */
	private String type;
	/** 数量单位 */
	private String amountUnit;
	/** 金额单位 */
	private String sumUnit;
	/** 从 */
	private Double from;
	/** 到 */
	private Double to;
	/** 返款比率 */
	private Double returnTerm;
	/** 返款金额 */
	private Double returnAmount;
	/** 佣金计划明细 */
	private CommissionPlanItem commissionPlanItem;
	
	public CommissionTerm(){}

	public String getCrCode() {
		return crCode;
	}

	public void setCrCode(String crCode) {
		this.crCode = crCode;
	}

	public String getCrName() {
		return crName;
	}

	public void setCrName(String crName) {
		this.crName = crName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public String getSumUnit() {
		return sumUnit;
	}

	public void setSumUnit(String sumUnit) {
		this.sumUnit = sumUnit;
	}

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

	public Double getReturnTerm() {
		return returnTerm;
	}

	public void setReturnTerm(Double returnTerm) {
		this.returnTerm = returnTerm;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public CommissionPlanItem getCommissionPlanItem() {
		return commissionPlanItem;
	}

	public void setCommissionPlanItem(CommissionPlanItem commissionPlanItem) {
		this.commissionPlanItem = commissionPlanItem;
	}
}
