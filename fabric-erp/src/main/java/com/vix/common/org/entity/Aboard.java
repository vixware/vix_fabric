package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 个人关系->出国（出境）情况
 * @author 李大鹏
 */
public class Aboard extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 员工编码 */
	private String employeeCode;
	/** 出国目的 */
	private String purposeOfJourney;
	/** 国家 */
	private String country;
	/** 访问单位 */
	private String accessUnit;
	/** 出国日期 */
	private Date dateOfDeparture;
	/** 回国日期 */
	private Date returnDate;
	/** 签证类型 */
	private String typeOfVisa;
	/** 签证编号 */
	private String visaNumber;
	/** 派遣单位 */
	private String dispatchUnit;
	/** 团组名称 */
	private String goupsName;
	/** 组团单位名称 */
	private String groupUnitName;
	/** 在团组内身份 */
	private String groupsWithinTheIdentity;
	/** 批准单位 */
	private String approvedBy;
	/** 批准日期 */
	private Date approvalDate;
	/** 批准文件名 */
	private String approvalFileName;
	/** 批准文号 */
	private String approvalNumber;
	/** 费用供应商 */
	private String costSuppliers;
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

	public String getPurposeOfJourney() {
		return purposeOfJourney;
	}

	public void setPurposeOfJourney(String purposeOfJourney) {
		this.purposeOfJourney = purposeOfJourney;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAccessUnit() {
		return accessUnit;
	}

	public void setAccessUnit(String accessUnit) {
		this.accessUnit = accessUnit;
	}

	public Date getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(Date dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getTypeOfVisa() {
		return typeOfVisa;
	}

	public void setTypeOfVisa(String typeOfVisa) {
		this.typeOfVisa = typeOfVisa;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public String getDispatchUnit() {
		return dispatchUnit;
	}

	public void setDispatchUnit(String dispatchUnit) {
		this.dispatchUnit = dispatchUnit;
	}

	public String getGoupsName() {
		return goupsName;
	}

	public void setGoupsName(String goupsName) {
		this.goupsName = goupsName;
	}

	public String getGroupUnitName() {
		return groupUnitName;
	}

	public void setGroupUnitName(String groupUnitName) {
		this.groupUnitName = groupUnitName;
	}

	public String getGroupsWithinTheIdentity() {
		return groupsWithinTheIdentity;
	}

	public void setGroupsWithinTheIdentity(String groupsWithinTheIdentity) {
		this.groupsWithinTheIdentity = groupsWithinTheIdentity;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalFileName() {
		return approvalFileName;
	}

	public void setApprovalFileName(String approvalFileName) {
		this.approvalFileName = approvalFileName;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public String getCostSuppliers() {
		return costSuppliers;
	}

	public void setCostSuppliers(String costSuppliers) {
		this.costSuppliers = costSuppliers;
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
