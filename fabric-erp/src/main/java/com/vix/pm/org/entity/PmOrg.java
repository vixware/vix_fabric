package com.vix.pm.org.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 业务组织
 * 
 * @author Administrator
 * 
 */
public class PmOrg extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 组织单元编码 */
	private String businessOrgCode;
	/** 组织单元名称 */
	private String businessOrgName;
	
	/**组织单元名称(引用实际组织单元) */
	private String orgUnitName;
	/**组织单元编码(引用实际组织单元) */
	private String orgUnitCode;
	/** 是否为虚拟节点  */
	private String isVirtualUnitNode;

	/** 父业务组织 */
	private PmOrg parentBusinessOrg;
	/** 子业务组织集合 */
	private Set<PmOrg> subBusinessOrgs = new HashSet<PmOrg>();

	
	 /**
     * 业务组织类型
     * "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
     */
    private String bizOrgType;
	/** 组织机构单元 部门 */
	private OrganizationUnit organizationUnit;//private Set<OrganizationUnit> organizationUnits;
	/**  角色*/
	private Role role;
	/** 人员 */
	private Employee employee;
	
	
	
	
	/** 业务组织视图 */
	private PmView pmView;
	
	
	public PmOrg() {
	}

	public PmOrg(String id) {
		setId(id);
	}
	
	public String getIsVirtualUnitNode() {
		return isVirtualUnitNode;
	}

	public void setIsVirtualUnitNode(String isVirtualUnitNode) {
		this.isVirtualUnitNode = isVirtualUnitNode;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	public String getBusinessOrgCode() {
		return businessOrgCode;
	}

	public void setBusinessOrgCode(String businessOrgCode) {
		this.businessOrgCode = businessOrgCode;
	}

	public String getBusinessOrgName() {
		return businessOrgName;
	}

	public void setBusinessOrgName(String businessOrgName) {
		this.businessOrgName = businessOrgName;
	}

	public PmOrg getParentBusinessOrg() {
		return parentBusinessOrg;
	}

	public void setParentBusinessOrg(PmOrg parentBusinessOrg) {
		this.parentBusinessOrg = parentBusinessOrg;
	}

	public Set<PmOrg> getSubBusinessOrgs() {
		return subBusinessOrgs;
	}

	public void setSubBusinessOrgs(Set<PmOrg> subBusinessOrgs) {
		this.subBusinessOrgs = subBusinessOrgs;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public PmView getPmView() {
		return pmView;
	}

	public void setPmView(PmView pmView) {
		this.pmView = pmView;
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

	public String getBizOrgType() {
		return bizOrgType;
	}

	public void setBizOrgType(String bizOrgType) {
		this.bizOrgType = bizOrgType;
	}

	public void initChildList() {
		if (subBusinessOrgs == null)
			subBusinessOrgs = new HashSet<PmOrg>();
	}

	public void addChildren(PmOrg child) {
		initChildList();
		this.subBusinessOrgs.add(child);
	}
}
