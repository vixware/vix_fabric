/**
 * 
 */
package com.vix.dtbcenter.costestimating.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 订单成本明细
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-13
 */
public class SaleOrderCostItem extends BaseEntity {
	private static final long serialVersionUID = -810666258828806384L;
	/* 费用代码 */
	private String costCode;
	/* 费用名称 */
	private String costName;
	/* 组别代码 */
	private String groupCode;
	/* 类型 */
	private String costType;
	/* 复合类型 */
	private String compoundTypes;
	/* 计算公式 */
	private String calculationFormula;
	/* 费用额 */
	private Double payments;
	/* 单位 */
	private String unit;
	/**
	 * 订单成本
	 */
	private SaleOrderCost saleOrderCost;

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getCompoundTypes() {
		return compoundTypes;
	}

	public void setCompoundTypes(String compoundTypes) {
		this.compoundTypes = compoundTypes;
	}

	public String getCalculationFormula() {
		return calculationFormula;
	}

	public void setCalculationFormula(String calculationFormula) {
		this.calculationFormula = calculationFormula;
	}

	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public SaleOrderCost getSaleOrderCost() {
		return saleOrderCost;
	}

	public void setSaleOrderCost(SaleOrderCost saleOrderCost) {
		this.saleOrderCost = saleOrderCost;
	}

}
