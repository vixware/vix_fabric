package com.vix.inventory.inbound.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * StockTransferItem entity. @author MyEclipse Persistence Tools
 */

public class StockTransferItem extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3556986411621084993L;
	private String stcode;
	private String itemcode;
	private String itemname;
	private String itemtype;
	private String specification;
	private Integer rdsid;
	private Integer stnumber;
	private Integer stquantity;
	private Integer stactualcost;
	private Integer stactualprice;
	private Integer stplancost;
	private Integer stplanprice;
	private String stvbatch;
	private Date stvdisabledate;
	private String porjectcode;
	private String projectclass;
	private Float unitcost;
	private Float price;
	private Float salecost;
	private Float saleprice;
	private Integer massdate;
	private Date appdate;
	private String barcode;
	private String assitunit;
	private String vendercode;
	private String inwarehousevouchcode;
	private Date producedate;
	private String massunit;
	private String closer;
	private Integer ufts;
	private String primaryKey;
	private Date createTime;
	private Date updateTime;
	private String companyCode;
	private Date startTime;
	private Date endTime;

	// Constructors

	/** default constructor */
	public StockTransferItem() {
	}

	public String getStcode() {
		return this.stcode;
	}

	public void setStcode(String stcode) {
		this.stcode = stcode;
	}

	public String getItemcode() {
		return this.itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemtype() {
		return this.itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Integer getRdsid() {
		return this.rdsid;
	}

	public void setRdsid(Integer rdsid) {
		this.rdsid = rdsid;
	}

	public Integer getStnumber() {
		return this.stnumber;
	}

	public void setStnumber(Integer stnumber) {
		this.stnumber = stnumber;
	}

	public Integer getStquantity() {
		return this.stquantity;
	}

	public void setStquantity(Integer stquantity) {
		this.stquantity = stquantity;
	}

	public Integer getStactualcost() {
		return this.stactualcost;
	}

	public void setStactualcost(Integer stactualcost) {
		this.stactualcost = stactualcost;
	}

	public Integer getStactualprice() {
		return this.stactualprice;
	}

	public void setStactualprice(Integer stactualprice) {
		this.stactualprice = stactualprice;
	}

	public Integer getStplancost() {
		return this.stplancost;
	}

	public void setStplancost(Integer stplancost) {
		this.stplancost = stplancost;
	}

	public Integer getStplanprice() {
		return this.stplanprice;
	}

	public void setStplanprice(Integer stplanprice) {
		this.stplanprice = stplanprice;
	}

	public String getStvbatch() {
		return this.stvbatch;
	}

	public void setStvbatch(String stvbatch) {
		this.stvbatch = stvbatch;
	}

	public Date getStvdisabledate() {
		return this.stvdisabledate;
	}

	public void setStvdisabledate(Date stvdisabledate) {
		this.stvdisabledate = stvdisabledate;
	}

	public String getPorjectcode() {
		return this.porjectcode;
	}

	public void setPorjectcode(String porjectcode) {
		this.porjectcode = porjectcode;
	}

	public String getProjectclass() {
		return this.projectclass;
	}

	public void setProjectclass(String projectclass) {
		this.projectclass = projectclass;
	}

	public Float getUnitcost() {
		return this.unitcost;
	}

	public void setUnitcost(Float unitcost) {
		this.unitcost = unitcost;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getSalecost() {
		return this.salecost;
	}

	public void setSalecost(Float salecost) {
		this.salecost = salecost;
	}

	public Float getSaleprice() {
		return this.saleprice;
	}

	public void setSaleprice(Float saleprice) {
		this.saleprice = saleprice;
	}

	public Integer getMassdate() {
		return this.massdate;
	}

	public void setMassdate(Integer massdate) {
		this.massdate = massdate;
	}

	public Date getAppdate() {
		return this.appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getAssitunit() {
		return this.assitunit;
	}

	public void setAssitunit(String assitunit) {
		this.assitunit = assitunit;
	}

	public String getVendercode() {
		return this.vendercode;
	}

	public void setVendercode(String vendercode) {
		this.vendercode = vendercode;
	}

	public String getInwarehousevouchcode() {
		return this.inwarehousevouchcode;
	}

	public void setInwarehousevouchcode(String inwarehousevouchcode) {
		this.inwarehousevouchcode = inwarehousevouchcode;
	}

	public Date getProducedate() {
		return this.producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}

	public String getMassunit() {
		return this.massunit;
	}

	public void setMassunit(String massunit) {
		this.massunit = massunit;
	}

	public String getCloser() {
		return this.closer;
	}

	public void setCloser(String closer) {
		this.closer = closer;
	}

	public Integer getUfts() {
		return this.ufts;
	}

	public void setUfts(Integer ufts) {
		this.ufts = ufts;
	}

	@Override
	public String getPrimaryKey() {
		return this.primaryKey;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Date getCREATETIME() {
		return this.createTime;
	}

	public void setCREATETIME(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUPDATETIME() {
		return this.updateTime;
	}

	public void setUPDATETIME(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String getCompanyCode() {
		return this.companyCode;
	}

	@Override
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Date getStartDate() {
		return this.startTime;
	}

	public void setStartDate(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return this.endTime;
	}

	public void setEndDate(Date endTime) {
		this.endTime = endTime;
	}

}