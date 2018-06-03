package com.vix.core.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vix.core.constant.BizConstant;

public class ContextUtil {
	
	public static ServletContext getServletContext() {
		return getRequest().getServletContext();
	}
	
	public static String getAppRealPath(String path) {
		//return ServletActionContext.getServletContext().getRealPath(path);
		return getRequest().getServletContext().getRealPath(path);
	}

	public static String getAppRoot() {
		return getAppRealPath("/");
	}

	public static String getAppCxtPath() {
		//return ServletActionContext.getRequest().getContextPath();
		return getRequest().getContextPath();
	}

	public static int getServerPort() {
		//return ServletActionContext.getRequest().getServerPort();
		return getRequest().getServerPort();
	}

	public void logout() {
		//HttpSession session = ServletActionContext.getRequest().getSession(false);
		HttpSession session = getRequest().getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	public static HttpServletRequest getRequest() {
		//HttpServletRequest request = ServletActionContext.getRequest();
		//return request;
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		//HttpServletRequest request = attr.getRequest();
		return attr.getRequest();
	}
	
	public static HttpServletResponse getResponse4Struts() {
		return ServletActionContext.getResponse();
		//HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		/*HttpServletRequest request = getRequest();
		ServletWebRequest swr = new ServletWebRequest(request);
		HttpServletResponse response = swr.getResponse();
		return response;*/
	}

	public Object getRequestAttr(String key) {
		//return ServletActionContext.getRequest().getAttribute(key);
		return getRequest().getAttribute(key);
	}

	/**
	 * 是否有上下文
	 * @return
	 */
	public static Boolean hasContext(){
		/*if(ServletActionContext.getContext()==null){
			return false;
		}
		return true;*/
		HttpServletRequest req = getRequest();
		if(req==null){
			return false;
		}
		return true;
	}
	
	public void setRequestAttr(String key, Object value) {
		//ServletActionContext.getRequest().setAttribute(key, value);
		getRequest().setAttribute(key, value);
	}

	public static Object getSessionAttr(String key) {
		/*
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if (session == null) {
			return null;
		} else {
			return session.getAttribute(key);
		}*/
		
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		} else {
			return session.getAttribute(key);
		}
	}

	public static void setSessionAttr(String key, Object value) {
		//HttpSession session = ServletActionContext.getRequest().getSession();
		HttpSession session = getRequest().getSession();
		session.setAttribute(key, value);
	}

	public static void removeAttribute(String key) {
		//HttpSession session = ServletActionContext.getRequest().getSession();
		HttpSession session = getRequest().getSession();
		session.removeAttribute(key);
	}

	public Object getApplicationAttr(String key) {
		//return ServletActionContext.getServletContext().getAttribute(key);
		return getRequest().getServletContext().getAttribute(key);
		
	}

	public void setApplicationAttr(String key, Object value) {
		//ServletActionContext.getServletContext().setAttribute(key, value);
		getRequest().getServletContext().setAttribute(key, value);
	}

	public String getSessionId(boolean isCreate) {
		//HttpSession session = ServletActionContext.getRequest().getSession(isCreate);
		HttpSession session = getRequest().getSession(isCreate);
		if (session == null) {
			return null;
		} else {
			return session.getId();
		}
	}

	public String getRemoteIp() {
		//return ServletActionContext.getRequest().getRemoteAddr();
		return getRequest().getRemoteAddr();
	}

	public int getRemotePort() {
		//return ServletActionContext.getRequest().getRemotePort();
		return getRequest().getRemotePort();
	}

	public String getRequestURL() {
		//return ServletActionContext.getRequest().getRequestURL().toString();
		return getRequest().getRequestURL().toString();
	}

	public String getRequestBrowser() {
		String userAgent = getRequestUserAgent();
		String[] agents = userAgent.split(";");
		if (agents.length > 1) {
			return agents[1].trim();
		} else {
			return null;
		}
	}

	public String getRequestOs() {
		String userAgent = getRequestUserAgent();
		String[] agents = userAgent.split(";");
		if (agents.length > 2) {
			return agents[2].trim();
		} else {
			return null;
		}
	}

	public String getRequestUserAgent() {
		//HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletRequest req = getRequest();
		String userAgent = req.getHeader("user-agent");
		return userAgent;
	}

	public void addCookie(Cookie cookie) {
		//ServletActionContext.getResponse().addCookie(cookie);
		getResponse4Struts().addCookie(cookie);
	}

	public Cookie getCookie(String name) {
		//Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		Cookie[] cookies = getRequest().getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		return null;
	}

	public boolean isMethodPost() {
		//String method = ServletActionContext.getRequest().getMethod();
		String method = getRequest().getMethod();
		if ("post".equalsIgnoreCase(method)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 查询用户的登录方式
	 * 默认是BizConstant.COMMON_SECURITY_RESTYPE_P        PC
	 * @return
	 */
	public static String getUserMenuContextType(){
		//return BizConstant.COMMON_SECURITY_RESTYPE_D;
		return BizConstant.COMMON_SECURITY_RESTYPE_P;
	}
	
	public static String getUserMenuContextType(HttpServletRequest request){
		//return BizConstant.COMMON_SECURITY_RESTYPE_D;
		return BizConstant.COMMON_SECURITY_RESTYPE_P;
	}
}
