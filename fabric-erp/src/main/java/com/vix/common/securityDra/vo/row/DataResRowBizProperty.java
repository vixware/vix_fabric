package com.vix.common.securityDra.vo.row;

import java.io.Serializable;

/**
 * 行级权限的  业务对象（集合）属性的约束条件   用于序列化和反序列化
 * @author Administrator
 *
 */
public class DataResRowBizProperty implements Serializable {

	/** 当前属性对应的主对象的业务对象别名 */
	private String masterAlilasName;
	
	/** 当前属性对应别名 */
	private String bizAlilasName;
	
	/** 业务对象属性 */
	private String bizProperty;
	
	/**
	 * 业务对象类型
	 * true  业务对象
	 * false 集合属性
	 */
	private boolean isBizType;
	
	/** 
	 * 用于判断实际hql中是否有改   主对象的别名.属性
	 * masterAlilasName.bizProperty
	 */
	private String innerJoinBizCondition;
	
	/** 操作符号 */
	private String op;
	
	/**
	 * 业务对象类型时条件
	 */
	private String hqlBizCondition;
	/**
	 * 业务对象为集合时的条件
	 */
	private String hqlBizSetCondition;
	
	public String getMasterAlilasName() {
		return masterAlilasName;
	}
	public void setMasterAlilasName(String masterAlilasName) {
		this.masterAlilasName = masterAlilasName;
	}
	public String getBizAlilasName() {
		return bizAlilasName;
	}
	public void setBizAlilasName(String bizAlilasName) {
		this.bizAlilasName = bizAlilasName;
	}
	public String getBizProperty() {
		return bizProperty;
	}
	public void setBizProperty(String bizProperty) {
		this.bizProperty = bizProperty;
	}

	public boolean isBizType() {
		return isBizType;
	}
	public void setBizType(boolean isBizType) {
		this.isBizType = isBizType;
	}
	public String getInnerJoinBizCondition() {
		return innerJoinBizCondition;
	}
	public void setInnerJoinBizCondition(String innerJoinBizCondition) {
		this.innerJoinBizCondition = innerJoinBizCondition;
	}
	public String getHqlBizCondition() {
		return hqlBizCondition;
	}
	public void setHqlBizCondition(String hqlBizCondition) {
		this.hqlBizCondition = hqlBizCondition;
	}
	public String getHqlBizSetCondition() {
		return hqlBizSetCondition;
	}
	public void setHqlBizSetCondition(String hqlBizSetCondition) {
		this.hqlBizSetCondition = hqlBizSetCondition;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	@Override
	public String toString() {
		return "DataResRowBizProperty [masterAlilasName=" + masterAlilasName
				+ ", bizAlilasName=" + bizAlilasName + ", bizProperty="
				+ bizProperty + ", isBizType=" + isBizType
				+ ", innerJoinBizCondition=" + innerJoinBizCondition
				+ ", hqlBizCondition=" + hqlBizCondition
				+ ", hqlBizSetCondition=" + hqlBizSetCondition + "]";
	}
	
}
