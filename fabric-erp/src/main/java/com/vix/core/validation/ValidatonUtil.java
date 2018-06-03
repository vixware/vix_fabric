package com.vix.core.validation;

import java.util.Map;

import com.vix.core.utils.JSonUtils;

public class ValidatonUtil {

	/**
	 * @Title: validator
	 * @Description: bean的校验器工具调用
	 * @param @param vd
	 * @param @param isThrowExp
	 * @param @param request
	 * @param @return    设定文件
	 * @return Map<String,String>    返回类型
	 * @throws
	 */
	public static Map<String,String> validator(Validator vd,Object vdEntity,
				Boolean isThrowExp){//, HttpServletRequest request
		//vd.va
		vd.setValidatorObject(vdEntity);
		vd.validate();
		Map<String,String> msgMap = vd.getMsgMap();
		if(msgMap.isEmpty()){
			return msgMap;
		}
		if(isThrowExp){
			String jsonMsg = JSonUtils.toJSon(msgMap);
			throw new ValidateException(jsonMsg);
		}
		return msgMap;
	}
}
