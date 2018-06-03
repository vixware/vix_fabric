package com.vix.inventory.limitstake.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 限额领料单
 * 
 * @author zhanghaibing
 * @date 2013-5-21
 */
public class StockLimitedTaking extends BaseEntity {
	private static final long serialVersionUID = -230955655611963786L;
	/**
	 * 发料仓库
	 */
	private String warehousecode;
	/**
	 * 用途
	 */
	private String purpose;

	/**
	 * 领料周期
	 */
	private Date acquisitionCycle;
	/**
	 * 供应商
	 */
	private String supplier;
	/**
	 * 领料部门负责人
	 */
	private String departmentManager;
	/**
	 * 库管员
	 */
	private String warehousePerson;
	/**
	 * 领料人
	 */
	private String pickingPeople;
	/**
	 * 发料人
	 */
	private String sendingPeople;
	
	private Double totalAmount;

	private Set<StockLimitedTakingItem> stockLimitedTakingItem = new HashSet<StockLimitedTakingItem>();

	public String getWarehousecode() {
		return warehousecode;
	}

	public void setWarehousecode(String warehousecode) {
		this.warehousecode = warehousecode;
	}

	public Date getAcquisitionCycle() {
		return acquisitionCycle;
	}

	public void setAcquisitionCycle(Date acquisitionCycle) {
		this.acquisitionCycle = acquisitionCycle;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getDepartmentManager() {
		return departmentManager;
	}

	public void setDepartmentManager(String departmentManager) {
		this.departmentManager = departmentManager;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getWarehousePerson() {
		return warehousePerson;
	}

	public void setWarehousePerson(String warehousePerson) {
		this.warehousePerson = warehousePerson;
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

	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Set<StockLimitedTakingItem> getStockLimitedTakingItem() {
		return stockLimitedTakingItem;
	}

	public void setStockLimitedTakingItem(Set<StockLimitedTakingItem> stockLimitedTakingItem) {
		this.stockLimitedTakingItem = stockLimitedTakingItem;
	}
}