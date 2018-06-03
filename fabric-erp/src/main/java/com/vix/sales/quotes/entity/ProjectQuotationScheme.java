package com.vix.sales.quotes.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.Regional;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 项目式报价方案 */
public class ProjectQuotationScheme extends BaseBOEntity {
 
	private static final long serialVersionUID = 1L;
	/** 地域 */
	private Regional regional;
	/** 单据日期	 */
	private Date billDate;
	/** 单据名称 */
	private String name;
	/** 单据类型 */
	private String type;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 联系人 */
	private ContactPerson contactPerson;
	/** 部门 */
	private OrganizationUnit organizationUnit;
	/** 单据成组编码 */
	private String groupCode;
	/** 业务类型 */
	private String bizType;
	/** 单据类型 */
	private String formType;
	/** 销售组织 */
	private OrganizationUnit salesOrg;
	/** 销售人员 */
	private Employee salesMan;
	/** 需求内容 */
	private String requirementContent;
	/** 币种 */
	private String currency;
	/** 税率 */
	private Double tax;
	/** 金额 */
	private Double amount;
	/** 是否为临时对象 临时为：1 实际使用为空 */
	private String isTemp;
	/** 上级分类 */
	private SalesQuotationCategory salesQuotationCategory;
	/** 项目式报价明细 */
	private Set<ProjectQuotationSchemeItem> projectQuotationSchemeItems = new HashSet<ProjectQuotationSchemeItem>();
	
	public ProjectQuotationScheme(){}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public OrganizationUnit getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(OrganizationUnit salesOrg) {
		this.salesOrg = salesOrg;
	}

	public Employee getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(Employee salesMan) {
		this.salesMan = salesMan;
	}

	public String getRequirementContent() {
		return requirementContent;
	}

	public void setRequirementContent(String requirementContent) {
		this.requirementContent = requirementContent;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public SalesQuotationCategory getSalesQuotationCategory() {
		return salesQuotationCategory;
	}

	public void setSalesQuotationCategory(
			SalesQuotationCategory salesQuotationCategory) {
		this.salesQuotationCategory = salesQuotationCategory;
	}

	public Set<ProjectQuotationSchemeItem> getProjectQuotationSchemeItems() {
		return projectQuotationSchemeItems;
	}

	public void setProjectQuotationSchemeItems(
			Set<ProjectQuotationSchemeItem> projectQuotationSchemeItems) {
		this.projectQuotationSchemeItems = projectQuotationSchemeItems;
	}
}
