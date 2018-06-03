package com.vix.oa.currentexpense.entity;

import java.util.Date;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.hr.organization.entity.Employee;

/**
 * 报销汇总表
 * 
 * com.vix.oa.currentexpense.entity.ExpenseAccountSummary
 *
 * @author bjitzhang
 *
 * @date 2015年6月15日
 */

public class ExpenseAccountSummary extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 报销类型
	 */
	private String businessType;
	/**
	 * 报销日期
	 */
	private Date businessDate;
	/**
	 * 业务员
	 */
	private String salesman;
	/**
	 * 币种
	 */
	private CurrencyType currencyType;
	/**
	 * 报销人
	 */
	private String expensesPerson;
	/**
	 * 金额
	 */
	private Double expensesAmount;
	/**
	 * 用途
	 */
	private String use;
	/**
	 * 人员
	 */
	private Employee employee;
	/**
	 * 部门
	 */
	private OrganizationUnit organizationUnit;
	/**
	 * 项目
	 */
	private String project;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getExpensesPerson() {
		return expensesPerson;
	}

	public void setExpensesPerson(String expensesPerson) {
		this.expensesPerson = expensesPerson;
	}

	public Double getExpensesAmount() {
		return expensesAmount;
	}

	public void setExpensesAmount(Double expensesAmount) {
		this.expensesAmount = expensesAmount;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

}