package com.vix.common.org.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 公司 部门 分销 的视图
 * 
 * @author Administrator
 *
 */
public class OrgDrpView {

	private String id;

	private String code;

	/** 承租户标识 */
	private String tenantId;
	/** 公司标识 */
	private String companyCode;

	private String companyInnerCode;

	private Long realId;

	/** 组织单元名称 */
	private String orgName;

	/** 父业务组织 */
	private String parentId;

	/**
	 * 公司C 部门O 分销D
	 * 
	 */
	private String orgType;

	private String departmentCode;

	private Set<OrgView> subOrganizations = new HashSet<OrgView>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getRealId() {
		return realId;
	}

	public void setRealId(Long realId) {
		this.realId = realId;
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

	public Set<OrgView> getSubOrganizations() {
		return subOrganizations;
	}

	public void setSubOrganizations(Set<OrgView> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

}
