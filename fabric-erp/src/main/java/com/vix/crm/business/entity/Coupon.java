package com.vix.crm.business.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.item.entity.ItemCatalog;

/**
 * 优惠券
 * 
 * com.vix.crm.business.entity.Coupon
 *
 * @author zhanghaibing
 *
 * @date 2015年1月21日
 */
public class Coupon extends BaseEntity {

	/**
	 * status 1,未发放2,已发放
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 面额
	 */
	private Double amount;
	/**
	 * 消费额
	 */
	private Double expenditure;
	/**
	 * 优惠券种类
	 */
	private String type;
	/**
	 * 使用限制 0,无限制 1,订单总额满
	 */
	private String userule;
	/**
	 * 数量
	 */
	private Long quantity;
	/**
	 * 应用范围
	 */
	private String rangeOfUse;
	/**
	 * 发券方式 1 直接发放 2会员领取 3营销引擎发放
	 */
	private String endmode;
	/**
	 * 领取资格
	 */
	private String receiveQualification;
	/**
	 * 开始领取时间
	 */
	private Date receiveStartDate;
	/**
	 * 结束领取时间
	 */
	private Date receiveEndDate;
	/**
	 * 有效期开始时间
	 */
	private Date userStartDate;
	/**
	 * 有效期结束时间
	 */
	private Date userEndDate;
	private Set<CouponDetail> subCouponDetails = new HashSet<CouponDetail>();
	/**
	 * 店铺
	 */
	private ChannelDistributor channelDistributor;
	/**
	 * 公司
	 */
	private Organization organization;

	/**
	 * 使用数量
	 * 
	 */
	private Long useQuantity;

	/**
	 * 作废数量
	 */
	private Long cancellationQuantity;

	/**
	 * 已发放数量
	 * 
	 */
	private Long hasGiveOut;
	/**
	 * 客户标签ID
	 */
	private String memberTagsIds;
	/**
	 * 客户标签名称
	 */
	private String memberTagsNames;
	/** 营销类型 shop:店铺优惠券 ，category:分类优惠券，microShopCategory:店铺分类优惠券 */
	private String shopMarketType;

	/** 物料分类ids 非持久化字段 */
	private String itemCatalogIds = "";
	// 分类编码
	private String itemCataCode = "";
	/** 物料分类names 非持久化字段 */
	private String itemCatalogNames = "";
	/** 使用条件 1:订单总额满，0：无限制 */
	private String couponUseCondition;
	/**
	 * 商品分类
	 */
	private ItemCatalog itemCatalog;
	/**
	 * 有效期限
	 */
	private Integer couponValidatePeriod;

	public String getReceiveStartDateStr() {
		if (null != receiveStartDate) {
			return DateUtil.format(receiveStartDate);
		}
		return "";
	}

	public String getReceiveEndDateStr() {
		if (null != receiveEndDate) {
			return DateUtil.format(receiveEndDate);
		}
		return "";
	}

	public String getUserStartDateStr() {
		if (null != userStartDate) {
			return DateUtil.format(userStartDate);
		}
		return "";
	}

