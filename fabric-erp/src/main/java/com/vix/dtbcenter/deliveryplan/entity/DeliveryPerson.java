/**
 * 
 */
package com.vix.dtbcenter.deliveryplan.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送人员
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-27
 */
public class DeliveryPerson extends BaseEntity {
	private static final long serialVersionUID = -6538059554703223527L;
	/** 员工编号 */
	private String employeeCode;
	/** 助记码 */
	private String sCode;
	/** 姓 */
	private String lastName;
	/** 名 */
	private String firstName;
	/** 英文名 */
	private String englishName;
	/** 性别 */
	private String sex;
	/** 身份证号 */
	private String identityNumber;
	/** 学历 */
	private String educationalBackground;
	/** 职务 */
	private String position;
	/** 工号 */
	private String workNumber;
	/** 手机号码 */
	private String mobile;
	/** 联系电话 */
	private String telephone;
	/** E-MAIL */
	private String email;
	/** 任职部门 */
	private String jobDepartment;
	/** 关系 */
	private String relationship;

	public DeliveryPerson() {
		super();
	}

	public DeliveryPerson(String id) {
		super();
		setId(id);
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getEducationalBackground() {
		return educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobDepartment() {
		return jobDepartment;
	}

	public void setJobDepartment(String jobDepartment) {
		this.jobDepartment = jobDepartment;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
}
