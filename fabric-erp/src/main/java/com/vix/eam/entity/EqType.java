package com.vix.eam.entity;

import com.vix.eam.common.entity.EamBaseEntity;

public class EqType extends EamBaseEntity {

	/** * 类别名称	 */
	private String typeName;
	/** * 类别编码	 */
	private String typeCode;
	
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
}
