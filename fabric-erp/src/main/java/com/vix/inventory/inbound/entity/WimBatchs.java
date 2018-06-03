package com.vix.inventory.inbound.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 批次管理
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-5
 */
public class WimBatchs extends BaseEntity {
	private static final long serialVersionUID = -8083032167010062019L;

	/**
	 * 批次号
	 */
	private String batchcode;
	/**
	 * 物料编码
	 */
	private String itemcode;
	/**
	 * 物料名称
	 */
	private String itemname;
	/**
	 * 仓库编码
	 */
	private String warehousecode;
	/**
	 * 仓库
	 */
	private String warehouse;

	/** default constructor */
	public WimBatchs() {
	}

	public String getBatchcode() {
		return this.batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
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

	public String getWarehousecode() {
		return this.warehousecode;
	}

	public void setWarehousecode(String warehousecode) {
		this.warehousecode = warehousecode;
	}

	public String getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

}