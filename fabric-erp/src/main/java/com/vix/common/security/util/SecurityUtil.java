package com.vix.common.security.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.common.share.entity.BaseOrganizationUnit;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SecurityScope;
import com.vix.core.utils.ContextUtil;
import com.vix.mdm.crm.entity.CustomerAccount;

import jodd.util.RandomStringUtil;

public final class SecurityUtil {

	public static String getTopAuthorityCode(String authCode) {
		if (org.springframework.util.StringUtils.hasText(authCode)) {
			int s1 = StringUtils.indexOf(authCode, "_");
			return StringUtils.substring(authCode, 0, s1 + 4);
		}
		return null;
	}

	/**
	 * @Title: getParentAuthorityCode @Description: 返回为空
	 *         则说明没有父权限编码 @param @param authCode @param @return 设定文件 @return
	 *         String 返回类型 @throws
	 */
	public static String getParentAuthorityCode(String authCode) {
		int s1 = StringUtils.indexOf(authCode, "_");
		int maxSize = s1 + 4;
		int strSize = authCode.length();
		// System.out.println("MaxSize:"+maxSize+";strSize"+str.length());
		if (strSize > maxSize) {
			return StringUtils.substring(authCode, 0, strSize - 3);
		} else {
			return null;
		}
	}

	/**
	 * 取得当前用户的登录名, 如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUserName() {
		Authentication auth = getAuthentication();
		if (auth == null)
			return "";
		return auth.getName();
	}

	/**
	 * 取得当前用户, 返回值为SpringSecurity的User类及其子类, 如果当前用户未登录则返回null.
	 */
	public static UserDetails getCurrentUser() {
		Authentication auth = getAuthentication();
		UserDetails principal = null;
		if (auth != null) {
			principal = (UserDetails) auth.getPrincipal();
			if (principal == null)
				return null;
		}

		return principal;
	}

