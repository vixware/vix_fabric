package com.vix.oa.task.taskDefinition.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.project.entity.CrmProject;
import com.vix.crm.project.entity.ProjectRequirement;
import com.vix.crm.project.entity.ProjectSolution;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.personaloffice.entity.Communication;
import com.vix.oa.personaloffice.entity.WorkLog;
import com.vix.oa.travelclaims.entity.TravelExpense;

/**
 * 
 * @ClassName: Uploader
 * @Description: 上传附件
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-7-21 上午11:13:36
 */
public class Uploader extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 文件名称 */
	private String fileName;

	/** 文件路径 */
	private String filePath;

	/** 上传时间 */
	private Date uploadTime;

	/** 上传人id */
	private String uploadPersonId;

	/** id上传人 */
	private String uploadPerson;

	private String uploadPersonName;

	/** id备注 */
	private String note;

	/** 文档名称 */
	private String title;

	/** 文档类型 */
	private String fileType;
	/**
	 * 文件大小
	 */
	private String filesize;

	/** 任务定义 */
	private VixTask taskDefinition;
	/**
	 * 项目
	 */
	private Project project;

	/** 任务反馈 */
	private ExecutionFeedback executionFeedback;
	/**
	 * 工作日志
	 */
	private WorkLog workLog;
	/** CRM项目 */
	private CrmProject crmProject;
	/** 详细需求 */
	private ProjectRequirement projectRequirement;
	/** CRM项目 */
	private ProjectSolution projectSolution;
	/**
	 * 文档类型
	 */
	private UploaderType uploaderType;
	private UploaderTypeKeyWord uploaderTypeKeyWord;
	private ApplicationMg applicationMg;
	private TravelExpense travelExpense;
	private Communication communication;
	/**
	 * 下载次数
	 */
	private Integer downloadNum = 0;
	/**
	 * 密级
	 */
	private Integer declassified;
	/**
	 * 创建人
	 */
	private Employee employee;

	private Set<Employee> subEmployees = new HashSet<Employee>();

	public Uploader() {
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getDeclassified() {
		return declassified;
	}

	public void setDeclassified(Integer declassified) {
		this.declassified = declassified;
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

	public Integer getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}

	public Communication getCommunication() {
		return communication;
	}

	public void setCommunication(Communication communication) {
		this.communication = communication;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	/**
	 * @return the filesize
	 */
	public String getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize
	 *            the filesize to set
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	/**
	 * @return the travelExpense
	 */
	public TravelExpense getTravelExpense() {
		return travelExpense;
	}

	/**
	 * @param travelExpense
	 *            the travelExpense to set
	 */
	public void setTravelExpense(TravelExpense travelExpense) {
		this.travelExpense = travelExpense;
	}

	public Set<Employee> getSubEmployees() {
		return subEmployees;
	}

	public void setSubEmployees(Set<Employee> subEmployees) {
		this.subEmployees = subEmployees;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public VixTask getTaskDefinition() {
		return taskDefinition;
	}

	public void setTaskDefinition(VixTask taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public ExecutionFeedback getExecutionFeedback() {
		return executionFeedback;
	}

	public void setExecutionFeedback(ExecutionFeedback executionFeedback) {
		this.executionFeedback = executionFeedback;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public ProjectRequirement getProjectRequirement() {
		return projectRequirement;
	}

	public void setProjectRequirement(ProjectRequirement projectRequirement) {
		this.projectRequirement = projectRequirement;
	}

	public ProjectSolution getProjectSolution() {
		return projectSolution;
	}

	public void setProjectSolution(ProjectSolution projectSolution) {
		this.projectSolution = projectSolution;
	}

	/**
	 * @return the uploaderTypeKeyWord
	 */
	public UploaderTypeKeyWord getUploaderTypeKeyWord() {
		return uploaderTypeKeyWord;
	}

	/**
	 * @param uploaderTypeKeyWord
	 *            the uploaderTypeKeyWord to set
	 */
	public void setUploaderTypeKeyWord(UploaderTypeKeyWord uploaderTypeKeyWord) {
		this.uploaderTypeKeyWord = uploaderTypeKeyWord;
	}

	/**
	 * @return the uploaderType
	 */
	public UploaderType getUploaderType() {
		return uploaderType;
	}

	/**
	 * @param uploaderType
	 *            the uploaderType to set
	 */
	public void setUploaderType(UploaderType uploaderType) {
		this.uploaderType = uploaderType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getEmployeeName() {
		if (employee != null) {
			return employee.getName();
		}
		return "";
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
