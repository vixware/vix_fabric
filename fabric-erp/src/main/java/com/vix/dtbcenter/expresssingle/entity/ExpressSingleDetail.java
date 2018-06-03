package com.vix.dtbcenter.expresssingle.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 快递单明细
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-1
 */
public class ExpressSingleDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 价格 */
	private Double price;
	/** 数量 */
	private Integer count;
	/**
	 * 拖寄物内容
	 */
	private String goodsDescription;
	/**
	 * 数量
	 */
	private Integer goodsQuantity;
	/**
	 * 长(cm)
	 */
	private Double goodsLength;
	/**
	 * 宽(cm)
	 */
	private Double goodsWidth;
	/**
	 * 高(cm)
	 */
	private Double goodsHeight;
	/** 快递单 */
	private ExpressSingle expressSingle;

	public ExpressSingleDetail() {
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public Double getGoodsLength() {
		return goodsLength;
	}

	public void setGoodsLength(Double goodsLength) {
		this.goodsLength = goodsLength;
	}

	public Double getGoodsWidth() {
		return goodsWidth;
	}

	public void setGoodsWidth(Double goodsWidth) {
		this.goodsWidth = goodsWidth;
	}

	public Double getGoodsHeight() {
		return goodsHeight;
	}

	public void setGoodsHeight(Double goodsHeight) {
		this.goodsHeight = goodsHeight;
	}

	public ExpressSingle getExpressSingle() {
		return expressSingle;
	}

	public void setExpressSingle(ExpressSingle expressSingle) {
		this.expressSingle = expressSingle;
	}

}
