package com.vix.mdm.purchase.order.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 拣货单明细
 * 
 * @类全名 com.vix.mdm.purchase.order.entity.NvixPickingListDetail
 *
 * @author zhanghaibing
 *
 * @date 2016年11月30日
 */
public class NvixPickingListDetail extends BaseEntity {
	/**
	 * status 0,未采购;1,已采购.
	 */
	private static final long serialVersionUID = 1L;

	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 规格型号 */
	private String specification;
	/** 计量单位 */
	private String unit;
	/** 单价 */
	private Double price;
	/** 数量 */
	private Double amount;
	/**
	 * 批次单
	 */
	private NvixOrderBatch nvixOrderBatch;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public NvixOrderBatch getNvixOrderBatch() {
		return nvixOrderBatch;
	}

	public void setNvixOrderBatch(NvixOrderBatch nvixOrderBatch) {
		this.nvixOrderBatch = nvixOrderBatch;
	}

}
