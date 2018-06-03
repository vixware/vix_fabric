/**   
* @Title: ContractTemplate7.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午3:46:21  
*/
package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractTemplate7
 * @Description: 合同附件2 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午3:46:21
 */
public class ContractTemplate extends BaseEntity {

	private static final long serialVersionUID = -5532863115927903219L;
	/**
	 * 名称
	 *//*
	private String name;*/
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 时间
	 *//*
	private Date createDate;*/
	/**
	 * 文件大小
	 */
	private String fileSize;
	/**
	 * 上传人
	 */
	private String operator;
	
	/** 合同 */
	private Contract contract;
	
	/** 合同模板 */
	private ContractAssociateTemplate contractAssociateTemplate;
	/** 合同协议*/
	private ProtocolTemplete protocolTemplete;
	
	public ContractAssociateTemplate getContractAssociateTemplate() {
		return contractAssociateTemplate;
	}

	public void setContractAssociateTemplate(
			ContractAssociateTemplate contractAssociateTemplate) {
		this.contractAssociateTemplate = contractAssociateTemplate;
	}

	public ContractTemplate() {
		super();
	}
	
	public ContractTemplate(String id) {
		super();
		setId(id);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/*public Date getCreateTime() {
		return createDate;
	}

	public void setCreateTime(Date createDate) {
		this.createDate = createDate;
	}*/

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public ProtocolTemplete getProtocolTemplete() {
		return protocolTemplete;
	}

	public void setProtocolTemplete(ProtocolTemplete protocolTemplete) {
		this.protocolTemplete = protocolTemplete;
	}
	
}
