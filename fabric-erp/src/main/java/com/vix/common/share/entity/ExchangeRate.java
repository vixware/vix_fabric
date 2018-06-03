package com.vix.common.share.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.contract.mamanger.entity.Contract;


/**
 * 汇率
 * @author Administrator
 *
 */
public class ExchangeRate extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 本币 */
	private CurrencyType currentCurrencyType;
	/** 汇率 */
	private Double exchangeRate;
	/** 外币 */
	private CurrencyType foreignCurrencyType;
	
	/** 合同*/
	private Set<Contract> contract = new HashSet<Contract>();
	
	/** 合同查询*/
	private Set<ContractInquiry> contractInquiry = new HashSet<ContractInquiry>();

	public ExchangeRate(){}

	public CurrencyType getCurrentCurrencyType() {
		return currentCurrencyType;
	}

	public void setCurrentCurrencyType(CurrencyType currentCurrencyType) {
		this.currentCurrencyType = currentCurrencyType;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public CurrencyType getForeignCurrencyType() {
		return foreignCurrencyType;
	}

	public void setForeignCurrencyType(CurrencyType foreignCurrencyType) {
		this.foreignCurrencyType = foreignCurrencyType;
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
