package com.vix.sales.forecast.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class ForecastModel extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Date time;
	
	public ForecastModel(){}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
