/**
 * 
 */
package com.vix.WebService.vo;

import java.util.Date;
import java.util.List;

/**
 * 
 * com.vix.WebService.vo.CustomerAccountVo
 * 
 * @author zhanghaibing
 *
 * @date 2014-6-30
 */
public class CustomerAccountVo {

	/**
	 * 
	 */
	/** 自动编号 */
	private Long id;
	/** 数据唯一编码 */
	private String uuid;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 业务主键标识 */
	private String primaryKey;
	/** 对象语言标识 */
	private String language;
	/** 组织(公司内部标识) */
	private String companyInnerCode;
	/** 公司标识 */
	private String companyCode;
	/** 帐套 */
	private String accountSet;
	/** 创建时间 */
	private Date createTime;
	/** 对象更新时间 */
	private Date updateTime;
	/** 企业编码 废弃 */
	private String enterpriseCode;
	/** 部门编码 */
	private String departmentCode;
	/** 部门名称 */
	private String department;
	/** 创建人 */
	private String creator;
	/** 状态 */
	private String status;
	/** 密级 */
	private String secretLevel;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 承租户标识 */
	private String tenantId;
	/** 语言编码 */
	private String langCode;
	/** 国家编码 */
	private String countryCode;
	/** 当事人 */
	private String interestedPartyPerson;
	/** 当事人角色编码 */
	private String interestedPartyRoleCode;
	/** 当事人角色 */
	private String interestedPartyRole;
	/** 版本 */
	private String version;
	/** 备注 */
	private String memo;
	/** 描述 */
	private String description;
	/**
	 * 是否为正式数据
	 */
	private String isTemp;
	private String searchText1;
	private String searchText2;
	/** 客户英文名称 */
	private String englishName;
	/** 简称 */
	private String shorterName;
	/** 检索词 */
	private String indexWord;
	/** 商标 */
	private String trademark;
	/** 客户类型： enterPrise 企业 ，personal 个人 */
	private String type;
	/** 热点客户 */
	private String isHotCustomer;
	/** 价值评估 */
	private String valueAssessment;
	/** 信用等级 */
	private String creditLevel;
	/** 输入日期 */
	private Date dateEntered;
	/** 修改日期 */
	private Date dateModified;
	/** 修改用户编号 */
	private String modifiedUserId;
	/** 创建人 */
	private String createdBy;
	/** 是否删除标识 */
	private String isDeleted;
	/** 分配用户编号 */
	private String assignedUserId;
	/** 账户类型 */
	private String accountType;
	/** 年收入 */
	private Double annualRevenue;
	/** 电话传真 */
	private String phoneFax;
	/** 评级 */
	private String rating;
	/** 积分值 */
	private Long pointValue;
	/** 办公室电话 */
	private String phoneOffice;
	/** 电话交替 */
	private String phoneAlternate;
	/** 网站 */
	private String website;
	/** 所有权 */
	private String ownership;
	/** 员工 */
	private String employees;
	/** 符号 */
	private String rickerSymbol;
	/** 邮编 */
	private String shippingAddressPostalcode;
	/** 国家 */
	private String shippingAddressCountry;
	/** 主要编号 */
	private String parentId;
	/** 编码 */
	private String sicCode;
	/** 客户种类 */
	private String style;
	/** QQ帐号 */
	private String qqAccount;
	/** MSN帐号 */
	private String msnAccount;
	/** 淘宝旺旺帐号 */
	private String wangAccount;
	/** Skype帐号 */
	private String skypeAccount;
	/** 公司简介 */
	private String companyBrief;
	/** 未联系天数 */
	private Integer uncontactDays;
	/** 渠道编码 */
	private String channelCode;
	/** 销售组织 */
	private String saleOrgCode;
	/** 产品组 */
	private String productGroup;
	/** 竞选编号 */
	private String campaignId;
	/** 信用控制方法 */
	private String creditControlMethod;
	/** 税后 */
	private Double afterTax;
	/** 币种 */
	private String currencu;
	/** 客户是否同时为供应商 */
	private String isAlsoSupplier;
	/** 行业 */
	private String customerIndustry;
	/** 运输区域 */
	private String transArea;
	/** 销售地区 */
	private String saleArea;
	/** 等级标识 */
	private Long levelId;
	/** 积分历史 */
	private Long pointHistory;
	/** 冻结积分 */
	private Long pointFreeze;
	/** 可用积分 */
	private Long point;
	// 新加
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 证件类型
	 */
	private String identityType;
	/**
	 * 证件号码
	 */
	private String identityId;
	/**
	 * 移动电话
	 */
	private String mobilePhone;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 会员渠道 10 公关, 20 商业宣传, 30 互联网 ,40 会员推荐, 99其他,31微信 ,90总部
	 */
	private String vipChannel;
	/**
	 * 电子邮件
	 */
	private String email;

	private List<MemberShipCardVo> memberShipCardVoList;

	// 新加
	public CustomerAccountVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getAccountSet() {
		return accountSet;
	}

