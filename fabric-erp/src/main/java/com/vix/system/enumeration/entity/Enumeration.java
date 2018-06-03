package com.vix.system.enumeration.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * 枚举项
 * 系统中所有可能在系统运维过程中发生变化的小数据量字典信息
 * @author arron
 *
 */
public class Enumeration extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 枚举名称 */	
 	private String name;
 	/** 显示信息 */	
 	private String displayName;
 	/** 字典值 */	
 	private Set<EnumValue> enumValues = new HashSet<EnumValue>();
 	/** 字典分类 */
 	private EnumerationCategory enumerationCategory;
 	/**枚举编码*/
 	private String enumerationCode;
 	
 	public Enumeration(){}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Set<EnumValue> getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(Set<EnumValue> enumValues) {
		this.enumValues = enumValues;
	}

	public EnumerationCategory getEnumerationCategory() {
		return enumerationCategory;
	}

	public void setEnumerationCategory(EnumerationCategory enumerationCategory) {
		this.enumerationCategory = enumerationCategory;
	}

	public Integer getValueSize(){
		return enumValues.size();
	}

	public String getEnumerationCode() {
		return enumerationCode;
	}

	public void setEnumerationCode(String enumerationCode) {
		this.enumerationCode = enumerationCode;
	}
	
}
