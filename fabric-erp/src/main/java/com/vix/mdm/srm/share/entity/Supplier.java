package com.vix.mdm.srm.share.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;

/**
 * 供应商
 * 
 * @author Ivan 2013-05-21
 */
public class Supplier extends BaseEntity {
	private static final long serialVersionUID = 7202245736042736730L;

	// status:无效、寻源、评估、正式
	public static final String status_void = "4";// 无效,创建页面时4
	public static final String status_source = "1";// 寻源
	public static final String status_auditing = "2";// 待评估
	public static final String status_formal = "3";// 正式

	/** 供应商代码 */
	private String indetity;
	/** 简称 */
	private String shortName;
	/** 拼音 */
	private String pinYin;
	/** 所属分类 */
	private String catalog;
	/** 类型 */
	private String type;
	/** 所属地区 */
	private String region;
	/** 员工人数 */
	private Long employeeCounts;
	/** 母公司代码 */
	private String parentCompanyCode;
	/** 母公司名称 */
	private String parentCompanyName;
	/** 所属行业 */
	private String industry;
	/** 币种 */
	private String currency;
	/** 分类 */
	private String catalogs;
	/** 注册资金 */
	private String registeredCapital;
	/** 开户银行 */
	private String openingBank;
	/** 银行帐号 */
	private String bankAccount;
	/** 税号 */
	private String taxCode;
	/** 法人 */
	private String artificialPerson;
	/** 负责采购员编码 */
	private String purchaserCode;
	/** 采购员 */
	private String purchaser;
	/** 成本中心编码 */
	private String costCenterCode;
	/** 成本中心 */
	private String costCenter;
	/** 是否为委外供应商 */
	private String isOutSourcing;
	/** 委外仓库 */
	private String outSourcingWarehouse;
	/** 委外货位 */
	private String outSourcingPositon;
	/** 是否为服务提供商 */
	private String isServiceProvider;
	/** 是否为国外供应商 */
	private String isForeignSupplier;
	/** 是否直接提供产品 */
	private String isDirectltProvideProduct;
	/** 是否齐批接收 */
	private String isTotalBatchRecieve;
	/** 是否单价接收 */
	private String isNeedUnitPrice;
	/** 是否进行IQC检验 */
	private String isNeedIQC;
	/** 超交处理方式 */
	private String exceedDealType;
	/** 超交百分比 */
	private Integer exceedPercent;
	/** 超交数量 */
	private Integer exceedAmount;
	/** 超交是否付款 */
	private String isPayByExceed;
	/** 等级 */
	private String classIfy;
	/** ABC等级 */
	private String abcClassIfy;
	/** 分管部门 */
	private String chargeDepartment;
	/** 电话 */
	private String telephone;
	/** 手机 */
	private String cellphone;
	/** 邮政编码 */
	private String postalcode;
	/** 联系人 */
	private String contacts;
	/** 传真 */
	private String portraiture;
	/** 到货地址 */
	private String deliveryAddress;
	/** 到货仓库 */
	private String deliveryWarehouse;
	/** Email */
	private String email;
	/** 分类 */
	private SupplierCategory supplierCategory;

	// ！！！！以下对象为配置hbm文件

	/** 资质 */
	private Set<SupplierAptitudeInfo> supplieAptitudeInfo = new HashSet<SupplierAptitudeInfo>();
	/** 地址 */
	private Set<SupplierAddress> supplierAddresses = new HashSet<SupplierAddress>();
	/** 银行信息 */
	private Set<SupplierBankInfo> supplierBankInfos = new HashSet<SupplierBankInfo>();
	/** 财务信息 */
	private Set<SupplierAccountingInfo> supplierAccountingInfos = new HashSet<SupplierAccountingInfo>();
	/** 信用 */
	private Set<SupplierCreditInfo> supplierCreditInfos = new HashSet<SupplierCreditInfo>();
	/** 指标 */
	private Set<SupplierIndicators> supplierIndicators = new HashSet<SupplierIndicators>();
	/** 附件 */
	private Set<Attachments> attachments = new HashSet<Attachments>();
	/** 投标 */
	private Set<SupplierTender> supplierTenders = new HashSet<SupplierTender>();
	/** VMI */
	private Set<SupplierVMI> supplierVMIs = new HashSet<SupplierVMI>();
	/** 父分类ID */
	private Long parentId;
	/** 子分类集合 */
	private Set<Supplier> suppliers = new HashSet<Supplier>();
	/** 父分类 */
	private Supplier supplier;
	/** 招标 */
	private Set<PurchaseTender> purchaseTenders = new HashSet<PurchaseTender>();
	/** 职员 */
	private Set<Employee> employees = new HashSet<Employee>();
	/**
	 * 币种
	 */
	private CurrencyType currencyType;
	/**
	 * 负责人
	 */
	private Employee employee;
	/**
	 * 采购业务员
	 */
	private Employee purchaserEmployee;
	/**
	 * 管理员ID
	 */
	private String employeeId;
	/**
	 * 供应商等级
	 */
	private SupplierLevel supplierLevel;
	public String getIndetity() {
		return indetity;
	}

