/**   
* @Title: ContractTrace.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午3:51:09  
*/
package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractTrace
 * @Description:合同执行跟踪 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午3:51:09
 */
public class ContractTrace extends BaseEntity {

	private static final long serialVersionUID = -1517759161132684219L;
	/**
	 * 合同编码
	 */
	private String contractCode;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 执行主题
	 */
	private String subject;
	/**
	 * 执行内容
	 */
	private String content;
	/**
	 * 执行人
	 */
	private String executor;
	/**
	 * 执行时间
	 */
	private Date executeTime;
	/**
	 * 合同
	 *//*
	private Contract contract;*/
	
	public ContractTrace() {
		super();
	}
	
	public ContractTrace(String id) {
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	/*public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}*/
	
}
