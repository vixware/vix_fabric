package com.vix.common.properties.util;

/**
 * 
 * @author guojiangpeng
 * MapBeanInt 一般专用于把统计图表中，从数据库查出的数据进行实体化，
 * key   是 x轴数据
 * value 是 y轴数据
 */
public class MapBeanInt{
		private String key;
		private Integer value;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}
		public MapBeanInt(String key, Integer value) {
			super();
			this.key = key;
			this.value = value;
		}
		public MapBeanInt() {
			super();
		}
		@Override
		public String toString() {
			return "Map_BeanWxInt [key=" + key + ", value=" + value + "]";
		}
		
	}

