package com.vix.mdm.crm.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

public class CustomerShare extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 客户 */
	private CustomerAccount customerAccount;
	/** 职员 */
	private Employee employee;
	/** 客户组 */
	private CustomerAccountGroup customerAccountGroup;
	/** 权限 */
	private String read;
	private String write;
	
	public CustomerShare(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public CustomerAccountGroup getCustomerAccountGroup() {
		return customerAccountGroup;
	}

	public void setCustomerAccountGroup(CustomerAccountGroup customerAccountGroup) {
		this.customerAccountGroup = customerAccountGroup;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getWrite() {
		return write;
	}

	public void setWrite(String write) {
		this.write = write;
	}
}
