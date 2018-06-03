package com.vix.crm.project.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.crm.base.entity.ProjectCostType;
import com.vix.hr.organization.entity.Employee;

/** 项目费用 */
public class ProjectCost extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 发生日期 */
	private Date happenDate;
	/** 金额  */
	private Double totalAmount;
	/** 票据张数 */
	private Long billCount;
	/** 用途 */
	private String use;
	/** 备注 */
	private String memo;
	/** 是否审批通过 0:未通过 1：通过*/
	private String isApproved;
	/** 项目 */
	private CrmProject crmProject; 
	/** 申请人 */
	private Employee employee;
	/** 项目费用类型 */
	private ProjectCostType projectCostType;
	/** 币种 */
	private CurrencyType currencyType;
	
	public ProjectCost(){}

	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getBillCount() {
		return billCount;
	}

	public void setBillCount(Long billCount) {
		this.billCount = billCount;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ProjectCostType getProjectCostType() {
		return projectCostType;
	}

	public void setProjectCostType(ProjectCostType projectCostType) {
		this.projectCostType = projectCostType;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}
}
