package com.vix.mdm.crm.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

public class CustomerContact extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 客户 */
	private CustomerAccount customerAccount;
	/** 拥有人 */
	private Set<Employee> customerAccountOwner = new HashSet<Employee>();
	
	public CustomerContact(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Set<Employee> getCustomerAccountOwner() {
		return customerAccountOwner;
	}

	public void setCustomerAccountOwner(Set<Employee> customerAccountOwner) {
		this.customerAccountOwner = customerAccountOwner;
	}
}
