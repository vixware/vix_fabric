/**
 * 
 */
package com.vix.system.billTypeSet.entity;

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
public class BillsCategoryDictionary extends BaseEntity {

	private static final long serialVersionUID = -4613757386606245604L;
	/** 分类编码 */
	private String categoryCode;
	/** 分类名称 */
	private String categoryName;
	/** 分类描述 */
	private String categoryDescription;
	/** 单据性质 */
	private Set<BillsPropertyDictionary> billsPropertyDictionarys = new HashSet<BillsPropertyDictionary>();

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

	public Set<BillsPropertyDictionary> getBillsPropertyDictionarys() {
		return billsPropertyDictionarys;
	}

	public void setBillsPropertyDictionarys(Set<BillsPropertyDictionary> billsPropertyDictionarys) {
		this.billsPropertyDictionarys = billsPropertyDictionarys;
	}

}
