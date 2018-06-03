
package com.vix.oa.personaloffice.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 
 * @ClassName: WorkLog
 * @Description: 工作日志
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-4-17 下午3:11:49
 */
public class WorkLog extends BaseEntity {

	private static final long serialVersionUID = 1396865628678655168L;
	/** 中文首字母 */
	private String chineseFirstLetter;

	/** 日志标题 */
	private String title;

	/** 日志类型 0 工作日志 1个人日志 */
	private Integer logType;

	/** 日志日期 */
	private Date logDate;

	private String bizOrgNames;

	/** 日志内容 */
	private String logContent;

	/** 评论条数 */
	public long commentsnumber;

	public void setCommentsnumber(long commentsnumber) {
		this.commentsnumber = commentsnumber;
	}

	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 阅读次数 */
	private Long readTimes;
	/** 工作日志评论 */
	private Set<LogComment> logComment = new HashSet<LogComment>();
	/**
	 * 图片附件
	 */
	private Set<WxpQyPicture> subWxpQyPictures = new HashSet<WxpQyPicture>();
	/**
	 * 附件
	 */
	private Set<Uploader> subUploaders = new HashSet<Uploader>();

	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();

	private Set<Employee> subEmployees = new HashSet<Employee>();

	private Employee employee;
	/**
	 * 附件数量
	 */
	private Integer uploaderNum;

	public WorkLog() {
		super();
	}

	public WorkLog(String id) {
		super();
		setId(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	/**
	 * @return the readTimes
	 */
	public Long getReadTimes() {
		return readTimes;
	}

	/**
	 * @param readTimes
	 *            the readTimes to set
	 */
	public void setReadTimes(Long readTimes) {
		this.readTimes = readTimes;
	}

	/**
	 * @return the subEmployees
	 */
	public Set<Employee> getSubEmployees() {
		return subEmployees;
	}

	/**
	 * @param subEmployees
	 *            the subEmployees to set
	 */
	public void setSubEmployees(Set<Employee> subEmployees) {
		this.subEmployees = subEmployees;
	}

	public Set<EvaluationReview> getSubEvaluationReviews() {
		return subEvaluationReviews;
	}

	public void setSubEvaluationReviews(Set<EvaluationReview> subEvaluationReviews) {
		this.subEvaluationReviews = subEvaluationReviews;
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

	public String getLogDateStr() {
		if (null != logDate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(logDate);
		} else {
			return "";
		}
	}

	public Integer getUploaderNum() {
		if (getSubUploaders() != null && getSubUploaders().size() > 0) {
			return getSubUploaders().size();
		}
		return uploaderNum;
	}

	public void setUploaderNum(Integer uploaderNum) {
		this.uploaderNum = uploaderNum;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public Set<LogComment> getLogComment() {
		return logComment;
	}

	public void setLogComment(Set<LogComment> logComment) {
		this.logComment = logComment;
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

	public Long getCommentsnumber() {
		return commentsnumber;
	}

	public void setCommentsnumber(Long commentsnumber) {
		this.commentsnumber = commentsnumber;
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

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	/**
	 * @return the subUploaders
	 */
	public Set<Uploader> getSubUploaders() {
		return subUploaders;
	}

	/**
	 * @param subUploaders
	 *            the subUploaders to set
	 */
	public void setSubUploaders(Set<Uploader> subUploaders) {
		this.subUploaders = subUploaders;
	}

	public String getBizOrgNames() {
		return bizOrgNames;
	}

	public void setBizOrgNames(String bizOrgNames) {
		this.bizOrgNames = bizOrgNames;
	}

}
