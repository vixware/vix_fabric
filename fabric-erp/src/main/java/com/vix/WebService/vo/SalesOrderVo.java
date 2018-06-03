/**
 * 
 */
package com.vix.WebService.vo;

import java.util.Date;

/**
 * @author zhanghaibing
 * 
 * @date 2014-4-30
 */
public class SalesOrderVo {

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
	private String searchText1;
	private String searchText2;
	/** 主题 */
	private String title;
	/** 订单创建日期 */
	private Date orderCreateDate;
	/** 单据日期 */
	private Date billDate;
	/** 成组编码 */
	private String groupCode;
	/** 客户公司 */
	private String customerCompany;
	/** 部门编码 */
	private String deptCode;
	/** 业务员编码 */
	private String salePersonCode;
	/** 采购订单标识ID */
	private String purchaseOrderCode;
	/** 业务类型 */
	private String bizType;
	/** 单据类型 */
	private String formType;
	/** 销售渠道 */
	private String saleChannel;
	/** 销售组织 */
	private String saleOrg;
	/** 是否冻结 */
	private String isFreeze;
	/** 付款条件 */
	private String payCondition;
	/** 变更原因 */
	private String changeReason;
	/** 过账日期 */
	private Date postingTime;
	/** 交货日期 */
	private Date deliveryTime;
	/** 使用机器IP */
	private String ip;
	/** 流程阶段 */
	private String flowPhase;
	/** 金额 */
	private Double amount;
	/** 运费 */
	private Double freight;
	/** 税率 */
	private Double taxRate;
	/** 税额 */
	private Double tax;
	/** 金额总计 */
	private Double amountTotal;
	/** 删除标志 */
	private String isDeleted;
	/** 是否为临时对象 临时为：1 实际使用为空 */
	private String isTemp;

	public SalesOrderVo() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getCustomerCompany() {
		return customerCompany;
	}

	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getSalePersonCode() {
		return salePersonCode;
	}

	public void setSalePersonCode(String salePersonCode) {
		this.salePersonCode = salePersonCode;
	}

	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getSaleChannel() {
		return saleChannel;
	}

	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public Date getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Date postingTime) {
		this.postingTime = postingTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFlowPhase() {
		return flowPhase;
	}

	public void setFlowPhase(String flowPhase) {
		this.flowPhase = flowPhase;
	}

	public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

}
