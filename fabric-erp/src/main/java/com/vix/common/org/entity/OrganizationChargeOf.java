package com.vix.common.org.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

public class OrganizationChargeOf extends BaseEntity {

	/**
	 */
	// private UserAccount userAccount;
	/**
	 * 主体职员
	 */
	private Employee employee;

	/**
	 * 分管公司列表
	 */
	private Set<Organization> organizations;

	/**
	 * 分管部门列表
	 */
	private Set<OrganizationUnit> organizationUnits;

	/** 分管开始时间 */
	// private Date chargeBeginDate;

	/** 分管结束时间 */
	// private Date chargeEndDate;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

}
