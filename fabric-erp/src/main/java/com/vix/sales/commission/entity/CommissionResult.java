package com.vix.sales.commission.entity;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.hr.organization.entity.Employee;

/** 佣金计算结果 */
public class CommissionResult extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 销售组织 */
	private OrganizationUnit saleOrg;
	/** 佣金方案编码 */
	private String cpCode;
	/** 佣金方案名称 */
	private String cpName;
	/** 销售人员 */
	private Employee saleMan;
	/** 年度 */
	private String year;
	/** 维度值 */
	private String dimensionValue;
	/** 维度 */
	private String dimension;
	/** 佣金额 */
	private Double commissionAmount;
	/** 币种 */
	private CurrencyType currencyType;
	/** 是否已经参与佣金计算 参与为：1 未参与为：0 */
	private String isCommissionCalculation;
	/** 是否完成 完成：1 未完成：0 */
	private String isComplate;
	/** 是否临时数据 ：1 是  0 否*/
	private String isTemp;

	public CommissionResult(){}

	public OrganizationUnit getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getCpCode() {
		return cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public Employee getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(Employee saleMan) {
		this.saleMan = saleMan;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDimensionValue() {
		return dimensionValue;
	}

	public void setDimensionValue(String dimensionValue) {
		this.dimensionValue = dimensionValue;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public Double getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(Double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getIsCommissionCalculation() {
		return isCommissionCalculation;
	}

	public void setIsCommissionCalculation(String isCommissionCalculation) {
		this.isCommissionCalculation = isCommissionCalculation;
	}

	public String getIsComplate() {
		return isComplate;
	}

	public void setIsComplate(String isComplate) {
		this.isComplate = isComplate;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}
	
}
