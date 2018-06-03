package com.vix.traceability.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * com.traceability.test.ModelClassHelper
 *
 * @author bjitzhang
 *
 * @date 2015年9月29日
 */
public class ModelClassHelper {
	public static HashMap<String, Class> init(String classPath) {
		try {
			//"com.geocompass.model.STSTBPRPModel"
			HashMap<String, Class> fieldHashMap = new HashMap<String, Class>();
			Class cls = Class.forName(classPath); //com.geocompass.model.STSTBPRPModel
			Field[] fieldlist = cls.getDeclaredFields();
			for (int i = 0; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				fieldHashMap.put(fld.getName(), fld.getType());
				System.out.println("name = " + fld.getName());
				System.out.println("decl class = " + fld.getDeclaringClass());
				System.out.println("type = " + fld.getType());
				System.out.println("-----");
			}
			return fieldHashMap;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getTableName(String classPath) {
		try {
			Class cls = Class.forName(classPath);
			Method test = cls.getDeclaredMethod("getTableName");
			Object invoke = test.invoke(cls.newInstance());
			return invoke.toString();
			//cls.asSubclass(TabModel.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/*public static void main(String[] args) throws Exception {
		Fruit obj = new Fruit();
		obj.setName("name value");
		obj.setCode("code value");
		Field[] fds = obj.getClass().getSuperclass().getDeclaredFields();

		System.out.println(fds.length);
		for (int i = 0; i < fds.length; i++) {
			System.out.println(fds[i].get(obj));
			System.out.println("type = " + fds[i].getType().getSimpleName());
		}
	}*/
	public static void main(String[] args) {
	}

	public static String reflect(Object obj) {
		StringBuffer objectExpandValue = new StringBuffer();
		if (obj == null)
			return objectExpandValue.toString();
		Field[] fields = obj.getClass().getFields();
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);
			// 字段名  
			objectExpandValue.append(fields[j].getName()).append(":");
			// 字段值  
			if (fields[j].getType().getName().equals(java.lang.String.class.getName())) {
				// String type  
				try {
					objectExpandValue.append(fields[j].get(obj));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (fields[j].getType().getName().equals(java.util.Date.class.getName())) {
				try {
					//System.out.println("date");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			objectExpandValue.append(",");
		}
		System.out.println(objectExpandValue.toString());
		return objectExpandValue.toString();
	}

	public static String getTypeName(Class<?> cls, String fieldName) {
		Field[] fieldlist = cls.getDeclaredFields();
		for (int i = 0; i < fieldlist.length; i++) {
			Field fld = fieldlist[i];
			if (fieldName.equals(fld.getName())) {
				return fld.getType().getName();
			}
		}
		return null;
	}
}
