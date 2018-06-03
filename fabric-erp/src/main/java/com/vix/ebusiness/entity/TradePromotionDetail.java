/**
 * 
 */
package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author zhanghaibing
 * @date 2012-4-10
 */
public class TradePromotionDetail extends BaseEntity {

	private static final long serialVersionUID = 9039385568741741366L;

	private Long tradeId;
	private String tradeNo;
	private String outerSkuId;
	private String promotionName;
	private String discountFee;
	private String giftItemName;
	private String promotionType;

	private Order order;

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOuterSkuId() {
		return outerSkuId;
	}

	public void setOuterSkuId(String outerSkuId) {
		this.outerSkuId = outerSkuId;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}

	public String getGiftItemName() {
		return giftItemName;
	}

	public void setGiftItemName(String giftItemName) {
		this.giftItemName = giftItemName;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
