package com.vix.common.security.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: IndexPageDataConfig
 * @Description: 首页面数据配置
 * @author wangmingchen
 * @date 2015年4月26日 下午4:53:33
 */
public class IndexPageDataConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** id选择器标识 */
	private String divId;
	/** 请求url */
	private String divUrl;
	
	/** 名称 */
	//private String name;
	/** 显示名称 */
	private String displayName;
	/** 描述 */
	private String memo;
	
	/**
	 * 角色
	 */
	private Set<Role> roles = new HashSet<Role>();

	public IndexPageDataConfig(){}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getDivUrl() {
		return divUrl;
	}

	public void setDivUrl(String divUrl) {
		this.divUrl = divUrl;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
