package com.vix.oa.adminMg.conference.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: MeetingDevice
 * @Description: 会议设备管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-6 下午3:06:26
 */
public class MeetingDevice extends BaseEntity {

	private static final long serialVersionUID = 87031862813442337L;

	/** 设备编号 */
	public String deviceCode;

	/** 设备名称/型号 */
	public String deviceName;
	
	/** 设备状态 0 可用 1 不可用*/
	public Integer deviceStatus;
	
	/** 同类设备 0 没有 1有*/
	public Integer similarEquipment;

	/** 设备描述 */
	public String deviceDescribe;

	/** 子分类集合 */
	private Set<MeetingDevice> subCategorys = new HashSet<MeetingDevice>();

	/** 父分类 */
	private MeetingDevice parentCategory;
	
	/** 申请会议*/
	private Set<ApplicationMg> applicationMg = new HashSet<ApplicationMg>();
	
	/** 设备明细*/
	private Set<EquipmentDetails> equipmentDetails = new HashSet<EquipmentDetails>();


	public MeetingDevice() {
		super();
	}

	public MeetingDevice(String id) {
		super();
		setId(id);
	}

	public Set<MeetingDevice> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(Set<MeetingDevice> subCategorys) {
		this.subCategorys = subCategorys;
	}

	public MeetingDevice getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(MeetingDevice parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Integer getSimilarEquipment() {
		return similarEquipment;
	}

	public void setSimilarEquipment(Integer similarEquipment) {
		this.similarEquipment = similarEquipment;
	}

	public String getDeviceDescribe() {
		return deviceDescribe;
	}

	public void setDeviceDescribe(String deviceDescribe) {
		this.deviceDescribe = deviceDescribe;
	}

	public Set<ApplicationMg> getApplicationMg() {
		return applicationMg;
	}

	public void setApplicationMg(Set<ApplicationMg> applicationMg) {
		this.applicationMg = applicationMg;
	}

	public Set<EquipmentDetails> getEquipmentDetails() {
		return equipmentDetails;
	}

	public void setEquipmentDetails(Set<EquipmentDetails> equipmentDetails) {
		this.equipmentDetails = equipmentDetails;
	}
}
