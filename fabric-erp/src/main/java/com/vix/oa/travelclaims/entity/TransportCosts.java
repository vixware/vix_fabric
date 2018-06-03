package com.vix.oa.travelclaims.entity;

import java.util.Date;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.claimsmanagement.areaLevel.entity.AreaLevel;
import com.vix.oa.claimsmanagement.areaLevel.entity.VixCity;
import com.vix.oa.claimsmanagement.vehicle.entity.Transport;

/**
 * 交通费用
 */

public class TransportCosts extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1999431480804853691L;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 出发地
	 */
	private String fromPlace;
	/**
	 * 目的地
	 */
	private String toPlace;
	/**
	 * 费用
	 */
	private Double cost;
	/**
	 * 报销标准
	 */
	private Double reimbursementAmount;
	/**
	 * 报销
	 */
	private TravelExpense travelExpense;
	/**
	 * 乘坐交通工具时间
	 */
	private Date usingTime;
	/**
	 * 出差城市
	 */
	private VixCity vixCity;
	/**
	 * 区域
	 */
	private AreaLevel areaLevel;
	/**
	 * 交通工具
	 */
	private Transport transport;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	/**
	 * @return the reimbursementAmount
	 */
	public Double getReimbursementAmount() {
		return reimbursementAmount;
	}

	/**
	 * @param reimbursementAmount
	 *            the reimbursementAmount to set
	 */
	public void setReimbursementAmount(Double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public TravelExpense getTravelExpense() {
		return travelExpense;
	}

	public void setTravelExpense(TravelExpense travelExpense) {
		this.travelExpense = travelExpense;
	}

	public Date getUsingTime() {
		return usingTime;
	}
	public String getUsingTimeStr() {
		if (null != usingTime) {
			return DateUtil.format(usingTime);
		}
		return "";
	}
	public String getUsingTimeTimeStr() {
		if (null != usingTime) {
			return DateUtil.formatTime(usingTime);
		}
		return "";
	}
	public void setUsingTime(Date usingTime) {
		this.usingTime = usingTime;
	}

	/**
	 * @return the vixCity
	 */
	public VixCity getVixCity() {
		return vixCity;
	}

	/**
	 * @param vixCity
	 *            the vixCity to set
	 */
	public void setVixCity(VixCity vixCity) {
		this.vixCity = vixCity;
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

	/**
	 * @return the transport
	 */
	public Transport getTransport() {
		return transport;
	}

	/**
	 * @param transport
	 *            the transport to set
	 */
	public void setTransport(Transport transport) {
		this.transport = transport;
	}

}