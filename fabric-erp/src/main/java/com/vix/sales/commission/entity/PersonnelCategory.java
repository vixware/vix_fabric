package com.vix.sales.commission.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class PersonnelCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 子分类集合 */
	private Set<PersonnelCategory> subPersonnelCategorys = new HashSet<PersonnelCategory>();
	/** 父分类 */
	private PersonnelCategory parentPersonnelCategory;
	
	public PersonnelCategory(){}

	public Set<PersonnelCategory> getSubPersonnelCategorys() {
		return subPersonnelCategorys;
	}

	public void setSubPersonnelCategorys(
			Set<PersonnelCategory> subPersonnelCategorys) {
		this.subPersonnelCategorys = subPersonnelCategorys;
	}

	public PersonnelCategory getParentPersonnelCategory() {
		return parentPersonnelCategory;
	}
	public String getParentName() {
		if(parentPersonnelCategory != null) {
			return parentPersonnelCategory.getName();
		}
		return "";
	}

	public void setParentPersonnelCategory(PersonnelCategory parentPersonnelCategory) {
		this.parentPersonnelCategory = parentPersonnelCategory;
	}
}
