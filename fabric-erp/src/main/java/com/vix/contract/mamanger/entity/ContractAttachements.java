/**   
* @Title: ContractAttachements.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午2:31:56  
*/
package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractAttachements
 * @Description:合同附件 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午2:31:56
 */
public class ContractAttachements extends BaseEntity {

	private static final long serialVersionUID = -4911603398234704239L;
	/**
	 * 合同编码
	 */
	private String contractCode;
	/**
	 * 合同文本文件路径
	 */
	private String contractFilePath;
	/**
	 * 谈判纪要文件路径
	 */
	private String negotiationsContextPath;
	/**
	 * 合同
	 *//*
	private Set<Contract> contract = new HashSet<Contract>();
	*/
	public ContractAttachements() {
		super();
	}
	public ContractAttachements(String id) {
		super();
		setId(id);
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractFilePath() {
		return contractFilePath;
	}
	public void setContractFilePath(String contractFilePath) {
		this.contractFilePath = contractFilePath;
	}
	public String getNegotiationsContextPath() {
		return negotiationsContextPath;
	}
	public void setNegotiationsContextPath(String negotiationsContextPath) {
		this.negotiationsContextPath = negotiationsContextPath;
	}
	
}
