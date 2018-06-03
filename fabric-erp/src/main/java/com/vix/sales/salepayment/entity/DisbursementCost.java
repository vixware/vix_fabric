package com.vix.sales.salepayment.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 代垫费用 */
public class DisbursementCost extends BaseEntity {
 
	private static final long serialVersionUID = 1L;

	/** 代垫日期 */
	private Date disbursementDate;
	/** 代垫单号 */
	private String disbursementBillNumber;
	/** 发票号 */
	private String invoice;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 销售部门 */
	private String saleOrg;
	/** 业务员 */
	private Employee employee;
	/** 备注 */
	private String memo;
	/** 币种 */
	private CurrencyType currencyType;
	
	private Set<DisbursementCostDetail> disbursementCostDetails = new HashSet<DisbursementCostDetail>();
	
	public DisbursementCost(){}

	public Date getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	public String getDisbursementBillNumber() {
		return disbursementBillNumber;
	}

	public void setDisbursementBillNumber(String disbursementBillNumber) {
		this.disbursementBillNumber = disbursementBillNumber;
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

	public Set<DisbursementCostDetail> getDisbursementCostDetails() {
		return disbursementCostDetails;
	}

	public void setDisbursementCostDetails(
			Set<DisbursementCostDetail> disbursementCostDetails) {
		this.disbursementCostDetails = disbursementCostDetails;
	}
}
