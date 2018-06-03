package com.vix.common.securityDra.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.DataRowConstant;

public class HandleVixSecurityContext {

	public static Object getContextValue(String securityContextParamConstants){
		if(securityContextParamConstants.equalsIgnoreCase(DataRowConstant.SYS_CUR_USERACCOUNT)){
			return SecurityUtil.getCurrentUserId();
		}else if(securityContextParamConstants.equalsIgnoreCase(DataRowConstant.SYS_CUR_ROLE)){
			UserAccount ua = SecurityUtil.getCurrentUserAccount();
			Set<Role> roleSet = ua.getRoles();
			List<String> idList = new ArrayList<String>();
			if(roleSet!=null){
				for(Role r:roleSet){
					idList.add(r.getId());
				}
			}
			return StringUtils.join(idList.iterator(), ",");
		}else if(securityContextParamConstants.equalsIgnoreCase(DataRowConstant.SYS_CUR_EMPLOYEE)){
			String empId = SecurityUtil.getCurrentEmpId();
			return empId;
		}else if(securityContextParamConstants.equalsIgnoreCase(DataRowConstant.SYS_CUR_EMPLOYEE_ORG_UNIT)){
			//String empOrgUnitId = SecurityUtil.getCurrentEmpId();
			//return empOrgUnitId;
		}
		return null;
	}
}
