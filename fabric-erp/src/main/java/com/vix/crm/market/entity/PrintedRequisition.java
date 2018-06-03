package com.vix.crm.market.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/** 印刷品领用 */
public class PrintedRequisition extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 领用时间 */
	private Date requisitionDate;
	/** 数量 */
	private Long count;
	/** 用途 */
	private String use;
	/** 领用人 */
	private Employee employee;
	/** 印刷品 */
	private PrintedMatter printedMatter;
	
	public PrintedRequisition(){}

	public Date getRequisitionDate() {
		return requisitionDate;
	}

	public void setRequisitionDate(Date requisitionDate) {
		this.requisitionDate = requisitionDate;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
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

	public PrintedMatter getPrintedMatter() {
		return printedMatter;
	}

	public void setPrintedMatter(PrintedMatter printedMatter) {
		this.printedMatter = printedMatter;
	}
}
