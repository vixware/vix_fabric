package com.vix.core.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BizConstant {

	/** 标识 tenantId 的 key,并且是  绑定到HQL中的 变量名  */
	public static final String COMMON_GLOBAL_FLAG_TENANTID_KEY = "tenantId";
	/** 标识不加载tenantId 的 条件  */
	public static final String COMMON_GLOBAL_FLAG_TENANTID_KEY_NO = "-1";
	
	
	/** HQL的 条件  tenantId 属性 */
	public static final String COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID="tenantId";
	
	
	/** 标识 companyInnerCode 的 key,并且是  绑定到HQL中的 变量名  */
	public static final String COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY = "companyInnerCode";
	/** 标识不加载companyCode 的 条件  */
	public static final String COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY_NO = "-2";
	/** HQL的 条件  companyCode 属性 */
	public static final String COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE="companyInnerCode";
	
	/** 标识 departmentCode 的 key,并且是  绑定到HQL中的 变量名  */
	public static final String COMMON_GLOBAL_FLAG_PROXY_KEY = "proxyCode";
	/** 标识不加载departmentCode 的 条件  */
	public static final String COMMON_GLOBAL_FLAG_PROXY_KEY_NO = "-3";
	
	/** HQL的 条件  departmentCode 属性
	 *  16777216
	 *  */
	public static final String COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE="departmentCode";

	
	public static final String COMMON_BOOLEAN_YES = "Y";
	public static final String COMMON_BOOLEAN_NO  = "N";
    public static final Map<String,String> COMMON_BOOLEAN_TYPE = new HashMap<String,String>() {{
        put(COMMON_BOOLEAN_YES,"是");
        put(COMMON_BOOLEAN_NO,"否");
    }};
	
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
    
    
    
    public static final String COMMON_LANUAGUAGE_TYPE_ZH ="ZH";
    
    public static final String COMMON_LANUAGUAGE_TYPE_EN ="F";
    
    public static final Map<String,String> COMMON_LANUAGUAGE_TYPE = new HashMap<String,String>() {{
        put(COMMON_LANUAGUAGE_TYPE_ZH,"中文");
        put(COMMON_LANUAGUAGE_TYPE_EN,"英文");
    }};
    
    
    
    
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
        put(COMMON_SECURITY_RESTYPE_P,"PC");
        put(COMMON_SECURITY_RESTYPE_M,"MOBILE");
        put(COMMON_SECURITY_RESTYPE_D,"PAD");
    }};
  /*  public static final Map<String,String> COMMON_SECURITY_RESTYPE = new HashMap<String,String>() {{
       
        put(COMMON_SECURITY_RESTYPE_M,"MOBILE");
        put(COMMON_SECURITY_RESTYPE_D,"PAD");
        put(COMMON_SECURITY_RESTYPE_P,"PC");
    }};*/
    
    
    /** 超级管理员角色 */
    public static final String ROLE_SUPERADMIN="ROLE_SUPER_ADMIN";
    /** 超级管理员 用户名*/
    public static final String USERACCOUNT_SUPERADMIN="superadmin";
    
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
    
    /** 默认标识 */
    public static final String COMMON_DEFAULT_FLAG = "DEFAULT";
    
    
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
    
    
    /** 部门类型 基准部门	*/
    public static final String COMMON_ORGUNIT_TYPE_JZBM ="JZBM";
    /** 部门类型 销售办公室	*/
    public static final String COMMON_ORGUNIT_TYPE_XSBGS ="XSBGS";
    /** 部门类型 销售组	*/
    public static final String COMMON_ORGUNIT_TYPE_XSZ ="XSZ";
    /** 部门类型 销售组织	*/
    public static final String COMMON_ORGUNIT_TYPE_XSZZ ="XSZZ";
    
    
    public static final Map<String,String> COMMON_ORGUNIT_TYPE_MAP = new HashMap<String,String>() {{
        put(COMMON_ORGUNIT_TYPE_JZBM," 基准部门");
        put(COMMON_ORGUNIT_TYPE_XSBGS,"销售办公室");
        put(COMMON_ORGUNIT_TYPE_XSZ,"销售组");
        put(COMMON_ORGUNIT_TYPE_XSZZ,"销售组织");
    }};
    
    public static final List<String> COMMON_ORGUNIT_TYPE_XS_LIST = new ArrayList<String>() {{
    	add(COMMON_ORGUNIT_TYPE_XSBGS);
    	add(COMMON_ORGUNIT_TYPE_XSZ);
    	add(COMMON_ORGUNIT_TYPE_XSZZ);
    }};
    public static final Set<String> COMMON_ORGUNIT_TYPE_XS_SET = new HashSet<String>() {{
    	add(COMMON_ORGUNIT_TYPE_XSBGS);
    	add(COMMON_ORGUNIT_TYPE_XSZ);
    	add(COMMON_ORGUNIT_TYPE_XSZZ);
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

//工作台,供应链,生产管理,财务会计,管理会计,人力资源,协同办公,企业资产管理,主数据管理,系统管理
//TM_,   SCM_, MM_,     FI_,    MA_,     HR_,    OA_,     EAM_,       MD_,       SM_
    /** 模块权限编码  工作台,*/
	public static final String COMMON_SECURITY_MOD_SHOUYE = "TM_";
    /** 模块权限编码  供应链*/
	public static final String COMMON_SECURITY_MOD_GONGYINGLIAN = "SCM_";
    /** 模块权限编码 生产管理*/
	public static final String COMMON_SECURITY_MOD_SHENGCHAN = "MM_";
    /** 模块权限编码  财务会计*/
	public static final String COMMON_SECURITY_MOD_CAIWUKUAIJI = "FI_";
    /** 模块权限编码  管理会计*/
	public static final String COMMON_SECURITY_MOD_GUANLIKUAIJI = "MA_";
    /** 模块权限编码  人力资源*/
	public static final String COMMON_SECURITY_MOD_RENLIZIYUAN = "HR_";
    /** 模块权限编码  协同办公*/
	public static final String COMMON_SECURITY_MOD_XIETGONGBANGONG = "OA_";
	/** 模块权限编码 企业资产*/
	public static final String COMMON_SECURITY_MOD_QIYEZICHAN = "EAM_";
	/** 模块权限编码  主数据管理*/
	public static final String COMMON_SECURITY_MOD_ZHUSHUJU = "MD_";
	/** 模块权限编码  系统管理*/
	public static final String COMMON_SECURITY_MOD_XITONG = "SM";
	
    public static final Map<String,String> COMMON_SECURITY_MOD = new LinkedHashMap<String,String>() {{
        put(COMMON_SECURITY_MOD_SHOUYE,"工作台");
        put(COMMON_SECURITY_MOD_GONGYINGLIAN,"供应链");
        put(COMMON_SECURITY_MOD_SHENGCHAN,"生产管理");
        put(COMMON_SECURITY_MOD_CAIWUKUAIJI,"财务会计");
        put(COMMON_SECURITY_MOD_GUANLIKUAIJI,"管理会计");
        put(COMMON_SECURITY_MOD_RENLIZIYUAN,"人力资源");
        put(COMMON_SECURITY_MOD_XIETGONGBANGONG,"协同办公");
        put(COMMON_SECURITY_MOD_QIYEZICHAN,"企业资产管理");
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
    
    
    
    
    /**************hr 相关 *************************************************************/
    
    //薪酬
   
    /** 工资项类型  输入项 */
    public static final String HR_SAL_ITEMTYPE_SRX ="SRX";
    /** 工资项类型  定值项 */
    public static final String HR_SAL_ITEMTYPE_DZX ="DZX";
    /** 工资项类型  计算项*/
    public static final String HR_SAL_ITEMTYPE_JSX ="JSX";
    /** 工资项类型  所得税 */
    public static final String HR_SAL_ITEMTYPE_SDS ="SDS ";
    
    /** 
     * 工资项类型		计算项/输入项/定值/所得税
     */
    public static final Map<String,String> HR_SAL_ITEMTYPE = new LinkedHashMap<String,String>() {{
        put(HR_SAL_ITEMTYPE_SRX,"输入项");
        put(HR_SAL_ITEMTYPE_DZX,"定值项");
        put(HR_SAL_ITEMTYPE_JSX,"计算项");
        put(HR_SAL_ITEMTYPE_SDS,"所得税");
    }};
    
    
    
    /** 工资辅助项类型  业务对象值*/
    public static final String HR_SAL_OPTYPE_YWDX ="YWDX";
    /** 工资辅助项类型   变值类型值） */
    public static final String HR_SAL_OPTYPE_BLLX ="BLLX";
    
    /** 
     * 业务对象值/变值类型值） YWDX 业务对象 BLLX 变值类型值
     */
    public static final Map<String,String> HR_SAL_OPTYPE = new HashMap<String,String>() {{
        put(HR_SAL_OPTYPE_YWDX,"业务对象");
        put(HR_SAL_OPTYPE_BLLX,"变值类型");
    }};
    
    
    
    
    /** 公式选择项类型      工资项    */
    public static final String HR_SAL_FORMULA_TYPE_GZX ="G";
    /** 公式选择项类型      工资辅助项    */
    public static final String HR_SAL_FORMULA_TYPE_FZX ="F";

    public static final Map<String,String> HR_SAL_FORMULA_TYPE = new LinkedHashMap<String,String>() {{
        
        put(HR_SAL_FORMULA_TYPE_GZX,"工资项");
        put(HR_SAL_FORMULA_TYPE_FZX,"工资辅助项");
    }};
}
