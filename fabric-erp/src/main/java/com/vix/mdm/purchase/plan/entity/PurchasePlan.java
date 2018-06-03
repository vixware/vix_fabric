package com.vix.mdm.purchase.plan.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseBOEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 采购计划
 * 
 * @ClassFullName com.vix.mdm.purchase.plan.entity.PurchasePlan
 *
 * @author bjitzhang
 *
 * @date 2016年1月18日
 *
 */
public class PurchasePlan extends BaseBOEntity {

	/**
	 * status   1,未执行     2,已执行
	 */
	private static final long serialVersionUID = 1L;
	/** 采购计划编码 */
	private String purchasePlanCode;
	/** 采购计划名称 */
	private String purchasePlanName;
	/** 父计划编码 */
	private String parentPlanCode;
	/** 需求来源 */
	private String requestSource;
	/** 供应商编码 */
	private String supplierCode;
	/** 供应商名称 */
	private String supplierName;
	/** 数量 */
	private Double amount;
	/**
	 * 价格
	 */
	private Double price;
	/** 执行日期 */
	private Date executeDate;
	/** 计划编制人 */
	private String planMaker;
	/** 类型 */
	private String style;
	/** 审批状态 */
	private String approvalStatus;
	/** 计划执行人 */
	private String executePerson;
	/** 计划执行部门 */
	private String executeDepartment;

	private PurchasePlanPackage purchasePlanPackage;
	/** 明细 */
	private Set<PurchasePlanItems> purchasePlanItems = new HashSet<PurchasePlanItems>();
	/** 附件 */
	private Set<Attachments> attachments = new HashSet<Attachments>();
	/**
	 * 是否生成销售订单
	 */
	private String isCreateSalesOrder;
	/**
	 * 是否正式版本
	 */
	private String isFormally;
	/**
	 * 计划时间
	 */
	private String planTime;
	/**
	 * 是否上报 0,未上报; 1,已上报.
	 */
	private String isReport;
	/**
	 * 计划任务下发类型 P:个人 D:部门
	 */
	private String executeType;
	/**
	 * 上报计划到个人
	 */
	private Employee employee;
	/**
	 * 上报计划到部门
	 */
	private OrganizationUnit organizationUnit;
	/**
	 * 供应商
	 */
	private Supplier supplier;

	public String getPurchasePlanCode() {
		return purchasePlanCode;
	}

	public void setPurchasePlanCode(String purchasePlanCode) {
		this.purchasePlanCode = purchasePlanCode;
	}

	public String getPurchasePlanName() {
		return purchasePlanName;
	}

	public void setPurchasePlanName(String purchasePlanName) {
		this.purchasePlanName = purchasePlanName;
	}

	public String getParentPlanCode() {
		return parentPlanCode;
	}

	public void setParentPlanCode(String parentPlanCode) {
		this.parentPlanCode = parentPlanCode;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the planTime
	 */
	public String getPlanTime() {
		return planTime;
	}

	/**
	 * @param planTime
	 *            the planTime to set
	 */
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getRequestSource() {
		return requestSource;
	}

	public void setRequestSource(String requestSource) {
		this.requestSource = requestSource;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}

	/**
	 * @return the purchasePlanPackage
	 */
	public PurchasePlanPackage getPurchasePlanPackage() {
		return purchasePlanPackage;
	}

	/**
	 * @param purchasePlanPackage
	 *            the purchasePlanPackage to set
	 */
	public void setPurchasePlanPackage(PurchasePlanPackage purchasePlanPackage) {
		this.purchasePlanPackage = purchasePlanPackage;
	}

	public String getPlanMaker() {
		return planMaker;
	}

	public void setPlanMaker(String planMaker) {
		this.planMaker = planMaker;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier
	 *            the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getExecutePerson() {
		return executePerson;
	}

	public void setExecutePerson(String executePerson) {
		this.executePerson = executePerson;
	}

	public String getExecuteDepartment() {
		return executeDepartment;
	}

	public void setExecuteDepartment(String executeDepartment) {
		this.executeDepartment = executeDepartment;
	}

	/**
	 * @return the isReport
	 */
	public String getIsReport() {
		return isReport;
	}

	/**
	 * @param isReport
	 *            the isReport to set
	 */
	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setAttachments(Set<Attachments> attachments) {
		this.attachments = attachments;
	}

	public Set<PurchasePlanItems> getPurchasePlanItems() {
		return purchasePlanItems;
	}

	public void setPurchasePlanItems(Set<PurchasePlanItems> purchasePlanItems) {
		this.purchasePlanItems = purchasePlanItems;
	}

	public String getIsCreateSalesOrder() {
		return isCreateSalesOrder;
	}

	public void setIsCreateSalesOrder(String isCreateSalesOrder) {
		this.isCreateSalesOrder = isCreateSalesOrder;
	}

	/**
	 * @return the isFormally
	 */
	public String getIsFormally() {
		return isFormally;
	}

	/**
	 * @param isFormally
	 *            the isFormally to set
	 */
	public void setIsFormally(String isFormally) {
		this.isFormally = isFormally;
	}

	/**
	 * @return the executeType
	 */
	public String getExecuteType() {
		return executeType;
	}

	/**
	 * @param executeType
	 *            the executeType to set
	 */
	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the organizationUnit
	 */
	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	/**
	 * @param organizationUnit
	 *            the organizationUnit to set
	 */
	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

}
