package com.vix.nvix.wx.util;

import java.util.Iterator;
import java.util.List;

import com.vix.core.utils.JSonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

//!hibernate 转json使用，暂时有问题，没有调通

public class PadJsonUtil extends JSonUtils {
	public static String modelBeanToJSON(final List<String> filterField, List<?> list) {
		JSONArray jsonObjects = new JSONArray();

		int i = 0;
		Iterator<?> it = list.iterator();

		while (it.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			Object obj = it.next();// 这里的Obj对象只是list中的存放的对象类型

			JsonConfig jsonConfig = new JsonConfig();

			PropertyFilter filter = new PropertyFilter() {
				@Override
				public boolean apply(Object source, String name, Object value) {
					boolean isFiltered = false;
					for (String string : filterField) {
						if (string.equals(name)) {
							isFiltered = true;
						}
					}
					if (isFiltered) {
						return true;
					}
					return false;
				}
			};
			jsonConfig.setJsonPropertyFilter(filter);

			jsonObject = JSONObject.fromObject(obj, jsonConfig);
			jsonObjects.add(i++, jsonObject);
		}
		return jsonObjects.toString();
	}
}
