/**   
* @Title: ApprovalOpinion.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 上午10:50:23  
*/
package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ApprovalOpinion
 * @Description: 审批意见
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 上午10:50:23
 */
public class ApprovalOpinion extends BaseEntity{

	private static final long serialVersionUID = 6671373447246378466L;
	/**
	 * 合并编码
	 */
	private String contractCode;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 审批流程编码
	 */
	private String approvalProcessCode;
	/**
	 * 审批单编码
	 */
	private String approvalCode;
	/**
	 * 审批部门编码
	 */
	private String apprDepartmentCode;
	/**
	 * 审批部门
	 */
	private String apprDepartment;
	/**
	 * 审批人
	 */
	private String approval;
	/**
	 * 审批意见
	 */
	private String approvalOpinion;
	/**
	 * 审批时间
	 */
	private Date approvalTime;
	/**
	 * 合同审批单
	 */
	/*private ApplicationForm applicationForm;*/
	/**
	 * 开口金额
	 *//*
	private ContractOpenValue contractOpenValue;*/
	
	public ApprovalOpinion() {
		super();
	}

	public ApprovalOpinion(String id) {
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
	public String getApprovalProcessCode() {
		return approvalProcessCode;
	}
	public void setApprovalProcessCode(String approvalProcessCode) {
		this.approvalProcessCode = approvalProcessCode;
	}
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	public String getApprDepartmentCode() {
		return apprDepartmentCode;
	}
	public void setApprDepartmentCode(String apprDepartmentCode) {
		this.apprDepartmentCode = apprDepartmentCode;
	}
	public String getApprDepartment() {
		return apprDepartment;
	}
	public void setApprDepartment(String apprDepartment) {
		this.apprDepartment = apprDepartment;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getApprovalOpinion() {
		return approvalOpinion;
	}
	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	
}
