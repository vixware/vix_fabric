/**    
 * 文件名：UserAccount.java    
 *    
 * 版本信息：    
 * 日期：2012-6-18    
 * Copyright wangmingchen  2012     
 * 版权所有    
 *    
 */
package com.vix.common.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.constant.SecurityDataLevel;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 
 * 项目名称：vix 类名称：UserAccount 类描述： 创建人：wangmingchen 创建时间：2012-6-18 下午2:35:18
 * 修改人：wangmingchen 修改时间：2012-6-18 下午2:35:18 修改备注：
 * 
 * @version
 * 
 * 			忽略实现的UserDetails接口 UserService中单独实现
 */
public class UserAccount extends BaseEntity implements org.springframework.security.core.userdetails.UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 注册用户名 */
	private String regUserName;

	/** 账号 */
	private String account;
	/** 密码 */
	private String password;
	/**
	 * 初始密码
	 */
	private String initpassword;
	/** 账号是否限制登录， 0:禁用 1：激活 */
	private String enable;

	/** 微信账号 */
	private String wxAccount;
	/** 手机 */
	private String mobile;
	private String telephone;
	/** 电子邮件 */
	private String email;
	/** 备用邮箱 */
	private String backupEmail;
	/** qq号 */
	private String qqNumber;
	/** 是否设置密保问题 */
	private String isSet = "N";

	/**
	 * 账户的业务分类
	 * 
	 */
	private String accountBizType;

	/** 职员 */
	private BaseEmployee employee;
	private CustomerAccount customerAccount;

	/** 角色集合 */
	private Set<Role> roles = new HashSet<Role>(0);

	/** 用户组集合 */
	// private Set<UserGroup> userGroups = new HashSet<UserGroup>(0);

	private String passwordConfirm;

	/**
	 * 登录成功后的跳转页面
	 */
	private String loginPage;

	/**
	 * C 传统方式 U 二级菜单升级到一级菜单方式 参见BizConstants.COMMON_SECURITY_USERMENUTYPE
	 */
	private String userResourceReadType;

	/**
	 * 模块 承租户管理使用
	 */
	private Set<Module> modules = new HashSet<Module>();

	// 非持久化属性

	/**
	 * 账号默认的安全级别 从帐号的岗位中读取（多岗位） 账号登录时从岗位中取得最高的密集数字放到账号中
	 */
	private Integer securityDataLevel = SecurityDataLevel.BIZ_SEC_DATA_LEVEL_MIN;

	/** 创建人id 测试 待删除 */
	private String createUserAccountId;

	/** 激活帐号的截至时间戳 */
	private Long activeEndTime;

	/** 组织架构id */
	private String orgId;

	/** 是否承租户创建管理员 */
	private Boolean isCompAdmin = false;

	/** 所有权限集合 */
	private Collection<? extends GrantedAuthority> allAuthoritys;

	/** 用户的所有角色编码集合 */
	private Set<ConfigAttribute> allRoleCodes;

	private Collection<GrantedAuthority> allArantedAuthorities;

	private HttpServletRequest request;

	private Date lastRequestDate;

	private String sessionId;

	/** 代理账号ID */
	private String proxyUserAccountId;
	/** 代理账号 */
	private String proxyUserAccountLoginName;
	/** 代理账号的职员姓名信息 */
	private String proxyUserAccountEmpName;
	/**
	 * 员工信息,非持久化属性,用于流程
	 */
	private String employeeName;
	/** 选取一个角色编码 */
	private String ftRoleCode;

	public String getCreateUserAccountId() {
		return createUserAccountId;
	}

	public void setCreateUserAccountId(String createUserAccountId) {
		this.createUserAccountId = createUserAccountId;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public UserAccount() {
		super();
	}

	public UserAccount(String id) {
		super();
		setId(id);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getInitpassword() {
		return initpassword;
	}

	public void setInitpassword(String initpassword) {
		this.initpassword = initpassword;
	}

	public BaseEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(BaseEmployee employee) {
		this.employee = employee;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getAccountBizType() {
		return accountBizType;
	}

	public void setAccountBizType(String accountBizType) {
		this.accountBizType = accountBizType;
	}

	public String getUserResourceReadType() {
		return userResourceReadType;
	}

	public void setUserResourceReadType(String userResourceReadType) {
		this.userResourceReadType = userResourceReadType;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public Integer getSecurityDataLevel() {
		return securityDataLevel;
	}

	public void setSecurityDataLevel(Integer securityDataLevel) {
		this.securityDataLevel = securityDataLevel;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getWxAccount() {
		return wxAccount;
	}

	public void setWxAccount(String wxAccount) {
		this.wxAccount = wxAccount;
	}

	public String getProxyUserAccountId() {
		return proxyUserAccountId;
	}

	public void setProxyUserAccountId(String proxyUserAccountId) {
		this.proxyUserAccountId = proxyUserAccountId;
	}

	public String getProxyUserAccountLoginName() {
		return proxyUserAccountLoginName;
	}

	public void setProxyUserAccountLoginName(String proxyUserAccountLoginName) {
		this.proxyUserAccountLoginName = proxyUserAccountLoginName;
	}

	public String getProxyUserAccountEmpName() {
		return proxyUserAccountEmpName;
	}

	public void setProxyUserAccountEmpName(String proxyUserAccountEmpName) {
		this.proxyUserAccountEmpName = proxyUserAccountEmpName;
	}

	public Long getActiveEndTime() {
		return activeEndTime;
	}

	public void setActiveEndTime(Long activeEndTime) {
		this.activeEndTime = activeEndTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBackupEmail() {
		return backupEmail;
	}

	public void setBackupEmail(String backupEmail) {
		this.backupEmail = backupEmail;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getIsSet() {
		return isSet;
	}

	public void setIsSet(String isSet) {
		this.isSet = isSet;
	}

	/*
	 * public Set<UserGroup> getUserGroups() { return userGroups; }
	 * 
	 * public void setUserGroups(Set<UserGroup> userGroups) { this.userGroups =
	 * userGroups; }
	 */
	public Collection<? extends GrantedAuthority> getAllAuthoritys() {
		if (allAuthoritys == null || allAuthoritys.isEmpty()) {
			allAuthoritys = loadAllAuthority();
		}
		return allAuthoritys;
	}

	public void setAllAuthoritys(Collection<? extends GrantedAuthority> allAuthoritys) {
		this.allAuthoritys = allAuthoritys;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (allAuthoritys == null || allAuthoritys.isEmpty()) {
			allAuthoritys = loadAllAuthority();
		}
		return allAuthoritys;
	}

	private Collection<? extends GrantedAuthority> loadAllAuthority() {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		if (getRoles() != null) {
			for (Role role : getRoles()) {
				Set<Authority> roleAuthority = role.getAuthoritys();
				for (Authority roa : roleAuthority) {
					authSet.add(new SimpleGrantedAuthority(roa.getAuthorityCode()));
				}
			}
		}

		return authSet;
	}

	@Override
	public String getUsername() {
		return account;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		boolean enabled = false;
		if ("1".equals(getEnable())) {
			enabled = true;
		}
		Date now = new Date();
		Date st = getStartTime();
		Date ed = getEndTime();
		st = (st == null ? now : st);
		ed = (ed == null ? now : ed);
		if (now.compareTo(st) >= 0 || now.compareTo(ed) <= 0) {
			enabled = true;
		}

		return enabled;
	}

	public Set<ConfigAttribute> getAllRoleCodes() {
		if (allRoleCodes == null || allRoleCodes.isEmpty()) {
			allRoleCodes = getUseAllRoleCodes();
		}
		return allRoleCodes;
	}

	public void setAllRoleCodes(Set<ConfigAttribute> allRoleCodes) {
		this.allRoleCodes = allRoleCodes;
	}

	private Set<ConfigAttribute> getUseAllRoleCodes() {
		Set<ConfigAttribute> authSet = new HashSet<ConfigAttribute>();
		if (getRoles() != null) {
			for (Role role : getRoles()) {
				authSet.add(new SecurityConfig(role.getRoleCode()));
			}
		}

		return authSet;
	}

	public String getOrgId() {
		if (null != employee && null != employee.getOrganizationUnitId()) {
			orgId = employee.getOrganizationUnitId();
		}
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Collection<GrantedAuthority> getAllArantedAuthorities() {
		return allArantedAuthorities;
	}

	public void setAllArantedAuthorities(Collection<GrantedAuthority> allArantedAuthorities) {
		this.allArantedAuthorities = allArantedAuthorities;
	}

	/**
	 * 标签使用
	 * 
	 * @return
	 */
	public Collection<GrantedAuthority> getUserAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if (getRoles() != null && getRoles().size() > 0) {
			for (Role role : getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));// GrantedAuthorityImpl
			}
		}
		return grantedAuthorities;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Date getLastRequestDate() {
		return lastRequestDate;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setLastRequestDate(Date lastRequestDate) {
		this.lastRequestDate = lastRequestDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRegUserName() {
		return regUserName;
	}

	public void setRegUserName(String regUserName) {
		this.regUserName = regUserName;
	}

	public Boolean getIsCompAdmin() {
		return isCompAdmin;
	}

	public void setIsCompAdmin(Boolean isCompAdmin) {
		this.isCompAdmin = isCompAdmin;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName
	 *            the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getFtRoleCode() {
		return ftRoleCode;
	}

	public void setFtRoleCode(String ftRoleCode) {
		this.ftRoleCode = ftRoleCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		UserAccount other = (UserAccount) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
