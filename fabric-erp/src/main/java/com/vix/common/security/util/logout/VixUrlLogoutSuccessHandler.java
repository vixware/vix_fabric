package com.vix.common.security.util.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;

/**
 * URL处理
 * @author Administrator
 *
 */
public class VixUrlLogoutSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		//super.handle(request, response, authentication);
		handle(request, response, authentication);
	}

	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, 
			Authentication authentication)throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response);
		
		//ContextUtil.setSessionAttr(SecurityScope.USER_LOGIN_SUCCESS_USERINFO, user);
		/*Object obj = request.getSession().getAttribute(SecurityScope.USER_LOGIN_SUCCESS_USERINFO);
		System.out.println("-------------"+obj);*/
		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		
		//用户已被清空   不能通过SecurityUtil.getUserAccount取得UserAccount   NullException
		if(authentication!=null){//session is Invalid 
			UserAccount ua = (UserAccount)authentication.getPrincipal();
			String tenantId = ua.getTenantId();
			targetUrl = SecurityUtil.addParamTenantId(ua.getAccount(),targetUrl, tenantId);
		}

		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
}
