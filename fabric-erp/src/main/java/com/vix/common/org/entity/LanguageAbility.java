package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description: 个人能力-> 语言能力
 * @author 李大鹏
 * @date 2013-10-29
 * 
 */
public class LanguageAbility extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 员工编码 */
	private String employeeCode;
	/** 语种 */
	private String cassificationOfLanguage;
	/** 熟练程度 */
	private String skilledInChengdu;
	/** 掌握语种水平级别 */
	private String masterLanguageLevel;
	/** 证书类型 */
	private String typeOfCertificate;
	/** 其他证书类型 */
	private String otherTypeOfCertificate;
	/** 证书编号 */
	private String certificateNumber;
	/** 成绩 */
	private String score;
	/** 证书取得时间 */
	private Date certificateInTime;
	/** 认证机构 */
	private String certificationBody;
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

	public String getCassificationOfLanguage() {
		return cassificationOfLanguage;
	}

	public void setCassificationOfLanguage(String cassificationOfLanguage) {
		this.cassificationOfLanguage = cassificationOfLanguage;
	}

	public String getSkilledInChengdu() {
		return skilledInChengdu;
	}

	public void setSkilledInChengdu(String skilledInChengdu) {
		this.skilledInChengdu = skilledInChengdu;
	}

	public String getMasterLanguageLevel() {
		return masterLanguageLevel;
	}

	public void setMasterLanguageLevel(String masterLanguageLevel) {
		this.masterLanguageLevel = masterLanguageLevel;
	}

	public String getTypeOfCertificate() {
		return typeOfCertificate;
	}

	public void setTypeOfCertificate(String typeOfCertificate) {
		this.typeOfCertificate = typeOfCertificate;
	}

	public String getOtherTypeOfCertificate() {
		return otherTypeOfCertificate;
	}

	public void setOtherTypeOfCertificate(String otherTypeOfCertificate) {
		this.otherTypeOfCertificate = otherTypeOfCertificate;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Date getCertificateInTime() {
		return certificateInTime;
	}

	public void setCertificateInTime(Date certificateInTime) {
		this.certificateInTime = certificateInTime;
	}

	public String getCertificationBody() {
		return certificationBody;
	}

	public void setCertificationBody(String certificationBody) {
		this.certificationBody = certificationBody;
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
