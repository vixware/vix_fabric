/**   
* @Title: ContractPaymentRecord.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午2:37:34  
*/
package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractPaymentRecord
 * @Description:合同收付款记录 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午2:37:34
 */
public class ContractPaymentRecord extends BaseEntity {

	private static final long serialVersionUID = -7960464708092994143L;
	/**
	 * 合同编码
	 */
	private String contractCode;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 金额
	 */
	private Double amount;
	/**
	 * 付款类型
	 */
	private String type;
	/**
	 * 收付款人
	 */
	private String aprPerson;
	/**
	 * 收付款时间
	 */
	private Date aprTime;
	/**
	 * 合同
	 *//*
	private Contract contract;*/
	
	public ContractPaymentRecord() {
		super();
	}
	public ContractPaymentRecord(String id) {
		super();
		setId(id);
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAprPerson() {
		return aprPerson;
	}
	public void setAprPerson(String aprPerson) {
		this.aprPerson = aprPerson;
	}
	public Date getAprTime() {
		return aprTime;
	}
	public void setAprTime(Date aprTime) {
		this.aprTime = aprTime;
	}
}
