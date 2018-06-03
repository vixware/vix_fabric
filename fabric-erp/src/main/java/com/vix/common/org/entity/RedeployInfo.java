package com.vix.common.org.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description: 个人关系->流动情况
 * @author 李大鹏
 */
public class RedeployInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 借调前原单位 */
	private String secondedbeforeUnits;
	/** 借调前原部门 */
	private String secondedbeforeDep;
	/** 借调前原岗位 */
	private String secondedbeforePost;
	/** 借调去往单位 */
	private String secondedDestinedForUnits;
	/** 借调去往部门 */
	private String secondedDestinedSector;
	/** 借调岗位 */
	private String secondedPosts;
	/** 期限 */
	private String deadline;
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

	public String getSecondedbeforeUnits() {
		return secondedbeforeUnits;
	}

	public void setSecondedbeforeUnits(String secondedbeforeUnits) {
		this.secondedbeforeUnits = secondedbeforeUnits;
	}

	public String getSecondedbeforeDep() {
		return secondedbeforeDep;
	}

	public void setSecondedbeforeDep(String secondedbeforeDep) {
		this.secondedbeforeDep = secondedbeforeDep;
	}

	public String getSecondedbeforePost() {
		return secondedbeforePost;
	}

	public void setSecondedbeforePost(String secondedbeforePost) {
		this.secondedbeforePost = secondedbeforePost;
	}

	public String getSecondedDestinedForUnits() {
		return secondedDestinedForUnits;
	}

	public void setSecondedDestinedForUnits(String secondedDestinedForUnits) {
		this.secondedDestinedForUnits = secondedDestinedForUnits;
	}

	public String getSecondedDestinedSector() {
		return secondedDestinedSector;
	}

	public void setSecondedDestinedSector(String secondedDestinedSector) {
		this.secondedDestinedSector = secondedDestinedSector;
	}

	public String getSecondedPosts() {
		return secondedPosts;
	}

	public void setSecondedPosts(String secondedPosts) {
		this.secondedPosts = secondedPosts;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
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
