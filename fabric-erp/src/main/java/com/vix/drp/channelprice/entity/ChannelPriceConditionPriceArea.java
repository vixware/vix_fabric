package com.vix.drp.channelprice.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 价格区间 com.vix.drp.channelprice.entity.ChannelPriceConditionPriceArea
 *
 * @author bjitzhang
 *
 * @date 2015年3月17日
 */
public class ChannelPriceConditionPriceArea extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 条件类型 */
	private String conditionType;
	/** 累计金额开始 */
	private Double startTotalAmount;
	/** 结束价格 */
	private Double endTotalAmount;
	/** 区间价格类型 all:全部，refund:返款，discount:折扣 */
	private String areaPriceType;
	/** 返款 */
	private Double refund;
	/** 折扣 */
	private Double discount;
	/** 价格 */
	private Double price;
	/** 物料 */
	//	private Item item;
	/** 定价条件 */
	private ChannelPriceCondition channelPriceCondition;

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public Double getStartTotalAmount() {
		return startTotalAmount;
	}

	public void setStartTotalAmount(Double startTotalAmount) {
		this.startTotalAmount = startTotalAmount;
	}

	public Double getEndTotalAmount() {
		return endTotalAmount;
	}

	public void setEndTotalAmount(Double endTotalAmount) {
		this.endTotalAmount = endTotalAmount;
	}

	public String getAreaPriceType() {
		return areaPriceType;
	}

	public void setAreaPriceType(String areaPriceType) {
		this.areaPriceType = areaPriceType;
	}

	public Double getRefund() {
		return refund;
	}

	public void setRefund(Double refund) {
		this.refund = refund;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ChannelPriceCondition getChannelPriceCondition() {
		return channelPriceCondition;
	}

	public void setChannelPriceCondition(ChannelPriceCondition channelPriceCondition) {
		this.channelPriceCondition = channelPriceCondition;
	}

}
