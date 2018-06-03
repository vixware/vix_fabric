package com.vix.core.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class BizOcConstant {

	/**
	 * OC默认承租户编码
	 */
	public static final String OC_DEFAULT_TENANT_CODE = "DEF_T";

	/**
	 * OC默认公司编码
	 */
	public static final String OC_DEFAULT_TENANT_COMPANYINNERCODE = "DEF_T_COMP";

	/** VIX */
	public static final String OC_SERVICE_TNT_TYPE_VIX = "ERP";
	/** 协作 */
	public static final String OC_SERVICE_TNT_TYPE_NVIX = "NV";
	/** 微信 */
	public static final String OC_SERVICE_TNT_TYPE_WX = "WX";
	/** 电商 SDP */
	public static final String OC_SERVICE_TNT_TYPE_EB = "EB";
	/** O2O */
	public static final String OC_SERVICE_TNT_TYPE_O2O = "O2O";

	/** 基础业务 */
	public static final String OC_SERVICE_TNT_TYPE_BIZ = "BIZ";
	/** 其他 */
	public static final String OC_SERVICE_TNT_TYPE_OTHER = "OTHER";
	/** OC */
	public static final String OC_SERVICE_TNT_TYPE_OC = "OC";

	/** 平台类型 */
	public static final Map<String, String> OC_SERVICE_TNT_TYPE = new LinkedHashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		{
			put(OC_SERVICE_TNT_TYPE_VIX, "ERP");
			put(OC_SERVICE_TNT_TYPE_NVIX, "协作");
			put(OC_SERVICE_TNT_TYPE_WX, "微信");
			put(OC_SERVICE_TNT_TYPE_EB, "电商");
			put(OC_SERVICE_TNT_TYPE_O2O, "O2O");
		}
	};

	/** 平台收费类型 */
	public static final Map<String, String> OC_SERVICE_TNT_PRODUCT_TYPE = new LinkedHashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		{
			put(OC_SERVICE_TNT_TYPE_BIZ, "基础业务");
			put(OC_SERVICE_TNT_TYPE_VIX, "ERP");
			put(OC_SERVICE_TNT_TYPE_NVIX, "协作");
			put(OC_SERVICE_TNT_TYPE_WX, "微信");
			put(OC_SERVICE_TNT_TYPE_EB, "电商");
			put(OC_SERVICE_TNT_TYPE_O2O, "O2O");
		}
	};

	public static final Map<String, String> OC_SERVICE_TNT_TYPE_ALL = new LinkedHashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		{
			put(OC_SERVICE_TNT_TYPE_VIX, "ERP");
			put(OC_SERVICE_TNT_TYPE_NVIX, "协作");
			put(OC_SERVICE_TNT_TYPE_WX, "微信");
			put(OC_SERVICE_TNT_TYPE_EB, "电商");
			put(OC_SERVICE_TNT_TYPE_O2O, "O2O");
			put(OC_SERVICE_TNT_TYPE_OTHER, "其他");
			put(OC_SERVICE_TNT_TYPE_OC, "运营中心");
		}
	};

	// 承租户物料属性
	/** 制造 */
	public static final String OC_TNT_MP_ZZ = "ZZ";
	/** 流通 */
	public static final String OC_TNT_MP_LT = "LT";
	/** 服务 */
	public static final String OC_TNT_MP_FW = "FW";
	/** 制造 服务 */
	public static final String OC_TNT_MP_ZZFW = "ZZ_FW";
	/** 流通 服务 */
	public static final String OC_TNT_MP_LTFW = "LT_FW";

	public static final Map<String, String> OC_TNT_MP = new LinkedHashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		{
			put(OC_TNT_MP_ZZ, "制造");
			put(OC_TNT_MP_LT, "流通");
			put(OC_TNT_MP_FW, "服务");
			put(OC_TNT_MP_ZZFW, "制造+服务");
			put(OC_TNT_MP_LTFW, "流通+服务");
		}
	};

	public static final String OC_TNT_ORDER_ERP_MOD = "MOD";
	public static final String OC_TNT_ORDER_ERP_IND = "IND";
	public static final String OC_TNT_ORDER_ERP_VER = "VER";
	/*
	 * public static final Map<String,String> OC_TNT_ORDER_ERP_IND = new
	 * LinkedHashMap<String,String>() {{ put("","选择"); put("JY","教育");
	 * put("DL","电力"); put("DL","能源"); put("KJ","科技"); }}; public static final
	 * Map<String,String> OC_TNT_ORDER_ERP_VER = new
	 * LinkedHashMap<String,String>() {{ put("","选择"); put("base","基本版");
	 * put("stand","标准版"); put("ep","企业版"); }};
	 */

	// 基础业务
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_BASE_BIZ = "base_biz";

	// NV 行业模块
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_NVIX_MOD = "nvix_mod";

	// ERP 行业模块
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_ERP_MOD = "erp_mod";
	// ERP 常规
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_ERP_CMN = "erp_cmn";

	// 微信 版本
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_WX_VER = "wx_ver";
	// 微信 行业类型
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_WX_BT = "wx_bt";
	// 微信智慧门店分类 版本
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_WX_VER_ZHMD = "wx_ver_zhmd";

	// 营销 活动
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_YX_HD = "yx_hd";

	// 电商 版本
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_SDP_VER = "eb_ver";
	// 电商 行业类型
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_SDP_BT = "eb_bt";

	// o2o 版本
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_O2O_VER = "o2o_ver";
	// o2o 行业类型
	public static final String OC_TNT_ORDER_PRODUCT_CATE_TYPE_O2O_BT = "o2o_bt";

	// 产品分类编码
	public static final Map<String, String> OC_TNT_ORDER_PRODUCT_CATE_TYPE_MAP = new LinkedHashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		{
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_ERP_MOD, "ERP行业模块");
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_ERP_CMN, "ERP常规");
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_WX_VER, "微信版本");
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_WX_BT, "微信行业");
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_YX_HD, "营销活动");
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_SDP_VER, "电商版本");
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_SDP_BT, "电商行业");
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_O2O_VER, "O2O版本");
			put(OC_TNT_ORDER_PRODUCT_CATE_TYPE_O2O_BT, "O2O行业");
		}
	};

	// 微信类型
	public static final String OC_TNT_ORDER_TYPE_WX_TYPE = "wxType";
	// o2o类型
	public static final String OC_TNT_ORDER_TYPE_O2O_TYPE = "o2oType";
	/*
	 * public static final Map<String,String> OC_TNT_ORDER_TYPE_WX_MAP = new
	 * LinkedHashMap<String,String>() {{ put("CY","餐饮"); put("DZSW","电子商务");
	 * put("DC","地产"); put("JS","健身"); put("WY","物业"); put("QX","汽修");
	 * put("JD","酒店"); put("JJ","家具"); put("JY","教育"); put("LY","旅游");
	 * 
	 * }};
	 * 
	 * public static final Map<String,String> OC_TNT_ORDER_TYPE_O2O_MAP = new
	 * LinkedHashMap<String,String>() {{ put("CY","餐饮"); put("MF","美发");
	 * put("MJD","美甲店"); put("ZS","诊所"); put("JSF","健身房"); put("HS","会所");
	 * put("ZL","足疗");
	 * 
	 * }};
	 */
	// ##########################产品基础业务类型的 产品业务分类 bizProductType
	// 基础业务 按人员收费
	public static final String OC_TNT_ORDER_PRODUCT_BIZ_PRO_TYPE_PERUSER = "per_user";
	// 基础业务 订单数收费
	public static final String OC_TNT_ORDER_PRODUCT_BIZ_PRO_TYPE_PERORDER = "per_order";

	public static final Map<String, String> OC_TNT_ORDER_PRODUCT_BIZ_PRO_TYPE = new LinkedHashMap<String, String>() {
		{
			put(OC_TNT_ORDER_PRODUCT_BIZ_PRO_TYPE_PERUSER, "按用户数");
			put(OC_TNT_ORDER_PRODUCT_BIZ_PRO_TYPE_PERORDER, "按每月订单数");
		}
	};

	// 订单类型
	/** 购买承租户 R */
	public static final String OC_BIZ_ORDER_TYPE_R = " R";
	/** 续费 F */
	public static final String OC_BIZ_ORDER_TYPE_F = "F";
	public static final Map<String, String> OC_BIZ_ORDER_TYPE_MAP = new LinkedHashMap<String, String>() {
		{
			put(OC_BIZ_ORDER_TYPE_R, "购买");
			put(OC_BIZ_ORDER_TYPE_F, "续费");
		}
	};

	// 订单状态
	/** 未确认 */
	public final static String OC_TNT_ORDER_STATUS_N = "OC_TNT_ORDER_STATUS_N";
	/** 已确认 */
	public final static String OC_TNT_ORDER_STATUS_Y = "OC_TNT_ORDER_STATUS_Y";
	/** 已完成 */
	public final static String OC_TNT_ORDER_STATUS_O = "OC_TNT_ORDER_STATUS_O";
	/** 已取消 */
	public final static String OC_TNT_ORDER_STATUS_C = "OC_TNT_ORDER_STATUS_C";
	/** 暂停处理 */
	public final static String OC_TNT_ORDER_STATUS_P = "OC_TNT_ORDER_STATUS_P";
	/** 已退货 */
	public final static String OC_TNT_ORDER_STATUS_R = "OC_TNT_ORDER_STATUS_R";
	/** 缺货 */
	/** 已拆分 */

	public static final Map<String, String> OC_TNT_ORDER_STATUS_MAP = new LinkedHashMap<String, String>() {
		{
			put(OC_TNT_ORDER_STATUS_N, "未确认");
			put(OC_TNT_ORDER_STATUS_Y, "已确认");
			put(OC_TNT_ORDER_STATUS_O, "已完成");
			put(OC_TNT_ORDER_STATUS_C, "已取消");

			put(OC_TNT_ORDER_STATUS_P, "暂停处理");
			put(OC_TNT_ORDER_STATUS_R, "已退");
		}
	};

	// 支付状态
	/** 未支付 */
	public final static String OC_TNT_ORDER_PAY_STATUS_N = "OC_TNT_PAY_STATUS_N";
	/** 已支付 */
	public final static String OC_TNT_ORDER_PAY_STATUS_Y = "OC_TNT_PAY_STATUS_Y";
	/** 货到付款 */
	// public final static String OC_TNT_PAY_STATUS_C = "OC_TNT_PAY_STATUS_C";
	/** 已退款 */
	public final static String OC_TNT_ORDER_PAY_STATUS_R = "OC_TNT_PAY_STATUS_R";
	/** 作废 */
	public final static String OC_TNT_ORDER_PAY_STATUS_F = "OC_TNT_PAY_STATUS_F";

	public static final Map<String, String> OC_TNT_PAY_STATUS_MAP = new LinkedHashMap<String, String>() {
		{
			/** 未支付 */
			put(OC_TNT_ORDER_PAY_STATUS_N, "未支付");
			/** 已支付 */
			put(OC_TNT_ORDER_PAY_STATUS_Y, "已支付");
			/** 货到付款 */
			// put(OC_TNT_PAY_STATUS_C, "货到付款");
			/** 已退款 */
			put(OC_TNT_ORDER_PAY_STATUS_R, "已退款");
			put(OC_TNT_ORDER_PAY_STATUS_F, "作废");
		}
	};

	/** 账户类型 注册 */
	public static final String OC_CMN_USERACCOUNT_TYPE_U = "U";
	/** 账户类型 系统 */
	public static final String OC_CMN_USERACCOUNT_TYPE_S = "S";

	public static final Map<String, String> OC_CMN_USERACCOUNT_TYPE_MAP = new LinkedHashMap<String, String>() {
		{
			put(OC_CMN_USERACCOUNT_TYPE_U, "注册");
			put(OC_CMN_USERACCOUNT_TYPE_S, "系统");
		}
	};

	/** 角色编码 账户类型 注册 */
	public static final String OC_CMN_USERACCOUNT_TYPE_ROLE_U = "OC_ROLE_USER_REG";
	/** 角色编码 账户类型 系统 */
	public static final String OC_CMN_USERACCOUNT_TYPE_ROLE_S = "OC_ROLE_USER_SYS";

	public static final Map<String, String> OC_CMN_USERACCOUNT_TYPE_ROLE_MAP = new LinkedHashMap<String, String>() {
		{
			put(OC_CMN_USERACCOUNT_TYPE_ROLE_U, "注册用户");
			put(OC_CMN_USERACCOUNT_TYPE_ROLE_S, "系统用户");
		}
	};

	// 代金券方案类型
	/** 直接发放 */
	public static final String OC_EXPENSEVOUCHER_PLAN_TYPE_ZJFF = "zjff";
	/** 会员领取 */
	public static final String OC_EXPENSEVOUCHER_PLAN_TYPE_HYLQ = "hylq";

	// 代金券使用条件
	/** 无限制 */
	public static final String OC_EXPENSEVOUCHER_USECONDITION_WXZ = "wxz";
	/** 订单总额满 */
	public static final String OC_EXPENSEVOUCHER_USECONDITION_DDZEM = "ddzem";

}