	/**
	 * 取得当前用户, 返回值为SpringSecurity的User类的id, 如果当前用户未登录则返回0.
	 */
	public static String getCurrentUserId() {
		Authentication authentication = getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal == null) {
			return null;
		} else {
			UserAccount u = (UserAccount) principal;
			return u.getId();
		}
	}

	/**
	 * 获取当前登录账号
	 * 
	 * @return
	 */
	public static UserAccount getCurrentUserAccount() {
		Authentication authentication = getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal == null) {
			return null;
		} else {
			UserAccount u = (UserAccount) principal;
			return u;
		}
	}

	/**
	 * 获取tenantId
	 * 
	 * @return
	 */
	public static String getCurrentUserTenantId() {
		Authentication authentication = getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal == null) {
			return null;
		} else {
			UserAccount u = (UserAccount) principal;
			return u.getTenantId();
		}
	}

	public static String getTenantRandomString() {
		String tenantId = getCurrentUserTenantId();
		if (StringUtils.isEmpty(tenantId)) {
			tenantId = "";
		}
		return tenantId + "#" + RandomStringUtil.randomAlphaNumeric(32);
	}

	public static Set<ConfigAttribute> getUserAccountRoles() {
		UserAccount ua = getCurrentUserAccount();
		Set<ConfigAttribute> roleAttrSet = ua.getAllRoleCodes();
		if (roleAttrSet == null) {
			return new HashSet<ConfigAttribute>();
		}
		return roleAttrSet;
	}

	/**
	 * @Title: isSuperAdmin @Description: 是否超级管理员 @param @return 设定文件 @return
	 *         Boolean 返回类型 @throws
	 */
	public static Boolean isSuperAdmin() {
		Collection<GrantedAuthority> userAuthorities = SecurityUtil.getCurrentUserAccount().getUserAuthorities();
		if (userAuthorities == null || userAuthorities.isEmpty()) {
			return false;
		}
		for (GrantedAuthority ga : userAuthorities) {
			if (ga.getAuthority().equals(BizConstant.ROLE_SUPERADMIN)) {
				return true;
			}
		}
		return false;
	}

	public static BaseEmployee getCurrentRealUser() {
		Authentication authentication = getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal == null) {
			return null;
		} else {
			UserAccount u = (UserAccount) principal;
			return u.getEmployee();
		}
	}
	public static String getBindHomeTemplateCode() {
		Authentication authentication = getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal == null) {
			return null;
		} else {
			UserAccount u = (UserAccount) principal;
			if (u != null) {
				for (Role role : u.getRoles()) {
					if (role != null && role.getHomeTemplate() != null) {
						return role.getHomeTemplate().getTypeCode();
					}
				}
			}
		}
		return null;
	}
	public static String getBindHomeTemplateId() {
		Authentication authentication = getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal == null) {
			return null;
		} else {
			UserAccount u = (UserAccount) principal;
			if (u != null) {
				for (Role role : u.getRoles()) {
					if (role != null && role.getHomeTemplate() != null) {
						return role.getHomeTemplate().getId();
					}
				}
			}
		}
		return null;
	}

	public static CustomerAccount getCustomerAccount() {
		Authentication authentication = getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal == null) {
			return null;
		} else {
			UserAccount u = (UserAccount) principal;
			return u.getCustomerAccount();
		}
	}

	/**
	 * 获取当前员工id
	 * 
	 * @return
	 */
	public static String getCurrentEmpId() {
		Authentication authentication = getAuthentication();
		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (principal == null) {
			return null;
		} else {
			UserAccount u = (UserAccount) principal;
			BaseEmployee emp = u.getEmployee();
			if (emp != null) {
				return emp.getId();
			}
		}
		return null;
	}

	/**
	 * 得到登录用户的CompInnerCode
	 * 
	 * @return
	 */
	public static String getCurrentUserAccountCompanyInnerCode() {
		UserAccount ua = getCurrentUserAccount();
		if (ua != null) {
			String compInnerCode = ua.getCompanyInnerCode();
			return compInnerCode;
		}
		return null;
	}

	public static String getCurrentUserAccountCompanyCode() {
		UserAccount ua = getCurrentUserAccount();
		if (ua != null) {
			String companyCode = ua.getCompanyCode();
			return companyCode;
		}
		return null;
	}

	/**
	 * 取得当前用户登录IP, 如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUserIp() {
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			Object details = authentication.getDetails();
			if (details instanceof WebAuthenticationDetails) {
				WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
				return webDetails.getRemoteAddress();
			}
		}

		return "";
	}

	/**
	 * 取得Authentication, 如当前SecurityContext为空时返回null.
	 */
	public static Authentication getAuthentication() {
		Authentication authentication = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			authentication = context.getAuthentication();
			return authentication;
		}
		return authentication;
	}

	/**
	 * 添加参数
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String addParam(String url, Map<String, Object> param) {
		StringBuffer urlBuf = new StringBuffer(url);
		if (!url.contains("?")) {
			urlBuf.append("?a=1");
		}
		if (param != null) {
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				urlBuf.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		return urlBuf.toString();
	}

	/**
	 * 添加tenantId
	 * 
	 * @param url
	 * @param tenantId
	 * @return
	 */
	public static String addParamTenantId(String loginName, String url, String tenantId) {
		if (StringUtils.isEmpty(loginName)) {
			return url;
		}

		if (loginName.equalsIgnoreCase(BizConstant.USERACCOUNT_SUPERADMIN)) {
			return url;
		}

		StringBuffer urlBuf = new StringBuffer(url);
		/*
		 * if(!url.contains("?")){ urlBuf.append("?a=1"); }
		 */
		int pos = url.lastIndexOf("?");
		int length = url.length();

		if (!url.contains("?")) {
			// s.charAt(s.length()-1)=='?'
			urlBuf.append("?");
		} else if (pos != (length - 1)) {
			urlBuf.append("&");
		}
		urlBuf.append("tenantId=").append(tenantId);
		return urlBuf.toString();
	}

	public static String getCookiePath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		return contextPath.length() > 0 ? contextPath : "/";
	}

	/**
	 * 添加tenantId
	 * 
	 * @param url
	 * @return
	 * 
	 * 		public static String addParamTenantId(String url){ UserAccount ua
	 *         = getCurrentUserAccount(); String tenantId = ua.getTenantId();
	 *         return addParamTenantId(url,tenantId); }
	 */

	/**
	 * 得到当前登录人员的公司内部编码
	 */
	public static String getCurrentEmpOrgInnerCode() {
		if (!ContextUtil.hasContext()) {
			return null;
		}

		Object orgInnerCodeObj = ContextUtil.getSessionAttr(SecurityScope.USER_ORG_INNERCODE);
		if (orgInnerCodeObj != null) {
			return (String) orgInnerCodeObj;
		}
		return null;
	}

	/**
	 * 得到当前登录人员的公司的数据过滤类型
	 */
	public static String getCurrentEmpOrgDataFilterType() {
		if (!ContextUtil.hasContext()) {
			return null;
		}

		Object orgDataFilterTypeObj = ContextUtil.getSessionAttr(SecurityScope.USER_ORG_DATAFILTERTYPE);
		if (orgDataFilterTypeObj != null) {
			return (String) orgDataFilterTypeObj;
		}
		return null;
	}

	/**
	 * 是否是根节点集团公司人员
	 * 
	 * @return
	 */
	public static Boolean getCurrentEmpIsRoot() {
		Object userOrgTypeObj = ContextUtil.getSessionAttr(SecurityScope.USER_ORG_TYPE);
		if (userOrgTypeObj != null) {
			String userOrgType = (String) userOrgTypeObj;
			if (userOrgType.equals(BizConstant.COMMON_BOOLEAN_YES)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 得到当前登录人员的职员的所在部门编码
	 */
	public static String getCurrentEmpOrgUnitCode() {
		if (!ContextUtil.hasContext()) {
			return null;
		}
		Object orgUnitCodeObj = ContextUtil.getSessionAttr(SecurityScope.USERACCOUNT_EMP_ORGUNIT_CODE);
		if (orgUnitCodeObj != null) {
			return (String) orgUnitCodeObj;
		}
		return null;
	}

	/**
	 * 是否有代理信息 （公司和部门） @Title: hasProxyData @param @return 设定文件 @return Boolean
	 * 返回类型 @throws
	 */
	public static Boolean hasProxyData() {
		Object proxyOrg = ContextUtil.getSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORG_IDS);
		Object proxyOrgUnit = ContextUtil.getSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORG_IDS);
		if (proxyOrg != null || proxyOrgUnit != null) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getProxyAllOrg @Description: 获取分管公司信息 @param @return 设定文件 @return
	 *         Set<BaseOrganization> 返回类型 @throws
	 */
	public static Set<BaseOrganization> getProxyAllOrg() {
		Object proxyOrg = ContextUtil.getSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORG_IDS);
		if (proxyOrg != null) {
			Set<BaseOrganization> proxyOrgObj = (Set<BaseOrganization>) proxyOrg;
			return proxyOrgObj;
		}
		return null;
	}

	/**
	 * @Title: getProxyAllOrgUnit @Description: TODO @param @return 设定文件 @return
	 *         String 返回类型 @throws
	 */
	public static Set<BaseOrganizationUnit> getProxyAllOrgUnit() {
		Object proxyUnit = ContextUtil.getSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORGUNIT_IDS);
		if (proxyUnit != null) {
			Set<BaseOrganizationUnit> proxyOrgUnitSet = (Set<BaseOrganizationUnit>) proxyUnit;
			return proxyOrgUnitSet;
		}
		return null;
	}

	/**
	 * @Title: getProxyOrgIds @Description: 获取分管公司信息 没有则返回null
	 *         不可频繁调用 @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String getProxyAllOrgIds() {
		String proxyOrgIds = null;
		Set<BaseOrganization> proxyOrgObj = getProxyAllOrg();
		if (proxyOrgObj != null) {
			/*
			 * String proxyOrgStr = (String)proxyOrg;
			 * if(StrUtils.isNotEmpty(proxyOrgStr)){ proxyOrgIds = proxyOrgStr;
			 * }
			 */
			List<String> proxyOrgId = new ArrayList<String>();
			for (BaseOrganization po : proxyOrgObj) {
				proxyOrgId.add("'" + po.getCompanyInnerCode() + "'");
			}
			proxyOrgIds = StringUtils.join(proxyOrgId, ",");
		}
		return proxyOrgIds;
	}

	/**
	 * @Title: getProxyOrgUnitIds @Description: 获取分管部门信息 没有则返回null,
	 *         不可频繁调用 @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String getProxyAllOrgUnitIds() {
		String proxyUnitIds = null;
		Set<BaseOrganizationUnit> proxyOrgUnitSet = getProxyAllOrgUnit();
		if (proxyOrgUnitSet != null) {
			/*
			 * String proxyUnitStr = (String)proxyUnit;
			 * if(StrUtils.isNotEmpty(proxyUnitStr)){ proxyUnitIds =
			 * proxyUnitStr; }
			 */
			List<String> proxyOrgUnitId = new ArrayList<String>();
			for (BaseOrganizationUnit pu : proxyOrgUnitSet) {
				proxyOrgUnitId.add("'" + pu.getCode() + "'");
			}
			proxyUnitIds = StringUtils.join(proxyOrgUnitId, ",");
		}
		return proxyUnitIds;
	}

	public static void main(String[] args) {
		// String s = "asdasdasdd?1=2";
		// System.out.println(RandomStringUtil.randomAlphaNumeric(32));

		// System.out.println(s.charAt(s.length()-1)=='?');
		// System.out.println(s.lastIndexOf("?"));
		// System.out.println(s.length());
		// String tt = addParamTenantId(s, "123");
		// System.out.println(tt);

		String authCode = "sm_001";
		System.out.println(getParentAuthorityCode(authCode));
	}
}
