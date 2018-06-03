package com.vix.drp.competitorInformation.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 竞争者/竞争渠道信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-29
 */
public class CompetingChannelInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 销售组织
	 */
	private String saleOrg;
	/**
	 * 销售组织编码
	 */
	private String saleOrgCode;
	/**
	 * 渠道经销商码
	 */
	private String indetity;
	/**
	 * 简称
	 */
	private String shortName;
	/**
	 * 拼音
	 */
	private String pinYin;
	/**
	 * 英文名称
	 */
	private String englishName;
	/**
	 * 上级渠道经销商编码
	 */
	private String parentChannelDistributorCode;
	/**
	 * 上级渠道经销商
	 */
	private String parentChannelDistributorName;
	/**
	 * 所属分类
	 */
	private String catalog;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 所属地区
	 */
	private String region;
	/**
	 * 员工人数
	 */
	private Long employeeCounts;
	/**
	 * 母公司代码
	 */
	private String parentCompanyCode;
	/**
	 * 母公司
	 */
	private String parentCompanyName;
	/**
	 * 所属行业
	 */
	private String industry;
	/**
	 * 币种
	 */
	private String currency;
	/**
	 * 分类
	 */
	private String catalogs;
	/**
	 * 注册资金
	 */
	private String registeredCapital;
	/**
	 * 开户银行
	 */
	private String openingBank;
	/**
	 * 银行帐号
	 */
	private String bankAccount;
	/**
	 * 税号
	 */
	private String taxCode;
	/**
	 * 法人
	 */
	private String artificialPerson;
	/**
	 * 等级
	 */
	private String classes;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 传真
	 */
	private String telefax;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 网址
	 */
	private String url;

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getSaleOrgCode() {
		return saleOrgCode;
	}

	public void setSaleOrgCode(String saleOrgCode) {
		this.saleOrgCode = saleOrgCode;
	}

	public String getIndetity() {
		return indetity;
	}

	public void setIndetity(String indetity) {
		this.indetity = indetity;
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

	public String getParentChannelDistributorCode() {
		return parentChannelDistributorCode;
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

	public void setRegion(String region) {
		this.region = region;
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

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelefax() {
		return telefax;
	}

	public void setTelefax(String telefax) {
		this.telefax = telefax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
