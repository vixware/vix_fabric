package com.vix.common.org.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description: 个人能力->培训情况
 * @author 李大鹏
 */
public class Training extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 员工编码 */
	private String employeeCode;
	/** 培训类别 */
	private String trainingClass;
	/** 主办层次 */
	private String hostLevel;
	/** 培训项目名称 */
	private String projectTraining;
	/** 培训对象 */
	private String trainingObjects;
	/** 培训内容 */
	private String trainingContent;
	/** 培训形式 */
	private String trainingForm;
	/** 培训教材 */
	private String trainingMaterials;
	/** 培训讲师 */
	private String trainingInstructor;
	/** 计划时间 */
	private Date planTime;
	/** 学时 */
	private String cassHour;
	/** 人数 */
	private String peopleNumber;
	/** 期数 */
	private String periods;
	/** 培训机构 */
	private String trainingAgency;
	/** 联系人 */
	private String contacts;
	/** 联系电话 */
	private String contactNumber;
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

	public String getTrainingClass() {
		return trainingClass;
	}

	public void setTrainingClass(String trainingClass) {
		this.trainingClass = trainingClass;
	}

	public String getHostLevel() {
		return hostLevel;
	}

	public void setHostLevel(String hostLevel) {
		this.hostLevel = hostLevel;
	}

	public String getProjectTraining() {
		return projectTraining;
	}

	public void setProjectTraining(String projectTraining) {
		this.projectTraining = projectTraining;
	}

	public String getTrainingObjects() {
		return trainingObjects;
	}

	public void setTrainingObjects(String trainingObjects) {
		this.trainingObjects = trainingObjects;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public String getTrainingForm() {
		return trainingForm;
	}

	public void setTrainingForm(String trainingForm) {
		this.trainingForm = trainingForm;
	}

	public String getTrainingMaterials() {
		return trainingMaterials;
	}

	public void setTrainingMaterials(String trainingMaterials) {
		this.trainingMaterials = trainingMaterials;
	}

	public String getTrainingInstructor() {
		return trainingInstructor;
	}

	public void setTrainingInstructor(String trainingInstructor) {
		this.trainingInstructor = trainingInstructor;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public String getCassHour() {
		return cassHour;
	}

	public void setCassHour(String cassHour) {
		this.cassHour = cassHour;
	}

	public String getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getTrainingAgency() {
		return trainingAgency;
	}

	public void setTrainingAgency(String trainingAgency) {
		this.trainingAgency = trainingAgency;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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
