package com.vix.nvix.wx.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class WxpPayDirectGroup extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String title; // 名称

	String snCode; // 外联编码

	String mendian;

	Set<WxpPayDirect> payDirects = new HashSet<WxpPayDirect>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<WxpPayDirect> getPayDirects() {
		return payDirects;
	}

	public void setPayDirects(Set<WxpPayDirect> payDirects) {
		this.payDirects = payDirects;
	}

	public String getMendian() {
		return mendian;
	}

	public void setMendian(String mendian) {
		this.mendian = mendian;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

}
