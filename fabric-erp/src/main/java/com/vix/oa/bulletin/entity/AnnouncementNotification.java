package com.vix.oa.bulletin.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.wechat.base.entity.WxpQyPicture;

public class AnnouncementNotification extends BaseEntity {

	private static final long serialVersionUID = -2460679357978359820L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 公告分类 字典key */
	public String catalogy;
	/** 标题 */
	public String title;
	/** 内容 */
	private String content;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	/**
	 * 动态类型
	 */
	private String type;

	private String uploadPersonName;
	/** 有效期开始时间 */
	private Date activeStartDate;
	/** 有效期结束时间 */
	private Date activeEndDate;

	/** 插入事务提醒 0 否 1 是 */
	private Boolean sendMsg;
	/** 置顶 */
	/** 是否置顶 0 否 1 是 */
	private Boolean onTop;

	/** 天后结束置顶，0表示一直置顶 */
	private Integer onTopDay;

	/**
	 * 发布人员类型 "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
	 */
	private String pubType;
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
	/** 发布对象的名称集合 */
	private String pubNames;

	/** 内容简介 */
	private String plotSummary;

	/** 关键词 */
	public String keywords;

	/** 开始时间和结束时间参见父类 */

	/**
	 * 数据状态 0 创建 1 审核中 2 审核通过 3 审核没通过
	 * 
	 **/
	public Integer bulletinStatus;

	/** 阅读次数 */
	public Long readTimes;

	/** 失效日期 */
	public Date invalidDate;

	/** 创建人 */
	public UserAccount createUser;

	/** 审核人 */
	public UserAccount auditUser;

	/** 是否审核 1 是 0 否 */
	public Integer isAudit;

	/** 审核时间 */
	public Date auditDate;

	/** 是否发布 1 生效 0终止 */
	public Integer isPublish;

	/** 是否已读 1 生效 0终止 */
	public Integer isRead;

	/** 是否发送消息 */
	public Integer isSendMsg;

	/** 公告类型 0全体公告 1部门公告 2行政公告 3通知 */
	public Integer bulletinType;

	/** 公告通知查看人员统计 */
	private Set<AccountStatements> accountStatements = new HashSet<AccountStatements>();

	/** 上传附件 */
	private Set<NoticeUploader> noticeUploader = new HashSet<NoticeUploader>();
	private Set<WxpQyPicture> subWxpQyPicture = new HashSet<WxpQyPicture>();
	/**
	 * 第一张图片的地址
	 */
	private String firstPictureUrl;
	/**
	 * 是否查看 N :新数据,O:已查看数据
	 */
	private String isNew;

	/**
	 * 发布人
	 */
	private Employee employee;
	/**
	 * 点赞次数
	 */
	private Integer pointPraiseNums;

	private Long replyNums;
	/**
	 * 
	 */
	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();

	public AnnouncementNotification() {
		super();
	}

	public AnnouncementNotification(String id) {
		super();
		setId(id);
	}

	public String getCatalogy() {
		return catalogy;
	}

	public void setCatalogy(String catalogy) {
		this.catalogy = catalogy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Long readTimes) {
		this.readTimes = readTimes;
	}

	/**
	 * @return the replyNums
	 */
	public Long getReplyNums() {
		return replyNums;
	}

	/**
	 * @param replyNums
	 *            the replyNums to set
	 */
	public void setReplyNums(Long replyNums) {
		this.replyNums = replyNums;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	/**
	 * @return the subWxpQyPicture
	 */
	public Set<WxpQyPicture> getSubWxpQyPicture() {
		return subWxpQyPicture;
	}

	/**
	 * @param subWxpQyPicture
	 *            the subWxpQyPicture to set
	 */
	public void setSubWxpQyPicture(Set<WxpQyPicture> subWxpQyPicture) {
		this.subWxpQyPicture = subWxpQyPicture;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
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
	 * @return the firstPictureUrl
	 */
	public String getFirstPictureUrl() {
		return firstPictureUrl;
	}

	/**
	 * @param firstPictureUrl
	 *            the firstPictureUrl to set
	 */
	public void setFirstPictureUrl(String firstPictureUrl) {
		this.firstPictureUrl = firstPictureUrl;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public Integer getIsSendMsg() {
		return isSendMsg;
	}

	public void setIsSendMsg(Integer isSendMsg) {
		this.isSendMsg = isSendMsg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserAccount getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserAccount createUser) {
		this.createUser = createUser;
	}

	public UserAccount getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(UserAccount auditUser) {
		this.auditUser = auditUser;
	}

	public Integer getBulletinStatus() {
		return bulletinStatus;
	}

	public void setBulletinStatus(Integer bulletinStatus) {
		this.bulletinStatus = bulletinStatus;
	}

	public Date getActiveStartDate() {
		return activeStartDate;
	}

	public void setActiveStartDate(Date activeStartDate) {
		this.activeStartDate = activeStartDate;
	}

	public String getActiveStartDateStr() {
		if (null != activeStartDate) {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return myFormatter.format(activeStartDate);
		}
		return "";
	}

	public Date getActiveEndDate() {
		return activeEndDate;
	}

	public void setActiveEndDate(Date activeEndDate) {
		this.activeEndDate = activeEndDate;
	}

	public String getActiveEndDateStr() {
		if (null != activeEndDate) {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return myFormatter.format(activeEndDate);
		}
		return "";
	}

	public Boolean getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(Boolean sendMsg) {
		this.sendMsg = sendMsg;
	}

	public Boolean getOnTop() {
		return onTop;
	}

	public void setOnTop(Boolean onTop) {
		this.onTop = onTop;
	}

	public Integer getOnTopDay() {
		return onTopDay;
	}

	public void setOnTopDay(Integer onTopDay) {
		this.onTopDay = onTopDay;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

	public String getPlotSummary() {
		return plotSummary;
	}

	public void setPlotSummary(String plotSummary) {
		this.plotSummary = plotSummary;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getBulletinType() {
		return bulletinType;
	}

	public void setBulletinType(Integer bulletinType) {
		this.bulletinType = bulletinType;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
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

	public Set<AccountStatements> getAccountStatements() {
		return accountStatements;
	}

	public void setAccountStatements(Set<AccountStatements> accountStatements) {
		this.accountStatements = accountStatements;
	}

	public Set<NoticeUploader> getNoticeUploader() {
		return noticeUploader;
	}

	public void setNoticeUploader(Set<NoticeUploader> noticeUploader) {
		this.noticeUploader = noticeUploader;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	/**
	 * @return the pointPraiseNums
	 */
	public Integer getPointPraiseNums() {
		return pointPraiseNums;
	}

	/**
	 * @param pointPraiseNums
	 *            the pointPraiseNums to set
	 */
	public void setPointPraiseNums(Integer pointPraiseNums) {
		this.pointPraiseNums = pointPraiseNums;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}