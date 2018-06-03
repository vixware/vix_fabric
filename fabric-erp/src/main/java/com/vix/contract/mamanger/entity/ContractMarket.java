package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
/**
 * 
 * @ClassName: ContractMarket
 * @Description: 销售定价条件 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-1-21 下午3:44:52
 */
public class ContractMarket extends BaseEntity {

	private static final long serialVersionUID = 1482456870778679154L;
	/**编号*/
	private String number;
	/**供应商*/
	private String supplier;
	/**物料编码*/
	private String materialCode;
	/**采购组织*/
	private String purchasingOrganization;
	/**描述*/
	private String descriptions;
	/**采购数量起*/
	private String purchasequantityRise;
	/**采购数量止*/
	private String purchasequantityTo;
	/**定价类型*/
	private String pricingType ;
	/**定价等级*/
	private String pricingGrade;
	/**计算类型*/
	private String calculationType ;
	/**定价类别*/
	private String pricingCategory;
	/**价格（折扣）*/
	private Double price;
	/**有效时间*/
	private Date effectiveTime;
	
	
	/** 合同 */
	private Contract contract;
	
	/** 合同查询 */
	private ContractInquiry contractInquiry;
	
	public ContractMarket() {
		super();
	}

	public ContractMarket(String id) {
		super();
		setId(id);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getPurchasingOrganization() {
		return purchasingOrganization;
	}

	public void setPurchasingOrganization(String purchasingOrganization) {
		this.purchasingOrganization = purchasingOrganization;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getPurchasequantityRise() {
		return purchasequantityRise;
	}

	public void setPurchasequantityRise(String purchasequantityRise) {
		this.purchasequantityRise = purchasequantityRise;
	}

	public String getPurchasequantityTo() {
		return purchasequantityTo;
	}

	public void setPurchasequantityTo(String purchasequantityTo) {
		this.purchasequantityTo = purchasequantityTo;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
	}

	public String getPricingGrade() {
		return pricingGrade;
	}

	public void setPricingGrade(String pricingGrade) {
		this.pricingGrade = pricingGrade;
	}

	public String getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

	public String getPricingCategory() {
		return pricingCategory;
	}

	public void setPricingCategory(String pricingCategory) {
		this.pricingCategory = pricingCategory;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
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
