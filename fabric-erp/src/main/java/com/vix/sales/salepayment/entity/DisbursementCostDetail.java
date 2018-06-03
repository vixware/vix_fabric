package com.vix.sales.salepayment.entity;

import com.vix.common.share.entity.BaseEntity;

/** 代垫费用明细 */
public class DisbursementCostDetail extends BaseEntity {
 
	private static final long serialVersionUID = 1L;

	/** 费用项目 */
	private String costItem;
	/** 代垫金额 */
	private Double amount;
	/** 存货名称 */
	private String goodsName;
	/** 规格 */
	private String specification;
	/** 代垫费用 */
	private DisbursementCost disbursementCost;
	
	public DisbursementCostDetail(){}

	public String getCostItem() {
		return costItem;
	}

	public void setCostItem(String costItem) {
		this.costItem = costItem;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public DisbursementCost getDisbursementCost() {
		return disbursementCost;
	}

	public void setDisbursementCost(DisbursementCost disbursementCost) {
		this.disbursementCost = disbursementCost;
	}
}
