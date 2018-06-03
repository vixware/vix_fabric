package com.vix.core.utils;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.hibernate.proxy.HibernateProxy;

import com.vix.core.utils.json.felxjson.former.FelxLazyInitObjectTransformer;

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
		.transform(new FelxLazyInitObjectTransformer(), LazyInitializer.class)
		.transform(new FelxLazyInitObjectTransformer(), HibernateProxy.class)
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
	private static JSONSerializer makeSerializerObjInclude(String... includeExpression){
		return new JSONSerializer()
		.transform(new FelxLazyInitObjectTransformer(), LazyInitializer.class)
		.transform(new FelxLazyInitObjectTransformer(), HibernateProxy.class)
		.exclude("*.class")
		.exclude("*.handler")
		.exclude("*.hibernateLazyInitializer")
		.include(includeExpression);
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
	public static String toJsonInclude(Object bean,String... includeExpression){
		return makeSerializerObjInclude(includeExpression).serialize(bean);
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
		js = js.include(includeExpression);
		return js;
	}


	
	/**
	 * 序列化List
	 * @param <T>
	 * @param dataList
	 * @param exludeExpression
	 * @return
	 */
	public static <T> String convertListToJson(List<T> dataList,String... exludeExpression) {
		return makeSerializerObjExlude(exludeExpression).serialize(dataList);
	}
	/**
	 * 序列化Set
	 */
	public static <T> String convertSetToJson(Set<T> dataList,String... includeExpression) {
		return makeSerializerObjInclude(includeExpression).serialize(dataList);
	}
	
	
	
	public static <T> List<T> deserializeObjStrList(String jsonListStr,Class<T> listClz){
		List<T> resList = new JSONDeserializer<List<T>>()
				//.use(null, listClz)
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
