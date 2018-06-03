package com.vix.common.mail.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 员工邮箱关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-14
 */
public class EmployeeToPersonalEmail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 员工
	 */
	private Employee employee;
	/**
	 * 个人邮箱
	 */
	private PersonalEmail personalEmail;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PersonalEmail getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(PersonalEmail personalEmail) {
		this.personalEmail = personalEmail;
	}

}
