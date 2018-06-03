package com.vix.mdm.item.entity;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 物料
 * 
 * @类全名 com.vix.mdm.item.entity.Item
 *
 * @author zhanghaibing
 *
 * @date 2016年9月22日
 */
public class Item extends ItemBaseEntity {

	private static final long serialVersionUID = 1L;

	/** 旧物料编码 */
	private String oldItemCode;
	/** 物料低阶码 */
	private String lowerCode;
	/** 服务内容 */
	private String serviceContent;
	/** EAN/UPC */
	private String eanupc;
	/** 中文拼音简写 */
	private String chinaShortName;
	/** 批次号 */
	private String batchCode;
	/** 序列号 */
	private String serialCode;
	/** 图号 */
	private String drawingCode;
	/** 物料助记码 */
	private String scode;
	/** sku码 */
	private String skuCode;
	/** 物料代码 */
	private String xcode;
	/** 物料类型 */
	private String itemType;
	/** 状态码 */
	private String statusCode;
	/** 快捷码 */
	private String fastCode;
	/** 行业领域 */
	private String industry;
	/**
	 * 物料状态码 private String status;   
	 */
	/** 规格型号 */
	private String specification;
	/** 规格说明 */
	private String specDescription;
	/** 净含量 */
	private Double net;
	/** 成分 */
	private String composition;
	/** 地区 */
	private String region;
	/** 尺码 */
	private String size;
	/** 颜色 */
	private String color;
	/** 味道类型 */
	private String tasteType;
	/** 价格 */
	private Double price;
	/** 一级批发价 */
	private Double firstLevelTradePrice;
	/** 二级批发价 */
	private Double secondLevelTradePrice;
	/** 成本价格 */
	private Double costPrice;
	/** 采购价格 */
	private Double purchasePrice;
	/** 批发价格 */
	private Double wholesalePrice;
	/** 出厂日期 */
	private Date productionDate;
	/** 失效日期 */
	private Date expiryDate;
	/** 生产日期 */
	private Date produceDate;
	/** 保质期 */
	private Float qualityPeriod;
	/** 保质期单位 */
	private String qualityPeriodUnit;
	/** 投放日期 */
	private Date putOnDate;
	/** 停售日期 */
	private Date stopSellingDate;
	/** 材料质地 */
	private String material;
	/** 通用名称 */
	private String gernalName;
	/** 英文名 */
	private String englishName;
	/** 物料类型 */
	private String type;
	/** 海关编码 */
	private String customsCode;
	/** 海关计量单位 */
	private String customsUnit;
	/** 海关单位换算率 */
	private Double customsUnitExchange;
	/** 重量计量组 */
	private String weightMensurationGroup;
	/** 重量单位 */
	private String weightUnit;
	/** 净重 */
	private Double netWeight;
	/** 毛重 */
	private Double grossWeight;
	/** 体积计量组 */
	private String volumeMensurationGroup;
	/** 体积单位 */
	private String volumeUnit;
	/** 长 */
	private Double length;
	/** 宽 */
	private Double width;
	/** 高 */
	private Double height;
	/** 单位体积 */
	private Double unitVolume;
	/** 物料分类 */
	private String catalog;
	/** 是否物料组 */
	private String isItemGroup;
	/** 单位组 */
	private MeasureUnitGroup measureUnitGroup;
	/** 主计量单位 */
	private MeasureUnit measureUnit;
	/** 主计量单位 */
	private MeasureUnit assitMeasureUnit;
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
	/**
	 * 生产企业代码 private String enterpriseCode;
	 */
	/** 供应商 */
	private String supplierId;
	private String supplierName;
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
	/** 是否为虚拟件 */
	private String isVirtual;
	/** 是否主生产计划物料(MPS) */
	private String isMPS;
	/** 是否需要配置 */
	private String isConfig;
	/** 是否质量检查 */
	private String isQualityCheck;
	/** 是否质量检查 */
	private String isSaleItem;
	/** 是否外购物料 */
	private String isPurchaseItem;
	/** 是否生产耗用 */
	private String isProduceItem;
	/** 是否委外 */
	private String isOutSourcingItem;
	/** 是否自制 */
	private String isSelfProduce;
	/** 是否在制 */
	private String isInProduce;
	/** 是否内销 */
	private String isDomesticMarket;
	/** 是否外销 */
	private String isOverseaMarket;
	/** 是否外购 */
	private String isPurchase;
	/** 是否生产耗用 */
	private String isProduceConsumption;
	/** 是否计划品 */
	private String isPlanProduct;
	/** 是否选项类 */
	private String isOptions;
	/** 是否备件 */
	private String isSparePart;
	/** 是否PTO */
	private String isPTO;
	/** 是否ATO */
	private String isATO;
	/** 是否模型 */
	private String isModel;
	/** 是否资产 */
	private String isAsset;
	/** 是否工程物料 */
	private String isEngineeringItem;
	/** 是否计件 */
	private String isPiecework;
	/** 是否服务项目 */
	private String isServiceItem;
	/** 是否服务配件 */
	private String isServiceParts;
	/** 是否服务产品 */
	private String isServiceProduct;
	/** 是否折扣 */
	private String isDiscount;
	/** 是否委托代销 */
	private String isConsignmentsale;
	/** 是否成套件 */
	private String isProductSuite;
	/** 批注文号 */
	private String referenceNumber;
	/** 包装规格 */
	private String packageSpecs;
	/** 合格证号 */
	private String certificateOfApprovalNumber;
	/** 注册商标 */
	private String registerBrand;
	/** 入关证号 */
	private String enterCustomsNumber;
	/** 许可证号 */
	private String licenseNumber;
	/** 专利名称 */
	private String patentName;
	/** 国际非专利名 */
	private String nonPatentName;
	/** 质量要求 */
	private String qualityRequirement;
	/** 操作人 */
	private String operator;
	/**
	 * 部门 private String departmentCode;
	 */
	/** 操作时间 */
	private Date operateTime;
	/** 是否统计 */
	private String isStatics;
	/** 是否有效 */
	private String isValid;
	/** 进项税率 */
	private Double inTax;
	/** 物料(产品)编码 */
	private Set<ItemCodes> itemCodes = new HashSet<ItemCodes>();
	/** 币种 */
	private CurrencyType currencyType;
	/** 物料组 */
	private ItemGroup itemGroup;
	/** 物料品牌 */
	private ItemBrand itemBrand;
	/** 物料(产品)成本属性 */
	private ItemCostProperties itemCostProperties;
	/** 物料(产品)基本属性 */
	private ItemGernalProperties itemGernalProperties;
	/** 物料(产品)采购属性 */
	private ItemPurchaseProperties itemPurchaseProperties;
	/** 物料(产品)销售属性 */
	private ItemSaleProperties itemSaleProperties;
	/** 物料(产品)库存属性 */
	private ItemInventoryProperties itemInventoryProperties;
	/** 物料(产品)MRP属性 */
	private ItemMRPProperties itemMRPProperties;
	/** 物料(产品)运输与包装属性 */
	private ItemPackageProperties itemPackageProperties;
	/** 物料(产品)计划属性 */
	private ItemPlanProperties itemPlanProperties;
	/** 物料(产品)质量属性 */
	private ItemQualityProperties itemQualityProperties;
	/** 价格条件 */
	private Set<PriceCondition> priceCondition = new HashSet<PriceCondition>();
	/** 对象类型 */
	private ObjectExpand objectExpand;
	/** 物料类型 */
	private String itemClass;
	/** 物料分类 */
	private Set<ItemCatalog> itemCatalogs = new HashSet<ItemCatalog>();
	/** 单据附件 */
	private Set<ItemAttachFile> itemAttachFiles = new HashSet<ItemAttachFile>();
	/** 替代物料 */
	private Set<ItemReplace> itemReplaces = new HashSet<ItemReplace>();
	/** 物料图片 */
	private Set<ItemImage> itemImages = new HashSet<ItemImage>();
	/** 物料价格 */
	private Set<ItemPrice> itemPrices = new HashSet<ItemPrice>();

