/**
 * 
 */
package com.vix.dtbcenter.costestimating.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 订单成本估算模板
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-13
 */
public class SaleOrderCost extends BaseEntity {

	private static final long serialVersionUID = -2756542025641982781L;
	/** 销售订单编码 */
	private String saleOrderCode;
	/** 成本项目 */
	private String costItem;
	/** 成本费用 */
	private Double costCharge;
	/** 成本项费率 */
	private Double costItemRate;
	/** 数量 */
	private Double amount;
	/** 成本项单位 */
	private String costUnit;
	/** 成本项分类 */
	private String costCatalog;
	/**
	 * 直接成本
	 */
	private Double directCost;
	/**
	 * 间接成本
	 */
	private Double indirectCost;
	/**
	 * 费用总额
	 */
	private Double total;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 订单成本明细
	 */
	private Set<SaleOrderCostItem> saleOrderCostItems = new HashSet<SaleOrderCostItem>();

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getCostItem() {
		return costItem;
	}

	public void setCostItem(String costItem) {
		this.costItem = costItem;
	}

	public Double getCostCharge() {
		return costCharge;
	}

	public void setCostCharge(Double costCharge) {
		this.costCharge = costCharge;
	}

	public Double getCostItemRate() {
		return costItemRate;
	}

	public void setCostItemRate(Double costItemRate) {
		this.costItemRate = costItemRate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCostUnit() {
		return costUnit;
	}

	public void setCostUnit(String costUnit) {
		this.costUnit = costUnit;
	}

	public String getCostCatalog() {
		return costCatalog;
	}

	public void setCostCatalog(String costCatalog) {
		this.costCatalog = costCatalog;
	}

	public Double getDirectCost() {
		return directCost;
	}

	public void setDirectCost(Double directCost) {
		this.directCost = directCost;
	}

	public Double getIndirectCost() {
		return indirectCost;
	}

	public void setIndirectCost(Double indirectCost) {
		this.indirectCost = indirectCost;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Set<SaleOrderCostItem> getSaleOrderCostItems() {
		return saleOrderCostItems;
	}

	public void setSaleOrderCostItems(Set<SaleOrderCostItem> saleOrderCostItems) {
		this.saleOrderCostItems = saleOrderCostItems;
	}

}
