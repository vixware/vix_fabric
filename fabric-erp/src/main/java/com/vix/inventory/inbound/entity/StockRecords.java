package com.vix.inventory.inbound.entity;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.inventory.inventorytype.entity.InventoryType;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.sales.order.entity.SalesOrder;

/**
 * 出入库记录单
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.inventory.inbound.entity.StockRecords
 *
 * @date 2018年2月2日
 */

public class StockRecords extends BaseBOEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 出入库标志
	 */
	private String flag;
	/**
	 * 单据类型编码
	 */
	private String bizformtype;
	/**
	 * 业务类型 1 ：采购入库单, 0 ：产成品入库单, 2：其他入库单,3:红字入库单,4:期初入库单
	 */
	private String biztype;
	/**
	 * 类型 1,总部单据 2,门店单据
	 */
	private String type;
	/**
	 * 单据来源
	 */
	private String bizsource;
	/**
	 * 对应业务单号
	 */
	private String bizcode;
	/**
	 * 仓库编码
	 */
	private String warehousecode;
	/**
	 * 库管员
	 */
	private String warehousePerson;
	/**
	 * 收发单据号
	 */
	private String formcode;
	/**
	 * 出入库类别编码
	 */
	private String bizclasscode;
	/**
	 * 业务员编码
	 */
	private String personcode;
	/**
	 * 采购类型编码
	 */
	private String purchasetypecode;
	/**
	 * 销售类型编码
	 */
	private String saletypecode;
	/**
	 * 客户编码
	 */
	private String customercode;
	/**
	 * 供应商编码
	 */
	private String sppliercode;
	/**
	 * 采购订单号
	 */
	private String pocode;
	/**
	 * 采购到货单号
	 */
	private String purchasearrivalcode;
	private String purchasebillcode;
	private String purchasereturnformcode;
	private String producebatch;
	private String checker;
	private String istransflag;
	private String accounter;
	private String maker;
	private Boolean netlock;
	private String keepdate;
	private Date checkdate;
	private String bpufirst;
	private String biafirst;
	private Integer producequantity;
	private Date arrivaldate;
	private String checkformcode;
	private Date checkingdate;
	/**
	 * 检验员
	 */
	private String checkperson;
	private String templatecode;
	private String bisstqc;
	/**
	 * 父项产品编码
	 */
	private String parentitemcode;
	/**
	 * 生产订单编号
	 */
	private String manufactureordercode;
	private String gspcheck;
	private String purchaseordercode;
	private String pruchasearrivalcode;
	private String mocode;
	private String returnformcode;
	/**
	 * 发票号
	 */
	private String billcode;
	private Date ufts;
	private Long taxrate;
	private Integer exchangerate;
	private String echangecurrencyname;
	/**
	 * 发货地址
	 */
	private String shipaddress;
	private Integer bomfirst;

	private Integer deliveryType; // 出库类型
	private Date deliveryTime; // 出库时间
	private Integer tradeClass; // 0普通出库单，1团购优惠 ，2批发订单
	private Long amount; // 出库总件数
	private Double totalWeight; // 出库总重量
	private Long createdStaffId; // 单据创建人
	private Long confirmStaffId; // 单据审核人
	private Date confirmTime; // 审核时间
	private String tradeNo;
	private String pickingNo; // 拣货单编号
	private Integer packageNum; // 包裹数量
	private Double shippingFee; // 物流费用
	private String corpCode; // 物流公司编码
	private String shippingOutsId; // 物流单号
	private Date datetime;
	private String wareAddress; // 库房地址
	private String staffIdName; // 创建者名字
	private String confirmStaffName; //
	private String pickerName; // 提货人
	private Double totalAmount;// 出库总金额
	private InventoryType inventoryType;
	/**
	 * 商城订单
	 */
	private Order order;
	/**
	 * 分拣批次
	 */
	private OrderBatch orderBatch;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;
	/**
	 * 入库单明细
	 */
	private Set<StockRecordLines> subStockRecordLines = new HashSet<StockRecordLines>();
	/**
	 * 销售订单
	 */
	private PurchaseOrder purchaseOrder;

	private SalesOrder salesOrder;
	/**
	 * 是否进行入库操作:0,否;1,是
	 */
	private String isInventory;

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBizformtype() {
		return this.bizformtype;
	}

	public void setBizformtype(String bizformtype) {
		this.bizformtype = bizformtype;
	}

	public String getBiztype() {
		return this.biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public String getBizsource() {
		return this.bizsource;
	}

	public void setBizsource(String bizsource) {
		this.bizsource = bizsource;
	}

	public String getBizcode() {
		return this.bizcode;
	}

	public void setBizcode(String bizcode) {
		this.bizcode = bizcode;
	}

	public String getWarehousecode() {
		return this.warehousecode;
	}

	public void setWarehousecode(String warehousecode) {
		this.warehousecode = warehousecode;
	}

	public String getWarehousePerson() {
		return warehousePerson;
	}

	public void setWarehousePerson(String warehousePerson) {
		this.warehousePerson = warehousePerson;
	}

	public String getFormcode() {
		return this.formcode;
	}

	public void setFormcode(String formcode) {
		this.formcode = formcode;
	}

	public String getBizclasscode() {
		return this.bizclasscode;
	}

	public void setBizclasscode(String bizclasscode) {
		this.bizclasscode = bizclasscode;
	}

	public String getPersoncode() {
		return this.personcode;
	}

	public void setPersoncode(String personcode) {
		this.personcode = personcode;
	}

	public String getPurchasetypecode() {
		return this.purchasetypecode;
	}

	public void setPurchasetypecode(String purchasetypecode) {
		this.purchasetypecode = purchasetypecode;
	}

	public String getSaletypecode() {
		return this.saletypecode;
	}

	public void setSaletypecode(String saletypecode) {
		this.saletypecode = saletypecode;
	}

	public String getCustomercode() {
		return this.customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getSppliercode() {
		return this.sppliercode;
	}

	public void setSppliercode(String sppliercode) {
		this.sppliercode = sppliercode;
	}

	public String getPocode() {
		return this.pocode;
	}

	public void setPocode(String pocode) {
		this.pocode = pocode;
	}

	public String getPurchasearrivalcode() {
		return this.purchasearrivalcode;
	}

	public void setPurchasearrivalcode(String purchasearrivalcode) {
		this.purchasearrivalcode = purchasearrivalcode;
	}

	public String getPurchasebillcode() {
		return this.purchasebillcode;
	}

	public void setPurchasebillcode(String purchasebillcode) {
		this.purchasebillcode = purchasebillcode;
	}

	public String getPurchasereturnformcode() {
		return this.purchasereturnformcode;
	}

	public void setPurchasereturnformcode(String purchasereturnformcode) {
		this.purchasereturnformcode = purchasereturnformcode;
	}

	public String getProducebatch() {
		return this.producebatch;
	}

	public void setProducebatch(String producebatch) {
		this.producebatch = producebatch;
	}

	/**
	 * @return the pickerName
	 */
	public String getPickerName() {
		return pickerName;
	}

	/**
	 * @param pickerName
	 *            the pickerName to set
	 */
	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}

	public String getChecker() {
		return this.checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getIstransflag() {
		return this.istransflag;
	}

	public void setIstransflag(String istransflag) {
		this.istransflag = istransflag;
	}

	public String getAccounter() {
		return this.accounter;
	}

	public void setAccounter(String accounter) {
		this.accounter = accounter;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getNetlock() {
		return this.netlock;
	}

	public void setNetlock(Boolean netlock) {
		this.netlock = netlock;
	}

	public String getKeepdate() {
		return this.keepdate;
	}

	public void setKeepdate(String keepdate) {
		this.keepdate = keepdate;
	}

	public Date getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public String getBpufirst() {
		return this.bpufirst;
	}

	public void setBpufirst(String bpufirst) {
		this.bpufirst = bpufirst;
	}

	public String getBiafirst() {
		return this.biafirst;
	}

	public void setBiafirst(String biafirst) {
		this.biafirst = biafirst;
	}

	public Integer getProducequantity() {
		return this.producequantity;
	}

	public void setProducequantity(Integer producequantity) {
		this.producequantity = producequantity;
	}

	public Date getArrivaldate() {
		return this.arrivaldate;
	}

	public void setArrivaldate(Date arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public String getCheckformcode() {
		return this.checkformcode;
	}

	public void setCheckformcode(String checkformcode) {
		this.checkformcode = checkformcode;
	}

	public Date getCheckingdate() {
		return this.checkingdate;
	}

	public void setCheckingdate(Date checkingdate) {
		this.checkingdate = checkingdate;
	}

	public String getCheckperson() {
		return this.checkperson;
	}

	public void setCheckperson(String checkperson) {
		this.checkperson = checkperson;
	}

	public String getTemplatecode() {
		return this.templatecode;
	}

	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}

	public String getBisstqc() {
		return this.bisstqc;
	}

	public void setBisstqc(String bisstqc) {
		this.bisstqc = bisstqc;
	}

	public String getParentitemcode() {
		return this.parentitemcode;
	}

	public void setParentitemcode(String parentitemcode) {
		this.parentitemcode = parentitemcode;
	}

	public String getManufactureordercode() {
		return this.manufactureordercode;
	}

	public void setManufactureordercode(String manufactureordercode) {
		this.manufactureordercode = manufactureordercode;
	}

	public String getGspcheck() {
		return this.gspcheck;
	}

	public void setGspcheck(String gspcheck) {
		this.gspcheck = gspcheck;
	}

	public String getPurchaseordercode() {
		return this.purchaseordercode;
	}

	public void setPurchaseordercode(String purchaseordercode) {
		this.purchaseordercode = purchaseordercode;
	}

	public String getPruchasearrivalcode() {
		return this.pruchasearrivalcode;
	}

	public void setPruchasearrivalcode(String pruchasearrivalcode) {
		this.pruchasearrivalcode = pruchasearrivalcode;
	}

	public String getMocode() {
		return this.mocode;
	}

	public void setMocode(String mocode) {
		this.mocode = mocode;
	}

	public String getReturnformcode() {
		return this.returnformcode;
	}

	public void setReturnformcode(String returnformcode) {
		this.returnformcode = returnformcode;
	}

	public String getBillcode() {
		return this.billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public Date getUfts() {
		return this.ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public Long getTaxrate() {
		return this.taxrate;
	}

	public void setTaxrate(Long taxrate) {
		this.taxrate = taxrate;
	}

	public Integer getExchangerate() {
		return this.exchangerate;
	}

	public void setExchangerate(Integer exchangerate) {
		this.exchangerate = exchangerate;
	}

	public String getEchangecurrencyname() {
		return this.echangecurrencyname;
	}

	public void setEchangecurrencyname(String echangecurrencyname) {
		this.echangecurrencyname = echangecurrencyname;
	}

	public String getShipaddress() {
		return this.shipaddress;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	public Integer getBomfirst() {
		return this.bomfirst;
	}

	public void setBomfirst(Integer bomfirst) {
		this.bomfirst = bomfirst;
	}

	public Set<StockRecordLines> getSubStockRecordLines() {
		return subStockRecordLines;
	}

	public void setSubStockRecordLines(Set<StockRecordLines> subStockRecordLines) {
		this.subStockRecordLines = subStockRecordLines;
	}

	public String getWarehouseName() {
		if (invWarehouse != null) {
			return invWarehouse.getName();
		}
		return "";
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getTradeClass() {
		return tradeClass;
	}

	public void setTradeClass(Integer tradeClass) {
		this.tradeClass = tradeClass;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Long getCreatedStaffId() {
		return createdStaffId;
	}

	public void setCreatedStaffId(Long createdStaffId) {
		this.createdStaffId = createdStaffId;
	}

	public Long getConfirmStaffId() {
		return confirmStaffId;
	}

	public void setConfirmStaffId(Long confirmStaffId) {
		this.confirmStaffId = confirmStaffId;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPickingNo() {
		return pickingNo;
	}

	public void setPickingNo(String pickingNo) {
		this.pickingNo = pickingNo;
	}

	public Integer getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}

	public Double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getShippingOutsId() {
		return shippingOutsId;
	}

	public void setShippingOutsId(String shippingOutsId) {
		this.shippingOutsId = shippingOutsId;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getWareAddress() {
		return wareAddress;
	}

	public void setWareAddress(String wareAddress) {
		this.wareAddress = wareAddress;
	}

	public String getStaffIdName() {
		return staffIdName;
	}

	public void setStaffIdName(String staffIdName) {
		this.staffIdName = staffIdName;
	}

	public String getConfirmStaffName() {
		return confirmStaffName;
	}

	public void setConfirmStaffName(String confirmStaffName) {
		this.confirmStaffName = confirmStaffName;
	}

	public Double getTotalAmount() {
		if (totalAmount != null) {
			DecimalFormat df = new DecimalFormat(".##");
			String st = df.format(totalAmount);
			return Double.parseDouble(st);
		} else {
			return 0D;
		}
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderBatch getOrderBatch() {
		return orderBatch;
	}

	public void setOrderBatch(OrderBatch orderBatch) {
		this.orderBatch = orderBatch;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	/**
	 * @return the purchaseOrder
	 */
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	/**
	 * @param purchaseOrder
	 *            the purchaseOrder to set
	 */
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	/**
	 * @return the salesOrder
	 */
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	/**
	 * @param salesOrder
	 *            the salesOrder to set
	 */
	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	/**
	 * @return the isInventory
	 */
	public String getIsInventory() {
		return isInventory;
	}

	/**
	 * @param isInventory
	 *            the isInventory to set
	 */
	public void setIsInventory(String isInventory) {
		this.isInventory = isInventory;
	}

}