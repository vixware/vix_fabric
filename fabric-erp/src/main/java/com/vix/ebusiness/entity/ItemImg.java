package com.vix.ebusiness.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class ItemImg extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 图片创建时间 时间格式：yyyy-MM-dd HH:mm:ss
	 */
	private Date created;

	/**
	 * 图片放在第几张（多图时可设置）
	 */
	private Long position;

	/**
	 * 图片链接地址
	 */
	private String url;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
