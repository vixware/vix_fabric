package com.vix.drp.competitiveproducts.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 竞争产品信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-28
 */
public class CompetingProducts extends BaseEntity {
	private static final long serialVersionUID = -7062700099990317009L;
	/** EAN/UPC */
	private String eanupc;
	/** 批次号 */
	private String batchCode;
	/** 序列号 */
	private String serialCode;
	/** 物料组 */
	private String itemGroup;
	/** 物料助记码 */
	private String scode;
	/** 物料代码 */
	private String xcode;
	/** 物料类型 */
	private String itemType;
	/** 行业领域 */
	private String industry;
	/** 规格型号 */
	private String specification;
	/** 规格说明 */
	private String specDescription;
	/** 尺码 */
	private String size;
	/** 颜色 */
	private String color;
	/** 材料质地 */
	private String material;
	/** 通用名称 */
	private String gernalName;
	/** 英文名 */
	private String engishName;
	/** 物料分类 */
	private String catalog;
	/** 主计量单位 */
	private String masterUnit;
	/** 采购默认单位 */
	private String purchaseUnit;
	/** 销售默认计量单位 */
	private String saleUnit;
	/** 生产计量单位 */
	private String produceUnit;
	/** 销售默认单位 */
	private String saleDefaultUnit;
	/** 库存默认单位 */
	private String stockDefaultUnit;
	/** 成本默认计量单位 */
	private String costDefaultUnit;
	/** 零售计量单位 */
	private String retailUnit;
	/** 销售税率 */
	private Double saleTax;
	/** 生产国别 */
	private String productCountry;
	/** 生产企业 */
	private String productEnterprise;
	/** 生产企业代码 */
	private String enterpriseCode;
	/** 产品线 */
	private String productLine;
	/** 次产品线 */
	private String viceProductLine;
	/** 生产地点 */
	private String productLocation;
	/** 品牌 */
	private String brand;
	/** 产地 */
	private String origin;
	/** 操作人 */
	private String operator;
	/** 产品组 */
	private String productGroup;
	/** 操作时间 */
	private Date operateTime;
	/** 是否统计 */
	private String isStatics;
	/** 是否有效 */
	private String isValid;
	/** 价格 */
	private Double price;

	public String getEanupc() {
		return eanupc;
	}

	public void setEanupc(String eanupc) {
		this.eanupc = eanupc;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public String getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getXcode() {
		return xcode;
	}

	public void setXcode(String xcode) {
		this.xcode = xcode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getSpecDescription() {
		return specDescription;
	}

	public void setSpecDescription(String specDescription) {
		this.specDescription = specDescription;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getGernalName() {
		return gernalName;
	}

	public void setGernalName(String gernalName) {
		this.gernalName = gernalName;
	}

	public String getEngishName() {
		return engishName;
	}

	public void setEngishName(String engishName) {
		this.engishName = engishName;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getMasterUnit() {
		return masterUnit;
	}

	public void setMasterUnit(String masterUnit) {
		this.masterUnit = masterUnit;
	}

	public String getPurchaseUnit() {
		return purchaseUnit;
	}

	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}

	public String getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}

	public String getProduceUnit() {
		return produceUnit;
	}

	public void setProduceUnit(String produceUnit) {
		this.produceUnit = produceUnit;
	}

	public String getSaleDefaultUnit() {
		return saleDefaultUnit;
	}

	public void setSaleDefaultUnit(String saleDefaultUnit) {
		this.saleDefaultUnit = saleDefaultUnit;
	}

	public String getStockDefaultUnit() {
		return stockDefaultUnit;
	}

	public void setStockDefaultUnit(String stockDefaultUnit) {
		this.stockDefaultUnit = stockDefaultUnit;
	}

	public String getCostDefaultUnit() {
		return costDefaultUnit;
	}

	public void setCostDefaultUnit(String costDefaultUnit) {
		this.costDefaultUnit = costDefaultUnit;
	}

	public String getRetailUnit() {
		return retailUnit;
	}

	public void setRetailUnit(String retailUnit) {
		this.retailUnit = retailUnit;
	}

	public Double getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(Double saleTax) {
		this.saleTax = saleTax;
	}

	public String getProductCountry() {
		return productCountry;
	}

	public void setProductCountry(String productCountry) {
		this.productCountry = productCountry;
	}

	public String getProductEnterprise() {
		return productEnterprise;
	}

	public void setProductEnterprise(String productEnterprise) {
		this.productEnterprise = productEnterprise;
	}

	@Override
	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	@Override
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getViceProductLine() {
		return viceProductLine;
	}

	public void setViceProductLine(String viceProductLine) {
		this.viceProductLine = viceProductLine;
	}

	public String getProductLocation() {
		return productLocation;
	}

	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getIsStatics() {
		return isStatics;
	}

	public void setIsStatics(String isStatics) {
		this.isStatics = isStatics;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
