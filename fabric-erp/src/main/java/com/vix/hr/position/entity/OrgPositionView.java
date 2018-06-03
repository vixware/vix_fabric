package com.vix.hr.position.entity;


/**
 * 岗位的视图
 * @author Administrator
 *
 */
public class OrgPositionView {

    
    private String id;
    
    /** 承租户标识 */
	private String tenantId;
	/** 公司标识 */
	private String companyCode;
	
	private String companyInnerCode;
	
    private String realId;

    /** 组织单元名称 */
    private String orgName;

    /** 父业务组织 */
    private String parentId;
    
    /**
     * 公司C
     * 部门O
     * 岗位P
     * 
     */
    private String orgType;
    
    private String departmentCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealId() {
		return realId;
	}

	public void setRealId(String realId) {
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

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
    
}
