package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/** 
 * 赠品
 * @author Administrator
 *
 */
public class PriceConditionGift extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 物料 */
	private Item item;
	/** 数量 */
	private Double count;
	/** 规格 */
	private String specification;
	/** 单价 */
	private Double price;
	/** 备注 */
	private String momo;
	/** 数量区间 */
	private PriceConditionCountArea priceConditionCountArea;
	/** 价格区间 */
	private PriceConditionPriceArea priceConditionPriceArea;
	
	public PriceConditionGift(){}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMomo() {
		return momo;
	}

	public void setMomo(String momo) {
		this.momo = momo;
	}

	public PriceConditionCountArea getPriceConditionCountArea() {
		return priceConditionCountArea;
	}

	public void setPriceConditionCountArea(
			PriceConditionCountArea priceConditionCountArea) {
		this.priceConditionCountArea = priceConditionCountArea;
	}

	public PriceConditionPriceArea getPriceConditionPriceArea() {
		return priceConditionPriceArea;
	}

	public void setPriceConditionPriceArea(
			PriceConditionPriceArea priceConditionPriceArea) {
		this.priceConditionPriceArea = priceConditionPriceArea;
	}
}
