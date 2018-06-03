package com.vix.inventory.standingbook.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;

/**
 * 存货档案清单(现存量汇总表)
 * com.vix.inventory.standingbook.entity.MasterInventoryCurrentStock
 *
 * @author zhanghaibing
 *
 * @date 2014年11月3日
 */

public class MasterInventoryCurrentStock extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 物料编码
	 */
	private String itemcode;
	/**
	 * 物料名称
	 */
	private String itemname;
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
	 * 序列号
	 */
	private String serialcode;
	/**
	 * 调拨在途数量
	 */
	private Long transinquantity;
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
	 * 保质期单位名称
	 */
	private String massunitname;
	/**
	 * 冻结数量
	 */
	private Integer freezequantity;
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
	 * 检验状态
	 */
	private String checkstate;
	/**
	 * 上年最后检验日期
	 */
	private Date lastyearcheckdate;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;
	/**
	 * 货位
	 */
	private InvShelf invShelf;
	/**
	 * 货位名称
	 */
	private String invShelfName;
	/**
	 * 商品
	 */
	private Item item;
	/**
	 * 产品渠道/经销商/代理销售商/门店 类型: 渠道、分销商、代理商、门店
	 */
	private ChannelDistributor channelDistributor;

	private Set<InventoryCurrentStock> inventoryCurrentStockList = new HashSet<InventoryCurrentStock>();

	public MasterInventoryCurrentStock() {
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
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

	public String getMasterUnit() {
		return masterUnit;
	}

	public void setMasterUnit(String masterUnit) {
		this.masterUnit = masterUnit;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAvaquantity() {
		return avaquantity;
	}

	public void setAvaquantity(Double avaquantity) {
		this.avaquantity = avaquantity;
	}

	public Integer getAssitnum() {
		return assitnum;
	}

	public void setAssitnum(Integer assitnum) {
		this.assitnum = assitnum;
	}

	public Integer getOutquantity() {
		return outquantity;
	}

	public void setOutquantity(Integer outquantity) {
		this.outquantity = outquantity;
	}

	public Integer getOutassistnum() {
		return outassistnum;
	}

	public void setOutassistnum(Integer outassistnum) {
		this.outassistnum = outassistnum;
	}

	public Integer getInquantity() {
		return inquantity;
	}

	public void setInquantity(Integer inquantity) {
		this.inquantity = inquantity;
	}

	public Integer getInassitnum() {
		return inassitnum;
	}

	public void setInassitnum(Integer inassitnum) {
		this.inassitnum = inassitnum;
	}

	public String getSerialcode() {
		return serialcode;
	}

	public void setSerialcode(String serialcode) {
		this.serialcode = serialcode;
	}

	public Long getTransinquantity() {
		return transinquantity;
	}

	public void setTransinquantity(Long transinquantity) {
		this.transinquantity = transinquantity;
	}

	public Integer getTransinnum() {
		return transinnum;
	}

	public void setTransinnum(Integer transinnum) {
		this.transinnum = transinnum;
	}

	public Integer getTransoutquantity() {
		return transoutquantity;
	}

	public void setTransoutquantity(Integer transoutquantity) {
		this.transoutquantity = transoutquantity;
	}

	public Integer getTransoutnum() {
		return transoutnum;
	}

	public void setTransoutnum(Integer transoutnum) {
		this.transoutnum = transoutnum;
	}

	public Integer getPlanquantity() {
		return planquantity;
	}

	public void setPlanquantity(Integer planquantity) {
		this.planquantity = planquantity;
	}

	public Integer getPlannum() {
		return plannum;
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
		return nonqualifiedassitquantity;
	}

	public void setNonqualifiedassitquantity(Integer nonqualifiedassitquantity) {
		this.nonqualifiedassitquantity = nonqualifiedassitquantity;
	}

	public Integer getAvaassitquantity() {
		return avaassitquantity;
	}

	public void setAvaassitquantity(Integer avaassitquantity) {
		this.avaassitquantity = avaassitquantity;
	}

	public Date getUfts() {
		return ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public Float getQualityperiod() {
		return qualityperiod;
	}

	public void setQualityperiod(Float qualityperiod) {
		this.qualityperiod = qualityperiod;
	}

	public String getIsgspfreeze() {
		return isgspfreeze;
	}

	public void setIsgspfreeze(String isgspfreeze) {
		this.isgspfreeze = isgspfreeze;
	}

	public Integer getMassunit() {
		return massunit;
	}

	public void setMassunit(Integer massunit) {
		this.massunit = massunit;
	}

	public String getMassunitname() {
		return massunitname;
	}

	public void setMassunitname(String massunitname) {
		this.massunitname = massunitname;
	}

	public Integer getFreezequantity() {
		return freezequantity;
	}

	public void setFreezequantity(Integer freezequantity) {
		this.freezequantity = freezequantity;
	}

	public Integer getFreezenum() {
		return freezenum;
	}

	public void setFreezenum(Integer freezenum) {
		this.freezenum = freezenum;
	}

	public Date getLastcheckdate() {
		return lastcheckdate;
	}

	public void setLastcheckdate(Date lastcheckdate) {
		this.lastcheckdate = lastcheckdate;
	}

	public Date getListingDate() {
		return listingDate;
	}

	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}

	public String getCheckstate() {
		return checkstate;
	}

	public void setCheckstate(String checkstate) {
		this.checkstate = checkstate;
	}

	public Date getLastyearcheckdate() {
		return lastyearcheckdate;
	}

	public void setLastyearcheckdate(Date lastyearcheckdate) {
		this.lastyearcheckdate = lastyearcheckdate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
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

	public String getInvShelfName() {
		return invShelfName;
	}

	public void setInvShelfName(String invShelfName) {
		this.invShelfName = invShelfName;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public Set<InventoryCurrentStock> getInventoryCurrentStockList() {
		return inventoryCurrentStockList;
	}

	public void setInventoryCurrentStockList(Set<InventoryCurrentStock> inventoryCurrentStockList) {
		this.inventoryCurrentStockList = inventoryCurrentStockList;
	}

}