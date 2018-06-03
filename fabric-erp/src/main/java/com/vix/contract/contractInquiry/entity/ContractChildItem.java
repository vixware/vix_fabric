package com.vix.contract.contractInquiry.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.mamanger.entity.Contract;

/**
 * 
* @ClassName: ContractChildItem
* @Description: 合同子项
* @author bobchen
* @date 2015年12月25日 上午9:40:57
*
 */
public class ContractChildItem extends BaseEntity {

	private static final long serialVersionUID = -9159059653064562340L;
	/** 类型 */
	private String contractType;
	/** 状态 */
	private String contractStatus;
	/** 内容 */
	private String theContract;
	/** 合同查询 */
	private ContractInquiry contractInquiry;
	/** 合同 */
	private Contract contract;
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

	public String getTheContract() {
		return theContract;
	}

	public void setTheContract(String theContract) {
		this.theContract = theContract;
	}

	public ContractInquiry getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(ContractInquiry contractInquiry) {
		this.contractInquiry = contractInquiry;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
