package com.vix.core.persistence.hibernate.util;

import java.util.HashMap;
import java.util.Map;

import com.vix.core.constant.BizConstant;

public final class HqlTenantIdUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 不处理TenantId
	 * @param pamramMap
	 */
	public static void handleParamMap4TenantId(Map<String,Object> pamramMap){
		if(pamramMap==null){
			pamramMap = new HashMap<String, Object>();
		}
		
		pamramMap.put(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY, BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY_NO);
	}
	
	/**
	 * 不处理CompanyInnerCode
	 * @param pamramMap
	 */
	public static void handleParamMap4CompanyInnerCode(Map<String,Object> pamramMap){
		if(pamramMap==null){
			pamramMap = new HashMap<String, Object>();
		}
		pamramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY_NO);
	}
}
