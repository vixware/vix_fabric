package com.vix.crm.business.vo;

/**
 * 商品销售信息
 * 
 * @author Think
 *
 */
public class GoodsSaleInformation {
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 成交金额
	 */
	private Double amount;
	/**
	 * 成交件数
	 */
	private Long quantity;
	/**
	 * 件单价
	 */
	private Double unitPrice;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
