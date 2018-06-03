package com.vix.inventory.takestock.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 盘点单子表
 * 
 * @类全名 com.vix.inventory.takestock.entity.StockTakingItem
 *
 * @author zhanghaibing
 *
 * @date 2016年8月24日
 */

public class StockTakingItem extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 盘点单号
	 */
	private String stcode;
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
	 * 单位
	 */
	private String unit;
	/**
	 * 收发记录子表标识
	 */
	private String rdsid;
	/**
	 * 账面辅计量数量
	 */
	private Float cvnumber;
	/**
	 * 账面数量
	 */
	private Double cvquantity;
	/**
	 * 盘点辅计量数量
	 */
	private Float assitcvcnum;
	/**
	 * 初盘数量
	 */
	private Double cvcquantity;
	/**
	 * 二盘数量
	 */
	private Double twoQuantity;
	/**
	 * 批号
	 */
	private String cvbatch;
	/**
	 * 盘点原因
	 */
	private String cvreason;
	/**
	 * 失效日期
	 */
	private Date cvdisdate;
	/**
	 * 单价
	 */
	private Float singleprice;
	/**
	 * 账面金额
	 */
	private Float amountof;
	/**
	 * 实际单价
	 */
	private Float actualsingleprice;
	/**
	 * 实际金额
	 */
	private Float actualamount;
	/**
	 * 货位编码
	 */
	private String cvposition;
	/**
	 * 项目编码
	 */
	private String cvitemcode;
	/**
	 * 项目大类编码
	 */
	private String cvitemClass;
	/**
	 * 项目名称
	 */
	private String cname;
	/**
	 * 项目大类名称
	 */
	private String citemcname;
	/**
	 * 盘点单子表标识
	 */
	private Long autoid;
	/**
	 * 盘点单主表标识
	 */
	private String cvcode;
	/**
	 * 对应条形码编码
	 */
	private String cbarcode;
	/**
	 * 初盘数量
	 */
	private Double firststocktakingamount;
	/**
	 * 初盘金额
	 */
	private Double firststocktaking;
	/**
	 * 复盘数量
	 */
	private Double recheckamount;
	/**
	 * 复盘金额
	 */
	private Double rechecktotal;
	/**
	 * 抽盘数量
	 */
	private Double spotcheckamount;
	/**
	 * 抽盘金额
	 */
	private Double spotchecktotal;
	/**
	 * 调整入库数量
	 */
	private Double iadinquantity;
	/**
	 * 调整入库辅计量数量
	 */
	private Double iadinnum;
	/**
	 * 调整出库数量
	 */
	private Double iadoutquantity;
	/**
	 * 调整出库辅计量数量
	 */
	private Double iadoutnum;
	/**
	 * 合理损耗率
	 */
	private Double iallowwaste;
	/**
	 * 实际损耗率
	 */
	private Double iactualwaste;
	/**
	 * 辅计量单位编码
	 */
	private String cassunit;
	/**
	 * 供应商编码
	 */
	private String cbvencode;
	/**
	 * 对应入库单号
	 */
	private String cinvouchcode;
	/**
	 * 保质期天数
	 */
	private Integer imassdate;
	/**
	 * 生产日期
	 */
	private Date dmadedate;
	/**
	 * 保质期单位
	 */
	private String massunit;
	/**
	 * sku编码
	 */
	private String skuCode;
	/**
	 * 盘点单
	 */
	private StockTaking stockTaking;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;
	/**
	 * 货位
	 */
	private InvShelf invShelf;

	public StockTakingItem() {
	}

	public String getStcode() {
		return stcode;
	}

	public void setStcode(String stcode) {
		this.stcode = stcode;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRdsid() {
		return rdsid;
	}

	public void setRdsid(String rdsid) {
		this.rdsid = rdsid;
	}

	public Float getCvnumber() {
		return cvnumber;
	}

	public void setCvnumber(Float cvnumber) {
		this.cvnumber = cvnumber;
	}

	public Double getCvquantity() {
		return cvquantity;
	}

	public void setCvquantity(Double cvquantity) {
		this.cvquantity = cvquantity;
	}

	public Double getTwoQuantity() {
		return twoQuantity;
	}

	public Double getDvalue() {
		if (cvquantity != null && cvcquantity != null) {
			return cvquantity - cvcquantity;
		}
		return 0D;
	}

	public void setTwoQuantity(Double twoQuantity) {
		this.twoQuantity = twoQuantity;
	}

	public Float getAssitcvcnum() {
		return assitcvcnum;
	}

	public void setAssitcvcnum(Float assitcvcnum) {
		this.assitcvcnum = assitcvcnum;
	}

	public Double getCvcquantity() {
		return cvcquantity;
	}

	public void setCvcquantity(Double cvcquantity) {
		this.cvcquantity = cvcquantity;
	}

	public String getCvbatch() {
		return cvbatch;
	}

	public void setCvbatch(String cvbatch) {
		this.cvbatch = cvbatch;
	}

	public String getCvreason() {
		return cvreason;
	}

	public void setCvreason(String cvreason) {
		this.cvreason = cvreason;
	}

	public Date getCvdisdate() {
		return cvdisdate;
	}

	public void setCvdisdate(Date cvdisdate) {
		this.cvdisdate = cvdisdate;
	}

	public Float getSingleprice() {
		return singleprice;
	}

	public void setSingleprice(Float singleprice) {
		this.singleprice = singleprice;
	}

	public Float getAmountof() {
		return amountof;
	}

	public void setAmountof(Float amountof) {
		this.amountof = amountof;
	}

	public Float getActualsingleprice() {
		return actualsingleprice;
	}

	public void setActualsingleprice(Float actualsingleprice) {
		this.actualsingleprice = actualsingleprice;
	}

	public Float getActualamount() {
		return actualamount;
	}

	public void setActualamount(Float actualamount) {
		this.actualamount = actualamount;
	}

	public String getCvposition() {
		return cvposition;
	}

	public void setCvposition(String cvposition) {
		this.cvposition = cvposition;
	}

	public String getCvitemcode() {
		return cvitemcode;
	}

	public void setCvitemcode(String cvitemcode) {
		this.cvitemcode = cvitemcode;
	}

	public String getCvitemClass() {
		return cvitemClass;
	}

	public void setCvitemClass(String cvitemClass) {
		this.cvitemClass = cvitemClass;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCitemcname() {
		return citemcname;
	}

	public void setCitemcname(String citemcname) {
		this.citemcname = citemcname;
	}

	public Long getAutoid() {
		return autoid;
	}

	public void setAutoid(Long autoid) {
		this.autoid = autoid;
	}

	public String getCvcode() {
		return cvcode;
	}

	public void setCvcode(String cvcode) {
		this.cvcode = cvcode;
	}

	public String getCbarcode() {
		return cbarcode;
	}

	public void setCbarcode(String cbarcode) {
		this.cbarcode = cbarcode;
	}

	public Double getFirststocktakingamount() {
		return firststocktakingamount;
	}

	public void setFirststocktakingamount(Double firststocktakingamount) {
		this.firststocktakingamount = firststocktakingamount;
	}

	public Double getFirststocktaking() {
		return firststocktaking;
	}

	public void setFirststocktaking(Double firststocktaking) {
		this.firststocktaking = firststocktaking;
	}

	public Double getRecheckamount() {
		return recheckamount;
	}

	public void setRecheckamount(Double recheckamount) {
		this.recheckamount = recheckamount;
	}

	public Double getRechecktotal() {
		return rechecktotal;
	}

	public void setRechecktotal(Double rechecktotal) {
		this.rechecktotal = rechecktotal;
	}

	public Double getSpotcheckamount() {
		return spotcheckamount;
	}

	public void setSpotcheckamount(Double spotcheckamount) {
		this.spotcheckamount = spotcheckamount;
	}

	public Double getSpotchecktotal() {
		return spotchecktotal;
	}

	public void setSpotchecktotal(Double spotchecktotal) {
		this.spotchecktotal = spotchecktotal;
	}

	public Double getIadinquantity() {
		return iadinquantity;
	}

	public void setIadinquantity(Double iadinquantity) {
		this.iadinquantity = iadinquantity;
	}

	public Double getIadinnum() {
		return iadinnum;
	}

	public void setIadinnum(Double iadinnum) {
		this.iadinnum = iadinnum;
	}

	public Double getIadoutquantity() {
		return iadoutquantity;
	}

	public void setIadoutquantity(Double iadoutquantity) {
		this.iadoutquantity = iadoutquantity;
	}

	public Double getIadoutnum() {
		return iadoutnum;
	}

	public void setIadoutnum(Double iadoutnum) {
		this.iadoutnum = iadoutnum;
	}

	public Double getIallowwaste() {
		return iallowwaste;
	}

	public void setIallowwaste(Double iallowwaste) {
		this.iallowwaste = iallowwaste;
	}

	public Double getIactualwaste() {
		return iactualwaste;
	}

	public void setIactualwaste(Double iactualwaste) {
		this.iactualwaste = iactualwaste;
	}

	public String getCassunit() {
		return cassunit;
	}

	public void setCassunit(String cassunit) {
		this.cassunit = cassunit;
	}

	public String getCbvencode() {
		return cbvencode;
	}

	public void setCbvencode(String cbvencode) {
		this.cbvencode = cbvencode;
	}

	public String getCinvouchcode() {
		return cinvouchcode;
	}

	public void setCinvouchcode(String cinvouchcode) {
		this.cinvouchcode = cinvouchcode;
	}

	public Integer getImassdate() {
		return imassdate;
	}

	public void setImassdate(Integer imassdate) {
		this.imassdate = imassdate;
	}

	public Date getDmadedate() {
		return dmadedate;
	}

	public void setDmadedate(Date dmadedate) {
		this.dmadedate = dmadedate;
	}

	public String getMassunit() {
		return massunit;
	}

	public void setMassunit(String massunit) {
		this.massunit = massunit;
	}

	public StockTaking getStockTaking() {
		return stockTaking;
	}

	public void setStockTaking(StockTaking stockTaking) {
		this.stockTaking = stockTaking;
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

}