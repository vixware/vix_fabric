package com.vix.common.org.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 业务组织的视图
 * 
 * @author Administrator
 *
 */
public class OrgView {

	private String id;

	/** 承租户标识 */
	private String tenantId;
	/** 公司标识 */
	private String companyCode;

	/** 公司标识 */
	private String companyInnerCode;

	private String realId;

	/** 组织单元名称 */
	private String orgName;

	/** 父业务组织 */
	private String parentId;

	/**
	 * 公司C 部门O
	 * 
	 */
	private String orgType;

	/**
	 * 组织机构类型 orgType为C 时 jtgs gs orgType为O 时 基准部门 JZBM 销售办公室 XSBGS 销售组 XSZ
	 */
	private String orgUnitType;

	private String departmentCode;

	private Set<OrgView> subOrganizations = new HashSet<OrgView>();

	public Set<OrgView> getSubOrganizations() {
		return subOrganizations;
	}

	public void setSubOrganizations(Set<OrgView> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public OrgView() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

	public String getOrgUnitType() {
		return orgUnitType;
	}

	public void setOrgUnitType(String orgUnitType) {
		this.orgUnitType = orgUnitType;
	}

	public String getRealId() {
		return realId;
	}

	public void setRealId(String realId) {
		this.realId = realId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
}
