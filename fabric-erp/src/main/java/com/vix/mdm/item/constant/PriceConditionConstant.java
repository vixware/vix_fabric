package com.vix.mdm.item.constant;

/**
 * 定价条件类型
 */
public interface PriceConditionConstant {
	
	/** 采购框架协议  供应商+物料*/
	public static String PURCHASE_FRAMEWORK_AGREEMENT = "purchaseFrameworkAgreement";
	/** 销售框架协议  客户+物料*/
	public static String SALE_FRAMEWORK_AGREEMENT = "saleFrameworkAgreement";
	/** 客户分类 客户分类+物料+地域 */
	public static String CUSTOMERACCOUNT_CATEGORY = "customerAccountCategory";
	/** 物料分类  物料分类+地域 */
	public static String ITEM_CATEGORY = "itemCategory";
	/** 物料组  物料组+地域*/
	public static String ITEM_GROUP  = "itemGroup";
	/** 客户组  客户组+地域*/
	public static String CUSTOMERACCOUNT_GROUP  = "customerAccountGroup";
	/** 分销商  分销商+物料+地域*/
	public static String CHANNELDISTRIBUTOR = "channelDistributor";
	/** 供应商  供应商+物料+地域*/
	public static String SUPPLIER = "supplier";
	/** 客户  客户+地域*/
	public static String CUSTOMERACCOUNT = "customerAccount";
	/** 物料  物料+地域 */
	public static String ITEM = "item";
	/** 客户与物料  客户+物料+地域*/
	public static String CUSTOMERACCOUNT_ITEM = "customerAccountAndItem";
	 
}
