/**
 * 
 */
package com.vix.drp.retailPriceSurvey.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 零售价格调查明细
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class RetailPriceSurveyDetail extends BaseEntity {
	private static final long serialVersionUID = -1259555264722771329L;
	/**
	 * 产品名称
	 */
	private String itemName;
	/**
	 * 零售店名称
	 */
	private String retailStores;
	/**
	 * 标准零售价格
	 */
	private Double standardRetailPrice;
	/**
	 * 金额折扣
	 */
	private Double discountAmount;

	/**
	 * 是否捆绑销售
	 */
	private String isBundling;

	/**
	 * 是否M送N销售
	 */
	private String isMSendNSales;

	private RetailPriceSurvey retailPriceSurvey;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getRetailStores() {
		return retailStores;
	}

	public void setRetailStores(String retailStores) {
		this.retailStores = retailStores;
	}

	public Double getStandardRetailPrice() {
		return standardRetailPrice;
	}

	public void setStandardRetailPrice(Double standardRetailPrice) {
		this.standardRetailPrice = standardRetailPrice;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getIsBundling() {
		return isBundling;
	}

	public void setIsBundling(String isBundling) {
		this.isBundling = isBundling;
	}

	public String getIsMSendNSales() {
		return isMSendNSales;
	}

	public void setIsMSendNSales(String isMSendNSales) {
		this.isMSendNSales = isMSendNSales;
	}

	public RetailPriceSurvey getRetailPriceSurvey() {
		return retailPriceSurvey;
	}

	public void setRetailPriceSurvey(RetailPriceSurvey retailPriceSurvey) {
		this.retailPriceSurvey = retailPriceSurvey;
	}

}
