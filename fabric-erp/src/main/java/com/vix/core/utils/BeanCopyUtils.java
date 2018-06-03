package com.vix.core.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanCopyUtils {

	
	/* 
	 * 复制的对象必须同时拥有setter和getter方法，只有一个的时候会报异常，都没有的时候就不复制 
	 */ 
	public static void Copy(Object source, Object dest) throws Exception {  
        //获取属性  
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);  
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();  
  
        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);  
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();  
  
        try {  
            for (int i = 0; i < sourceProperty.length; i++) { 
            	
                if (sourceProperty[i].getName().equals("serialVersionUID"))  
                    continue;  
                if (sourceProperty[i].getPropertyType() == Map.class)  
                    continue;  
                if (sourceProperty[i].getPropertyType() == Set.class)  
                    continue;  
                if (sourceProperty[i].getPropertyType() == List.class)  
                    continue; 
            	
                for (int j = 0; j < destProperty.length; j++) {  
  
                    if (sourceProperty[i].getName().equals(destProperty[j].getName())) {  
                        //调用source的getter方法和dest的setter方法  
                        destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));  
                        break;  
                    }  
                }  
            }  
        } catch (Exception e) {  
            throw new Exception("属性复制失败:" + e);  
        }  
    }
	
	
	
	
	
	/* 
	 * 该方法接收两个参数，一个是复制的源对象——要复制的对象，一个是复制的目标对象——对象副本。 
	 * 当然这个方法也可以在两个不同对象间使用，这时候只要目标对象和对象具有一个或多个相同类型及名称的属性，那么就会把源对象的属性值赋给目标对象的属性 
	 */  
	public static <T> T getBean(T TargetBean, T SourceBean) throws Exception {  
        if (TargetBean == null)  
            return null;  
        Field[] tFields = TargetBean.getClass().getDeclaredFields();  
        Field[] sFields = SourceBean.getClass().getDeclaredFields();  
        try {  
            for (Field field : tFields) {  
                String fieldName = field.getName();  
                if (fieldName.equals("serialVersionUID"))  
                    continue;  
                if (field.getType() == Map.class)  
                    continue;  
                if (field.getType() == Set.class)  
                    continue;  
                if (field.getType() == List.class)  
                    continue;  
                for (Field sField : sFields) {  
                    if (!sField.getName().equals(fieldName)) {  
                        continue;  
                    }  
                    Class type = field.getType();  
                    String setName = getSetMethodName(fieldName);  
                    Method tMethod = TargetBean.getClass().getMethod(setName, new Class[] { type });  
                    String getName = getGetMethodName(fieldName);  
                    Method sMethod = SourceBean.getClass().getMethod(getName, null);  
                    Object setterValue = sMethod.invoke(SourceBean, null);  
                    tMethod.invoke(TargetBean, new Object[] { setterValue });  
                }  
            }  
        } catch (Exception e) {  
            throw new Exception("设置参数信息发生异常", e);  
        }  
        return TargetBean;  
    }  
  
    private static String getGetMethodName(String fieldName) {  
        fieldName = replaceFirstCharToUpper(fieldName);  
        return "get" + fieldName;  
    }  
  
    private static String getSetMethodName(String fieldName) {  
        fieldName = replaceFirstCharToUpper(fieldName);  
        return "set" + fieldName;  
    }  
  
    private static String replaceFirstCharToUpper(String fieldName) {  
        char[] chars = new char[1];  
        chars[0] = fieldName.charAt(0);  
        String temp = new String(chars);  
        if (chars[0] >= 'a' && chars[0] <= 'z') {  
            fieldName = fieldName.replaceFirst(temp, temp.toUpperCase());  
        }  
        return fieldName;  
    } 
    
    
    
    
    
    /* 
     * 采用反射，通过源对象getter 方法获取属性值，并通过目标对象的setter方法设置到目标对象中去 
     */


    /** 
     * 利用反射实现对象之间属性复制 
     * @param from 
     * @param to 
     */ 
   public static void copyProperties(Object from, Object to) throws Exception {  
       copyPropertiesExclude(from, to, null);  
   }  
     
   /** 
    * 复制对象属性 
    * @param from 
    * @param to 
    * @param excludsArray 排除属性列表 
    * @throws Exception 
    */  
   @SuppressWarnings("unchecked")  
   public static void copyPropertiesExclude(Object from, Object to, String[] excludsArray) throws Exception {  
       List<String> excludesList = null;  
       if(excludsArray != null && excludsArray.length > 0) {  
           excludesList = Arrays.asList(excludsArray); //构造列表对象  
       }  
       Method[] fromMethods = from.getClass().getDeclaredMethods();  
       Method[] toMethods = to.getClass().getDeclaredMethods();  
       Method fromMethod = null, toMethod = null;  
       String fromMethodName = null, toMethodName = null;  
       for (int i = 0; i < fromMethods.length; i++) {  
           fromMethod = fromMethods[i];  
           fromMethodName = fromMethod.getName();  
           if (!fromMethodName.contains("get"))  
               continue;  
           //排除列表检测  
           if(excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {  
               continue;  
           }  
           toMethodName = "set" + fromMethodName.substring(3);  
           toMethod = findMethodByName(toMethods, toMethodName);  
           if (toMethod == null)  
               continue;  
           Object value = fromMethod.invoke(from, new Object[0]);  
           if(value == null)  
               continue;  
           //集合类判空处理  
           if(value instanceof Collection) {  
               Collection newValue = (Collection)value;  
               if(newValue.size() <= 0)  
                   continue;  
           }  
           toMethod.invoke(to, new Object[] {value});  
       }  
   }  
     
   /** 
    * 对象属性值复制，仅复制指定名称的属性值 
    * @param from 
    * @param to 
    * @param includsArray 
    * @throws Exception 
    */  
   @SuppressWarnings("unchecked")  
   public static void copyPropertiesInclude(Object from, Object to, String[] includsArray) throws Exception {  
       List<String> includesList = null;  
       if(includsArray != null && includsArray.length > 0) {  
           includesList = Arrays.asList(includsArray); //构造列表对象  
       } else {  
           return;  
       }  
       Method[] fromMethods = from.getClass().getDeclaredMethods();  
       Method[] toMethods = to.getClass().getDeclaredMethods();  
       Method fromMethod = null, toMethod = null;  
       String fromMethodName = null, toMethodName = null;  
       for (int i = 0; i < fromMethods.length; i++) {  
           fromMethod = fromMethods[i];  
           fromMethodName = fromMethod.getName();  
           if (!fromMethodName.contains("get"))  
               continue;  
           //排除列表检测  
           String str = fromMethodName.substring(3);  
           if(!includesList.contains(str.substring(0,1).toLowerCase() + str.substring(1))) {  
               continue;  
           }  
           toMethodName = "set" + fromMethodName.substring(3);  
           toMethod = findMethodByName(toMethods, toMethodName);  
           if (toMethod == null)  
               continue;  
           Object value = fromMethod.invoke(from, new Object[0]);  
           if(value == null)  
               continue;  
           //集合类判空处理  
           if(value instanceof Collection) {  
               Collection newValue = (Collection)value;  
               if(newValue.size() <= 0)  
                   continue;  
           }  
           toMethod.invoke(to, new Object[] {value});  
       }  
   }  
     
   /** 
    * 从方法数组中获取指定名称的方法 
    *  
    * @param methods 
    * @param name 
    * @return 
    */  
   public static Method findMethodByName(Method[] methods, String name) {  
       for (int j = 0; j < methods.length; j++) {  
           if (methods[j].getName().equals(name))  
               return methods[j];  
       }  
       return null;  
   }  
   
   
   
   
   
   
	
}  
