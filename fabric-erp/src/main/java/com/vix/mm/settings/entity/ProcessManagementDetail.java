package com.vix.mm.settings.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description:设置-工序管理明细
 * @author 李大鹏
 */
public class ProcessManagementDetail extends BaseEntity {

	private static final long serialVersionUID = -3638213613154990155L;
	/** 行号 */
	private String lineNumber;
	/** 资源编码 */
	private String resourceCode;
	/** 资源名称 */
	private String resourceName;
	/** 资源活动 */
	private String resourceActivities;
	/** 基础类型 */
	private String baTypes;
	/** 工时 */
	private String workingHours;
	/** 工时(分子) */
	private String manHourMolecules;
	/** 工时(分母) */
	private String workingHoursDenominator;
	/** 效率 */
	private String efficiency;
	/** 是否计划 */
	private String whetherPlan;
	/** 计费类型 */
	private String chargingTypes;
	/** 工序管理 */
	private ProcessManagement processManagement;

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceActivities() {
		return resourceActivities;
	}

	public void setResourceActivities(String resourceActivities) {
		this.resourceActivities = resourceActivities;
	}

	public String getBaTypes() {
		return baTypes;
	}

	public void setBaTypes(String baTypes) {
		this.baTypes = baTypes;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getManHourMolecules() {
		return manHourMolecules;
	}

	public void setManHourMolecules(String manHourMolecules) {
		this.manHourMolecules = manHourMolecules;
	}

	public String getWorkingHoursDenominator() {
		return workingHoursDenominator;
	}

	public void setWorkingHoursDenominator(String workingHoursDenominator) {
		this.workingHoursDenominator = workingHoursDenominator;
	}

	public String getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(String efficiency) {
		this.efficiency = efficiency;
	}

	public String getWhetherPlan() {
		return whetherPlan;
	}

	public void setWhetherPlan(String whetherPlan) {
		this.whetherPlan = whetherPlan;
	}

	public String getChargingTypes() {
		return chargingTypes;
	}

	public void setChargingTypes(String chargingTypes) {
		this.chargingTypes = chargingTypes;
	}

	public ProcessManagement getProcessManagement() {
		return processManagement;
	}

	public void setProcessManagement(ProcessManagement processManagement) {
		this.processManagement = processManagement;
	}

}
