package com.vix.oa.adminMg.officeSupplies.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 办公用品分类
 * @author Thinkpad
 *
 */
public class OfficeCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 子分类集合 */
	private Set<OfficeCategory> subOfficeCategory = new HashSet<OfficeCategory>();
	/**
	 * 个人文件柜
	 */
	private Set<OfficeSupplies> officeSuppliess = new HashSet<OfficeSupplies>();
	/** 父分类 */
	private OfficeCategory parentOfficeCategory;

	public OfficeCategory() {
	}

	public Set<OfficeCategory> getSubOfficeCategory() {
		return subOfficeCategory;
	}

	public void setSubOfficeCategory(Set<OfficeCategory> subOfficeCategory) {
		this.subOfficeCategory = subOfficeCategory;
	}

	public Set<OfficeSupplies> getOfficeSuppliess() {
		return officeSuppliess;
	}

	public void setOfficeSuppliess(Set<OfficeSupplies> officeSuppliess) {
		this.officeSuppliess = officeSuppliess;
	}

	public OfficeCategory getParentOfficeCategory() {
		return parentOfficeCategory;
	}

	public void setParentOfficeCategory(OfficeCategory parentOfficeCategory) {
		this.parentOfficeCategory = parentOfficeCategory;
	}

}
