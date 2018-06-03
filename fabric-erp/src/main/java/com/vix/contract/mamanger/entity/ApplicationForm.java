/**   
 * @Title: Applicationform.java 
 * @Package com.vix.contract.entity 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-8 下午2:09:35  
 */
package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.core.utils.DateUtil;

/**
 * @ClassName: ApplicationForm
 * @Description:合同审批单
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-8 下午2:09:35
 */
public class ApplicationForm extends BaseEntity {

	private static final long serialVersionUID = 5818388791584655491L;

	/**
	 * 审批单编号
	 **/
	private String approveProcessCode;
	/**
	 * 流程实例编号
	 **/
	private String processInstanceId;
	/**
	 * 承办人
	 **/
	private String underTakePerson;
	/**
	 * 承办单位
	 * */
	private String underTakeCompany;
	/**
	 * 承办时间
	 **/
	private Date underTakeDate;
	/**
	 * 完成形式
	 **/
	private String completeType;
	/**
	 * 单价
	 **/
	private Double unitPrice;
	/**
	 * 数量
	 **/
	private String count;
	/**
	 * 供方类型审批单号，合同中唯一
	 * */
	private String approveCode;
	/**
	 * 所属课题编号
	 **/
	private String subjectId;
	/**
	 * 课题子类
	 **/
	private String subjectChild;
	/**
	 * 其他需要说明情况
	 **/
	private String otherCondition;
	/**
	 * 外协厂家，其他需要说明情况，乙方
	 **/
	private String otherCondition2;
	/** 合同 */
	private Contract contract;
	
	/** 合同查询 */
	private ContractInquiry contractInquiry;

	

	public String getApproveProcessCode() {
		return approveProcessCode;
	}

	public void setApproveProcessCode(String approveProcessCode) {
		this.approveProcessCode = approveProcessCode;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getUnderTakePerson() {
		return underTakePerson;
	}

	public void setUnderTakePerson(String underTakePerson) {
		this.underTakePerson = underTakePerson;
	}

	public String getUnderTakeCompany() {
		return underTakeCompany;
	}

	public void setUnderTakeCompany(String underTakeCompany) {
		this.underTakeCompany = underTakeCompany;
	}

	public Date getUnderTakeDate() {
		return underTakeDate;
	}
	public String getUnderTakeDateStr() {
		if(underTakeDate != null){
			return DateUtil.format(underTakeDate);
		}
		return "";
	}

	public void setUnderTakeDate(Date underTakeDate) {
		this.underTakeDate = underTakeDate;
	}

	public String getCompleteType() {
		return completeType;
	}

	public void setCompleteType(String completeType) {
		this.completeType = completeType;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCount() {
		return count;
	}

	public String getApproveCode() {
		return approveCode;
	}

	public void setApproveCode(String approveCode) {
		this.approveCode = approveCode;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectChild() {
		return subjectChild;
	}

	public void setSubjectChild(String subjectChild) {
		this.subjectChild = subjectChild;
	}

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}

	public String getOtherCondition2() {
		return otherCondition2;
	}

	public void setOtherCondition2(String otherCondition2) {
		this.otherCondition2 = otherCondition2;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public ContractInquiry getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(ContractInquiry contractInquiry) {
		this.contractInquiry = contractInquiry;
	}
}
