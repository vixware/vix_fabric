package com.vix.inventory.warehouse.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.expressFeeRules.entity.Provinces;
import com.vix.hr.organization.entity.Employee;

/**
 * 仓库
 */

public class InvWarehouse extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 库存量不足是否允许出库
	 */
	private String isPermitOutIfLess;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 仓库性质/属性(存货仓,非存货仓) <option value="1" selected="selected">普通仓</option>
	 * <option value="2">赠品仓</option> <option value="3">WIP-在制品区[工作中心]</option>
	 * <option value="4">正品仓</option> <option value="5">残次品仓</option>
	 * <option value="6">其他仓</option>
	 */
	private String properties;
	/**
	 * 是否纳入可用量计算
	 */
	private String isTakeInAtp;
	/**
	 * 负责人
	 */
	private Employee employee;
	/**
	 * 对应条码
	 */
	private String barCode;
	/**
	 * RFID
	 */
	private String rfidCode;
	/**
	 * 计价方式
	 */
	private String priceStyle;
	/**
	 * 是否参与MRP运算
	 */
	private String isInvolveMrp;
	/**
	 * 是否参与ROP计算
	 */
	private String isInvolveRop;
	/**
	 * 是否为代管仓库
	 */
	private String isAgentInventory;
	/**
	 * 工厂代码
	 */
	private String factoryCode;
	/**
	 * 工厂名称
	 */
	private String factoryName;
	/**
	 * 位置代码
	 */
	private String positionCode;
	/**
	 * 地点
	 */
	private String postion;
	/**
	 * 库管员编号
	 */
	private String warehousePersonCode;
	/**
	 * 库管员
	 */
	private String warehousePerson;
	/* 资金定额 */
	private Double fundQuota;

	/** 仓库地址 */
	private Set<InvWarehouselocation> invWarehouselocations = new HashSet<InvWarehouselocation>();
	/** 货区 */
	private Set<InvWarehousezone> invWarehousezones = new HashSet<InvWarehousezone>();
	/** 货架(位) */
	private Set<InvShelf> invShelfs = new HashSet<InvShelf>();
	/**
	 * 发货省份
	 */
	private Set<Provinces> subProvincess = new HashSet<Provinces>();
	/**
	 * 发货省份
	 */
	private String provincesName;
	/**
	 * 父类
	 */
	private InvWarehouse parentInvWarehouse;
	/**
	 * 子集合
	 */
	private Set<InvWarehouse> subInvWarehouse;

	private ChannelDistributor channelDistributor;
	private Organization organization;
	/**
	 * 1,公司仓库 2,渠道仓库
	 */
	private String type;
	/**
	 * 是否默认仓库
	 */
	private String isDefault;

	public InvWarehouse() {
	}

	public String getIsPermitOutIfLess() {
		return isPermitOutIfLess;
	}

	public void setIsPermitOutIfLess(String isPermitOutIfLess) {
		this.isPermitOutIfLess = isPermitOutIfLess;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsTakeInAtp() {
		return isTakeInAtp;
	}

	public void setIsTakeInAtp(String isTakeInAtp) {
		this.isTakeInAtp = isTakeInAtp;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getRfidCode() {
		return rfidCode;
	}

	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

	public String getPriceStyle() {
		return priceStyle;
	}

	public void setPriceStyle(String priceStyle) {
		this.priceStyle = priceStyle;
	}

	public String getIsInvolveMrp() {
		return isInvolveMrp;
	}

	public void setIsInvolveMrp(String isInvolveMrp) {
		this.isInvolveMrp = isInvolveMrp;
	}

	public String getIsInvolveRop() {
		return isInvolveRop;
	}

	public void setIsInvolveRop(String isInvolveRop) {
		this.isInvolveRop = isInvolveRop;
	}

	public String getIsAgentInventory() {
		return isAgentInventory;
	}

	public void setIsAgentInventory(String isAgentInventory) {
		this.isAgentInventory = isAgentInventory;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}

	public String getWarehousePersonCode() {
		return warehousePersonCode;
	}

	public void setWarehousePersonCode(String warehousePersonCode) {
		this.warehousePersonCode = warehousePersonCode;
	}

	public String getWarehousePerson() {
		return warehousePerson;
	}

	public void setWarehousePerson(String warehousePerson) {
		this.warehousePerson = warehousePerson;
	}

	public Set<InvWarehouselocation> getInvWarehouselocations() {
		return invWarehouselocations;
	}

	public void setInvWarehouselocations(Set<InvWarehouselocation> invWarehouselocations) {
		this.invWarehouselocations = invWarehouselocations;
	}

	public Set<InvWarehousezone> getInvWarehousezones() {
		return invWarehousezones;
	}

	public void setInvWarehousezones(Set<InvWarehousezone> invWarehousezones) {
		this.invWarehousezones = invWarehousezones;
	}

	public Set<InvShelf> getInvShelfs() {
		return invShelfs;
	}

	public void setInvShelfs(Set<InvShelf> invShelfs) {
		this.invShelfs = invShelfs;
	}

	public Double getFundQuota() {
		return fundQuota;
	}

	public void setFundQuota(Double fundQuota) {
		this.fundQuota = fundQuota;
	}

	public InvWarehouse getParentInvWarehouse() {
		return parentInvWarehouse;
	}

	public void setParentInvWarehouse(InvWarehouse parentInvWarehouse) {
		this.parentInvWarehouse = parentInvWarehouse;
	}

	public Set<InvWarehouse> getSubInvWarehouse() {
		return subInvWarehouse;
	}

	public void setSubInvWarehouse(Set<InvWarehouse> subInvWarehouse) {
		this.subInvWarehouse = subInvWarehouse;
	}

	public String getProvincesName() {
		return provincesName;
	}

	public void setProvincesName(String provincesName) {
		this.provincesName = provincesName;
	}

	public Set<Provinces> getSubProvincess() {
		return subProvincess;
	}

	public void setSubProvincess(Set<Provinces> subProvincess) {
		this.subProvincess = subProvincess;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}