package com.vix.nvix.wx.entity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUserMessageList
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpUserMessageList extends WxpUserMessage {

	private static final long serialVersionUID = 1L;
	private WxpUser user;

	public String getOpenId() {
		if (this.user != null)
			return this.user.getOpenId();
		else
			return null;
	}

	@Override
	public WxpUser getUser() {
		return user;
	}

	@Override
	public void setUser(WxpUser user) {
		this.user = user;
	}
}
