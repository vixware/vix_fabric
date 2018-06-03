
package com.vix.contract.config.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractClause
 * @Description: 合同条款 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-7-21 下午4:43:50
 */
public class ContractClause extends BaseEntity{

	private static final long serialVersionUID = 1396865628678655168L;
	
	/**
	 * 合同编号
	 */
	private String contractCode;
	/**
	 * 条款内容
	 */
	private String clauseContent;
	/**
	 * 合同类型
	 */
	private String type;
	/**
	 * 状态
	 */
	private String mode;
	
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getClauseContent() {
		return clauseContent;
	}
	public void setClauseContent(String clauseContent) {
		this.clauseContent = clauseContent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
