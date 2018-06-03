
package com.vix.oa.personaloffice.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.Uploader;

/**
 * 
 * @类全名 com.vix.oa.personaloffice.entity.Communication
 *
 * @author zhanghaibing
 *
 * @date 2016年12月6日
 */
public class Communication extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String logContent;
	/**
	 * 附件
	 */
	private Set<Uploader> subUploaders = new HashSet<Uploader>();
	/**
	 * 查阅人
	 */
	private Set<Employee> subEmployees = new HashSet<Employee>();
	/**
	 * 创建人
	 */
	private Employee employee;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public Set<Uploader> getSubUploaders() {
		return subUploaders;
	}

	public void setSubUploaders(Set<Uploader> subUploaders) {
		this.subUploaders = subUploaders;
	}

	public Set<Employee> getSubEmployees() {
		return subEmployees;
	}

	public void setSubEmployees(Set<Employee> subEmployees) {
		this.subEmployees = subEmployees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getEmployeeName() {
		if (employee != null) {
			return employee.getName();
		}
		return "";
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}