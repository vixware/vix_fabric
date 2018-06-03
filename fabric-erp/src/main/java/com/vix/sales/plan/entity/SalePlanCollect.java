package com.vix.sales.plan.entity;

import java.util.Date;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
/** 销售计划汇总*/
public class SalePlanCollect extends BaseEntity {

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
	private String year;
	private Long jan;//一月份计划数量
	private Long feb;//二月份计划数量
	private Long mar;//三月份计划数量
	private Long apr;//四月份计划数量
	private Long may;//五月份计划数量
	private Long jun;//六月份计划数量
	private Long jul;//七月份计划数量
	private Long aug;//八月份计划数量
	private Long sep;//九月份计划数量
	private Long oct;//十月份计划数量
	private Long nov;//十一月份计划数量
	private Long december;//十二月份计划数量
	public SalePlanCollect(){}

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
	public String getTimeStr() {
		if(time != null) {
			return DateUtil.format(time);
		}
		return "";
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
	public String getSaleOrgName() {
		if(saleOrg != null) {
			return saleOrg.getFullName();
		}
		return "";
	}

	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}

	public OrganizationUnit getDepartmet() {
		return departmet;
	}
	public String getDepartmetName() {
		if(departmet != null) {
			return departmet.getFullName();
		}
		return "";
	}

	public void setDepartmet(OrganizationUnit departmet) {
		this.departmet = departmet;
	}

	public Employee getSalesMan() {
		return salesMan;
	}
	public String getSalesManName() {
		if(salesMan != null) {
			return salesMan.getName();
		}
		return "";
	}

	public void setSalesMan(Employee salesMan) {
		this.salesMan = salesMan;
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

	public MeasureUnitGroup getMeasureUnitGroup() {
		return measureUnitGroup;
	}

	public void setMeasureUnitGroup(MeasureUnitGroup measureUnitGroup) {
		this.measureUnitGroup = measureUnitGroup;
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

	public Double getTotalAcount() {
		return totalAcount;
	}

	public void setTotalAcount(Double totalAcount) {
		this.totalAcount = totalAcount;
	}

	public Long getTotalAmount() {
		totalAmount = december+nov+oct+sep+aug+jul+jun+may+apr+mar+feb+jan;
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getJan() {
		return jan;
	}

	public void setJan(Long jan) {
		this.jan = jan;
	}

	public Long getFeb() {
		return feb;
	}

	public void setFeb(Long feb) {
		this.feb = feb;
	}

	public Long getMar() {
		return mar;
	}

	public void setMar(Long mar) {
		this.mar = mar;
	}

	public Long getApr() {
		return apr;
	}

	public void setApr(Long apr) {
		this.apr = apr;
	}

	public Long getMay() {
		return may;
	}

	public void setMay(Long may) {
		this.may = may;
	}

	public Long getJun() {
		return jun;
	}

	public void setJun(Long jun) {
		this.jun = jun;
	}

	public Long getJul() {
		return jul;
	}

	public void setJul(Long jul) {
		this.jul = jul;
	}

	public Long getAug() {
		return aug;
	}

	public void setAug(Long aug) {
		this.aug = aug;
	}

	public Long getSep() {
		return sep;
	}

	public void setSep(Long sep) {
		this.sep = sep;
	}

	public Long getOct() {
		return oct;
	}

	public void setOct(Long oct) {
		this.oct = oct;
	}

	public Long getNov() {
		return nov;
	}

	public void setNov(Long nov) {
		this.nov = nov;
	}

	public Long getDecember() {
		return december;
	}

	public void setDecember(Long december) {
		this.december = december;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
