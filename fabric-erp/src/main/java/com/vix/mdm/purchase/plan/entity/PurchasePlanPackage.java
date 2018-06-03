package com.vix.mdm.purchase.plan.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 采购计划包 com.vix.mdm.purchase.plan.entity.PurchasePlanPackage
 *
 * @author bjitzhang
 *
 * @date 2015年11月12日
 */
public class PurchasePlanPackage extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 上报日期 */
	private Date packageDate;
	/** 上报人 */
	private String planMaker;
	/** 上报类型 */
	private String packageType;
	/** 计划类型 */
	private String planType;
	/** 审批状态 */
	private String approvalStatus;
	/** 计划 */
	private Set<PurchasePlan> subPurchasePlans = new HashSet<PurchasePlan>();
	/**
	 * 上报计划到个人
	 */
	private Employee employee;
	/**
	 * 上报计划到部门
	 */
	private OrganizationUnit organizationUnit;
	/**
	 * 采购数量
	 */
	private Double amount;
	/**
	 * 采购金额
	 */
	private Double price;

	/**
	 * @return the packageDate
	 */
	public Date getPackageDate() {
		return packageDate;
	}

	/**
	 * @param packageDate
	 *            the packageDate to set
	 */
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}

	/**
	 * @return the planMaker
	 */
	public String getPlanMaker() {
		return planMaker;
	}

	/**
	 * @param planMaker
	 *            the planMaker to set
	 */
	public void setPlanMaker(String planMaker) {
		this.planMaker = planMaker;
	}

	/**
	 * @return the packageType
	 */
	public String getPackageType() {
		return packageType;
	}

	/**
	 * @param packageType
	 *            the packageType to set
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
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
	 * @return the planType
	 */
	public String getPlanType() {
		return planType;
	}

	/**
	 * @param planType
	 *            the planType to set
	 */
	public void setPlanType(String planType) {
		this.planType = planType;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @param approvalStatus
	 *            the approvalStatus to set
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * @return the subPurchasePlans
	 */
	public Set<PurchasePlan> getSubPurchasePlans() {
		return subPurchasePlans;
	}

	/**
	 * @param subPurchasePlans
	 *            the subPurchasePlans to set
	 */
	public void setSubPurchasePlans(Set<PurchasePlan> subPurchasePlans) {
		this.subPurchasePlans = subPurchasePlans;
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
