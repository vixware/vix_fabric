package com.vix.crm.business.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.mdm.item.entity.ItemGift;
/**
 * 促销规则
 * @author jackie
 *
 */
public class PromotionRule extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 名称 */
	private String name;
	/** 促销策略 */
	private String promotionType;
	/** 客户端类型 */
	private String clientType;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	
	/** 第一级别开始 单品 */
	/** 促销价、买赠、折扣、秒杀 */
	private Set<PromotionRuleDetail> promotionRuleDetails = new HashSet<PromotionRuleDetail>();
	/**分类/品牌/全场折扣  */
	private Double promotionDiscountcb; 
	/** 第一级别结束 */
	
	/** 第二级别开始 整单 */
	/** 订单总价 */
	private Double orderTotalPrice;
	/**满减： 减少金额 */
	private Double reducePrice;
	/**满赠： 关联赠品 */
	private Set<ItemGift> itemGifts = new HashSet<ItemGift>();
	/** 积分翻倍的倍数 */
	private Integer scoreDouble;
	/** 优惠券面值 */
	private Long couponValue;
	/** 优惠券数量 */
	private Integer couponCount;
	/** 赠送优惠券 有效开始时间 */
	private Date couponStartTime;
	/** 赠送优惠券 有效结束时间 */
	private Date couponEndTime;
	/** 第二级别结束 */
	
	/**status 状态 0：禁用,1：启用 */
	
	
	/** 客户等级  注：保存数据暂不处理 */
	private MemberLevel memberLevel;
	
	public PromotionRule(){}
	
	public Long getSurplusHour(){
		Date currentDate = new Date();
		if(null == endTime || currentDate.getTime() > endTime.getTime()){
			return 0l;
		}
		long surplusHour = 0;
		long stime = currentDate.getTime();
		long etime = endTime.getTime();
		surplusHour = (etime - stime)/1000/60/60;
		return surplusHour;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public String getStartTimeStr() {
		if(null != startTime){
			return DateUtil.format(startTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
	
	@Override
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}
	
	@Override
	public String getEndTimeStr() {
		if(null != endTime){
			return DateUtil.format(endTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	@Override
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Set<PromotionRuleDetail> getPromotionRuleDetails() {
		return promotionRuleDetails;
	}

	public void setPromotionRuleDetails(
			Set<PromotionRuleDetail> promotionRuleDetails) {
		this.promotionRuleDetails = promotionRuleDetails;
	}

	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Double getReducePrice() {
		return reducePrice;
	}

	public void setReducePrice(Double reducePrice) {
		this.reducePrice = reducePrice;
	}


	public Integer getScoreDouble() {
		return scoreDouble;
	}

	public void setScoreDouble(Integer scoreDouble) {
		this.scoreDouble = scoreDouble;
	}

	public Long getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(Long couponValue) {
		this.couponValue = couponValue;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public Date getCouponStartTime() {
		return couponStartTime;
	}
	
	public String getCouponStartTimeStr() {
		if(null != couponStartTime){
			return DateUtil.format(couponStartTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	public void setCouponStartTime(Date couponStartTime) {
		this.couponStartTime = couponStartTime;
	}

	public Date getCouponEndTime() {
		return couponEndTime;
	}

	public String getCouponEndTimeStr() {
		if(null != couponEndTime){
			return DateUtil.format(couponEndTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	public void setCouponEndTime(Date couponEndTime) {
		this.couponEndTime = couponEndTime;
	}

	public Double getPromotionDiscountcb() {
		return promotionDiscountcb;
	}

	public void setPromotionDiscountcb(Double promotionDiscountcb) {
		this.promotionDiscountcb = promotionDiscountcb;
	}

	public Set<ItemGift> getItemGifts() {
		return itemGifts;
	}

	public void setItemGifts(Set<ItemGift> itemGifts) {
		this.itemGifts = itemGifts;
	}

	public MemberLevel getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}
}
