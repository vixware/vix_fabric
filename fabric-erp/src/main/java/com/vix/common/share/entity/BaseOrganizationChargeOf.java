package com.vix.common.share.entity;

import java.util.Set;

public class BaseOrganizationChargeOf extends BaseEntity{

	/**
	 */
	//private UserAccount userAccount;
	/**
	 * 主体职员
	 */
	private BaseEmployee employee;
	
	/**
	 * 分管公司列表
	 */
	private Set<BaseOrganization> organizations;
	
	/**
	 * 分管部门列表
	 */
	private Set<BaseOrganizationUnit> organizationUnits;
	
	
	
	
	
	
	
	
	/** 分管开始时间 */
	//private Date chargeBeginDate;
	
	/** 分管结束时间 */
	//private Date chargeEndDate;

	public BaseEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(BaseEmployee employee) {
		this.employee = employee;
	}

	public Set<BaseOrganization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<BaseOrganization> organizations) {
		this.organizations = organizations;
	}

	public Set<BaseOrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<BaseOrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

}
