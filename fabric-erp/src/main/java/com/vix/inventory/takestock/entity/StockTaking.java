package com.vix.inventory.takestock.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 盘点单主表
 */

public class StockTaking extends BaseBOEntity {

	private static final long serialVersionUID = -1990748833525999567L;
	/**
	 * 盘点单号
	 */
	private String stocktakingcode;
	/**
	 * 业务员编码/盘点人
	 */
	private String personcode;
	/**
	 * 入库类别编码
	 */
	private String instockcatalogcode;
	/**
	 * 出库类别编码
	 */
	private String outstockcatalogcode;
	/**
	 * 盘点批号
	 */
	private String stbatch;
	/**
	 * 记账人
	 */
	private String accounter;
	/**
	 * 制单人
	 */
	private String maker;
	/**
	 * 现无用
	 */
	private Integer inetlock;
	/**
	 * 货位编码
	 */
	private String itemposition;
	/**
	 * 账面日期
	 */
	private Date accountdate;
	/**
	 * 单据模版号
	 */
	private String tempaltecode;
	/**
	 * 是否传递
	 */
	private String istransflag;
	/**
	 * 时间戳
	 */
	private Date ufts;
	/**
	 * 盘点类型
	 */
	private String sttype;
	/**
	 * 盘点会计期间
	 */
	private String stperiod;
	/**
	 * 类型 1,总部单据 2,门店单据
	 */
	private String type;
	/**
	 * 盘点单明细
	 */
	private Set<StockTakingItem> stockTakingItem = new HashSet<StockTakingItem>();
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;

	public StockTaking() {
	}

	public String getStocktakingcode() {
		return this.stocktakingcode;
	}

	public void setStocktakingcode(String stocktakingcode) {
		this.stocktakingcode = stocktakingcode;
	}

	public String getPersoncode() {
		return this.personcode;
	}

	public void setPersoncode(String personcode) {
		this.personcode = personcode;
	}

	public String getInstockcatalogcode() {
		return this.instockcatalogcode;
	}

	public void setInstockcatalogcode(String instockcatalogcode) {
		this.instockcatalogcode = instockcatalogcode;
	}

	public String getOutstockcatalogcode() {
		return this.outstockcatalogcode;
	}

	public void setOutstockcatalogcode(String outstockcatalogcode) {
		this.outstockcatalogcode = outstockcatalogcode;
	}

	public String getWarehousecode() {
		if (invWarehouse != null) {
			return invWarehouse.getCode();
		}
		return "";
	}

	public String getWarehouseName() {
		if (invWarehouse != null) {
			return invWarehouse.getName();
		}
		return "";
	}

	public String getStbatch() {
		return this.stbatch;
	}

	public void setStbatch(String stbatch) {
		this.stbatch = stbatch;
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

	public Integer getInetlock() {
		return this.inetlock;
	}

	public void setInetlock(Integer inetlock) {
		this.inetlock = inetlock;
	}

	public String getItemposition() {
		return this.itemposition;
	}

	public void setItemposition(String itemposition) {
		this.itemposition = itemposition;
	}

	public Date getAccountdate() {
		return this.accountdate;
	}

	public void setAccountdate(Date accountdate) {
		this.accountdate = accountdate;
	}

	public String getTempaltecode() {
		return this.tempaltecode;
	}

	public void setTempaltecode(String tempaltecode) {
		this.tempaltecode = tempaltecode;
	}

	public String getIstransflag() {
		return this.istransflag;
	}

	public void setIstransflag(String istransflag) {
		this.istransflag = istransflag;
	}

	public Date getUfts() {
		return this.ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public String getSttype() {
		return this.sttype;
	}

	public void setSttype(String sttype) {
		this.sttype = sttype;
	}

	public String getStperiod() {
		return this.stperiod;
	}

	public void setStperiod(String stperiod) {
		this.stperiod = stperiod;
	}

	public Set<StockTakingItem> getStockTakingItem() {
		return stockTakingItem;
	}

	public void setStockTakingItem(Set<StockTakingItem> stockTakingItem) {
		this.stockTakingItem = stockTakingItem;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}