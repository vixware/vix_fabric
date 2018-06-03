package com.vix.inventory.transfer.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * WimStocktakingapplicationitem entity. @author MyEclipse Persistence Tools
 */

public class WimStocktakingapplicationitem extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2314488784931051604L;
	// Fields

	private String stvcode;
	private String itemcode;
	private String itemname;
	private String itemtype;
	private String specification;
	private Float itemnumber;
	private Float stvquantity;
	private Float stvchknum;
	private Float stvchkquantity;
	private Float istvsumnum;
	private Float stvsumquantity;
	private Float stvpcost;
	private Float stvplanprice;
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

	// Constructors

	/** default constructor */
	public WimStocktakingapplicationitem() {
	}

	public String getStvcode() {
		return this.stvcode;
	}

	public void setStvcode(String stvcode) {
		this.stvcode = stvcode;
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

	public Float getItemnumber() {
		return this.itemnumber;
	}

	public void setItemnumber(Float itemnumber) {
		this.itemnumber = itemnumber;
	}

	public Float getStvquantity() {
		return this.stvquantity;
	}

	public void setStvquantity(Float stvquantity) {
		this.stvquantity = stvquantity;
	}

	public Float getStvchknum() {
		return this.stvchknum;
	}

	public void setStvchknum(Float stvchknum) {
		this.stvchknum = stvchknum;
	}

	public Float getStvchkquantity() {
		return this.stvchkquantity;
	}

	public void setStvchkquantity(Float stvchkquantity) {
		this.stvchkquantity = stvchkquantity;
	}

	public Float getIstvsumnum() {
		return this.istvsumnum;
	}

	public void setIstvsumnum(Float istvsumnum) {
		this.istvsumnum = istvsumnum;
	}

	public Float getStvsumquantity() {
		return this.stvsumquantity;
	}

	public void setStvsumquantity(Float stvsumquantity) {
		this.stvsumquantity = stvsumquantity;
	}

	public Float getStvpcost() {
		return this.stvpcost;
	}

	public void setStvpcost(Float stvpcost) {
		this.stvpcost = stvpcost;
	}

	public Float getStvplanprice() {
		return this.stvplanprice;
	}

	public void setStvplanprice(Float stvplanprice) {
		this.stvplanprice = stvplanprice;
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

}