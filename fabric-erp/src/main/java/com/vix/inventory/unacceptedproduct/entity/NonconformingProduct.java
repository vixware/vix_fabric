package com.vix.inventory.unacceptedproduct.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 不合格品记录单
 */

public class NonconformingProduct extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 单据类型编码
	 */
	private String bizformtype;
	/**
	 * 业务类型
	 */
	private String biztype;
	/**
	 * 单据来源
	 */
	private String bizsource;
	/**
	 * 对应业务单号
	 */
	private String bizcode;
	/**
	 * 仓库编码
	 */
	private String warehousecode;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 库管员
	 */
	private String warehousePerson;
	/**
	 * 收发单据号
	 */
	private String formcode;
	/**
	 * 业务员编码
	 */
	private String personcode;
	/**
	 * 采购类型编码
	 */
	private String purchasetypecode;
	/**
	 * 销售类型编码
	 */
	private String saletypecode;
	/**
	 * 客户编码
	 */
	private String customercode;
	/**
	 * 供应商编码
	 */
	private String sppliercode;
	/**
	 * 采购订单号
	 */
	private String pocode;
	/**
	 * 采购到货单号
	 */
	private String purchasearrivalcode;
	/**
	 * 检验员
	 */
	private String checkperson;
	/**
	 * 父项产品编码
	 */
	private String parentitemcode;
	/**
	 * 生产订单编号
	 */
	private String manufactureordercode;
	/**
	 * 发票号
	 */
	private String billcode;
	/**
	 * 发货地址
	 */
	private String shipaddress;
	private String purchasebillcode;
	private String purchasereturnformcode;
	private String producebatch;
	private String checker;
	private String istransflag;
	private String accounter;
	private String maker;
	private Boolean netlock;
	private String keepdate;
	private Date checkdate;
	private String bpufirst;
	private String biafirst;
	private Integer producequantity;
	private Date arrivaldate;
	private String checkformcode;
	private Date checkingdate;
	private String templatecode;
	private String bisstqc;
	private String gspcheck;
	private String purchaseordercode;
	private String pruchasearrivalcode;
	private String mocode;
	private String returnformcode;
	private Date ufts;
	private Long taxrate;
	private Integer exchangerate;
	private String echangecurrencyname;
	private Integer bomfirst;
	private String companyCode;
	private InvWarehouse invWarehouse;
	private Set<NonconformingProductDetails> nonconformingProductDetailss = new HashSet<NonconformingProductDetails>();

	public String getBizformtype() {
		return bizformtype;
	}

	public void setBizformtype(String bizformtype) {
		this.bizformtype = bizformtype;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public String getBizsource() {
		return bizsource;
	}

	public void setBizsource(String bizsource) {
		this.bizsource = bizsource;
	}

	public String getBizcode() {
		return bizcode;
	}

	public void setBizcode(String bizcode) {
		this.bizcode = bizcode;
	}

	public String getWarehousecode() {
		return warehousecode;
	}

	public void setWarehousecode(String warehousecode) {
		this.warehousecode = warehousecode;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehousePerson() {
		return warehousePerson;
	}

	public void setWarehousePerson(String warehousePerson) {
		this.warehousePerson = warehousePerson;
	}

	public String getFormcode() {
		return formcode;
	}

	public void setFormcode(String formcode) {
		this.formcode = formcode;
	}

	public String getPersoncode() {
		return personcode;
	}

	public void setPersoncode(String personcode) {
		this.personcode = personcode;
	}

	public String getPurchasetypecode() {
		return purchasetypecode;
	}

	public void setPurchasetypecode(String purchasetypecode) {
		this.purchasetypecode = purchasetypecode;
	}

	public String getSaletypecode() {
		return saletypecode;
	}

	public void setSaletypecode(String saletypecode) {
		this.saletypecode = saletypecode;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getSppliercode() {
		return sppliercode;
	}

	public void setSppliercode(String sppliercode) {
		this.sppliercode = sppliercode;
	}

	public String getPocode() {
		return pocode;
	}

	public void setPocode(String pocode) {
		this.pocode = pocode;
	}

	public String getPurchasearrivalcode() {
		return purchasearrivalcode;
	}

	public void setPurchasearrivalcode(String purchasearrivalcode) {
		this.purchasearrivalcode = purchasearrivalcode;
	}

	public String getCheckperson() {
		return checkperson;
	}

	public void setCheckperson(String checkperson) {
		this.checkperson = checkperson;
	}

	public String getParentitemcode() {
		return parentitemcode;
	}

	public void setParentitemcode(String parentitemcode) {
		this.parentitemcode = parentitemcode;
	}

	public String getManufactureordercode() {
		return manufactureordercode;
	}

	public void setManufactureordercode(String manufactureordercode) {
		this.manufactureordercode = manufactureordercode;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getShipaddress() {
		return shipaddress;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	public String getPurchasebillcode() {
		return purchasebillcode;
	}

	public void setPurchasebillcode(String purchasebillcode) {
		this.purchasebillcode = purchasebillcode;
	}

	public String getPurchasereturnformcode() {
		return purchasereturnformcode;
	}

	public void setPurchasereturnformcode(String purchasereturnformcode) {
		this.purchasereturnformcode = purchasereturnformcode;
	}

	public String getProducebatch() {
		return producebatch;
	}

	public void setProducebatch(String producebatch) {
		this.producebatch = producebatch;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getIstransflag() {
		return istransflag;
	}

	public void setIstransflag(String istransflag) {
		this.istransflag = istransflag;
	}

	public String getAccounter() {
		return accounter;
	}

	public void setAccounter(String accounter) {
		this.accounter = accounter;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Boolean getNetlock() {
		return netlock;
	}

	public void setNetlock(Boolean netlock) {
		this.netlock = netlock;
	}

	public String getKeepdate() {
		return keepdate;
	}

	public void setKeepdate(String keepdate) {
		this.keepdate = keepdate;
	}

	public Date getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public String getBpufirst() {
		return bpufirst;
	}

	public void setBpufirst(String bpufirst) {
		this.bpufirst = bpufirst;
	}

	public String getBiafirst() {
		return biafirst;
	}

	public void setBiafirst(String biafirst) {
		this.biafirst = biafirst;
	}

	public Integer getProducequantity() {
		return producequantity;
	}

	public void setProducequantity(Integer producequantity) {
		this.producequantity = producequantity;
	}

	public Date getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(Date arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public String getCheckformcode() {
		return checkformcode;
	}

	public void setCheckformcode(String checkformcode) {
		this.checkformcode = checkformcode;
	}

	public Date getCheckingdate() {
		return checkingdate;
	}

	public void setCheckingdate(Date checkingdate) {
		this.checkingdate = checkingdate;
	}

	public String getTemplatecode() {
		return templatecode;
	}

	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}

	public String getBisstqc() {
		return bisstqc;
	}

	public void setBisstqc(String bisstqc) {
		this.bisstqc = bisstqc;
	}

	public String getGspcheck() {
		return gspcheck;
	}

	public void setGspcheck(String gspcheck) {
		this.gspcheck = gspcheck;
	}

	public String getPurchaseordercode() {
		return purchaseordercode;
	}

	public void setPurchaseordercode(String purchaseordercode) {
		this.purchaseordercode = purchaseordercode;
	}

	public String getPruchasearrivalcode() {
		return pruchasearrivalcode;
	}

	public void setPruchasearrivalcode(String pruchasearrivalcode) {
		this.pruchasearrivalcode = pruchasearrivalcode;
	}

	public String getMocode() {
		return mocode;
	}

	public void setMocode(String mocode) {
		this.mocode = mocode;
	}

	public String getReturnformcode() {
		return returnformcode;
	}

	public void setReturnformcode(String returnformcode) {
		this.returnformcode = returnformcode;
	}

	public Date getUfts() {
		return ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public Long getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Long taxrate) {
		this.taxrate = taxrate;
	}

	public Integer getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(Integer exchangerate) {
		this.exchangerate = exchangerate;
	}

	public String getEchangecurrencyname() {
		return echangecurrencyname;
	}

	public void setEchangecurrencyname(String echangecurrencyname) {
		this.echangecurrencyname = echangecurrencyname;
	}

	public Integer getBomfirst() {
		return bomfirst;
	}

	public void setBomfirst(Integer bomfirst) {
		this.bomfirst = bomfirst;
	}

	@Override
	public String getCompanyCode() {
		return companyCode;
	}

	@Override
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Set<NonconformingProductDetails> getNonconformingProductDetailss() {
		return nonconformingProductDetailss;
	}

	public void setNonconformingProductDetailss(Set<NonconformingProductDetails> nonconformingProductDetailss) {
		this.nonconformingProductDetailss = nonconformingProductDetailss;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

}