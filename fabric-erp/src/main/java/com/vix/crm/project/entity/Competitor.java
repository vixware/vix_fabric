package com.vix.crm.project.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.base.entity.Competitive;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 竞争对手 */
public class Competitor extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 公司名称 */
	private String companyName;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 价格 */
	private Double price;
	/** 竞争产品 */
	private String competeProduct;
	/** 优势 */
	private String superiority;
	/** 劣势 */
	private String inferior;
	/** 应对策略 */
	private String copeStrategy;
	/** 备注 */
	private String memo;
	/** 项目 */
	private CrmProject crmProject;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 销售机会 */
	private SaleChance saleChance;
	/** 竞争能力  */
	private Competitive competitive;
	/** 竞争产品 */
	private Set<CompetitorProduct> competitorProducts = new HashSet<CompetitorProduct>();
	
	/**
	 * @author Bluesnow
	 * 2015-11-26
	 * 添加竞争对手公司详细信息字段
	 * 
	 * */
	
	/** 公司地址 */
	private String address;
	/** 公司网站 */
	private String website;
	/** 联系人 */
	private String contactPerson;
	/** 联系人电话 */
	private String cpPhone;
	/** 产品描述  */
	private String description;
	
	public Competitor(){}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCompeteProduct() {
		return competeProduct;
	}

	public void setCompeteProduct(String competeProduct) {
		this.competeProduct = competeProduct;
	}

	public String getSuperiority() {
		return superiority;
	}

	public void setSuperiority(String superiority) {
		this.superiority = superiority;
	}

	public String getInferior() {
		return inferior;
	}

	public void setInferior(String inferior) {
		this.inferior = inferior;
	}

	public String getCopeStrategy() {
		return copeStrategy;
	}

	public void setCopeStrategy(String copeStrategy) {
		this.copeStrategy = copeStrategy;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public SaleChance getSaleChance() {
		return saleChance;
	}

	public void setSaleChance(SaleChance saleChance) {
		this.saleChance = saleChance;
	}

	public Set<CompetitorProduct> getCompetitorProducts() {
		return competitorProducts;
	}

	public void setCompetitorProducts(Set<CompetitorProduct> competitorProducts) {
		this.competitorProducts = competitorProducts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCpPhone() {
		return cpPhone;
	}

	public void setCpPhone(String cpPhone) {
		this.cpPhone = cpPhone;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public Competitive getCompetitive() {
		return competitive;
	}

	public void setCompetitive(Competitive competitive) {
		this.competitive = competitive;
	}
}
