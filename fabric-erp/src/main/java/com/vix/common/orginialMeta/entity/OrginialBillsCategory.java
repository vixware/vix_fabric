package com.vix.common.orginialMeta.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: OrginialBillsCategory
 * @Description: 单据分类   原始数据源   用于系统常量，供承租户创建使用
 * @author wangmingchen
 * @date 2014年10月5日 下午5:19:47
 */
public class OrginialBillsCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 分类编码 */
	private String categoryCode;
	/** 分类名称 */
	private String categoryName;
	/** 分类描述 */
	//private String categoryDescription;
	/** 单据性质 */
	private Set<OrginialBillsProperty> orginialBillsPropertys = new HashSet<OrginialBillsProperty>();

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
/*
	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}*/

	public Set<OrginialBillsProperty> getOrginialBillsPropertys() {
		return orginialBillsPropertys;
	}

	public void setOrginialBillsPropertys(
			Set<OrginialBillsProperty> orginialBillsPropertys) {
		this.orginialBillsPropertys = orginialBillsPropertys;
	}

}
