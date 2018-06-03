package com.vix.sales.plan.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
/** 销售计划 */
public class SalePlan extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料 */
	private Item item;
	/** 规格 */
	private String specifications;
	/** 数量 */
	private Long amount;
	/** 计量单位 */
	private String measurementUnit;
	/** 辅助计量单位 */
	private String auxiliaryUnit;
	/** 生产组织 */
	private String produceOrg;
	/** 时间 */
	private Date time;
	/** 周期 */
	private String cycle;
	/** 销售组织 */
	private OrganizationUnit saleOrg;
	/** 部门 */
	private OrganizationUnit departmet;
	/** 销售人员 */
	private Employee salesMan;
	/** 子分类集合 */
	private Set<SalePlan> subSalePlans = new HashSet<SalePlan>();
	/** 父分类 */
	private SalePlan parentSalePlan;
	/**
	 * 销售金额  
	 */
	private Double acount;
	/**
	 * 币种
	 */
	private CurrencyType currencyType;
	/** 单位组 */
	private MeasureUnitGroup measureUnitGroup;
	/** 主计量单位 */
	private MeasureUnit measureUnit;
	/** 辅助计量单位 */
	private MeasureUnit assitMeasureUnit;
	/**
	 * 总金额
	 */
	private Double totalAcount;
	/**
	 * 总数量
	 */
	private Long totalAmount;
	private String planDate;//计划周期
	/**
	 * 是否汇总 1,是    0,否
	 */
	private String isCollect;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getAuxiliaryUnit() {
		return auxiliaryUnit;
	}
	public void setAuxiliaryUnit(String auxiliaryUnit) {
		this.auxiliaryUnit = auxiliaryUnit;
	}
	public String getProduceOrg() {
		return produceOrg;
	}
	public void setProduceOrg(String produceOrg) {
		this.produceOrg = produceOrg;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public OrganizationUnit getSaleOrg() {
		return saleOrg;
	}
	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}
	public OrganizationUnit getDepartmet() {
		return departmet;
	}
	public void setDepartmet(OrganizationUnit departmet) {
		this.departmet = departmet;
	}
	public Employee getSalesMan() {
		return salesMan;
	}
	public void setSalesMan(Employee salesMan) {
		this.salesMan = salesMan;
	}
	public Set<SalePlan> getSubSalePlans() {
		return subSalePlans;
	}
	public void setSubSalePlans(Set<SalePlan> subSalePlans) {
		this.subSalePlans = subSalePlans;
	}
	public SalePlan getParentSalePlan() {
		return parentSalePlan;
	}
	public void setParentSalePlan(SalePlan parentSalePlan) {
		this.parentSalePlan = parentSalePlan;
	}
	public Double getAcount() {
		return acount;
	}
	public void setAcount(Double acount) {
		this.acount = acount;
	}
	public CurrencyType getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}
	public MeasureUnitGroup getMeasureUnitGroup() {
		return measureUnitGroup;
	}
	public void setMeasureUnitGroup(MeasureUnitGroup measureUnitGroup) {
		this.measureUnitGroup = measureUnitGroup;
	}
	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}
	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}
	public MeasureUnit getAssitMeasureUnit() {
		return assitMeasureUnit;
	}
	public void setAssitMeasureUnit(MeasureUnit assitMeasureUnit) {
		this.assitMeasureUnit = assitMeasureUnit;
	}
	public Double getTotalAcount() {
		return totalAcount;
	}
	public void setTotalAcount(Double totalAcount) {
		this.totalAcount = totalAcount;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public String getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}
}
