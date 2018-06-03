package com.vix.common.share.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.Organization;

/**
 * 计量单位组 编码 名称
 * 
 * @author Administrator
 * 
 */
public class MeasureUnitGroup extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8617453473553727283L;
	/** 类别 */
	private String type;

	private Organization organization;
	private Set<MeasureUnit> measureUnits = new HashSet<MeasureUnit>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<MeasureUnit> getMeasureUnits() {
		return measureUnits;
	}

	public void setMeasureUnits(Set<MeasureUnit> measureUnits) {
		this.measureUnits = measureUnits;
	}

}
