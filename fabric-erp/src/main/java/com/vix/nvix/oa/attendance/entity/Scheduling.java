/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import java.util.Date;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.properties.Utils;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow 2016年7月15日
 * 
 *         排班
 */
public class Scheduling extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 班次 */
	private AttendanceRuleSet attRuleSet;
	/** 时间段 */
	private PeriodRule periodRule;
	/** 职员 */
	private Employee emp;
	/** 部门 */
	private OrganizationUnit unit;
	/** 公司 */
	private Organization org;
	/** 开始日期 */
	private Date scheduleStartTime;
	/** 结束日期 */
	private Date scheduleEndTime;
	/** 使用状态 */
	private Integer isEnable;

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public AttendanceRuleSet getAttRuleSet() {
		return attRuleSet;
	}

	public void setAttRuleSet(AttendanceRuleSet attRuleSet) {
		this.attRuleSet = attRuleSet;
	}

	public PeriodRule getPeriodRule() {
		return periodRule;
	}

	public void setPeriodRule(PeriodRule periodRule) {
		this.periodRule = periodRule;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public OrganizationUnit getUnit() {
		return unit;
	}

	public String getUnitName() {
		if (null != unit) {
			return unit.getFs();
		}
		if (null != emp && null != emp.getOrganizationUnit()) {
			return emp.getOrganizationUnit().getFs();
		}
		return "";
	}

	public String getOrgName() {
		if (null != org) {
			return org.getBriefName();
		}
		if (null != emp && null != emp.getOrganizationUnit()
				&& null != emp.getOrganizationUnit().getOrganization()) {
			return emp.getOrganizationUnit().getOrganization().getBriefName();
		}
		return "";
	}

	public void setUnit(OrganizationUnit unit) {
		this.unit = unit;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Date getScheduleStartTime() {
		return scheduleStartTime;
	}

	public String getScheduleStartTimeStr() {
		if (Utils.isNotEmpty(scheduleStartTime)) {
			return DateUtil.format(scheduleStartTime);
		}
		return "";
	}

	public void setScheduleStartTime(Date scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	public Date getScheduleEndTime() {
		return scheduleEndTime;
	}

	public String getScheduleEndTimeStr() {
		if (Utils.isNotEmpty(scheduleEndTime)) {
			return DateUtil.format(scheduleEndTime);
		}
		return "";
	}

	public void setScheduleEndTime(Date scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}

}
