package com.vix.inventory.standingbook.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.inventory.standingbook.entity.InventoryCurrentStock
 *
 * @date 2018年1月2日
 */

public class InventoryCurrentStock extends BaseEntity {

	/**
	 * status  0,正常     1,即将到期   2,已过期   3,库存不足   4,库存超限  
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 仓库编码
	 */
	private String warehousecode;
	/**
	 * 仓库
	 */
	private String warehouse;
	/**
	 * 物料编码
	 */
	private String itemcode;
	/**
	 * 物料名称
	 */
	private String itemname;
	/**
	 * 规格型号
	 */
	private String specification;
	/**
	 * 主计量单位
	 */
	private String masterUnit;
	/**
	 * 物料类型
	 */
	private String itemtype;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 结存数量
	 */
	private Double quantity;
	/**
	 * 结存辅计量数量
	 */
	private Integer assitnum;
	/**
	 * 待发货数量
	 */
	private Integer outquantity;
	/**
	 * 待发货辅计量数量
	 */
	private Integer outassistnum;
	/**
	 * 待入库数量
	 */
	private Integer inquantity;
	/**
	 * 待入库辅计量数量
	 */
	private Integer inassitnum;
	/**
	 * 批号
	 */
	private String batchcode;
	/**
	 * 条形码
	 */
	private String barCode;
	/**
	 * 序列号
	 */
	private String serialcode;
	/**
	 * 失效日期
	 */
	private Date invaliddate;
	/**
	 * 库存是否冻结 0,否     1,是
	 */
	private Integer isfreezeflag;
	/**
	 * 调拨在途数量
	 */
	private Long transinquantity;
	/**
	 * 生产日期
	 */
	private Date producedate;
	/**
	 * 调拨在途辅计量数量
	 */
	private Integer transinnum;
	/**
	 * 调拨待发数量
	 */
	private Integer transoutquantity;
	/**
	 * 调拨待发辅计量数量
	 */
	private Integer transoutnum;
	/**
	 * 计划备料数量
	 */
	private Integer planquantity;
	/**
	 * 计划备料辅计量数量
	 */
	private Integer plannum;
	/**
	 * 不合格数量
	 */
	private Double nonqualifiedquantity;
	/**
	 * 不合格辅计量数量
	 */
	private Integer nonqualifiedassitquantity;
	/**
	 * 可用数量
	 */
	private Double avaquantity;
	/**
	 * 可用辅计量数量
	 */
	private Integer avaassitquantity;
	/**
	 * 时间戳
	 */
	private Date ufts;
	/**
	 * 保质期天数
	 */
	private Float qualityperiod;
	/**
	 * GSP是否冻结
	 */
	private String isgspfreeze;
	/**
	 * 保质期单位
	 */
	private Integer massunit;
	/**
	 * 保质期到
	 */
	private Date massunitEndTime;
	/**
	 * 保质期单位名称
	 */
	private String massunitname;
	/**
	 * 冻结数量
	 */
	private Double freezequantity;
	/**
	 * 冻结件数
	 */
	private Integer freezenum;
	/**
	 * 最后检验日期
	 */
	private Date lastcheckdate;

	/**
	 * 上市日期
	 */
	private Date listingDate;
	/**
	 * 出厂日期
	 */
	private Date manufactureDate;
	/**
	 * 检验状态
	 */
	private String checkstate;
	/**
	 * 是否绑定商品 1 绑定 2 未绑定
	 */
	private String isBinding;
	/**
	 * 上年最后检验日期
	 */
	private Date lastyearcheckdate;
	/**
	 * 绑定数量
	 */
	private Integer bindingNumber;
	/**
	 * 组合数量
	 */
	private Long groupAmount;
	/**
	 * 是否合格1,合格 ，2,不合格
	 */
	private Integer isQualfied;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 库存成本
	 */
	private Double costPrice;
	/**
	 * sku编码
	 */
	private String skuCode;
	/**
	 * 绑定以后的库存信息
	 */
	private InventoryCurrentStock inventoryCurrentStock;
	/**
	 * 虚拟组合商品
	 */
	private GroupInventoryCurrentStock groupInventoryCurrentStock;
	/**
	 * 仓库/正品仓
	 */
	private InvWarehouse invWarehouse;
	/**
	 * 批次
	 */
	private InvMainBatch invMainBatch;
	/**
	 * 货位
	 */
	private InvShelf invShelf;
	private String invShelfName;
	/**
	 * 商品
	 */
	private Item item;
	/**
	 * 店铺商品
	 */
	private StoreItem storeItem;

	private MasterInventoryCurrentStock masterInventoryCurrentStock;
	/**
	 * 绑定商品列表
	 */
	private Set<InventoryCurrentStock> inventoryCurrentStockList = new HashSet<InventoryCurrentStock>();
	/**
	 * 产品渠道/经销商/代理销售商/门店 类型: 渠道、分销商、代理商、门店
	 */
	private ChannelDistributor channelDistributor;
	/**
	 * 单位
	 */
	private MeasureUnit measureUnit;
	/**
	 * 供应商
	 */
	private Supplier supplier;
	/**
	 * 
	 */
	private ItemCatalog itemCatalog;
	
