package com.vix.common.org.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 基本信息->联系方式
 * @author 李大鹏
 */
public class Linkman extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 序号 */
	private String employeeCode;
	/** 联系人 */
	private String contact;
	/** 类型 */
	private String contactType;
	/** 内容 */
	private String contactContent;
	/** 备注 */
	private String remarks;

	/** 职员 */
	private Employee employee;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactContent() {
		return contactContent;
	}

	public void setContactContent(String contactContent) {
		this.contactContent = contactContent;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