	public void setAccountSet(String accountSet) {
		this.accountSet = accountSet;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getInterestedPartyPerson() {
		return interestedPartyPerson;
	}

	public void setInterestedPartyPerson(String interestedPartyPerson) {
		this.interestedPartyPerson = interestedPartyPerson;
	}

	public String getInterestedPartyRoleCode() {
		return interestedPartyRoleCode;
	}

	public void setInterestedPartyRoleCode(String interestedPartyRoleCode) {
		this.interestedPartyRoleCode = interestedPartyRoleCode;
	}

	public String getInterestedPartyRole() {
		return interestedPartyRole;
	}

	public void setInterestedPartyRole(String interestedPartyRole) {
		this.interestedPartyRole = interestedPartyRole;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getSearchText1() {
		return searchText1;
	}

	public void setSearchText1(String searchText1) {
		this.searchText1 = searchText1;
	}

	public String getSearchText2() {
		return searchText2;
	}

	public void setSearchText2(String searchText2) {
		this.searchText2 = searchText2;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getShorterName() {
		return shorterName;
	}

	public void setShorterName(String shorterName) {
		this.shorterName = shorterName;
	}

	public String getIndexWord() {
		return indexWord;
	}

	public void setIndexWord(String indexWord) {
		this.indexWord = indexWord;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsHotCustomer() {
		return isHotCustomer;
	}

	public void setIsHotCustomer(String isHotCustomer) {
		this.isHotCustomer = isHotCustomer;
	}

	public String getValueAssessment() {
		return valueAssessment;
	}

	public void setValueAssessment(String valueAssessment) {
		this.valueAssessment = valueAssessment;
	}

	public String getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getModifiedUserId() {
		return modifiedUserId;
	}

	public void setModifiedUserId(String modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getAnnualRevenue() {
		return annualRevenue;
	}

	public void setAnnualRevenue(Double annualRevenue) {
		this.annualRevenue = annualRevenue;
	}

	public String getPhoneFax() {
		return phoneFax;
	}

	public void setPhoneFax(String phoneFax) {
		this.phoneFax = phoneFax;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Long getPointValue() {
		return pointValue;
	}

	public void setPointValue(Long pointValue) {
		this.pointValue = pointValue;
	}

	public String getPhoneOffice() {
		return phoneOffice;
	}

	public void setPhoneOffice(String phoneOffice) {
		this.phoneOffice = phoneOffice;
	}

	public String getPhoneAlternate() {
		return phoneAlternate;
	}

	public void setPhoneAlternate(String phoneAlternate) {
		this.phoneAlternate = phoneAlternate;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

	public String getRickerSymbol() {
		return rickerSymbol;
	}

	public void setRickerSymbol(String rickerSymbol) {
		this.rickerSymbol = rickerSymbol;
	}

	public String getShippingAddressPostalcode() {
		return shippingAddressPostalcode;
	}

	public void setShippingAddressPostalcode(String shippingAddressPostalcode) {
		this.shippingAddressPostalcode = shippingAddressPostalcode;
	}

	public String getShippingAddressCountry() {
		return shippingAddressCountry;
	}

	public void setShippingAddressCountry(String shippingAddressCountry) {
		this.shippingAddressCountry = shippingAddressCountry;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSicCode() {
		return sicCode;
	}

	public void setSicCode(String sicCode) {
		this.sicCode = sicCode;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getQqAccount() {
		return qqAccount;
	}

	public void setQqAccount(String qqAccount) {
		this.qqAccount = qqAccount;
	}

	public String getMsnAccount() {
		return msnAccount;
	}

	public void setMsnAccount(String msnAccount) {
		this.msnAccount = msnAccount;
	}

	public String getWangAccount() {
		return wangAccount;
	}

	public void setWangAccount(String wangAccount) {
		this.wangAccount = wangAccount;
	}

	public String getSkypeAccount() {
		return skypeAccount;
	}

	public void setSkypeAccount(String skypeAccount) {
		this.skypeAccount = skypeAccount;
	}

	public String getCompanyBrief() {
		return companyBrief;
	}

	public void setCompanyBrief(String companyBrief) {
		this.companyBrief = companyBrief;
	}

	public Integer getUncontactDays() {
		return uncontactDays;
	}

	public void setUncontactDays(Integer uncontactDays) {
		this.uncontactDays = uncontactDays;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getSaleOrgCode() {
		return saleOrgCode;
	}

	public void setSaleOrgCode(String saleOrgCode) {
		this.saleOrgCode = saleOrgCode;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getCreditControlMethod() {
		return creditControlMethod;
	}

	public void setCreditControlMethod(String creditControlMethod) {
		this.creditControlMethod = creditControlMethod;
	}

	public Double getAfterTax() {
		return afterTax;
	}

	public void setAfterTax(Double afterTax) {
		this.afterTax = afterTax;
	}

	public String getCurrencu() {
		return currencu;
	}

	public void setCurrencu(String currencu) {
		this.currencu = currencu;
	}

	public String getIsAlsoSupplier() {
		return isAlsoSupplier;
	}

	public void setIsAlsoSupplier(String isAlsoSupplier) {
		this.isAlsoSupplier = isAlsoSupplier;
	}

	public String getCustomerIndustry() {
		return customerIndustry;
	}

	public void setCustomerIndustry(String customerIndustry) {
		this.customerIndustry = customerIndustry;
	}

	public String getTransArea() {
		return transArea;
	}

	public void setTransArea(String transArea) {
		this.transArea = transArea;
	}

	public String getSaleArea() {
		return saleArea;
	}

	public void setSaleArea(String saleArea) {
		this.saleArea = saleArea;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Long getPointHistory() {
		return pointHistory;
	}

	public void setPointHistory(Long pointHistory) {
		this.pointHistory = pointHistory;
	}

	public Long getPointFreeze() {
		return pointFreeze;
	}

	public void setPointFreeze(Long pointFreeze) {
		this.pointFreeze = pointFreeze;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<MemberShipCardVo> getMemberShipCardVoList() {
		return memberShipCardVoList;
	}

	public void setMemberShipCardVoList(List<MemberShipCardVo> memberShipCardVoList) {
		this.memberShipCardVoList = memberShipCardVoList;
	}

	public String getVipChannel() {
		return vipChannel;
	}

	public void setVipChannel(String vipChannel) {
		this.vipChannel = vipChannel;
	}

}
