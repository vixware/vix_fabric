package com.vix.nvix.wx.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BizConstant {

    public static final String COMMON_SECURITY_ROLE_RoleType_S ="S";
    
    public static final String COMMON_SECURITY_ROLE_RoleType_B ="B";
    
    public static final Map COMMON_SECURITY_ROLE_RoleType = new HashMap() {{
        put("S","系统角色");
        put("B","业务角色");
    }};
   
    
    //帐号的业务分类
    /** 账号业务分类  系统类型 */
    public static final String COMMON_SECURITY_ACCOUNT_BizType_Sys ="sys";
    /** 分销模块 */
    public static final String COMMON_SECURITY_ACCOUNT_BizType_FX ="fx";
    
    
    
    
    public static final String COMMON_SECURITY_AUTHORITY_TYPE_M ="M";
    
    public static final String COMMON_SECURITY_AUTHORITY_TYPE_F ="F";
    
    public static final String COMMON_SECURITY_AUTHORITY_TYPE_R ="R";
    
    public static final String COMMON_SECURITY_AUTHORITY_TYPE_O="O";
    
    public static final Map<String,String> COMMON_SECURITY_AUTHORITY_TYPE = new HashMap<String,String>() {{
        put("M","菜单");
        put("F","功能");
        //put("F","界面资源");
        //put("F","其他资源");
    }};
    
    
    /** 个人菜单读取方式  普通方式*/
    public static final String COMMON_SECURITY_USERMENUTYPE_C ="C";
    
    /** 个人菜单读取方式   二级菜单升级到一级菜单方式（显示到一级菜单）*/
    public static final String COMMON_SECURITY_USERMENUTYPE_U ="U";
    
    public static final Map<String,String> COMMON_SECURITY_USERMENUTYPE = new HashMap<String,String>() {{
        put(COMMON_SECURITY_USERMENUTYPE_C,"C");
        put(COMMON_SECURITY_USERMENUTYPE_U,"U");
    }};
    
    
    /** 菜单类型  PC  */
    public static final String COMMON_SECURITY_RESTYPE_P ="P";
    /** 菜单类型  MOBILE */
    public static final String COMMON_SECURITY_RESTYPE_M ="M";
    /** 菜单类型   PAD */
    public static final String COMMON_SECURITY_RESTYPE_D ="D";
    
    public static final Map<String,String> COMMON_SECURITY_RESTYPE = new LinkedHashMap<String,String>() {{
        
        put(COMMON_SECURITY_RESTYPE_M,"MOBILE");
        put(COMMON_SECURITY_RESTYPE_D,"PAD");
        put(COMMON_SECURITY_RESTYPE_P,"PC");
    }};
   /* public static final Map<String,String> COMMON_SECURITY_RESTYPE = new HashMap<String,String>() {{
       
        put(COMMON_SECURITY_RESTYPE_M,"MOBILE");
        put(COMMON_SECURITY_RESTYPE_D,"PAD");
        put(COMMON_SECURITY_RESTYPE_P,"PC");
    }};*/
    
    
    /** 超级管理员 */
    public static final String ROLE_SUPERADMIN="ROLE_SUPER_ADMIN";
    /** 公司超级管理原前缀 */
    public static final String ROLE_COMP_PRE_SUPERADMIN="ROLE_SUPER_ADMIN_CP_";
    
    
    
    /** 公司 */
    public static final String COMMON_ORG_C ="C";
    /** 部门  */
    public static final String COMMON_ORG_O ="O";
    /** 职员  */
    public static final String COMMON_ORG_E ="E";
    /** 角色  */
    public static final String COMMON_ORG_R ="R";
    
    
    /** 业务组织视图 */
    public static final String COMMON_BIZORG_V ="V";
    /** 业务组织 */
    public static final String COMMON_BIZORG_O ="O";
    
    
    /** 业务组织视图类型  审批业务视图*/
    public static final String COMMON_BIZORG_V_TYPE_SP ="SP";
    /** 业务组织视图类型 项目业务视图*/
    public static final String COMMON_BIZORG_V_TYPE_XM ="XM";
    /** 业务组织视图类型 矩阵业务视图 */
    public static final String COMMON_BIZORG_V_TYPE_JZ ="JZ";
    
    public static final Map<String,String> COMMON_BIZORG_V_TYPE = new HashMap<String,String>() {{
        put(COMMON_BIZORG_V_TYPE_SP,"审批业务视图");
        put(COMMON_BIZORG_V_TYPE_XM,"项目业务视图");
        put(COMMON_BIZORG_V_TYPE_JZ,"矩阵业务视图");
        //put("F","界面资源");
        //put("F","其他资源");
    }};
    
    
    
    /** 公告 */
    public static final String COMMON_BULLETIN_GG ="GG";
    /** 通知  */
    public static final String COMMON_BULLETIN_TZ ="TZ";
    
    public static final Map<String,String> COMMON_BULLETIN = new LinkedHashMap<String,String>() {{
        put(COMMON_BULLETIN_GG,"公告");
        put(COMMON_BULLETIN_TZ,"通知");
        //put("F","界面资源");
        //put("F","其他资源");
    }};
    
    /** 邮件协议*/
    public static final String COMMON_EMAIL_SMTP ="smtp";
    /** 邮件协议 */
    public static final String COMMON_EMAIL_POP3 ="pop3";
    /** 邮件协议 */
    public static final String COMMON_EMAIL_IMAP ="imap";
    
    /** 邮件类型 临时邮件*/
    public static final Integer COMMON_EMAIL_TYPE_TMP =-1;
    /** 邮件类型 草稿*/
    public static final Integer COMMON_EMAIL_TYPE_CG =0;
    /** 邮件类型 已发邮件*/
    public static final Integer COMMON_EMAIL_TYPE_YF =1;
    /** 邮件类型 待发送*/
    public static final Integer COMMON_EMAIL_TYPE_DFS =2;
    /** 邮件类型 收邮件*/
    public static final Integer COMMON_EMAIL_TYPE_SJX = 3;
    /** 邮件类型 垃圾箱*/
    public static final Integer COMMON_EMAIL_TYPE_LJX = 4;
    /** 邮件类型 已（从垃圾箱）删除*/
    public static final Integer COMMON_EMAIL_TYPE_REALDEL =5;
    
    
    
    /** 附件格式   邮件附件 */
    public static final String COMMON_ATT_EMAIL_ATT ="EMAIL_ATT";
    /** 附件格式  邮件EML */
    public static final String COMMON_ATT_EMAIL_EML ="EML";
    
    public static final Map<String,String> COMMON_ATT_EMAIL = new LinkedHashMap<String,String>() {{
        put(COMMON_ATT_EMAIL_ATT,"邮件附件");
        put(COMMON_ATT_EMAIL_EML,"邮件EML");
        //put("F","界面资源");
        //put("F","其他资源");
    }};
    
    
    /** 文件状态 临时*/
    public static final Integer COMMON_FILETYPE_TMP = 0;
    /** 文件状态 非临时*/
    public static final Integer COMMON_FILETYPE_NOTMP =1;
    
    
    
    
	//元数据属性的简单类型
    /** 元数据属性的简单类型 整型*/
    public static final String COMMON_METADATA_DATATYPE_Integer = "Integer";
    /** 元数据属性的简单类型 长整型*/
    public static final String COMMON_METADATA_DATATYPE_Long = "Long";
    /** 元数据属性的简单类型 双精度*/
    public static final String COMMON_METADATA_DATATYPE_Double = "Double";
    /** 元数据属性的简单类型 日期*/
    public static final String COMMON_METADATA_DATATYPE_Date = "Date";
    /** 元数据属性的简单类型 字符串*/
    public static final String COMMON_METADATA_DATATYPE_String = "String";
    
    public static final Map<String,String> COMMON_METADATA_DATATYPE = new LinkedHashMap<String,String>() {{
    	put(null,"无");
        put(COMMON_METADATA_DATATYPE_Integer,"整型");
        put(COMMON_METADATA_DATATYPE_Long,"长整型");
        put(COMMON_METADATA_DATATYPE_Double,"双精度");
        put(COMMON_METADATA_DATATYPE_Date,"日期");
        put(COMMON_METADATA_DATATYPE_String,"字符串");
    }};
    
    
	//元数据属性的简单类型
    /** 模块权限编码  首页*/
	public static final String COMMON_SECURITY_MOD_SHOUYE = "shouye_";
    /** 模块权限编码  供应链*/
	public static final String COMMON_SECURITY_MOD_GONGYINGLIAN = "chain_";
    /** 模块权限编码 生产管理*/
	public static final String COMMON_SECURITY_MOD_SHENGCHAN = "shengchan_";
    /** 模块权限编码  财务会计*/
	public static final String COMMON_SECURITY_MOD_CAIWUKUAIJI = "caiwukuaiji_";
    /** 模块权限编码  管理会计*/
	public static final String COMMON_SECURITY_MOD_GUANLIKUAIJI = "guanlikuaiji_";
    /** 模块权限编码  人力资源*/
	public static final String COMMON_SECURITY_MOD_RENLIZIYUAN = "hr_";
    /** 模块权限编码  协同办公*/
	public static final String COMMON_SECURITY_MOD_XIETGONGBANGONG = "oa_";
	/** 模块权限编码 企业资产*/
	public static final String COMMON_SECURITY_MOD_QIYEZICHAN = "qiyezichan_";
	/** 模块权限编码  主数据管理*/
	public static final String COMMON_SECURITY_MOD_ZHUSHUJU = "mdm_";
	/** 模块权限编码  系统管理*/
	public static final String COMMON_SECURITY_MOD_XITONG = "system_";
	
    public static final Map<String,String> COMMON_SECURITY_MOD = new LinkedHashMap<String,String>() {{
        put(COMMON_SECURITY_MOD_SHOUYE,"首页");
        put(COMMON_SECURITY_MOD_GONGYINGLIAN,"供应链");
        put(COMMON_SECURITY_MOD_SHENGCHAN,"生产管理");
        put(COMMON_SECURITY_MOD_CAIWUKUAIJI,"财务会计");
        put(COMMON_SECURITY_MOD_GUANLIKUAIJI,"管理会计");
        put(COMMON_SECURITY_MOD_RENLIZIYUAN,"人力资源");
        put(COMMON_SECURITY_MOD_XIETGONGBANGONG,"协同办公");
        put(COMMON_SECURITY_MOD_QIYEZICHAN,"企业资产");
        put(COMMON_SECURITY_MOD_ZHUSHUJU,"主数据管理");
        put(COMMON_SECURITY_MOD_XITONG,"系统管理");
    }};
    
    
    
    /** 数据字典 可增加子项*/
    public static final String COMMON_SYSTEM_EDITORTYPE_ADD = "1";
    /** 数据字典 可编辑子项*/
    public static final String COMMON_SYSTEM_EDITORTYPE_EDIT = "2";
    /** 数据字典 可删除子项*/
    public static final String COMMON_SYSTEM_EDITORTYPE_DEL = "3";
    
    public static final Map<String,String> COMMON_SYSTEM_EDITORTYPE = new LinkedHashMap<String,String>() {{
        put(COMMON_SYSTEM_EDITORTYPE_ADD,"可增加子项");
        put(COMMON_SYSTEM_EDITORTYPE_EDIT,"可编辑子项");
        put(COMMON_SYSTEM_EDITORTYPE_DEL,"可删除子项");
    }};
}
