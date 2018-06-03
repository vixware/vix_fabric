package com.vix.mdm.item.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 定价类型
 */
public class PriceConditionConstantInitialization implements PriceConditionConstant{
	
	/** 销售定价条件 */
	public static Map<String,String> getSalePriceConditionConstantMap(){
		Map<String,String> columnTypeConstantMap = new HashMap<String,String>();
		columnTypeConstantMap.put(PURCHASE_FRAMEWORK_AGREEMENT,"mdm_purchaseFrameworkAgreement");
		columnTypeConstantMap.put(SALE_FRAMEWORK_AGREEMENT,"mdm_saleFrameworkAgreement");
		columnTypeConstantMap.put(CUSTOMERACCOUNT_CATEGORY,"mdm_customerAccountCategory");
		columnTypeConstantMap.put(ITEM_CATEGORY,"mdm_itemCategory");
		columnTypeConstantMap.put(ITEM_GROUP,"mdm_itemGroup");
		columnTypeConstantMap.put(CUSTOMERACCOUNT_GROUP,"mdm_customerAccountGroup");
		columnTypeConstantMap.put(CHANNELDISTRIBUTOR,"mdm_channelDistributor");
		columnTypeConstantMap.put(SUPPLIER,"mdm_supplier");
		columnTypeConstantMap.put(CUSTOMERACCOUNT,"mdm_customerAccount");
		columnTypeConstantMap.put(ITEM,"mdm_item");
		columnTypeConstantMap.put(CUSTOMERACCOUNT_ITEM,"mdm_customerAccountAndItem");
		return columnTypeConstantMap;
	}
	
	/** 采购定价条件 */
	public static Map<String,String> getPurchasePriceConditionConstantMap(){
		Map<String,String> columnTypeConstantMap = new HashMap<String,String>();
		columnTypeConstantMap.put(PURCHASE_FRAMEWORK_AGREEMENT,"mdm_purchaseFrameworkAgreement");
		columnTypeConstantMap.put(ITEM_CATEGORY,"mdm_itemCategory");
		columnTypeConstantMap.put(ITEM_GROUP,"mdm_itemGroup");
		columnTypeConstantMap.put(SUPPLIER,"mdm_supplier");
		columnTypeConstantMap.put(ITEM,"mdm_item");
		return columnTypeConstantMap;
	}
}
