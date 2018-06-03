package com.vix.common.security.vo;

import com.vix.core.utils.excel.ExcelVOAttribute;

public class OrginialAuthorityImpVo {

	/** 权限编码 */
	@ExcelVOAttribute(name = "权限编码", column = "A", isExport = true, prompt = "编码必须填写！")
	private String authorityCode;

	/** 权限名称 */
	@ExcelVOAttribute(name = "权限名称", column = "B", isExport = true, prompt = "名称必须填写！")
	private String name;

	/** 显示名称 */
	@ExcelVOAttribute(name = "权限显示名称", column = "C", isExport = true, prompt = "显示名称必须填写！")
	private String displayName;

	@ExcelVOAttribute(name = "排序号", column = "D", isExport = true, prompt = "显示名称必须填写！")
	private Integer sortOrder;

	/**
	 * M:菜单,F:功能, R:界面资源,O:其它资源， D:数据权限
	 */
	@ExcelVOAttribute(name = "资源类型(M:菜单,F:功能)", column = "E", isExport = true, combo = {"M", "F"})
	private String authType;

	/** 连接的请求url */
	@ExcelVOAttribute(name = "权限URL", column = "F", isExport = true)
	private String menuHrefUrl;

	@ExcelVOAttribute(name = "上级权限编码", column = "G", isExport = true)
	private String parentAuthorityCode;

	@ExcelVOAttribute(name = "菜单业务分类(P:PC,D:Pad,M:MOBILE)", column = "H", isExport = true, combo = {"P", "D", "M"})
	private String bizType;

	@ExcelVOAttribute(name = "菜单读取方式(C:正常,U:升级)", column = "I", isExport = true, combo = {"C", "U"})
	private String viewPos;

	@ExcelVOAttribute(name = "菜单图标", column = "J", isExport = true)
	private String iconClass;

	public OrginialAuthorityImpVo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getMenuHrefUrl() {
		return menuHrefUrl;
	}

	public void setMenuHrefUrl(String menuHrefUrl) {
		this.menuHrefUrl = menuHrefUrl;
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

}
