package com.vix.oa.adminMg.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;

/**
 * 工作计划
 * 
 * @类全名 com.vix.oa.adminMg.entity.ProjectManagement
 *
 * @author zhanghaibing
 *
 * @date 2016年8月6日
 */
public class ProjectManagement extends BaseEntity {
	private static final long serialVersionUID = -3943610172581798396L;

	/** 中文首字母 */
	private String chineseFirstLetter;

	/** 计划名称 */
	private String proposalTitle;

	/** 发布范围（部门） */
	private String distributionDepartment;

	/** 发布范围（人员） */
	private String distributionPersonnel;

	/** 参与人 */
	private String participant;

	/** 阅读次数 */
	public String readTimes;

	/** 负责人 */
	private String principal;

	/** 领导批注 */
	private String leadershipNotation;

	/** 开始时间 */
	private Date initiateTime;

	/** 结束时间 */
	private Date overTime;

	/** 附件 */
	private String accessory;

	/** 附件说明 */
	private String enclosure;

	/** 备注 */
	private String remark;

	/** 计划内容 */
	private String planContent;

	/** 计划类型 */
	private String plantype;

	/** 计划类型编码 */
	private String plantypecode;
	/** 进度 */
	private String progress;

	/** 是否发布 1 生效 0终止 */
	public Integer isPublish;

	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/**
	 * 发布人员类型 "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
	 */
	private String pubType;
	private String pubType1;
	private String pubType2;
	private String pubType3;

	/** 计划 0 今日计划 1本周计划 2本月计划 */
	private Integer workPlan;

	/** 按需求选择部门人员角色 */
	private String bizOrgNames;
	private String bizOrgNames1;
	private String bizOrgNames2;
	private String bizOrgNames3;
	/**
	 * 按部门发布
	 */
	private Set<OrganizationUnit> organizationUnits;
	/**
	 * 按角色发布
	 */
	private Set<Role> roles;
	/**
	 * 按人员发布
	 */
	private Set<Employee> employees;

	/** 发布对象的id集合 */
	private String pubIds;
	private String pubIds1;
	private String pubIds2;
	private String pubIds3;
	/** 发布对象的名称集合 */
	private String pubNames;

	/** 工作计划类型设置 */
	/* private WorkPlanType workPlanType; */
	/** 进度日志 */
	/* private ProgressLog progressLog; */
	/** 批注 */
	private Set<Postil> postil = new HashSet<Postil>();

	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();

	private Employee employee;

	public ProjectManagement() {
		super();
	}

	public ProjectManagement(String id) {
		super();
		setId(id);
	}

	public Set<Postil> getPostil() {
		return postil;
	}

	public void setPostil(Set<Postil> postil) {
		this.postil = postil;
	}

	public String getProposalTitle() {
		return proposalTitle;
	}

	public void setProposalTitle(String proposalTitle) {
		this.proposalTitle = proposalTitle;
	}

	public String getDistributionDepartment() {
		return distributionDepartment;
	}

	public void setDistributionDepartment(String distributionDepartment) {
		this.distributionDepartment = distributionDepartment;
	}

	public String getDistributionPersonnel() {
		return distributionPersonnel;
	}

	public void setDistributionPersonnel(String distributionPersonnel) {
		this.distributionPersonnel = distributionPersonnel;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getLeadershipNotation() {
		return leadershipNotation;
	}

	public void setLeadershipNotation(String leadershipNotation) {
		this.leadershipNotation = leadershipNotation;
	}

	public Date getInitiateTime() {
		return initiateTime;
	}

	public void setInitiateTime(Date initiateTime) {
		this.initiateTime = initiateTime;
	}

	public String getInitiateTimeStr() {
		if (null != initiateTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(initiateTime);
		} else {
			return "";
		}
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public String getOverTimeStr() {
		if (null != overTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(overTime);
		} else {
			return "";
		}
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getPlantype() {
		return plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public String getPlantypecode() {
		return plantypecode;
	}

	public void setPlantypecode(String plantypecode) {
		this.plantypecode = plantypecode;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getPubNames() {
		return pubNames;
	}

	public void setPubNames(String pubNames) {
		this.pubNames = pubNames;
	}

	public String getPubType1() {
		return pubType1;
	}

	public void setPubType1(String pubType1) {
		this.pubType1 = pubType1;
	}

	public String getPubType2() {
		return pubType2;
	}

	public void setPubType2(String pubType2) {
		this.pubType2 = pubType2;
	}

	public String getPubType3() {
		return pubType3;
	}

	public void setPubType3(String pubType3) {
		this.pubType3 = pubType3;
	}

	public String getBizOrgNames() {
		return bizOrgNames;
	}

	public void setBizOrgNames(String bizOrgNames) {
		this.bizOrgNames = bizOrgNames;
	}

	public String getBizOrgNames1() {
		return bizOrgNames1;
	}

	public void setBizOrgNames1(String bizOrgNames1) {
		this.bizOrgNames1 = bizOrgNames1;
	}

	public String getBizOrgNames2() {
		return bizOrgNames2;
	}

	public void setBizOrgNames2(String bizOrgNames2) {
		this.bizOrgNames2 = bizOrgNames2;
	}

	public String getBizOrgNames3() {
		return bizOrgNames3;
	}

	public void setBizOrgNames3(String bizOrgNames3) {
		this.bizOrgNames3 = bizOrgNames3;
	}

	public String getPubIds1() {
		return pubIds1;
	}

	public void setPubIds1(String pubIds1) {
		this.pubIds1 = pubIds1;
	}

	public String getPubIds2() {
		return pubIds2;
	}

	public void setPubIds2(String pubIds2) {
		this.pubIds2 = pubIds2;
	}

	public String getPubIds3() {
		return pubIds3;
	}

	public void setPubIds3(String pubIds3) {
		this.pubIds3 = pubIds3;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
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

	public Integer getWorkPlan() {
		return workPlan;
	}

	public void setWorkPlan(Integer workPlan) {
		this.workPlan = workPlan;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(String readTimes) {
		this.readTimes = readTimes;
	}

	public Set<EvaluationReview> getSubEvaluationReviews() {
		return subEvaluationReviews;
	}

	public void setSubEvaluationReviews(Set<EvaluationReview> subEvaluationReviews) {
		this.subEvaluationReviews = subEvaluationReviews;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}