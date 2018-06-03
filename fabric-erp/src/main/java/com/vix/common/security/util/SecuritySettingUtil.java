package com.vix.common.security.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.extprovider.VixLoginAuthenticationProvider;
import com.vix.common.security.util.extuserdetail.VixUsernamePasswordAuthenticationToken;
import com.vix.core.constant.SecurityScope;
import com.vix.core.utils.ContextUtil;
import com.vix.core.utils.SpringUtil;


/**
 * @ClassName: SecuritySettingUtil
 * @Description: 权限设置类
 * @author wangmingchen
 * @date 2015年1月31日 下午1:25:22
 */
public final class SecuritySettingUtil {
	//http://stackoverflow.com/questions/4664893/how-to-manually-set-an-authenticated-user-in-spring-security-springmvc
	//http://stackoverflow.com/questions/4664893/how-to-manually-set-an-authenticated-user-in-spring-security-springmvc/4672083#4672083
	//http://javahotpot.blogspot.in/2013/12/spring-security-adding-more-information.html
	//http://ev9d9.blogspot.com/2012/12/http-basic-authentication-in-grails.html
	      
	/**
	 * @Title: modifyCurrentUserCompanyInnerCode
	 * @Description: 更改登录用户的部门编码和公司编码
	 * @param @param departmentCode
	 * @param @param companyInnerCode    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public static void modifyCurrentUserCompanyInnerCode(String departmentCode,String companyInnerCode) {
		UserAccount oldUa = SecurityUtil.getCurrentUserAccount();
		UserDetails ud = oldUa;
		
		if(StringUtils.isNotEmpty(departmentCode) || StringUtils.isNotEmpty(companyInnerCode)){
			if(StringUtils.isNotEmpty(departmentCode)){
				oldUa.setDepartmentCode(departmentCode);
				ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_ORGUNIT_CODE,departmentCode);
			}
			if(StringUtils.isNotEmpty(companyInnerCode)){
				oldUa.setCompanyInnerCode(companyInnerCode);
				//设定session中  公司和部门编码ContextUtil.getSessionAttr(SecurityScope.USER_ORG_INNERCODE)
				ContextUtil.setSessionAttr(SecurityScope.USER_ORG_INNERCODE,companyInnerCode);
			}
			Authentication authentication = new VixUsernamePasswordAuthenticationToken(oldUa, ud.getPassword(), oldUa.getTenantId(), ud.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//设定账号上下文
			ContextUtil.setSessionAttr(SecurityScope.USER_LOGIN_SUCCESS_USERINFO,oldUa);
		}
		
	}
	
	
    public static Authentication doAuthLogin(UserAccount newLoginUserAccount,HttpServletRequest request,HttpServletResponse response){
    	VixUsernamePasswordAuthenticationToken token = new VixUsernamePasswordAuthenticationToken(newLoginUserAccount, newLoginUserAccount.getPassword(), 
    			newLoginUserAccount.getTenantId(), newLoginUserAccount.getAuthorities());
    	token.setDetails(new WebAuthenticationDetails(request));
    	VixLoginAuthenticationProvider authenticationProvider = SpringUtil.getBean("loginAuthenticationProvider");
    	Authentication authentication = authenticationProvider.authenticate(token);
    	/*
    	SecurityContext ctx = SecurityContextHolder.createEmptyContext();
    	SecurityContextHolder.setContext(ctx);
    	*/
    	SecurityContext ctx = SecurityContextHolder.getContext();
    	ctx.setAuthentication(authentication);
    	
    	request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", ctx);
    	
    	//缺少实现
    	/*VixAuthSuccessHandler vixAuthSuccessHandler = SpringUtil.getBean("authSuccessHandler");
		vixAuthSuccessHandler.onAuthenticationSuccess(request,response,authentication);*/
    	return authentication;
    }
    
    public static final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    
    public static void main(String[] args) {
    }
}
