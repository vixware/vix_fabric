package com.vix.crm.market.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/** 礼品领用 */
public class CrmGiftRequisition extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 领用时间 */
	private Date requisitionDate;
	/** 数量 */
	private Long count;
	/** 用途 */
	private String use;
	/** 领用人 */
	private Employee employee;
	/** 礼品 */
	private CrmGift crmGift;
	
	public CrmGiftRequisition(){}

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

	public CrmGift getCrmGift() {
		return crmGift;
	}

	public void setCrmGift(CrmGift crmGift) {
		this.crmGift = crmGift;
	}
}
