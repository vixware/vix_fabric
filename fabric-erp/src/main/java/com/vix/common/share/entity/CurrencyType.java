package com.vix.common.share.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.contract.mamanger.entity.Contract;


/**
 * 币种管理
 *
 */
public class CurrencyType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否本位币 1:是，0：否 */
	private String isBaseCurrency;
	
	/** 合同*/
	private Set<Contract> contract = new HashSet<Contract>();
	/** 合同查询*/
	private Set<ContractInquiry> contractInquiry = new HashSet<ContractInquiry>();
	
	public CurrencyType(){}

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

	public String getIsBaseCurrency() {
		return isBaseCurrency;
	}

	public void setIsBaseCurrency(String isBaseCurrency) {
		this.isBaseCurrency = isBaseCurrency;
	}

	public Set<ContractInquiry> getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(Set<ContractInquiry> contractInquiry) {
		this.contractInquiry = contractInquiry;
	}

	public Set<Contract> getContract() {
		return contract;
	}

	public void setContract(Set<Contract> contract) {
		this.contract = contract;
	}
}
