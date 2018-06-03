package com.vix.core.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public final class DataRowOperator {

	public static final Map<String,String> operateMap = new LinkedHashMap<String,String>();
	
	public static final String DENGYU = "=";
	
	public static final String DENGYU_NO = "<>";
	
	public static final String DAYU = ">";
	
	public static final String DAYU_2 = ">=";
	
	public static final String XIAOYU = "<";
	
	public static final String XIAOYU_2 = "<=";
	
	public static final String LIKE = "like";
	
	public static final String IN = "in";
	
	public static final String NOT_IN = "not in";

	static{
		operateMap.put(DENGYU, "等于");
		operateMap.put(DENGYU_NO, "不等于");
		operateMap.put(DAYU, "大于");
		operateMap.put(DAYU_2, "大于等于");
		operateMap.put(XIAOYU, "小于");
		operateMap.put(XIAOYU_2, "小于等于");
		operateMap.put(LIKE, "相似");
		operateMap.put(IN, "在范围内");
		operateMap.put(NOT_IN, "不在范围内");
	}
	
	
	
	
	public static final String CONDITION_AND = "and";
	
	public static final String CONDITION_OR = "or";
	
	public static final Map<String,String> conditionMap = new LinkedHashMap<String,String>();
	
	static{
		conditionMap.put(CONDITION_AND, "并且");
		conditionMap.put(CONDITION_OR, "或者");
	}
	
	
	
	
	public static final String DATATYPE_INTEGER = "integer";
	
	public static final String DATATYPE_LONG="long";
	
	public static final String DATATYPE_DOUBLE="double";
	
	public static final String DATATYPE_FLOAT = "float";
	
	public static final String DATATYPE_STRING="string";
	
	public static final String DATATYPE_DATE_YEAR="date_year";
	
	public static final String DATATYPE_DATE_MONTH="date_month";
	
	public static final String DATATYPE_DATE_DATE="date_date";
	
	public static final String DATATYPE_DATE_TIME="date_time";
	
	public static final String DATATYPE_HQL = "hql";
	
	public static final Map<String,String> typeMap = new LinkedHashMap<String,String>();
	
	static{
		typeMap.put(DATATYPE_INTEGER, "整型");
		typeMap.put(DATATYPE_LONG, "长整型");
		typeMap.put(DATATYPE_DOUBLE, "浮点型");
		typeMap.put(DATATYPE_FLOAT, "双精度");
		typeMap.put(DATATYPE_STRING, "字符串");
		typeMap.put(DATATYPE_DATE_YEAR, "年度");
		typeMap.put(DATATYPE_DATE_MONTH, "月份");
		typeMap.put(DATATYPE_DATE_DATE, "日期");
		typeMap.put(DATATYPE_DATE_TIME, "时间");
		typeMap.put(DATATYPE_HQL, "HQL");
	}
	
}
