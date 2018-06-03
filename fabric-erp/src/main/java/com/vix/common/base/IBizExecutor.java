package com.vix.common.base;

import java.util.Map;


/**
 * IBizProcess Interface
 * @author Jackie
 * 
 * 定义流程引擎的相关使用接口
 *
 */
public interface IBizExecutor {

	/**
	 * executeParameterProcess 从request对象中获取参数,分析后构造对应的业务对象.
	 * @return Map
	 * 返回结果中的Map按照一个标准格式保存了处理的结果.
	 * HttpServletRequest request = ServletActionContext.getRequest();
	 * String Value = new String(request.getParameter("KEY").getBytes("ISO8859-1"), "UTF-8"); 
	 */
	public abstract Map<String, Object> executeParameterProcess();	
	
	/**
	 * execute 实现每个Action的具体业务逻辑.
	 * @param parameter
	 * @throws Exception
	 */
	public abstract void execute(Map<String, Object> parameter) throws Exception;
    //{
	//   
	//}
	
	/**
	 * executeLogger 在重载后实现对具体实现Action类的日志输出
	 */
	public abstract void executeLogger();
	
}
