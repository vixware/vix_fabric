package com.vix.common.meta.vo;

import com.vix.core.utils.excel.ExcelVOAttribute;

/**
 * 元数据定义(固有属性)
 * @author Administrator
 *
 */
public class BaseMetaDataDefineImpVo {
	
	private static final long serialVersionUID = 1L;
	
	/** 元数据编码 */
	@ExcelVOAttribute(name = "元数据编码", column = "A", isExport = true, prompt="元数据编码必须填写！")
	private String metaDataCode;
	
	/** 属性描述  */
	@ExcelVOAttribute(name = "属性描述", column = "B", isExport = true, prompt="属性描述必须填写！")
	private String propertyName;
	
	/** 属性编码  */
	@ExcelVOAttribute(name = "属性编码", column = "C", isExport = true, prompt="属性编码必须填写！")
	private String propertyCode;
	
	/** 属性  */
	@ExcelVOAttribute(name = "属性", column = "D", isExport = true, prompt="属性必须填写！")
	private String property;

	/** 属性数据类型  */
	@ExcelVOAttribute(name = "属性数据类型", column = "E", isExport = false)//, prompt="属性数据类型必须填写！"
	private String propertyType;
	
	/** 简单数据类型类型  */
	@ExcelVOAttribute(name = "简单数据类型类型", column = "F", isExport = false)//, prompt="简单数据类型类型必须填写！"
	private String dataType;
	
	/** 是否集合属性 */
	@ExcelVOAttribute(name = "是否集合属性 ", column = "G", isExport = true,prompt="是否集合属性必须填写！")//, prompt="简单数据类型类型必须填写！"
	private String isCollectionType;
	
	/** 是否显示 */
	@ExcelVOAttribute(name = "是否显示 ", column = "H", isExport = true,prompt="是否显示必须填写！")//, prompt="简单数据类型类型必须填写！"
	private String isSelectView;

	public BaseMetaDataDefineImpVo() {
		super();
	}
	
	public BaseMetaDataDefineImpVo(String metaDataCode, String propertyName,
			String propertyCode, String property, String propertyType,
			String dataType, String isCollectionType, String isSelectView) {
		super();
		this.metaDataCode = metaDataCode;
		this.propertyName = propertyName;
		this.propertyCode = propertyCode;
		this.property = property;
		this.propertyType = propertyType;
		this.dataType = dataType;
		this.isCollectionType = isCollectionType;
		this.isSelectView = isSelectView;
	}



	public String getMetaDataCode() {
		return metaDataCode;
	}

	public void setMetaDataCode(String metaDataCode) {
		this.metaDataCode = metaDataCode;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getIsCollectionType() {
		return isCollectionType;
	}

	public void setIsCollectionType(String isCollectionType) {
		this.isCollectionType = isCollectionType;
	}

	public String getIsSelectView() {
		return isSelectView;
	}

	public void setIsSelectView(String isSelectView) {
		this.isSelectView = isSelectView;
	}
	
}
