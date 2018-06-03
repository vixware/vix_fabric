package com.vix.oa.personaloffice.entity;

import com.vix.common.share.entity.BaseEntity;

//请假出差类型
public class LeaveType extends BaseEntity {

	// 排序号
	private String rank;
	// 类型标题
	private String title;
	// 类型内容
	private String content;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
