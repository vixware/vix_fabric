package com.vix.core.persistence.jdbc.dbstruct;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * 系统扩展属性
 * @author arron
 *
 */
public class ObjectExpandField extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 所属类型 */
	private ObjectExpand objectExpand;
	/** 显示名称 */
	private String displayName;
	/** 数据库属性名称 */
	private String fieldName;
	/** 数据库属性类型 */
	private String fieldType;
	/** 数据库属性长度 */
	private Long length;
	/** 精度 */
	private Long accuracy;
	/** 引用字典 */
	private String refTag;
	/** 默认值 */
	private String defaultValue;
	/** 是否必添  0：否 1：是 */
	private String isRequired;
	/** 是否显示  0：否 1：是 */
	private String isShow;
	/** 顺序号 */
	private Long orderNumber;
	/** 扩展表名称 */
	private String expandTableName;
	/** 备注 */
	private String memo;
	
	public ObjectExpandField(){}
 
	public ObjectExpand getObjectExpand() {
		return objectExpand;
	}

	public void setObjectExpand(ObjectExpand objectExpand) {
		this.objectExpand = objectExpand;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Long getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Long accuracy) {
		this.accuracy = accuracy;
	}

	public String getRefTag() {
		return refTag;
	}

	public void setRefTag(String refTag) {
		this.refTag = refTag;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}
 
	public String getExpandTableName() {
		return expandTableName;
	}

	public void setExpandTableName(String expandTableName) {
		this.expandTableName = expandTableName;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
