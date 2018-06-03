package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.mdm.item.entity.Item;
/**
 * @ClassName: ContractPricingConditions
 * @Description: 合同采购定价条件
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-12-28
 */
public class ContractPricingConditions extends BaseEntity {

	private static final long serialVersionUID = 1482456870778679154L;
	/** 物料名称 */
	private String itemName;
	
	/** 供应商Id*/
	private String supplierId;
	
	/** 采购组织 */
	private String purchasingOrganization;
	
	/** 价格 */
	private Double price;
	
	/** 折扣 */
	private Double discount;
	
	/** 订货量(从) */
	private Double from;
	
	/** 订货量(到) */
	private Double to;
	
	/** 价格区间-开始 */
	private Double startPrice;
	
	/** 价格区间-截止 */
	private Double endPrice;
	
	/** 采购数量起 */
	private Double minCount;
	
	/** 采购数量止 */
	private Double maxCount;
	
	/** 币种 */
	private String currency;
	
	/** 价格类型 */
	private String priceType;
		
	/** 合同 */
	private Contract contract;
	
	/** 合同 */
	private ContractInquiry contractInquiry;
	
	/** 物料 */
	private Item item;

	
	public ContractPricingConditions() {
		super();
	}

	public ContractPricingConditions(String id) {
		super();
		setId(id);
	}
	
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPurchasingOrganization() {
		return purchasingOrganization;
	}

	public void setPurchasingOrganization(String purchasingOrganization) {
		this.purchasingOrganization = purchasingOrganization;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getFrom() {
		return from;
	}

	public void setFrom(Double from) {
		this.from = from;
	}

	public Double getTo() {
		return to;
	}

	public void setTo(Double to) {
		this.to = to;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Double getMinCount() {
		return minCount;
	}

	public void setMinCount(Double minCount) {
		this.minCount = minCount;
	}

	public Double getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Double maxCount) {
		this.maxCount = maxCount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ContractInquiry getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(ContractInquiry contractInquiry) {
		this.contractInquiry = contractInquiry;
	}

}
