package com.vix.core.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.vix.core.constant.vo.IndexPage;
import com.vix.core.utils.UserAgentUtil;

/**
 * 权限配置
 * @author Administrator
 *
 */
public class SecurityConfigConstant {
	
	public static final String SPRING_SECURITY_NO_AUTH_CONST="NO_AUTH";
	
	
	/**
	 * 登录账户如果没有配置承租户id  则跳转到页面进行配置
	 */
	public static final String USERACCOUNT_CONFIG_URL_NO_TENANTID = "/common/vixAction!goSetup.action";
	
	
	/*** 系统登录页面的相关配置 */
	//public static final String SECURITY_LOGIN_PAGE_0 = "/WEB-INF/content/common/vix_index.jsp";
	/** 系统默认界面   不能修改 */
	public static final String SECURITY_LOGIN_PAGE_0 = "vixAction!goDefaultPage";
	
	public static final String SECURITY_LOGIN_PAGE_1 = "vix3Action!test";

	public static final String SECURITY_LOGIN_PAGE_2 = "/index2.jsp";
	
	public static final String SECURITY_LOGIN_PAGE_3 = "/index3.jsp";

	public static final String SECURITY_LOGIN_PAGE_4 = "/index4.jsp";
	
	
	/** 系统默认界面  key   不能修改 */
	public static final String SECURITY_LOGIN_PAGE_0_key = "page0";
	
	public static final String SECURITY_LOGIN_PAGE_1_key = "page1";

	public static final String SECURITY_LOGIN_PAGE_2_key = "page2";
	
	public static final String SECURITY_LOGIN_PAGE_3_key = "page3";

	public static final String SECURITY_LOGIN_PAGE_4_key = "page4";
	
	
	/**
	 * 键值 和 描述的映射
	 */
	public static final Map<String,String> SECURITY_LOGIN_PAGE_NOTE = new LinkedHashMap<String,String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(SECURITY_LOGIN_PAGE_0_key, "默认");
			put(SECURITY_LOGIN_PAGE_1_key, "系统人员");
			put(SECURITY_LOGIN_PAGE_2_key, "销售人员");
			put(SECURITY_LOGIN_PAGE_3_key, "业务人员");
			put(SECURITY_LOGIN_PAGE_4_key, "生产人员");
		}
	};
	
	/**
	 * 键值 和 跳转url 的映射
	 */
	public static final Map<String,String> SECURITY_LOGIN_PAGE_KEY = new LinkedHashMap<String,String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(SECURITY_LOGIN_PAGE_0_key, SECURITY_LOGIN_PAGE_0);
			put(SECURITY_LOGIN_PAGE_1_key, SECURITY_LOGIN_PAGE_1);
			put(SECURITY_LOGIN_PAGE_2_key, SECURITY_LOGIN_PAGE_2);
			put(SECURITY_LOGIN_PAGE_3_key, SECURITY_LOGIN_PAGE_3);
			put(SECURITY_LOGIN_PAGE_4_key, SECURITY_LOGIN_PAGE_4);
		}
	};
	
	
	/**
	 * 键值 和 不同客户端访问地址  映射
	 */
	public static final Map<String,IndexPage> SECURITY_LOGIN_PAGE_MAPPAGE = new LinkedHashMap<String,IndexPage>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			//默认映射
			IndexPage key0 = new IndexPage("/WEB-INF/content/common/vix_index.jsp", "/WEB-INF/content/common/vix_index_pad.jsp", "/WEB-INF/content/common/vix_index_mobile.jsp") ;
			
			IndexPage key1 = new IndexPage("/index0_pc.jsp", "/index0_pad.jsp", "/index0_mobile.jsp") ;
			IndexPage key2 = new IndexPage("/index1_pc.jsp", "/index1_pad.jsp", "/index1_mobile.jsp") ;
			IndexPage key3 = new IndexPage("/index2_pc.jsp", "/index2_pad.jsp", "/index2_mobile.jsp") ;
			IndexPage key4 = new IndexPage("/index3_pc.jsp", "/index3_pad.jsp", "/index3_mobile.jsp") ;
			
			
			put(SECURITY_LOGIN_PAGE_0_key, key0);
			put(SECURITY_LOGIN_PAGE_1_key, key1);
			put(SECURITY_LOGIN_PAGE_2_key, key2);
			put(SECURITY_LOGIN_PAGE_3_key, key3);
			put(SECURITY_LOGIN_PAGE_4_key, key4);
		}
	};
	
	
	/**
	 * 根据key 和 客户端类型  返回应该登陆的首页面
	 * @param key
	 * @return
	 */
	public static String getUserIndexPage(String key){
		if(StringUtils.isEmpty(key)){
			return null;
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String via = request.getHeader("Via");
		String userAgent = request.getHeader("user-agent");
		
		
		IndexPage page = SECURITY_LOGIN_PAGE_MAPPAGE.get(key);
		/*if(true){
			return page.getMobilePage();
		}*/
		
		if(UserAgentUtil.isPC(userAgent)){
			if(page!=null){
				return page.getPcPage();
			}
		}else if(UserAgentUtil.isMobile(via, userAgent)){
			if(page!=null){
				return page.getMobilePage();
			}
		}else{
			//pad
			if(page!=null){
				return page.getPadPage();
			}
		}
		
		return null;
	}
	
	
	
	/** 默认登录页面 */
	//public static final String LOGIN_TYPE_PAGE_NAV_DEFAULT = "/WEB-INF/content/login.jsp";
	public static final String LOGIN_TYPE_PAGE_NAV_DEFAULT = "/WEB-INF/vixnt/common/login.jsp";
	/**  移动页面 */
	public static final String LOGIN_TYPE_PAGE_NAV_MOBILE = "/WEB-INF/phone/login_phone.jsp";
	
	
	/** 默认登录页面 key */
	public static final String LOGIN_TYPE_PAGE_NAV_DEFAULT_KEY = "DEFAULT";
	/**  移动页面 key */
	public static final String LOGIN_TYPE_PAGE_NAV_MOBILE_KEY = "MOBILE";
	
	public static final Map<String,String> LOGIN_TYPE_PAGE_NAV_MAP = new HashMap<String,String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(LOGIN_TYPE_PAGE_NAV_DEFAULT_KEY, LOGIN_TYPE_PAGE_NAV_DEFAULT);
			put(LOGIN_TYPE_PAGE_NAV_MOBILE_KEY, LOGIN_TYPE_PAGE_NAV_MOBILE);
		}
	};
}
