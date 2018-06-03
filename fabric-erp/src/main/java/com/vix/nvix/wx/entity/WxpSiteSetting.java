package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpSiteSetting
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpSiteSetting extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String namespace;
	private String dataValue;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

}
