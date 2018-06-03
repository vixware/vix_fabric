package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUserSyncDetail
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpUserSyncDetail extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String openId;
	private String timeMark;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTimeMark() {
		return timeMark;
	}

	public void setTimeMark(String timeMark) {
		this.timeMark = timeMark;
	}

}
