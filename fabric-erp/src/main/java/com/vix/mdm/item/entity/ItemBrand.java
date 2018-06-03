package com.vix.mdm.item.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 商品品牌
 * @author arron
 *
 */
public class ItemBrand extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 品牌名称 */
	private String name;
	/** 品牌编码 */
	private String code;
	/** 品牌公司名称 */
	private String brandCompany;
	/** 品牌公司地址 */
	private String companyAddress;
	/** 品牌排序 */
	private Long orderBy;
	/** 品牌描述 */
	private String memo;
	/** 分类子分类集合 */
	private Set<Item> items = new HashSet<Item>();
	
	public ItemBrand(){}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	public String getBrandCompany() {
		return brandCompany;
	}

	public void setBrandCompany(String brandCompany) {
		this.brandCompany = brandCompany;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
}
