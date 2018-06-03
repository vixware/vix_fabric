package com.vix.system.enumeration.entity;

import com.vix.common.share.entity.BaseEntity;

/** 
 * 
 * 枚举值
 * 
 * @author arron
 *
 */
public class EnumValue extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 枚举值 */	
 	private String value;
 	/** 显示信息 */	
 	private String displayName;
	/** 是否启用 */
	private String enable;
	/** 是否缺省 */
	private String isDefault;
 	/** 枚举项 */	
 	private Enumeration enumeration;
 	/** 枚举值编码*/
 	private String enumValueCode;
 	
 	public EnumValue(){}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public Enumeration getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

	public String getEnumValueCode() {
		return enumValueCode;
	}

	public void setEnumValueCode(String enumValueCode) {
		this.enumValueCode = enumValueCode;
	}
	
}
