package com.vix.common.share.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 国家
 * @author Administrator
 *
 */
public class BaseCountry extends BaseEntity {
 
	private static final long serialVersionUID = 1L;
	/** 地区 */
	private Set<BaseArea> baseAreas = new HashSet<BaseArea>();
	
	public BaseCountry(){}

	public Set<BaseArea> getBaseAreas() {
		return baseAreas;
	}

	public void setBaseAreas(Set<BaseArea> baseAreas) {
		this.baseAreas = baseAreas;
	}
}
