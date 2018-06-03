package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractItems
 * @Description:合同条款
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午3:56:07
 */
public class ContractItems extends BaseEntity {

	private static final long serialVersionUID = 2886497169106479701L;
	/**
	 * 合同编码
	 */
	private String contractCode;
	/**
	 * 条款主题
	 */
	private String subject;
	/**
	 * 条款内容
	 */
	private String content;
	/**
	 * 合同
	 *//*
	private Contract contract;
	*/
	public ContractItems() {
		super();
	}
	
	public ContractItems(String id) {
		super();
		setId(id);
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
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

}
