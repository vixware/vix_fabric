package com.vix.system.formBinding.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * com.vix.system.formBinding.entity.TemplateAndOrganizationUnit
 *
 * @author bjitzhang
 *
 * @date 2015年6月17日
 */
public class TemplateAndOrganizationUnit extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 表单模板ID
	 */
	private String businessFormId;
	/**
	 * 角色ID
	 */
	private String roleId;

	private String empId;
	private String type;

	/**
	 * @return the businessFormId
	 */
	public String getBusinessFormId() {
		return businessFormId;
	}

	/**
	 * @param businessFormId
	 *            the businessFormId to set
	 */
	public void setBusinessFormId(String businessFormId) {
		this.businessFormId = businessFormId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}