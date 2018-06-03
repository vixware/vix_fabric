package com.vix.inventory.standingbook.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 存货档案清单(现存量汇总表)
 * 
 * @类全名 com.vix.inventory.standingbook.entity.InventoryCurrentStockHistory
 *
 * @author zhanghaibing
 *
 * @date 2016年8月11日
 */

public class InventoryCurrentStockHistory extends BaseEntity {

	/**
	 * 
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
	private Long quantity;
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
	 * 库存是否冻结
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
	private Long nonqualifiedquantity;
	/**
	 * 不合格辅计量数量
	 */
	private Integer nonqualifiedassitquantity;
	/**
	 * 可用数量
	 */
	private Long avaquantity;
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
	private Long freezequantity;
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
	 * sku编码
	 */
	private String skuCode;
	private String invShelfName;
	/**
	 * 库存
	 */
	private InventoryCurrentStock inventoryCurrentStock;

	public InventoryCurrentStockHistory() {
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

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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
		return this.batchcode;
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

	/**
	 * @return the nonqualifiedquantity
	 */
	public Long getNonqualifiedquantity() {
		return nonqualifiedquantity;
	}

	/**
	 * @param nonqualifiedquantity
	 *            the nonqualifiedquantity to set
	 */
	public void setNonqualifiedquantity(Long nonqualifiedquantity) {
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

	public Long getFreezequantity() {
		return freezequantity;
	}

	public void setFreezequantity(Long freezequantity) {
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
		return unit;
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
		return invShelfName;
	}

	public void setInvShelfName(String invShelfName) {
		this.invShelfName = invShelfName;
	}

	public Long getGroupAmount() {
		return groupAmount;
	}

	public void setGroupAmount(Long groupAmount) {
		this.groupAmount = groupAmount;
	}

	/**
	 * @return the avaquantity
	 */
	public Long getAvaquantity() {
		return avaquantity;
	}

	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}

	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}

	public void setAvaquantity(Long avaquantity) {
		this.avaquantity = avaquantity;
	}

}