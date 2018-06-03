/**   
* @Title: ContractOpenValue.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 上午11:05:18  
*/
package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractOpenValue
 * @Description:开口金额 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 上午11:05:18
 */
public class ContractOpenValue extends BaseEntity{

	private static final long serialVersionUID = -438383369952867199L;
	/**
	 * 合同编号
	 */
	private String contractCode;
	/**
	 * 合同开口金额
	 */
	private Double contractOpenValue;
	/**
	 * 开口合同金额审批原因
	 */
	private String contractApproveReason;
	/**
	 * 审批意见
	 *//*
	private Set<ApprovalOpinion> approvalOpinions = new HashSet<ApprovalOpinion>();
	*//**
	 * 合同
	 *//*
	private Contract contract;
	*/
	public ContractOpenValue() {
		super();
	}

	public ContractOpenValue(String id) {
		super();
		setId(id);
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Double getContractOpenValue() {
		return contractOpenValue;
	}

	public void setContractOpenValue(Double contractOpenValue) {
		this.contractOpenValue = contractOpenValue;
	}

	public String getContractApproveReason() {
		return contractApproveReason;
	}

	public void setContractApproveReason(String contractApproveReason) {
		this.contractApproveReason = contractApproveReason;
	}

}
