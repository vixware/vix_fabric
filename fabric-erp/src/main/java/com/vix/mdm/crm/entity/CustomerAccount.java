package com.vix.mdm.crm.entity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.core.utils.DateUtil;
import com.vix.crm.base.entity.AccountType;
import com.vix.crm.base.entity.CustomerSource;
import com.vix.crm.base.entity.CustomerStage;
import com.vix.crm.base.entity.CustomerType;
import com.vix.crm.base.entity.HotLevel;
import com.vix.crm.base.entity.Industry;
import com.vix.crm.base.entity.RelationshipClass;
import com.vix.crm.base.entity.StaffScale;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.customer.entity.CustomerAccountCategory;
import com.vix.crm.lead.entity.SaleLead;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.crm.member.entity.MemberTag;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.crm.service.entity.CustomerComplaint;
import com.vix.crm.service.entity.CustomerServices;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.entity.AddressInfo;
import com.vix.sales.credit.entity.CustomerCreditInfo;

/** 客户信息 */
public class CustomerAccount extends BaseBOEntity {
	private DecimalFormat    df   = new DecimalFormat("######0.00");
	private static final long serialVersionUID = 1L;
	/**
	 * status 2,已办卡 已缴费 1,已办卡 未缴费 0,未办卡
	 */
	/** 客户英文名称 */
	private String englishName;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 简称 */
	private String shorterName;
	/** 检索词 */
	private String indexWord;
	/** 商标 */
	private String trademark;
	/** 客户类型： enterPrise 企业 ，personal 个人 ,internal 内部,member 会员 */
	private String type;
	/** 1:是 , 0:否 */
	private String isHighSea;
	/** 内部客户id，内部客户类型，内部客户名称，该数据为公司，部门，或门店 */
	private String internalUnitId;
	private String internalUnitType;
	private String internalUnitName;
	/** 热点客户 */
	private String isHotCustomer;
	/** 热点程度 */
	private HotLevel hotLevel;
	/** 价值评估 */
	private String valueAssessment;
	/** 信用等级 */
	private String creditLevel;
	/** 客户种类 */
	private CustomerType customerType;
	/** 关系等级 */
	private RelationshipClass relationshipClass;
	/** 人员规模 */
	private StaffScale staffScale;
	/** 来源 */
	private CustomerSource customerSource;
	/** 阶段 */
	private CustomerStage customerStage;
	/** 输入日期 */
	private Date dateEntered;
	/** 修改日期 */
	private Date dateModified;
	/** 修改用户编号 */
	private String modifiedUserId;
	/** 创建人 */
	private String createdBy;
	/** 属于(业务员代码) */
	private String belongerCode;
	/** 属于(业务员) */
	private String belonger;
	/** 是否删除标识 */
	private String isDeleted;
	/** 分配用户编号 */
	private String assignedUserId;
	/** 账户类型 */
	private AccountType accountType;
	/** 行业 */
	private Industry industry;
	/** 年收入 */
	private Double annualRevenue;
	/** 电话传真 */
	private String phoneFax;
	/** 帐单街道地址 */
	private String billingAddressStreet;
	/** 帐单城市地址 */
	private String billingAddressCity;
	/** 帐单地址状态 */
	private String billingAddressState;
	/** 账单邮编 */
	private String billingAddressPostalcode;
	/** 帐单国家地址 */
	private String billingAddressCountry;
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
	/** 交货街道地址 */
	private String shippingAddressStreet;
	/** 交货城市地址 */
	private String shippingAddressCity;
	/** 交货地址状态 */
	private String shippingAddressState;
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
	/** 价格组 */
	private String priceGroup;
	/** 发运优先级 */
	private String deliveryPriority;
	/** 交货工厂代码 */
	private String deliveryFactoryCode;
	/** 交货工厂 */
	private String factoryCode;
	/** 是否允许订单部分交货 */
	private String isAllowPartDelivery;
	/** 最大部分交货次数 */
	private Integer maxDeliveryTime;
	/** 卸货点 */
	private String recievePoint;
	/** 等级标识 */
	private String levelId;
	/** 积分历史 */
	private Long pointHistory;
	/** 冻结积分 */
	private Long pointFreeze;
	/** 可用积分 */
	private Double point;
	/** 性别   男 1      ；女 0  */
	private String sex;
	/** 证件类型 */
	private String identityType;
	/** 证件号码 */
	private String identityId;
	/** 移动电话 */
	private String mobilePhone;
	/** 固定电话 */
	private String telephone;
	/** 地址 */
	private String address;
	/** 电子邮件 */
	private String email;
	/**
	 * 是否已下载
	 */
	private String isDownLoad;
	/**
	 * 会员渠道 10 公关, 20 商业宣传, 30 互联网 ,40 会员推荐, 99其他,31微信 ,90总部
	 */
	private String vipChannel;

