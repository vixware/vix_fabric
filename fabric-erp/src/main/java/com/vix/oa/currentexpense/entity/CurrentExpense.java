package com.vix.oa.currentexpense.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 日常费用
 */

public class CurrentExpense extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 报销人
	 */
	private Employee employee;
	/**
	 * 报销金额
	 */
	private Double expensesAmount;
	/**
	 * 发票张数
	 */
	private Long invoiceNumber;

	private Set<CurrentExpenseDetail> subCurrentExpenseDetails;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Double getExpensesAmount() {
		return expensesAmount;
	}

	public void setExpensesAmount(Double expensesAmount) {
		this.expensesAmount = expensesAmount;
	}

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Set<CurrentExpenseDetail> getSubCurrentExpenseDetails() {
		return subCurrentExpenseDetails;
	}

	public void setSubCurrentExpenseDetails(Set<CurrentExpenseDetail> subCurrentExpenseDetails) {
		this.subCurrentExpenseDetails = subCurrentExpenseDetails;
	}

}