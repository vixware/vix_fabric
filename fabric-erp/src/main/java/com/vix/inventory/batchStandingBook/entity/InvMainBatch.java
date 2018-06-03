package com.vix.inventory.batchStandingBook.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

/**
 * 批次主文件
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-3
 */
public class InvMainBatch extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 库存组织
	 */
	private String invOrganization;
	/**
	 * 物料名称
	 */
	private String itemName;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/* 批次号 */
	private String batchCode;
	/* 批次说明 */
	private String batchState;
	/* 生产日期 */
	private Date produceDate;
	/* 保质期 */
	private Float qualityPeriod;
	/** 失效日期 */
	private Date expiryDate;
	/* 等级 */
	private Long level;
	/* 浓度 */
	private Double concentration;
	/**
	 * 自定义属性1
	 */
	private String customAttribute1;
	/**
	 * 自定义属性2
	 */
	private String customAttribute2;
	/**
	 * 自定义属性3
	 */
	private String customAttribute3;
	/**
	 * 自定义属性4
	 */
	private String customAttribute4;
	/**
	 * 自定义属性5
	 */
	private String customAttribute5;
	/**
	 * 自定义属性6
	 */
	private String customAttribute6;
	/**
	 * 自定义属性7
	 */
	private String customAttribute7;
	/**
	 * 自定义属性8
	 */
	private String customAttribute8;
	/**
	 * 自定义属性9
	 */
	private String customAttribute9;
	/**
	 * 自定义属性10
	 */
	private String customAttribute10;

	/**
	 * 入库单明细
	 */
	private Set<InventoryCurrentStock> subInventoryCurrentStocks = new HashSet<InventoryCurrentStock>();

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getBatchState() {
		return batchState;
	}

	public void setBatchState(String batchState) {
		this.batchState = batchState;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Float getQualityPeriod() {
		return qualityPeriod;
	}

	public void setQualityPeriod(Float qualityPeriod) {
		this.qualityPeriod = qualityPeriod;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Double getConcentration() {
		return concentration;
	}

	public void setConcentration(Double concentration) {
		this.concentration = concentration;
	}

	public String getCustomAttribute1() {
		return customAttribute1;
	}

	public void setCustomAttribute1(String customAttribute1) {
		this.customAttribute1 = customAttribute1;
	}

	public String getCustomAttribute2() {
		return customAttribute2;
	}

	public void setCustomAttribute2(String customAttribute2) {
		this.customAttribute2 = customAttribute2;
	}

	public String getCustomAttribute3() {
		return customAttribute3;
	}

	public void setCustomAttribute3(String customAttribute3) {
		this.customAttribute3 = customAttribute3;
	}

	public String getCustomAttribute4() {
		return customAttribute4;
	}

	public void setCustomAttribute4(String customAttribute4) {
		this.customAttribute4 = customAttribute4;
	}

	public String getCustomAttribute5() {
		return customAttribute5;
	}

	public void setCustomAttribute5(String customAttribute5) {
		this.customAttribute5 = customAttribute5;
	}

	public String getCustomAttribute6() {
		return customAttribute6;
	}

	public void setCustomAttribute6(String customAttribute6) {
		this.customAttribute6 = customAttribute6;
	}

	public String getCustomAttribute7() {
		return customAttribute7;
	}

	public void setCustomAttribute7(String customAttribute7) {
		this.customAttribute7 = customAttribute7;
	}

	public String getCustomAttribute8() {
		return customAttribute8;
	}

	public void setCustomAttribute8(String customAttribute8) {
		this.customAttribute8 = customAttribute8;
	}

	public String getCustomAttribute9() {
		return customAttribute9;
	}

	public void setCustomAttribute9(String customAttribute9) {
		this.customAttribute9 = customAttribute9;
	}

	public String getCustomAttribute10() {
		return customAttribute10;
	}

	public void setCustomAttribute10(String customAttribute10) {
		this.customAttribute10 = customAttribute10;
	}

	public String getInvOrganization() {
		return invOrganization;
	}

	public void setInvOrganization(String invOrganization) {
		this.invOrganization = invOrganization;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Set<InventoryCurrentStock> getSubInventoryCurrentStocks() {
		return subInventoryCurrentStocks;
	}

	public void setSubInventoryCurrentStocks(Set<InventoryCurrentStock> subInventoryCurrentStocks) {
		this.subInventoryCurrentStocks = subInventoryCurrentStocks;
	}

}
