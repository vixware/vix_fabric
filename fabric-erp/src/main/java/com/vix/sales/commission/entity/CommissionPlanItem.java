package com.vix.sales.commission.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.sales.common.entity.SalesPerformanceEvaluationMethod;

/** 佣金方案明细 */
public class CommissionPlanItem extends BaseEntity {
 
	private static final long serialVersionUID = 1L;

	/** 条款编码 */
	private String cpiCode;
	/** 名称	 */
	private String name;
	/** 业务类别 */
	private String bizType;
	/** 物料类别 */
	private String itemType;
	/** 物料类别 */
	private String itemTypeName;
	/** 计算基础 */
	private String computeBaseTarget;
	/** 业绩考评方式 */
	//private String performanceEvaluationMethod;
	private SalesPerformanceEvaluationMethod performanceEvaluationMethod;
	/** 业务调整系数 */
	private String adjustCoefficient;
	/** 计算方式 */
	private String computeStyle;
	/** 是否考虑累计业绩 */
	private String isGrandTotal;
	/** 固定佣金比率 */
	private Double fixedCommissionTerm;
	/** 固定佣金 */
	private Double fixedCommission;
	/** 佣金计划 */
	private CommissionPlan commissionPlan;
	/** 佣金比率 */
	private Set<CommissionTerm> commissionTerms = new HashSet<CommissionTerm>();
	
	public CommissionPlanItem(){}

	public String getCpiCode() {
		return cpiCode;
	}

	public void setCpiCode(String cpiCode) {
		this.cpiCode = cpiCode;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getComputeBaseTarget() {
		return computeBaseTarget;
	}

	public void setComputeBaseTarget(String computeBaseTarget) {
		this.computeBaseTarget = computeBaseTarget;
	}

	public SalesPerformanceEvaluationMethod getPerformanceEvaluationMethod() {
		return performanceEvaluationMethod;
	}

	public void setPerformanceEvaluationMethod(
			SalesPerformanceEvaluationMethod performanceEvaluationMethod) {
		this.performanceEvaluationMethod = performanceEvaluationMethod;
	}

	public String getAdjustCoefficient() {
		return adjustCoefficient;
	}

	public void setAdjustCoefficient(String adjustCoefficient) {
		this.adjustCoefficient = adjustCoefficient;
	}

	public String getComputeStyle() {
		return computeStyle;
	}

	public void setComputeStyle(String computeStyle) {
		this.computeStyle = computeStyle;
	}

	public String getIsGrandTotal() {
		return isGrandTotal;
	}

	public void setIsGrandTotal(String isGrandTotal) {
		this.isGrandTotal = isGrandTotal;
	}

	public Double getFixedCommissionTerm() {
		return fixedCommissionTerm;
	}

	public void setFixedCommissionTerm(Double fixedCommissionTerm) {
		this.fixedCommissionTerm = fixedCommissionTerm;
	}

	public Double getFixedCommission() {
		return fixedCommission;
	}

	public void setFixedCommission(Double fixedCommission) {
		this.fixedCommission = fixedCommission;
	}

	public CommissionPlan getCommissionPlan() {
		return commissionPlan;
	}

	public void setCommissionPlan(CommissionPlan commissionPlan) {
		this.commissionPlan = commissionPlan;
	}

	public Set<CommissionTerm> getCommissionTerms() {
		return commissionTerms;
	}

	public void setCommissionTerms(Set<CommissionTerm> commissionTerms) {
		this.commissionTerms = commissionTerms;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
}
