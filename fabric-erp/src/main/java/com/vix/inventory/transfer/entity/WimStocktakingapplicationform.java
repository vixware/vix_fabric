package com.vix.inventory.transfer.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * WimStocktakingapplicationform entity. @author MyEclipse Persistence Tools
 */

public class WimStocktakingapplicationform extends BaseEntity {

	private static final long serialVersionUID = 2169755518865609762L;
	private String outwarehousecode;
	private String outwarehouse;
	private String inwarehousecode;
	private String inwarehouse;
	private String outdepartmentCode;
	private String indepartmentCode;
	private String personcode;
	private String instockcatalogcode;
	private String outstockcatalogcode;
	private String maker;
	private String templatecode;
	private String checkperson;
	private Date checkdate;
	private Date ufts;
	private String closer;

	// Constructors

	/** default constructor */
	public WimStocktakingapplicationform() {
	}

	// Property accessors

	public String getOutwarehousecode() {
		return this.outwarehousecode;
	}

	public void setOutwarehousecode(String outwarehousecode) {
		this.outwarehousecode = outwarehousecode;
	}

	public String getOutwarehouse() {
		return this.outwarehouse;
	}

	public void setOutwarehouse(String outwarehouse) {
		this.outwarehouse = outwarehouse;
	}

	public String getInwarehousecode() {
		return inwarehousecode;
	}

	public void setInwarehousecode(String inwarehousecode) {
		this.inwarehousecode = inwarehousecode;
	}

	public String getInwarehouse() {
		return this.inwarehouse;
	}

	public void setInwarehouse(String inwarehouse) {
		this.inwarehouse = inwarehouse;
	}

	public String getOutdepartmentCode() {
		return this.outdepartmentCode;
	}

	public void setOutdepartmentCode(String outdepartmentCode) {
		this.outdepartmentCode = outdepartmentCode;
	}

	public String getIndepartmentCode() {
		return this.indepartmentCode;
	}

	public void setIndepartmentCode(String indepartmentCode) {
		this.indepartmentCode = indepartmentCode;
	}

	public String getPersoncode() {
		return this.personcode;
	}

	public void setPersoncode(String personcode) {
		this.personcode = personcode;
	}

	public String getInstockcatalogcode() {
		return this.instockcatalogcode;
	}

	public void setInstockcatalogcode(String instockcatalogcode) {
		this.instockcatalogcode = instockcatalogcode;
	}

	public String getOutstockcatalogcode() {
		return this.outstockcatalogcode;
	}

	public void setOutstockcatalogcode(String outstockcatalogcode) {
		this.outstockcatalogcode = outstockcatalogcode;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getTemplatecode() {
		return this.templatecode;
	}

	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}

	public String getCheckperson() {
		return this.checkperson;
	}

	public void setCheckperson(String checkperson) {
		this.checkperson = checkperson;
	}

	public Date getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public Date getUfts() {
		return this.ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public String getCloser() {
		return this.closer;
	}

	public void setCloser(String closer) {
		this.closer = closer;
	}

}