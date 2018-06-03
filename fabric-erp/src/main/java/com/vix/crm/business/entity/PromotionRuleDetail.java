package com.vix.crm.business.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemGift;
/**
 * 促销规则明细
 * @author jackie
 *
 */
public class PromotionRuleDetail extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 促销规则 */
	private PromotionRule promotionRule;
	/** 促销商品 */
	private Item item;
	/** 商品最小购买数量 */
	private Integer minBuyCount;
	/** 是否限制销量 1：是 0：否 */
	private String isLimitBuyCount;
	/** 限制销售数量 */
	private Integer limitSaleCount;
	/** 促销价格 */
	private Double promotionPrice;
	/** 折扣 */
	private Double promotionDiscount;
	/** 促销商品sku */
	private String sku;
	/** 促销商品sku对应的价格 */
	private Double skuPrice;
	/** 关联赠品 */
	private Set<ItemGift> itemGifts = new HashSet<ItemGift>();
	
	public Long getSurplusHour(){
		Date currentDate = new Date();
		if(null == promotionRule || null == promotionRule.getEndTime() || currentDate.getTime() > promotionRule.getEndTime().getTime()){
			return 0l;
		}
		long surplusHour = 0;
		long stime = currentDate.getTime();
		long etime = promotionRule.getEndTime().getTime();
		surplusHour = (etime - stime)/1000/60/60;
		return surplusHour;
	}
	
	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	public PromotionRuleDetail(){}

	public Integer getMinBuyCount() {
		return minBuyCount;
	}

	public void setMinBuyCount(Integer minBuyCount) {
		this.minBuyCount = minBuyCount;
	}

	public String getIsLimitBuyCount() {
		return isLimitBuyCount;
	}

	public void setIsLimitBuyCount(String isLimitBuyCount) {
		this.isLimitBuyCount = isLimitBuyCount;
	}

	public Integer getLimitSaleCount() {
		return limitSaleCount;
	}

	public void setLimitSaleCount(Integer limitSaleCount) {
		this.limitSaleCount = limitSaleCount;
	}

	public Double getPromotionPrice() {
		if(null == promotionPrice){
			return 0d;
		}
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	
	public Double getPromotionDiscount() {
		return promotionDiscount;
	}

	public void setPromotionDiscount(Double promotionDiscount) {
		this.promotionDiscount = promotionDiscount;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Double getSkuPrice() {
		if(null == skuPrice){
			return 0d;
		}
		return skuPrice;
	}

	public void setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Set<ItemGift> getItemGifts() {
		return itemGifts;
	}

	public void setItemGifts(Set<ItemGift> itemGifts) {
		this.itemGifts = itemGifts;
	}
	public String getItemName(){
		if(null != item){
			return item.getName();
		}
		return "";
	}
	public String getItemPrice(){
		if(null != item){
			return String.valueOf(item.getPrice());
		}
		return "";
	}
	public String getPromotionRulePromotionType(){
		if(null != promotionRule){
			return promotionRule.getPromotionType();
		}
		return "";
	}
}
