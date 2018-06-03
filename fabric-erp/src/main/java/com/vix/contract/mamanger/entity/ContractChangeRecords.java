package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractChangeRecords
 * @Description: 合同变更信息
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-8 下午3:19:03
 */
public class ContractChangeRecords extends BaseEntity {

	private static final long serialVersionUID = 4182185455793650367L;
	/**
	 * 单据编号
	 **//*
	private String Code;*/
	/**
	 * 修改的字段
	 **/
	private String updateField;
	/**
	 * 修改前信息
	 **/
	private String beforeUpdateValue;
	/**
	 * 修改后信息
	 **/
	private String afterUpdateValue;
	/**
	 * 修改时间
	 **/
	private Date lastModifyDate;
	/**
	 * 修改人
	 **/
	private String updatePerson;
	/** 
	 * 合同审批单
	 **/
	/*private ApplicationForm applicationForm;*/
	/**
	 * 合同
	 *//*
	private Contract contract;
*/
	private ContractChangeRecords() {
		super();
	}

	private ContractChangeRecords(String id) {
		super();
		setId(id);
	}

	/*public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}
*/
	public String getUpdateField() {
		return updateField;
	}

	public void setUpdateField(String updateField) {
		this.updateField = updateField;
	}

	public String getBeforeUpdateValue() {
		return beforeUpdateValue;
	}

	public void setBeforeUpdateValue(String beforeUpdateValue) {
		this.beforeUpdateValue = beforeUpdateValue;
	}

	public String getAfterUpdateValue() {
		return afterUpdateValue;
	}

	public void setAfterUpdateValue(String afterUpdateValue) {
		this.afterUpdateValue = afterUpdateValue;
	}

	@Override
	public Date getUpdateTime() {
		return lastModifyDate;
	}

	@Override
	public void setUpdateTime(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
}
