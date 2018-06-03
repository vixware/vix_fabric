package com.vix.mm.settings.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description:设置-工作中心
 * @author 李大鹏
 */
public class WorkCenter extends BaseEntity {

	private static final long serialVersionUID = 3962592455638059415L;
	/** 工作中心编码 */
	private String org;
	/** 工作中心名称 */
	private String orgName;
	/** 每日人工产能  */
	private Double humanCapacity;
	/** 每日机器产能  */
	private Double machineCapacity;
	/** 工作中心效率(标准人工效率) */
	private Double orgEfficiency;
	/** 标准机器负荷  */
	private Double machineLoad;
	/** 制费分摊依据(类型) 0:人时 1:机时2:人工 */
	private String types;
	/** 标准工资率  */
	private Double wageRate;
	/** 标准制费分摊率 */
	private Double systemRate;
	/** 需要装配和操作人数 */
	private String assemblyOperationNumber;
	/** 机器类型 */
	private String machineType;
	/** 每小时人工工资 */
	private String hourlyWages;
	/** 每小时制造费用 */
	private String manufacturingCostPerHour;
	/** 机台类型说明 */
	private String machineTypeDescription;
	/** 部门 */
	private String departments;
	/** WIP仓库 */
	private String wipWarehouse;
	/** 成本中心 */
	private String costCenter;
	/** WIP储位 */
	private String wipStorage;
	/** 位置 */
	private String position;
	/** 预设排队时间 */
	private Double defaultQueueTime;
	/** 每天每班工作时数 */
	private String everyHoursWork;
	/** 预设等待时间 */
	private Double presetWaitTime;
	/** 每天班次数量 */
	private String numberDailyFlights;
	/** 预设搬运时间 */
	private Double handlingTime;
	/** 机器数量或人数 */
	private String machinesNumber;
	/** 建立用户 */
	private String establishmentUser;
	/** 建立日期 */
	private Date creDate;
	/** 预设上班班次 */
	private String presetWorkShifts;
	/** 工作日期 */
	private Date workDate;
	/** 是否生产线 */
	private String whetherProductionLine;

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getAssemblyOperationNumber() {
		return assemblyOperationNumber;
	}

	public void setAssemblyOperationNumber(String assemblyOperationNumber) {
		this.assemblyOperationNumber = assemblyOperationNumber;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getHourlyWages() {
		return hourlyWages;
	}

	public void setHourlyWages(String hourlyWages) {
		this.hourlyWages = hourlyWages;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getManufacturingCostPerHour() {
		return manufacturingCostPerHour;
	}

	public void setManufacturingCostPerHour(String manufacturingCostPerHour) {
		this.manufacturingCostPerHour = manufacturingCostPerHour;
	}

	public String getMachineTypeDescription() {
		return machineTypeDescription;
	}

	public void setMachineTypeDescription(String machineTypeDescription) {
		this.machineTypeDescription = machineTypeDescription;
	}

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
	}

	public String getWipWarehouse() {
		return wipWarehouse;
	}

	public void setWipWarehouse(String wipWarehouse) {
		this.wipWarehouse = wipWarehouse;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getWipStorage() {
		return wipStorage;
	}

	public void setWipStorage(String wipStorage) {
		this.wipStorage = wipStorage;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEveryHoursWork() {
		return everyHoursWork;
	}

	public void setEveryHoursWork(String everyHoursWork) {
		this.everyHoursWork = everyHoursWork;
	}

	public String getNumberDailyFlights() {
		return numberDailyFlights;
	}

	public void setNumberDailyFlights(String numberDailyFlights) {
		this.numberDailyFlights = numberDailyFlights;
	}

	public String getMachinesNumber() {
		return machinesNumber;
	}

	public void setMachinesNumber(String machinesNumber) {
		this.machinesNumber = machinesNumber;
	}

	public String getEstablishmentUser() {
		return establishmentUser;
	}

	public void setEstablishmentUser(String establishmentUser) {
		this.establishmentUser = establishmentUser;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Date getCreDate() {
		return creDate;
	}
	
	public String getCreDateStr() {
		if(creDate != null){
			return DateUtil.format(creDate);
		}
		return "";
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public String getPresetWorkShifts() {
		return presetWorkShifts;
	}

	public void setPresetWorkShifts(String presetWorkShifts) {
		this.presetWorkShifts = presetWorkShifts;
	}

	public Date getWorkDate() {
		return workDate;
	}
	
	public String getWorkDateStr() {
		if(workDate != null){
			return DateUtil.format(workDate);
		}
		return "";
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getWhetherProductionLine() {
		return whetherProductionLine;
	}

	public void setWhetherProductionLine(String whetherProductionLine) {
		this.whetherProductionLine = whetherProductionLine;
	}

	public Double getDefaultQueueTime() {
		return defaultQueueTime;
	}

	public void setDefaultQueueTime(Double defaultQueueTime) {
		this.defaultQueueTime = defaultQueueTime;
	}

	public Double getPresetWaitTime() {
		return presetWaitTime;
	}

	public void setPresetWaitTime(Double presetWaitTime) {
		this.presetWaitTime = presetWaitTime;
	}

	public Double getHandlingTime() {
		return handlingTime;
	}

	public void setHandlingTime(Double handlingTime) {
		this.handlingTime = handlingTime;
	}

	public Double getHumanCapacity() {
		return humanCapacity;
	}

	public void setHumanCapacity(Double humanCapacity) {
		this.humanCapacity = humanCapacity;
	}

	public Double getMachineCapacity() {
		return machineCapacity;
	}

	public void setMachineCapacity(Double machineCapacity) {
		this.machineCapacity = machineCapacity;
	}

	public Double getOrgEfficiency() {
		return orgEfficiency;
	}

	public void setOrgEfficiency(Double orgEfficiency) {
		this.orgEfficiency = orgEfficiency;
	}

	public Double getMachineLoad() {
		return machineLoad;
	}

	public void setMachineLoad(Double machineLoad) {
		this.machineLoad = machineLoad;
	}

	public Double getWageRate() {
		return wageRate;
	}

	public void setWageRate(Double wageRate) {
		this.wageRate = wageRate;
	}

	public Double getSystemRate() {
		return systemRate;
	}

	public void setSystemRate(Double systemRate) {
		this.systemRate = systemRate;
	}

}
