/**
 * 
 */
package com.vix.drp.channel.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.drp.advertisingCampaign.entity.AdvertisingCampaign;
import com.vix.drp.channelPriceSurvey.entity.ChannelPrice;
import com.vix.drp.customerAndSalesDistribution.entity.Customer;
import com.vix.drp.customerAndSalesDistribution.entity.SalesDistribution;
import com.vix.drp.marketingActivity.entity.MarketActivity;
import com.vix.drp.natureAndScaleBranch.entity.OperatingConditions;
import com.vix.drp.natureAndScaleBranch.entity.PropertiesScale;
import com.vix.drp.newProductLaunchInformation.entity.ProductInformation;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.StoreItem;

/**
 * 产品渠道/经销商/代理销售商/门店 类型: 渠道、分销商、代理商、门店、网店
 * com.vix.drp.channel.entity.ChannelDistributor
 * 
 * @author zhanghaibing
 * 
 * @date 2014-7-3
 */
public class ChannelDistributor extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 简称 */
	private String shortName;
	/** 拼音 */
	private String pinYin;
	/** 英文名称 */
	private String englishName;
	/** 上级渠道经销商编码 */
	private String parentChannelDistributorCode;
	/** 上级渠道经销商 */
	private String parentChannelDistributorName;
	/** 所属分类 */
	private String catalog;
	/**
	 * 类型 1、渠道，2、经销商，3、代理销售商，4、门店,5,网店
	 */
	private String type;
	/** 所属地区 */
	private String region;
	/** 员工人数 */
	private Long employeeCounts;
	/** 母公司代码 */
	private String parentCompanyCode;
	/** 母公司 */
	private String parentCompanyName;
	/** 所属行业 */
	private String industry;
	/** 币种 */
	private String currency;
	/** 分类 */
	private String catalogs;
	/** 注册资金 */
	private String registeredCapital;
	/** 开户银行 */
	private String openingBank;
	/** 银行帐号 */
	private String bankAccount;
	/** 税号 */
	private String taxCode;
	/** 法人 */
	private String artificialPerson;
	/** 等级 */
	private String level;
	/** 电话 */
	private String telephone;
	/** 邮箱 */
	private String email;
	/** 营业执照 */
	private String permit;
	/** 地图链接 */
	private String maplink;
	/** 图片 */
	private String picture;
	/** 附件 */
	private String accessory;
	/** 地址 */
	private String address;
	/** 网址 */
	private String url;
	/**
	 * 平台类型
	 */
	private StoreType storeType;
	/** 应用唯一标识 **/
	private String appKey;
	/** 应用密钥 **/
	private String appSecret;
	/** 商家登陆账户 **/
	private String nick;
	/** 商家标识 **/
	private String sellerId;
	/** 授权Session **/
	private String session;
	/** 市场标识 **/
	private String marketPlaceId;
	private Long channelTypeId;
	private String channelCode;
	private String channelName;
	private String channelProps;
	private String channelDesc;
	private Integer state;
	private Integer isDelete;
	private Integer channelClass;
	private Integer isDefault;
	private Integer sortOrder;
	/**
	 * 京东 网店 店铺模式(sop lbp )
	 */
	private String mallType;
	// 负责人
	private Employee employee;
	// 业务员
	private Employee salesEmployee;
	/** 父分销商 */
	private ChannelDistributor parentChannelDistributor;
	/** 子分销商集合 */
	private Set<ChannelDistributor> subChannelDistributors = new HashSet<ChannelDistributor>();
	/** 职员 */
	private Set<Employee> employees = new HashSet<Employee>();
	/** 所属公司 （可能） */
	private Organization organization;
	/** 所属部门 （可能） */
	private OrganizationUnit organizationUnit;
	/**
	 * 下级部门
	 */
	private Set<OrganizationUnit> organizationUnits = new HashSet<OrganizationUnit>();
	/* 物料 */
	private Set<Item> mdmItem = new HashSet<Item>();
	private Set<StoreItem> subStoreItems = new HashSet<StoreItem>();
	/* 渠道价格 */
	private Set<ChannelPrice> channelPrices = new HashSet<ChannelPrice>();
	/**
	 * 经营状况
	 */
	private Set<OperatingConditions> operatingConditionss = new HashSet<OperatingConditions>();
	/**
	 * 性质规模
	 */
	private Set<PropertiesScale> propertiesScales = new HashSet<PropertiesScale>();
	/**
	 * 产品投放信息
	 */
	private Set<ProductInformation> productInformations = new HashSet<ProductInformation>();
	/**
	 * 市场活动信息
	 */
	private Set<MarketActivity> marketActivitys = new HashSet<MarketActivity>();
	/**
	 * 广告活动信息
	 */
	private Set<AdvertisingCampaign> advertisingCampaigns = new HashSet<AdvertisingCampaign>();
	/**
	 * 客户
	 */
	private Set<Customer> customers = new HashSet<Customer>();
	/**
	 * 销售分布
	 */
	private Set<SalesDistribution> salesDistributions = new HashSet<SalesDistribution>();
	private Set<ItemCatalog> subItemCatalogs = new HashSet<ItemCatalog>();
	/**
	 * 币种
	 */
	private CurrencyType currencyType;
	/**
	 * 是否含有上级渠道商/分销商
	 */
	private String isHasParentChannelDistributor;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 门店类型 1,连锁 2,独立
	 */
	private String shopType;
	/**
	 * 短信发送次数
	 */
	private Double messageSendNum;
	/**
	 * 店长ID
	 */
	private String employeeId;
	/**
	 * 授权金额
	 */
	private Double amount;
	/**
	 * 授权次数
	 */
	private Long quantity;

	public ChannelDistributor() {
		super();
	}

	public ChannelDistributor(String id) {
		super();
		setId(id);
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public Employee getSalesEmployee() {
		return salesEmployee;
	}

	public void setSalesEmployee(Employee salesEmployee) {
		this.salesEmployee = salesEmployee;
	}

	public String getParentChannelDistributorCode() {
		return parentChannelDistributorCode;
	}

	public Set<com.vix.mdm.item.entity.StoreItem> getSubStoreItems() {
		return subStoreItems;
	}

	public void setSubStoreItems(Set<com.vix.mdm.item.entity.StoreItem> subStoreItems) {
		this.subStoreItems = subStoreItems;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

	public void setParentChannelDistributorCode(String parentChannelDistributorCode) {
		this.parentChannelDistributorCode = parentChannelDistributorCode;
	}

	public String getParentChannelDistributorName() {
		return parentChannelDistributorName;
	}

	public void setParentChannelDistributorName(String parentChannelDistributorName) {
		this.parentChannelDistributorName = parentChannelDistributorName;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public String getMallType() {
		return mallType;
	}

	public void setMallType(String mallType) {
		this.mallType = mallType;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getEmployeeCounts() {
		return employeeCounts;
	}

	public void setEmployeeCounts(Long employeeCounts) {
		this.employeeCounts = employeeCounts;
	}

	public String getParentCompanyCode() {
		return parentCompanyCode;
	}

	public void setParentCompanyCode(String parentCompanyCode) {
		this.parentCompanyCode = parentCompanyCode;
	}

	public String getParentCompanyName() {
		return parentCompanyName;
	}

	public void setParentCompanyName(String parentCompanyName) {
		this.parentCompanyName = parentCompanyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(String catalogs) {
		this.catalogs = catalogs;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getArtificialPerson() {
		return artificialPerson;
	}

	public void setArtificialPerson(String artificialPerson) {
		this.artificialPerson = artificialPerson;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public String getMaplink() {
		return maplink;
	}

	public void setMaplink(String maplink) {
		this.maplink = maplink;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public ChannelDistributor getParentChannelDistributor() {
		return parentChannelDistributor;
	}

	public void setParentChannelDistributor(ChannelDistributor parentChannelDistributor) {
		this.parentChannelDistributor = parentChannelDistributor;
	}

	public Set<ChannelDistributor> getSubChannelDistributors() {
		return subChannelDistributors;
	}

	public void setSubChannelDistributors(Set<ChannelDistributor> subChannelDistributors) {
		this.subChannelDistributors = subChannelDistributors;
	}

	public Set<Item> getMdmItem() {
		return mdmItem;
	}

	public void setMdmItem(Set<Item> mdmItem) {
		this.mdmItem = mdmItem;
	}

	public Set<ChannelPrice> getChannelPrices() {
		return channelPrices;
	}

	public void setChannelPrices(Set<ChannelPrice> channelPrices) {
		this.channelPrices = channelPrices;
	}

	public Set<OperatingConditions> getOperatingConditionss() {
		return operatingConditionss;
	}

	public void setOperatingConditionss(Set<OperatingConditions> operatingConditionss) {
		this.operatingConditionss = operatingConditionss;
	}

	public Set<PropertiesScale> getPropertiesScales() {
		return propertiesScales;
	}

	public void setPropertiesScales(Set<PropertiesScale> propertiesScales) {
		this.propertiesScales = propertiesScales;
	}

	public Set<ProductInformation> getProductInformations() {
		return productInformations;
	}

	public void setProductInformations(Set<ProductInformation> productInformations) {
		this.productInformations = productInformations;
	}

	public Set<MarketActivity> getMarketActivitys() {
		return marketActivitys;
	}

	public void setMarketActivitys(Set<MarketActivity> marketActivitys) {
		this.marketActivitys = marketActivitys;
	}

	public Set<AdvertisingCampaign> getAdvertisingCampaigns() {
		return advertisingCampaigns;
	}

	public void setAdvertisingCampaigns(Set<AdvertisingCampaign> advertisingCampaigns) {
		this.advertisingCampaigns = advertisingCampaigns;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Set<SalesDistribution> getSalesDistributions() {
		return salesDistributions;
	}

	public void setSalesDistributions(Set<SalesDistribution> salesDistributions) {
		this.salesDistributions = salesDistributions;
	}

	public StoreType getStoreType() {
		return storeType;
	}

	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getMarketPlaceId() {
		return marketPlaceId;
	}

	public void setMarketPlaceId(String marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}

	public Long getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(Long channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelProps() {
		return channelProps;
	}

	public void setChannelProps(String channelProps) {
		this.channelProps = channelProps;
	}

	public String getChannelDesc() {
		return channelDesc;
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getChannelClass() {
		return channelClass;
	}

	public void setChannelClass(Integer channelClass) {
		this.channelClass = channelClass;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getIsHasParentChannelDistributor() {
		return isHasParentChannelDistributor;
	}

	public void setIsHasParentChannelDistributor(String isHasParentChannelDistributor) {
		this.isHasParentChannelDistributor = isHasParentChannelDistributor;
	}

	public Set<ItemCatalog> getSubItemCatalogs() {
		return subItemCatalogs;
	}

	public void setSubItemCatalogs(Set<ItemCatalog> subItemCatalogs) {
		this.subItemCatalogs = subItemCatalogs;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Double getMessageSendNum() {
		return messageSendNum;
	}

	public void setMessageSendNum(Double messageSendNum) {
		this.messageSendNum = messageSendNum;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
