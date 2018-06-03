package com.vix.system.enumeration.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * 枚举项
 * 系统中所有可能在系统运维过程中发生变化的小数据量字典信息
 * @author arron
 *
 */
public class EnumerationCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 名称 */	
 	private String name;
 	 /** 备注 */	
 	private String memo;
 	/** 子分类集合 */
	private Set<EnumerationCategory> subEnumerationCategorys = new HashSet<EnumerationCategory>();
	/** 父分类 */
	private EnumerationCategory parentEnumerationCategory;
	
 	public EnumerationCategory(){}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<EnumerationCategory> getSubEnumerationCategorys() {
		return subEnumerationCategorys;
	}

	public void setSubEnumerationCategorys(
			Set<EnumerationCategory> subEnumerationCategorys) {
		this.subEnumerationCategorys = subEnumerationCategorys;
	}

	public EnumerationCategory getParentEnumerationCategory() {
		return parentEnumerationCategory;
	}

	public void setParentEnumerationCategory(
			EnumerationCategory parentEnumerationCategory) {
		this.parentEnumerationCategory = parentEnumerationCategory;
	}
}
