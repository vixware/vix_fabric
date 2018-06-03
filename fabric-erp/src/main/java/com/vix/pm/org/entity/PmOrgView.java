package com.vix.pm.org.entity;

import java.io.Serializable;

/**
 * 视图
 * 业务组织视图和业务组织的联合视图
 * @author Administrator
 *
 */
public class PmOrgView implements Serializable {

	private String id;
	
	private String name;
	
	/**
	 * V  业务视图
	 * O  业务组织
	 */
	private String viewType;
	
	private Long realId;
	
	private String parentId;

    /** 承租户标识 */
	private String tenantId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public Long getRealId() {
		return realId;
	}

	public void setRealId(Long realId) {
		this.realId = realId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTenantId()
	{
		return tenantId;
	}

	public void setTenantId(String tenantId)
	{
		this.tenantId = tenantId;
	}
}
