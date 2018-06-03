package com.vix.hr.trainning.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @Description:培训需求申请
 * @author bobchen
 * @date 2015-9-2 上午10:20:32
 */
public class DemandApply extends BaseEntity {

	private static final long serialVersionUID = -8485291618096444564L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	private String pubType;
	/** 发布对象的id集合 */
	private String pubIds;
	/** 申请编号*/
	private String applicationNumber;
	/** 申请名称 */
	private String applicationName;
	/** 申请日期 */
	private Date applicationDate;
	/**
	 * 申请部门
	 */
	private OrganizationUnit departmet;
	/** 申请部门负责人*/
	private Employee  noticePerson;
	
	/** 培训实施部门 */
	private OrganizationUnit applyDepartmet;
	/** 培训部门负责人 */
	private Employee applyPerson;
	/** 培训课程名称 */
	private String nameOfTrainingCourse;
	/** 培训方式 */
	private String courseCategory;
	/** 课程内容说明 */
	private String courseDescription;
	/** 课程目的 */
	private String courseObjective;
	/** 建议培训机构 */
	private String proposedTrainingInstitutions;
	/** 建议培训讲师 */
	private String trainingInstructor;
	/** 联系方式 */
	private String contactway;
	/** 参与人数 */
	private String numberOfParticipants;
	/** 期望开始日期 */
	private Date expectStartDate;
	/** 期望结束日期 */
	private Date expectEndDate;
	/** 备注 */
	private String remarks;
	/** 教师编码 */
	private String teacherid;
	/** 课程编码 */
	private String courseid;
	/** 课程名称 */
	private String courseName;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 部门 */
	private OrganizationUnit organizationUnit;
	/** 培训课程 */
	private Set<TrainingCourse> trainingCourse = new HashSet<TrainingCourse>();
	/** 培训资源 */
	private Set<TrainingData> trainingDatas = new HashSet<TrainingData>();
	/** 培训设施 */
	private Set<TrainingFacilities> trainingFacilities = new HashSet<TrainingFacilities>();
	/** 培训需求汇总 */
	private DemandSummary demandSummary;

	public Date getExpectStartDate() {
		return expectStartDate;
	}
    
	public String getExpectStartDateStr() {
		if (null != expectStartDate) {
			return DateUtil.format(expectStartDate);
		}
		return "";
	}
	
	public void setExpectStartDate(Date expectStartDate) {
		this.expectStartDate = expectStartDate;
	}

	public Date getExpectEndDate() {
		return expectEndDate;
	}
    
	public String getExpectEndDateStr() {
		if (null != expectEndDate) {
			return DateUtil.format(expectEndDate);
		}
		return "";
	}
	
	public void setExpectEndDate(Date expectEndDate) {
		this.expectEndDate = expectEndDate;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}
    
	public String getApplicationDateStr() {
		if (null != applicationDate) {
			return DateUtil.format(applicationDate);
		}
		return "";
	}
	
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getNameOfTrainingCourse() {
		return nameOfTrainingCourse;
	}

	public void setNameOfTrainingCourse(String nameOfTrainingCourse) {
		this.nameOfTrainingCourse = nameOfTrainingCourse;
	}

	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseObjective() {
		return courseObjective;
	}

	public void setCourseObjective(String courseObjective) {
		this.courseObjective = courseObjective;
	}

	public String getProposedTrainingInstitutions() {
		return proposedTrainingInstitutions;
	}

	public void setProposedTrainingInstitutions(String proposedTrainingInstitutions) {
		this.proposedTrainingInstitutions = proposedTrainingInstitutions;
	}

	public String getTrainingInstructor() {
		return trainingInstructor;
	}

	public void setTrainingInstructor(String trainingInstructor) {
		this.trainingInstructor = trainingInstructor;
	}

	public String getContactway() {
		return contactway;
	}

	public void setContactway(String contactway) {
		this.contactway = contactway;
	}

	public String getNumberOfParticipants() {
		return numberOfParticipants;
	}

	public void setNumberOfParticipants(String numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public Set<TrainingCourse> getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(Set<TrainingCourse> trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public Set<TrainingData> getTrainingDatas() {
		return trainingDatas;
	}

	public void setTrainingDatas(Set<TrainingData> trainingDatas) {
		this.trainingDatas = trainingDatas;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public Set<TrainingFacilities> getTrainingFacilities() {
		return trainingFacilities;
	}

	public void setTrainingFacilities(Set<TrainingFacilities> trainingFacilities) {
		this.trainingFacilities = trainingFacilities;
	}

	public DemandSummary getDemandSummary() {
		return demandSummary;
	}

	public void setDemandSummary(DemandSummary demandSummary) {
		this.demandSummary = demandSummary;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public OrganizationUnit getDepartmet() {
		return departmet;
	}

	public void setDepartmet(OrganizationUnit departmet) {
		this.departmet = departmet;
	}

	public Employee getNoticePerson() {
		return noticePerson;
	}

	public void setNoticePerson(Employee noticePerson) {
		this.noticePerson = noticePerson;
	}

	public OrganizationUnit getApplyDepartmet() {
		return applyDepartmet;
	}

	public void setApplyDepartmet(OrganizationUnit applyDepartmet) {
		this.applyDepartmet = applyDepartmet;
	}

	public Employee getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(Employee applyPerson) {
		this.applyPerson = applyPerson;
	}
    
}