	// 新加
	/** 信用状况 */
	private Set<CustomerCreditInfo> customerCreditInfos = new HashSet<CustomerCreditInfo>();
	/** 客户分类 */
	private CustomerAccountCategory customerAccountCategory;
	/** 客户组 */
	private CustomerAccountGroup customerAccountGroup;
	/** 联系人 */
	private Set<ContactPerson> contactPersons = new HashSet<ContactPerson>();
	/** 销售机会 */
	private Set<SaleChance> saleChances = new HashSet<SaleChance>();
	/** 销售线索 */
	private Set<SaleLead> saleLeads = new HashSet<SaleLead>();
	/** 客户投诉 */
	private Set<CustomerComplaint> customerComplaints = new HashSet<CustomerComplaint>();
	/** 客户服务 */
	private Set<CustomerServices> customerServices = new HashSet<CustomerServices>();
	/**
	 * 会员标签
	 */
	private Set<MemberTag> memberTags = new HashSet<MemberTag>();
	/**
	 * 优惠券
	 */
	private Set<CouponDetail> subCouponDetails = new HashSet<CouponDetail>();

	private String memberTag;
	private MemberTag mbtags;
	/**
	 * 是否发放优惠券 0 未发放 1 已发放
	 */
	private String isCouponing;
	/**
	 * 会员等级
	 */
	private MemberLevel memberLevel;
	/**
	 * 购买次数
	 */
	private Long buyNum;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 来源
	 */
	private String memberSource;
	/**
	 * 有效期至
	 */
	private Date expiryDate;
	/**
	 * 是否启用: Y,启用  N,禁用
	 */
	private String isUse;
	/**
	 * 账户余额
	 */
	public Double money;
	/**
	 * 会员卡号
	 */
	private String clipNumber;
	/**
	 * 会员头像
	 */
	private String headImage;
	/**
	 * 推荐人
	 */
	private String referrerPhone;
	/**
	 * 工作单位地址
	 */
	private String workAddress;
	/** openId */
	private String openId;
	/**
	 * 储值总额
	 */
	private Double amountMoney;
	/**
	 * 积分总额
	 */
	private Double amountPoint;
	/**
	 * 会员卡类型
	 */
	private String customerClipType;
	/**
	 * 是否挂失
	 */
	private String isReport;
	/** 所有者 */
	private Employee employee;
	/** 是否领取  */
	private String isReceive;
	/** 省  */
	private AddressInfo province;
	/** 市  */
	private AddressInfo city;
	/** 区  */
	private AddressInfo district;
	/** 停滞天数 */
	private Integer stagnateDay;
	/** 联系人ID */
	private String contactPersonIds;
	/** 联系人Name */
	private String contactPersonNames;
	/** 公司LOGO */
	private String logo;
	
	public CustomerAccount() {
	}
	