	public InventoryCurrentStock() {
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public String getSupplierName() {
		if (supplier != null) {
			return supplier.getName();
		}
		return "";
	}

	public String getMeasureUnitName() {
		if (measureUnit != null) {
			return measureUnit.getName();
		}
		return "";
	}

	public String getMeasureUnitId() {
		if (measureUnit != null) {
			return measureUnit.getId();
		}
		return "";
	}

	public String getInvWarehouseName() {
		if (invWarehouse != null) {
			return invWarehouse.getName();
		}
		return "";
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getMassunitEndTimeStr() {
		if (null != massunitEndTime) {
			return DateUtil.format(massunitEndTime);
		}
		return "";
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

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Date getMassunitEndTime() {
		return massunitEndTime;
	}

	public void setMassunitEndTime(Date massunitEndTime) {
		this.massunitEndTime = massunitEndTime;
	}

	public GroupInventoryCurrentStock getGroupInventoryCurrentStock() {
		return groupInventoryCurrentStock;
	}

	public void setGroupInventoryCurrentStock(GroupInventoryCurrentStock groupInventoryCurrentStock) {
		this.groupInventoryCurrentStock = groupInventoryCurrentStock;
	}

	public String getItemcode() {
		return this.itemcode;
	}

	public String getMasterUnit() {
		return masterUnit;
	}

	public void setMasterUnit(String masterUnit) {
		this.masterUnit = masterUnit;
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

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getItemtype() {
		return this.itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public Integer getAssitnum() {
		return this.assitnum;
	}

	public void setAssitnum(Integer assitnum) {
		this.assitnum = assitnum;
	}

	public Integer getOutquantity() {
		return this.outquantity;
	}

	public void setOutquantity(Integer outquantity) {
		this.outquantity = outquantity;
	}

	public Integer getOutassistnum() {
		return this.outassistnum;
	}

	public void setOutassistnum(Integer outassistnum) {
		this.outassistnum = outassistnum;
	}

	public Integer getInquantity() {
		return this.inquantity;
	}

	public void setInquantity(Integer inquantity) {
		this.inquantity = inquantity;
	}

	public Integer getInassitnum() {
		return this.inassitnum;
	}

	public void setInassitnum(Integer inassitnum) {
		this.inassitnum = inassitnum;
	}

	public String getBatchcode() {
		if (StringUtils.isNotEmpty(batchcode)) {
			return this.batchcode;
		}
		return "";
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

	public String getSerialcode() {
		return this.serialcode;
	}

	public void setSerialcode(String serialcode) {
		this.serialcode = serialcode;
	}

	public Date getInvaliddate() {
		return this.invaliddate;
	}

	public void setInvaliddate(Date invaliddate) {
		this.invaliddate = invaliddate;
	}

	public Integer getIsfreezeflag() {
		return this.isfreezeflag;
	}

	public void setIsfreezeflag(Integer isfreezeflag) {
		this.isfreezeflag = isfreezeflag;
	}

	public Long getTransinquantity() {
		return this.transinquantity;
	}

	public void setTransinquantity(Long transinquantity) {
		this.transinquantity = transinquantity;
	}

	public Date getProducedate() {
		return this.producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}

	public Integer getTransinnum() {
		return this.transinnum;
	}

	public void setTransinnum(Integer transinnum) {
		this.transinnum = transinnum;
	}

	public Integer getTransoutquantity() {
		return this.transoutquantity;
	}

	public void setTransoutquantity(Integer transoutquantity) {
		this.transoutquantity = transoutquantity;
	}

	public Integer getTransoutnum() {
		return this.transoutnum;
	}

	public void setTransoutnum(Integer transoutnum) {
		this.transoutnum = transoutnum;
	}

	public Integer getPlanquantity() {
		return this.planquantity;
	}

	public void setPlanquantity(Integer planquantity) {
		this.planquantity = planquantity;
	}

	public Integer getPlannum() {
		return this.plannum;
	}

	public void setPlannum(Integer plannum) {
		this.plannum = plannum;
	}

	public Double getNonqualifiedquantity() {
		return nonqualifiedquantity;
	}

	public void setNonqualifiedquantity(Double nonqualifiedquantity) {
		this.nonqualifiedquantity = nonqualifiedquantity;
	}

	public Integer getNonqualifiedassitquantity() {
		return this.nonqualifiedassitquantity;
	}

	public void setNonqualifiedassitquantity(Integer nonqualifiedassitquantity) {
		this.nonqualifiedassitquantity = nonqualifiedassitquantity;
	}

	public Integer getAvaassitquantity() {
		return this.avaassitquantity;
	}

	public void setAvaassitquantity(Integer avaassitquantity) {
		this.avaassitquantity = avaassitquantity;
	}

	public Date getUfts() {
		return this.ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public Float getQualityperiod() {
		return this.qualityperiod;
	}

	public void setQualityperiod(Float qualityperiod) {
		this.qualityperiod = qualityperiod;
	}

	public String getIsgspfreeze() {
		return this.isgspfreeze;
	}

	public void setIsgspfreeze(String isgspfreeze) {
		this.isgspfreeze = isgspfreeze;
	}

	public Integer getMassunit() {
		return this.massunit;
	}

	public void setMassunit(Integer massunit) {
		this.massunit = massunit;
	}

	public String getMassunitname() {
		return this.massunitname;
	}

	public void setMassunitname(String massunitname) {
		this.massunitname = massunitname;
	}

	public Double getFreezequantity() {
		return freezequantity;
	}

	public void setFreezequantity(Double freezequantity) {
		this.freezequantity = freezequantity;
	}

	public Integer getFreezenum() {
		return this.freezenum;
	}

	public void setFreezenum(Integer freezenum) {
		this.freezenum = freezenum;
	}

	public Date getLastcheckdate() {
		return this.lastcheckdate;
	}

	public void setLastcheckdate(Date lastcheckdate) {
		this.lastcheckdate = lastcheckdate;
	}

	public String getCheckstate() {
		return this.checkstate;
	}

	public void setCheckstate(String checkstate) {
		this.checkstate = checkstate;
	}

	public Date getLastyearcheckdate() {
		return this.lastyearcheckdate;
	}

	public void setLastyearcheckdate(Date lastyearcheckdate) {
		this.lastyearcheckdate = lastyearcheckdate;
	}

	public String getUnit() {
		if (measureUnit != null) {
			return measureUnit.getName();
		}
		if (StringUtils.isNotEmpty(unit)) {
			return unit;
		}
		return "";
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(String isBinding) {
		this.isBinding = isBinding;
	}

	public Integer getBindingNumber() {
		return bindingNumber;
	}

	public void setBindingNumber(Integer bindingNumber) {
		this.bindingNumber = bindingNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}

	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}

	public Set<InventoryCurrentStock> getInventoryCurrentStockList() {
		return inventoryCurrentStockList;
	}

	public void setInventoryCurrentStockList(Set<InventoryCurrentStock> inventoryCurrentStockList) {
		this.inventoryCurrentStockList = inventoryCurrentStockList;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getIsQualfied() {
		return isQualfied;
	}

	public void setIsQualfied(Integer isQualfied) {
		this.isQualfied = isQualfied;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public String getWarehouseName() {
		if (invWarehouse != null) {
			return invWarehouse.getName();
		}
		return "";
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public InvShelf getInvShelf() {
		return invShelf;
	}

	public void setInvShelf(InvShelf invShelf) {
		this.invShelf = invShelf;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getListingDate() {
		return listingDate;
	}

	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public String getInvShelfName() {
		if (invShelf != null) {
			return invShelf.getName();
		}
		return invShelfName;
	}

	public String getInvShelfId() {
		if (invShelf != null) {
			return invShelf.getId();
		}
		return "";
	}

	public void setInvShelfName(String invShelfName) {
		this.invShelfName = invShelfName;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public Long getGroupAmount() {
		return groupAmount;
	}

	/**
	 * @return the measureUnit
	 */
	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	/**
	 * @param measureUnit
	 *            the measureUnit to set
	 */
	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	public void setGroupAmount(Long groupAmount) {
		this.groupAmount = groupAmount;
	}

	public InvMainBatch getInvMainBatch() {
		return invMainBatch;
	}

	public void setInvMainBatch(InvMainBatch invMainBatch) {
		this.invMainBatch = invMainBatch;
	}

	public MasterInventoryCurrentStock getMasterInventoryCurrentStock() {
		return masterInventoryCurrentStock;
	}

	public void setMasterInventoryCurrentStock(MasterInventoryCurrentStock masterInventoryCurrentStock) {
		this.masterInventoryCurrentStock = masterInventoryCurrentStock;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	// public Double getAvaquantity() {
	// DecimalFormat df = new DecimalFormat(".##");
	// String st = df.format(avaquantity);
	// return Double.parseDouble(st);
	// }

	public void setAvaquantity(Double avaquantity) {
		this.avaquantity = avaquantity;
	}

	public Double getAvaquantity() {
		return avaquantity;
	}

	public Double getCostPrice() {

		if (storeItem != null && storeItem.getPurchasePrice() != null && quantity != null) {
			return storeItem.getPurchasePrice() * quantity;
		}
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public ItemCatalog getItemCatalog() {
		return itemCatalog;
	}

	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}

	public StoreItem getStoreItem() {
		return storeItem;
	}

	public void setStoreItem(StoreItem storeItem) {
		this.storeItem = storeItem;
	}
}