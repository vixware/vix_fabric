package com.vix.dtbcenter.deliveryplan.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送计划
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DeliveryPlan extends BaseEntity {
	private static final long serialVersionUID = -2930661277204196189L;
	/** 计划编码 */
	private String dpCode;
	/** 配送时间 */
	private Date deliveryPlanTime;
	/** 配送车号 */
	private String deliveryVehicleNumber;
	/** 车辆信息 */
	private String vehicleInfo;
	/** 明细 */
	private String detail;
	/** 删除标志 */
	private String isDeleted;
	/** 单据日期 */
	private Date billDate;
	/** 成组编码 */
	private String groupCode;
	/** 业务员编码 */
	private String salePersonCode;
	/** 提货时间 */
	private Date loadTime;
	/** 发车时间 */
	private Date vehicleDeliveryTime;
	/** 运输负责人 */
	private String transferCharget;

	public String getDpCode() {
		return dpCode;
	}

	public void setDpCode(String dpCode) {
		this.dpCode = dpCode;
	}

	public Date getDeliveryPlanTime() {
		return deliveryPlanTime;
	}

	public void setDeliveryPlanTime(Date deliveryPlanTime) {
		this.deliveryPlanTime = deliveryPlanTime;
	}

	public String getDeliveryVehicleNumber() {
		return deliveryVehicleNumber;
	}

	public void setDeliveryVehicleNumber(String deliveryVehicleNumber) {
		this.deliveryVehicleNumber = deliveryVehicleNumber;
	}

	public String getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(String vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String getIsDeleted() {
		return isDeleted;
	}

	@Override
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getSalePersonCode() {
		return salePersonCode;
	}

	public void setSalePersonCode(String salePersonCode) {
		this.salePersonCode = salePersonCode;
	}

	public Date getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(Date loadTime) {
		this.loadTime = loadTime;
	}

	public Date getVehicleDeliveryTime() {
		return vehicleDeliveryTime;
	}

	public void setVehicleDeliveryTime(Date vehicleDeliveryTime) {
		this.vehicleDeliveryTime = vehicleDeliveryTime;
	}

	public String getTransferCharget() {
		return transferCharget;
	}

	public void setTransferCharget(String transferCharget) {
		this.transferCharget = transferCharget;
	}

}
