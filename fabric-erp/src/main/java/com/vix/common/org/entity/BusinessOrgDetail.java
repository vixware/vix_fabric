package com.vix.common.org.entity;

import com.vix.common.security.entity.Role;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 业务组织  具体详情
 * 
 * @author Administrator
 * 
 */
public class BusinessOrgDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 所属 业务组织 */
	private BusinessOrg businessOrg;
	/** 所属业务组织的所属业务组织视图 */
	private BusinessView businessView;
	
	/**
     * 业务组织类型
     * "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
     */
    private String bizOrgType;
    
	/** 部门 */
	private OrganizationUnit organizationUnit;
	/**  角色*/
	private Role role;
	/** 人员 */
	private Employee employee;
	
	
	public BusinessOrgDetail() {
	}

	public BusinessOrgDetail(String id) {
		setId(id);
	}

	public BusinessOrg getBusinessOrg() {
		return businessOrg;
	}

	public void setBusinessOrg(BusinessOrg businessOrg) {
		this.businessOrg = businessOrg;
	}

	public BusinessView getBusinessView() {
		return businessView;
	}

	public void setBusinessView(BusinessView businessView) {
		this.businessView = businessView;
	}

	public String getBizOrgType() {
		return bizOrgType;
	}

	public void setBizOrgType(String bizOrgType) {
		this.bizOrgType = bizOrgType;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
