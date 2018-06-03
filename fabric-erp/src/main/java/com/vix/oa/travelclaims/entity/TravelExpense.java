package com.vix.oa.travelclaims.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.currentexpense.entity.CurrentExpenseDetail;
import com.vix.oa.task.taskDefinition.entity.Uploader;

/**
 * 报销费用
 */

/**
 * @author  zhanghaibing
 * 
 * @类全名   com.vix.oa.travelclaims.entity.TravelExpense
 *
 * @date   2018年4月3日
 */
/**
 * @author zhanghaibing
 * 
 * @类全名 com.vix.oa.travelclaims.entity.TravelExpense
 *
 * @date 2018年4月3日
 */
public class TravelExpense extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5167127785199158067L;
	/**
	 * 报销人
	 */
	private Employee employee;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 报销金额
	 */
	private Double cost;
	private Set<TransportCosts> transportCostss = new HashSet<TransportCosts>();
	private Set<AccommodationFee> accommodationFees = new HashSet<AccommodationFee>();
	private Set<CurrentExpenseDetail> subCurrentExpenseDetails = new HashSet<CurrentExpenseDetail>();

	/**
	 * 部门
	 */
	private OrganizationUnit organizationUnit;
	/**
	 * 汇总合计
	 */
	private Double amount;
	/** 上传附件 */
	private Set<Uploader> subUploaders = new HashSet<Uploader>();
	/**
	 * 报销日期
	 */
	private Date travelExpenseDate;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	 * @return the subUploaders
	 */
	public Set<Uploader> getSubUploaders() {
		return subUploaders;
	}

	/**
	 * @param subUploaders
	 *            the subUploaders to set
	 */
	public void setSubUploaders(Set<Uploader> subUploaders) {
		this.subUploaders = subUploaders;
	}

	public Set<TransportCosts> getTransportCostss() {
		return transportCostss;
	}

	public void setTransportCostss(Set<TransportCosts> transportCostss) {
		this.transportCostss = transportCostss;
	}

	public Set<AccommodationFee> getAccommodationFees() {
		return accommodationFees;
	}

	public void setAccommodationFees(Set<AccommodationFee> accommodationFees) {
		this.accommodationFees = accommodationFees;
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

	/**
	 * @return the travelExpenseDate
	 */
	public Date getTravelExpenseDate() {
		return travelExpenseDate;
	}
	public String getTravelExpenseDateStr() {
		if (null != travelExpenseDate) {
			return DateUtil.format(travelExpenseDate);
		}
		return "";
	}
	public String getTravelExpenseDateTimeStr() {
		if (null != travelExpenseDate) {
			return DateUtil.formatTime(travelExpenseDate);
		}
		return "";
	}

	/**
	 * @param travelExpenseDate
	 *            the travelExpenseDate to set
	 */
	public void setTravelExpenseDate(Date travelExpenseDate) {
		this.travelExpenseDate = travelExpenseDate;
	}

	/**
	 * @return the subCurrentExpenseDetails
	 */
	public Set<CurrentExpenseDetail> getSubCurrentExpenseDetails() {
		return subCurrentExpenseDetails;
	}

	/**
	 * @param subCurrentExpenseDetails
	 *            the subCurrentExpenseDetails to set
	 */
	public void setSubCurrentExpenseDetails(Set<CurrentExpenseDetail> subCurrentExpenseDetails) {
		this.subCurrentExpenseDetails = subCurrentExpenseDetails;
	}

}