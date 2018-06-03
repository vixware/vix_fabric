package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class WxpModulePage extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String pageId; // 页面的标签，通过此标签回调页面
	String title; // 功能页名称
	String pageFile; // 页面的路径/文件名，基础路径固定
	Integer wxOauthType; // 0/1分别对应微信页面oauth授权时scope参数对应的snsapi_base和snsapi_userinfo
	private Date startDate; // 有效期起始日期
	private Date endDate; // 有效期结束日期

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getPageFile() {
		return pageFile;
	}

	public void setPageFile(String pageFile) {
		this.pageFile = pageFile;
	}

	public Integer getWxOauthType() {
		return wxOauthType;
	}

	public void setWxOauthType(Integer wxOauthType) {
		this.wxOauthType = wxOauthType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
