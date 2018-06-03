package com.vix.drp.channelPriceSurvey.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 渠道价格
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class ChannelPrice extends BaseEntity {
	private static final long serialVersionUID = 8151920822925745828L;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 渠道价格
	 */
	private Double channelPrice;
	/**
	 * 促销价格
	 */
	private Double promotionalPrice;
	/**
	 * 摆放返点
	 */
	private Double layoutRebate;
	/**
	 * 市场准入
	 */
	private String marketAccess;
	/**
	 * 账期
	 */
	private Integer paymentTerm;
	/**
	 * 现付贴现
	 */
	private Double cashDiscount;
	/**
	 * 赠品率
	 */
	private Double premiumRate;
	/**
	 * 批量折扣
	 */
	private Double bulkDiscount;

	/**
	 * 时段返点
	 */
	private Double timePeriod;
	/**
	 * 年终返点
	 */
	private Double yearEndRebate;
	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getChannelPrice() {
		return channelPrice;
	}

	public void setChannelPrice(Double channelPrice) {
		this.channelPrice = channelPrice;
	}

	public Double getPromotionalPrice() {
		return promotionalPrice;
	}

	public void setPromotionalPrice(Double promotionalPrice) {
		this.promotionalPrice = promotionalPrice;
	}

	public Double getLayoutRebate() {
		return layoutRebate;
	}

	public void setLayoutRebate(Double layoutRebate) {
		this.layoutRebate = layoutRebate;
	}

	public String getMarketAccess() {
		return marketAccess;
	}

	public void setMarketAccess(String marketAccess) {
		this.marketAccess = marketAccess;
	}

	public Integer getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(Integer paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public Double getCashDiscount() {
		return cashDiscount;
	}

	public void setCashDiscount(Double cashDiscount) {
		this.cashDiscount = cashDiscount;
	}

	public Double getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(Double premiumRate) {
		this.premiumRate = premiumRate;
	}

	public Double getBulkDiscount() {
		return bulkDiscount;
	}

	public void setBulkDiscount(Double bulkDiscount) {
		this.bulkDiscount = bulkDiscount;
	}

	public Double getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(Double timePeriod) {
		this.timePeriod = timePeriod;
	}

	public Double getYearEndRebate() {
		return yearEndRebate;
	}

	public void setYearEndRebate(Double yearEndRebate) {
		this.yearEndRebate = yearEndRebate;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
