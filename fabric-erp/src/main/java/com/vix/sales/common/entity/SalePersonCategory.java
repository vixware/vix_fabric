package com.vix.sales.common.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

public class SalePersonCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 销售组织 */
	private String saleOrg;
	/** 销售编码 */
	private String saleCatalogCode;
	/** 销售名称 */
	private String saleCatalogName;
	/** 核对人员 */
	private Employee checker;
	 
	public SalePersonCategory(){}

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getSaleCatalogCode() {
		return saleCatalogCode;
	}

	public void setSaleCatalogCode(String saleCatalogCode) {
		this.saleCatalogCode = saleCatalogCode;
	}

	public String getSaleCatalogName() {
		return saleCatalogName;
	}

	public void setSaleCatalogName(String saleCatalogName) {
		this.saleCatalogName = saleCatalogName;
	}

	public Employee getChecker() {
		return checker;
	}

	public void setChecker(Employee checker) {
		this.checker = checker;
	}
}
