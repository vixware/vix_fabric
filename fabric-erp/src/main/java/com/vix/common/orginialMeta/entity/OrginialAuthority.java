package com.vix.common.orginialMeta.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.vix.common.share.entity.BaseEntity;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;

/**
 * @ClassName: OrginialAuthority
 * @Description: 权限的定义源 包括菜单 功能（按钮）
 * @author wangmingchen
 * @date 2014年11月11日 上午10:38:31
 */
public class OrginialAuthority extends BaseEntity implements Comparable<Object> {

	private static final long serialVersionUID = 1L;

	/** 权限名称 */
	// private String name;
	/** 权限编码 */
	private String authorityCode;
	/** 父权限编码 */
	private String parentAuthorityCode;

	/** 显示名称 */
	private String displayName;
	/** 备注 */
	// private String memo;

	private Integer sortOrder;

	/**
	 * M:菜单,F:功能, R:界面资源,O:其它资源， D:数据权限
	 */
	private String authType;

	/**
	 * 菜单业务分类 BizConstants的COMMON_SECURITY_RESTYPE PC Pad Mobile
	 */
	private String bizType;

	/**
	 * 是否显示在一级菜单 BizConstants.COMMON_SECURITY_USERMENUTYPE C 一般 U 显示到head
	 */
	private String viewPos;

	/** 连接的请求url */
	private String menuHrefUrl;

	/** 根节点的code */
	private String topParentCode;

	/** 父权限 */
	// private OrginialAuthority parentAuthority;

	/** 子权限 */
	// private Set<OrginialAuthority> subAuthoritys = new
	// HashSet<OrginialAuthority>();

	/** 拥有角色的权限，在角色方维护该关系 */
	// private Set<Role> roles = new HashSet<Role>();

	/** 权限具有的资源 对URL的控制 */
	private Set<OrginialResource> resources = new HashSet<OrginialResource>();

	/** 所属行业模块 */
	private Set<IndustryManagementModule> industryManagementModules = new HashSet<IndustryManagementModule>();

	/** 不参与数据持久化 如果该权限与某角色关联 则记录为"Y" 否则记为"N" */
	private String isCheck;

	private String pId;// 树使用 父id

	private String checkId;

	/** 行业模块id */
	private String immId;
	/** 角色id */
	private String rId;

	private Long subCount;

	/** 非持久化属性 */
	// private String parentId;

	private String treeId;

	private String state;

	private Set<OrginialAuthority> children;
	/**
	 * 菜单图标
	 */
	private String iconClass;

	public OrginialAuthority() {
	}

