package com.vix.common.tag;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.security.util.SecurityUtil;
import com.vix.common.tag.util.VixVarTenantUtil;
import com.vix.core.utils.ContextUtil;
import com.vix.core.utils.SpringUtil;
import com.vix.system.systemVar.dao.ISystemVarDao;

/**
 * @ClassName: VixVariableFunction
 * @Description:  系统变量显示函数
 * @author wangmingchen
 * @date 2014年12月23日 上午8:39:59
 */
public class VixVariableFunction {

	public static String DEFAULT_VIEW_VALUE="";
	
	public static ContextUtil cu = new ContextUtil();
	
	public static String view(String varCode){
		
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		if(StringUtils.isNotEmpty(varCode)){
			//Object tenantVarMap = ServletActionContext.getServletContext().getAttribute("VIX_TENANT_VAR_"+tenantId);
			if(StringUtils.isEmpty(tenantId)){
				tenantId = "";
			}
			Object tenantVarMap = cu.getApplicationAttr(VixVarTenantUtil.VAR_KEY_P+tenantId);
			String resStr = "";
			if(tenantVarMap != null ){
				Map<String,String> tenantMap = (Map<String, String>) tenantVarMap;
				/*for(Map.Entry<String, String> entry:tenantMap.entrySet()){
					String key = entry.getKey();
					String value = entry.getValue();
					System.out.println(key + "###" +value);
				}*/
				Object varTenantMapObj = tenantMap.get(VixVarTenantUtil.VAR_KEY_P+tenantId);
				if(varTenantMapObj!=null){
					Map<String,String> varTenantMap = (Map<String, String>) varTenantMapObj;
					resStr = varTenantMap.get(varCode);
				}
				
			}else{
				tenantVarMap = new ConcurrentHashMap<String,Map<String,String>>(0);
				Map tenantVarMapNew = (Map) tenantVarMap;
				ISystemVarDao svDao = SpringUtil.getBean("systemVarDao");
				Map<String,String> tenantValMap = svDao.findSystemVarDisplayViewByTenant(tenantId);
				
				tenantVarMapNew.put(VixVarTenantUtil.VAR_KEY_P+tenantId, tenantValMap);
				cu.setApplicationAttr(VixVarTenantUtil.VAR_KEY_P+tenantId, tenantVarMapNew);
				
				resStr = tenantValMap.get(varCode);
			}
			if(StringUtils.isEmpty(resStr)){
				resStr = "";
			}
			return resStr;
		}
		
		return DEFAULT_VIEW_VALUE;
	}
}
