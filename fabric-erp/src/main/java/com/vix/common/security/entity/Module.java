package com.vix.common.security.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;

/**
 * 模块
 * @author shadow
 *
 */
public class Module extends BaseEntity {
	
	//name
	
	/** 编码 */
	private String moduleCode;
	
	/**
	 * 行业模块
	 */
	private Set<IndustryManagementModule> industryManagementModules;
	
	/**
	 * 菜单业务分类
	 * BizConstants的COMMON_SECURITY_RESTYPE
	 * PC
	 * Pad
	 * Mobile
	 */
	//private String moduleType;
	
	 /** 权限菜单集合 */
	//@Deprecated
	//private Set<Authority> authoritys = new HashSet<Authority>(0);
	
	/** 账户 */
	//@Deprecated
	//private Set<UserAccount> userAccounts = new HashSet<UserAccount>(0);

	/*public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
*/
	/*public Set<Authority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(Set<Authority> authoritys) {
		this.authoritys = authoritys;
	}

	public Set<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}*/

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public Set<IndustryManagementModule> getIndustryManagementModules() {
		return industryManagementModules;
	}

	public void setIndustryManagementModules(
			Set<IndustryManagementModule> industryManagementModules) {
		this.industryManagementModules = industryManagementModules;
	}
	
}
