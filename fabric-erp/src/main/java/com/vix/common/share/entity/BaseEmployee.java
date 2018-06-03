package com.vix.common.share.entity;

import java.util.Date;

public class BaseEmployee extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseEmployee() {
		super();
	}

	public BaseEmployee(String id) {
		super();
		setId(id);
	}

	/** 员工姓名 */
	// private String name;
	/** 曾经姓名 */
	private String oldName;
	/** 姓名缩写 */
	private String fsName;
	/** 员工职号 */
	private String staffJobNumber;
	/** 身份证号 */
	private String idNumber;
	/** 血型 */
	private String bloodType;

	/** 学历代号 */
	private String qualificationsCode;
	/** 科系代号 */
	private String departmentCode;
	/** 户籍地址 */
	private String residenceAddress;

	/** 出生年月 */
	private Date birthday;
	/** 性别 1：男 0：女 */
	private String gender;
	/** 婚否 1：是 0：否 */
	private String isMarriage;
	/** 毕业院校 */
	private String graduation;
	/** 个人专业 */
	private String professional;
	/** 籍贯 */
	private String birthplace;
	/** 现居住地 */
	private String currentResidence;
	/** 民族 */
	private String national;
	/** 是否离职 1：是 0：否 */
	private String isDemission;
	/** 入职时间 */
	private Date entityTime;
	/** 联系电话 */
	private String telephone;
	/** 人员状态 1：在职 0：后备 */
	private String employeeType;
	private String headImgUrl; // 成员头像

	/**
	 * 职员类型 S 标准 ,D 分销,SE 供应商,ST门店
	 */
	private String empType;

	private String organizationUnitId;

	/** 所属部门 */
	private BaseOrganizationUnit organizationUnit;

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public String getStaffJobNumber() {
		return staffJobNumber;
	}

	public void setStaffJobNumber(String staffJobNumber) {
		this.staffJobNumber = staffJobNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getQualificationsCode() {
		return qualificationsCode;
	}

	public void setQualificationsCode(String qualificationsCode) {
		this.qualificationsCode = qualificationsCode;
	}

	@Override
	public String getDepartmentCode() {
		return departmentCode;
	}

	@Override
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIsMarriage() {
		return isMarriage;
	}

	public void setIsMarriage(String isMarriage) {
		this.isMarriage = isMarriage;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getCurrentResidence() {
		return currentResidence;
	}

	public void setCurrentResidence(String currentResidence) {
		this.currentResidence = currentResidence;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getIsDemission() {
		return isDemission;
	}

	public void setIsDemission(String isDemission) {
		this.isDemission = isDemission;
	}

	public Date getEntityTime() {
		return entityTime;
	}

	public void setEntityTime(Date entityTime) {
		this.entityTime = entityTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getOrganizationUnitId() {
		return organizationUnitId;
	}

	public void setOrganizationUnitId(String organizationUnitId) {
		this.organizationUnitId = organizationUnitId;
	}

	public BaseOrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(BaseOrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

}
