/**
 * 
 */
package com.vix.WebService.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.vix.WebService.filter.ExtendStruts2Filter
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-22
 */
public class ExtendStruts2Filter extends StrutsPrepareAndExecuteFilter {
	private static Logger log = LoggerFactory.getLogger(ExtendStruts2Filter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		try {
			HttpServletRequest request = (HttpServletRequest) req;
			// 判断是否是向WebService发出的请求
			if (request.getRequestURI().contains("/services")) {
				// 如果是来自向CXFService发出的请求
				chain.doFilter(req, res);
			} else {
				// 默认action请求
				super.doFilter(req, res, chain);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
