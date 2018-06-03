package com.vix.inventory.transfer.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 库存调拨单子表
 */

public class WimTransvouchitem extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3376293653172723048L;
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
	 * 计量单位
	 */
	private MeasureUnit measureUnit;
	/**
	 * 调拨数量
	 */
	private Double tvquantity;
	/**
	 * sku编码
	 */
	private String skuCode;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;

	/**
	 * 货位
	 */
	private InvShelf invShelf;
	/**
	 * 入库单编码
	 */
	private String tvcode;
	private String cinvcode;
	private Integer rdsid;
	private Float tvnum;
	/**
	 * 实际单价
	 */
	private Float tvacost;
	/**
	 * 实际金额
	 */
	private Float tvaprice;
	/**
	 * 计划价或售价
	 */
	private Float tvpcost;
	private Float tvpprice;
	private String tvbatch;
	private Date ddisdate;
	private String citemcode;
	private String citemClass;
	/**
	 * 零售单价
	 */
	private Double salecost;
	/**
	 * 零售金额
	 */
	private Float saleprice;
	private String cname;
	private String citemcname;
	private Integer autoid;
	private Integer massdate;
	private String barcode;
	private String assitunit;
	private Integer impoids;
	private String vendercode;
	private String invouchcode;
	private Date producedate;
	private Integer massunit;
	private Integer itrids;
	private String isapptransids;

	// 是否更新库存 1,未更新 2,已更新
	private String isUpdateStore;
	/**
	 * 调拨单主表
	 */
	private WimTransvouch wimTransvouch;

	// Constructors

	/** default constructor */
	public WimTransvouchitem() {
	}

	public String getTvcode() {
		return this.tvcode;
	}

	public void setTvcode(String tvcode) {
		this.tvcode = tvcode;
	}

	public String getCinvcode() {
		return this.cinvcode;
	}

	public void setCinvcode(String cinvcode) {
		this.cinvcode = cinvcode;
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

	public Integer getRdsid() {
		return this.rdsid;
	}

	public void setRdsid(Integer rdsid) {
		this.rdsid = rdsid;
	}

	public Float getTvnum() {
		return this.tvnum;
	}

	public void setTvnum(Float tvnum) {
		this.tvnum = tvnum;
	}

	public Float getTvacost() {
		return this.tvacost;
	}

	public void setTvacost(Float tvacost) {
		this.tvacost = tvacost;
	}

	public Float getTvaprice() {
		return this.tvaprice;
	}

	public void setTvaprice(Float tvaprice) {
		this.tvaprice = tvaprice;
	}

	public Float getTvpcost() {
		return this.tvpcost;
	}

	public void setTvpcost(Float tvpcost) {
		this.tvpcost = tvpcost;
	}

	public Float getTvpprice() {
		return this.tvpprice;
	}

	public void setTvpprice(Float tvpprice) {
		this.tvpprice = tvpprice;
	}

	public String getTvbatch() {
		return this.tvbatch;
	}

	public void setTvbatch(String tvbatch) {
		this.tvbatch = tvbatch;
	}

	public Date getDdisdate() {
		return this.ddisdate;
	}

	public void setDdisdate(Date ddisdate) {
		this.ddisdate = ddisdate;
	}

	public String getCitemcode() {
		return this.citemcode;
	}

	public void setCitemcode(String citemcode) {
		this.citemcode = citemcode;
	}

	public String getCitemClass() {
		return this.citemClass;
	}

	public void setCitemClass(String citemClass) {
		this.citemClass = citemClass;
	}

	public Double getSalecost() {
		return salecost;
	}

	public void setSalecost(Double salecost) {
		this.salecost = salecost;
	}

	public Float getSaleprice() {
		return this.saleprice;
	}

	public void setSaleprice(Float saleprice) {
		this.saleprice = saleprice;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCitemcname() {
		return this.citemcname;
	}

	public void setCitemcname(String citemcname) {
		this.citemcname = citemcname;
	}

	public Integer getAutoid() {
		return this.autoid;
	}

	public void setAutoid(Integer autoid) {
		this.autoid = autoid;
	}

	public Integer getMassdate() {
		return this.massdate;
	}

	public void setMassdate(Integer massdate) {
		this.massdate = massdate;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getAssitunit() {
		return this.assitunit;
	}

	public void setAssitunit(String assitunit) {
		this.assitunit = assitunit;
	}

	public Integer getImpoids() {
		return this.impoids;
	}

	public void setImpoids(Integer impoids) {
		this.impoids = impoids;
	}

	public String getVendercode() {
		return this.vendercode;
	}

	public void setVendercode(String vendercode) {
		this.vendercode = vendercode;
	}

	public String getInvouchcode() {
		return this.invouchcode;
	}

	public void setInvouchcode(String invouchcode) {
		this.invouchcode = invouchcode;
	}

	public Date getProducedate() {
		return this.producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}

	public Integer getMassunit() {
		return this.massunit;
	}

	public void setMassunit(Integer massunit) {
		this.massunit = massunit;
	}

	public Integer getItrids() {
		return this.itrids;
	}

	public void setItrids(Integer itrids) {
		this.itrids = itrids;
	}

	public String getIsapptransids() {
		return this.isapptransids;
	}

	public void setIsapptransids(String isapptransids) {
		this.isapptransids = isapptransids;
	}

	public WimTransvouch getWimTransvouch() {
		return wimTransvouch;
	}

	public void setWimTransvouch(WimTransvouch wimTransvouch) {
		this.wimTransvouch = wimTransvouch;
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

	public Double getTvquantity() {
		return tvquantity;
	}

	public void setTvquantity(Double tvquantity) {
		this.tvquantity = tvquantity;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	/**
	 * @return the isUpdateStore
	 */
	public String getIsUpdateStore() {
		return isUpdateStore;
	}

	/**
	 * @param isUpdateStore
	 *            the isUpdateStore to set
	 */
	public void setIsUpdateStore(String isUpdateStore) {
		this.isUpdateStore = isUpdateStore;
	}

}