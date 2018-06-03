package com.vix.nvix.wx.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class WxpKeyData extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parentId;
	private String namespace;
	private String dataTitle;
	private String dataValue;
	private Integer sortNum;
	private Integer isActive;
	private String dataUuid;

	WxpKeyData parent;

	Set<WxpKeyData> subData = new HashSet<WxpKeyData>();

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getDataTitle() {
		return dataTitle;
	}

	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public WxpKeyData getParent() {
		return parent;
	}

	public void setParent(WxpKeyData parent) {
		this.parent = parent;
	}

	public Set<WxpKeyData> getSubData() {
		return subData;
	}

	public void setSubData(Set<WxpKeyData> subData) {
		this.subData = subData;
	}

	public String getDataUuid() {
		return dataUuid;
	}

	public void setDataUuid(String dataUuid) {
		this.dataUuid = dataUuid;
	}

}
