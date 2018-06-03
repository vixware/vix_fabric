/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 项目组织
 * 
 * @类全名 com.vix.mdm.pm.common.entity.ProjectRole
 *
 * @author zhanghaibing
 *
 * @date 2016年8月17日
 */
public class ProjectRole extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 项目
	 */
	private Project project;
	/**
	 * 父组织
	 */
	private ProjectRole parentProjectRole;
	/**
	 * 子组织
	 */
	private Set<ProjectRole> subProjectRoles;
	/**
	 * 人员
	 */
	private Set<Employee> subEmployees;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectRole getParentProjectRole() {
		return parentProjectRole;
	}

	public void setParentProjectRole(ProjectRole parentProjectRole) {
		this.parentProjectRole = parentProjectRole;
	}

	public Set<ProjectRole> getSubProjectRoles() {
		return subProjectRoles;
	}

	public void setSubProjectRoles(Set<ProjectRole> subProjectRoles) {
		this.subProjectRoles = subProjectRoles;
	}

	public Set<Employee> getSubEmployees() {
		return subEmployees;
	}

	public void setSubEmployees(Set<Employee> subEmployees) {
		this.subEmployees = subEmployees;
	}

}
