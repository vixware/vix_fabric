/**   
* @Title: ProtocolTemplete.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午1:49:32  
*/
package com.vix.contract.mamanger.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ProtocolTemplete
 * @Description:合同与协议模版 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午1:49:32
 */
public class ProtocolTemplete extends BaseEntity {

	private static final long serialVersionUID = 5543195697375752081L;
	/**
	 * 协议类型
	 */
	private String protocolType;
	/**
	 * 是否预置
	 */
	private String isPreset;
	/**
	 * word模版
	 */
	private String wordFile;
	/**
	 * 配置文件
	 */
	private String configFile;
	/**
	 * 模板分类
	 */
	private String templateClassify;
	/**
	 * 模板使用
	 */
	private String attribute7;
	/**
	 * 甲方姓名
	 */
	private String partyName;
	/**
	 * 乙方姓名
	 */
	private String bsquarename;
	/**
	 * 协议内容
	 */
	private String agreementContent;
	/**
	 * 甲方签章
	 */
	private String partysignature;
	/**
	 * 乙方签章
	 */
	private String bsquareSignature;
	/**
	 * 身份证
	 */
	private String idName;
	/**
	 * 邮政编码
	 */
	private String postalCoding;
	/**
	 * 合同签订地点
	 */
	private String contractSignedLocation;
	/**
	 * 合同签订时间
	 */
	private Date contractSignedTime;
	
	/** 合同附件 */
	private Set<ContractTemplate> contractTemplate = new HashSet<ContractTemplate>();
	
	/**
	 * 合同
	 *//*
	private Set<Contract> contract = new HashSet<Contract>();
*/
	public ProtocolTemplete() {
		super();
	}
	
	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public ProtocolTemplete(String id) {
		super();
		setId(id);
	}

	public String getIsPreset() {
		return isPreset;
	}

	public void setIsPreset(String isPreset) {
		this.isPreset = isPreset;
	}

	public String getWordFile() {
		return wordFile;
	}

	public void setWordFile(String wordFile) {
		this.wordFile = wordFile;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public String getTemplateClassify() {
		return templateClassify;
	}

	public void setTemplateClassify(String templateClassify) {
		this.templateClassify = templateClassify;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


	public String getAgreementContent() {
		return agreementContent;
	}

	public void setAgreementContent(String agreementContent) {
		this.agreementContent = agreementContent;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getPostalCoding() {
		return postalCoding;
	}

	public void setPostalCoding(String postalCoding) {
		this.postalCoding = postalCoding;
	}

	public String getContractSignedLocation() {
		return contractSignedLocation;
	}

	public void setContractSignedLocation(String contractSignedLocation) {
		this.contractSignedLocation = contractSignedLocation;
	}

	public Date getContractSignedTime() {
		return contractSignedTime;
	}

	public void setContractSignedTime(Date contractSignedTime) {
		this.contractSignedTime = contractSignedTime;
	}

	public Set<ContractTemplate> getContractTemplate() {
		return contractTemplate;
	}

	public void setContractTemplate(Set<ContractTemplate> contractTemplate) {
		this.contractTemplate = contractTemplate;
	}


	public String getBsquarename() {
		return bsquarename;
	}

	public void setBsquarename(String bsquarename) {
		this.bsquarename = bsquarename;
	}

	public String getPartysignature() {
		return partysignature;
	}

	public void setPartysignature(String partysignature) {
		this.partysignature = partysignature;
	}

	public String getBsquareSignature() {
		return bsquareSignature;
	}

	public void setBsquareSignature(String bsquareSignature) {
		this.bsquareSignature = bsquareSignature;
	}
	
}
