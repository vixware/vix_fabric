package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
/**
 * @ClassName: ContractSubject
 * @Description: 合同标的 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-8-5 下午3:38:40
 */
public class ContractSubject extends BaseEntity {

	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1482456870778679154L;
	/**
	 * 标的编码
	 */
	private String subjectCode;
	/**
	 * 标的名称
	 */
	private String subjectName;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 存货分类编码
	 */
	private String stockClassificationCode;
	/**
	 * 对应存货编码
	 */
	private String correspondingInventoryCode;
	/**
	 * 项目大类
	 */
	private String projectCatalog;
	/**
	 * 存货规格型号
	 */
	private String inventoriesSpecification;
	/**
	 * 无税原币单价
	 */
	private Double  nnTOCurrencyPrice;
	/**
	 * 含税原币单价
	 */
	private Double ttIOriginalCurrencyPrice;
	/**
	 * 无税原币金额
	 */
	private Double nnTaxAmountOriginalCurrency;
	/**
	 * 含税原币金额
	 */
	private Double ttATOriginalCurrency;
	/**
	 * 执行数量
	 */
	private String executionQuantity;
	/**
	 * 执行无税金额原币
	 */
	private Double eeTAOriginalCurrency;
	/**
	 * 执行含税金额原币
	 */
	private Double eeTAIncLriginalCurrency;
	
	/** 合同 */
	private Contract contract;
	
	/** 合同查询 */
	private ContractInquiry contractInquiry;

	public ContractSubject() {
		super();
	}

	public ContractSubject(String id) {
		super();
		setId(id);
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStockClassificationCode() {
		return stockClassificationCode;
	}

	public void setStockClassificationCode(String stockClassificationCode) {
		this.stockClassificationCode = stockClassificationCode;
	}

	public String getCorrespondingInventoryCode() {
		return correspondingInventoryCode;
	}

	public void setCorrespondingInventoryCode(String correspondingInventoryCode) {
		this.correspondingInventoryCode = correspondingInventoryCode;
	}

	public String getProjectCatalog() {
		return projectCatalog;
	}

	public void setProjectCatalog(String projectCatalog) {
		this.projectCatalog = projectCatalog;
	}

	public String getInventoriesSpecification() {
		return inventoriesSpecification;
	}

	public void setInventoriesSpecification(String inventoriesSpecification) {
		this.inventoriesSpecification = inventoriesSpecification;
	}
	
	public Double getNnTOCurrencyPrice() {
		return nnTOCurrencyPrice;
	}

	public void setNnTOCurrencyPrice(Double nnTOCurrencyPrice) {
		this.nnTOCurrencyPrice = nnTOCurrencyPrice;
	}

	public Double getTtIOriginalCurrencyPrice() {
		return ttIOriginalCurrencyPrice;
	}

	public void setTtIOriginalCurrencyPrice(Double ttIOriginalCurrencyPrice) {
		this.ttIOriginalCurrencyPrice = ttIOriginalCurrencyPrice;
	}

	public Double getNnTaxAmountOriginalCurrency() {
		return nnTaxAmountOriginalCurrency;
	}

	public void setNnTaxAmountOriginalCurrency(Double nnTaxAmountOriginalCurrency) {
		this.nnTaxAmountOriginalCurrency = nnTaxAmountOriginalCurrency;
	}

	public Double getTtATOriginalCurrency() {
		return ttATOriginalCurrency;
	}

	public void setTtATOriginalCurrency(Double ttATOriginalCurrency) {
		this.ttATOriginalCurrency = ttATOriginalCurrency;
	}

	public String getExecutionQuantity() {
		return executionQuantity;
	}

	public void setExecutionQuantity(String executionQuantity) {
		this.executionQuantity = executionQuantity;
	}

	public Double getEeTAOriginalCurrency() {
		return eeTAOriginalCurrency;
	}

	public void setEeTAOriginalCurrency(Double eeTAOriginalCurrency) {
		this.eeTAOriginalCurrency = eeTAOriginalCurrency;
	}

	public Double getEeTAIncLriginalCurrency() {
		return eeTAIncLriginalCurrency;
	}

	public void setEeTAIncLriginalCurrency(Double eeTAIncLriginalCurrency) {
		this.eeTAIncLriginalCurrency = eeTAIncLriginalCurrency;
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
