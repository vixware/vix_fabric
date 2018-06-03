package com.vix.inventory.positionadjustment.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 货位调整单主表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-29
 */
public class WimAdjustpvouch extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 仓库编码
	 */
	private String warehousecode;
	/**
	 * 业务员编码
	 */
	private String personcode;
	/**
	 * 制单人
	 */
	private String maker;
	/**
	 * 单据模版号
	 */
	private String templatecode;
	/**
	 * 时间戳
	 */
	private Date ufts;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;
	/**
	 * 货位调整单明细
	 */
	private Set<WimAdjustpvouchItem> wimAdjustpvouchItem = new HashSet<WimAdjustpvouchItem>();

	/** default constructor */
	public WimAdjustpvouch() {
	}

	public String getWarehousecode() {
		return warehousecode;
	}

	public void setWarehousecode(String warehousecode) {
		this.warehousecode = warehousecode;
	}

	public String getPersoncode() {
		return personcode;
	}

	public void setPersoncode(String personcode) {
		this.personcode = personcode;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getTemplatecode() {
		return templatecode;
	}

	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}

	public Date getUfts() {
		return ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public Set<WimAdjustpvouchItem> getWimAdjustpvouchItem() {
		return wimAdjustpvouchItem;
	}

	public void setWimAdjustpvouchItem(Set<WimAdjustpvouchItem> wimAdjustpvouchItem) {
		this.wimAdjustpvouchItem = wimAdjustpvouchItem;
	}

}