package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

public class WxpInfoMemberCard extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title; // 标题，注释用
	String imageName; // 图片名称，路径是固定的
	Integer cardLevel; // 会员卡级别，对应member_level
	String note; // 会员卡说明

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getCardLevel() {
		return cardLevel;
	}

	public void setCardLevel(Integer cardLevel) {
		this.cardLevel = cardLevel;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
