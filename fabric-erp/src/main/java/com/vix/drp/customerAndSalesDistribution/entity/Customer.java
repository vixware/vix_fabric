package com.vix.drp.customerAndSalesDistribution.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.base.entity.CustomerSource;
import com.vix.crm.base.entity.CustomerStage;
import com.vix.crm.base.entity.CustomerType;
import com.vix.crm.base.entity.HotLevel;
import com.vix.crm.base.entity.Industry;
import com.vix.crm.base.entity.RelationshipClass;
import com.vix.crm.base.entity.StaffScale;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 客户
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class Customer extends BaseEntity {

	private static final long serialVersionUID = 1L;

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
	private String accountType;
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
	/** 客户分组 */
	private String customerGroup;
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
	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;

	public Customer() {
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
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

	public String getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
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

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
