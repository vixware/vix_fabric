package com.vix.oa.personaloffice.entity;

import com.vix.common.share.entity.BaseEntity;

//工作时长设置
public class WorkTime extends BaseEntity {
	
	//工作时长
	private String workLength;

	public String getWorkLength() {
		return workLength;
	}

	public void setWorkLength(String workLength) {
		this.workLength = workLength;
	}

}
