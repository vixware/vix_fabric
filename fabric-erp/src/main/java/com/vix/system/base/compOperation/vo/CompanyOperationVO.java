package com.vix.system.base.compOperation.vo;

import java.util.ArrayList;
import java.util.List;

import com.vix.common.org.entity.Organization;

/**
 * 公司运营管理
 * 
 * @author Administrator
 * 
 */
public class CompanyOperationVO extends Organization {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 父公司id */
	private String parentId;
	
	private String userAccountId;
	
	//oc的 账号密码参数 
	private String compAccount;
	private String compPassword;
	
	// 创建公司超级管理员
	/** 账号 */
	private String account;
	
	private String oldPassword;
	/** 密码 */
	private String password;
	private String passwordConfirm;
	
	
	private List<String> useModuleList;
	
	/** 是否重置权限 */
	private String isResetAuth;
	
	/** 角色所属行业 */
	//private String industryManagementId;
	//private String industryManagementName;
	
	//private String industryManagementModuleUUIDs;
	
	//门店参数
	private List<OrganizationTenantShopVO> shopList = new ArrayList<OrganizationTenantShopVO>(0);
	
	@Override
	public String getAccount() {
		return account;
	}

	@Override
	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	@Override
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@Override
	public String getUserAccountId() {
		return userAccountId;
	}

	@Override
	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}

	public List<String> getUseModuleList() {
		return useModuleList;
	}

	public void setUseModuleList(List<String> useModuleList) {
		this.useModuleList = useModuleList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIsResetAuth() {
		return isResetAuth;
	}

	public void setIsResetAuth(String isResetAuth) {
		this.isResetAuth = isResetAuth;
	}

	public String getCompAccount() {
		return compAccount;
	}

	public void setCompAccount(String compAccount) {
		this.compAccount = compAccount;
	}

	public String getCompPassword() {
		return compPassword;
	}

	public void setCompPassword(String compPassword) {
		this.compPassword = compPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public List<OrganizationTenantShopVO> getShopList() {
		return shopList;
	}

	public void setShopList(List<OrganizationTenantShopVO> shopList) {
		this.shopList = shopList;
	}


}
