package com.vix.common.properties.util;

/**
 * 
 * @author guojiangpeng
 * Map_BeanWx 一般专用于把统计图表中，从数据库查出的数据进行实体化，
 * key 是 x轴数据
 * value 是 y轴数据
 * Map_BeanWx 比系统的map集合好用一些
 * 2017-7-12
 */
public class MapBean{
		private String key;
		private String value;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public MapBean(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}
		public MapBean() {
			super();
		}
		@Override
		public String toString() {
			return "MapBean [key=" + key + ", value=" + value + "]";
		}
	}

