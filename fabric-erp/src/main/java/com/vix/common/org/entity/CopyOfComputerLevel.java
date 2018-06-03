package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description: 个人能力->计算机水平
 * @author 李大鹏
 */
public class CopyOfComputerLevel extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 员工编码 */
	private String employeeCode;
	/** 计算机水平级别 */
	private String computerProficiencyLevel;
	/** 证书名称 */
	private String obtainCertificateName;
	/** 其他证书名称 */
	private String otherCertificateName;
	/** 证书取得时间 */
	private Date certificateToObtainTime;
	/** 认证机构 */
	private String certificationBody;
	/** 备注 */
	private String remarks;

	/** 职员 */
	private Employee employee;

	public String getComputerProficiencyLevel() {
		return computerProficiencyLevel;
	}

	public void setComputerProficiencyLevel(String computerProficiencyLevel) {
		this.computerProficiencyLevel = computerProficiencyLevel;
	}

	public String getObtainCertificateName() {
		return obtainCertificateName;
	}

	public void setObtainCertificateName(String obtainCertificateName) {
		this.obtainCertificateName = obtainCertificateName;
	}

	public String getOtherCertificateName() {
		return otherCertificateName;
	}

	public void setOtherCertificateName(String otherCertificateName) {
		this.otherCertificateName = otherCertificateName;
	}

	public Date getCertificateToObtainTime() {
		return certificateToObtainTime;
	}

	public void setCertificateToObtainTime(Date certificateToObtainTime) {
		this.certificateToObtainTime = certificateToObtainTime;
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

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
