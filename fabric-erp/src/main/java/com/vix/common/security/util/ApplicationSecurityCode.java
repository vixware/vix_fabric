package com.vix.common.security.util;

public class ApplicationSecurityCode {

	/**
	 * 功能点按钮
	 */
	//public static Map<String, Collection<ConfigAttribute>> permissionCodeMap=null;
	
	/**
     * 权限的map
     */
	//public static Map<RequestMatcher, Collection<ConfigAttribute>> resourceMap = null;
	
	
	/**
	 * hql方法拦截的map   key 拦截的类方法全称    value basemetadata的 id
	 * 修改为
	 * hql方法拦截的map   key 拦截的类方法全称    value hqlMethod的 id
	
	public static BiMap<String,Long> hqlMethodMetadataMap = null;
     */
    /**
	 * 重置系统权限信息
	 */
//	public static void toReloadVixResourceMap(){
//		resourceMap=null;
//	}
	
	/**
	 * 重置系统功能点信息
	 */
//	public static void toReloadVixFunctionMap(){
//		ApplicationSecurityCode.permissionCodeMap=null;
//	}
//	
//	public static void refreshObject(){
//		resourceMap=null;
//		ApplicationSecurityCode.permissionCodeMap=null;
//		
//		SecurityTag.permissionCodeMap = null;
//	}
	
	
	/*public static void refreshHqlMethodMetataMap(){
		hqlMethodMetadataMap = null;
	}*/
}
