package com.vix.hr.organization.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.mail.entity.MailInfo;
import com.vix.common.mail.entity.MailServer;
import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.entity.UserGroup;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.mdm.pm.common.entity.ProjectRole;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.personaloffice.entity.TripRecord;
import com.vix.oa.personaloffice.entity.WorkLog;
import com.vix.oa.share.entity.Trends;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.pm.org.entity.PmOrg;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * @ClassName: Employee
 * @Description: 企业职员
 * @author wangmingchen
 * @date 2013-5-4 上午9:15:55
 * 
 */
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 1L;
	// 编码用code
	/** 员工姓名 */
	// private String name;
	/** 曾经姓名 */
	private String oldName;
	/** 姓名缩写 */
	private String fsName;
	/** 员工职号 */
	private String staffJobNumber;
	/** 身份证号 */
	private String idNumber;
	/** 血型 */
	private String bloodType;

	/** 学历代号 */
	private String qualificationsCode;
	/** 科系代号 */
	private String departmentCode;
	/** 户籍地址 */
	private String residenceAddress;

	/** 出生年月 */
	private Date birthday;
	/** 性别 1：男 0：女 */
	private Integer gender;
	/** 婚否 1：是 0：否 */
	private String isMarriage;
	/** 毕业院校 */
	private String graduation;
	/** 个人专业 */
	private String professional;
	/** 籍贯 */
	private String birthplace;
	/** 现居住地 */
	private String currentResidence;
	/** 民族 */
	private String national;
	/** 是否离职 1：是 0：否 */
	private String isDemission;
	/** 入职时间 */
	private Date entityTime;
	/** 联系电话 */
	private String telephone;
	/** 人员状态 1：在职 0：后备 */
	private String employeeType;

	/**
	 * 职员类型 S标准,D分销,SE供应商,ST门店
	 */
	private String empType;

	/** 报销额度 */
	/* private Double expenseAccount; */
	/** 所属职务 */
	private String subordinatePosition;
	/** 所属职称 */
	private String subordinateTitle;
	/** 职务编码 */
	private String positionid;
	/** 职称编码 */
	private String titleid;
	/**
	 * 照片路径
	 */
	private String pictureurl;
	/** 职员登陆账号 */
	private Set<UserAccount> userAccounts;
	/** 职员用户组 */
	private Set<UserGroup> userGroups;

	/** 项目的 业务组织 */
	private Set<PmOrg> pmOrgs;

	/** 职员和岗位的关系 */
	private Set<OrgPosition> orgPositions;

	/** 所属部门 */
	private OrganizationUnit organizationUnit;
	/**
	 * 当账户类型 为D（分销）时 用户的所在的分销商信息 分销商/渠道
	 */
	private ChannelDistributor channelDistributor;
	/**
	 * 员工提成类型
	 */
	private EmployeeType employeePercentageType;

	/** 通讯录 */
	// private EmpCommunication empCommunication;
	/**
	 * 以下是个人的联系方式 邮件配置
	 */
	/** email */
	private String email;
	/** email 账户 */
	private String emailUserName;
	/** email 密码 */
	private String emailPassword;

	/** email 的服务器 配置 */
	private MailServer mailServer;

	/** 邮件 */
	private Set<MailInfo> mailInfos;

	/** 供应商 */
	private Supplier supplier;
	/**
	 * 微信企业号信息
	 */
	private String userId; // 成员UserID,非wxp_user表id
	private String position; // 职位信息'
	private String mobile; // 手机号码
	// String email; //邮箱
	private String weixinid; // 微信号。企业内必须唯一。（注意：是微信号，不是微信的名字）'
	private String openId; // 相对于公众号的标示，关联wxp_user粉丝表
	private String headImgUrl; // 成员头像
	private String qq; // 成员头像
	/**
	 * 任务
	 */
	private Set<VixTask> vixTasks = new HashSet<VixTask>();
	/**
	 * 公告通知
	 */
	private Set<AnnouncementNotification> announcementNotification = new HashSet<AnnouncementNotification>();
	/**
	 * 公告通知
	 */
	private Set<Trends> trends = new HashSet<Trends>();
	private Set<WorkLog> subWorkLogs = new HashSet<WorkLog>();
	/**
	 * 项目
	 */
	private Set<Project> subProjects = new HashSet<Project>();
	private Set<WxpQyPicture> subWxpQyPictures = new HashSet<WxpQyPicture>();
	private Set<TripRecord> subTripRecords = new HashSet<TripRecord>();
	/**
	 * 发起的会议
	 */
	private Set<ApplicationMg> issuerApplicationMgs = new HashSet<ApplicationMg>();
	/**
	 * 参与的会议
	 */
	private Set<ApplicationMg> affiliatedApplicationMgs = new HashSet<ApplicationMg>();
	/**
	 * 项目组织
	 */
	private Set<ProjectRole> subProjectRoles = new HashSet<ProjectRole>();
	/**
	 * 上传附件
	 */
	private Set<Uploader> subUploaders = new HashSet<Uploader>();

	/**
	 * 密级
	 */
	private Integer declassified;
	/**
	 * 员工职务 0,管理员 1,店员 只在门店启用
	 */
	private String userType;
	/**
	 * 是否可以查看普通员工不能查看的数据 0,否; 1,是.
	 */
	private String isViewData;

	public Employee() {
	}

	public Employee(String id) {
		super();
		setId(id);
	}

	public String getBirthdayString() {
		if (null != this.getBirthday()) {
			return DateUtil.format(this.getBirthday(), "yyyy-MM-dd");
		} else {
			return "";
		}
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	public String getOrganizationUnitName() {
		if (organizationUnit != null) {
			return organizationUnit.getFs();
		}
		return "";
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		if (organizationUnit != null) {
			return organizationUnit.getFs();
		}
		return position;
	}
    
	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	public Set<Uploader> getSubUploaders() {
		return subUploaders;
	}

	public void setSubUploaders(Set<Uploader> subUploaders) {
		this.subUploaders = subUploaders;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the subWorkLogs
	 */
	public Set<WorkLog> getSubWorkLogs() {
		return subWorkLogs;
	}

	/**
	 * @param subWorkLogs
	 *            the subWorkLogs to set
	 */
	public void setSubWorkLogs(Set<WorkLog> subWorkLogs) {
		this.subWorkLogs = subWorkLogs;
	}

	/**
	 * @return the subProjects
	 */
	public Set<Project> getSubProjects() {
		return subProjects;
	}

	/**
	 * @param subProjects
	 *            the subProjects to set
	 */
	public void setSubProjects(Set<Project> subProjects) {
		this.subProjects = subProjects;
	}

	/**
	 * @return the issuerApplicationMgs
	 */
	public Set<ApplicationMg> getIssuerApplicationMgs() {
		return issuerApplicationMgs;
	}

	/**
	 * @param issuerApplicationMgs
	 *            the issuerApplicationMgs to set
	 */
	public void setIssuerApplicationMgs(Set<ApplicationMg> issuerApplicationMgs) {
		this.issuerApplicationMgs = issuerApplicationMgs;
	}

	/**
	 * @return the affiliatedApplicationMgs
	 */
	public Set<ApplicationMg> getAffiliatedApplicationMgs() {
		return affiliatedApplicationMgs;
	}

	/**
	 * @param affiliatedApplicationMgs
	 *            the affiliatedApplicationMgs to set
	 */
	public void setAffiliatedApplicationMgs(Set<ApplicationMg> affiliatedApplicationMgs) {
		this.affiliatedApplicationMgs = affiliatedApplicationMgs;
	}

	/**
	 * @return the subTripRecords
	 */
	public Set<TripRecord> getSubTripRecords() {
		return subTripRecords;
	}

	/**
	 * @param subTripRecords
	 *            the subTripRecords to set
	 */
	public void setSubTripRecords(Set<TripRecord> subTripRecords) {
		this.subTripRecords = subTripRecords;
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

	/**
	 * @return the weixinid
	 */
	public String getWeixinid() {
		return weixinid;
	}

	/**
	 * @param weixinid
	 *            the weixinid to set
	 */
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId
	 *            the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the headImgUrl
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * @param headImgUrl
	 *            the headImgUrl to set
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Set<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public Set<OrgPosition> getOrgPositions() {
		return orgPositions;
	}

	public void setOrgPositions(Set<OrgPosition> orgPositions) {
		this.orgPositions = orgPositions;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailUserName() {
		return emailUserName;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public MailServer getMailServer() {
		return mailServer;
	}

	public void setMailServer(MailServer mailServer) {
		this.mailServer = mailServer;
	}

	public Set<MailInfo> getMailInfos() {
		return mailInfos;
	}

	public void setMailInfos(Set<MailInfo> mailInfos) {
		this.mailInfos = mailInfos;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Set<PmOrg> getPmOrgs() {
		return pmOrgs;
	}

	public void setPmOrgs(Set<PmOrg> pmOrgs) {
		this.pmOrgs = pmOrgs;
	}

	public String getSubordinatePosition() {
		return subordinatePosition;
	}

	public void setSubordinatePosition(String subordinatePosition) {
		this.subordinatePosition = subordinatePosition;
	}

	public String getSubordinateTitle() {
		return subordinateTitle;
	}

	public void setSubordinateTitle(String subordinateTitle) {
		this.subordinateTitle = subordinateTitle;
	}

	public String getPositionid() {
		return positionid;
	}

	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}

	public String getTitleid() {
		return titleid;
	}

	public void setTitleid(String titleid) {
		this.titleid = titleid;
	}

	public String getPictureurl() {
		return pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public String getStaffJobNumber() {
		return staffJobNumber;
	}

	public void setStaffJobNumber(String staffJobNumber) {
		this.staffJobNumber = staffJobNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getQualificationsCode() {
		return qualificationsCode;
	}

	public void setQualificationsCode(String qualificationsCode) {
		this.qualificationsCode = qualificationsCode;
	}

	@Override
	public String getDepartmentCode() {
		return departmentCode;
	}

	@Override
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getIsMarriage() {
		return isMarriage;
	}

	public void setIsMarriage(String isMarriage) {
		this.isMarriage = isMarriage;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getCurrentResidence() {
		return currentResidence;
	}

	public void setCurrentResidence(String currentResidence) {
		this.currentResidence = currentResidence;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getIsDemission() {
		return isDemission;
	}

	public void setIsDemission(String isDemission) {
		this.isDemission = isDemission;
	}

	public Date getEntityTime() {
		return entityTime;
	}

	public String getEntityTimeStr() {
		if (null != entityTime) {
			return DateUtil.format(entityTime);
		}
		return "";
	}

	public void setEntityTime(Date entityTime) {
		this.entityTime = entityTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
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

	public Set<AnnouncementNotification> getAnnouncementNotification() {
		return announcementNotification;
	}

	public void setAnnouncementNotification(Set<AnnouncementNotification> announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

	public Set<Trends> getTrends() {
		return trends;
	}

	public void setTrends(Set<Trends> trends) {
		this.trends = trends;
	}

	public Integer getDeclassified() {
		return declassified;
	}

	public void setDeclassified(Integer declassified) {
		this.declassified = declassified;
	}

	public Set<ProjectRole> getSubProjectRoles() {
		return subProjectRoles;
	}

	public void setSubProjectRoles(Set<ProjectRole> subProjectRoles) {
		this.subProjectRoles = subProjectRoles;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the isViewData
	 */
	public String getIsViewData() {
		return isViewData;
	}

	/**
	 * @param isViewData
	 *            the isViewData to set
	 */
	public void setIsViewData(String isViewData) {
		this.isViewData = isViewData;
	}

	public EmployeeType getEmployeePercentageType() {
		return employeePercentageType;
	}

	public void setEmployeePercentageType(EmployeeType employeePercentageType) {
		this.employeePercentageType = employeePercentageType;
	}
	
}