package com.vix.common.orginialMeta.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: OrginialMeasureUnit
 * @Description: 计量单位 （编码 名称 备注）
 * @author wangmingchen
 * @date 2014年11月1日 上午9:53:46
 */
public class OrginialMeasureUnit extends BaseEntity {

	private static final long serialVersionUID = -4275424169434701709L;

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
	 * code ,name ,memo,
	 */

	/** 计量单位组 */
	private OrginialMeasureUnitGroup orginialMeasureUnitGroup;

	public OrginialMeasureUnit() {
		super();
	}

	public OrginialMeasureUnit(String id) {
		super();
		setId(id);
	}

	public String getIsMeasurementUnit() {
		return isMeasurementUnit;
	}

	public void setIsMeasurementUnit(String isMeasurementUnit) {
		this.isMeasurementUnit = isMeasurementUnit;
	}

	public String getEnglishCode() {
		return englishCode;
	}

	public void setEnglishCode(String englishCode) {
		this.englishCode = englishCode;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public OrginialMeasureUnitGroup getOrginialMeasureUnitGroup() {
		return orginialMeasureUnitGroup;
	}

	public void setOrginialMeasureUnitGroup(OrginialMeasureUnitGroup orginialMeasureUnitGroup) {
		this.orginialMeasureUnitGroup = orginialMeasureUnitGroup;
	}

}
