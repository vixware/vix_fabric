/**   
* @Title: Digistment.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午1:25:59  
*/
package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: Digistment
 * @Description:谈判纪要 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午1:25:59
 */
public class Digistment extends BaseEntity {

	private static final long serialVersionUID = -2036455815013906714L;
	/**
	 * 合同编号
	 */
	private String contractCode;
	/**
	 * 模板编号
	 */
	private String templeteId;
	/**
	 * 审批单编号
	 */
	private String contractApproveCode;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * 模板中字段序列
	 */
	private String templeteSequence;
	/**
	 * 数据项
	 */
	private Object data;
	/**
	 * 合同
	 *//*
	private Contract contract;
*/
	public Digistment() {
		super();
	}
	
	public Digistment(String id) {
		super();
		setId(id);
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getTempleteId() {
		return templeteId;
	}

	public void setTempleteId(String templeteId) {
		this.templeteId = templeteId;
	}

	public String getContractApproveCode() {
		return contractApproveCode;
	}

	public void setContractApproveCode(String contractApproveCode) {
		this.contractApproveCode = contractApproveCode;
	}

	public String getTempleteSequence() {
		return templeteSequence;
	}

	public void setTempleteSequence(String templeteSequence) {
		this.templeteSequence = templeteSequence;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
}