	public void setIndetity(String indetity) {
		this.indetity = indetity;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getEmployeeCounts() {
		return employeeCounts;
	}

	public void setEmployeeCounts(Long employeeCounts) {
		this.employeeCounts = employeeCounts;
	}

	public String getParentCompanyCode() {
		return parentCompanyCode;
	}

	public void setParentCompanyCode(String parentCompanyCode) {
		this.parentCompanyCode = parentCompanyCode;
	}

	public String getParentCompanyName() {
		return parentCompanyName;
	}

	public void setParentCompanyName(String parentCompanyName) {
		this.parentCompanyName = parentCompanyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(String catalogs) {
		this.catalogs = catalogs;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getArtificialPerson() {
		return artificialPerson;
	}

	public void setArtificialPerson(String artificialPerson) {
		this.artificialPerson = artificialPerson;
	}

	public String getPurchaserCode() {
		return purchaserCode;
	}

	public void setPurchaserCode(String purchaserCode) {
		this.purchaserCode = purchaserCode;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getIsOutSourcing() {
		return isOutSourcing;
	}

	public void setIsOutSourcing(String isOutSourcing) {
		this.isOutSourcing = isOutSourcing;
	}

	public String getOutSourcingWarehouse() {
		return outSourcingWarehouse;
	}

	public void setOutSourcingWarehouse(String outSourcingWarehouse) {
		this.outSourcingWarehouse = outSourcingWarehouse;
	}

	public String getOutSourcingPositon() {
		return outSourcingPositon;
	}

	public void setOutSourcingPositon(String outSourcingPositon) {
		this.outSourcingPositon = outSourcingPositon;
	}

	public String getIsServiceProvider() {
		return isServiceProvider;
	}

	public void setIsServiceProvider(String isServiceProvider) {
		this.isServiceProvider = isServiceProvider;
	}

	public String getIsForeignSupplier() {
		return isForeignSupplier;
	}

	public void setIsForeignSupplier(String isForeignSupplier) {
		this.isForeignSupplier = isForeignSupplier;
	}

	public String getIsDirectltProvideProduct() {
		return isDirectltProvideProduct;
	}

	public void setIsDirectltProvideProduct(String isDirectltProvideProduct) {
		this.isDirectltProvideProduct = isDirectltProvideProduct;
	}

	public String getIsTotalBatchRecieve() {
		return isTotalBatchRecieve;
	}

	public void setIsTotalBatchRecieve(String isTotalBatchRecieve) {
		this.isTotalBatchRecieve = isTotalBatchRecieve;
	}

	public String getIsNeedUnitPrice() {
		return isNeedUnitPrice;
	}

	public void setIsNeedUnitPrice(String isNeedUnitPrice) {
		this.isNeedUnitPrice = isNeedUnitPrice;
	}

	public String getIsNeedIQC() {
		return isNeedIQC;
	}

	public void setIsNeedIQC(String isNeedIQC) {
		this.isNeedIQC = isNeedIQC;
	}

	public String getExceedDealType() {
		return exceedDealType;
	}

	public void setExceedDealType(String exceedDealType) {
		this.exceedDealType = exceedDealType;
	}

	public Integer getExceedPercent() {
		return exceedPercent;
	}

	public void setExceedPercent(Integer exceedPercent) {
		this.exceedPercent = exceedPercent;
	}

	public Integer getExceedAmount() {
		return exceedAmount;
	}

	public void setExceedAmount(Integer exceedAmount) {
		this.exceedAmount = exceedAmount;
	}

	public String getIsPayByExceed() {
		return isPayByExceed;
	}

	public void setIsPayByExceed(String isPayByExceed) {
		this.isPayByExceed = isPayByExceed;
	}

	public String getClassIfy() {
		return classIfy;
	}

	public void setClassIfy(String classIfy) {
		this.classIfy = classIfy;
	}

	public String getAbcClassIfy() {
		return abcClassIfy;
	}

	public void setAbcClassIfy(String abcClassIfy) {
		this.abcClassIfy = abcClassIfy;
	}

	public String getChargeDepartment() {
		return chargeDepartment;
	}

	public void setChargeDepartment(String chargeDepartment) {
		this.chargeDepartment = chargeDepartment;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPortraiture() {
		return portraiture;
	}

	public void setPortraiture(String portraiture) {
		this.portraiture = portraiture;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryWarehouse() {
		return deliveryWarehouse;
	}

	public void setDeliveryWarehouse(String deliveryWarehouse) {
		this.deliveryWarehouse = deliveryWarehouse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<SupplierAptitudeInfo> getSupplieAptitudeInfo() {
		return supplieAptitudeInfo;
	}

	public void setSupplieAptitudeInfo(Set<SupplierAptitudeInfo> supplieAptitudeInfo) {
		this.supplieAptitudeInfo = supplieAptitudeInfo;
	}

	public Set<SupplierAddress> getSupplierAddresses() {
		return supplierAddresses;
	}

	public void setSupplierAddresses(Set<SupplierAddress> supplierAddresses) {
		this.supplierAddresses = supplierAddresses;
	}

	public Set<SupplierBankInfo> getSupplierBankInfos() {
		return supplierBankInfos;
	}

	public void setSupplierBankInfos(Set<SupplierBankInfo> supplierBankInfos) {
		this.supplierBankInfos = supplierBankInfos;
	}

	public Set<SupplierAccountingInfo> getSupplierAccountingInfos() {
		return supplierAccountingInfos;
	}

	public void setSupplierAccountingInfos(Set<SupplierAccountingInfo> supplierAccountingInfos) {
		this.supplierAccountingInfos = supplierAccountingInfos;
	}

	public Set<SupplierCreditInfo> getSupplierCreditInfos() {
		return supplierCreditInfos;
	}

	public void setSupplierCreditInfos(Set<SupplierCreditInfo> supplierCreditInfos) {
		this.supplierCreditInfos = supplierCreditInfos;
	}

	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachments> attachments) {
		this.attachments = attachments;
	}

	public Set<SupplierIndicators> getSupplierIndicators() {
		return supplierIndicators;
	}

	public void setSupplierIndicators(Set<SupplierIndicators> supplierIndicators) {
		this.supplierIndicators = supplierIndicators;
	}

	public SupplierCategory getSupplierCategory() {
		return supplierCategory;
	}

	public void setSupplierCategory(SupplierCategory supplierCategory) {
		this.supplierCategory = supplierCategory;
	}

	public Set<SupplierTender> getSupplierTenders() {
		return supplierTenders;
	}

	public void setSupplierTenders(Set<SupplierTender> supplierTenders) {
		this.supplierTenders = supplierTenders;
	}

	public Set<SupplierVMI> getSupplierVMIs() {
		return supplierVMIs;
	}

	public void setSupplierVMIs(Set<SupplierVMI> supplierVMIs) {
		this.supplierVMIs = supplierVMIs;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Set<PurchaseTender> getPurchaseTenders() {
		return purchaseTenders;
	}

	public void setPurchaseTenders(Set<PurchaseTender> purchaseTenders) {
		this.purchaseTenders = purchaseTenders;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the purchaserEmployee
	 */
	public Employee getPurchaserEmployee() {
		return purchaserEmployee;
	}

	/**
	 * @param purchaserEmployee
	 *            the purchaserEmployee to set
	 */
	public void setPurchaserEmployee(Employee purchaserEmployee) {
		this.purchaserEmployee = purchaserEmployee;
	}

	public SupplierLevel getSupplierLevel() {
		return supplierLevel;
	}

	public void setSupplierLevel(SupplierLevel supplierLevel) {
		this.supplierLevel = supplierLevel;
	}
}
