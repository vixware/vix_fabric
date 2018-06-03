package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMsgCount
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpMsgCount extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 是否允许群发：0，是；1，否 */
	private String isAllowSend;
	/** 微信公众号类型：fuwu，服务号；dingyue，订阅号 */
	private String weixinType;
	private int year;
	/** 日期中天 */
	private int day;
	/** 日期中月 */
	private int month;
	/** 可群发数量 */
	private int count;
	/** 处于定时状态消息数量 */
	private int timingCount;

	public String getIsAllowSend() {
		return isAllowSend;
	}

	public void setIsAllowSend(String isAllowSend) {
		this.isAllowSend = isAllowSend;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTimingCount() {
		return timingCount;
	}

	public void setTimingCount(int timingCount) {
		this.timingCount = timingCount;
	}

	public String getWeixinType() {
		return weixinType;
	}

	public void setWeixinType(String weixinType) {
		this.weixinType = weixinType;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
