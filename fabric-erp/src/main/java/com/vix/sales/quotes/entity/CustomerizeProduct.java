package com.vix.sales.quotes.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 本业务对象记录客户对产品的需要描述，可以是一个附件，也可以是分项描述，即针对不同的物料[配件]进行描述；或者是按照技术规格进行描述。
 * formType表示为需求类型,
 * 定制产品需求环节非必需环节, 可以直接通过报价模板创建报价单，并进行分解，并下达。
 * @author Administrator
 */
public class CustomerizeProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 单据日期 */
	private Date billDate;
	/** 部门 */
	private OrganizationUnit organizationUnit;
	/** 单据成组编码 */
	private String groupCode;
	/** 需求类型 */
	private String requirementType;
	/** 销售组织 */
	private String salesOrg;
	/** 销售人员 */
	private Employee salePerson;
	/** 需求内容 */
	private String requirementContent;
	/** 开始日期 */
	private Date startDate;
	/** 结束日期 */
	private Date endDate;
	/** 是否为临时对象 临时为：1 实际使用为空 */
	private String isTemp;
	/** 客户需求明细 */
	private Set<CustomerizeProductItem> customerizeProductItems = new HashSet<CustomerizeProductItem>();
	/** 项目式价单单 */
	private ProjectQuotationScheme projectQuotationScheme;
	
	public CustomerizeProduct(){}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getRequirementType() {
		return requirementType;
	}

	public void setRequirementType(String requirementType) {
		this.requirementType = requirementType;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getRequirementContent() {
		return requirementContent;
	}

	public void setRequirementContent(String requirementContent) {
		this.requirementContent = requirementContent;
	}

	public Set<CustomerizeProductItem> getCustomerizeProductItems() {
		return customerizeProductItems;
	}

	public void setCustomerizeProductItems(
			Set<CustomerizeProductItem> customerizeProductItems) {
		this.customerizeProductItems = customerizeProductItems;
	}

	public ProjectQuotationScheme getProjectQuotationScheme() {
		return projectQuotationScheme;
	}

	public void setProjectQuotationScheme(
			ProjectQuotationScheme projectQuotationScheme) {
		this.projectQuotationScheme = projectQuotationScheme;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public Employee getSalePerson() {
		return salePerson;
	}

	public void setSalePerson(Employee salePerson) {
		this.salePerson = salePerson;
	}
}
