package com.vix.mdm.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.base.entity.ContactPersonType;
import com.vix.crm.base.entity.CredentialType;
import com.vix.crm.base.entity.CrmContactType;

/** 联系人 */
public class ContactPerson extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 对应客户 */
	private CustomerAccount customerAccount;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 姓*/
	private String lastName;
	/** 名 */
	private String firstName;
	/** 负责业务 */
	private String presideBusiness;
	/** 英文名 */
	private String engFirstName;
	/** 英文姓 */
	private String engLastName;
	/** 性别 */
	private String sex;
	/** 公司 */
	private String company;
	/** 称谓 */
	private String callTitle;
	/** 头衔 */
	private String title;
	/** 职务 */
	private String position;
	/** 等级 */
	private String level;
	/** 影响力 */
	private String influence;
	/** 负责业务 */
	private String chargeBusiness;
	/** 邮件地址 */
	private String email;
	/** 生日 */
	private Date birthday;
	/** 爱好 */
	private String hobby;
	/** 证件号码 */
	private String credentialCode;
	/** 证件类型 */
	private CredentialType credentialType;
	/** 联系人类型（分类） */
	private ContactPersonType contactPersonType;
	/** 积分值 */
	private Long pointValue;
	/** 输入日期 */
	private Date dateEntered;
	/** 修改日期 */
	private Date dateModified;
	/** 修改用户编号 */
	private String modifiedUserId;
	/** 创建人 */
	private String createdBy;
	/** 描述 */
	private String memo;
	/** 删除 */
	private String isDeleted;
	/** 分配用户编号 */
	private String assignedUserId;
	/** 帐户类型 */
	private String accountType;
	/** 行业 */
	private String industry;
	/** 年收入 */
	private Double annualRevenue;
	/** 直线电话 */
	private String directPhone;
	/** 传真 */
	private String fax;
	/** 分开户银行 */
	private String bank;
	/** 银行帐号 */
	private String bankAccount;
	/** 帐单街道地址 */
	private String billingAddressStreet;
	/** 帐单地址状态 */
	private String billingAddressState;
	/** 帐单邮政编码 */
	private String billingAddressPostalcode;
	/** 国家 */
	private String billingAddressCountry;
	/** 评级 */
	private String rating;
	/** 移动电话 */
	private String mobile;
	private String phone;
	/** 办公电话 */
	private String phoneOffice;
	/** 家庭电话 */
	private String phoneHome;
	/** 电话交替 */
	private String phoneAlternate;
	/** 办公电子邮件 */
	private String officeEmail;
	/** 私人电子邮件 */
	private String privateEmail;
	/** 分开户银行 */
	private String website;
	/** 所有权 */
	private String ownership;
	/** 员工 */
	private String employees;
	/** 送货街道地址 */
	private String shippingAddressStreet;
	/** 送货城市地址 */
	private String shippingAddressCity;
	/** 送货地址状态 */
	private String shippingAddressState;
	/** 送货地址邮编  */
	private String shippingAddressPostalcode;
	/** 送货国家地址 */
	private String shippingAddressCountry;
	/** 主要编号 */
	private String parentId;
	/** 编码 */
	private String sicCode;
	/** 竞选编号 */
	private String campaignId;
	/** QQ帐号 */
	private String qqAccount;
	/** MSN帐号 */
	private String msnAccount;
	/** 淘宝旺旺帐号 */
	private String wangAccount;
	/** Skype帐号 */
	private String skypeAccount;
	/** 未联系天数 */
	private String uncontactDays;
	/** 是否同步到Outlook */
	private String isSyncOutlook;
	/** 是否电话联系 */
	private String isPhoneCall;
	/** 是否主要联系人   1：是   ,  0：否*/
	private String primaryContact;
	/** 是否主要联系人  (更换为字典)*/
	private CrmContactType crmContactType;
	
	/** 会员属性 */
	/** 标识 */
	private String tag;
	/** 姓名全拼 */
	private String nameAllSpelling;
	/** 外部编号 */
	private String outNumber;
	/** 是否已婚	 */
	private String isMarriage;
	/** 是否允许联系 */
	private String isAllowConnect;
	/** 积分密码 */
	private String integratePassword;
	/** 教育程度 */
	private String education;
	/** 收入水平 */
	private String incomeLevel;
	/** 客户身份 */
	private String identity;
	/** 是否黑名单客户 1:是 0：否 */
	private String isBlack;
	/** 加入黑名单的原因 */
	private String blackReason;
	/** 移除黑名单的原因 */
	private String removeBlackReason;
	private String address;
	
	public ContactPerson(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	
	public String getCustomerAccountName() {
		if(customerAccount != null){
			return customerAccount.getName();
		}
		return "";
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	
	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPresideBusiness() {
		return presideBusiness;
	}

	public void setPresideBusiness(String presideBusiness) {
		this.presideBusiness = presideBusiness;
	}

	public String getEngFirstName() {
		return engFirstName;
	}

	public void setEngFirstName(String engFirstName) {
		this.engFirstName = engFirstName;
	}

	public String getEngLastName() {
		return engLastName;
	}

	public void setEngLastName(String engLastName) {
		this.engLastName = engLastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCallTitle() {
		return callTitle;
	}

	public void setCallTitle(String callTitle) {
		this.callTitle = callTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getInfluence() {
		return influence;
	}

	public void setInfluence(String influence) {
		this.influence = influence;
	}

	public String getChargeBusiness() {
		return chargeBusiness;
	}

	public void setChargeBusiness(String chargeBusiness) {
		this.chargeBusiness = chargeBusiness;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	public String getBirthdayStr(){
		if (null != birthday) {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			return myFormatter.format(birthday); // 注意,这句整体作为String
		} else {
			return "";
		}
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getCredentialCode() {
		return credentialCode;
	}

	public void setCredentialCode(String credentialCode) {
		this.credentialCode = credentialCode;
	}

	public CredentialType getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(CredentialType credentialType) {
		this.credentialType = credentialType;
	}

	public ContactPersonType getContactPersonType() {
		return contactPersonType;
	}

	public void setContactPersonType(ContactPersonType contactPersonType) {
		this.contactPersonType = contactPersonType;
	}

	public Long getPointValue() {
		return pointValue;
	}

	public void setPointValue(Long pointValue) {
		this.pointValue = pointValue;
	}

	public Date getDateEntered() {
		return dateEntered;
	}
	
	public String getDateEnteredStr() {
		if(null != dateEntered){
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			return myFormatter.format(dateEntered);
		}
		return "";
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

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String getIsDeleted() {
		return isDeleted;
	}

	@Override
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

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Double getAnnualRevenue() {
		return annualRevenue;
	}

	public void setAnnualRevenue(Double annualRevenue) {
		this.annualRevenue = annualRevenue;
	}

	public String getDirectPhone() {
		return directPhone;
	}

	public void setDirectPhone(String directPhone) {
		this.directPhone = directPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBillingAddressStreet() {
		return billingAddressStreet;
	}

	public void setBillingAddressStreet(String billingAddressStreet) {
		this.billingAddressStreet = billingAddressStreet;
	}

	public String getBillingAddressState() {
		return billingAddressState;
	}

	public void setBillingAddressState(String billingAddressState) {
		this.billingAddressState = billingAddressState;
	}

	public String getBillingAddressPostalcode() {
		return billingAddressPostalcode;
	}

	public void setBillingAddressPostalcode(String billingAddressPostalcode) {
		this.billingAddressPostalcode = billingAddressPostalcode;
	}

	public String getBillingAddressCountry() {
		return billingAddressCountry;
	}

	public void setBillingAddressCountry(String billingAddressCountry) {
		this.billingAddressCountry = billingAddressCountry;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhoneOffice() {
		return phoneOffice;
	}

	public void setPhoneOffice(String phoneOffice) {
		this.phoneOffice = phoneOffice;
	}

	public String getPhoneHome() {
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getPhoneAlternate() {
		return phoneAlternate;
	}

	public void setPhoneAlternate(String phoneAlternate) {
		this.phoneAlternate = phoneAlternate;
	}

	public String getOfficeEmail() {
		return officeEmail;
	}

	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	public String getPrivateEmail() {
		return privateEmail;
	}

	public void setPrivateEmail(String privateEmail) {
		this.privateEmail = privateEmail;
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

	public String getShippingAddressStreet() {
		return shippingAddressStreet;
	}

	public void setShippingAddressStreet(String shippingAddressStreet) {
		this.shippingAddressStreet = shippingAddressStreet;
	}

	public String getShippingAddressCity() {
		return shippingAddressCity;
	}

	public void setShippingAddressCity(String shippingAddressCity) {
		this.shippingAddressCity = shippingAddressCity;
	}

	public String getShippingAddressState() {
		return shippingAddressState;
	}

	public void setShippingAddressState(String shippingAddressState) {
		this.shippingAddressState = shippingAddressState;
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

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
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

	public String getUncontactDays() {
		return uncontactDays;
	}

	public void setUncontactDays(String uncontactDays) {
		this.uncontactDays = uncontactDays;
	}

	public String getIsSyncOutlook() {
		return isSyncOutlook;
	}

	public void setIsSyncOutlook(String isSyncOutlook) {
		this.isSyncOutlook = isSyncOutlook;
	}

	public String getIsPhoneCall() {
		return isPhoneCall;
	}

	public void setIsPhoneCall(String isPhoneCall) {
		this.isPhoneCall = isPhoneCall;
	}
	
	public String getPersonName(){
		return lastName + firstName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getNameAllSpelling() {
		return nameAllSpelling;
	}

	public void setNameAllSpelling(String nameAllSpelling) {
		this.nameAllSpelling = nameAllSpelling;
	}

	public String getOutNumber() {
		return outNumber;
	}

	public void setOutNumber(String outNumber) {
		this.outNumber = outNumber;
	}

	public String getIsMarriage() {
		return isMarriage;
	}

	public void setIsMarriage(String isMarriage) {
		this.isMarriage = isMarriage;
	}

	public String getIsAllowConnect() {
		return isAllowConnect;
	}

	public void setIsAllowConnect(String isAllowConnect) {
		this.isAllowConnect = isAllowConnect;
	}

	public String getIntegratePassword() {
		return integratePassword;
	}

	public void setIntegratePassword(String integratePassword) {
		this.integratePassword = integratePassword;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getIncomeLevel() {
		return incomeLevel;
	}

	public void setIncomeLevel(String incomeLevel) {
		this.incomeLevel = incomeLevel;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
	}

	public String getBlackReason() {
		return blackReason;
	}

	public void setBlackReason(String blackReason) {
		this.blackReason = blackReason;
	}

	public String getRemoveBlackReason() {
		return removeBlackReason;
	}

	public void setRemoveBlackReason(String removeBlackReason) {
		this.removeBlackReason = removeBlackReason;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	public CrmContactType getCrmContactType() {
		return crmContactType;
	}
	
	public String getCrmContactTypeName() {
		if(crmContactType != null){
			return crmContactType.getName();
		}
		return "";
	}

	public void setCrmContactType(CrmContactType crmContactType) {
		this.crmContactType = crmContactType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
