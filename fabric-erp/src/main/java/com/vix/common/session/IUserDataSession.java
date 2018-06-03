/**
 * 
 */
package com.vix.common.session;

import javax.servlet.http.HttpSession;

/**
 * 在登录用户的session中加载所属企业的属性信息，如行业；以及 用户的权限信息等
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-14
 */
public interface IUserDataSession {
	/**
	 * 将用户所属企业的信息set到session中
	 * 
	 * @throws Exception
	 */
	public void setHttpSession() throws Exception;

	/**
	 * 获取session信息
	 * 
	 * @throws Exception
	 */
	public HttpSession getHttpSession() throws Exception;

}
