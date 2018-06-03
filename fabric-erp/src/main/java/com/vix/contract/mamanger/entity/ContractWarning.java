/**   
* @Title: ContractWarning.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 上午11:15:07  
*/
package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.core.utils.DateUtil;

/**
 * @ClassName: ContractWarning
 * @Description: 合同预警
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 上午11:15:07
 */
public class ContractWarning extends BaseEntity{

	private static final long serialVersionUID = 6342651875674889115L;
	/**
	 * 预警合同编码
	 */
	private String warnningContractCode;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 预警时间
	 */
	private Date warnningTime;
	/**
	 * 预警信息
	 */
	private String warnningContent;
	/**
	 * 预警类型
	 */
	private String warnningType;
	/**
	 * 状态
	 */
	private String mode;
	/**
	 * 合同
	 */
	private Contract contract;
	
	/** 合同查询 */
	private ContractInquiry contractInquiry;
	
	public ContractWarning() {
		super();
	}
	
	public ContractWarning(String id) {
		super();
		setId(id);
	}



	public String getWarnningContractCode() {
		return warnningContractCode;
	}

	public void setWarnningContractCode(String warnningContractCode) {
		this.warnningContractCode = warnningContractCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Date getWarnningTime() {
		return warnningTime;
	}
	public String getWarnningTimeStr() {
		if(warnningTime != null){
			return DateUtil.format(warnningTime);
		}
		return "";
	}

	public void setWarnningTime(Date warnningTime) {
		this.warnningTime = warnningTime;
	}

	public String getWarnningContent() {
		return warnningContent;
	}

	public void setWarnningContent(String warnningContent) {
		this.warnningContent = warnningContent;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getWarnningType() {
		return warnningType;
	}

	public void setWarnningType(String warnningType) {
		this.warnningType = warnningType;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public ContractInquiry getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(ContractInquiry contractInquiry) {
		this.contractInquiry = contractInquiry;
	}
}
