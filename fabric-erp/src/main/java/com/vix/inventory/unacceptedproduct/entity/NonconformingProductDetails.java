package com.vix.inventory.unacceptedproduct.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 不合格品记录单明细
 */

public class NonconformingProductDetails extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 物料编码
	 */
	private String itemcode;
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
	private Double price;
	/**
	 * 批号
	 */
	private String batchcode;
	/**
	 * 引用订单编号
	 */
	private String poCode;
	/**
	 * 仓库编码
	 */
	private String warehouseCode;
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
	/**
	 * 生产日期
	 */
	private Date producedate;
	/**
	 * 保质期天数
	 */
	private Integer massdate;
	/**
	 * 供应商编码
	 */
	private String suppliercode;
	/**
	 * sku编码
	 */
	private String skuCode;
	/**
	 * 单位
	 */
	private String unit;
	private Integer assitquantity;
	private Double iaprice;
	private Double planunitcost;
	private Double planprice;
	private String costobjectcode;
	private String invouchcode;
	private Integer flag;
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
	private String positions;
	private String projectcatalogcode;
	private String projectcode;
	private String projectname;
	private String projectcatalogname;
	private Integer iensid;
	private String barcode;
	private Integer rpquantity;
	private Integer rpassitquantity;
	private String assitunit;
	private Integer manorderitemcode;
	private Integer checkfromitemcode;
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
	/**
	 * 不合格品记录单
	 */
	private NonconformingProduct nonconformingProduct;

	private InvShelf invShelf;// 货位
	private InvWarehouse invWarehouse;// 仓库

	public NonconformingProductDetails() {
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBatchcode() {
		return batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
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

	public String getBillitemcode() {
		return billitemcode;
	}

	public void setBillitemcode(String billitemcode) {
		this.billitemcode = billitemcode;
	}

	public Integer getSendquantity() {
		return sendquantity;
	}

	public void setSendquantity(Integer sendquantity) {
		this.sendquantity = sendquantity;
	}

	public Integer getSendassitquantity() {
		return sendassitquantity;
	}

	public void setSendassitquantity(Integer sendassitquantity) {
		this.sendassitquantity = sendassitquantity;
	}

	public Date getProducedate() {
		return producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}

	public Integer getMassdate() {
		return massdate;
	}

	public void setMassdate(Integer massdate) {
		this.massdate = massdate;
	}

	public String getSuppliercode() {
		return suppliercode;
	}

	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getAssitquantity() {
		return assitquantity;
	}

	public void setAssitquantity(Integer assitquantity) {
		this.assitquantity = assitquantity;
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

	public String getCostobjectcode() {
		return costobjectcode;
	}

	public void setCostobjectcode(String costobjectcode) {
		this.costobjectcode = costobjectcode;
	}

	public String getInvouchcode() {
		return invouchcode;
	}

	public void setInvouchcode(String invouchcode) {
		this.invouchcode = invouchcode;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getDsdate() {
		return dsdate;
	}

	public void setDsdate(Date dsdate) {
		this.dsdate = dsdate;
	}

	public Integer getTaxamount() {
		return taxamount;
	}

	public void setTaxamount(Integer taxamount) {
		this.taxamount = taxamount;
	}

	public Integer getSumclearingquantity() {
		return sumclearingquantity;
	}

	public void setSumclearingquantity(Integer sumclearingquantity) {
		this.sumclearingquantity = sumclearingquantity;
	}

	public Integer getSumclearingassitquantity() {
		return sumclearingassitquantity;
	}

	public void setSumclearingassitquantity(Integer sumclearingassitquantity) {
		this.sumclearingassitquantity = sumclearingassitquantity;
	}

	public Integer getSumclearingamount() {
		return sumclearingamount;
	}

	public void setSumclearingamount(Integer sumclearingamount) {
		this.sumclearingamount = sumclearingamount;
	}

	public Integer getSumoutquantity() {
		return sumoutquantity;
	}

	public void setSumoutquantity(Integer sumoutquantity) {
		this.sumoutquantity = sumoutquantity;
	}

	public Integer getSumoutassitquantity() {
		return sumoutassitquantity;
	}

	public void setSumoutassitquantity(Integer sumoutassitquantity) {
		this.sumoutassitquantity = sumoutassitquantity;
	}

	public Integer getFactassitquantity() {
		return factassitquantity;
	}

	public void setFactassitquantity(Integer factassitquantity) {
		this.factassitquantity = factassitquantity;
	}

	public Integer getFactquantity() {
		return factquantity;
	}

	public void setFactquantity(Integer factquantity) {
		this.factquantity = factquantity;
	}

	public Date getInvaliddate() {
		return invaliddate;
	}

	public void setInvaliddate(Date invaliddate) {
		this.invaliddate = invaliddate;
	}

	public Integer getTransformitemcode() {
		return transformitemcode;
	}

	public void setTransformitemcode(Integer transformitemcode) {
		this.transformitemcode = transformitemcode;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public String getProjectcatalogcode() {
		return projectcatalogcode;
	}

	public void setProjectcatalogcode(String projectcatalogcode) {
		this.projectcatalogcode = projectcatalogcode;
	}

	public String getProjectcode() {
		return projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectcatalogname() {
		return projectcatalogname;
	}

	public void setProjectcatalogname(String projectcatalogname) {
		this.projectcatalogname = projectcatalogname;
	}

	public Integer getIensid() {
		return iensid;
	}

	public void setIensid(Integer iensid) {
		this.iensid = iensid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getRpquantity() {
		return rpquantity;
	}

	public void setRpquantity(Integer rpquantity) {
		this.rpquantity = rpquantity;
	}

	public Integer getRpassitquantity() {
		return rpassitquantity;
	}

	public void setRpassitquantity(Integer rpassitquantity) {
		this.rpassitquantity = rpassitquantity;
	}

	public String getAssitunit() {
		return assitunit;
	}

	public void setAssitunit(String assitunit) {
		this.assitunit = assitunit;
	}

	public Integer getManorderitemcode() {
		return manorderitemcode;
	}

	public void setManorderitemcode(Integer manorderitemcode) {
		this.manorderitemcode = manorderitemcode;
	}

	public Integer getCheckfromitemcode() {
		return checkfromitemcode;
	}

	public void setCheckfromitemcode(Integer checkfromitemcode) {
		this.checkfromitemcode = checkfromitemcode;
	}

	public String getStockreocrdcode() {
		return stockreocrdcode;
	}

	public void setStockreocrdcode(String stockreocrdcode) {
		this.stockreocrdcode = stockreocrdcode;
	}

	public Integer getIsgsp() {
		return isgsp;
	}

	public void setIsgsp(Integer isgsp) {
		this.isgsp = isgsp;
	}

	public String getGspstate() {
		return gspstate;
	}

	public void setGspstate(String gspstate) {
		this.gspstate = gspstate;
	}

	public Integer getIarrsid() {
		return iarrsid;
	}

	public void setIarrsid(Integer iarrsid) {
		this.iarrsid = iarrsid;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public Integer getCheckidbaks() {
		return checkidbaks;
	}

	public void setCheckidbaks(Integer checkidbaks) {
		this.checkidbaks = checkidbaks;
	}

	public String getRejectcode() {
		return rejectcode;
	}

	public void setRejectcode(String rejectcode) {
		this.rejectcode = rejectcode;
	}

	public Integer getRejectids() {
		return rejectids;
	}

	public void setRejectids(Integer rejectids) {
		this.rejectids = rejectids;
	}

	public String getCheckpersoncode() {
		return checkpersoncode;
	}

	public void setCheckpersoncode(String checkpersoncode) {
		this.checkpersoncode = checkpersoncode;
	}

	public Date getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public Integer getOriginaltaxcost() {
		return originaltaxcost;
	}

	public void setOriginaltaxcost(Integer originaltaxcost) {
		this.originaltaxcost = originaltaxcost;
	}

	public Integer getOriginalicost() {
		return originalicost;
	}

	public void setOriginalicost(Integer originalicost) {
		this.originalicost = originalicost;
	}

	public Integer getOriginalprice() {
		return originalprice;
	}

	public void setOriginalprice(Integer originalprice) {
		this.originalprice = originalprice;
	}

	public Integer getOriginaltaxprice() {
		return originaltaxprice;
	}

	public void setOriginaltaxprice(Integer originaltaxprice) {
		this.originaltaxprice = originaltaxprice;
	}

	public Integer getIorisum() {
		return iorisum;
	}

	public void setIorisum(Integer iorisum) {
		this.iorisum = iorisum;
	}

	public Integer getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Integer taxrate) {
		this.taxrate = taxrate;
	}

	public Integer getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Integer taxprice) {
		this.taxprice = taxprice;
	}

	public Integer getSumamount() {
		return sumamount;
	}

	public void setSumamount(Integer sumamount) {
		this.sumamount = sumamount;
	}

	public Integer getTaxstyle() {
		return taxstyle;
	}

	public void setTaxstyle(Integer taxstyle) {
		this.taxstyle = taxstyle;
	}

	public String getPurchaseordercode() {
		return purchaseordercode;
	}

	public void setPurchaseordercode(String purchaseordercode) {
		this.purchaseordercode = purchaseordercode;
	}

	public Integer getMassunit() {
		return massunit;
	}

	public void setMassunit(Integer massunit) {
		this.massunit = massunit;
	}

	public String getMolotcode() {
		return molotcode;
	}

	public void setMolotcode(String molotcode) {
		this.molotcode = molotcode;
	}

	public Integer getRefundinspectflag() {
		return refundinspectflag;
	}

	public void setRefundinspectflag(Integer refundinspectflag) {
		this.refundinspectflag = refundinspectflag;
	}

	public Integer getMaterialfee() {
		return materialfee;
	}

	public void setMaterialfee(Integer materialfee) {
		this.materialfee = materialfee;
	}

	public Integer getProcesscost() {
		return processcost;
	}

	public void setProcesscost(Integer processcost) {
		this.processcost = processcost;
	}

	public Integer getProcessfee() {
		return processfee;
	}

	public void setProcessfee(Integer processfee) {
		this.processfee = processfee;
	}

	public Date getDmsdate() {
		return dmsdate;
	}

	public void setDmsdate(Date dmsdate) {
		this.dmsdate = dmsdate;
	}

	public Integer getSummaterialfee() {
		return summaterialfee;
	}

	public void setSummaterialfee(Integer summaterialfee) {
		this.summaterialfee = summaterialfee;
	}

	public Integer getSumprocessfee() {
		return sumprocessfee;
	}

	public void setSumprocessfee(Integer sumprocessfee) {
		this.sumprocessfee = sumprocessfee;
	}

	public Integer getIomodid() {
		return iomodid;
	}

	public void setIomodid(Integer iomodid) {
		this.iomodid = iomodid;
	}

	public Integer getSaleorderitemid() {
		return saleorderitemid;
	}

	public void setSaleorderitemid(Integer saleorderitemid) {
		this.saleorderitemid = saleorderitemid;
	}

	public String getContractid() {
		return contractid;
	}

	public void setContractid(String contractid) {
		this.contractid = contractid;
	}

	public String getContractcode() {
		return contractcode;
	}

	public void setContractcode(String contractcode) {
		this.contractcode = contractcode;
	}

	public Integer getBfilled() {
		return bfilled;
	}

	public void setBfilled(Integer bfilled) {
		this.bfilled = bfilled;
	}

	public Integer getBchecked() {
		return bchecked;
	}

	public void setBchecked(Integer bchecked) {
		this.bchecked = bchecked;
	}

	public Integer getBrelated() {
		return brelated;
	}

	public void setBrelated(Integer brelated) {
		this.brelated = brelated;
	}

	public String getIomomid() {
		return iomomid;
	}

	public void setIomomid(String iomomid) {
		this.iomomid = iomomid;
	}

	public NonconformingProduct getNonconformingProduct() {
		return nonconformingProduct;
	}

	public void setNonconformingProduct(NonconformingProduct nonconformingProduct) {
		this.nonconformingProduct = nonconformingProduct;
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

}