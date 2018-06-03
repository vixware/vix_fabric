package com.vix.common.share.entity;

/**
 * 计量单位 （编码 名称 备注） com.vix.common.share.entity.MeasureUnit
 *
 * @author zhanghaibing
 *
 * @date 2014年9月9日
 */

public class MeasureUnit extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 是否主计量单位 */
	private String isMeasurementUnit;
	/**
	 * 英文名称
	 */
	private String englishCode;
	/**
	 * 对应条形码
	 */
	private String barCode;
	/** 换算率 */
	private Double rate;

	/**
	 * 计量单位组
	 */
	private MeasureUnitGroup measureUnitGroup;

	/**
	 * 
	 */
	public MeasureUnit() {
		super();
	}

	public MeasureUnit(String id) {
		super();
		setId(id);
	}

	public MeasureUnitGroup getMeasureUnitGroup() {
		return measureUnitGroup;
	}

	public void setMeasureUnitGroup(MeasureUnitGroup measureUnitGroup) {
		this.measureUnitGroup = measureUnitGroup;
	}

	public String getIsMeasurementUnit() {
		return isMeasurementUnit;
	}

	public void setIsMeasurementUnit(String isMeasurementUnit) {
		this.isMeasurementUnit = isMeasurementUnit;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getEnglishCode() {
		return englishCode;
	}

	public void setEnglishCode(String englishCode) {
		this.englishCode = englishCode;
	}

}
