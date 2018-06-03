package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: Resource
 * @Description: 权限的资源的定义，如URL的具体控制在这里定义
 * @author wangmingchen
 * @date 2013-4-18 下午8:57:34
 *
 */
public class Resource extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 控制的url，这里不能够写完整的url，而是带通配符方式的，供前台filter对该url进行授权和验证 */
	private String url;
	/** 资源编码  唯一 */
	private String resourceCode;
	
	/** 优先级 */
	private Integer priority;
	/** 类型，未想好，暂初定 0 */
	private Integer resType;
	/** 名称 */
	private String name;
	/** 显示名称 */
	private String displayName;
	/** 描述 */
	private String memo;
	
	/**
	 * 权限 
	 */
	private Authority authority;

	public Resource(){}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    @Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	@Override
	public String toString() {
		return "Resource [resourceCode=" + resourceCode + ", name=" + name
				+ "]";
	}
}
