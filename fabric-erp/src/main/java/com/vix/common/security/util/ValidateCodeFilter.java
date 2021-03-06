package com.vix.common.security.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.vix.common.security.util.extuserdetail.VixUsernamePasswordAuthenticationToken;

public class ValidateCodeFilter extends UsernamePasswordAuthenticationFilter {  
	
	private boolean postOnly = true;  
	private boolean allowEmptyValidateCode = true;  
	private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;  
	private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;  
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";  
	// session中保存的验证码  
	public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "ValidateCode";  
	// 输入的验证码  
	public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "validateCode";
	
	
	//自定义FailHandler
	//private AuthenticationFailureHandler failureHandler = new AuthFailureHandler();
	
	@Override  
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {  
	    if (postOnly && !request.getMethod().equals("POST")) {  
	        throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());  
	    }  
	    String username = obtainUsername(request);  
	    String password = obtainPassword(request);  
	    String tenantId = request.getParameter("tenantId");
	    if (username == null) {  
	        username = "";  
	    }  
	    if (password == null) {  
	        password = "";  
	    }
	    if(tenantId==null){
	    	tenantId="";
	    }
	    
	    username = username.trim(); 
	    tenantId = tenantId.trim();
	    
	   // UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);  
	    //VixUsernamePasswordAuthenticationToken authRequest = new VixUsernamePasswordAuthenticationToken(username, password, tenantId);
	    VixUsernamePasswordAuthenticationToken authRequest = new VixUsernamePasswordAuthenticationToken(username, password, tenantId);
	    
	    HttpSession session = request.getSession(false);  
	    if (session != null || getAllowSessionCreation()) {  
	        request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY,TextEscapeUtils.escapeEntities(username));  
	    }
	    // Allow subclasses to set the "details" property  
	    setDetails(request, authRequest);  
	    // check validate code  
	    if (!isAllowEmptyValidateCode()){  
	        checkValidateCode(request);  
	    }
	    return this.getAuthenticationManager().authenticate(authRequest);  
	}  
	
	/** 比较session中的验证码和用户输入的验证码是否相等 */  
	protected void checkValidateCode(HttpServletRequest request) {  
	    String sessionValidateCode = obtainSessionValidateCode(request);  
	    String validateCodeParameter = obtainValidateCodeParameter(request);  
	    if (StringUtils.isEmpty(validateCodeParameter)  
	            || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {  
	        throw new AuthenticationServiceException("验证码错误！");  
	    }  
	}  
	
	private String obtainValidateCodeParameter(HttpServletRequest request) {  
	    return request.getParameter(validateCodeParameter);  
	}  
	
	protected String obtainSessionValidateCode(HttpServletRequest request) {  
	    Object obj = request.getSession()  
	            .getAttribute(sessionvalidateCodeField);  
	    return null == obj ? "" : obj.toString();  
	}  
	
	
	
	/**
	 * 覆盖 失败处理
	 * @return
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        
		SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString());
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler" + getFailureHandler());
        }

        //getRememberMeServices().loginFail(request, response);

        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
	
	public boolean isPostOnly() {  
	    return postOnly;  
	}  
	
	@Override  
	public void setPostOnly(boolean postOnly) {  
	    this.postOnly = postOnly;  
	}  
	
	public String getValidateCodeName() {  
	    return sessionvalidateCodeField;  
	}  
	
	public void setValidateCodeName(String validateCodeName) {  
	    this.sessionvalidateCodeField = validateCodeName;  
	}  
	
	public boolean isAllowEmptyValidateCode() {  
	    return allowEmptyValidateCode;  
	}  
	
	public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {  
	    this.allowEmptyValidateCode = allowEmptyValidateCode;  
	}
}  