package com.vix.oa.travelclaims.entity;

import java.util.Date;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.claimsmanagement.areaLevel.entity.AreaLevel;

/**
 * 住宿费用
 */

public class AccommodationFee extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String project;
	private String lodgingFee;
	private Integer days;
	private Double housePrices;
	private Double cost;
	private TravelExpense travelExpense;
	/**
	 * 住宿地方
	 */
	private String accomodationsPlaces;
	/**
	 * 开始住宿时间
	 */
	private Date startAccomodationsTime;
	/**
	 * 结束住宿时间
	 */
	private Date endAccomodationsTime;
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getLodgingFee() {
		return lodgingFee;
	}

	public void setLodgingFee(String lodgingFee) {
		this.lodgingFee = lodgingFee;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Double getHousePrices() {
		return housePrices;
	}

	public void setHousePrices(Double housePrices) {
		this.housePrices = housePrices;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public TravelExpense getTravelExpense() {
		return travelExpense;
	}

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

	public String getAccomodationsPlaces() {
		return accomodationsPlaces;
	}

	public void setAccomodationsPlaces(String accomodationsPlaces) {
		this.accomodationsPlaces = accomodationsPlaces;
	}

	public Date getStartAccomodationsTime() {
		return startAccomodationsTime;
	}
	public String getStartAccomodationsTimeStr() {
		if (null != startAccomodationsTime) {
			return DateUtil.format(startAccomodationsTime);
		}
		return "";
	}
	public String getStartAccomodationsTimeTimeStr() {
		if (null != startAccomodationsTime) {
			return DateUtil.formatTime(startAccomodationsTime);
		}
		return "";
	}

	public void setStartAccomodationsTime(Date startAccomodationsTime) {
		this.startAccomodationsTime = startAccomodationsTime;
	}

	public Date getEndAccomodationsTime() {
		return endAccomodationsTime;
	}

	public void setEndAccomodationsTime(Date endAccomodationsTime) {
		this.endAccomodationsTime = endAccomodationsTime;
	}
	public String getEndAccomodationsTimeStr() {
		if (null != endAccomodationsTime) {
			return DateUtil.format(endAccomodationsTime);
		}
		return "";
	}
	public String getEndAccomodationsTimeTimeStr() {
		if (null != endAccomodationsTime) {
			return DateUtil.formatTime(endAccomodationsTime);
		}
		return "";
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