package com.vix.oa.task.taskDefinition.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.entity.ProjectManagement;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.personaloffice.entity.TripRecord;
import com.vix.oa.personaloffice.entity.WorkLog;
import com.vix.oa.share.entity.Trends;

/**
 * 
 * @ClassName: EvaluationReview
 * @Description: 评论/评估
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-5-7 下午4:31:44
 */
public class EvaluationReview extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 评论/评估时间 */
	private Date evaluationTime;
	/** 评论/评估内容 */
	private String evaluationContent;
	/** 上传人id */
	private String uploadPersonId;
	/** id上传人 */
	private String uploadPerson;

	private String uploadPersonName;
	/** 是否完成 0 完成 1未完成 */
	public Integer complete;
	/** 反馈任务 */
	private ExecutionFeedback executionFeedback;
	/**
	 * 任务
	 */
	private VixTask vixTask;
	/**
	 * 评论人
	 */
	private Employee employee;
	/**
	 * 项目
	 */
	private Project project;
	/** CRM项目  */
	private CrmProject crmProject;

	private TripRecord tripRecord;

	private ApplicationMg applicationMg;

	private WorkLog workLog;
	
	private ProjectManagement projectManagement;

	private Trends trends;
	
	private AnnouncementNotification announcementNotification;
	
	private EvaluationReview parentEvaluationReview;
	/**
	 * 子评论
	 */
	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();

	public EvaluationReview() {
	}

	/**
	 * @return the tripRecord
	 */
	public TripRecord getTripRecord() {
		return tripRecord;
	}

	/**
	 * @param tripRecord
	 *            the tripRecord to set
	 */
	public void setTripRecord(TripRecord tripRecord) {
		this.tripRecord = tripRecord;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	/**
	 * @return the vixTask
	 */
	public VixTask getVixTask() {
		return vixTask;
	}

	/**
	 * @param vixTask
	 *            the vixTask to set
	 */
	public void setVixTask(VixTask vixTask) {
		this.vixTask = vixTask;
	}

	/**
	 * @return the workLog
	 */
	public WorkLog getWorkLog() {
		return workLog;
	}

	/**
	 * @param workLog
	 *            the workLog to set
	 */
	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	/**
	 * @return the parentEvaluationReview
	 */
	public EvaluationReview getParentEvaluationReview() {
		return parentEvaluationReview;
	}

	/**
	 * @param parentEvaluationReview
	 *            the parentEvaluationReview to set
	 */
	public void setParentEvaluationReview(EvaluationReview parentEvaluationReview) {
		this.parentEvaluationReview = parentEvaluationReview;
	}

	/**
	 * @return the subEvaluationReviews
	 */
	public Set<EvaluationReview> getSubEvaluationReviews() {
		return subEvaluationReviews;
	}

	/**
	 * @param subEvaluationReviews
	 *            the subEvaluationReviews to set
	 */
	public void setSubEvaluationReviews(Set<EvaluationReview> subEvaluationReviews) {
		this.subEvaluationReviews = subEvaluationReviews;
	}

	/**
	 * @return the announcementNotification
	 */
	public AnnouncementNotification getAnnouncementNotification() {
		return announcementNotification;
	}

	/**
	 * @param announcementNotification
	 *            the announcementNotification to set
	 */
	public void setAnnouncementNotification(AnnouncementNotification announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

	/**
	 * @return the applicationMg
	 */
	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	/**
	 * @param applicationMg
	 *            the applicationMg to set
	 */
	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

	public Date getEvaluationTime() {
		return evaluationTime;
	}
	public String getEvaluationTimeTimeStr() {
		if (null != evaluationTime) {
			return DateUtil.formatTime(evaluationTime);
		}
		return "";
	}

	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public ExecutionFeedback getExecutionFeedback() {
		return executionFeedback;
	}

	public void setExecutionFeedback(ExecutionFeedback executionFeedback) {
		this.executionFeedback = executionFeedback;
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

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	/**
	 * @return the trends
	 */
	public Trends getTrends() {
		return trends;
	}

	/**
	 * @param trends
	 *            the trends to set
	 */
	public void setTrends(Trends trends) {
		this.trends = trends;
	}

	public ProjectManagement getProjectManagement() {
		return projectManagement;
	}

	public void setProjectManagement(ProjectManagement projectManagement) {
		this.projectManagement = projectManagement;
	}

}