	public OrginialAuthority(String id) {
		setId(id);
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMenuHrefUrl() {
		return menuHrefUrl;
	}

	public void setMenuHrefUrl(String menuHrefUrl) {
		this.menuHrefUrl = menuHrefUrl;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Set<IndustryManagementModule> getIndustryManagementModules() {
		return industryManagementModules;
	}

	public void setIndustryManagementModules(Set<IndustryManagementModule> industryManagementModules) {
		this.industryManagementModules = industryManagementModules;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	/*
	 * public String getParentId() { return parentId; }
	 * 
	 * public void setParentId(String parentId) { this.parentId = parentId; }
	 */

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getParentAuthorityCode() {
		return parentAuthorityCode;
	}

	public void setParentAuthorityCode(String parentAuthorityCode) {
		this.parentAuthorityCode = parentAuthorityCode;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getViewPos() {
		return viewPos;
	}

	public void setViewPos(String viewPos) {
		this.viewPos = viewPos;
	}

	public String getTopParentCode() {
		return topParentCode;
	}

	public void setTopParentCode(String topParentCode) {
		this.topParentCode = topParentCode;
	}

	public Set<OrginialAuthority> getChildren() {
		return children;
	}

	public void setChildren(Set<OrginialAuthority> children) {
		this.children = children;
	}

	public void initChildList() {
		if (children == null)
			children = new TreeSet<OrginialAuthority>();
	}

	public String getImmId() {
		return immId;
	}

	public void setImmId(String immId) {
		this.immId = immId;
	}

	public void addChildren(OrginialAuthority child) {
		initChildList();
		this.children.add(child);
	}

	public Long getSubCount() {
		return subCount;
	}

	public void setSubCount(Long subCount) {
		this.subCount = subCount;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public Set<OrginialResource> getResources() {
		return resources;
	}

	public void setResources(Set<OrginialResource> resources) {
		this.resources = resources;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	/**
	 * @return the iconClass
	 */
	public String getIconClass() {
		return iconClass;
	}

	/**
	 * @param iconClass
	 *            the iconClass to set
	 */
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	@Override
	public int compareTo(Object arg0) {
		OrginialAuthority obj = (OrginialAuthority) arg0;
		Integer myObj = this.sortOrder;
		Integer otherObj = obj.sortOrder;
		if (myObj == null || otherObj == null) {
			return -1;
		}

		if (myObj < otherObj) {
			return -1;
		} else if (myObj > otherObj) {
			return 1;
		}

		if (this.getId() == obj.getId()) {
			return 0;
		}

		// if return 0 then can't add
		return 1;
		// return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authType == null) ? 0 : authType.hashCode());
		result = prime * result + ((authorityCode == null) ? 0 : authorityCode.hashCode());
		result = prime * result + ((bizType == null) ? 0 : bizType.hashCode());
		result = prime * result + ((checkId == null) ? 0 : checkId.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((isCheck == null) ? 0 : isCheck.hashCode());
		result = prime * result + ((menuHrefUrl == null) ? 0 : menuHrefUrl.hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((parentAuthorityCode == null) ? 0 : parentAuthorityCode.hashCode());
		result = prime * result + ((sortOrder == null) ? 0 : sortOrder.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((topParentCode == null) ? 0 : topParentCode.hashCode());
		result = prime * result + ((treeId == null) ? 0 : treeId.hashCode());
		result = prime * result + ((viewPos == null) ? 0 : viewPos.hashCode());
		result = prime * result + ((iconClass == null) ? 0 : iconClass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrginialAuthority other = (OrginialAuthority) obj;

		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;

		if (authType == null) {
			if (other.authType != null)
				return false;
		} else if (!authType.equals(other.authType))
			return false;
		if (authorityCode == null) {
			if (other.authorityCode != null)
				return false;
		} else if (!authorityCode.equals(other.authorityCode))
			return false;
		if (bizType == null) {
			if (other.bizType != null)
				return false;
		} else if (!bizType.equals(other.bizType))
			return false;
		if (checkId == null) {
			if (other.checkId != null)
				return false;
		} else if (!checkId.equals(other.checkId))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (isCheck == null) {
			if (other.isCheck != null)
				return false;
		} else if (!isCheck.equals(other.isCheck))
			return false;
		if (menuHrefUrl == null) {
			if (other.menuHrefUrl != null)
				return false;
		} else if (!menuHrefUrl.equals(other.menuHrefUrl))
			return false;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (parentAuthorityCode == null) {
			if (other.parentAuthorityCode != null)
				return false;
		} else if (!parentAuthorityCode.equals(other.parentAuthorityCode))
			return false;
		if (sortOrder == null) {
			if (other.sortOrder != null)
				return false;
		} else if (!sortOrder.equals(other.sortOrder))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (topParentCode == null) {
			if (other.topParentCode != null)
				return false;
		} else if (!topParentCode.equals(other.topParentCode))
			return false;
		if (treeId == null) {
			if (other.treeId != null)
				return false;
		} else if (!treeId.equals(other.treeId))
			return false;
		if (viewPos == null) {
			if (other.viewPos != null)
				return false;
		} else if (!viewPos.equals(other.viewPos))
			return false;
		if (iconClass == null) {
			if (other.iconClass != null)
				return false;
		} else if (!iconClass.equals(other.iconClass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Authority [name=" + getName() + ", authorityCode=" + authorityCode + "]";
	}

}