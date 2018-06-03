package com.vix.crm.base.entity;

import com.vix.common.share.entity.BaseEntity;

public class CrmSet extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 客户提醒天数  */
	private Integer remindDay;
	/** 备注 */
	private String memo;
	
	public CrmSet(){}

	public Integer getRemindDay() {
		return remindDay;
	}

	public void setRemindDay(Integer remindDay) {
		this.remindDay = remindDay;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
