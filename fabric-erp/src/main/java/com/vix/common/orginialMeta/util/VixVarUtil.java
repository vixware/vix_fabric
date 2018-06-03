package com.vix.common.orginialMeta.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.vix.core.utils.ContextUtil;

public class VixVarUtil {

	public static String VAR_KEY_P = "VIX_TENANT_VAR_";
	
	public static ContextUtil cu = new ContextUtil();
	
	public static void refreshVixTenantVar(String tenantId){
		if(StringUtils.isEmpty(tenantId)){
			tenantId = "";
		}
		removeVar(VAR_KEY_P+tenantId);
	}
	
	private static void removeVar(String val){
		ServletActionContext.getServletContext().removeAttribute(val);
	}
}
