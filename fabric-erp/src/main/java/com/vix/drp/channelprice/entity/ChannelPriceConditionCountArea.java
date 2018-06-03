package com.vix.drp.channelprice.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 数量区间 com.vix.drp.channelprice.entity.ChannelPriceConditionCountArea
 *
 * @author bjitzhang
 *
 * @date 2015年3月17日
 */
public class ChannelPriceConditionCountArea extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 条件类型 */
	private String conditionType;
	/** 开始数量 */
	private Double startCount;
	/** 结束数量 */
	private Double endCount;
	/** 单位 */
	private String unit;
	/** 区间价格类型 all:全部，price:价格，discount:折扣 */
	private String areaPriceType;
	/** 价格 */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 定价条件 */
	private ChannelPriceCondition channelPriceCondition;

	/** 物料 */
	//private Item item;

	public ChannelPriceConditionCountArea() {
	}

	public ChannelPriceCondition getChannelPriceCondition() {
		return channelPriceCondition;
	}

	public void setChannelPriceCondition(ChannelPriceCondition channelPriceCondition) {
		this.channelPriceCondition = channelPriceCondition;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public Double getStartCount() {
		return startCount;
	}

	public void setStartCount(Double startCount) {
		this.startCount = startCount;
	}

	public Double getEndCount() {
		return endCount;
	}

	public void setEndCount(Double endCount) {
		this.endCount = endCount;
	}

	public String getUnit() {
		return unit;
	}

	public String getAreaPriceType() {
		if (null == areaPriceType || "".equals(areaPriceType)) {
			return "all";
		}
		return areaPriceType;
	}

	public void setAreaPriceType(String areaPriceType) {
		this.areaPriceType = areaPriceType;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

}
