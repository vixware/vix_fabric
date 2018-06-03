package com.vix.sales.plan.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;
/**
 * 销售计划明细
 * @author jackie
 *
 */
public class SalePlanDetails extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item item;//商品
	/**
	 * 销售计划
	 */
	private SalePlan salePlan;
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
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
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
	public SalePlan getSalePlan() {
		return salePlan;
	}
	public void setSalePlan(SalePlan salePlan) {
		this.salePlan = salePlan;
	}
	public Long getAmount() {
		return december+nov+oct+sep+aug+jul+jun+may+apr+mar+feb+jan;
	}
}
