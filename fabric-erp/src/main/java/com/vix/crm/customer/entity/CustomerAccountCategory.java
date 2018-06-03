package com.vix.crm.customer.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class CustomerAccountCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 子分类集合 */
	private Set<CustomerAccountCategory> subCustomerAccountCategorys = new HashSet<CustomerAccountCategory>();
	/** 父分类 */
	private CustomerAccountCategory parentCustomerAccountCategory;
	
	public CustomerAccountCategory(){}

	public Set<CustomerAccountCategory> getSubCustomerAccountCategorys() {
		return subCustomerAccountCategorys;
	}

	public void setSubCustomerAccountCategorys(
			Set<CustomerAccountCategory> subCustomerAccountCategorys) {
		this.subCustomerAccountCategorys = subCustomerAccountCategorys;
	}

	public CustomerAccountCategory getParentCustomerAccountCategory() {
		return parentCustomerAccountCategory;
	}

	public void setParentCustomerAccountCategory(
			CustomerAccountCategory parentCustomerAccountCategory) {
		this.parentCustomerAccountCategory = parentCustomerAccountCategory;
	}
}
