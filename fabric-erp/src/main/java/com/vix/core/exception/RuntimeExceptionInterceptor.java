package com.vix.core.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.log.entity.ErrorLog;
import com.vix.common.log.service.ILogService;

/**
 * 
 * 全局异常拦截器，分析异常信息，写入数据库，主要处理业务层和数据层的异常信息
 * @author arron
 *
 */
@Service("runtimeExceptionInterceptor")
public class RuntimeExceptionInterceptor implements MethodInterceptor {

	@Autowired
	private ILogService logService;

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object obj = "";
		try {
			obj = methodInvocation.proceed();
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			ErrorLog sl = new ErrorLog();
			sl.setTitle(ex.getClass().getName());
			/** 分析异常 */
			String content = dealExceptionLog(ex);
			sl.setContent(content);
			//logService.save(sl);
			throw ex;
		}
		return obj;
	}
	
	/** 分析异常 */
	private String dealExceptionLog(Throwable e){
		if(null == e){
			return "";
		}
		StringBuilder content = new StringBuilder();
		/** 获取异常堆栈 */
		StackTraceElement[] stackTrace = e.getStackTrace();
		for(StackTraceElement stackTraceElement : stackTrace){
			content.append(stackTraceElement.getClassName()+"-"+stackTraceElement.getMethodName());
		}
		return content.toString();
	}
}
