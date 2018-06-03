package com.vix.inventory.inbound.entity;

import java.text.DecimalFormat;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.utils.DateUtil;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.entity.Products;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 出入库记录单明细
 * 
 * @类全名 com.vix.inventory.inbound.entity.StockRecordLines
 *
 * @author zhanghaibing
 *
 * @date 2016年9月18日
 */

public class StockRecordLines extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stockrecordcode;
	/**
	 * 物料编码
	 */
	private String itemcode;
	/**
	 * 货位名称
	 */
	private String invshelfname;
	/**
	 * 物料名称
	 */
	private String itemname;
	/**
	 * 物料类型
	 */
	private String itemtype;
	/**
	 * 规格型号
	 */
	private String specification;
	private Integer assitquantity;
	/**
	 * 数量
	 */
	private Double quantity;
	/**
	 * 单价
	 */
	private Double unitcost;
	/**
	 * 金额
	 */
	private Double price = 0D;
	private Double iaprice;
	private Double planunitcost;
	private Double planprice;
	/**
	 * 批号
	 */
	private String batchcode;
	private String costobjectcode;
	private String invouchcode;
	/**
	 * 出入库标志
	 */
	private String flag;
	private Date dsdate;
	private Integer taxamount;
	private Integer sumclearingquantity;
	private Integer sumclearingassitquantity;
	private Integer sumclearingamount;
	private Integer sumoutquantity;
	private Integer sumoutassitquantity;
	private Integer factassitquantity;
	private Integer factquantity;
	private Date invaliddate;
	private Integer transformitemcode;
	/**
	 * 货位编码
	 */
	private String positions;
	private String projectcatalogcode;
	private String projectcode;
	/** 引用订单编号 */
	private String poCode;
	/**
	 * 仓库编码
	 */
	private String warehouseCode;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 采购订单子表标识
	 */
	private String purchaseOrderItemCode;
	/**
	 * 暂估单价
	 */
	private Double facost;
	/**
	 * 发货退货单子表标识
	 */
	private String returnformitemcode;
	/**
	 * 发票子表标识
	 */
	private String billitemcode;
	/**
	 * 本次发货数量
	 */
	private Integer sendquantity;
	/**
	 * 本次发货辅计量数量
	 */
	private Integer sendassitquantity;
	private String projectname;
	private String projectcatalogname;
	private Integer iensid;
	private Integer rpquantity;
	private Integer rpassitquantity;
	private String assitunit;
	/**
	 * 生产日期
	 */
	private Date producedate;
	/**
	 * 到期日
	 */
	private Date massunitEndTime;
	/**
	 * 保质期天数
	 */
	private Integer massdate;
	private Integer manorderitemcode;
	private Integer checkfromitemcode;
	/**
	 * 供应商编码
	 */
	private String suppliercode;
	private String stockreocrdcode;
	private Integer isgsp;
	private String gspstate;
	private Integer iarrsid;
	private String checkcode;
	private Integer checkidbaks;
	private String rejectcode;
	private Integer rejectids;
	private String checkpersoncode;
	private Date checkdate;
	private Integer originaltaxcost;
	private Integer originalicost;
	private Integer originalprice;
	private Integer originaltaxprice;
	private Integer iorisum;
	private Integer taxrate;
	private Integer taxprice;
	private Integer sumamount;
	private Integer taxstyle;
	private String purchaseordercode;
	private Integer massunit;
	private String molotcode;
	private Integer refundinspectflag;
	private Integer materialfee;
	private Integer processcost;
	private Integer processfee;
	private Date dmsdate;
	private Integer summaterialfee;
	private Integer sumprocessfee;
	private Integer iomodid;
	private Integer saleorderitemid;
	private String contractid;
	private String contractcode;
	private Integer bfilled;
	private Integer bchecked;
	private Integer brelated;
	private String iomomid;
	// 是否更新库存 1,未更新 2,已更新
	private String isUpdateStore;
	/**
	 * sku编码
	 */
	private String skuCode;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 入库单
	 */
	private StockRecords stockRecords;
	/* 批次主文件 */
	private InvMainBatch invMainBatch;
	/**
	 * 计量单位
	 */
	private MeasureUnit measureUnit;

	// private Long brandId; // 品牌标识
	// private String brandName; // 品牌名称
	private String bn; // 商品编码
	private String productName; // 货品名称
	private String productSpec; // 货品规格
	private Long amount; // 出库件数
	private Double productCost; // 出库总成本
	// 用于出库单显示
	private String spec;// 规格
	private Double weight;// 重量
	private String name; // 库存货品名称
	private String channelProductName;
	private Integer itemNum;
	private Integer store; //
	private String barCode; // 条形码
	private Integer finalNum; // 实际出库数
	private String finalBarCode;// 出库条形码
	private OrderDetail orderDetail;
	private Products products; // 货品
	private InvShelf invShelf;// 货位
	private InvWarehouse invWarehouse;// 仓库
	/**
	 * 供应商
	 */
	private Supplier supplier;

	public StockRecordLines() {
	}

	public String getStockrecordcode() {
		return this.stockrecordcode;
	}

	/**
	 * 货位名称
	 * 
	 * @return
	 */
	public String getInvShelfName() {
		if (invShelf != null) {
			return invShelf.getName();
		}
		return "";
	}

	public String getInvWarehouseName() {
		if (invWarehouse != null) {
			return invWarehouse.getName();
		}
		return "";
	}

	public String getInvshelfname() {
		return invshelfname;
	}

	public void setInvshelfname(String invshelfname) {
		this.invshelfname = invshelfname;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	/**
	 * 获取供应商名称
	 * 
	 * @return
	 */
	public String getSupplierName() {
		if (supplier != null) {
			return supplier.getName();
		}
		return "";
	}

	public String getMassunitEndTimeTimeStr() {
		if (null != massunitEndTime) {
			return DateUtil.formatTime(massunitEndTime);
		}
		return "";
	}

	public String getMassunitEndTimeStr() {
		if (null != massunitEndTime) {
			return DateUtil.format(massunitEndTime);
		}
		return "";
	}

	public void setStockrecordcode(String stockrecordcode) {
		this.stockrecordcode = stockrecordcode;
	}

	public String getItemcode() {
		return this.itemcode;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public Date getMassunitEndTime() {
		return massunitEndTime;
	}

	public void setMassunitEndTime(Date massunitEndTime) {
		this.massunitEndTime = massunitEndTime;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemtype() {
		return this.itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Integer getAssitquantity() {
		return this.assitquantity;
	}

	public void setAssitquantity(Integer assitquantity) {
		this.assitquantity = assitquantity;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(Double unitcost) {
		this.unitcost = unitcost;
	}

	public Double getPrice() {
		if (null != unitcost && null != quantity && unitcost > 0 && quantity > 0) {
			Double pm = unitcost * quantity;
			DecimalFormat df = new DecimalFormat(".##");
			String st = df.format(pm);
			return Double.parseDouble(st);
		} else {
			return price;
		}
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getIaprice() {
		return iaprice;
	}

	public void setIaprice(Double iaprice) {
		this.iaprice = iaprice;
	}

	public Double getPlanunitcost() {
		return planunitcost;
	}

	public void setPlanunitcost(Double planunitcost) {
		this.planunitcost = planunitcost;
	}

	public Double getPlanprice() {
		return planprice;
	}

	public void setPlanprice(Double planprice) {
		this.planprice = planprice;
	}

	public String getBatchcode() {
		return this.batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

	public String getCostobjectcode() {
		return this.costobjectcode;
	}

	public void setCostobjectcode(String costobjectcode) {
		this.costobjectcode = costobjectcode;
	}

	public String getInvouchcode() {
		return this.invouchcode;
	}

	public void setInvouchcode(String invouchcode) {
		this.invouchcode = invouchcode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getDsdate() {
		return this.dsdate;
	}

	public void setDsdate(Date dsdate) {
		this.dsdate = dsdate;
	}

	public Integer getTaxamount() {
		return this.taxamount;
	}

	public void setTaxamount(Integer taxamount) {
		this.taxamount = taxamount;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public Integer getSumclearingquantity() {
		return this.sumclearingquantity;
	}

	public void setSumclearingquantity(Integer sumclearingquantity) {
		this.sumclearingquantity = sumclearingquantity;
	}

	public Integer getSumclearingassitquantity() {
		return this.sumclearingassitquantity;
	}

	public void setSumclearingassitquantity(Integer sumclearingassitquantity) {
		this.sumclearingassitquantity = sumclearingassitquantity;
	}

	public Integer getSumclearingamount() {
		return this.sumclearingamount;
	}

	public void setSumclearingamount(Integer sumclearingamount) {
		this.sumclearingamount = sumclearingamount;
	}

	public Integer getSumoutquantity() {
		return this.sumoutquantity;
	}

	public void setSumoutquantity(Integer sumoutquantity) {
		this.sumoutquantity = sumoutquantity;
	}

	public Integer getSumoutassitquantity() {
		return this.sumoutassitquantity;
	}

	public void setSumoutassitquantity(Integer sumoutassitquantity) {
		this.sumoutassitquantity = sumoutassitquantity;
	}

	public Integer getFactassitquantity() {
		return this.factassitquantity;
	}

	public void setFactassitquantity(Integer factassitquantity) {
		this.factassitquantity = factassitquantity;
	}

	public Integer getFactquantity() {
		return this.factquantity;
	}

	public void setFactquantity(Integer factquantity) {
		this.factquantity = factquantity;
	}

	public Date getInvaliddate() {
		return this.invaliddate;
	}

	public void setInvaliddate(Date invaliddate) {
		this.invaliddate = invaliddate;
	}

	public Integer getTransformitemcode() {
		return this.transformitemcode;
	}

	public void setTransformitemcode(Integer transformitemcode) {
		this.transformitemcode = transformitemcode;
	}

	public String getPositions() {
		return this.positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public String getProjectcatalogcode() {
		return this.projectcatalogcode;
	}

	public void setProjectcatalogcode(String projectcatalogcode) {
		this.projectcatalogcode = projectcatalogcode;
	}

	public String getPurchaseOrderItemCode() {
		return purchaseOrderItemCode;
	}

	public void setPurchaseOrderItemCode(String purchaseOrderItemCode) {
		this.purchaseOrderItemCode = purchaseOrderItemCode;
	}

	public Double getFacost() {
		return facost;
	}

	public void setFacost(Double facost) {
		this.facost = facost;
	}

	public String getReturnformitemcode() {
		return returnformitemcode;
	}

	public void setReturnformitemcode(String returnformitemcode) {
		this.returnformitemcode = returnformitemcode;
	}

	public String getProjectcode() {
		return this.projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getBillitemcode() {
		return billitemcode;
	}

	public void setBillitemcode(String billitemcode) {
		this.billitemcode = billitemcode;
	}

	public Integer getSendquantity() {
		return this.sendquantity;
	}

	public void setSendquantity(Integer sendquantity) {
		this.sendquantity = sendquantity;
	}

	public Integer getSendassitquantity() {
		return this.sendassitquantity;
	}

	public void setSendassitquantity(Integer sendassitquantity) {
		this.sendassitquantity = sendassitquantity;
	}

	public String getProjectname() {
		return this.projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectcatalogname() {
		return this.projectcatalogname;
	}

	public void setProjectcatalogname(String projectcatalogname) {
		this.projectcatalogname = projectcatalogname;
	}

	public Integer getIensid() {
		return this.iensid;
	}

	public void setIensid(Integer iensid) {
		this.iensid = iensid;
	}

	public Integer getRpquantity() {
		return this.rpquantity;
	}

	public void setRpquantity(Integer rpquantity) {
		this.rpquantity = rpquantity;
	}

	public Integer getRpassitquantity() {
		return this.rpassitquantity;
	}

	public void setRpassitquantity(Integer rpassitquantity) {
		this.rpassitquantity = rpassitquantity;
	}

	public String getAssitunit() {
		return this.assitunit;
	}

	public void setAssitunit(String assitunit) {
		this.assitunit = assitunit;
	}

	public Date getProducedate() {
		return this.producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}

	public Integer getMassdate() {
		return this.massdate;
	}

	public void setMassdate(Integer massdate) {
		this.massdate = massdate;
	}

	public Integer getManorderitemcode() {
		return this.manorderitemcode;
	}

	public void setManorderitemcode(Integer manorderitemcode) {
		this.manorderitemcode = manorderitemcode;
	}

	public Integer getCheckfromitemcode() {
		return this.checkfromitemcode;
	}

	public void setCheckfromitemcode(Integer checkfromitemcode) {
		this.checkfromitemcode = checkfromitemcode;
	}

	public String getSuppliercode() {
		return this.suppliercode;
	}

	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}

	public String getStockreocrdcode() {
		return this.stockreocrdcode;
	}

	public void setStockreocrdcode(String stockreocrdcode) {
		this.stockreocrdcode = stockreocrdcode;
	}

	public Integer getIsgsp() {
		return this.isgsp;
	}

	public void setIsgsp(Integer isgsp) {
		this.isgsp = isgsp;
	}

	public String getGspstate() {
		return this.gspstate;
	}

	public void setGspstate(String gspstate) {
		this.gspstate = gspstate;
	}

	public Integer getIarrsid() {
		return this.iarrsid;
	}

	public void setIarrsid(Integer iarrsid) {
		this.iarrsid = iarrsid;
	}

	public String getCheckcode() {
		return this.checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public Integer getCheckidbaks() {
		return this.checkidbaks;
	}

	public void setCheckidbaks(Integer checkidbaks) {
		this.checkidbaks = checkidbaks;
	}

	public String getRejectcode() {
		return this.rejectcode;
	}

	public void setRejectcode(String rejectcode) {
		this.rejectcode = rejectcode;
	}

	public Integer getRejectids() {
		return this.rejectids;
	}

	public void setRejectids(Integer rejectids) {
		this.rejectids = rejectids;
	}

	public String getCheckpersoncode() {
		return this.checkpersoncode;
	}

	public void setCheckpersoncode(String checkpersoncode) {
		this.checkpersoncode = checkpersoncode;
	}

	public Date getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public Integer getOriginaltaxcost() {
		return this.originaltaxcost;
	}

	public void setOriginaltaxcost(Integer originaltaxcost) {
		this.originaltaxcost = originaltaxcost;
	}

	public Integer getOriginalicost() {
		return this.originalicost;
	}

	public void setOriginalicost(Integer originalicost) {
		this.originalicost = originalicost;
	}

	public Integer getOriginalprice() {
		return this.originalprice;
	}

	public void setOriginalprice(Integer originalprice) {
		this.originalprice = originalprice;
	}

	public Integer getOriginaltaxprice() {
		return this.originaltaxprice;
	}

	public void setOriginaltaxprice(Integer originaltaxprice) {
		this.originaltaxprice = originaltaxprice;
	}

	public Integer getIorisum() {
		return this.iorisum;
	}

	public void setIorisum(Integer iorisum) {
		this.iorisum = iorisum;
	}

	public Integer getTaxrate() {
		return this.taxrate;
	}

	public void setTaxrate(Integer taxrate) {
		this.taxrate = taxrate;
	}

	public Integer getTaxprice() {
		return this.taxprice;
	}

	public void setTaxprice(Integer taxprice) {
		this.taxprice = taxprice;
	}

	public Integer getSumamount() {
		return this.sumamount;
	}

	public void setSumamount(Integer sumamount) {
		this.sumamount = sumamount;
	}

	public Integer getTaxstyle() {
		return this.taxstyle;
	}

	public void setTaxstyle(Integer taxstyle) {
		this.taxstyle = taxstyle;
	}

	public String getPurchaseordercode() {
		return this.purchaseordercode;
	}

	public void setPurchaseordercode(String purchaseordercode) {
		this.purchaseordercode = purchaseordercode;
	}

	public Integer getMassunit() {
		return this.massunit;
	}

	public void setMassunit(Integer massunit) {
		this.massunit = massunit;
	}

	public String getMolotcode() {
		return this.molotcode;
	}

	public void setMolotcode(String molotcode) {
		this.molotcode = molotcode;
	}

	public Integer getRefundinspectflag() {
		return this.refundinspectflag;
	}

	public void setRefundinspectflag(Integer refundinspectflag) {
		this.refundinspectflag = refundinspectflag;
	}

	public Integer getMaterialfee() {
		return this.materialfee;
	}

	public void setMaterialfee(Integer materialfee) {
		this.materialfee = materialfee;
	}

	public Integer getProcesscost() {
		return this.processcost;
	}

	public void setProcesscost(Integer processcost) {
		this.processcost = processcost;
	}

	public Integer getProcessfee() {
		return this.processfee;
	}

	public void setProcessfee(Integer processfee) {
		this.processfee = processfee;
	}

	public Date getDmsdate() {
		return this.dmsdate;
	}

	public void setDmsdate(Date dmsdate) {
		this.dmsdate = dmsdate;
	}

	public Integer getSummaterialfee() {
		return this.summaterialfee;
	}

	public void setSummaterialfee(Integer summaterialfee) {
		this.summaterialfee = summaterialfee;
	}

	public Integer getSumprocessfee() {
		return this.sumprocessfee;
	}

	public void setSumprocessfee(Integer sumprocessfee) {
		this.sumprocessfee = sumprocessfee;
	}

	public Integer getIomodid() {
		return this.iomodid;
	}

	public void setIomodid(Integer iomodid) {
		this.iomodid = iomodid;
	}

	public Integer getSaleorderitemid() {
		return this.saleorderitemid;
	}

	public void setSaleorderitemid(Integer saleorderitemid) {
		this.saleorderitemid = saleorderitemid;
	}

	public String getContractid() {
		return this.contractid;
	}

	public void setContractid(String contractid) {
		this.contractid = contractid;
	}

	public String getContractcode() {
		return this.contractcode;
	}

	public void setContractcode(String contractcode) {
		this.contractcode = contractcode;
	}

	public Integer getBfilled() {
		return this.bfilled;
	}

	public void setBfilled(Integer bfilled) {
		this.bfilled = bfilled;
	}

	public Integer getBchecked() {
		return this.bchecked;
	}

	public void setBchecked(Integer bchecked) {
		this.bchecked = bchecked;
	}

	public Integer getBrelated() {
		return this.brelated;
	}

	public void setBrelated(Integer brelated) {
		this.brelated = brelated;
	}

	public String getIomomid() {
		return this.iomomid;
	}

	public void setIomomid(String iomomid) {
		this.iomomid = iomomid;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public StockRecords getStockRecords() {
		return stockRecords;
	}

	public void setStockRecords(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
	}

	public InvMainBatch getInvMainBatch() {
		return invMainBatch;
	}

	public void setInvMainBatch(InvMainBatch invMainBatch) {
		this.invMainBatch = invMainBatch;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Double getProductCost() {
		return productCost;
	}

	public void setProductCost(Double productCost) {
		this.productCost = productCost;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getChannelProductName() {
		return channelProductName;
	}

	public void setChannelProductName(String channelProductName) {
		this.channelProductName = channelProductName;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Integer getFinalNum() {
		return finalNum;
	}

	public void setFinalNum(Integer finalNum) {
		this.finalNum = finalNum;
	}

	public String getFinalBarCode() {
		return finalBarCode;
	}

	public void setFinalBarCode(String finalBarCode) {
		this.finalBarCode = finalBarCode;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public InvShelf getInvShelf() {
		return invShelf;
	}

	public void setInvShelf(InvShelf invShelf) {
		this.invShelf = invShelf;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public String getIsUpdateStore() {
		return isUpdateStore;
	}

	public void setIsUpdateStore(String isUpdateStore) {
		this.isUpdateStore = isUpdateStore;
	}

}