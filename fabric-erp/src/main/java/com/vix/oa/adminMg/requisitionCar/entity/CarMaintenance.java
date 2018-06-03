package com.vix.oa.adminMg.requisitionCar.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.adminMg.typeSettings.entity.MaintenanceType;
/**
 * 
 * @ClassName: CarMaintenance
 * @Description: 车辆维护 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-3 下午5:11:20
 */
public class CarMaintenance extends BaseEntity {

	private static final long serialVersionUID = 375069367357967245L;
	
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 子分类集合 */
	private Set<CarMaintenance> subCategorys = new HashSet<CarMaintenance>();
	/** 父分类 */
	private CarMaintenance parentCategory;
	/** 车辆牌号  */
	private VehicleRequest vehicleRequest;
	/** 维护类型  */
	private MaintenanceType maintenanceType;
	
	/** 维护日期 */
	private Date maintenanceDate;
	/** 维护原因 */
	private String maintenanceReason;
	/** 维护情况 */
	private String maintenance;
	/**  经办人id */
	private String uploadPersonId;	
	/** id 经办人 */
	private String uploadPerson;
	/** 经办人 */
	private String uploadPersonName;
	/** 维护费用 */
	private Double maintenanceCost;
	/** 备注 */
	private String remark;


	public Date getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getMaintenanceReason() {
		return maintenanceReason;
	}

	public void setMaintenanceReason(String maintenanceReason) {
		this.maintenanceReason = maintenanceReason;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public Double getMaintenanceCost() {
		return maintenanceCost;
	}

	public void setMaintenanceCost(Double maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<CarMaintenance> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(Set<CarMaintenance> subCategorys) {
		this.subCategorys = subCategorys;
	}

	public CarMaintenance getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(CarMaintenance parentCategory) {
		this.parentCategory = parentCategory;
	}

	public VehicleRequest getVehicleRequest() {
		return vehicleRequest;
	}

	public void setVehicleRequest(VehicleRequest vehicleRequest) {
		this.vehicleRequest = vehicleRequest;
	}

	public MaintenanceType getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(MaintenanceType maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}

}
