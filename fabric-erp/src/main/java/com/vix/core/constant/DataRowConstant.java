package com.vix.core.constant;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;



public final class DataRowConstant {
	
	//以下是系统自定义常量 
	public static final Map<String,String> sysparamMap = new LinkedHashMap<String,String>();
	
	public static final String SYS_CUR_USERACCOUNT = "{SysUserAccount}";
	
	public static final String SYS_CUR_ROLE = "{SysRole}";
	
	public static final String SYS_CUR_EMPLOYEE = "{SysEmployee}";
	
	public static final String SYS_CUR_EMPLOYEE_ORG_UNIT = "{SysEmployeeOrgUnit}";
	
	
	

	//以下是系统自定义常量 的转换hql参数名
	public static final BiMap<String,String> sysparamValueMap = HashBiMap.create();
	
	public static final String SYS_CUR_USERACCOUNT_VALUE = ":_SysUserAccount_";
	
	public static final String SYS_CUR_ROLE_VALUE = ":_SysRole_";
	
	public static final String SYS_CUR_EMPLOYEE_VALUE = ":_SysEmployee_";
	
	public static final String SYS_CUR_EMPLOYEE_ORG_UNIT_VALUE = ":_SysEmployeeOrgUnit_";
	
	
	
	public static final String SYS_CUR_USERACCOUNT_VALUE_KEY = "_SysUserAccount_";
	
	public static final String SYS_CUR_ROLE_VALUE_KEY = "_SysRole_";
	
	public static final String SYS_CUR_EMPLOYEE_VALUE_KEY = "_SysEmployee_";
	
	public static final String SYS_CUR_EMPLOYEE_ORG_UNIT_VALUE_KEY = "_SysEmployeeOrgUnit_";
	
	
	static{
		sysparamMap.put(SYS_CUR_USERACCOUNT, "当前账号");
		sysparamMap.put(SYS_CUR_ROLE, "当前角色");
		sysparamMap.put(SYS_CUR_EMPLOYEE, "当前职员");
		sysparamMap.put(SYS_CUR_EMPLOYEE_ORG_UNIT, "所属部门");
		
		sysparamValueMap.put(SYS_CUR_USERACCOUNT, SYS_CUR_USERACCOUNT_VALUE);
		sysparamValueMap.put(SYS_CUR_ROLE,SYS_CUR_ROLE_VALUE);
		sysparamValueMap.put(SYS_CUR_EMPLOYEE, SYS_CUR_EMPLOYEE_VALUE);
		sysparamValueMap.put(SYS_CUR_EMPLOYEE_ORG_UNIT, SYS_CUR_EMPLOYEE_ORG_UNIT_VALUE);
	}
	
	
	public static void handleValueType(Map<String,Object> params){
		if(params!=null){
			if(params.containsKey(SYS_CUR_USERACCOUNT_VALUE_KEY)){
				Object objValue = params.get(SYS_CUR_USERACCOUNT_VALUE_KEY);
				params.put(SYS_CUR_USERACCOUNT_VALUE_KEY,  Long.valueOf(String.valueOf(objValue)));
			}else if( params.containsKey(SYS_CUR_ROLE_VALUE_KEY)){
				Object objValue = params.get(SYS_CUR_ROLE_VALUE_KEY);
				params.put(SYS_CUR_ROLE_VALUE_KEY, Long.valueOf(String.valueOf(objValue)));
			}else if(params.containsKey(SYS_CUR_EMPLOYEE_VALUE_KEY)){
				Object objValue = params.get(SYS_CUR_EMPLOYEE_VALUE_KEY);
				params.put(SYS_CUR_EMPLOYEE_VALUE_KEY,Long.valueOf(String.valueOf(objValue)));
			}else if(params.containsKey(SYS_CUR_EMPLOYEE_ORG_UNIT_VALUE_KEY)){
				Object objValue = params.get(SYS_CUR_EMPLOYEE_ORG_UNIT_VALUE_KEY);
				params.put(SYS_CUR_EMPLOYEE_ORG_UNIT_VALUE_KEY,Long.valueOf(String.valueOf(objValue)));
			}
		}
	}
	
}
