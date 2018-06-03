package com.vix.oa.adminMg.typeSettings.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.adminMg.requisitionCar.entity.CarMaintenance;

/**
 * 
 * @ClassName: MaintenanceType
 * @Description: 维护类型 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-3 下午5:06:07
 */
public class MaintenanceType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	private String disabled;
	
	/** 车辆维护*/
	private Set<CarMaintenance> carMaintenance = new HashSet<CarMaintenance>();
	
	public MaintenanceType(){}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Set<CarMaintenance> getCarMaintenance() {
		return carMaintenance;
	}

	public void setCarMaintenance(Set<CarMaintenance> carMaintenance) {
		this.carMaintenance = carMaintenance;
	}

}
