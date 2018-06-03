package com.vix.common.org.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 工作情况->军转情况
 * @author 李大鹏
 */
public class SoldierTuneInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 复转类型 */
	private String reTurnType;
	/** 军衔名称 */
	private String militaryRank;
	/** 级别 */
	private String militaryRankLevel;
	/** 所在部队 */
	private String whereTheTroops;
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

	public String getReTurnType() {
		return reTurnType;
	}

	public void setReTurnType(String reTurnType) {
		this.reTurnType = reTurnType;
	}

	public String getMilitaryRank() {
		return militaryRank;
	}

	public void setMilitaryRank(String militaryRank) {
		this.militaryRank = militaryRank;
	}

	public String getMilitaryRankLevel() {
		return militaryRankLevel;
	}

	public void setMilitaryRankLevel(String militaryRankLevel) {
		this.militaryRankLevel = militaryRankLevel;
	}

	public String getWhereTheTroops() {
		return whereTheTroops;
	}

	public void setWhereTheTroops(String whereTheTroops) {
		this.whereTheTroops = whereTheTroops;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
