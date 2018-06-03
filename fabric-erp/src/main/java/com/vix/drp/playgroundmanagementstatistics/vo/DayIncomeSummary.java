package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 日收入汇总
 * 
 * com.vix.drp.playgroundmanagementstatistics.vo.DayIncomeSummary
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class DayIncomeSummary {
	/**
	 * 员工名称
	 */
	private String name;
	/**
	 * 现金
	 */
	private Double cash;
	/**
	 * POS机
	 */
	private Double pos;
	/**
	 * 团购
	 */
	private Double groupBuying;

	private Double realIncomesCash;

	private Double realIncomesPos;

	private Double realIncomesGroupBuying;

	private Double differenceAmount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Double getPos() {
		return pos;
	}

	public void setPos(Double pos) {
		this.pos = pos;
	}

	public Double getGroupBuying() {
		return groupBuying;
	}

	public void setGroupBuying(Double groupBuying) {
		this.groupBuying = groupBuying;
	}

	public Double getRealIncomesCash() {
		return realIncomesCash;
	}

	public void setRealIncomesCash(Double realIncomesCash) {
		this.realIncomesCash = realIncomesCash;
	}

	public Double getRealIncomesPos() {
		return realIncomesPos;
	}

	public void setRealIncomesPos(Double realIncomesPos) {
		this.realIncomesPos = realIncomesPos;
	}

	public Double getRealIncomesGroupBuying() {
		return realIncomesGroupBuying;
	}

	public void setRealIncomesGroupBuying(Double realIncomesGroupBuying) {
		this.realIncomesGroupBuying = realIncomesGroupBuying;
	}

	public Double getDifferenceAmount() {
		return differenceAmount;
	}

	public void setDifferenceAmount(Double differenceAmount) {
		this.differenceAmount = differenceAmount;
	}

}
