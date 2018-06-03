package com.vix.core.persistence.jdbc.dbstruct;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 对象类型
 * 
 * @author arron
 *
 */
public class ObjectExpand extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 扩展对象编码 */
	private String extendedObjectCode;
	/** 扩展表 */
	private String expandTableName;
	/** 扩展表是否已经生成 1已生成 0 未生成 */
	private String isGenerateTable;
	/** 外键key */
	private String foreignKey;
	/** 是否已经被引用 1：被引用 0：未引用 */
	private String isReference;
	/** 扩展字段 */
	private Set<ObjectExpandField> objectExpandFields = new HashSet<ObjectExpandField>();
	/** 对象类型下的规格 */
	private Set<Specification> specifications = new HashSet<Specification>();
	/** 规格表是否已生成 0：未生成 1： 已生成 */
	private String specTableIsGenerate;
	/** 是否已删除 */
	private String isDelete;

	public ObjectExpand() {
	}

	public String getExtendedObjectCode() {
		return extendedObjectCode;
	}

	public void setExtendedObjectCode(String extendedObjectCode) {
		this.extendedObjectCode = extendedObjectCode;
	}

	public String getExpandTableName() {
		return expandTableName;
	}

	public void setExpandTableName(String expandTableName) {
		this.expandTableName = expandTableName;
	}

	public String getIsGenerateTable() {
		return isGenerateTable;
	}

	public void setIsGenerateTable(String isGenerateTable) {
		this.isGenerateTable = isGenerateTable;
	}

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	public String getIsReference() {
		if (null == isReference) {
			isReference = "";
		}
		return isReference;
	}

	public void setIsReference(String isReference) {
		this.isReference = isReference;
	}

	public Set<ObjectExpandField> getObjectExpandFields() {
		return objectExpandFields;
	}

	public void setObjectExpandFields(Set<ObjectExpandField> objectExpandFields) {
		this.objectExpandFields = objectExpandFields;
	}

	public Set<Specification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<Specification> specifications) {
		this.specifications = specifications;
	}

	public String getSpecTableIsGenerate() {
		return specTableIsGenerate;
	}

	public void setSpecTableIsGenerate(String specTableIsGenerate) {
		this.specTableIsGenerate = specTableIsGenerate;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public boolean getHasSpecifications() {
		if (specifications.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}