package com.vix.oa.task.taskDefinition.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.oa.task.typeSettings.entity.ProgressSituation;

/**
 * 
 * @ClassName: ExecutionFeedback
 * @Description: 执行反馈
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-3-14 下午1:30:40
 */
public class ExecutionFeedback extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 判断日期如果对于0是今天如果对于1是昨天如果等于2是前天
	 * 
	 * @return
	 */
	public long getTimeDiff() {
		if (this.getFeedbackTime() != null) {
			long time = Timestamp.valueOf(DateUtil.format(new Date(), "yyyy-MM-dd") + " 00:00:00").getTime();
			long timeDiff = time - this.getFeedbackTime().getTime();

			if (timeDiff < 0) {
				return 0;
			} else {
				return timeDiff / 86400000 + 1;
			}

		} else {
			return 0;
		}
	}

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 反馈时间 */
	private Date feedbackTime;
	/** 执行反馈内容 */
	public String executionFeedback;

	/** 上传人id */
	private String uploadPersonId;

	/** id上传人 */
	private String uploadPerson;

	private String uploadPersonName;

	/**
	 * 是否为正式数据
	 */
	private String isTemp;
	private String searchText1;
	private String searchText2;

	/** 是否完成 0 完成 1未完成 */
	public Integer complete;
	
	/** 进度  */
	private Integer schedule;

	/** 任务定义 */
	private VixTask taskDefinition;
	/**
	 * 项目
	 */
	private Project project;
	/**
	 * 反馈人
	 */
	private Employee employee;

	/** 进度情况 */
	private ProgressSituation progressSituation;

	/** 评论/评估 */
	private Set<EvaluationReview> evaluationReview;

	/** 上传附件 */
	private Set<Uploader> uploader = new HashSet<Uploader>();

	public ExecutionFeedback() {
	}
	public String getFeedbackTimeStr() {
		if (null != feedbackTime) {
			return DateUtil.format(feedbackTime);
		}
		return "";
	}

	public String getFeedbackTimeTimeStr() {
		if (null != feedbackTime) {
			return DateUtil.formatTime(feedbackTime);
		}
		return "";
	}
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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

	public String getExecutionFeedback() {
		return executionFeedback;
	}

	public void setExecutionFeedback(String executionFeedback) {
		this.executionFeedback = executionFeedback;
	}

	public VixTask getTaskDefinition() {
		return taskDefinition;
	}

	public void setTaskDefinition(VixTask taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public ProgressSituation getProgressSituation() {
		return progressSituation;
	}

	public void setProgressSituation(ProgressSituation progressSituation) {
		this.progressSituation = progressSituation;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	public Set<EvaluationReview> getEvaluationReview() {
		return evaluationReview;
	}

	public void setEvaluationReview(Set<EvaluationReview> evaluationReview) {
		this.evaluationReview = evaluationReview;
	}

	public Set<Uploader> getUploader() {
		return uploader;
	}

	public void setUploader(Set<Uploader> uploader) {
		this.uploader = uploader;
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

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	@Override
	public String getSearchText1() {
		return searchText1;
	}

	@Override
	public void setSearchText1(String searchText1) {
		this.searchText1 = searchText1;
	}

	@Override
	public String getSearchText2() {
		return searchText2;
	}

	@Override
	public void setSearchText2(String searchText2) {
		this.searchText2 = searchText2;
	}
	public Integer getSchedule() {
		return schedule;
	}
	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
	}

}
