package com.vix.common.share.entity;

import java.util.Date;

/**
 * 共用-对象修改记录
 * @author Administrator
 *
 */
public class CMNObjectModifyRecord extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 业务对象编码  */
	private String boCode;
	/** 业务对象名称 */
	private String boName;
	/** 业务对象类型 */
	private String boStyle;
	/** 行代码 */
	private String rowCode;
	/** 键 */
	private String key;
	/** 值 */
	private String oldValue;
	/** 值 */
	private String newValue;
	/** 操作人 */
	private String operator;
	/** 操作时间  */
	private Date operateTime;
	
	public CMNObjectModifyRecord(){}

	public String getBoCode() {
		return boCode;
	}

	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getBoStyle() {
		return boStyle;
	}

	public void setBoStyle(String boStyle) {
		this.boStyle = boStyle;
	}

	public String getRowCode() {
		return rowCode;
	}

	public void setRowCode(String rowCode) {
		this.rowCode = rowCode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
}
