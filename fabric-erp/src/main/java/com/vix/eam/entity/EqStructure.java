package com.vix.eam.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.eam.common.entity.EamBaseEntity;


public class EqStructure extends EamBaseEntity{

	/**编码*/
	private String eqCode;
	/**名称*/
	private String eqName;
	/**父设备id*/
	private String parentId;
	/**描述*/
	private String memo;
	/**类型,0：逻辑位置(逻辑设备，该位置下挂物理设备)；1：设备（即有相同编号的物理设备,在该设备下挂其他设备）*/
	private String eqType;
	/** 子分类集合 */
	private Set<EqStructure> subEqStructure = new HashSet<EqStructure>();
	
	public String getEqCode() {
		return eqCode;
	}
	public void setEqCode(String eqCode) {
		this.eqCode = eqCode;
	}
	public String getEqName() {
		return eqName;
	}
	public void setEqName(String eqName) {
		this.eqName = eqName;
	}
	@Override
	public String getMemo() {
		return memo;
	}
	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEqType() {
		return eqType;
	}
	public void setEqType(String eqType) {
		this.eqType = eqType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Set<EqStructure> getSubEqStructure() {
		return subEqStructure;
	}
	public void setSubEqStructure(Set<EqStructure> subEqStructure) {
		this.subEqStructure = subEqStructure;
	}

}
