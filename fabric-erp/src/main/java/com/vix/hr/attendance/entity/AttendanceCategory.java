package com.vix.hr.attendance.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 考勤类别
 * @author 李大鹏
 */
public class AttendanceCategory extends BaseEntity {

	private static final long serialVersionUID = -2470851671165177481L;
	/** 类别编码 */
	private String categoryCode;
	/** 类别名称 */
	private String categoryName;
	/** 备注 */
	private String remarks;
	/** 时间单位 0：小时 1：天 */
	private String unitTime;;
	/** 最小请假时间 */
	private String minimumOffTime;
	/** 带薪假 0：是 1：否 */
	private String paidVacation;
	/** 允许使用加班抵扣 0：是 1：否 */
	private String allowsOvertime;
	/** 启用额度检查 0：是 1：否 */
	private String enableLimitCheck;
	/** 固定期间-自然年 0：是 1：否 */
	private String fixedPeriodYear;
	/** 其他期间类型 0：是 1：否* */
	private String otherPeriodType;
	/** 假期额度结算公式 */
	private String holidayFormula;
	/** 对象类型 */
	private String objectType;
	/** 父分类 */
	private AttendanceCategory attendanceCategory;
	/** 子分类 */
	private Set<AttendanceCategory> attendanceCategories = new HashSet<AttendanceCategory>();

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUnitTime() {
		return unitTime;
	}

	public void setUnitTime(String unitTime) {
		this.unitTime = unitTime;
	}

	public String getMinimumOffTime() {
		return minimumOffTime;
	}

	public void setMinimumOffTime(String minimumOffTime) {
		this.minimumOffTime = minimumOffTime;
	}

	public String getPaidVacation() {
		return paidVacation;
	}

	public void setPaidVacation(String paidVacation) {
		this.paidVacation = paidVacation;
	}

	public String getAllowsOvertime() {
		return allowsOvertime;
	}

	public void setAllowsOvertime(String allowsOvertime) {
		this.allowsOvertime = allowsOvertime;
	}

	public String getEnableLimitCheck() {
		return enableLimitCheck;
	}

	public void setEnableLimitCheck(String enableLimitCheck) {
		this.enableLimitCheck = enableLimitCheck;
	}

	public String getFixedPeriodYear() {
		return fixedPeriodYear;
	}

	public void setFixedPeriodYear(String fixedPeriodYear) {
		this.fixedPeriodYear = fixedPeriodYear;
	}

	public String getOtherPeriodType() {
		return otherPeriodType;
	}

	public void setOtherPeriodType(String otherPeriodType) {
		this.otherPeriodType = otherPeriodType;
	}

	public String getHolidayFormula() {
		return holidayFormula;
	}

	public void setHolidayFormula(String holidayFormula) {
		this.holidayFormula = holidayFormula;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public AttendanceCategory getAttendanceCategory() {
		return attendanceCategory;
	}

	public void setAttendanceCategory(AttendanceCategory attendanceCategory) {
		this.attendanceCategory = attendanceCategory;
	}

	public Set<AttendanceCategory> getAttendanceCategories() {
		return attendanceCategories;
	}

	public void setAttendanceCategories(Set<AttendanceCategory> attendanceCategories) {
		this.attendanceCategories = attendanceCategories;
	}

}
