package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 数据预处理定义值
 * @author Administrator
 *
 */
public class DataResRowPredata extends BaseEntity {

	private String preData;
	
	private Integer priority;
	
	private DataResRowSystemParams dataResRowSystemParams;

	public String getPreData() {
		return preData;
	}

	public void setPreData(String preData) {
		this.preData = preData;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public DataResRowSystemParams getDataResRowSystemParams() {
		return dataResRowSystemParams;
	}

	public void setDataResRowSystemParams(
			DataResRowSystemParams dataResRowSystemParams) {
		this.dataResRowSystemParams = dataResRowSystemParams;
	}
	
}
