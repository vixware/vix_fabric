/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目人员
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class PMProjectEmployee extends BaseEntity {
	/**
	 * 项目人员姓名
	 */
	private String projectPerson;
	/**
	 * 人员标识
	 */
	private String personCode;
	/**
	 * 项目角色
	 */
	private String projectRole;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * email
	 */
	private String email;

	public String getProjectPerson() {
		return projectPerson;
	}

	public void setProjectPerson(String projectPerson) {
		this.projectPerson = projectPerson;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
