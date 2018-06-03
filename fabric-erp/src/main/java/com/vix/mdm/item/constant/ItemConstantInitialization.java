package com.vix.mdm.item.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展列类型常量 
 *
 */
public class ItemConstantInitialization implements ItemConstant{
	
	public static Map<String,String> getItemTypeConstantMap(){
		Map<String,String> columnTypeConstantMap = new HashMap<String,String>();
		columnTypeConstantMap.put(GOODS,"mdm_goods");
		columnTypeConstantMap.put(FIXEDASSETS,"mdm_fixedassets");
		columnTypeConstantMap.put(MATERIAL,"mdm_material");
		columnTypeConstantMap.put(PURCHASEPART,"mdm_purchasepart");
		columnTypeConstantMap.put(PURCHASEBACKUPPART,"mdm_purchasebackuppart");
		columnTypeConstantMap.put(MACHININGPART,"mdm_machiningpart");
		columnTypeConstantMap.put(SEMIFINISHED,"mdm_semifinished");
		columnTypeConstantMap.put(ASSEMBLYPART,"mdm_assemblypart");
		columnTypeConstantMap.put(FINISHEDGOODS,"mdm_finishedgoods");
		columnTypeConstantMap.put(EQUIPMENT,"mdm_equipment");
		columnTypeConstantMap.put(OFFICESUPPLY,"mdm_officesupply");
		columnTypeConstantMap.put(AFTERSERVICE,"mdm_afterservice");
		columnTypeConstantMap.put(MAINTENANCESERVICE,"mdm_maintenanceservice");
		columnTypeConstantMap.put(OUTSIDESERVICE,"mdm_outsideservice");
		return columnTypeConstantMap;
	}
}
