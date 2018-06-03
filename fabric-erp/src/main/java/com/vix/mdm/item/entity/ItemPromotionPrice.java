package com.vix.mdm.item.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.business.entity.PromotionRule;

/**
 * 
 * @类全名 com.vix.mdm.item.entity.ItemPromotionPrice
 * 
 * @author zhanghaibing
 *
 * @date 2017年12月5日
 */
public class ItemPromotionPrice extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 商品 */
	private Item item;
	/** 促销规则 */
	private PromotionRule promotionRule;

	private String promotionRuleDetailId;
	/** PromotionResultTypeConstant， price : 价格 ，gift ： 赠品 */
	private String promotionType;
	/** 促销价格对应的客户端类型 */
	private String clientType;
	/** 促销价格 */
	private Double promotionPrice;
	/** 开始时间 */
	private Date startDate;
	/** 结束时间 */
	private Date endDate;

	public ItemPromotionPrice() {
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	public String getPromotionRuleDetailId() {
		return promotionRuleDetailId;
	}

	public void setPromotionRuleDetailId(String promotionRuleDetailId) {
		this.promotionRuleDetailId = promotionRuleDetailId;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public Double getPromotionPrice() {
		if (null == promotionPrice) {
			return 0d;
		}
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
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
}
