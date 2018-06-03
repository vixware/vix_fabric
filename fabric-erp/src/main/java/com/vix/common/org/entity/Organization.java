package com.vix.common.org.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * @ClassName: Organization
 * @Description: 组织机构公司 集团与公司的行政单位，也包括支持连锁店模式i的门店实体。
 * 
 *               公司为经营实体，记录公司的基本信息，其中包括集团公司、子公司、门店等信息。
 * 
 *               orgType表示：集团公司、全资子公司、参股子公司、直接经营的门店、加盟的门店等经济实体。
 *               orgType同样表示：销售分公司、销售组织、渠道、代理商、销售点、销售办公室等组织结构。
 * 
 *               分销管理中的渠道、代理商是否也融合在此处进行管理还是单独设计一套模型进行处理？
 *               统一处理i结构可以简化对应的业务单据的逻辑，同样可以将账号信息统一进行处理。
 * 
 *               公司编码使用前缀编码模式，同一个集团的实体尽量采用统一前缀。
 * @author wangmingchen  
 * @date 2013-5-4 下午4:31:49
 * 
 */
public class Organization extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 名称 */
	private String orgName;
	/** 简称 */
	private String briefName;
	/** 拼音缩写 */
	private String shortName;
	/** 英文名称 */
	private String enName;

	/** 地址 */
	private String address;
	/** 所属国家 */
	private String country;
	/** 地区 */
	private String area;
	/** 使用语言 */
	private String countryLanguage;
	/** 成立时间 */
	private Date companyCreateDate;
	/** 联系电话 */
	private String telephone;
	/** 传真 */
	private String fax;
	/** 所属行业 */
	private String industry;
	/** 法人代表 */
	private String corporateRepresentative;
	/** 主页 */
	private String homePage;
	/** 组织简介 */
	private String orgSummary;
	/** 启用时间 */
	private Date startUsingDate;
	/** 停用时间 */
	private Date stopUsingDate;
	/** 备注 */
	private String memo;

	/**
	 * 跳转页面
	 */
	private String configUrlCode;

	// ********************************************************************************8以下数据没有进行持久化
	/**
	 * 组织类型
	 * 
	 * jtgs 集团公司 gs 公司
	 * 
	 */
	private String orgType;

	/** 期间数 */
	private Integer numberOfPeriods;
	/** 当前总帐期间 */
	private Integer currentPeriod;
	/** 财务年度开始日期 */
	private Date beginningOfFiscalYear;
	/** 应付会计期间 */
	private Integer accountPayableCurrentPeroid;
	/** 应付会计年度起始日期 */
	private Date accountPaybaleBeginningOfFisaclYear;
	/** 应收会计期间 */
	private Integer accountReceivableCurrentPeriod;
	/** 应收会计年度起始日期 */
	private Date accountReceivableBeginningOfFiscalYear;
	/** 本位币 */
	private String currency;
	/** 会计期间日历 */
	private String fiscalCalendar;
	/** 是否评估组织架构 */
	private String isEvaluationOrg;

	/** 父公司 */
	private Organization parentOrganization;
	/** 子公司集合 */
	private Set<Organization> subOrganizations = new HashSet<Organization>();

	/** 公司组织机构单元 */
	private Set<OrganizationUnit> organizationUnits;

	/** 公司的职位 */
	private Set<OrgJob> orgJobs;

	/** 分销/渠道（部门） */
	private Set<ChannelDistributor> channelDistributors = new HashSet<ChannelDistributor>();

	/** 公司超级管理员 */
	private UserAccount compSuperAdmin;
	private String userAccountId;
	private String account;
	private String password;
	private String passwordConfirm;

	/**
	 * 如果该公司类型为 集团公司，则启用该属性。 SecurityScope.USER_ORG_DATAFILTERTYPE_N N 不使用
	 * SecurityScope.USER_ORG_DATAFILTERTYPE_P 只能查看自己数据
	 * SecurityScope.USER_ORG_DATAFILTERTYPE_A 集团公司人员可查看自己和所有子公司数据，
	 * 子公司人员只能查看本公司数据
	 */
	private String orgDataFilterType;

	/** 行业模块 */
	private String industryManagementModuleIds;

	private String industryManagementModuleNames;

	/**
	 * 物料属性 制造 流通 服务
	 * 
	 */
	private String matProp;

	// #################### oc 相关业务数据购买控制数据 begin ############################
	// null 不限制数量
	/** 用户数量 */
	private Integer bizDataAccountAmount;
	/** 订单数量 */
	private Integer bizDataOrderAmount;
	// #################### oc 相关业务数据购买控制数据 end ############################

	/** 选择服务 */
	private String serviceTypeList;

	// private String wxSuite;
	// private String o2oBiz;
	// private String sdpBiz;
	/** 微信类型 */
	// private String wxSuiteType;

	/** 服务年限 */
	private String serviceTime;

	// 2016 03 09 新扩展属性
	// 法人 参见 corporateRepresentative
	// 企业注册码
	private String companyOrgRegCode;
	// 联系人
	private String companyOrgContact;
	// email
	private String email;// email
	// mobile
	private String mobile;// email

	/**
	 * 是否开启微信企业号 Y 开启
	 */
	private String isOpenWxQy;
	/**
	 * 是否进行企业号授权 Y 已经授权 N 未授权
	 */
	private String isAuthWxQy;

	private String appId; // 公众号id

	/** 非持久化 企业号编码 */
	private String wxQyCode;

	public Organization() {
	}

	public Organization(String id) {
		setId(id);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getConfigUrlCode() {
		return configUrlCode;
	}

	public void setConfigUrlCode(String configUrlCode) {
		this.configUrlCode = configUrlCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String getAppId() {
		return appId;
	}

	@Override
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCountryLanguage() {
		return countryLanguage;
	}

	public void setCountryLanguage(String countryLanguage) {
		this.countryLanguage = countryLanguage;
	}

	public Date getCompanyCreateDate() {
		return companyCreateDate;
	}
    
	public String getCompanyCreateDateStr() {
		if (null != companyCreateDate) {
			return DateUtil.format(companyCreateDate);
		}
		return "";
	}
	
	public void setCompanyCreateDate(Date companyCreateDate) {
		this.companyCreateDate = companyCreateDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCorporateRepresentative() {
		return corporateRepresentative;
	}

	public void setCorporateRepresentative(String corporateRepresentative) {
		this.corporateRepresentative = corporateRepresentative;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getOrgSummary() {
		return orgSummary;
	}

	public void setOrgSummary(String orgSummary) {
		this.orgSummary = orgSummary;
	}

	public Date getStartUsingDate() {
		return startUsingDate;
	}

	public void setStartUsingDate(Date startUsingDate) {
		this.startUsingDate = startUsingDate;
	}

	public Date getStopUsingDate() {
		return stopUsingDate;
	}

	public void setStopUsingDate(Date stopUsingDate) {
		this.stopUsingDate = stopUsingDate;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Integer getNumberOfPeriods() {
		return numberOfPeriods;
	}

	public void setNumberOfPeriods(Integer numberOfPeriods) {
		this.numberOfPeriods = numberOfPeriods;
	}

	public Integer getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Integer currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public Date getBeginningOfFiscalYear() {
		return beginningOfFiscalYear;
	}

	public void setBeginningOfFiscalYear(Date beginningOfFiscalYear) {
		this.beginningOfFiscalYear = beginningOfFiscalYear;
	}

	public Integer getAccountPayableCurrentPeroid() {
		return accountPayableCurrentPeroid;
	}

	public void setAccountPayableCurrentPeroid(Integer accountPayableCurrentPeroid) {
		this.accountPayableCurrentPeroid = accountPayableCurrentPeroid;
	}

	public Date getAccountPaybaleBeginningOfFisaclYear() {
		return accountPaybaleBeginningOfFisaclYear;
	}

	public void setAccountPaybaleBeginningOfFisaclYear(Date accountPaybaleBeginningOfFisaclYear) {
		this.accountPaybaleBeginningOfFisaclYear = accountPaybaleBeginningOfFisaclYear;
	}

	public Integer getAccountReceivableCurrentPeriod() {
		return accountReceivableCurrentPeriod;
	}

	public void setAccountReceivableCurrentPeriod(Integer accountReceivableCurrentPeriod) {
		this.accountReceivableCurrentPeriod = accountReceivableCurrentPeriod;
	}

	public Date getAccountReceivableBeginningOfFiscalYear() {
		return accountReceivableBeginningOfFiscalYear;
	}

	public void setAccountReceivableBeginningOfFiscalYear(Date ReceivableBeginningOfFiscalYear) {
		this.accountReceivableBeginningOfFiscalYear = accountReceivableBeginningOfFiscalYear;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFiscalCalendar() {
		return fiscalCalendar;
	}

	public void setFiscalCalendar(String fiscalCalendar) {
		this.fiscalCalendar = fiscalCalendar;
	}

	public String getIsEvaluationOrg() {
		return isEvaluationOrg;
	}

	public void setIsEvaluationOrg(String isEvaluationOrg) {
		this.isEvaluationOrg = isEvaluationOrg;
	}

	public Organization getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(Organization parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

	public Set<Organization> getSubOrganizations() {
		return subOrganizations;
	}

	public void setSubOrganizations(Set<Organization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

	public Set<OrgJob> getOrgJobs() {
		return orgJobs;
	}

	public void setOrgJobs(Set<OrgJob> orgJobs) {
		this.orgJobs = orgJobs;
	}

	public Set<ChannelDistributor> getChannelDistributors() {
		return channelDistributors;
	}

	public void setChannelDistributors(Set<ChannelDistributor> channelDistributors) {
		this.channelDistributors = channelDistributors;
	}

	public UserAccount getCompSuperAdmin() {
		return compSuperAdmin;
	}

	public void setCompSuperAdmin(UserAccount compSuperAdmin) {
		this.compSuperAdmin = compSuperAdmin;
	}

	public String getOrgDataFilterType() {
		return orgDataFilterType;
	}

	public void setOrgDataFilterType(String orgDataFilterType) {
		this.orgDataFilterType = orgDataFilterType;
	}

	public String getIndustryManagementModuleIds() {
		return industryManagementModuleIds;
	}

	public void setIndustryManagementModuleIds(String industryManagementModuleIds) {
		this.industryManagementModuleIds = industryManagementModuleIds;
	}

	public String getIndustryManagementModuleNames() {
		return industryManagementModuleNames;
	}

	public void setIndustryManagementModuleNames(String industryManagementModuleNames) {
		this.industryManagementModuleNames = industryManagementModuleNames;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBriefName() {
		return briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getMatProp() {
		return matProp;
	}

	public void setMatProp(String matProp) {
		this.matProp = matProp;
	}

	public String getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(String serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Integer getBizDataAccountAmount() {
		return bizDataAccountAmount;
	}

	public void setBizDataAccountAmount(Integer bizDataAccountAmount) {
		this.bizDataAccountAmount = bizDataAccountAmount;
	}

	public Integer getBizDataOrderAmount() {
		return bizDataOrderAmount;
	}

	public void setBizDataOrderAmount(Integer bizDataOrderAmount) {
		this.bizDataOrderAmount = bizDataOrderAmount;
	}

	public String getCompanyOrgRegCode() {
		return companyOrgRegCode;
	}

	public void setCompanyOrgRegCode(String companyOrgRegCode) {
		this.companyOrgRegCode = companyOrgRegCode;
	}

	public String getCompanyOrgContact() {
		return companyOrgContact;
	}

	public void setCompanyOrgContact(String companyOrgContact) {
		this.companyOrgContact = companyOrgContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWxQyCode() {
		return wxQyCode;
	}

	public void setWxQyCode(String wxQyCode) {
		this.wxQyCode = wxQyCode;
	}

	public String getIsOpenWxQy() {
		return isOpenWxQy;
	}

	public void setIsOpenWxQy(String isOpenWxQy) {
		this.isOpenWxQy = isOpenWxQy;
	}

	public String getIsAuthWxQy() {
		return isAuthWxQy;
	}

	public void setIsAuthWxQy(String isAuthWxQy) {
		this.isAuthWxQy = isAuthWxQy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
    
}
