package com.vix.sales.invoice.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.base.entity.SaleInvoiceType;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SalesOrder;

/** 销售发票 */
public class SalesInvoice extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 销售订单编码 */
	private String saleOrderCode;
	/** 销售发票编号 */
	private String invoiceCode;
	/** 单据日期 */
	private Date billDate;
	/** 成组编码 */
	private String groupCode;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 部门编码 */
	private String deptCode;
	/** 业务员编码 */
	private String salePersonCode;
	/** 业务类型 */
	private String bizType;
	/** 单据类型 */
	private String formType;
	/** 发票类型  1：销售普通发票 2：销售专用发票 3：红字普通销售发票  4：红字专用销售发票 */
	private String invoiceType;
	private SaleInvoiceType saleInvoiceType;
	/** 联系人 */
//	private ContactPerson contactPerson;
	/** 销售组织 */
	private String saleOrg;
	/** 销售人员 */
//	private Employee salePerson;
	/** 发运地 */
	private String source;
	/** 目的地 */
	private String target;
	/** 金额 */
	private Double amount;
	/** 币种 */
//	private CurrencyType currencyType;
	/** 税率 */
	private Double tax;
	/** 付款条件 */
	private String payCondition;
	/** 交货日期 */
	private Date deliveryTime;
	/** 明细 */
	private Set<SalesInvoiceItem> salesInvoiceItems = new HashSet<SalesInvoiceItem>();
	/** 销售订单 */
	private SalesOrder salesOrder;
	/** 经手人  */
	private Employee employee;
	/** 期次  */
	private Integer issue;
	/** 是否回款  */
	private String isBackSection;
	/** 回款计划  */
	private String backSectionPlan;
	 
	public SalesInvoice(){}

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getSalePersonCode() {
		return salePersonCode;
	}

	public void setSalePersonCode(String salePersonCode) {
		this.salePersonCode = salePersonCode;
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

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Set<SalesInvoiceItem> getSalesInvoiceItems() {
		return salesInvoiceItems;
	}

	public void setSalesInvoiceItems(Set<SalesInvoiceItem> salesInvoiceItems) {
		this.salesInvoiceItems = salesInvoiceItems;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public SaleInvoiceType getSaleInvoiceType() {
		return saleInvoiceType;
	}

	public void setSaleInvoiceType(SaleInvoiceType saleInvoiceType) {
		this.saleInvoiceType = saleInvoiceType;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getIssue() {
		return issue;
	}

	public void setIssue(Integer issue) {
		this.issue = issue;
	}

	public String getIsBackSection() {
		return isBackSection;
	}

	public void setIsBackSection(String isBackSection) {
		this.isBackSection = isBackSection;
	}

	public String getBackSectionPlan() {
		return backSectionPlan;
	}

	public void setBackSectionPlan(String backSectionPlan) {
		this.backSectionPlan = backSectionPlan;
	}
	
}
