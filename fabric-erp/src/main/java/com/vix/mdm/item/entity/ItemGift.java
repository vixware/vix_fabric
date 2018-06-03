package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.business.entity.PromotionRule;

/**
 * 赠品
 * 
 * @author jackie
 *
 */
public class ItemGift extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 促销规则 */
	private PromotionRule promotionRule;
	/** 促销规则明细 */
	private PromotionRule promotionRuleDetail;
	/** 商品 */
	private Item item;
	/** 商品sku */
	private String sku;
	/** 赠品数量 */
	private Integer giftCount;

	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	public PromotionRule getPromotionRuleDetail() {
		return promotionRuleDetail;
	}

	public void setPromotionRuleDetail(PromotionRule promotionRuleDetail) {
		this.promotionRuleDetail = promotionRuleDetail;
	}

	public Item getItem() {
		return item;
	}
	
	public String getItemId() {
		if(item != null){
			return item.getId();
		}
		return "";
	}
	
	public String getItemName() {
		if(item != null){
			return item.getName();
		}
		return "";
	}
	
	public String getItemCode() {
		if(item != null){
			return item.getCode();
		}
		return "";
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(Integer giftCount) {
		this.giftCount = giftCount;
	}
}
