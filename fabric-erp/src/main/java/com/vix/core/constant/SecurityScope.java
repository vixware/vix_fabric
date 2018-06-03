package com.vix.core.constant;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统权限范围静态常量表
 *  
 * @author arron
 *
 */
public class SecurityScope implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 用户登录后的个人信息 */
	public static final String USER_LOGIN_SUCCESS_USERINFO = "userInfo";
	/** 用户登录后的个人所在承租户信息 */
	public static final String USER_LOGIN_SUCCESS_TENANTINFO = "tenantInfo";
	
	/** 用户登录后 的 url的拦截资源   map */
	public static final String USERINFO_AUTH_RES= "USER_AUTH_RES_MAP";
	/** 用户登陆后的权限功能点按钮的Set  */
	public static final String USERINFO_AUTH_FUN= "USER_AUTH_FUN_SET";
	
	/** 用户登录后 的 列权限  元数据配置 */
	public static final String USERINFO_AUTH_COLMETA= "USER_AUTH_COL_META";
	
	/** 用户登录后 的首页面的DIV显示 */
	public static final String USERINFO_INDEX_PDC= "USERINFO_INDEX_PDC";
	
	/** 用户所在公司的类型标识  */
	public static final String USER_ORG_TYPE="USER_ORG_TYPE";
	
	/** 用户所在公司的内部编码 */
	public static final String USER_ORG_INNERCODE="USER_ORG_INNERCODE";
	
	/**
	  * N  不使用
	  * P  只能查看自己数据
	  * A  集团公司人员可查看自己和所有子公司数据，  子公司人员只能查看本公司数据 
	  */
	public static final String USER_ORG_DATAFILTERTYPE="USER_ORG_DATAFILTERTYPE";
	
	/**	N  不使用 */
	public static final String USER_ORG_DATAFILTERTYPE_N ="N";
	/**	P  只能查看自己数据 */
	public static final String USER_ORG_DATAFILTERTYPE_P ="P";
	/**	A  集团公司人员可查看自己和所有子公司数据，  子公司人员只能查看本公司数据  */
	public static final String USER_ORG_DATAFILTERTYPE_A ="A";
	
	public static final Map<String,String> USER_ORG_DATAFILTERTYPE_MAP = new LinkedHashMap<String,String>() {{
		put(USER_ORG_DATAFILTERTYPE_P,"本公司数据");
		put(USER_ORG_DATAFILTERTYPE_A,"集团所有数据");
        put(USER_ORG_DATAFILTERTYPE_N,"不使用");
    }};
	
	
	
	
	
	

	/** 用户登录后  存储hql的限制条件 保存到session中的key	 */
	public static final String HQL_USER_OBJ = "HQL_USER_OBJ";
	/** 用户登录后  存储元数据对象全称   保存到session中的key	 */
	public static final String METADATA_USER_OBJ = "METADATA_USER_OBJ";
	
	
	
	/** 账号的职员所在部门的编码 session */
	public static final String USERACCOUNT_EMP_ORGUNIT_CODE="EMP_ORGUNIT_CODE";
	
	
	/** 账号的职员  分管  部门的编码   session */
	public static final String USERACCOUNT_EMP_PROXY_ORG_IDS="EMP_PROXY_ORG_CODE";
	/** 账号的职员  分管 部门的编码  session */
	public static final String USERACCOUNT_EMP_PROXY_ORGUNIT_IDS="EMP_PROXY_ORGUNIT_CODE";
	
	/** 账号的职员  分管  公司 对象   session */
	public static final String USERACCOUNT_EMP_PROXY_ORG_OBJ="EMP_PROXY_ORG_OBJ";
	/** 账号的职员  分管 部门 对象  session */
	public static final String USERACCOUNT_EMP_PROXY_ORGUNIT_OBJ="EMP_PROXY_ORGUNIT_OBJ";
	
	
	
	/** 用户级 */
	public static final int SECURITYSCOPE_USER_LEVEL = 1001;
	
	/** 当前部门级 */
	public static final int SECURITYSCOPE_CURRENTORG_LEVEL = 1002;
	
	/** 部门级 */
	public static final int SECURITYSCOPE_ORG_LEVEL = 1003;
	
	/** 部门及子部门级 */
	public static final int SECURITYSCOPE_ORGANDSUBORG_LEVEL = 1004;
	
	/** 系统级 */
	public static final int SECURITYSCOPE_SYSTEM_LEVEL = 1005;
	
	/** 当前部门及子部门级 */
	public static final int SECURITYSCOPE_CURRENTANDSUBORG_LEVEL = 1006;
}