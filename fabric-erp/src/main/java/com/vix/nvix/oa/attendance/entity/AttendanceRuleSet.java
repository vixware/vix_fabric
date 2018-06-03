/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import java.util.Date;

import com.vix.common.org.entity.Organization;
import com.vix.common.properties.Utils;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         班次设定
 * 
 */
public class AttendanceRuleSet extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 时间段 */
	private PeriodRule periodRule;
	/** 班次名称 */
	private String shiftName;
	/** 班次编码 */
	private String shiftCode;
	/** 班次类型 */
	private String shiftType;
	/** 班次性质 */
	private String shiftNature;
	/** 适用所属公司 */
	private Organization org;
	/** 是否启用 */
	private Integer isEnable;
	/** 上班时间是否免刷卡 */
	private Integer workShiftFreeCard;
	/** 下班时间是否免刷卡 */
	private Integer overTimeFreeCard;
	/** 是否提前加班 */
	private Integer overtimeInAdvance;
	/** 是否推迟加班 */
	private Integer delayedOvertime;
	/** 允许推迟时间(分钟) */
	private Integer delayedTimes;
	/** 启用时间 */
	private Date openedDate;
	/** 周期数 */
	private Integer cycle;
	/** 周期单位 */
	private Integer decSize;
	/** 执行日 */
	private String exerciseDate;
	/** 基本规则 */
	private BasicRule basicRule;
	/** 加班规则 */
	private OverTimeRule overTimeRule;
	/** 自动套班 */
	private AutomaticRule automaticRule;
	/** 计算方式 */
	private CalculationRule calculationRule;
	/** 其它规则 */
	private OtherRule otherRule;

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	public String getShiftNature() {
		return shiftNature;
	}

	public void setShiftNature(String shiftNature) {
		this.shiftNature = shiftNature;
	}

	public BasicRule getBasicRule() {
		return basicRule;
	}

	public void setBasicRule(BasicRule basicRule) {
		this.basicRule = basicRule;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public PeriodRule getPeriodRule() {
		return periodRule;
	}

	public void setPeriodRule(PeriodRule periodRule) {
		this.periodRule = periodRule;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getWorkShiftFreeCard() {
		return workShiftFreeCard;
	}

	public void setWorkShiftFreeCard(Integer workShiftFreeCard) {
		this.workShiftFreeCard = workShiftFreeCard;
	}

	public Integer getOverTimeFreeCard() {
		return overTimeFreeCard;
	}

	public void setOverTimeFreeCard(Integer overTimeFreeCard) {
		this.overTimeFreeCard = overTimeFreeCard;
	}

	public Integer getOvertimeInAdvance() {
		return overtimeInAdvance;
	}

	public void setOvertimeInAdvance(Integer overtimeInAdvance) {
		this.overtimeInAdvance = overtimeInAdvance;
	}

	public Integer getDelayedOvertime() {
		return delayedOvertime;
	}

	public void setDelayedOvertime(Integer delayedOvertime) {
		this.delayedOvertime = delayedOvertime;
	}

	public Integer getDelayedTimes() {
		return delayedTimes;
	}

	public void setDelayedTimes(Integer delayedTimes) {
		this.delayedTimes = delayedTimes;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public String getOpenedDateStr() {
		if (Utils.isNotEmpty(openedDate)) {
			return DateUtil.format(openedDate);
		}
		return "";
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getDecSize() {
		return decSize;
	}

	public void setDecSize(Integer decSize) {
		this.decSize = decSize;
	}

	public String getExerciseDate() {
		return exerciseDate;
	}

	public void setExerciseDate(String exerciseDate) {
		this.exerciseDate = exerciseDate;
	}

	public OverTimeRule getOverTimeRule() {
		return overTimeRule;
	}

	public void setOverTimeRule(OverTimeRule overTimeRule) {
		this.overTimeRule = overTimeRule;
	}

	public AutomaticRule getAutomaticRule() {
		return automaticRule;
	}

	public void setAutomaticRule(AutomaticRule automaticRule) {
		this.automaticRule = automaticRule;
	}

	public CalculationRule getCalculationRule() {
		return calculationRule;
	}

	public void setCalculationRule(CalculationRule calculationRule) {
		this.calculationRule = calculationRule;
	}

	public OtherRule getOtherRule() {
		return otherRule;
	}

	public void setOtherRule(OtherRule otherRule) {
		this.otherRule = otherRule;
	}

}
