/**
 * 
 */
package com.vix.system.billTypeManagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 单据分类
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class BillsCategory extends BaseEntity {

	private static final long serialVersionUID = -4613757386606245604L;
	/** 分类编码 */
	private String categoryCode;
	/** 分类名称 */
	private String categoryName;
	/** 分类描述 */
	private String categoryDescription;
	/** 单据性质 */
	private Set<BillsProperty> billsPropertys = new HashSet<BillsProperty>();

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

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Set<BillsProperty> getBillsPropertys() {
		return billsPropertys;
	}

	public void setBillsPropertys(Set<BillsProperty> billsPropertys) {
		this.billsPropertys = billsPropertys;
	}

}
