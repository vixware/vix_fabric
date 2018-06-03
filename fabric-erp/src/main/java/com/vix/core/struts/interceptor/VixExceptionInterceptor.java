package com.vix.core.struts.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.vix.common.base.action.BaseSecAction;
import com.vix.common.common.bo.MessageObject;
import com.vix.core.exception.BizException;
import com.vix.core.utils.JSonUtils;
import com.vix.core.validation.ValidateException;

public class VixExceptionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 3412128248715150833L;
	protected static final String OPER_SUCCESS = "操作成功!";
	protected static final String OPER_FAIL = "操作失败!";
	
	private static Logger logger = LoggerFactory.getLogger(VixExceptionInterceptor.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		MessageObject mo = null;
		try {
			result = invocation.invoke();
		} catch (ValidateException e) {
			//验证异常
			e.printStackTrace();
			logger.error("验证错误:",e);
			
			Map<String,String> msgMap = null;
			try {
				msgMap = JSonUtils.readValue(e.getMessage(), HashMap.class);
			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error("转换错误:",e1);
			}
			mo = new MessageObject(false, OPER_FAIL, msgMap);
		} catch (BizException e) {
			//业务异常  信息提示
			e.printStackTrace();
			logger.error("业务处理错误:",e);
			
			mo = new MessageObject(false, e.getMessage());
		} catch (Exception e) {
			//普通异常
			e.printStackTrace();
			logger.error("系统错误:",e);
		} 
		
		if(mo!=null){
			String resObjMsg = JSonUtils.toJSon(mo);
			BaseSecAction.renderJson(resObjMsg);
			return null;
		}
		return result;
	}
	

}
