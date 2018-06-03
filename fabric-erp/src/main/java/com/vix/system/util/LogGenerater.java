package com.vix.system.util;

import java.io.Serializable;

import com.vix.common.log.entity.ErrorLog;

/**
 * 
 * 日志生成器
 * @author arron
 *
 */
public class LogGenerater implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 根据异常信息生成错误日志 */
	public static ErrorLog getErrorLog(Throwable t,String title){
		ErrorLog el = new ErrorLog();
		el.setTitle(title);
		if(null != t){
			el.setContent(t.getMessage());
		}
		return el;
	}
	
	/** 根据异常信息生成错误日志 */
	public static ErrorLog getErrorLog(Throwable t){
		ErrorLog el = new ErrorLog();
		if(null != t){
			el.setTitle(t.getMessage());
			el.setContent(t.getLocalizedMessage());
		}
		return el;
	}
	
	/** 根据异常信息生成错误日志 */
	public static ErrorLog getErrorLog(String title,String content){
		ErrorLog el = new ErrorLog();
		el.setTitle(title);
		el.setContent(content);
		return el;
	}
}
