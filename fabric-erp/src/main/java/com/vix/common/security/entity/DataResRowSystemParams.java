package com.vix.common.security.entity;

import java.util.Set;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.share.entity.BaseEntity;

/**
 * 系统常量配置
 * @author Administrator
 *
 */
public class DataResRowSystemParams extends BaseEntity {

	
	/** S 系统常量  
	 *  P 预处理常量
	 * 
	 * */
	private String paramType;
	
	/** {SysRole} */
	private String keyProperty;
	/** 系统角色 */
	private String keyPropertyName;
	
	/**
	 * 值类型
	 * select 选择
	 * 														radio  从原数据加载选择框  且为单选  checkbox 从原数据加载选择框  且为多选
	 * text	纯文本值  自定义填写
	 * sql 纯文本  自定义sql
	 * hql  自定义HQL
	 */
	private String valueType;
	
	/**  /common/security/userAccountAction!goChooseRole.action?tag=choose s*/
	private String paramUrl;
	//后面可以做成通用的然后读取baseMetaDataDefine
	
	private String isActive;
	
	 /** 元数据对象 */
    private BaseMetaData baseMetaData;
    
    
    
    
    /**
     * 预处理数据使用  系统常量不适用
     * 角色信息
     */
    private Set<Role> roles;
    
    /**
     * 0 是系统常量，不从预处理数据中读取
     * 1 读取dataResRowPredatas
     */
    private String readFromPreData;
    
    /**
     * 预处理数据使用
     */
    //private Set<DataResRowPredata> dataResRowPredatas;

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getKeyProperty() {
		return keyProperty;
	}

	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	public String getKeyPropertyName() {
		return keyPropertyName;
	}

	public void setKeyPropertyName(String keyPropertyName) {
		this.keyPropertyName = keyPropertyName;
	}

	public String getParamUrl() {
		return paramUrl;
	}

	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}

	public BaseMetaData getBaseMetaData() {
		return baseMetaData;
	}

	public void setBaseMetaData(BaseMetaData baseMetaData) {
		this.baseMetaData = baseMetaData;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/*public Set<DataResRowPredata> getDataResRowPredatas() {
		return dataResRowPredatas;
	}

	public void setDataResRowPredatas(Set<DataResRowPredata> dataResRowPredatas) {
		this.dataResRowPredatas = dataResRowPredatas;
	}*/

	public String getReadFromPreData() {
		return readFromPreData;
	}

	public void setReadFromPreData(String readFromPreData) {
		this.readFromPreData = readFromPreData;
	}
}
