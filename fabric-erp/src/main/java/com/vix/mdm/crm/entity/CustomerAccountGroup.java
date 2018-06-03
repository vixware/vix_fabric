package com.vix.mdm.crm.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/***
 * 客户组
 */
public class CustomerAccountGroup extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 名称 */
	private String name;
	/** 备注 */
	private String memo;
	/** 客户 */
	private Set<CustomerAccount> customerAccounts = new HashSet<CustomerAccount>();
	
	public CustomerAccountGroup(){}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<CustomerAccount> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(Set<CustomerAccount> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}
}
