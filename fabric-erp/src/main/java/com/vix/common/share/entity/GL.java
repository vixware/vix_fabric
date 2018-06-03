package com.vix.common.share.entity;


/**
 * 标准计量单位
 * @author Administrator
 *
 */
public class GL extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 计量单位编码 */
	private String measurementUnitCode;
	/** 计量单位 */
	private String measurementUnit;

	public GL(){}

	public String getMeasurementUnitCode() {
		return measurementUnitCode;
	}

	public void setMeasurementUnitCode(String measurementUnitCode) {
		this.measurementUnitCode = measurementUnitCode;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
}
