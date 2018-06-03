package com.vix.oa.currentexpense.entity;

import java.util.Date;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.accountMaintenance.entity.ExpenseAccount;
import com.vix.oa.claimsmanagement.areaLevel.entity.AreaLevel;
import com.vix.oa.travelclaims.entity.TravelExpense;

/**
 * 日常费用 com.vix.oa.currentexpense.entity.CurrentExpenseDetail
 *
 * @author bjitzhang
 *
 * @date 2015年3月31日
 */

public class CurrentExpenseDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 开支日期
	 */
	private Date expensesDate;
	/**
	 * 报销标准
	 */
	private Double expensesStandard;
	/**
	 * 月份
	 */
	private String expensesMonth;
	/**
	 * 报销金额
	 */
	private Double expensesAmountDetail;
	/**
	 * 发票张数
	 */
	private Double invoiceNumberDetail;
	/**
	 * 科目
	 */
	private ExpenseAccount expenseAccount;
	/**
	 * 区域
	 */
	private AreaLevel areaLevel;
	/**
	 * 员工
	 */
	private Employee employee;
	/**
	 * 岗位
	 * 
	 * @return
	 */
	private OrgPosition orgPosition;
	/**
	 * 部门
	 */
	private OrganizationUnit organizationUnit;
	/**
	 * 报销单
	 */
	private TravelExpense travelExpense;

	public Date getExpensesDate() {
		return expensesDate;
	}
	public String getExpensesDateStr() {
		if (null != expensesDate) {
			return DateUtil.format(expensesDate);
		}
		return "";
	}
	public String getExpensesDateTimeStr() {
		if (null != expensesDate) {
			return DateUtil.formatTime(expensesDate);
		}
		return "";
	}

	public void setExpensesDate(Date expensesDate) {
		this.expensesDate = expensesDate;
	}

	public Double getExpensesStandard() {
		return expensesStandard;
	}

	public void setExpensesStandard(Double expensesStandard) {
		this.expensesStandard = expensesStandard;
	}

	public String getExpensesMonth() {
		return expensesMonth;
	}

	public void setExpensesMonth(String expensesMonth) {
		this.expensesMonth = expensesMonth;
	}

	public Double getExpensesAmountDetail() {
		return expensesAmountDetail;
	}

	public void setExpensesAmountDetail(Double expensesAmountDetail) {
		this.expensesAmountDetail = expensesAmountDetail;
	}

	public Double getInvoiceNumberDetail() {
		return invoiceNumberDetail;
	}

	public void setInvoiceNumberDetail(Double invoiceNumberDetail) {
		this.invoiceNumberDetail = invoiceNumberDetail;
	}

	public ExpenseAccount getExpenseAccount() {
		return expenseAccount;
	}

	public void setExpenseAccount(ExpenseAccount expenseAccount) {
		this.expenseAccount = expenseAccount;
	}

	/**
	 * @return the travelExpense
	 */
	public TravelExpense getTravelExpense() {
		return travelExpense;
	}

	/**
	 * @param travelExpense
	 *            the travelExpense to set
	 */
	public void setTravelExpense(TravelExpense travelExpense) {
		this.travelExpense = travelExpense;
	}

	/**
	 * @return the areaLevel
	 */
	public AreaLevel getAreaLevel() {
		return areaLevel;
	}

	/**
	 * @param areaLevel
	 *            the areaLevel to set
	 */
	public void setAreaLevel(AreaLevel areaLevel) {
		this.areaLevel = areaLevel;
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
	 * @return the orgPosition
	 */
	public OrgPosition getOrgPosition() {
		return orgPosition;
	}

	/**
	 * @param orgPosition
	 *            the orgPosition to set
	 */
	public void setOrgPosition(OrgPosition orgPosition) {
		this.orgPosition = orgPosition;
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