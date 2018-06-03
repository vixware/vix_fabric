package com.vix.sales.config.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.Regional;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;

public class ProductGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 名称 */
	private String name;
	/** 是否禁用 */
	private String enable;
	/** 备注 */
	private String memo;
	/** 销售组织 */
	private OrganizationUnit salesOrg;
	/** 人员 */
	private Set<Employee> employees = new HashSet<Employee>();
	/** 物料 */
	private Set<Item> items = new HashSet<Item>();
	/** 地域 */
	private Set<Regional> regionals = new HashSet<Regional>();
	
	public ProductGroup(){}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Override
	public String getMemo() {
		return memo;
	}
	
	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public OrganizationUnit getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(OrganizationUnit salesOrg) {
		this.salesOrg = salesOrg;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public Set<Item> getItems() {
		return items;
	}
	
	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Set<Regional> getRegionals() {
		return regionals;
	}

	public void setRegionals(Set<Regional> regionals) {
		this.regionals = regionals;
	}
}