	private Set<Item> subItems = new HashSet<Item>();
	/**
	 * SKU
	 */
	private Set<Sku> subSkus = new HashSet<Sku>();

	private Item parentItem;
	/**
	 * 是否绑定商品 1 绑定 2 未绑定
	 */
	private String isBinding;
	/**
	 * 绑定数量
	 */
	private Double bindingNum;

	private Set<ChannelDistributor> subChannelDistributors = new HashSet<ChannelDistributor>();

	private String goodsClass;// 商品种类 0普通商品 1捆绑商品

	/** 物料分类ids 非持久化字段 */
	private String itemCatalogIds = "";
	// 分类编码
	private String itemCataCode = "";
	/** 物料分类names 非持久化字段 */
	private String itemCatalogNames = "";
	// 会员价.保留2位小数
	private Double vipPrice;
	// status 是否启用 是，T 否,F
	private String pinyin;
	// 是否PLU商品. 是，T 否,F
	private String isplu;
	private String plu;// PLU编码
	private String pluMode;// PLU模式. 字典值:10,称重 20,计件
	private String revAccountId;// 收益科目编码

	// 价格
	/** 手机端销售价格 */
	private Double mobilePrice;
	/** APP端销售价格 */
	private Double appPrice;
	// 积分
	/** 积分兑换 */
	private Long coinExchange;
	/** 赠送积分 */
	private Long giveIntegral;
	// 商品标签
	/** 标签 */
	private String tags;
	/**
	 * 商品分类
	 */
	private ItemCatalog itemCatalog;
	private String itemCatalogName;
	private Supplier supplier;
	/**
	 * 是否常用商品
	 */
	private String isCommon;
	/**
	 * 商品主图路径
	 */
	private String imageFilePath;

