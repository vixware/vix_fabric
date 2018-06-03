/**   
 * @Title: ContractAssociateTemplate.java 
 * @Package com.vix.contract.entity 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-8 下午1:42:29  
 */
package com.vix.contract.mamanger.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractAssociateTemplate
 * @Description:合同关联模板
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-8 下午1:42:29
 */
public class ContractAssociateTemplate extends BaseEntity {

	private static final long serialVersionUID = -2623052639230330677L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;	
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/**
	 * 合同编号
	 * */
	private String contractCode;
	/**
	 * 主题
	 * */
	private String theme;	
	/**
	 * 状态
	 * */
	private String templateStatus;
	/**
	 * 创建人
	 * */
	private String created;
	/**
	 * 模板编号
	 * */
	private String templeteId;
	/**
	 * 审批单编号
	 * */
	private String contractApproveCode;
	/**
	 * 模板中字段序列
	 * */
	private String templeteSequence;
	/**
	 * 数据类型
	 * */
	private String dataType;
	/**
	 * 数据项
	 * */
	private Object data;
	
	/** 合同附件 */
	private Set<ContractTemplate> contractTemplate = new HashSet<ContractTemplate>();
	
	public ContractAssociateTemplate() {
		super();
	}

	public ContractAssociateTemplate(String id) {
		super();
		setId(id);
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getTempleteId() {
		return templeteId;
	}

	public void setTempleteId(String templeteId) {
		this.templeteId = templeteId;
	}

	public String getContractApproveCode() {
		return contractApproveCode;
	}

	public void setContractApproveCode(String contractApproveCode) {
		this.contractApproveCode = contractApproveCode;
	}

	public String getTempleteSequence() {
		return templeteSequence;
	}

	public void setTempleteSequence(String templeteSequence) {
		this.templeteSequence = templeteSequence;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getTemplateStatus() {
		return templateStatus;
	}

	public void setTemplateStatus(String templateStatus) {
		this.templateStatus = templateStatus;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Set<ContractTemplate> getContractTemplate() {
		return contractTemplate;
	}

	public void setContractTemplate(Set<ContractTemplate> contractTemplate) {
		this.contractTemplate = contractTemplate;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

}
