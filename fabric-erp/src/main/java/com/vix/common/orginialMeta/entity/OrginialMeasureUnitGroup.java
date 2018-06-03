package com.vix.common.orginialMeta.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: MeasureUnitGroup
 * @Description: 计量单位组 编码 名称
 * @author wangmingchen
 * @date 2014年11月1日 上午9:48:23
 */
public class OrginialMeasureUnitGroup extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	/**
	 * code name
	 */
	private Set<OrginialMeasureUnit> orginialMeasureUnits = new HashSet<OrginialMeasureUnit>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<OrginialMeasureUnit> getOrginialMeasureUnits() {
		return orginialMeasureUnits;
	}

	public void setOrginialMeasureUnits(Set<OrginialMeasureUnit> orginialMeasureUnits) {
		this.orginialMeasureUnits = orginialMeasureUnits;
	}

}
