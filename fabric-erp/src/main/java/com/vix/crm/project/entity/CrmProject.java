package com.vix.crm.project.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.Schedule;
import com.vix.core.utils.DateUtil;
import com.vix.crm.base.entity.ProjectSalePreviousStage;
import com.vix.crm.base.entity.ProjectStage;
import com.vix.crm.base.entity.ProjectStatus;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/** 项目 */
public class CrmProject extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 主题 */
	private String subject;
	/** 项目阶段备注 */
	private String projectStageMemo;
	/** 负责人 */
	private Employee personInCharge;
	/** 立项时间 */
	private Date projectEstablishDate;
	/** 项目概要 */
	private String projectSummary;
	/** 预计签单日期 */
	private Date forecastSignDate;
	/** 预计金额 */
	private Double forecastMoney;
	/** 可能性（10% -100%） */
	private String possibility;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 项目阶段 */
	private ProjectStage projectStage;
	/** 项目售前阶段 */
	private ProjectSalePreviousStage projectSalePreviousStage;
	/** 项目状态 */
	private ProjectStatus projectStatus;
	/** 项目组成员 */
	private Set<Employee> projectEmployees = new HashSet<Employee>();
	/** 竞争对手 */
	private Set<Competitor> competitors = new HashSet<Competitor>();
	/** 项目解决方案 */
	private Set<ProjectSolution> projectSolutions = new HashSet<ProjectSolution>();
	/** 项目需求 */
	private Set<ProjectRequirement> projectRequirements = new HashSet<ProjectRequirement>();
	/** 项目协作方 */
	private Set<ProjectCollaborator> projectCollaborators = new HashSet<ProjectCollaborator>();
	/** 行动历史 */
	private Set<ActionHistory> actionHistorys = new HashSet<ActionHistory>();
	/** 项目费用 */
	private Set<ProjectCost> projectCosts = new HashSet<ProjectCost>();
	/** 联系人 */
	private Set<ContactPerson> contactPersons = new HashSet<ContactPerson>();
	/** 任务 */
	private Set<VixTask> vixTasks = new HashSet<VixTask>();
	/** 日程 */
	private Set<Schedule> schedules = new HashSet<Schedule>();
	/** 渠道  */
	private ChannelDistributor channelDistributor;
	/** 沟通 */
	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();
	/** 项目进度 */
	private Integer projectSchedule;

	public CrmProject() {
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProjectStageMemo() {
		return projectStageMemo;
	}

	public void setProjectStageMemo(String projectStageMemo) {
		this.projectStageMemo = projectStageMemo;
	}

	public Employee getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(Employee personInCharge) {
		this.personInCharge = personInCharge;
	}

	public Date getProjectEstablishDate() {
		return projectEstablishDate;
	}
	
	public String getProjectEstablishDateStr(){
		if(null != projectEstablishDate){
			return DateUtil.format(projectEstablishDate);
		}
		return "";
	}

	public void setProjectEstablishDate(Date projectEstablishDate) {
		this.projectEstablishDate = projectEstablishDate;
	}

	public String getProjectSummary() {
		return projectSummary;
	}

	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
	}

	public Date getForecastSignDate() {
		return forecastSignDate;
	}
	
	public String getForecastSignDateStr(){
		if(null != forecastSignDate){
			return DateUtil.format(forecastSignDate);
		}
		return "";
	}

	public void setForecastSignDate(Date forecastSignDate) {
		this.forecastSignDate = forecastSignDate;
	}

	public Double getForecastMoney() {
		return forecastMoney;
	}

	public void setForecastMoney(Double forecastMoney) {
		this.forecastMoney = forecastMoney;
	}

	public String getPossibility() {
		return possibility;
	}

	public void setPossibility(String possibility) {
		this.possibility = possibility;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public ProjectStage getProjectStage() {
		return projectStage;
	}

	public void setProjectStage(ProjectStage projectStage) {
		this.projectStage = projectStage;
	}

	public ProjectSalePreviousStage getProjectSalePreviousStage() {
		return projectSalePreviousStage;
	}

	public void setProjectSalePreviousStage(ProjectSalePreviousStage projectSalePreviousStage) {
		this.projectSalePreviousStage = projectSalePreviousStage;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Set<Employee> getProjectEmployees() {
		return projectEmployees;
	}

	public void setProjectEmployees(Set<Employee> projectEmployees) {
		this.projectEmployees = projectEmployees;
	}

	public Set<Competitor> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(Set<Competitor> competitors) {
		this.competitors = competitors;
	}

	public Set<ProjectSolution> getProjectSolutions() {
		return projectSolutions;
	}

	public void setProjectSolutions(Set<ProjectSolution> projectSolutions) {
		this.projectSolutions = projectSolutions;
	}

	public Set<ProjectRequirement> getProjectRequirements() {
		return projectRequirements;
	}

	public void setProjectRequirements(Set<ProjectRequirement> projectRequirements) {
		this.projectRequirements = projectRequirements;
	}

	public Set<ProjectCollaborator> getProjectCollaborators() {
		return projectCollaborators;
	}

	public void setProjectCollaborators(Set<ProjectCollaborator> projectCollaborators) {
		this.projectCollaborators = projectCollaborators;
	}

	public Set<ActionHistory> getActionHistorys() {
		return actionHistorys;
	}

	public void setActionHistorys(Set<ActionHistory> actionHistorys) {
		this.actionHistorys = actionHistorys;
	}

	/**
	 * @return the vixTasks
	 */
	public Set<VixTask> getVixTasks() {
		return vixTasks;
	}

	/**
	 * @param vixTasks
	 *            the vixTasks to set
	 */
	public void setVixTasks(Set<VixTask> vixTasks) {
		this.vixTasks = vixTasks;
	}

	public Set<ProjectCost> getProjectCosts() {
		return projectCosts;
	}

	public void setProjectCosts(Set<ProjectCost> projectCosts) {
		this.projectCosts = projectCosts;
	}

	public Set<ContactPerson> getContactPersons() {
		return contactPersons;
	}

	public void setContactPersons(Set<ContactPerson> contactPersons) {
		this.contactPersons = contactPersons;
	}

	public Set<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public Set<EvaluationReview> getSubEvaluationReviews() {
		return subEvaluationReviews;
	}

	public void setSubEvaluationReviews(Set<EvaluationReview> subEvaluationReviews) {
		this.subEvaluationReviews = subEvaluationReviews;
	}

	public Integer getProjectSchedule() {
		return projectSchedule;
	}

	public void setProjectSchedule(Integer projectSchedule) {
		this.projectSchedule = projectSchedule;
	}
}
