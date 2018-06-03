package com.vix.sales.salepayment.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.utils.DateUtil;
import com.vix.crm.base.entity.ExpenseType;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 费用支出 */
public class Expenses extends BaseEntity {
 
	private static final long serialVersionUID = 1L;
	
	private Date expensesDate;
	
	private Double amount;
	
	private Integer num;
	
	private String expensesBillNumber;
	
	private String invoice;
	
	private CustomerAccount customerAccount;
	
	private CrmProject crmProject;
	
	private String saleOrg;
	
	private Employee employee;
	
	private String memo;
	
	private CurrencyType currencyType;
	
	private ExpenseType expenseType;
	
	private String purpose;
	
	private Set<ExpensesDetail> expensesDetails = new HashSet<ExpensesDetail>();
	
	public Expenses(){}

	public Date getExpensesDate() {
		return expensesDate;
	}
	
	public String getExpensesDateStr() {
		if(expensesDate != null){
			return DateUtil.format(expensesDate);
		}
		return "";
	}

	public void setExpensesDate(Date expensesDate) {
		this.expensesDate = expensesDate;
	}

	public String getExpensesBillNumber() {
		return expensesBillNumber;
	}

	public void setExpensesBillNumber(String expensesBillNumber) {
		this.expensesBillNumber = expensesBillNumber;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Set<ExpensesDetail> getExpensesDetails() {
		return expensesDetails;
	}

	public void setExpensesDetails(Set<ExpensesDetail> expensesDetails) {
		this.expensesDetails = expensesDetails;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}
}
