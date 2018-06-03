package com.vix.contract.config.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.contract.mamanger.entity.Contract;

/**所属行业*/
public class ViewIndustryType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	private String disabled;
	
	/** 合同*/
	private Set<Contract> contract = new HashSet<Contract>();
	
	/** 合同查询*/
	private Set<ContractInquiry> contractInquiry = new HashSet<ContractInquiry>();
	
	public ViewIndustryType(){}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Set<Contract> getContract() {
		return contract;
	}

	public void setContract(Set<Contract> contract) {
		this.contract = contract;
	}

	public Set<ContractInquiry> getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(Set<ContractInquiry> contractInquiry) {
		this.contractInquiry = contractInquiry;
	}
}
