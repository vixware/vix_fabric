package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUserRemark
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpUserRemark extends BaseEntity {

	private static final long serialVersionUID = 1L;
	public static int default_is_open_no = 0;// 私有
	public static int default_is_open_yes = 1;// 公开
	private String note; // 备注说明
	private String remarkType; // 标注原因
	private Integer isOpen; // 私有：0；公开：1；
	private WxpUser user;
	// private User createUser;

	public void setUser(WxpUser user) {
		this.user = user;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(String remarkType) {
		this.remarkType = remarkType;
	}

	/*
	 * public User getCreateUser() { return createUser; } public void
	 * setCreateUser(User createUser) { this.createUser = createUser; }
	 */
	public WxpUser getUser() {
		return user;
	}
}
