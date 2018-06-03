package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

public class ItemFinanceProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 物料 */
	private Item item;
	/** 物料类型 */
	private String itemType;
	/** 总帐分类 */
	private String gernalCatalog;
	/** 价格单位 */
	private String priceUnit;
	/** 公司标识代码 */
	private String companyCode;
	/** 库存组织 */
	private String stockOrg;	
	/** 仓库代码 */
	private String warehouseCode;
	/** 入库计价方式 */
	private String inStockPricingStyle;
	/** 出库计价方式 */
	private String outStockPricingStyle;
	/** 移动平均价 */
	private Double movingAveragePrice;
	/** 先进先出价 */
	private Double fifoPrice;
	/** 后进先出价 */
	private Double lifoPrice;
	/** 全月平均价 */
	private Double weightedAveragePrice;
	/** 标准成本价 */
	private Double standardCost;

	public ItemFinanceProperties(){}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getGernalCatalog() {
		return gernalCatalog;
	}

	public void setGernalCatalog(String gernalCatalog) {
		this.gernalCatalog = gernalCatalog;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	@Override
	public String getCompanyCode() {
		return companyCode;
	}

	@Override
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getStockOrg() {
		return stockOrg;
	}

	public void setStockOrg(String stockOrg) {
		this.stockOrg = stockOrg;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getInStockPricingStyle() {
		return inStockPricingStyle;
	}

	public void setInStockPricingStyle(String inStockPricingStyle) {
		this.inStockPricingStyle = inStockPricingStyle;
	}

	public String getOutStockPricingStyle() {
		return outStockPricingStyle;
	}

	public void setOutStockPricingStyle(String outStockPricingStyle) {
		this.outStockPricingStyle = outStockPricingStyle;
	}

	public Double getMovingAveragePrice() {
		return movingAveragePrice;
	}

	public void setMovingAveragePrice(Double movingAveragePrice) {
		this.movingAveragePrice = movingAveragePrice;
	}

	public Double getFifoPrice() {
		return fifoPrice;
	}

	public void setFifoPrice(Double fifoPrice) {
		this.fifoPrice = fifoPrice;
	}

	public Double getLifoPrice() {
		return lifoPrice;
	}

	public void setLifoPrice(Double lifoPrice) {
		this.lifoPrice = lifoPrice;
	}

	public Double getWeightedAveragePrice() {
		return weightedAveragePrice;
	}

	public void setWeightedAveragePrice(Double weightedAveragePrice) {
		this.weightedAveragePrice = weightedAveragePrice;
	}

	public Double getStandardCost() {
		return standardCost;
	}

	public void setStandardCost(Double standardCost) {
		this.standardCost = standardCost;
	}
}
