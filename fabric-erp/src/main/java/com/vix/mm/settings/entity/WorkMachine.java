package com.vix.mm.settings.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description:工作中心机器
 */
public class WorkMachine extends BaseEntity {

	private static final long serialVersionUID = 7345986234012512068L;
	/** 机器编号 */
	private String machineCode;
	/** 机器名称 */
	private String machineName;
	/** 机器产能 */
	private Double machineCapacity;
	/** 负荷率 */
	private Double loadRate;
	/** 工作中心 */
	private WorkCenter workCenter;

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public Double getMachineCapacity() {
		return machineCapacity;
	}

	public void setMachineCapacity(Double machineCapacity) {
		this.machineCapacity = machineCapacity;
	}

	public Double getLoadRate() {
		return loadRate;
	}

	public void setLoadRate(Double loadRate) {
		this.loadRate = loadRate;
	}

	public WorkCenter getWorkCenter() {
		return workCenter;
	}

	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}

}
