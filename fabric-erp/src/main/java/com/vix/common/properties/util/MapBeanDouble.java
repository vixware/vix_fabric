package com.vix.common.properties.util;
/**
 * 
 * @author guojiangpeng
 * MapBeanDouble 一般专用于把统计图表中，从数据库查出的数据进行实体化，
 * key   是 x轴数据
 * value 是 y轴数据
 */
public class MapBeanDouble {
	private String key;
	private Double value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public MapBeanDouble(String key, Double value) {
		super();
		this.key = key;
		this.value = value;
	}
	public MapBeanDouble() {
		super();
	}
	@Override
	public String toString() {
		return "Map_BeanWxDouble [key=" + key + ", value=" + value + "]";
	}
	
}
