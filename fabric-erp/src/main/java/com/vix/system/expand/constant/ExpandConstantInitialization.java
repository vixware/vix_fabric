package com.vix.system.expand.constant;

import java.util.HashMap;
import java.util.Map;

public class ExpandConstantInitialization implements ExpandConstant {

	
	public static Map<String,String> getExpandTypeConstantMap(){
		Map<String,String> expandTypeConstantMap = new HashMap<String,String>();
		expandTypeConstantMap.put(EXPAND_TYPE_ITEM,"expand_type_item");
		expandTypeConstantMap.put(EXPAND_TYPE_EQUIPMENT,"expand_type_equipment");
		return expandTypeConstantMap;
	}
	
	public static Map<String,String> getExpandColumnTypeConstantMap(){
		Map<String,String> expandColumnTypeConstantMap = new HashMap<String,String>();
		expandColumnTypeConstantMap.put(EXPAND_COLUMN_TYPE_DATE,"expand_column_type_date");
		expandColumnTypeConstantMap.put(EXPAND_COLUMN_TYPE_TEXT,"expand_column_type_text");
		expandColumnTypeConstantMap.put(EXPAND_COLUMN_TYPE_NUMBER,"expand_column_type_number");
		expandColumnTypeConstantMap.put(EXPAND_COLUMN_TYPE_SELECT,"expand_column_type_select");
		expandColumnTypeConstantMap.put(EXPAND_COLUMN_TYPE_RADIO,"expand_column_type_radio");
		expandColumnTypeConstantMap.put(EXPAND_COLUMN_TYPE_CHECKBOX,"expand_column_type_checkbox");
		return expandColumnTypeConstantMap;
	}
}
