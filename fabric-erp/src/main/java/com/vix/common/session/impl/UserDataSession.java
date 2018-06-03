package com.vix.common.session.impl;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.vix.common.session.IUserDataSession;

/**
 * @author zhanghaibing
 * 
 * @date 2013-10-14
 */
public class UserDataSession implements IUserDataSession {
	private HttpSession session = getSession();

	@Override
	public void setHttpSession() throws Exception {
	}

	@Override
	public HttpSession getHttpSession() throws Exception {
		return this.session;
	}

	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
}
