package com.vix.common.securityDra.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vix.core.constant.DataRowOperator;

public class HandleObjectType {

	public static Object convertValue(String fieldValue,String type){
		if(type.equals(DataRowOperator.DATATYPE_INTEGER)){
			return Integer.parseInt(fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_LONG)){
			return Long.parseLong(fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_LONG)){
			return Long.parseLong(fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_DOUBLE)){
			return Double.parseDouble(fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_FLOAT)){
			return Float.parseFloat(fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_STRING)){
			return String.valueOf(fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_DATE_YEAR)){
			return parseSqlDate("yyyy", fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_DATE_MONTH)){
			return parseSqlDate("yyyy-MM", fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_DATE_DATE)){
			return parseSqlDate("yyyy-MM-dd", fieldValue);
		}else if(type.equals(DataRowOperator.DATATYPE_DATE_TIME)){
			return parseSqlDate("yyyy-MM-dd HH:mm:ss", fieldValue);
		}
		return fieldValue;
		
	}
	
	
	private static Date parseSqlDate(String format,String source) {
		try {
			return new Date(new SimpleDateFormat(format).parse(source).getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
	
}
