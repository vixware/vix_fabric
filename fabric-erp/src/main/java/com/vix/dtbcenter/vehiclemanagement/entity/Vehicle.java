/**
 * 
 */
package com.vix.dtbcenter.vehiclemanagement.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 车辆
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class Vehicle extends BaseEntity {
	private static final long serialVersionUID = -8937332646806202375L;
	/** 车辆自编号 */
	private String vehicleCode;
	/** 车辆类型 */
	private String type;
	/** 车牌号 */
	private String vehicleNO;
	/** 主要驾驶员 */
	private String driver;
	/** 驾驶员电话 */
	private String driverTelephone;
	/** 车辆属性 */
	private String vehiclePropertyies;
	/** 车辆状态 */
	private String vehicleStauts;
	/** 调度状态 */
	private String scheduleStatus;
	/** 登记机构 */
	private String register;
	/** 所属承运商 */
	private String carrier;
	/** 使用年限 */
	private String useYear;
	/** 行驶里程上限 */
	private String mileageTopLimit;
	/** 里程表读数 */
	private String odometerReading;
	/** 百公里油耗 */
	private String oilWearPerHundredKM;
	/** 重车百公里油耗 */
	private String oilWearWithHeavy;
	/** 轻车百公里油耗 */
	private String oilWearWithEmpty;
	/** 制造商 */
	private String producer;
	/** 出厂日期 */
	private String dateOfManufacture;
	/** 型号 */
	private String model;
	/** 发动机号 */
	private String engineCode;
	/** 底盘号 */
	private String chassisNumber;
	/** 车架号 */
	private String frameNumber;
	/** 上牌日期 */
	private Date licensePlateDate;
	/** 建档日期 */
	private Date documnetDate;
	/** 购买日期 */
	private Date purchaseDate;
	/** 货保金额 */
	private Double cargoIinsurance;
	/** 货保生效日期 */
	private Date cargoIinsuranceValidDate;
	/** 货保终止日期 */
	private Date cargoIinsuranceInvalidDate;
	/** 油箱封号 */
	private String sealNumberOfTank;
	/** 铅封人 */
	private String leadSealOperator;
	/** 铅封日期 */
	private Date sealDate;
	/** 油箱铅封备注 */
	private String sealMemo;
	/** 载重 */
	private Double carryingcapacity;
	/* 重量单位 */
	private String carryingcapacityUnit;
	/** 车长 */
	private Double vehicleLength;
	/* 车长单位 */
	private String vehicleLengthUnit;
	/** 车宽 */
	private Double vehicleWidth;
	/* 车宽单位 */
	private String vehicleWidthUnit;
	/** 车高 */
	private Double vehicleHeight;
	/* 车高单位 */
	private String vehicleHeightUnit;
	/** 容量 */
	private Double capacity;
	/* 容量单位 */
	private String capacityUnit;
	/** 保险登记 */
	private Set<InsuranceRegister> insuranceRegisters = new HashSet<InsuranceRegister>();
	/** 安检登记 */
	private Set<SaftRegister> saftRegisters = new HashSet<SaftRegister>();
	/** 理赔 */
	private Set<SettlementOfClaim> settlementOfClaims = new HashSet<SettlementOfClaim>();
	/** 运输事故 */
	private Set<TransportationAccident> transportationAccidents = new HashSet<TransportationAccident>();
	/** 维修保养 */
	private Set<VehicleMaintance> vehicleMaintances = new HashSet<VehicleMaintance>();
	/** 车辆跟踪 */
	private Set<ViechleTrace> viechleTraces = new HashSet<ViechleTrace>();

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVehicleNO() {
		return vehicleNO;
	}

	public void setVehicleNO(String vehicleNO) {
		this.vehicleNO = vehicleNO;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriverTelephone() {
		return driverTelephone;
	}

	public void setDriverTelephone(String driverTelephone) {
		this.driverTelephone = driverTelephone;
	}

	public String getVehiclePropertyies() {
		return vehiclePropertyies;
	}

	public void setVehiclePropertyies(String vehiclePropertyies) {
		this.vehiclePropertyies = vehiclePropertyies;
	}

	public String getVehicleStauts() {
		return vehicleStauts;
	}

	public void setVehicleStauts(String vehicleStauts) {
		this.vehicleStauts = vehicleStauts;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getUseYear() {
		return useYear;
	}

	public void setUseYear(String useYear) {
		this.useYear = useYear;
	}

	public String getMileageTopLimit() {
		return mileageTopLimit;
	}

	public void setMileageTopLimit(String mileageTopLimit) {
		this.mileageTopLimit = mileageTopLimit;
	}

	public String getOdometerReading() {
		return odometerReading;
	}

	public void setOdometerReading(String odometerReading) {
		this.odometerReading = odometerReading;
	}

	public String getOilWearPerHundredKM() {
		return oilWearPerHundredKM;
	}

	public void setOilWearPerHundredKM(String oilWearPerHundredKM) {
		this.oilWearPerHundredKM = oilWearPerHundredKM;
	}

	public String getOilWearWithHeavy() {
		return oilWearWithHeavy;
	}

	public void setOilWearWithHeavy(String oilWearWithHeavy) {
		this.oilWearWithHeavy = oilWearWithHeavy;
	}

	public String getOilWearWithEmpty() {
		return oilWearWithEmpty;
	}

	public void setOilWearWithEmpty(String oilWearWithEmpty) {
		this.oilWearWithEmpty = oilWearWithEmpty;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(String dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEngineCode() {
		return engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public Date getLicensePlateDate() {
		return licensePlateDate;
	}

	public void setLicensePlateDate(Date licensePlateDate) {
		this.licensePlateDate = licensePlateDate;
	}

	public Date getDocumnetDate() {
		return documnetDate;
	}

	public void setDocumnetDate(Date documnetDate) {
		this.documnetDate = documnetDate;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getCargoIinsurance() {
		return cargoIinsurance;
	}

	public void setCargoIinsurance(Double cargoIinsurance) {
		this.cargoIinsurance = cargoIinsurance;
	}

	public Date getCargoIinsuranceValidDate() {
		return cargoIinsuranceValidDate;
	}

	public void setCargoIinsuranceValidDate(Date cargoIinsuranceValidDate) {
		this.cargoIinsuranceValidDate = cargoIinsuranceValidDate;
	}

	public Date getCargoIinsuranceInvalidDate() {
		return cargoIinsuranceInvalidDate;
	}

	public void setCargoIinsuranceInvalidDate(Date cargoIinsuranceInvalidDate) {
		this.cargoIinsuranceInvalidDate = cargoIinsuranceInvalidDate;
	}

	public String getSealNumberOfTank() {
		return sealNumberOfTank;
	}

	public void setSealNumberOfTank(String sealNumberOfTank) {
		this.sealNumberOfTank = sealNumberOfTank;
	}

	public String getLeadSealOperator() {
		return leadSealOperator;
	}

	public void setLeadSealOperator(String leadSealOperator) {
		this.leadSealOperator = leadSealOperator;
	}

	public Date getSealDate() {
		return sealDate;
	}

	public void setSealDate(Date sealDate) {
		this.sealDate = sealDate;
	}

	public String getSealMemo() {
		return sealMemo;
	}

	public void setSealMemo(String sealMemo) {
		this.sealMemo = sealMemo;
	}

	public Double getCarryingcapacity() {
		return carryingcapacity;
	}

	public void setCarryingcapacity(Double carryingcapacity) {
		this.carryingcapacity = carryingcapacity;
	}

	public Double getVehicleLength() {
		return vehicleLength;
	}

	public void setVehicleLength(Double vehicleLength) {
		this.vehicleLength = vehicleLength;
	}

	public Double getVehicleWidth() {
		return vehicleWidth;
	}

	public void setVehicleWidth(Double vehicleWidth) {
		this.vehicleWidth = vehicleWidth;
	}

	public Double getVehicleHeight() {
		return vehicleHeight;
	}

	public void setVehicleHeight(Double vehicleHeight) {
		this.vehicleHeight = vehicleHeight;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public String getCarryingcapacityUnit() {
		return carryingcapacityUnit;
	}

	public void setCarryingcapacityUnit(String carryingcapacityUnit) {
		this.carryingcapacityUnit = carryingcapacityUnit;
	}

	public String getVehicleLengthUnit() {
		return vehicleLengthUnit;
	}

	public void setVehicleLengthUnit(String vehicleLengthUnit) {
		this.vehicleLengthUnit = vehicleLengthUnit;
	}

	public String getVehicleWidthUnit() {
		return vehicleWidthUnit;
	}

	public void setVehicleWidthUnit(String vehicleWidthUnit) {
		this.vehicleWidthUnit = vehicleWidthUnit;
	}

	public String getVehicleHeightUnit() {
		return vehicleHeightUnit;
	}

	public void setVehicleHeightUnit(String vehicleHeightUnit) {
		this.vehicleHeightUnit = vehicleHeightUnit;
	}

	public String getCapacityUnit() {
		return capacityUnit;
	}

	public void setCapacityUnit(String capacityUnit) {
		this.capacityUnit = capacityUnit;
	}

	public Set<InsuranceRegister> getInsuranceRegisters() {
		return insuranceRegisters;
	}

	public void setInsuranceRegisters(Set<InsuranceRegister> insuranceRegisters) {
		this.insuranceRegisters = insuranceRegisters;
	}

	public Set<SaftRegister> getSaftRegisters() {
		return saftRegisters;
	}

	public void setSaftRegisters(Set<SaftRegister> saftRegisters) {
		this.saftRegisters = saftRegisters;
	}

	public Set<SettlementOfClaim> getSettlementOfClaims() {
		return settlementOfClaims;
	}

	public void setSettlementOfClaims(Set<SettlementOfClaim> settlementOfClaims) {
		this.settlementOfClaims = settlementOfClaims;
	}

	public Set<TransportationAccident> getTransportationAccidents() {
		return transportationAccidents;
	}

	public void setTransportationAccidents(Set<TransportationAccident> transportationAccidents) {
		this.transportationAccidents = transportationAccidents;
	}

	public Set<VehicleMaintance> getVehicleMaintances() {
		return vehicleMaintances;
	}

	public void setVehicleMaintances(Set<VehicleMaintance> vehicleMaintances) {
		this.vehicleMaintances = vehicleMaintances;
	}

	public Set<ViechleTrace> getViechleTraces() {
		return viechleTraces;
	}

	public void setViechleTraces(Set<ViechleTrace> viechleTraces) {
		this.viechleTraces = viechleTraces;
	}

}
