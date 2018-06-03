package com.vix.oa.adminMg.requisitionCar.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.adminMg.typeSettings.entity.DisplacementType;
import com.vix.oa.adminMg.typeSettings.entity.EngineType;
import com.vix.oa.adminMg.typeSettings.entity.TransmissionType;
import com.vix.oa.adminMg.typeSettings.entity.VehicleColor;
import com.vix.oa.adminMg.typeSettings.entity.VehicleType;
/**
 * 
 * @ClassName: VehicleRequest
 * @Description: 车辆申请/登记 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-5 下午5:59:24
 */
public class VehicleRequest extends BaseEntity {

	private static final long serialVersionUID = 375069367357967245L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 子分类集合 */
	private Set<VehicleRequest> subCategorys = new HashSet<VehicleRequest>();
	/** 父分类 */
	private VehicleRequest parentCategory;
	
	/** 车辆类型  */
	private VehicleType vehicleType;
	
	/** 车辆颜色  */
	private VehicleColor vehicleColor;
	
	/** 变速器类型  */
	private TransmissionType transmissionType;
	
	/** 引擎类型  */
	private EngineType engineType;
	
	/** 排量类型  */
	private DisplacementType displacementType;
	
	/** 车辆维护*/
	private Set<CarMaintenance> carMaintenance = new HashSet<CarMaintenance>();
	
	/** 车辆油耗*/
	private Set<Tpk> tpk = new HashSet<Tpk>();
	
	/** 车辆申请管理*/
	private Set<CarUse> carUse = new HashSet<CarUse>();
	
	/** 车辆类型 */
	private String type;
	/** 车辆型号 */
	private String model;
	/** 车牌号 */
	private String plateNumber;
	/** 发动机号 */
	private String engineNumber;
	/** 购买价格 */
	private Double price;
	/** 购置日期 */
	private Date acquiredDate;
	/** 驾驶员 */
	private String driver;
	
	public Integer carSituation;
	/** 驾驶员手机 */
	private String mobile;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getAcquiredDate() {
		return acquiredDate;
	}

	public void setAcquiredDate(Date acquiredDate) {
		this.acquiredDate = acquiredDate;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<VehicleRequest> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(Set<VehicleRequest> subCategorys) {
		this.subCategorys = subCategorys;
	}

	public VehicleRequest getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(VehicleRequest parentCategory) {
		this.parentCategory = parentCategory;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleColor getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(VehicleColor vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public TransmissionType getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(TransmissionType transmissionType) {
		this.transmissionType = transmissionType;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}

	public DisplacementType getDisplacementType() {
		return displacementType;
	}

	public void setDisplacementType(DisplacementType displacementType) {
		this.displacementType = displacementType;
	}

	public Set<CarMaintenance> getCarMaintenance() {
		return carMaintenance;
	}

	public void setCarMaintenance(Set<CarMaintenance> carMaintenance) {
		this.carMaintenance = carMaintenance;
	}

	public Set<Tpk> getTpk() {
		return tpk;
	}

	public void setTpk(Set<Tpk> tpk) {
		this.tpk = tpk;
	}

	public Set<CarUse> getCarUse() {
		return carUse;
	}

	public void setCarUse(Set<CarUse> carUse) {
		this.carUse = carUse;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public Integer getCarSituation() {
		return carSituation;
	}

	public void setCarSituation(Integer carSituation) {
		this.carSituation = carSituation;
	}

}
