package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 个人关系->家庭成员及社会关系
 * @author 李大鹏
 */
public class FamilyRelationship extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 成员类别 */
	private String categoriesOfMembership;
	/** 性别 */
	private String sex;
	/** 出生日期 */
	private Date dateOfBirth;
	/** 民族 */
	private String nation;
	/** 身份证号 */
	private String idNumber;
	/** 是否港澳台人员 */
	private String whetherHongKongMacaoAndTaiwanStaff;
	/** 户口所在地 */
	private String accountTheLocationOf;
	/** 政治面貌 */
	private String politicalLandscape;
	/** 最高学历 */
	private String highestDegree;
	/** 与本人关系 */
	private String relationship;
	/** 地址 */
	private String address;
	/** 联系电话 */
	private String tel;
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

	public String getCategoriesOfMembership() {
		return categoriesOfMembership;
	}

	public void setCategoriesOfMembership(String categoriesOfMembership) {
		this.categoriesOfMembership = categoriesOfMembership;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getWhetherHongKongMacaoAndTaiwanStaff() {
		return whetherHongKongMacaoAndTaiwanStaff;
	}

	public void setWhetherHongKongMacaoAndTaiwanStaff(String whetherHongKongMacaoAndTaiwanStaff) {
		this.whetherHongKongMacaoAndTaiwanStaff = whetherHongKongMacaoAndTaiwanStaff;
	}

	public String getAccountTheLocationOf() {
		return accountTheLocationOf;
	}

	public void setAccountTheLocationOf(String accountTheLocationOf) {
		this.accountTheLocationOf = accountTheLocationOf;
	}

	public String getPoliticalLandscape() {
		return politicalLandscape;
	}

	public void setPoliticalLandscape(String politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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
