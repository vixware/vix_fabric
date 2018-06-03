package com.vix.common.security.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.nvix.common.base.entity.HomeTemplate;

/**
 * @ClassName: Role
 * @Description: 角色
 * @author wangmingchen
 * @date 2013-4-18 下午8:19:33
 * 
 */
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 角色编码 */
	private String roleCode;
	/** 角色分类类型(系统管理：S，业务角色：B,供应商角色:SU) */
	private String roleType;

	/** 继承的父角色 */
	private Role parentRole;
	/** 子角色 */
	private Set<Role> subRoles = new HashSet<Role>(0);

	/** 帐号信息 */
	private Set<UserAccount> userAccounts = new HashSet<UserAccount>(0);
	/** 用户组信息 */
	private Set<UserGroup> userGroups = new HashSet<UserGroup>(0);

	/** 业务角色配置 */
	private Set<RoleBizConfig> roleBizConfigs = new HashSet<RoleBizConfig>(0);

	/** 角色类型 */
	private Set<RoleType> roleTypes = new HashSet<RoleType>(0);

	/** 权限集合 */
	private Set<Authority> authoritys = new HashSet<Authority>(0);

	/** 列权限配置 */
	private Set<DataResColConfig> dataResColConfigs = new HashSet<DataResColConfig>(0);
	/**
	 * 角色 所属行业 private IndustryManagement industryManagement;
	 */

	/**
	 * 行业模块
	 * 
	 * 承租户超级管理员才使用此属性
	 * 
	 * private Set<IndustryManagementModule> industryManagementModules;
	 */

	/**
	 * 预处理使用的 系统行集权限常量数据
	 */
	private Set<DataResRowSystemParams> dataResRowSystemParams = new HashSet<DataResRowSystemParams>(0);

	/** div配置 */
	private Set<IndexPageDataConfig> indexPageDataConfigs = new HashSet<IndexPageDataConfig>(0);
	/**
	 * 工作台模板
	 */
	private HomeTemplate homeTemplate;

	public Role() {
	}

	public Role(String id) {
		setId(id);
	}

	public Set<Authority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(Set<Authority> authoritys) {
		this.authoritys = authoritys;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Role getParentRole() {
		return parentRole;
	}

	public void setParentRole(Role parentRole) {
		this.parentRole = parentRole;
	}

	public Set<Role> getSubRoles() {
		return subRoles;
	}

	public void setSubRoles(Set<Role> subRoles) {
		this.subRoles = subRoles;
	}

	public Set<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public Set<RoleBizConfig> getRoleBizConfigs() {
		return roleBizConfigs;
	}

	public void setRoleBizConfigs(Set<RoleBizConfig> roleBizConfigs) {
		this.roleBizConfigs = roleBizConfigs;
	}

	public Set<RoleType> getRoleTypes() {
		return roleTypes;
	}

	public void setRoleTypes(Set<RoleType> roleTypes) {
		this.roleTypes = roleTypes;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public Set<IndexPageDataConfig> getIndexPageDataConfigs() {
		return indexPageDataConfigs;
	}

	public void setIndexPageDataConfigs(Set<IndexPageDataConfig> indexPageDataConfigs) {
		this.indexPageDataConfigs = indexPageDataConfigs;
	}

	@Override
	public String toString() {
		return "Role [name=" + getName() + ", roleCode=" + roleCode + "]";
	}

	public Set<DataResRowSystemParams> getDataResRowSystemParams() {
		return dataResRowSystemParams;
	}

	public void setDataResRowSystemParams(Set<DataResRowSystemParams> dataResRowSystemParams) {
		this.dataResRowSystemParams = dataResRowSystemParams;
	}

	public Set<DataResColConfig> getDataResColConfigs() {
		return dataResColConfigs;
	}

	public void setDataResColConfigs(Set<DataResColConfig> dataResColConfigs) {
		this.dataResColConfigs = dataResColConfigs;
	}

	/**
	 * @return the homeTemplate
	 */
	public HomeTemplate getHomeTemplate() {
		return homeTemplate;
	}

	/**
	 * @param homeTemplate
	 *            the homeTemplate to set
	 */
	public void setHomeTemplate(HomeTemplate homeTemplate) {
		this.homeTemplate = homeTemplate;
	}

}
