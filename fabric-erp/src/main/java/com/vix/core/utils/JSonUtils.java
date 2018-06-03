package com.vix.core.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.type.TypeReference;

public class JSonUtils {

    static ObjectMapper objectMapper;

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     *      转换为普通JavaBean：readValue(json,Student.class)
     *      转换为List:readValue(json,List.class).但是如果我们想把json转换为特定类型的List，比如List<Student>，就不能直接进行转换了。
     *          因为readValue(json,List.class)返回的其实是List<Map>类型，你不能指定readValue()的第二个参数是List<Student>.class，所以不能直接转换。
     *          我们可以把readValue()的第二个参数传递为Student[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List。
     *      转换为Map：readValue(json,Map.class)
     * 我们使用泛型，得到的也是泛型
     * 
     * @param content
     *            要转换的JavaBean类型
     * @param valueType
     *            原始json字符串数据
     * @return JavaBean对象
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
	public static <T> T json2GenericObject(String jsonString,TypeReference<T> tr) throws JsonParseException,JsonMappingException, IOException {
		if ((jsonString == null) || ("".equals(jsonString))) {
			return null;
		}
		return objectMapper.readValue(jsonString, tr);
	}

    /**
     * 把JavaBean转换为json字符串 
     *      普通对象转换：toJson(Student) 
     *      List转换：toJson(List)
     *      Map转换:toJson(Map)
     * 我们发现不管什么类型，都可以直接传入这个方法
     * 
     * @param object
     *            JavaBean对象
     * @return json字符串
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    
    
    
    
    
    
    
    
    
    
    public static String makeStringToJson(String s) {
        if (s == null) {
            return nullToJson();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '"':
                sb.append("\\\"");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\r':
                sb.append("\\r");
                break;
            case '\t':
                sb.append("\\t");
                break;
            case '/':
                sb.append("\\/");
                break;
            default:
                if (ch >= '\u0000' && ch <= '\u001F') {
                    String ss = Integer.toHexString(ch);
                    sb.append("\\u");
                    for (int k = 0; k < 4 - ss.length(); k++) {
                        sb.append('0');
                    }
                    sb.append(ss.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    public static String nullToJson() {
        return "";
    }

    public static String makeObjectToJson(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof Number) {
            json.append(numberToJson((Number) obj));
        } else if (obj instanceof Boolean) {
            json.append(booleanToJson((Boolean) obj));
        } else if (obj instanceof String) {
            json.append("\"").append(makeStringToJson(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(makeArrayToJson((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(makeListToJson((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(makeMapToJson((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(makeSetToJson((Set<?>) obj));
        } else if (obj instanceof Date) {
        	json.append("\"").append(makeDateToJson((Date) obj)).append("\"");
        } else {
            json.append(makeBeanToJson(obj));
        }
        return json.toString();
    }

    public static String numberToJson(Number number) {
        return number.toString();
    }

    public static String booleanToJson(Boolean bool) {
        return bool.toString();
    }

    /**
     * @param bean
     *            bean对象
     * @return String
     */
    public static String makeBeanToJson(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class)
                    .getPropertyDescriptors();
        } catch (IntrospectionException e) {
        }
        if (props != null) {
            for (int i = 0; i < props.length; i++) {
                try {
                    String name = makeObjectToJson(props[i].getName());
                    String value = makeObjectToJson(props[i].getReadMethod()
                            .invoke(bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * @param list
     *            list对象
     * @return String
     */
    public static String makeListToJson(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(makeObjectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * @param array
     *            对象数组
     * @return String
     */
    public static String makeArrayToJson(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(makeObjectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * @param map
     *            map对象
     * @return String
     */
    public static String makeMapToJson(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(makeObjectToJson(key));
                json.append(":");
                json.append(makeObjectToJson(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * @param set
     *            集合对象
     * @return String
     */
    public static String makeSetToJson(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(makeObjectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    
    /**
     * @param set
     *            集合对象
     * @return String
     */
    public static String makeDateToJson(Date date) {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
        return sdf.format(date);
    }
    
    
    
    public static String simpleEntityListToJson(List entityList, String... includeAttributes)
    {
    	if(entityList==null)
    		return "";
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	for(int i=0;i<entityList.size();i++)
    	{
    		Object entity = entityList.get(i);
    		if(i>0)
    			sb.append(",");
    		sb.append(simpleEntityToJson(entity, includeAttributes));
    	}
    	sb.append("]");
    	return sb.toString();
    }
    
    public static String simpleEntityToJson(Object entity, String... includeAttributes)
    {
    	Map<String,String> includeMap = null;
    	if(includeAttributes!=null && includeAttributes.length>0)
    	{
    		//如果有include，就只输出include中的对象 
    		includeMap = new HashMap<String,String>();
    		for(String inc :includeAttributes)
    		{
    			includeMap.put(inc, inc);
    		}
    	}
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(entity.getClass(), Object.class)
                    .getPropertyDescriptors();
        } catch (IntrospectionException e) {
        }
        if (props != null) {
            for (int i = 0; i < props.length; i++) {
                try {
                	String attrName = props[i].getName();
                	//如果有设置include，则只输出指定的属性
                	if(includeMap!=null && !includeMap.containsKey(attrName))
                		continue;
                	
                	Object attrValue = props[i].getReadMethod().invoke(entity);
                	String valJson = simpleObjectToJson(attrValue);
                	if(valJson==null)
                		continue;
                	
                    json.append("\"").append(attrName).append("\"");
                    json.append(":");
                    json.append(valJson);
                    json.append(",");
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }
    
    private static String simpleObjectToJson(Object obj)
    {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof Number) {
            json.append(numberToJson((Number) obj));
        } else if (obj instanceof Boolean) {
            json.append(booleanToJson((Boolean) obj));
        } else if (obj instanceof String) {
            json.append("\"").append(makeStringToJson(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(simpleArrayToJson((Object[]) obj));
        } else if (obj instanceof List) {
//            json.append(makeListToJson((List<?>) obj));
            return null;
        } else if (obj instanceof Map) {
//            json.append(makeMapToJson((Map<?, ?>) obj));
            return null;
        } else if (obj instanceof Set) {
//            json.append(makeSetToJson((Set<?>) obj));
            return null;
        } else if (obj instanceof Date) {
        	json.append("\"").append(makeDateToJson((Date) obj)).append("\"");
        } else {
//            json.append(makeBeanToJson(obj));
        	return null;
        }
        return json.toString();
    }
    
    private static String simpleArrayToJson(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(simpleObjectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    
}
