package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 个人关系->兼任情况
 * @author 李大鹏
 */
public class PartPostionInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 兼职类型 */
	private String partTimeType;
	/** 兼职岗位名称 */
	private String partTimeName;
	/** 兼职开始日期 */
	private Date partTimeStartDate;
	/** 兼职结束日期 */
	private Date partTimeEndDate;
	/** 岗位排序 */
	private String positionsSort;
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

	public String getPartTimeType() {
		return partTimeType;
	}

	public void setPartTimeType(String partTimeType) {
		this.partTimeType = partTimeType;
	}

	public String getPartTimeName() {
		return partTimeName;
	}

	public void setPartTimeName(String partTimeName) {
		this.partTimeName = partTimeName;
	}

	public Date getPartTimeStartDate() {
		return partTimeStartDate;
	}

	public void setPartTimeStartDate(Date partTimeStartDate) {
		this.partTimeStartDate = partTimeStartDate;
	}

	public Date getPartTimeEndDate() {
		return partTimeEndDate;
	}

	public void setPartTimeEndDate(Date partTimeEndDate) {
		this.partTimeEndDate = partTimeEndDate;
	}

	public String getPositionsSort() {
		return positionsSort;
	}

	public void setPositionsSort(String positionsSort) {
		this.positionsSort = positionsSort;
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
