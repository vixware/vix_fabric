package com.vix.common.orginialMeta.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: OrginialBillsType
 * @Description: 单据类型
 * @author wangmingchen
 * @date 2014年11月1日 上午9:26:26
 */
public class OrginialBillsType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 类型编码 */
	private String typeCode;
	/** 类型名称 */
	private String typeName;
	/** 类型描述 */
	//private String typeDescription;
	/** 所属分类 */
	private OrginialBillsProperty orginialBillsProperty;
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/*public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}*/
	public OrginialBillsProperty getOrginialBillsProperty() {
		return orginialBillsProperty;
	}
	public void setOrginialBillsProperty(OrginialBillsProperty orginialBillsProperty) {
		this.orginialBillsProperty = orginialBillsProperty;
	}
}
