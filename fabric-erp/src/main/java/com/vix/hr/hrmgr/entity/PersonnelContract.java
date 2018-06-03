package com.vix.hr.hrmgr.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.job.entity.HrAttachments;

/**
 * @Description: 人事合同
 * @author 李大鹏
 */
public class PersonnelContract extends BaseEntity {

	private static final long serialVersionUID = 3632869828740959844L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 合同编号 */
	private String contractCode;
	/** 合同类型 */
	private String contractType;
	/** 合同状态 */
	private String contractState;
	/** 当事人 */
	private String party;
	/** 经办人 */
	private String attn;
	/** 批准人 */
	private String approver;
	/** 签订日期 */
	private Date signingDate;
	/** 甲方名称 */
	private String jiaName;
	/** 甲方地址： */
	private String jiaAddress;
	/** 甲方代表人： */
	private String representative;
	/** 甲方所属行业 */
	private String jiaIndustry;
	/** 乙方名称： */
	private String lesseenName;
	/** 乙方住址： */
	private String lesseenAddress;
	/** 乙方身份证号： */
	private String IDNumber;
	/** 合同期限： */
	private String contractPeriod;
	/** 合同期限类别： */
	private String contractPeriodType;
	/** 生效日期： */
	private Date availabilityDate;
	/** 到期日期： */
	private Date dueDate;
	/** 试用期： */
	private String probation;
	/** 是否备案： */
	private String record;
	/** 续签日期： */
	private Date renewalDate;
	/** 解除/终止日期 */
	private Date enddingDate;
	/** 劳动合同违约金额： */
	private String contractAmount;
	/** 单位支付补偿金额： */
	private String orgContractAmount;
	/** 备注 */
	private String remarks;
	/** 附件 */
	private Set<HrAttachments> attachments = new HashSet<HrAttachments>();

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractState() {
		return contractState;
	}

	public void setContractState(String contractState) {
		this.contractState = contractState;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getAttn() {
		return attn;
	}

	public void setAttn(String attn) {
		this.attn = attn;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getSigningDate() {
		return signingDate;
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public String getJiaName() {
		return jiaName;
	}

	public void setJiaName(String jiaName) {
		this.jiaName = jiaName;
	}

	public String getJiaAddress() {
		return jiaAddress;
	}

	public void setJiaAddress(String jiaAddress) {
		this.jiaAddress = jiaAddress;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getJiaIndustry() {
		return jiaIndustry;
	}

	public void setJiaIndustry(String jiaIndustry) {
		this.jiaIndustry = jiaIndustry;
	}

	public String getLesseenName() {
		return lesseenName;
	}

	public void setLesseenName(String lesseenName) {
		this.lesseenName = lesseenName;
	}

	public String getLesseenAddress() {
		return lesseenAddress;
	}

	public void setLesseenAddress(String lesseenAddress) {
		this.lesseenAddress = lesseenAddress;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public String getContractPeriod() {
		return contractPeriod;
	}

	public void setContractPeriod(String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public String getContractPeriodType() {
		return contractPeriodType;
	}

	public void setContractPeriodType(String contractPeriodType) {
		this.contractPeriodType = contractPeriodType;
	}

	public Date getAvailabilityDate() {
		return availabilityDate;
	}

	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getProbation() {
		return probation;
	}

	public void setProbation(String probation) {
		this.probation = probation;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public Date getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(Date renewalDate) {
		this.renewalDate = renewalDate;
	}

	public Date getEnddingDate() {
		return enddingDate;
	}

	public void setEnddingDate(Date enddingDate) {
		this.enddingDate = enddingDate;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getOrgContractAmount() {
		return orgContractAmount;
	}

	public void setOrgContractAmount(String orgContractAmount) {
		this.orgContractAmount = orgContractAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<HrAttachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<HrAttachments> attachments) {
		this.attachments = attachments;
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
