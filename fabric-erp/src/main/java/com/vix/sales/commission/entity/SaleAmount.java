package com.vix.sales.commission.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 销售定额
 * @author Administrator
 *
 */
public class SaleAmount extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 销售组织 */
	private OrganizationUnit saleOrg;
	/** 销售员类别 */
	private PersonnelCategory personnelCategory;
	/** 员工编码 */
	private Employee employee;
	/** 年度 */
	private String year;
	/** 定额明细 */
	private Set<SaleAmountItem> saleAmountItems = new HashSet<SaleAmountItem>();
	
	public SaleAmount(){}

	public OrganizationUnit getSaleOrg() {
		return saleOrg;
	}
	public String getSaleOrgName() {
		if(saleOrg != null) {
			return saleOrg.getFullName();
		}
		return "";
	}

	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}

	public PersonnelCategory getPersonnelCategory() {
		return personnelCategory;
	}
	public String getPersonnelCategoryName() {
		if(personnelCategory != null) {
			return personnelCategory.getName();
		}
		return "";
	}

	public void setPersonnelCategory(PersonnelCategory personnelCategory) {
		this.personnelCategory = personnelCategory;
	}

	public Employee getEmployee() {
		return employee;
	}
	public String getEmployeeName() {
		if(employee != null){
			return employee.getName();
		}
		return "";
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Set<SaleAmountItem> getSaleAmountItems() {
		return saleAmountItems;
	}

	public void setSaleAmountItems(Set<SaleAmountItem> saleAmountItems) {
		this.saleAmountItems = saleAmountItems;
	}
}
