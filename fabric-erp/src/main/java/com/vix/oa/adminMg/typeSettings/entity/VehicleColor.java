package com.vix.oa.adminMg.typeSettings.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.adminMg.requisitionCar.entity.VehicleRequest;

/**
 * 
 * @ClassName: vehicleColor
 * @Description: 车辆颜色 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-6 上午10:59:05
 */
public class VehicleColor extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	private String disabled;
	
	/** 车辆申请/登记*/
	private Set<VehicleRequest> vehicleRequest = new HashSet<VehicleRequest>();
	
	public VehicleColor(){}

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

	public Set<VehicleRequest> getVehicleRequest() {
		return vehicleRequest;
	}

	public void setVehicleRequest(Set<VehicleRequest> vehicleRequest) {
		this.vehicleRequest = vehicleRequest;
	}

}
