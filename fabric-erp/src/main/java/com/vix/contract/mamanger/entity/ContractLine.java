package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractLine
 * @Description: 合同子项
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-7-9 下午1:16:49
 */
public class ContractLine extends BaseEntity {

	private static final long serialVersionUID = -9159059653064562340L;
	/**
	 * 内容
	 **/
	private String theContract;
	/**
	 * 合同类型
	 **/
	private String contractType;
	/**
	 * 合同状态
	 **/
	private String contractStatus;
	/** 合同 */
	private Contract contract;
	
	public ContractLine() {
		super();
	}

	public ContractLine(String id) {
		super();
		setId(id);
	}
	
	public String getTheContract() {
		return theContract;
	}
	public void setTheContract(String theContract) {
		this.theContract = theContract;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
