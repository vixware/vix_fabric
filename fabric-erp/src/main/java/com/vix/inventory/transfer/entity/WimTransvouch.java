package com.vix.inventory.transfer.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 库存调拨单主表
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.inventory.transfer.entity.WimTransvouch
 *
 * @date 2017年12月27日
 */

public class WimTransvouch extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 单据日期
	 */
	private Date tvdate;
	/**
	 * 转出仓库编码
	 */
	private String outwarehousecode;
	/**
	 * 转入仓库编码
	 */
	private String inwarehousecode;

	/**
	 * 转出仓库
	 */
	private InvWarehouse outInvWarehouse;
	/**
	 * 转入仓库
	 */
	private InvWarehouse inInvWarehouse;

	/**
	 * 转出部门
	 */
	private String outdepartmentCode;
	/**
	 * 转入部门
	 */
	private String indepartmentCode;
	private String personcode;
	/**
	 * 入库类别编码
	 */
	private String instockcatalogcode;
	/**
	 * 出库类别编码
	 */
	private String outstockcatalogcode;
	private String maker;
	private String templatecode;
	private String checkperson;
	private Date checkdate;
	private String closer;
	private Boolean netlock;
	private String parentitemcode;
	private String manordercode;
	private Float manquantity;
	private Short transflag;
	private Date ufts;
	private Integer manorderid;
	/**
	 * 订单类型
	 */
	private String ordertype;
	private String tranrequestcode;
	private String versions;
	private String bomcode;
	private String apptvcode;
	/**
	 * 调拨人
	 */
	private String allocationPerson;
	/**
	 * 批准人
	 */
	private String approvalPerson;
	/**
	 * 调拨单明细
	 */
	private Set<WimTransvouchitem> wimTransvouchitem = new HashSet<WimTransvouchitem>();

	public WimTransvouch() {
	}

	public Date getTvdate() {
		return this.tvdate;
	}

	public void setTvdate(Date tvdate) {
		this.tvdate = tvdate;
	}

	public String getOutwarehousecode() {
		return this.outwarehousecode;
	}

	public void setOutwarehousecode(String outwarehousecode) {
		this.outwarehousecode = outwarehousecode;
	}

	public String getOutdepartmentCode() {
		return this.outdepartmentCode;
	}

	public void setOutdepartmentCode(String outdepartmentCode) {
		this.outdepartmentCode = outdepartmentCode;
	}

	public String getIndepartmentCode() {
		return this.indepartmentCode;
	}

	public void setIndepartmentCode(String indepartmentCode) {
		this.indepartmentCode = indepartmentCode;
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

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getTemplatecode() {
		return this.templatecode;
	}

	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}

	public String getCheckperson() {
		return this.checkperson;
	}

	public void setCheckperson(String checkperson) {
		this.checkperson = checkperson;
	}

	public Date getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public String getCloser() {
		return this.closer;
	}

	public void setCloser(String closer) {
		this.closer = closer;
	}

	public Boolean getNetlock() {
		return this.netlock;
	}

	public void setNetlock(Boolean netlock) {
		this.netlock = netlock;
	}

	public String getParentitemcode() {
		return this.parentitemcode;
	}

	public void setParentitemcode(String parentitemcode) {
		this.parentitemcode = parentitemcode;
	}

	public String getManordercode() {
		return this.manordercode;
	}

	public void setManordercode(String manordercode) {
		this.manordercode = manordercode;
	}

	public Float getManquantity() {
		return this.manquantity;
	}

	public void setManquantity(Float manquantity) {
		this.manquantity = manquantity;
	}

	public Short getTransflag() {
		return this.transflag;
	}

	public void setTransflag(Short transflag) {
		this.transflag = transflag;
	}

	public Date getUfts() {
		return this.ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public Integer getManorderid() {
		return this.manorderid;
	}

	public void setManorderid(Integer manorderid) {
		this.manorderid = manorderid;
	}

	public String getOrdertype() {
		return this.ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getTranrequestcode() {
		return this.tranrequestcode;
	}

	public void setTranrequestcode(String tranrequestcode) {
		this.tranrequestcode = tranrequestcode;
	}

	public String getVersions() {
		return this.versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getBomcode() {
		return this.bomcode;
	}

	public void setBomcode(String bomcode) {
		this.bomcode = bomcode;
	}

	public String getApptvcode() {
		return this.apptvcode;
	}

	public void setApptvcode(String apptvcode) {
		this.apptvcode = apptvcode;
	}

	public String getInwarehousecode() {
		return inwarehousecode;
	}

	public void setInwarehousecode(String inwarehousecode) {
		this.inwarehousecode = inwarehousecode;
	}

	public Set<WimTransvouchitem> getWimTransvouchitem() {
		return wimTransvouchitem;
	}

	public void setWimTransvouchitem(Set<WimTransvouchitem> wimTransvouchitem) {
		this.wimTransvouchitem = wimTransvouchitem;
	}

	public String getAllocationPerson() {
		return allocationPerson;
	}

	public void setAllocationPerson(String allocationPerson) {
		this.allocationPerson = allocationPerson;
	}

	public String getApprovalPerson() {
		return approvalPerson;
	}

	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}

	public InvWarehouse getOutInvWarehouse() {
		return outInvWarehouse;
	}

	public void setOutInvWarehouse(InvWarehouse outInvWarehouse) {
		this.outInvWarehouse = outInvWarehouse;
	}

	public InvWarehouse getInInvWarehouse() {
		return inInvWarehouse;
	}

	public void setInInvWarehouse(InvWarehouse inInvWarehouse) {
		this.inInvWarehouse = inInvWarehouse;
	}
	public String getInInvWarehouseName() {
		if (null != inInvWarehouse) {
			return inInvWarehouse.getName();
		}
		return "";
	}
	public String getOutInvWarehouseName() {
		if (null != outInvWarehouse) {
			return outInvWarehouse.getName();
		}
		return "";
	}
}