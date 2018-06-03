package com.vix.inventory.reverseflushingmaterial.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 盘点单主表
 */

public class ReverseFlushingMaterial extends BaseBOEntity {
	private static final long serialVersionUID = -9025964006742073141L;
	/**
	 * 领料类型
	 */
	private String issueType;
	/**
	 * 领料用途
	 */
	private String purpose;
	/**
	 * 原单类型
	 */
	private String theOriginalSingleType;
	/**
	 * 选单号
	 */
	private String menuCode;
	/**
	 * 领料人
	 */
	private String pickingPeople;
	/**
	 * 发料人
	 */
	private String sendingPeople;
	/**
	 * 仓库编码
	 */
	private String warehousecode;
	/**
	 * 库管员
	 */
	private String warehousePerson;
	/**
	 * 审核日期
	 */
	private Date checkdate;
	/**
	 * 领料单明细
	 */
	private Set<ReverseFlushingMaterialItem> reverseFlushingMaterialItem = new HashSet<ReverseFlushingMaterialItem>();

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTheOriginalSingleType() {
		return theOriginalSingleType;
	}

	public void setTheOriginalSingleType(String theOriginalSingleType) {
		this.theOriginalSingleType = theOriginalSingleType;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getPickingPeople() {
		return pickingPeople;
	}

	public void setPickingPeople(String pickingPeople) {
		this.pickingPeople = pickingPeople;
	}

	public String getSendingPeople() {
		return sendingPeople;
	}

	public void setSendingPeople(String sendingPeople) {
		this.sendingPeople = sendingPeople;
	}

	public String getWarehousecode() {
		return warehousecode;
	}

	public void setWarehousecode(String warehousecode) {
		this.warehousecode = warehousecode;
	}

	public String getWarehousePerson() {
		return warehousePerson;
	}

	public void setWarehousePerson(String warehousePerson) {
		this.warehousePerson = warehousePerson;
	}

	public Date getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public Set<ReverseFlushingMaterialItem> getReverseFlushingMaterialItem() {
		return reverseFlushingMaterialItem;
	}

	public void setReverseFlushingMaterialItem(Set<ReverseFlushingMaterialItem> reverseFlushingMaterialItem) {
		this.reverseFlushingMaterialItem = reverseFlushingMaterialItem;
	}

}