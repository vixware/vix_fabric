package com.vix.common.org.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 业务组织
 * 
 * @author Administrator
 * 
 */
public class BusinessOrg extends BaseEntity {

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
	private BusinessOrg parentBusinessOrg;
	/** 子业务组织集合 */
	private Set<BusinessOrg> subBusinessOrgs = new HashSet<BusinessOrg>();

	/** 业务组织详情 */
	private Set<BusinessOrgDetail> businessOrgDetails;
	
    /** 负责人 */
    private Employee manager;
	
	
	
	/** 业务组织视图 */
	private BusinessView businessView;
	
	
	public BusinessOrg() {
	}

	public BusinessOrg(String id) {
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

	public BusinessOrg getParentBusinessOrg() {
		return parentBusinessOrg;
	}

	public void setParentBusinessOrg(BusinessOrg parentBusinessOrg) {
		this.parentBusinessOrg = parentBusinessOrg;
	}

	public Set<BusinessOrg> getSubBusinessOrgs() {
		return subBusinessOrgs;
	}

	public void setSubBusinessOrgs(Set<BusinessOrg> subBusinessOrgs) {
		this.subBusinessOrgs = subBusinessOrgs;
	}

	public BusinessView getBusinessView() {
		return businessView;
	}

	public void setBusinessView(BusinessView businessView) {
		this.businessView = businessView;
	}

	public Set<BusinessOrgDetail> getBusinessOrgDetails() {
		return businessOrgDetails;
	}

	public void setBusinessOrgDetails(Set<BusinessOrgDetail> businessOrgDetails) {
		this.businessOrgDetails = businessOrgDetails;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public void initChildList() {
		if (subBusinessOrgs == null)
			subBusinessOrgs = new HashSet<BusinessOrg>();
	}

	public void addChildren(BusinessOrg child) {
		initChildList();
		this.subBusinessOrgs.add(child);
	}
}
