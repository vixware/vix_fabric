/**
 * 
 */
package com.vix.system.billTypeManagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

/**
 * 单据类型
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class BillsType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3499771522069567606L;
	/** 类型编码 */
	private String typeCode;
	/** 类型名称 */
	private String typeName;
	/** 类型描述 */
	private String typeDescription;
	/** 所属分类 */
	private BillsProperty billsProperty;
	/* 编码管理 */
	private Set<EncodingRulesTableInTheMiddle> encodingRulesTableInTheMiddles = new HashSet<EncodingRulesTableInTheMiddle>();

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

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public BillsProperty getBillsProperty() {
		return billsProperty;
	}

	public void setBillsProperty(BillsProperty billsProperty) {
		this.billsProperty = billsProperty;
	}

	public Set<EncodingRulesTableInTheMiddle> getEncodingRulesTableInTheMiddles() {
		return encodingRulesTableInTheMiddles;
	}

	public void setEncodingRulesTableInTheMiddles(Set<EncodingRulesTableInTheMiddle> encodingRulesTableInTheMiddles) {
		this.encodingRulesTableInTheMiddles = encodingRulesTableInTheMiddles;
	}
}
