package com.vix.inventory.inbound.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * StockTransfer entity. @author MyEclipse Persistence Tools
 */

public class StockTransfer extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3738651954477686343L;
	private String stcode;
	private Date stdate;
	private String outwarehousecode;
	private String inwarehousecode;
	private String outdepcode;
	private String indepcode;
	private String personcode;
	private String inrdcode;
	private String outrdcode;
	private String accounter;
	private String maker;
	private Boolean inetlock;
	private Integer templatecode;
	private String verifyperson;
	private Date verifydate;
	private String parenetproductcode;
	private String mpocode;
	private Float quantity;
	private Short transflag;
	private Date ufts;
	private Integer iproorderid;
	private String cordertype;
	private String ctranrequestcode;
	private String versions;
	private Integer bomid;
	private String capptvcode;
	private String primaryKey;
	private Date createTime;
	private Date updateTime;
	private String companyCode;
	private Date startTime;
	private Date endTime;

	// Constructors

	/** default constructor */
	public StockTransfer() {
	}

	public String getStcode() {
		return this.stcode;
	}

	public void setStcode(String stcode) {
		this.stcode = stcode;
	}

	public Date getStdate() {
		return this.stdate;
	}

	public void setStdate(Date stdate) {
		this.stdate = stdate;
	}

	public String getOutwarehousecode() {
		return this.outwarehousecode;
	}

	public void setOutwarehousecode(String outwarehousecode) {
		this.outwarehousecode = outwarehousecode;
	}

	public String getInwarehousecode() {
		return this.inwarehousecode;
	}

	public void setInwarehousecode(String inwarehousecode) {
		this.inwarehousecode = inwarehousecode;
	}

	public String getOutdepcode() {
		return this.outdepcode;
	}

	public void setOutdepcode(String outdepcode) {
		this.outdepcode = outdepcode;
	}

	public String getIndepcode() {
		return this.indepcode;
	}

	public void setIndepcode(String indepcode) {
		this.indepcode = indepcode;
	}

	public String getPersoncode() {
		return this.personcode;
	}

	public void setPersoncode(String personcode) {
		this.personcode = personcode;
	}

	public String getInrdcode() {
		return this.inrdcode;
	}

	public void setInrdcode(String inrdcode) {
		this.inrdcode = inrdcode;
	}

	public String getOutrdcode() {
		return this.outrdcode;
	}

	public void setOutrdcode(String outrdcode) {
		this.outrdcode = outrdcode;
	}

	public String getAccounter() {
		return this.accounter;
	}

	public void setAccounter(String accounter) {
		this.accounter = accounter;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Boolean getInetlock() {
		return this.inetlock;
	}

	public void setInetlock(Boolean inetlock) {
		this.inetlock = inetlock;
	}

	public Integer getTemplatecode() {
		return this.templatecode;
	}

	public void setTemplatecode(Integer templatecode) {
		this.templatecode = templatecode;
	}

	public String getVerifyperson() {
		return this.verifyperson;
	}

	public void setVerifyperson(String verifyperson) {
		this.verifyperson = verifyperson;
	}

	public Date getVerifydate() {
		return this.verifydate;
	}

	public void setVerifydate(Date verifydate) {
		this.verifydate = verifydate;
	}

	public String getParenetproductcode() {
		return this.parenetproductcode;
	}

	public void setParenetproductcode(String parenetproductcode) {
		this.parenetproductcode = parenetproductcode;
	}

	public String getMpocode() {
		return this.mpocode;
	}

	public void setMpocode(String mpocode) {
		this.mpocode = mpocode;
	}

	public Float getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Short getTransflag() {
		return this.transflag;
	}

	public void setTransflag(Short transflag) {
		this.transflag = transflag;
	}

	public Date getUfts() {
		return this.ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public Integer getIproorderid() {
		return this.iproorderid;
	}

	public void setIproorderid(Integer iproorderid) {
		this.iproorderid = iproorderid;
	}

	public String getCordertype() {
		return this.cordertype;
	}

	public void setCordertype(String cordertype) {
		this.cordertype = cordertype;
	}

	public String getCtranrequestcode() {
		return this.ctranrequestcode;
	}

	public void setCtranrequestcode(String ctranrequestcode) {
		this.ctranrequestcode = ctranrequestcode;
	}

	public String getVersions() {
		return this.versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public Integer getBomid() {
		return this.bomid;
	}

	public void setBomid(Integer bomid) {
		this.bomid = bomid;
	}

	public String getCapptvcode() {
		return this.capptvcode;
	}

	public void setCapptvcode(String capptvcode) {
		this.capptvcode = capptvcode;
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