package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.contractInquiry.entity.ContractInquiry;

/**
 * @ClassName: PriceConditions
 * @Description:价格条件
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-8 下午2:09:35
 */
public class PriceConditions extends BaseEntity {

	private static final long serialVersionUID = 5818388791584655491L;

	/**
	 * 开始数量
	 **/
	private String startQuantity;
	/**
	 * 截止数量
	 **/
	private String cutoffQuantity;
	/**
	 * 折扣
	 **/
	private String discount;
	/**
	 * 数量（从）
	 * */
	private String numberFrom;
	/**
	 * 数量（到）
	 **/
	private String numberto;
	/**
	 * 采购价格条件
	 **/
	private String purchasepriceCondition;
	/**
	 * 销售价格条件
	 **/
	private String sellpriceCondition;
	
	/** 合同 */
	private Contract contract;
	
	/** 合同查询 */
	private ContractInquiry contractInquiry;

	public String getStartQuantity() {
		return startQuantity;
	}

	public void setStartQuantity(String startQuantity) {
		this.startQuantity = startQuantity;
	}

	public String getCutoffQuantity() {
		return cutoffQuantity;
	}

	public void setCutoffQuantity(String cutoffQuantity) {
		this.cutoffQuantity = cutoffQuantity;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getNumberFrom() {
		return numberFrom;
	}

	public void setNumberFrom(String numberFrom) {
		this.numberFrom = numberFrom;
	}


	public String getPurchasepriceCondition() {
		return purchasepriceCondition;
	}

	public String getNumberto() {
		return numberto;
	}

	public void setNumberto(String numberto) {
		this.numberto = numberto;
	}

	public void setPurchasepriceCondition(String purchasepriceCondition) {
		this.purchasepriceCondition = purchasepriceCondition;
	}

	public String getSellpriceCondition() {
		return sellpriceCondition;
	}

	public void setSellpriceCondition(String sellpriceCondition) {
		this.sellpriceCondition = sellpriceCondition;
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
