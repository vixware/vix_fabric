package com.vix.sales.order.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/** 客户定制产品(物料) */
public class OptionalProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 订单子项 */
	private SaleOrderItem saleOrderItem;
	/** 物料 */
	private Item item;
	/** 销售数量 */
	private Integer amount;
	/** 计量单位 */
	private String unit;
	/** 辅助计量单位 */
	private String assitUnit;
	/** 主辅计量单位换算率 */
	private Double unitExchange;
	/** 税率 */
	private Double tax;
	/** 单价 */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 净单价 */
	private Double netPrice;
	/** 含税单价 */
	private Double taxPrice;

	public OptionalProduct(){}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAssitUnit() {
		return assitUnit;
	}

	public void setAssitUnit(String assitUnit) {
		this.assitUnit = assitUnit;
	}

	public Double getUnitExchange() {
		return unitExchange;
	}

	public void setUnitExchange(Double unitExchange) {
		this.unitExchange = unitExchange;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}
}
