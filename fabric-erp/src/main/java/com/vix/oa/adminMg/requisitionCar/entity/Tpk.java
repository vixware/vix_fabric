package com.vix.oa.adminMg.requisitionCar.entity;

import com.vix.common.share.entity.BaseEntity;
/**
 * 
 * @ClassName: CarMaintenance
 * @Description: 车辆维护 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-3 下午5:11:20
 */
public class Tpk extends BaseEntity {

	private static final long serialVersionUID = 375069367357967245L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 车辆牌号  */
	private VehicleRequest vehicleRequest;
	/** 里程数（公里）*/
	private String mileage;
	/** 油耗数（升）*/
	private String guzzling;
	/** 油耗数（升/公里）*/
	private String guzzlingNumber;
	/**司机姓名*/
	private String carname;

	
	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getGuzzling() {
		return guzzling;
	}

	public void setGuzzling(String guzzling) {
		this.guzzling = guzzling;
	}

	public String getGuzzlingNumber() {
		return guzzlingNumber;
	}

	public void setGuzzlingNumber(String guzzlingNumber) {
		this.guzzlingNumber = guzzlingNumber;
	}

	public VehicleRequest getVehicleRequest() {
		return vehicleRequest;
	}

	public void setVehicleRequest(VehicleRequest vehicleRequest) {
		this.vehicleRequest = vehicleRequest;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

}
