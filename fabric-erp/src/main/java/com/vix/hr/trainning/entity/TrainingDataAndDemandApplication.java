package com.vix.hr.trainning.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @Description:培训需求-培训资料
 * @author bobchen
 * @date 2015-9-2 上午10:55:59
 */
public class TrainingDataAndDemandApplication extends BaseEntity {

	private static final long serialVersionUID = 8521532633760554701L;
	/** 资料编码 */
	private String datumCode;
	/** 资料类别 */
	private String datumType;
	/** 资料名称 */
	private String datumName;
	/** 资料简介 */
	private String profile;
	/** 资料作者 */
	private String datumauthor;
	/** 资料费用 */
	private String datumCost;
	/** 出版单位 */
	private String publishingUnit;
	/** 存放位置 */
	private String storageLocation;
	/** 备注 */
	private String remarksss;
	/** 培训需求 */
	private DemandApply demandApply;

	public String getDatumCode() {
		return datumCode;
	}

	public void setDatumCode(String datumCode) {
		this.datumCode = datumCode;
	}

	public String getDatumType() {
		return datumType;
	}

	public void setDatumType(String datumType) {
		this.datumType = datumType;
	}

	public String getDatumName() {
		return datumName;
	}

	public void setDatumName(String datumName) {
		this.datumName = datumName;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getDatumauthor() {
		return datumauthor;
	}

	public void setDatumauthor(String datumauthor) {
		this.datumauthor = datumauthor;
	}

	public String getDatumCost() {
		return datumCost;
	}

	public void setDatumCost(String datumCost) {
		this.datumCost = datumCost;
	}

	public String getPublishingUnit() {
		return publishingUnit;
	}

	public void setPublishingUnit(String publishingUnit) {
		this.publishingUnit = publishingUnit;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getRemarksss() {
		return remarksss;
	}

	public void setRemarksss(String remarksss) {
		this.remarksss = remarksss;
	}

	public DemandApply getDemandApply() {
		return demandApply;
	}

	public void setDemandApply(DemandApply demandApply) {
		this.demandApply = demandApply;
	}

}
