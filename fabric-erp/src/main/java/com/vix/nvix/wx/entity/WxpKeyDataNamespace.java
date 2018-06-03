package com.vix.nvix.wx.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class WxpKeyDataNamespace extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String namespace;
	private String title;
	String upLevelId;
	String downLevelId;
	Integer needUuid;
	Integer sortNum = 0;

	WxpKeyDataNamespace upLevel;
	WxpKeyDataNamespace downLevel;

	Set<WxpKeyDataNamespace> subNamespace = new HashSet<WxpKeyDataNamespace>();

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Set<WxpKeyDataNamespace> getSubNamespace() {
		return subNamespace;
	}

	public void setSubNamespace(Set<WxpKeyDataNamespace> subNamespace) {
		this.subNamespace = subNamespace;
	}

	public String getUpLevelId() {
		return upLevelId;
	}

	public void setUpLevelId(String upLevelId) {
		this.upLevelId = upLevelId;
	}

	public String getDownLevelId() {
		return downLevelId;
	}

	public void setDownLevelId(String downLevelId) {
		this.downLevelId = downLevelId;
	}

	public WxpKeyDataNamespace getUpLevel() {
		return upLevel;
	}

	public void setUpLevel(WxpKeyDataNamespace upLevel) {
		this.upLevel = upLevel;
	}

	public WxpKeyDataNamespace getDownLevel() {
		return downLevel;
	}

	public void setDownLevel(WxpKeyDataNamespace downLevel) {
		this.downLevel = downLevel;
	}

	public Integer getNeedUuid() {
		return needUuid;
	}

	public void setNeedUuid(Integer needUuid) {
		this.needUuid = needUuid;
	}

}
