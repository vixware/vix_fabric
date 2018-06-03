package com.vix.crm.salechance.entity;

import com.vix.common.share.entity.BaseEntity;

public class BackSectionRecordTotal extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 月份 */
	private Long month;
	/** 上月结转金额 */
	private Double previousMonthAmount = 0d;
	/** 本月收款金额 */
	private Double currentMonthAmount = 0d;
	
	public BackSectionRecordTotal(){}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Double getPreviousMonthAmount() {
		return previousMonthAmount;
	}

	public void setPreviousMonthAmount(Double previousMonthAmount) {
		this.previousMonthAmount = previousMonthAmount;
	}

	public Double getCurrentMonthAmount() {
		return currentMonthAmount;
	}

	public void setCurrentMonthAmount(Double currentMonthAmount) {
		this.currentMonthAmount = currentMonthAmount;
	}
 
	public Double getTotal(){
		return currentMonthAmount + previousMonthAmount;
	}
}
