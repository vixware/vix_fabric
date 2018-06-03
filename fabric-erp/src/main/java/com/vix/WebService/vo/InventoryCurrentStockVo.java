/**
 * 
 */
package com.vix.WebService.vo;

import java.util.Date;

/**
 * 
 * com.vix.WebService.vo.InventoryCurrentStockVo
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-30
 */
public class InventoryCurrentStockVo {
	/** 自动编号 */
	private Long id;
	/** 数据唯一编码 */
	private String uuid;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 业务主键标识 */
	private String primaryKey;
	/** 对象语言标识 */
	private String language;
	/** 组织(公司内部标识) */
	private String companyInnerCode;
	/** 公司标识 */
	private String companyCode;
	/** 帐套 */
	private String accountSet;
	/** 创建时间 */
	private Date createTime;
	/** 对象更新时间 */
	private Date updateTime;
	/** 企业编码 废弃 */
	private String enterpriseCode;
	/** 部门编码 */
	private String departmentCode;
	/** 部门名称 */
	private String department;
	/** 创建人 */
	private String creator;
	/** 状态 */
	private String status;
	/** 密级 */
	private String secretLevel;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 承租户标识 */
	private String tenantId;
	/** 语言编码 */
	private String langCode;
	/** 国家编码 */
	private String countryCode;
	/** 当事人 */
	private String interestedPartyPerson;
	/** 当事人角色编码 */
	private String interestedPartyRoleCode;
	/** 当事人角色 */
	private String interestedPartyRole;
	/** 版本 */
	private String version;
	/** 备注 */
	private String memo;
	/** 描述 */
	private String description;
	/**
	 * 是否为正式数据
	 */
	private String isTemp;
	private String searchText1;
	private String searchText2;
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
	 * 批号
	 */
	private String batchcode;
	/**
	 * 序列号
	 */
	private String serialcode;
	/**
	 * 失效日期
	 */
	private Date invaliddate;
	/**
	 * 生产日期
	 */
	private Date producedate;
	/**
	 * 可用数量
	 */
	private Integer avaquantity;
	/**
	 * 可用辅计量数量
	 */
	private Integer avaassitquantity;
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
	 * 是否绑定商品 1 绑定 2 未绑定
	 */
	private String isBinding;
	/**
	 * 绑定数量
	 */
	private Integer bindingNumber;
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

	/** default constructor */
	public InventoryCurrentStockVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getAccountSet() {
		return accountSet;
	}

	public void setAccountSet(String accountSet) {
		this.accountSet = accountSet;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getInterestedPartyPerson() {
		return interestedPartyPerson;
	}

	public void setInterestedPartyPerson(String interestedPartyPerson) {
		this.interestedPartyPerson = interestedPartyPerson;
	}

	public String getInterestedPartyRoleCode() {
		return interestedPartyRoleCode;
	}

	public void setInterestedPartyRoleCode(String interestedPartyRoleCode) {
		this.interestedPartyRoleCode = interestedPartyRoleCode;
	}

	public String getInterestedPartyRole() {
		return interestedPartyRole;
	}

	public void setInterestedPartyRole(String interestedPartyRole) {
		this.interestedPartyRole = interestedPartyRole;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getSearchText1() {
		return searchText1;
	}

	public void setSearchText1(String searchText1) {
		this.searchText1 = searchText1;
	}

	public String getSearchText2() {
		return searchText2;
	}

	public void setSearchText2(String searchText2) {
		this.searchText2 = searchText2;
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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
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

	public Date getProducedate() {
		return this.producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}

	public Integer getAvaquantity() {
		return this.avaquantity;
	}

	public void setAvaquantity(Integer avaquantity) {
		this.avaquantity = avaquantity;
	}

	public Integer getAvaassitquantity() {
		return this.avaassitquantity;
	}

	public void setAvaassitquantity(Integer avaassitquantity) {
		this.avaassitquantity = avaassitquantity;
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

}
