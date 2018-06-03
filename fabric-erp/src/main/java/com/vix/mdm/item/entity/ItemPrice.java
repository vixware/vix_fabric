package com.vix.mdm.item.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/** 价格预警 */
public class ItemPrice extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/** 价格类型： 销售价格：price 采购价格： purchasePrice */
	private String priceType;
	/** 物料sku */
	private String itemSku;
	/** 启用时间 */
	private Date startDate;
	/** 禁用时间 */
	private Date endDate;
	/** 实际价格 */
	private Double price;
	/** 最高价格 */
	private Double maxPrice;
	/** 最低价格 */
	private Double minPrice;
	/** 物料 */
	private Item item;
	
	public ItemPrice(){}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getItemSku() {
		return itemSku;
	}

	public void setItemSku(String itemSku) {
		this.itemSku = itemSku;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
