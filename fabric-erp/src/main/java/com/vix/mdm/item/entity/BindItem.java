package com.vix.mdm.item.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 捆绑商品
 * 
 * @类全名 com.vix.mdm.item.entity.BindItem
 * 
 * @author zhanghaibing
 *
 * @date 2017年12月5日
 */
public class BindItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** SKU */
	private String sku;
	/** 描述,简介 */
	private String description;
	/** 状态 0:禁用 1：启用 */
	private String status;
	/** 价格 */
	private Double price;
	/** 市场价格 */
	private Double marketPrice;
	/** 是否允许货到付款 */
	private String isAllowCashOnDelivery;
	/** 捆绑商品明细 */
	private Set<BindItemDetail> subBindItemDetails = new HashSet<BindItemDetail>();

	public BindItem() {
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getIsAllowCashOnDelivery() {
		return isAllowCashOnDelivery;
	}

	public void setIsAllowCashOnDelivery(String isAllowCashOnDelivery) {
		this.isAllowCashOnDelivery = isAllowCashOnDelivery;
	}

	public Set<BindItemDetail> getSubBindItemDetails() {
		return subBindItemDetails;
	}

	public void setSubBindItemDetails(Set<BindItemDetail> subBindItemDetails) {
		this.subBindItemDetails = subBindItemDetails;
	}

}