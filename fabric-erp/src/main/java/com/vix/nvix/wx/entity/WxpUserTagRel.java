package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUserTagRel
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpUserTagRel extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 是否是最后（腾讯）0，否；1，是； */
	private String isNextOpenId;
	private WxpUserTag tag;
	private WxpUser user;

	public WxpUserTag getTag() {
		return tag;
	}

	public void setTag(WxpUserTag tag) {
		this.tag = tag;
	}

	public WxpUser getUser() {
		return user;
	}

	public void setUser(WxpUser user) {
		this.user = user;
	}

	public String getIsNextOpenId() {
		return isNextOpenId;
	}

	public void setIsNextOpenId(String isNextOpenId) {
		this.isNextOpenId = isNextOpenId;
	}
}
