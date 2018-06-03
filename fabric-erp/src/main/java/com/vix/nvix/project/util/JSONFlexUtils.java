package com.vix.nvix.project.util;

import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONFlexUtils {

	/**
	 * 产生JSONSerializer(排除属性)
	 * @param exludeExpression
	 * @return
	 */
	private static JSONSerializer makeSerializerObjExlude(String... exludeExpression){
		return new JSONSerializer()
		.exclude("*.class")
		.exclude("*.handler")
		.exclude("*.hibernateLazyInitializer")
		.exclude(exludeExpression);
	}
	
	/**
	 * 产生JSONSerializer(排除属性的基础上添加属性)
	 * @param includeExpression
	 * @return
	 */
	private static JSONSerializer makeSerializerObjInclude(String[] excludes,String... includeExpression){
		JSONSerializer js = new JSONSerializer()
				.exclude("*.class")
				.exclude("*.handler")
				.exclude("*.hibernateLazyInitializer");
		if(null != excludes && excludes.length > 0){
			for(String exclude : excludes){
				js = js.exclude(exclude);
			}
		}
		if(null != includeExpression){
			js = js.include(includeExpression);
		}
		return js;
	}
	
	/**
	 * 转换bean为JSON对象
	 * @param bean
	 * @return
	 */
	public static String toJson(Object bean){
		return makeSerializerObjExlude().serialize(bean);
	}
	
	/**
	 * 转换bean为JSON对象
	 * @param bean
	 * @return
	 */
	public static String toJson(Object bean,String... exludeExpression){
		return makeSerializerObjExlude(exludeExpression).serialize(bean);
	}
	
	/**
	 * 转换bean为JSON对象
	 * @param bean
	 * @return
	 */
	public static String toJsonInclude(Object bean,String[] excludes,String... includeExpression){
		return makeSerializerObjInclude(excludes,includeExpression).serialize(bean);
	}
	
	/**
	 * 序列化List
	 * @param <T>
	 * @param dataList
	 * @param exludeExpression
	 * @return
	 */
	public <T> String convertListToJson(List<T> dataList,String... exludeExpression) {
		return makeSerializerObjExlude(exludeExpression).serialize(dataList);
	}
	
	public static <T> List<T> deserializeObjStrList(String jsonListStr,Class<T> listClz){
		List<T> resList = new JSONDeserializer<List<T>>()
				.use("values",listClz)
				.deserialize(jsonListStr);
		return resList;
	}

	public static <T> T deserializeObj(String jsonListStr,Class<T> clz){
		T res = new JSONDeserializer<T>()
				.use("values",clz).deserialize(jsonListStr);
		return res;
	}
}
