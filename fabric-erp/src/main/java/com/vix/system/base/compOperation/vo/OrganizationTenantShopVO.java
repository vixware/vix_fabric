package com.vix.system.base.compOperation.vo;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: OrganizationTenantShop
 * @Description: 企业门店
 * @author wangmingchen
 * @date 2016年8月15日 下午5:09:13
 */
public class OrganizationTenantShopVO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private String tenantId;
	/**
	 * 门店编号
	 */
	private String shopCode;
	
	/** 门店序号 */
	private Integer shopIndex;
	
	/**
	 * 门店名称
	 */
	private String shopName;
	
	/** 
	 * 门店状态
	 * Y	使用
	 * N	关闭
	 */
	private String shopStatus;
	
	/**
	 * 关闭时间
	 */
	private Date openTime;
	
	/**
	 * 关闭时间
	 */
	private Date closeTime;

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Integer getShopIndex() {
		return shopIndex;
	}

	public void setShopIndex(Integer shopIndex) {
		this.shopIndex = shopIndex;
	}
	
}
