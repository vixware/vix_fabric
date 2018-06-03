package com.vix.common.share.entity;


/**
 * 标准计量单位转换关系
 * @author Administrator
 *
 */
public class Transfer extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 计量单位编码 */
	private String measurementUnitCode;
	/** 计量单位 */
	private String measurementUnit;
	/** 转换量值(换算比率) */
	private Double exchageValue;
	/** 目标计量单位编码 */
	private String targetMeasurementUnitCode;
	/** 目标计量单位 */
	private String targetMeasurementUnit;
	/** 小数位数 */
	private Integer decimalDigits;
	/** 尾数处理方式 */
	private String mantissaType;
	 
	public Transfer(){}

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

	public Double getExchageValue() {
		return exchageValue;
	}

	public void setExchageValue(Double exchageValue) {
		this.exchageValue = exchageValue;
	}

	public String getTargetMeasurementUnitCode() {
		return targetMeasurementUnitCode;
	}

	public void setTargetMeasurementUnitCode(String targetMeasurementUnitCode) {
		this.targetMeasurementUnitCode = targetMeasurementUnitCode;
	}

	public String getTargetMeasurementUnit() {
		return targetMeasurementUnit;
	}

	public void setTargetMeasurementUnit(String targetMeasurementUnit) {
		this.targetMeasurementUnit = targetMeasurementUnit;
	}

	public Integer getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(Integer decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public String getMantissaType() {
		return mantissaType;
	}

	public void setMantissaType(String mantissaType) {
		this.mantissaType = mantissaType;
	}
}
