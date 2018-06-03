package com.vix.inventory.option.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 库存参数
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-7
 */

public class InventoryParameters extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 系统启用会计月
	 */
	private Date startAccountMonth;
	/**
	 * 有无组织拆卸业务 0：否 1：是
	 */
	private String isAssemblyAndDisassembly;
	/**
	 * 有无成套件管理 0：否 1：是
	 */
	private String isSuite;
	/**
	 * 有无形态转换业务 0：否 1：是
	 */
	private String isPatternsTransition;
	/**
	 * 有无批次管理 0：否 1：是
	 */
	private String isBatch;
	/**
	 * 有无委托代销 0：否 1：是
	 */
	private String isConsignmentSales;
	/**
	 * 有无保质期管理 0：否 1：是
	 */
	private String isShelfLife;

	/**
	 * 有无受托代销管理 0：否 1：是
	 */
	private String isOnCommission;
	/** 是否启用货位 0：否 1：是 */
	private String enableShelf;
	/** 是否启用规格 0：否 1：是 */
	private String enableSpecification;
	/** 是否启用POS 0：否 1：是 */
	private String enablePos;
	/** 是否启用电商 0：否 1：是 */
	private String enableEc;
	/** 是否服务 0：商品 1：服务 */
	private String isServiceItem;
	/**
	 * 出库模式0:先入先出 1:先入后出
	 */
	private String outMode;

	public Date getStartAccountMonth() {
		return startAccountMonth;
	}

	public void setStartAccountMonth(Date startAccountMonth) {
		this.startAccountMonth = startAccountMonth;
	}

	public String getIsAssemblyAndDisassembly() {
		return isAssemblyAndDisassembly;
	}

	public void setIsAssemblyAndDisassembly(String isAssemblyAndDisassembly) {
		this.isAssemblyAndDisassembly = isAssemblyAndDisassembly;
	}

	public String getIsSuite() {
		return isSuite;
	}

	public void setIsSuite(String isSuite) {
		this.isSuite = isSuite;
	}

	public String getIsPatternsTransition() {
		return isPatternsTransition;
	}

	public void setIsPatternsTransition(String isPatternsTransition) {
		this.isPatternsTransition = isPatternsTransition;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}

	public String getIsConsignmentSales() {
		return isConsignmentSales;
	}

	public void setIsConsignmentSales(String isConsignmentSales) {
		this.isConsignmentSales = isConsignmentSales;
	}

	public String getIsShelfLife() {
		return isShelfLife;
	}

	public void setIsShelfLife(String isShelfLife) {
		this.isShelfLife = isShelfLife;
	}

	public String getIsOnCommission() {
		return isOnCommission;
	}

	public void setIsOnCommission(String isOnCommission) {
		this.isOnCommission = isOnCommission;
	}

	/**
	 * @return the enableShelf
	 */
	public String getEnableShelf() {
		return enableShelf;
	}

	/**
	 * @param enableShelf
	 *            the enableShelf to set
	 */
	public void setEnableShelf(String enableShelf) {
		this.enableShelf = enableShelf;
	}

	/**
	 * @return the enableSpecification
	 */
	public String getEnableSpecification() {
		return enableSpecification;
	}

	/**
	 * @param enableSpecification
	 *            the enableSpecification to set
	 */
	public void setEnableSpecification(String enableSpecification) {
		this.enableSpecification = enableSpecification;
	}

	/**
	 * @return the enablePos
	 */
	public String getEnablePos() {
		return enablePos;
	}

	/**
	 * @param enablePos
	 *            the enablePos to set
	 */
	public void setEnablePos(String enablePos) {
		this.enablePos = enablePos;
	}

	/**
	 * @return the enableEc
	 */
	public String getEnableEc() {
		return enableEc;
	}

	/**
	 * @param enableEc
	 *            the enableEc to set
	 */
	public void setEnableEc(String enableEc) {
		this.enableEc = enableEc;
	}

	/**
	 * @return the isServiceItem
	 */
	public String getIsServiceItem() {
		return isServiceItem;
	}

	/**
	 * @param isServiceItem
	 *            the isServiceItem to set
	 */
	public void setIsServiceItem(String isServiceItem) {
		this.isServiceItem = isServiceItem;
	}

	/**
	 * @return the outMode
	 */
	public String getOutMode() {
		return outMode;
	}

	/**
	 * @param outMode
	 *            the outMode to set
	 */
	public void setOutMode(String outMode) {
		this.outMode = outMode;
	}

}