	public CustomerAccount(String id) {
		super();
		setId(id);
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public String getBirthdayStr(){
		if(null != birthday){
			return DateUtil.format(birthday);
		}
		return "";
	}
	
	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
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

	public Long getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Long buyNum) {
		this.buyNum = buyNum;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsHighSea() {
		return isHighSea;
	}

	public void setIsHighSea(String isHighSea) {
		this.isHighSea = isHighSea;
	}

	public String getInternalUnitId() {
		return internalUnitId;
	}

	public void setInternalUnitId(String internalUnitId) {
		this.internalUnitId = internalUnitId;
	}

	public String getInternalUnitType() {
		return internalUnitType;
	}

	public void setInternalUnitType(String internalUnitType) {
		this.internalUnitType = internalUnitType;
	}

	public String getInternalUnitName() {
		return internalUnitName;
	}

	public void setInternalUnitName(String internalUnitName) {
		this.internalUnitName = internalUnitName;
	}

	public String getIsHotCustomer() {
		return isHotCustomer;
	}

	public void setIsHotCustomer(String isHotCustomer) {
		this.isHotCustomer = isHotCustomer;
	}

	public HotLevel getHotLevel() {
		return hotLevel;
	}

	public void setHotLevel(HotLevel hotLevel) {
		this.hotLevel = hotLevel;
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

	public RelationshipClass getRelationshipClass() {
		return relationshipClass;
	}

	public void setRelationshipClass(RelationshipClass relationshipClass) {
		this.relationshipClass = relationshipClass;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public StaffScale getStaffScale() {
		return staffScale;
	}

	public void setStaffScale(StaffScale staffScale) {
		this.staffScale = staffScale;
	}

	public CustomerSource getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(CustomerSource customerSource) {
		this.customerSource = customerSource;
	}

	public CustomerStage getCustomerStage() {
		return customerStage;
	}

	public void setCustomerStage(CustomerStage customerStage) {
		this.customerStage = customerStage;
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

	public String getBelongerCode() {
		return belongerCode;
	}

	public void setBelongerCode(String belongerCode) {
		this.belongerCode = belongerCode;
	}

	public String getBelonger() {
		return belonger;
	}

	public void setBelonger(String belonger) {
		this.belonger = belonger;
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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Double getAnnualRevenue() {
		return annualRevenue;
	}

	public String getAnnualRevenueStr() {
		if (null != annualRevenue) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(annualRevenue); // 注意,这句整体作为String
		} else {
			return "";
		}
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

	public String getBillingAddressStreet() {
		return billingAddressStreet;
	}

	public void setBillingAddressStreet(String billingAddressStreet) {
		this.billingAddressStreet = billingAddressStreet;
	}

	public String getBillingAddressCity() {
		return billingAddressCity;
	}

	public void setBillingAddressCity(String billingAddressCity) {
		this.billingAddressCity = billingAddressCity;
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

	public String getPriceGroup() {
		return priceGroup;
	}

	public void setPriceGroup(String priceGroup) {
		this.priceGroup = priceGroup;
	}

	public String getDeliveryPriority() {
		return deliveryPriority;
	}

	public void setDeliveryPriority(String deliveryPriority) {
		this.deliveryPriority = deliveryPriority;
	}

	public String getDeliveryFactoryCode() {
		return deliveryFactoryCode;
	}

	public void setDeliveryFactoryCode(String deliveryFactoryCode) {
		this.deliveryFactoryCode = deliveryFactoryCode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getIsAllowPartDelivery() {
		return isAllowPartDelivery;
	}

	public void setIsAllowPartDelivery(String isAllowPartDelivery) {
		this.isAllowPartDelivery = isAllowPartDelivery;
	}

	public Integer getMaxDeliveryTime() {
		return maxDeliveryTime;
	}

	public void setMaxDeliveryTime(Integer maxDeliveryTime) {
		this.maxDeliveryTime = maxDeliveryTime;
	}

	public String getRecievePoint() {
		return recievePoint;
	}

	public void setRecievePoint(String recievePoint) {
		this.recievePoint = recievePoint;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
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

	public Double getPoint() {
		if(point != null){
			return Math.floor(point);
		}
		return 0d;
	}
	
	public void setPoint(Double point) {
		this.point = point;
	}

	public Set<CustomerCreditInfo> getCustomerCreditInfos() {
		return customerCreditInfos;
	}

	public void setCustomerCreditInfos(
			Set<CustomerCreditInfo> customerCreditInfos) {
		this.customerCreditInfos = customerCreditInfos;
	}

	public CustomerAccountCategory getCustomerAccountCategory() {
		return customerAccountCategory;
	}

	public void setCustomerAccountCategory(
			CustomerAccountCategory customerAccountCategory) {
		this.customerAccountCategory = customerAccountCategory;
	}

	public CustomerAccountGroup getCustomerAccountGroup() {
		return customerAccountGroup;
	}

	public void setCustomerAccountGroup(
			CustomerAccountGroup customerAccountGroup) {
		this.customerAccountGroup = customerAccountGroup;
	}

	public Set<ContactPerson> getContactPersons() {
		return contactPersons;
	}

	public void setContactPersons(Set<ContactPerson> contactPersons) {
		this.contactPersons = contactPersons;
	}

	public Set<SaleChance> getSaleChances() {
		return saleChances;
	}

	public void setSaleChances(Set<SaleChance> saleChances) {
		this.saleChances = saleChances;
	}

	public Set<SaleLead> getSaleLeads() {
		return saleLeads;
	}

	public void setSaleLeads(Set<SaleLead> saleLeads) {
		this.saleLeads = saleLeads;
	}

	public Set<CustomerComplaint> getCustomerComplaints() {
		return customerComplaints;
	}

	public void setCustomerComplaints(Set<CustomerComplaint> customerComplaints) {
		this.customerComplaints = customerComplaints;
	}

	public Set<CustomerServices> getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(Set<CustomerServices> customerServices) {
		this.customerServices = customerServices;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsDownLoad() {
		return isDownLoad;
	}

	public void setIsDownLoad(String isDownLoad) {
		this.isDownLoad = isDownLoad;
	}

	public String getVipChannel() {
		return vipChannel;
	}

	public void setVipChannel(String vipChannel) {
		this.vipChannel = vipChannel;
	}

	public MemberLevel getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Set<MemberTag> getMemberTags() {
		return memberTags;
	}

	public void setMemberTags(Set<MemberTag> memberTags) {
		this.memberTags = memberTags;
	}

	public String getMemberTag() {
		return memberTag;
	}

	public void setMemberTag(String memberTag) {
		this.memberTag = memberTag;
	}

	public MemberTag getMbtags() {
		return mbtags;
	}

	public void setMbtags(MemberTag mbtags) {
		this.mbtags = mbtags;
	}

	public String getIsCouponing() {
		return isCouponing;
	}

	public void setIsCouponing(String isCouponing) {
		this.isCouponing = isCouponing;
	}

	public Set<CouponDetail> getSubCouponDetails() {
		return subCouponDetails;
	}

	public void setSubCouponDetails(Set<CouponDetail> subCouponDetails) {
		this.subCouponDetails = subCouponDetails;
	}

	public String getMemberSource() {
		return memberSource;
	}

	public void setMemberSource(String memberSource) {
		this.memberSource = memberSource;
	}

	public ContactPerson getContactPerson() {
		if (null != contactPersons && contactPersons.size() > 0) {
			return contactPersons.iterator().next();
		}
		return null;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public Double getMoney(){
		return money;
	}
	public String getMoneyStr() {
		if(money != null){
			return df.format(money);
		}
		return "";
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getClipNumber() {
		return clipNumber;
	}

	public void setClipNumber(String clipNumber) {
		this.clipNumber = clipNumber;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getReferrerPhone() {
		return referrerPhone;
	}

	public void setReferrerPhone(String referrerPhone) {
		this.referrerPhone = referrerPhone;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/** 获取创建时间为yyyy-MM-dd HH:mm:ss **/
	public String getCreateTimeFormatA() {
		String dateString = "";
		try{
			Date createTime = this.getCreateTime();
			if(createTime != null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateString = formatter.format(createTime);
			}else{
				dateString = "";
			}
		}catch(Exception e){
			dateString = "";
		}
		return dateString;
	}
	/** 获取创建时间为yyyy-MM-dd **/
	public String getCreateTimeFormatB() {
		String dateString = "";
		try{
			Date createTime = this.getCreateTime();
			if(createTime != null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				dateString = formatter.format(createTime);
			}else{
				dateString = "";
			}
		}catch(Exception e){
			dateString = "";
		}
		return dateString;
	}
	/** 获取性别字符串 性别   男 1      ；女 0 **/
	public String getSexStr() {
		String sexStr = this.getSex();
		if(sexStr == null || "".equals(sexStr)){
			sexStr = new String("");
		}else{
			if("1".equals(sexStr)){
				sexStr = new String("男");
			}else if("0".equals(sexStr)){
				sexStr = new String("女");
			}else{
				sexStr = new String("");
			}
		}
		return sexStr;
	}
	
	/**获取等级标识**/
	public String getMemberLevelName() {
		 MemberLevel obj = this.getMemberLevel();
		String memberLevelName = "";
		 if(obj != null ){
			 memberLevelName = obj.getName();
		}
		return memberLevelName;
	}
	
	/**获取会员折扣**/
	public int getDiscount() {
		MemberLevel obj = this.getMemberLevel();
		int discount = 0;
		if(obj != null ){
			discount = (int)Math.floor(obj.getDiscount());
		}
		return discount;
	}
	public String getChannelDistributorName(){
		if(null != channelDistributor){
			return channelDistributor.getName();
		}
		return "";
	}

	public Double getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(Double amountMoney) {
		this.amountMoney = amountMoney;
	}

	public Double getAmountPoint() {
		return amountPoint;
	}

	public void setAmountPoint(Double amountPoint) {
		this.amountPoint = amountPoint;
	}

	public String getCustomerClipType() {
		return customerClipType;
	}

	public void setCustomerClipType(String customerClipType) {
		this.customerClipType = customerClipType;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}

	public AddressInfo getProvince() {
		return province;
	}

	public void setProvince(AddressInfo province) {
		this.province = province;
	}

	public AddressInfo getCity() {
		return city;
	}

	public void setCity(AddressInfo city) {
		this.city = city;
	}

	public AddressInfo getDistrict() {
		return district;
	}

	public void setDistrict(AddressInfo district) {
		this.district = district;
	}

	public Integer getStagnateDay() {
		if(stagnateDay != null){
			return stagnateDay;
		}else{
			return 0;
		}
	}

	public void setStagnateDay(Integer stagnateDay) {
		this.stagnateDay = stagnateDay;
	}

	public String getContactPersonIds() {
		return contactPersonIds;
	}

	public void setContactPersonIds(String contactPersonIds) {
		this.contactPersonIds = contactPersonIds;
	}

	public String getContactPersonNames() {
		return contactPersonNames;
	}

	public void setContactPersonNames(String contactPersonNames) {
		this.contactPersonNames = contactPersonNames;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	/** 计算停滞天数  */
	/*public Integer getStagnateDay(){
		if(getUpdateTime() != null){
			long from = DateUtil.praseSqlDate(getUpdateTimeStr()).getTime();
			long to = DateUtil.praseSqlDate(DateUtil.format(new Date())).getTime();
			int day = (int)((to-from)/(24*60*60*1000));
			return day;
		}
		return 0;
	}*/
}