	public String getUserEndDateStr() {
		if (null != userEndDate) {
			return DateUtil.format(userEndDate);
		}
		return "";
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(Double expenditure) {
		this.expenditure = expenditure;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getShopMarketType() {
		return shopMarketType;
	}

	public void setShopMarketType(String shopMarketType) {
		this.shopMarketType = shopMarketType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserule() {
		return userule;
	}

	public void setUserule(String userule) {
		this.userule = userule;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public Set<CouponDetail> getSubCouponDetails() {
		return subCouponDetails;
	}

	public void setSubCouponDetails(Set<CouponDetail> subCouponDetails) {
		this.subCouponDetails = subCouponDetails;
	}

	public String getCouponUseCondition() {
		return couponUseCondition;
	}

	public void setCouponUseCondition(String couponUseCondition) {
		this.couponUseCondition = couponUseCondition;
	}

	public Long getUseQuantity() {
		return useQuantity;
	}

	public Long getUseQuantityCount() {
		Long count = 0L;
		if (subCouponDetails != null) {
			for (CouponDetail couponDetail : subCouponDetails) {
				if ("1".equals(couponDetail.getIsUsed())) {
					count += 1;
				}
			}
		}
		return count;
	}

	public Long getNoUseQuantityCount() {
		Long count = 0L;
		if (subCouponDetails != null) {
			for (CouponDetail couponDetail : subCouponDetails) {
				Long effectiveDate = couponDetail.getEffectiveDate().getTime();
				Long cutOffDate = couponDetail.getCutOffDate().getTime();
				Long currentTime = System.currentTimeMillis();
				if ("0".equals(couponDetail.getIsUsed()) && effectiveDate <= currentTime && cutOffDate >= currentTime) {
					count += 1;
				}
			}
		}
		return count;
	}

	public void setUseQuantity(Long useQuantity) {
		this.useQuantity = useQuantity;
	}

	public Long getCancellationQuantity() {
		return cancellationQuantity;
	}

	public Long getCancellationQuantityCount() {
		Long count = 0L;
		if (subCouponDetails != null) {
			for (CouponDetail couponDetail : subCouponDetails) {
				Long cutOffDate = couponDetail.getCutOffDate().getTime();
				Long currentTime = System.currentTimeMillis();
				if ("0".equals(couponDetail.getIsUsed()) && cutOffDate < currentTime) {
					count += 1;
				}
			}
		}
		return count;
	}

	public void setCancellationQuantity(Long cancellationQuantity) {
		this.cancellationQuantity = cancellationQuantity;
	}

	public Long getHasGiveOut() {
		return hasGiveOut;
	}

	public void setHasGiveOut(Long hasGiveOut) {
		this.hasGiveOut = hasGiveOut;
	}

	public String getRangeOfUse() {
		return rangeOfUse;
	}

	public void setRangeOfUse(String rangeOfUse) {
		this.rangeOfUse = rangeOfUse;
	}

	public String getEndmode() {
		return endmode;
	}

	public void setEndmode(String endmode) {
		this.endmode = endmode;
	}

	public String getReceiveQualification() {
		return receiveQualification;
	}

	public void setReceiveQualification(String receiveQualification) {
		this.receiveQualification = receiveQualification;
	}

	public Date getReceiveStartDate() {
		return receiveStartDate;
	}

	public void setReceiveStartDate(Date receiveStartDate) {
		this.receiveStartDate = receiveStartDate;
	}

	public Date getReceiveEndDate() {
		return receiveEndDate;
	}

	public void setReceiveEndDate(Date receiveEndDate) {
		this.receiveEndDate = receiveEndDate;
	}

	public Date getUserStartDate() {
		return userStartDate;
	}

	public void setUserStartDate(Date userStartDate) {
		this.userStartDate = userStartDate;
	}

	public Date getUserEndDate() {
		return userEndDate;
	}

	public void setUserEndDate(Date userEndDate) {
		this.userEndDate = userEndDate;
	}

	public String getMemberTagsIds() {
		return memberTagsIds;
	}

	public void setMemberTagsIds(String memberTagsIds) {
		this.memberTagsIds = memberTagsIds;
	}

	public String getMemberTagsNames() {
		return memberTagsNames;
	}

	public void setMemberTagsNames(String memberTagsNames) {
		this.memberTagsNames = memberTagsNames;
	}

	public String getItemCatalogIds() {
		return itemCatalogIds;
	}

	public void setItemCatalogIds(String itemCatalogIds) {
		this.itemCatalogIds = itemCatalogIds;
	}

	public String getItemCataCode() {
		return itemCataCode;
	}

	public void setItemCataCode(String itemCataCode) {
		this.itemCataCode = itemCataCode;
	}

	public String getItemCatalogNames() {
		return itemCatalogNames;
	}

	public void setItemCatalogNames(String itemCatalogNames) {
		this.itemCatalogNames = itemCatalogNames;
	}

	public ItemCatalog getItemCatalog() {
		return itemCatalog;
	}

	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}

	public Integer getCouponValidatePeriod() {
		return couponValidatePeriod;
	}

	public void setCouponValidatePeriod(Integer couponValidatePeriod) {
		this.couponValidatePeriod = couponValidatePeriod;
	}
}