	private ItemPackage itemPackage;
	/**
	 * 是不是商品         T:是    F:否
	 */
	private String isItem; 
	public Item() {
	}

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getOldItemCode() {
		return oldItemCode;
	}

	public void setOldItemCode(String oldItemCode) {
		this.oldItemCode = oldItemCode;
	}

	public String getItemCataCode() {
		return itemCataCode;
	}

	public void setItemCataCode(String itemCataCode) {
		this.itemCataCode = itemCataCode;
	}

	public String getLowerCode() {
		return lowerCode;
	}

	public void setLowerCode(String lowerCode) {
		this.lowerCode = lowerCode;
	}

	public String getIsCommon() {
		return isCommon;
	}

	public void setIsCommon(String isCommon) {
		this.isCommon = isCommon;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Double getBindingNum() {
		return bindingNum;
	}

	public void setBindingNum(Double bindingNum) {
		this.bindingNum = bindingNum;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getEanupc() {
		return eanupc;
	}

	public void setEanupc(String eanupc) {
		this.eanupc = eanupc;
	}

	public String getChinaShortName() {
		return chinaShortName;
	}

	public void setChinaShortName(String chinaShortName) {
		this.chinaShortName = chinaShortName;
	}

	public void setItemCatalogName(String itemCatalogName) {
		this.itemCatalogName = itemCatalogName;
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

	public String getDrawingCode() {
		return drawingCode;
	}

	public void setDrawingCode(String drawingCode) {
		this.drawingCode = drawingCode;
	}

	public ItemGroup getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(ItemGroup itemGroup) {
		this.itemGroup = itemGroup;
	}

	public ItemBrand getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(ItemBrand itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Set<Item> getSubItems() {
		return subItems;
	}

	public void setSubItems(Set<Item> subItems) {
		this.subItems = subItems;
	}

	public Item getParentItem() {
		return parentItem;
	}

	public void setParentItem(Item parentItem) {
		this.parentItem = parentItem;
	}

	public String getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(String isBinding) {
		this.isBinding = isBinding;
	}

	public String getFastCode() {
		return fastCode;
	}

	public void setFastCode(String fastCode) {
		this.fastCode = fastCode;
	}

	public ItemCatalog getItemCatalog() {
		return itemCatalog;
	}

	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}

	@Override
	public String getIndustry() {
		return industry;
	}

	@Override
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

	public Double getNet() {
		return net;
	}

	public void setNet(Double net) {
		this.net = net;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public String getTasteType() {
		return tasteType;
	}

	public void setTasteType(String tasteType) {
		this.tasteType = tasteType;
	}

	public Double getPrice() {
		if (price != null) {
			DecimalFormat df = new DecimalFormat(".##");
			String st = df.format(price);
			return Double.parseDouble(st);
		} else {
			return null;
		}
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getFirstLevelTradePrice() {
		return firstLevelTradePrice;
	}

	public void setFirstLevelTradePrice(Double firstLevelTradePrice) {
		this.firstLevelTradePrice = firstLevelTradePrice;
	}

	public Double getSecondLevelTradePrice() {
		return secondLevelTradePrice;
	}

	public void setSecondLevelTradePrice(Double secondLevelTradePrice) {
		this.secondLevelTradePrice = secondLevelTradePrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public String getProfitMargin() {
		String pmargin = "";
		if (null != price && null != costPrice && price > 0 && costPrice > 0) {
			Double pm = (price - costPrice) / costPrice;
			DecimalFormat df = new DecimalFormat("###.00");
			pmargin = df.format(pm) + "%";
		}
		return pmargin;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(Double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getQualityPeriodUnit() {
		return qualityPeriodUnit;
	}

	public void setQualityPeriodUnit(String qualityPeriodUnit) {
		this.qualityPeriodUnit = qualityPeriodUnit;
	}

	public Date getPutOnDate() {
		return putOnDate;
	}

	public String getPutOnDateStr() {
		if (null != putOnDate) {
			return DateUtil.format(putOnDate);
		}
		return "";
	}

	public void setPutOnDate(Date putOnDate) {
		this.putOnDate = putOnDate;
	}

	public Date getStopSellingDate() {
		return stopSellingDate;
	}

	public String getStopSellingDateStr() {
		if (null != stopSellingDate) {
			return DateUtil.format(stopSellingDate);
		}
		return "";
	}

	public void setStopSellingDate(Date stopSellingDate) {
		this.stopSellingDate = stopSellingDate;
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

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getCustomsUnit() {
		return customsUnit;
	}

	public void setCustomsUnit(String customsUnit) {
		this.customsUnit = customsUnit;
	}

	public Double getCustomsUnitExchange() {
		return customsUnitExchange;
	}

	public void setCustomsUnitExchange(Double customsUnitExchange) {
		this.customsUnitExchange = customsUnitExchange;
	}

	public String getWeightMensurationGroup() {
		return weightMensurationGroup;
	}

	public void setWeightMensurationGroup(String weightMensurationGroup) {
		this.weightMensurationGroup = weightMensurationGroup;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getVolumeMensurationGroup() {
		return volumeMensurationGroup;
	}

	public void setVolumeMensurationGroup(String volumeMensurationGroup) {
		this.volumeMensurationGroup = volumeMensurationGroup;
	}

	public String getVolumeUnit() {
		return volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getUnitVolume() {
		return unitVolume;
	}

	public void setUnitVolume(Double unitVolume) {
		this.unitVolume = unitVolume;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getIsItemGroup() {
		return isItemGroup;
	}

	public void setIsItemGroup(String isItemGroup) {
		this.isItemGroup = isItemGroup;
	}

	public MeasureUnitGroup getMeasureUnitGroup() {
		return measureUnitGroup;
	}

	public void setMeasureUnitGroup(MeasureUnitGroup measureUnitGroup) {
		this.measureUnitGroup = measureUnitGroup;
	}

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	public MeasureUnit getAssitMeasureUnit() {
		return assitMeasureUnit;
	}

	public void setAssitMeasureUnit(MeasureUnit assitMeasureUnit) {
		this.assitMeasureUnit = assitMeasureUnit;
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

	public String getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getIsMPS() {
		return isMPS;
	}

	public void setIsMPS(String isMPS) {
		this.isMPS = isMPS;
	}

	public String getIsConfig() {
		return isConfig;
	}

	public void setIsConfig(String isConfig) {
		this.isConfig = isConfig;
	}

	public String getIsQualityCheck() {
		return isQualityCheck;
	}

	public void setIsQualityCheck(String isQualityCheck) {
		this.isQualityCheck = isQualityCheck;
	}

	public String getIsSaleItem() {
		return isSaleItem;
	}

	public void setIsSaleItem(String isSaleItem) {
		this.isSaleItem = isSaleItem;
	}

	public String getIsPurchaseItem() {
		return isPurchaseItem;
	}

	public void setIsPurchaseItem(String isPurchaseItem) {
		this.isPurchaseItem = isPurchaseItem;
	}

	public String getIsProduceItem() {
		return isProduceItem;
	}

	public void setIsProduceItem(String isProduceItem) {
		this.isProduceItem = isProduceItem;
	}

	public String getIsOutSourcingItem() {
		return isOutSourcingItem;
	}

	public void setIsOutSourcingItem(String isOutSourcingItem) {
		this.isOutSourcingItem = isOutSourcingItem;
	}

	public String getIsSelfProduce() {
		return isSelfProduce;
	}

	public void setIsSelfProduce(String isSelfProduce) {
		this.isSelfProduce = isSelfProduce;
	}

	public String getIsInProduce() {
		return isInProduce;
	}

	public void setIsInProduce(String isInProduce) {
		this.isInProduce = isInProduce;
	}

	public String getIsDomesticMarket() {
		return isDomesticMarket;
	}

	public void setIsDomesticMarket(String isDomesticMarket) {
		this.isDomesticMarket = isDomesticMarket;
	}

	public String getIsOverseaMarket() {
		return isOverseaMarket;
	}

	public void setIsOverseaMarket(String isOverseaMarket) {
		this.isOverseaMarket = isOverseaMarket;
	}

	public String getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(String isPurchase) {
		this.isPurchase = isPurchase;
	}

	public String getIsProduceConsumption() {
		return isProduceConsumption;
	}

	public void setIsProduceConsumption(String isProduceConsumption) {
		this.isProduceConsumption = isProduceConsumption;
	}

	public String getIsPlanProduct() {
		return isPlanProduct;
	}

	public void setIsPlanProduct(String isPlanProduct) {
		this.isPlanProduct = isPlanProduct;
	}

	public String getIsOptions() {
		return isOptions;
	}

	public void setIsOptions(String isOptions) {
		this.isOptions = isOptions;
	}

	public String getIsSparePart() {
		return isSparePart;
	}

	public void setIsSparePart(String isSparePart) {
		this.isSparePart = isSparePart;
	}

	public String getIsPTO() {
		return isPTO;
	}

	public void setIsPTO(String isPTO) {
		this.isPTO = isPTO;
	}

	public String getIsATO() {
		return isATO;
	}

	public void setIsATO(String isATO) {
		this.isATO = isATO;
	}

	public String getIsModel() {
		return isModel;
	}

	public void setIsModel(String isModel) {
		this.isModel = isModel;
	}

	public String getIsAsset() {
		return isAsset;
	}

	public void setIsAsset(String isAsset) {
		this.isAsset = isAsset;
	}

	public String getIsEngineeringItem() {
		return isEngineeringItem;
	}

	public void setIsEngineeringItem(String isEngineeringItem) {
		this.isEngineeringItem = isEngineeringItem;
	}

	public String getIsPiecework() {
		return isPiecework;
	}

	public void setIsPiecework(String isPiecework) {
		this.isPiecework = isPiecework;
	}

	/**
	 * @return the supplierId
	 */
	public String getSupplierId() {
		if (supplier != null) {
			return supplier.getId();
		}
		return supplierId;
	}

	public String getItemCatalogName() {
		return itemCatalogName;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		if (supplier != null) {
			return supplier.getName();
		}
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getIsServiceItem() {
		return isServiceItem;
	}

	public void setIsServiceItem(String isServiceItem) {
		this.isServiceItem = isServiceItem;
	}

	public String getIsServiceParts() {
		return isServiceParts;
	}

	public void setIsServiceParts(String isServiceParts) {
		this.isServiceParts = isServiceParts;
	}

	public String getIsServiceProduct() {
		return isServiceProduct;
	}

	public void setIsServiceProduct(String isServiceProduct) {
		this.isServiceProduct = isServiceProduct;
	}

	public String getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}

	public String getIsConsignmentsale() {
		return isConsignmentsale;
	}

	public void setIsConsignmentsale(String isConsignmentsale) {
		this.isConsignmentsale = isConsignmentsale;
	}

	public String getIsProductSuite() {
		return isProductSuite;
	}

	public void setIsProductSuite(String isProductSuite) {
		this.isProductSuite = isProductSuite;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getPackageSpecs() {
		return packageSpecs;
	}

	public void setPackageSpecs(String packageSpecs) {
		this.packageSpecs = packageSpecs;
	}

	public String getCertificateOfApprovalNumber() {
		return certificateOfApprovalNumber;
	}

	public void setCertificateOfApprovalNumber(String certificateOfApprovalNumber) {
		this.certificateOfApprovalNumber = certificateOfApprovalNumber;
	}

	public String getRegisterBrand() {
		return registerBrand;
	}

	public void setRegisterBrand(String registerBrand) {
		this.registerBrand = registerBrand;
	}

	public String getEnterCustomsNumber() {
		return enterCustomsNumber;
	}

	public void setEnterCustomsNumber(String enterCustomsNumber) {
		this.enterCustomsNumber = enterCustomsNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getPatentName() {
		return patentName;
	}

	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}

	public String getNonPatentName() {
		return nonPatentName;
	}

	public void setNonPatentName(String nonPatentName) {
		this.nonPatentName = nonPatentName;
	}

	public String getQualityRequirement() {
		return qualityRequirement;
	}

	public void setQualityRequirement(String qualityRequirement) {
		this.qualityRequirement = qualityRequirement;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public Double getInTax() {
		return inTax;
	}

	public void setInTax(Double inTax) {
		this.inTax = inTax;
	}

	public Set<ItemCodes> getItemCodes() {
		return itemCodes;
	}

	public void setItemCodes(Set<ItemCodes> itemCodes) {
		this.itemCodes = itemCodes;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public ItemCostProperties getItemCostProperties() {
		return itemCostProperties;
	}

	public void setItemCostProperties(ItemCostProperties itemCostProperties) {
		this.itemCostProperties = itemCostProperties;
	}

	public ItemGernalProperties getItemGernalProperties() {
		return itemGernalProperties;
	}

	public void setItemGernalProperties(ItemGernalProperties itemGernalProperties) {
		this.itemGernalProperties = itemGernalProperties;
	}

	public ItemPurchaseProperties getItemPurchaseProperties() {
		return itemPurchaseProperties;
	}

	public void setItemPurchaseProperties(ItemPurchaseProperties itemPurchaseProperties) {
		this.itemPurchaseProperties = itemPurchaseProperties;
	}

	public ItemSaleProperties getItemSaleProperties() {
		return itemSaleProperties;
	}

	public void setItemSaleProperties(ItemSaleProperties itemSaleProperties) {
		this.itemSaleProperties = itemSaleProperties;
	}

	public ItemInventoryProperties getItemInventoryProperties() {
		return itemInventoryProperties;
	}

	public void setItemInventoryProperties(ItemInventoryProperties itemInventoryProperties) {
		this.itemInventoryProperties = itemInventoryProperties;
	}

	public ItemMRPProperties getItemMRPProperties() {
		return itemMRPProperties;
	}

	public void setItemMRPProperties(ItemMRPProperties itemMRPProperties) {
		this.itemMRPProperties = itemMRPProperties;
	}

	public ItemPackageProperties getItemPackageProperties() {
		return itemPackageProperties;
	}

	public void setItemPackageProperties(ItemPackageProperties itemPackageProperties) {
		this.itemPackageProperties = itemPackageProperties;
	}

	public ItemPlanProperties getItemPlanProperties() {
		return itemPlanProperties;
	}

	public void setItemPlanProperties(ItemPlanProperties itemPlanProperties) {
		this.itemPlanProperties = itemPlanProperties;
	}

	public ItemQualityProperties getItemQualityProperties() {
		return itemQualityProperties;
	}

	public void setItemQualityProperties(ItemQualityProperties itemQualityProperties) {
		this.itemQualityProperties = itemQualityProperties;
	}

	public Set<PriceCondition> getPriceCondition() {
		return priceCondition;
	}

	public void setPriceCondition(Set<PriceCondition> priceCondition) {
		this.priceCondition = priceCondition;
	}

	public ObjectExpand getObjectExpand() {
		return objectExpand;
	}

	public void setObjectExpand(ObjectExpand objectExpand) {
		this.objectExpand = objectExpand;
	}

	public Set<ItemCatalog> getItemCatalogs() {
		return itemCatalogs;
	}

	public void setItemCatalogs(Set<ItemCatalog> itemCatalogs) {
		this.itemCatalogs = itemCatalogs;
	}

	public Set<ItemAttachFile> getItemAttachFiles() {
		return itemAttachFiles;
	}

	public void setItemAttachFiles(Set<ItemAttachFile> itemAttachFiles) {
		this.itemAttachFiles = itemAttachFiles;
	}

	public Set<ItemReplace> getItemReplaces() {
		return itemReplaces;
	}

	public void setItemReplaces(Set<ItemReplace> itemReplaces) {
		this.itemReplaces = itemReplaces;
	}

	public void setItemCatalogIds(String itemCatalogIds) {
		this.itemCatalogIds = itemCatalogIds;
	}

	public void setItemCatalogNames(String itemCatalogNames) {
		this.itemCatalogNames = itemCatalogNames;
	}

	public String getItemCatalogIds() {
		/*
		 * if(null == itemCatalogIds || "".equals(itemCatalogIds)){ if(null !=
		 * itemCatalogs && itemCatalogs.size() > 0){ for(ItemCatalog ic :
		 * itemCatalogs){ if(null != ic){ itemCatalogIds += ic.getId() + ",";
		 * itemCatalogNames += ic.getName() + ","; } } } }
		 */
		return itemCatalogIds;
	}

	public String getItemCatalogNames() {
		/*
		 * if(null == itemCatalogNames || "".equals(itemCatalogNames)){ if(null
		 * != itemCatalogs && itemCatalogs.size() > 0){ for(ItemCatalog ic :
		 * itemCatalogs){ if(null != ic){ itemCatalogIds += ic.getId() + ",";
		 * itemCatalogNames += ic.getName() + ","; } } } }
		 */
		return itemCatalogNames;
	}

	public Set<ItemImage> getItemImages() {
		return itemImages;
	}

	public void setItemImages(Set<ItemImage> itemImages) {
		this.itemImages = itemImages;
	}

	public String getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(String goodsClass) {
		this.goodsClass = goodsClass;
	}

	public Set<ItemPrice> getItemPrices() {
		return itemPrices;
	}

	public void setItemPrices(Set<ItemPrice> itemPrices) {
		this.itemPrices = itemPrices;
	}

	public Set<ChannelDistributor> getSubChannelDistributors() {
		return subChannelDistributors;
	}

	public void setSubChannelDistributors(Set<ChannelDistributor> subChannelDistributors) {
		this.subChannelDistributors = subChannelDistributors;
	}

	public Double getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(Double vipPrice) {
		this.vipPrice = vipPrice;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getIsplu() {
		return isplu;
	}

	public void setIsplu(String isplu) {
		this.isplu = isplu;
	}

	public String getPlu() {
		return plu;
	}

	public void setPlu(String plu) {
		this.plu = plu;
	}

	public String getPluMode() {
		return pluMode;
	}

	public void setPluMode(String pluMode) {
		this.pluMode = pluMode;
	}

	public String getMeasureUnitName() {
		if (measureUnit != null) {
			return measureUnit.getName();
		}
		return "";
	}

	public String getItemCatalogCode() {
		if (itemCatalog != null) {
			return itemCatalog.getCodeRule();
		}
		return "";
	}

	public String getMeasureUnitId() {
		if (measureUnit != null) {
			return measureUnit.getId();
		}
		return "";
	}

	public String getRevAccountId() {
		return revAccountId;
	}

	public void setRevAccountId(String revAccountId) {
		this.revAccountId = revAccountId;
	}

	public Double getMobilePrice() {
		return mobilePrice;
	}

	public void setMobilePrice(Double mobilePrice) {
		this.mobilePrice = mobilePrice;
	}

	public Double getAppPrice() {
		return appPrice;
	}

	public void setAppPrice(Double appPrice) {
		this.appPrice = appPrice;
	}

	public Long getCoinExchange() {
		return coinExchange;
	}

	public void setCoinExchange(Long coinExchange) {
		this.coinExchange = coinExchange;
	}

	public Long getGiveIntegral() {
		return giveIntegral;
	}

	public void setGiveIntegral(Long giveIntegral) {
		this.giveIntegral = giveIntegral;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public ItemPackage getItemPackage() {
		return itemPackage;
	}

	public void setItemPackage(ItemPackage itemPackage) {
		this.itemPackage = itemPackage;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	/**
	 * @return the subSkus
	 */
	public Set<Sku> getSubSkus() {
		return subSkus;
	}

	/**
	 * @param subSkus
	 *            the subSkus to set
	 */
	public void setSubSkus(Set<Sku> subSkus) {
		this.subSkus = subSkus;
	}

	public String getIsItem() {
		return isItem;
	}

	public void setIsItem(String isItem) {
		this.isItem = isItem;
	}
}