package com.vix.wechat.base.entity;

import com.vix.common.share.entity.BaseEntity;

public class WxpKnowLedge extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 标题
	private String title;
	// 分类
	private String classLfication;
	// 已读人数
	private String population;
	// 状态
	private String state;

	// 排序号
	private String sequenceNnumber;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassLfication() {
		return classLfication;
	}

	public void setClassLfication(String classLfication) {
		this.classLfication = classLfication;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSequenceNnumber() {
		return sequenceNnumber;
	}

	public void setSequenceNnumber(String sequenceNnumber) {
		this.sequenceNnumber = sequenceNnumber;
	}

}
