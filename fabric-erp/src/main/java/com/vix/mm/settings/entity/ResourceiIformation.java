package com.vix.mm.settings.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;

/**
 * @Description:设置-资源资料
 * @author 李大鹏
 */
public class ResourceiIformation extends BaseEntity {

	private static final long serialVersionUID = -7845845573844554025L;
	/** 资源编码 */
	private String resourcesCode;
	/** 资源名称 */
	private String resourcesName;
	/** 资源类别 */
	private String resourcesType;
	/** 计费类型 */
	private String chargingType;
	/** 申请部门 */
	private String applicationDepartment;
	/** 备注 */
	private String remarks;
	/** 资源资料明细 */
	private Set<ResourceiIformationDetail> resourceiIformationDetails = new HashSet<ResourceiIformationDetail>();
	/** 部门 */
	private OrganizationUnit organizationUnit;
	/** 工序管理 */
	private ProcessManagement processManagement;

	public String getResourcesCode() {
		return resourcesCode;
	}

	public void setResourcesCode(String resourcesCode) {
		this.resourcesCode = resourcesCode;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getResourcesType() {
		return resourcesType;
	}

	public void setResourcesType(String resourcesType) {
		this.resourcesType = resourcesType;
	}

	public String getChargingType() {
		return chargingType;
	}

	public void setChargingType(String chargingType) {
		this.chargingType = chargingType;
	}

	public Set<ResourceiIformationDetail> getResourceiIformationDetails() {
		return resourceiIformationDetails;
	}

	public void setResourceiIformationDetails(Set<ResourceiIformationDetail> resourceiIformationDetails) {
		this.resourceiIformationDetails = resourceiIformationDetails;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public String getApplicationDepartment() {
		return applicationDepartment;
	}

	public void setApplicationDepartment(String applicationDepartment) {
		this.applicationDepartment = applicationDepartment;
	}

	public ProcessManagement getProcessManagement() {
		return processManagement;
	}

	public void setProcessManagement(ProcessManagement processManagement) {
		this.processManagement = processManagement;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
