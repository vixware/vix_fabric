package com.vix.oa.share.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 
 * @ClassName: Trends
 * @Description: 行政办公——新闻管理
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-3-20 下午6:24:23
 */
public class Trends extends BaseEntity {

	private static final long serialVersionUID = 6113386415996390066L;

	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 标题 */
	public String title;
	/** 附标题 */
	public String subtitle;
	/** 关键词 */
	public String keywords;
	/** 内容 */
	private String content;
	/** 关键内容 */
	private String keyContent;
	/** 新闻来源 */
	private String sourceFrom;
	/** 新闻来源url */
	private String sourceFromUrl;
	/** 按需求选择部门人员角色 */
	private String bizOrgNames;
	/*发布时间*/
	private Date createTime;

	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;

	private String uploadPersonName;
	/**
	 * 发布人员类型 "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
	 */
	private String pubType;

	/** 发布对象的id集合 */
	private String pubIds;

	/** 是否审核 1 是 0 否 */
	public Integer isAudit;
	/** 审核时间 */
	public Date auditDate;
	/** 是否发布 1 生效 0终止 */
	public Integer isPublish;
	/** 是否置顶 1 是 0否 */
	public Integer isTop;
	/** 新闻类型 0全体新闻 1内部新闻 */
	public Integer bulletinType;

	/** 阅读次数 */
	public Long readTimes;
	/**
	 * 点赞次数
	 */
	public Integer pointPraiseNums;
	/**
	 * 回复数量
	 */
	public Long replyNums;
	/**
	 * 新闻类型 0 文本新闻 1 图片新闻
	 */
	public Integer newsType;

	/**
	 * 评论类型 0 实名评论 1匿名评论 2禁止评论
	 */
	public Integer review;

	/** 图片新闻的路径 */
	public String imgPath;

	/** 新闻评论 */
	private Set<Comments> comments = new HashSet<Comments>();

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
	/**
	 * 图片
	 */
	private Set<WxpQyPicture> subWxpQyPictures;
	/**
	 * 是否查看 N :新数据,O:已查看数据
	 */
	private String isNew;
	/**
	 * 首个图片的路径
	 */
	private String firstPictureUrl;
	/**
	 * 发布人
	 */
	private Employee employee;
	/**
	 * 
	 */
	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();

	public Trends() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyContent() {
		return keyContent;
	}

	public void setKeyContent(String keyContent) {
		this.keyContent = keyContent;
	}

	public String getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public String getSourceFromUrl() {
		return sourceFromUrl;
	}

	public void setSourceFromUrl(String sourceFromUrl) {
		this.sourceFromUrl = sourceFromUrl;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
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

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
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

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getBizOrgNames() {
		return bizOrgNames;
	}

	public void setBizOrgNames(String bizOrgNames) {
		this.bizOrgNames = bizOrgNames;
	}

	public Integer getReview() {
		return review;
	}

	public void setReview(Integer review) {
		this.review = review;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Override
	public String getCreateTimeStr() {
		if (null != createTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(createTime);
		} else {
			return "";
		}
	}

	public Integer getBulletinType() {
		return bulletinType;
	}

	public void setBulletinType(Integer bulletinType) {
		this.bulletinType = bulletinType;
	}
}
