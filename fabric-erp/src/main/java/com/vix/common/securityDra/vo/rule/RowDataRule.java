package com.vix.common.securityDra.vo.rule;

import java.io.Serializable;

/**
 * 数据权限规则设定的 规则
 * @author Administrator
 *
 */
public class RowDataRule implements Serializable {

	/** 属性  包括元数据的属性  或者系统定义的常量  用{}包含 */
	private String fields;
	/** 操作符   在security的常量配置中有设置  如 = > < */
	private String op;
	/** 属性的值  可以是配置好的常量  也可以是系统定义的变量（如sql ） */
	private String value;
	/** 属性的值  如果realValue不为空 则以此值为准，value只是作为显示使用 */
	private String realValue;
	
	/**  属性值的类型  可以是基础类型  也可以是SQL*/
	private String valueType;
	
	/**  用于对 rowDataRule 的集合中每个规则对象之间的关系  值为 and 或者 or */
	private String ruleOp;
	
	/**
	 * 0 简单属性
	 * 1  业务对象属性
	 * 2 集合属性
	 */
	private String propertyType;
	
	
	//行集权限使用
	//private String isCollectionType;
	
	public RowDataRule() {
		super();
	}
	
	public RowDataRule(String fields) {
		super();
		this.fields = fields;
	}
	
	public RowDataRule(String fields, String valueType) {
		super();
		this.fields = fields;
		this.valueType = valueType;
	}

	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getRuleOp() {
		return ruleOp;
	}
	public void setRuleOp(String ruleOp) {
		this.ruleOp = ruleOp;
	}
	public String getRealValue() {
		return realValue;
	}
	public void setRealValue(String realValue) {
		this.realValue = realValue;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	/*public String getIsCollectionType() {
		return isCollectionType;
	}

	public void setIsCollectionType(String isCollectionType) {
		this.isCollectionType = isCollectionType;
	}*/
	
}
