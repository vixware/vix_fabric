package com.vix.mdm.srm.share.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class SupplierCategory extends BaseEntity {

	private static final long serialVersionUID = -7598610863426452986L;
	/** 对象类型 */
	private String objectType;
	/** 父分类 */
	private SupplierCategory supplierCategory;
	/** 子分类 */
	private Set<SupplierCategory> supplierCategories = new HashSet<SupplierCategory>();
	/** 供应商 */
	private Set<Supplier> suppliers = new HashSet<Supplier>();

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public SupplierCategory getSupplierCategory() {
		return supplierCategory;
	}

	public void setSupplierCategory(SupplierCategory supplierCategory) {
		this.supplierCategory = supplierCategory;
	}

	public Set<SupplierCategory> getSupplierCategories() {
		return supplierCategories;
	}

	public void setSupplierCategories(Set<SupplierCategory> supplierCategories) {
		this.supplierCategories = supplierCategories;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

}
