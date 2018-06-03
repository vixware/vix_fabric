package com.vix.oa.task.taskDefinition.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.oa.task.typeSettings.entity.CompletionMark;
import com.vix.oa.task.typeSettings.entity.DifficultyCoefficient;
import com.vix.oa.task.typeSettings.entity.TaskLevel;
import com.vix.oa.task.typeSettings.entity.TaskSourceType;
import com.vix.oa.task.typeSettings.entity.TaskType;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * @ClassName: VixTask
 * @Description: 任务定义
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-2-24 下午3:45:04
 */
public class VixTask extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 判断日期如果对于0是今天如果对于1是昨天如果等于2是前天
	 * 
	 * @return
	 */
	public long getEndTimeDiff() {
		if (this.getEndTime() != null) {
			long time = Timestamp.valueOf(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss")).getTime();
			long timeDiff = time - this.getEndTime().getTime();

			if (timeDiff < 0) {
				return 0;
			} else {
				return timeDiff / 86400000 + 1;
			}

		} else {
			return 0;
		}
	}

	private String empliststr;
	/**
	 * 项目下达人
	 */
	private String employeestr;

	/** 中文首字母 */
	private String chineseFirstLetter;

	/** 任务名称 */
	public String questName;

	/** 有效期 */
	public String validity;

	/** 任务权重 */
	public String taskWeights;

	/** 任务描述 */
	public String taskDescription;

	/** 执行部门 */
	public String executiveAgency;

	/** 考核部门 */
	public String assessDepartment;

	/** 审核部门 */
	public String reviewDivision;

	/** 执行人 */
	public String transactor;

	/** 考核人 */
	public String appraisalPeople;

	/** 审核人 */
	public String reviewer;

	/** 反馈内容 */
	public String info;

	/** 上传时间 */
	private Date uploadTime;

	/** 任务开始时间 */
	private Date taskStartTime;

	/** 任务结束时间 */
	private Date taskEndTime;
	/** 任务实际开始时间 */
	private Date realityStartTime;

	/** 任务实际结束时间 */
	private Date realityEndTime;

	/** 上传人id */
	private String uploadPersonId;

	/** id上传人 */
	private String uploadPerson;

	private String uploadPersonName;

	/** 是否下达 0 下达 1终止 */
	public Integer isPublish;
	/**
	 * isDeleted 0正常,1已删除
	 * 
	 * isTemp 0草稿,1正式数据
	 * 
	 * status 0正常,1关闭
	 * 
	 * complete 0未开始 ,1进行中,2完成,3超时
	 */
	public Integer complete;
	/**
	 * 是否查看 N :新数据,O:已查看数据
	 */
	public String isNew;

	/**
	 * 发布人员类型 "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
	 */
	private String pubType;
	/**
	 * 发布人员类型 "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
	 */
	private String pubType1;
	/**
	 * 发布人员类型 "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
	 */
	private String pubType2;

	/** 发布对象的id集合 */
	private String pubIds;

	/** 发布对象的id集合 */
	private String pubIds1;

	/** 发布对象的id集合 */
	private String pubIds2;

	/** 进度 */
	public String schedule;
	/**
	 * 任务进度
	 */
	private Integer taskSchedule = 0;

	/**
	 * 是否为正式数据
	 */
	private String isTemp;
	private String searchText1;
	private String searchText2;

	/** 按部门发布 */
	private Set<OrganizationUnit> organizationUnits;

	/** 按人员发布 */
	private Set<Employee> employees;

	/** 任务来源 */
	private TaskSourceType taskSourceType;

	/**
	 * 任务类型：
	 * "1\":\"临时任务\",\"2\":\"紧急任务\",\"3\":\"日清任务\",\"4\":\"周期任务,\"5\":\"里程碑,\"6\":\"虚拟任务
	 */
	private TaskType taskType;

	/** 难度系数 */
	private DifficultyCoefficient difficultyCoefficient;

	/** 任务层级 */
	private TaskLevel taskLevel;

	/** 完成标志 */
	private CompletionMark completionMark;

	/** 执行反馈 */
	private Set<ExecutionFeedback> executionFeedbacks;

	/** 上传附件 */
	private Set<Uploader> uploader = new HashSet<Uploader>();

	/** 子任务集合 */
	private Set<VixTask> subVixTasks = new HashSet<VixTask>();
	/**
	 * 微信企业号成员
	 */
	// private Set<WxpQyContacts> subWxpQyContacts = new
	// HashSet<WxpQyContacts>();
	/**
	 * 图片
	 */
	private Set<WxpQyPicture> subWxpQyPictures = new HashSet<WxpQyPicture>();

	/** 父任务 */
	private VixTask parentVixTask;
	/**
	 * 项目
	 */
	private Project project;
	/**
	 * 发布人
	 */
	private Employee employee;
	/**
	 * 
	 */
	private CustomerAccount customerAccount;
	/**
	 * 
	 */
	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();
	/**
	 * 优先级
	 */
	private Integer priority;

	private String pictureUrl;
	/**
	 * 任务类型
	 * 
	 * "1\":\"临时任务\",\"2\":\"紧急任务\",\"3\":\"日清任务\",\"4\":\"周期任务,\"5\":\"里程碑,\"6\":\"虚拟任务
	 */
	private Integer type;
	/**
	 * 是否反馈1,是 0,否
	 */
	private String isFeedback;
	/**
	 * 开始反馈时间
	 */
	private String startFeedbackTime;
	/**
	 * 结束反馈时间
	 */
	private String endFeedbackTime;
	/**
	 * 0,普通 1,团队 2,项目
	 */
	private String flag;

	public String getProjectName() {

		return "";
	}

	public String getTaskStartTimeStr() {
		if (null != taskStartTime) {
			return DateUtil.format(taskStartTime);
		}
		return "";
	}

	public String getRealityStartTimeTimeStr() {
		if (null != realityStartTime) {
			return DateUtil.format(realityStartTime);
		}
		return "";
	}

	public String getRealityEndTimeTimeStr() {
		if (null != realityEndTime) {
			return DateUtil.format(realityEndTime);
		}
		return "";
	}

	public String getTaskStartTimeTimeStr() {
		if (null != taskStartTime) {
			return DateUtil.formatTime(taskStartTime);
		}
		return "";
	}

	public String getEmployeestr() {

		StringBuffer sb = new StringBuffer();
		if (getEmployee() != null) {
			sb.append("<a href='javascript:void(0)'><img src='" + getEmployee().getHeadImgUrl() + "'>"+getEmployee().getName()+"</a>");
			return String.valueOf(sb);
		} else {
			return employeestr;
		}
	}

	public void setEmployeestr(String employeestr) {
		this.employeestr = employeestr;
	}

	public String getTaskEndTimeStr() {
		if (null != taskEndTime) {
			return DateUtil.format(taskEndTime);
		}
		return "";
	}

	public String getTaskEndTimeTimeStr() {
		if (null != taskEndTime) {
			return DateUtil.formatTime(taskEndTime);
		}
		return "";
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return the empliststr
	 */
	public String getEmpliststr() {
		if (getEmployees() != null) {
			StringBuffer sb = new StringBuffer();
			Set<Employee> employees = getEmployees();
			for (Employee employee : employees) {
				if (employee != null) {
					sb.append("<a href='javascript:void(0)'><img src='" + employee.getHeadImgUrl() + "'>"+employee.getName()+"</a>");
				}
			}
			return sb.toString();
		} else {
			return empliststr;
		}
	}

	/**
	 * @param empliststr
	 *            the empliststr to set
	 */
	public void setEmpliststr(String empliststr) {
		this.empliststr = empliststr;
	}

	public VixTask() {
		super();
	}

	public VixTask(String id) {
		super();
		setId(id);
	}

	/**
	 * @return the pictureUrl
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * @param pictureUrl
	 *            the pictureUrl to set
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getQuestName() {
		return questName;
	}

	public void setQuestName(String questName) {
		this.questName = questName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the taskSchedule
	 */
	public Integer getTaskSchedule() {
		return taskSchedule;
	}

	/**
	 * @param taskSchedule
	 *            the taskSchedule to set
	 */
	public void setTaskSchedule(Integer taskSchedule) {
		this.taskSchedule = taskSchedule;
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
	 * @return the isNew
	 */
	public String getIsNew() {
		return isNew;
	}

	/**
	 * @param isNew
	 *            the isNew to set
	 */
	public void setIsNew(String isNew) {
		this.isNew = isNew;
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

	/**
	 * @return the customerAccount
	 */
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	/**
	 * @param customerAccount
	 *            the customerAccount to set
	 */
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getTaskWeights() {
		return taskWeights;
	}

	public void setTaskWeights(String taskWeights) {
		this.taskWeights = taskWeights;
	}

	public String getExecutiveAgency() {
		return executiveAgency;
	}

	public void setExecutiveAgency(String executiveAgency) {
		this.executiveAgency = executiveAgency;
	}

	public String getIsFeedback() {
		return isFeedback;
	}

	public void setIsFeedback(String isFeedback) {
		this.isFeedback = isFeedback;
	}

	public String getStartFeedbackTime() {
		return startFeedbackTime;
	}

	public void setStartFeedbackTime(String startFeedbackTime) {
		this.startFeedbackTime = startFeedbackTime;
	}

	public String getEndFeedbackTime() {
		return endFeedbackTime;
	}

	public void setEndFeedbackTime(String endFeedbackTime) {
		this.endFeedbackTime = endFeedbackTime;
	}

	public String getAssessDepartment() {
		return assessDepartment;
	}

	public void setAssessDepartment(String assessDepartment) {
		this.assessDepartment = assessDepartment;
	}

	public String getReviewDivision() {
		return reviewDivision;
	}

	public void setReviewDivision(String reviewDivision) {
		this.reviewDivision = reviewDivision;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getAppraisalPeople() {
		return appraisalPeople;
	}

	public void setAppraisalPeople(String appraisalPeople) {
		this.appraisalPeople = appraisalPeople;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public TaskSourceType getTaskSourceType() {
		return taskSourceType;
	}

	public void setTaskSourceType(TaskSourceType taskSourceType) {
		this.taskSourceType = taskSourceType;
	}

	public DifficultyCoefficient getDifficultyCoefficient() {
		return difficultyCoefficient;
	}

	public void setDifficultyCoefficient(DifficultyCoefficient difficultyCoefficient) {
		this.difficultyCoefficient = difficultyCoefficient;
	}

	public TaskLevel getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(TaskLevel taskLevel) {
		this.taskLevel = taskLevel;
	}

	public CompletionMark getCompletionMark() {
		return completionMark;
	}

	public void setCompletionMark(CompletionMark completionMark) {
		this.completionMark = completionMark;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Set<ExecutionFeedback> getExecutionFeedbacks() {
		return executionFeedbacks;
	}

	public void setExecutionFeedbacks(Set<ExecutionFeedback> executionFeedbacks) {
		this.executionFeedbacks = executionFeedbacks;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public Integer getComplete() {
		if (taskEndTime != null) {
			boolean flag = taskEndTime.before(new Date());
			if (flag)
				return 3;
		}
		if (taskSchedule != null) {
			if (taskSchedule == 100) {
				return 2;
			} else if (taskSchedule > 0 && taskSchedule < 100) {
				return 1;
			}
		}
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
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

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
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

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * @return the subVixTasks
	 */
	public Set<VixTask> getSubVixTasks() {
		return subVixTasks;
	}

	/**
	 * @param subVixTasks
	 *            the subVixTasks to set
	 */
	public void setSubVixTasks(Set<VixTask> subVixTasks) {
		this.subVixTasks = subVixTasks;
	}

	/**
	 * @return the parentVixTask
	 */
	public VixTask getParentVixTask() {
		return parentVixTask;
	}

	/**
	 * @param parentVixTask
	 *            the parentVixTask to set
	 */
	public void setParentVixTask(VixTask parentVixTask) {
		this.parentVixTask = parentVixTask;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
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

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Date getRealityStartTime() {
		return realityStartTime;
	}

	public void setRealityStartTime(Date realityStartTime) {
		this.realityStartTime = realityStartTime;
	}

	public Date getRealityEndTime() {
		return realityEndTime;
	}

	public void setRealityEndTime(Date realityEndTime) {
		this.realityEndTime = realityEndTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
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

	public Set<Uploader> getUploader() {
		return uploader;
	}

	public void setUploader(Set<Uploader> uploader) {
		this.uploader = uploader;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	/**
	 * @return the subWxpQyPictures
	 */
	public Set<WxpQyPicture> getSubWxpQyPictures() {
		return subWxpQyPictures;
	}

	/**
	 * @param subWxpQyPictures
	 *            the subWxpQyPictures to set
	 */
	public void setSubWxpQyPictures(Set<WxpQyPicture> subWxpQyPictures) {
		this.subWxpQyPictures = subWxpQyPictures;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}