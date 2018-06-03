package com.vix.contract.mamanger.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.ExchangeRate;
import com.vix.contract.config.entity.ContractGroupType;
import com.vix.contract.config.entity.ContractNatureType;
import com.vix.contract.config.entity.ContractStageGroupType;
import com.vix.contract.config.entity.ContractTypeCombine;
import com.vix.contract.config.entity.EnableStageType;
import com.vix.contract.config.entity.ModeType;
import com.vix.contract.config.entity.ViewIndustryType;
import com.vix.core.utils.DateUtil;
import com.vix.crm.project.entity.CrmProject;

/**
 * @ClassName: Contract
 * @Description:合同
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-8 上午9:56:12
 */
public class Contract extends BaseEntity {

	private static final long serialVersionUID = -2801104912462819966L;
	/**
	 * status   0,正常    1,即将到期      2,已经到期
	 */
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 合同编号 */
	private String contractCode;
	/** 项目名称 */
	private String projectName;
	/**
	 * 项目   
	 */
	private CrmProject crmProject;
	/** 审批编号 */
	private String contractApproveCode;
	/** 项目代码 */
	private String projectCode;
	/** 项目大类 */
	private String projectCatalog;
	/** 项目子类 */
	private String projectSubCatalog;
	/** 合同名称 */
	private String contractName;
	/** 合同类型 */
	private Integer contractType;//1.采购合同   2,采购框架协议   3,销售合同   4 销售框架协议   5项目合同 6,劳动合同
	/** 类型编码 */
	private String typeCode;
	/** 合同付款类型 */
	private String paymentType;
	/** 是否开口合同 */
	private String isOpenValueContract;
	/** 甲方ID */
	private long firstPartyId;
	/** 甲方名称 */
	private String firstParty;
	/** 甲方地址 */
	private String firstPartyAddress;
	/** 甲方单位名称 */
	private String partyUnitName;
	/** 甲方联系人 */
	private String firstPartyContact;
	/** 甲方联系电话 */
	private String firstPartyPhone;
	/** 甲方邮件 */
	private String firstPartyEmail;
	/** 乙方ID */
	private String secondPartyId;
	/** 乙方名称 */
	private String secondParty;
	/** 乙方地址 */
	private String baddress;
	/** 乙方单位名称 */
	private String bunitName;
	/** 乙方联系人 */
	private String bcontact;
	/** 乙方联系电话 */
	private String bphone;
	/** 乙方邮件 */
	private String email;
	/** 丙方 */
	private String thirdParty;
	/** 丙方地址 */
	private String partyCAddress;
	/**
	 * 丙方单位名称
	 */
	private String partyCUnitName;
	/**
	 * 丙方联系人
	 */
	private String partyCContact;
	/**
	 * 丙方联系电话
	 */
	private String partyCPhone;
	/**
	 * 丙方邮件
	 */
	private String partyCEmail;
	/**
	 * 主合同编码
	 */
	private String masterContractCoding;
	/**
	 * 所属合同组编码
	 */
	private String belongsContractGroupTypeCode;
	/**
	 * 合同执行金额
	 */
	private Double contractExecutionMoney;
	/**
	 * 质保金计算方式
	 */
	private String retentionCalculation;
	/**
	 * 质保金开始日期
	 */
	private Date retentionsStartDate;
	/**
	 * 质保金结束日期
	 */
	private Date retentionEndDate;
	/**
	 * 质保金比例
	 */
	private String retentionRatio;
	/**
	 * 质保金额度
	 */
	private Double warrantyAmount;
	/**
	 * 保修期
	 */
	private String warranty;
	/**
	 * 时效控制方式
	 */
	private String agingcontrol;
	/**
	 * 时效控制方式编码
	 */
	private String agingcontroltypecode;
	/**
	 * 时效控制环节
	 */
	private String agingControllink;
	/**
	 * 时效控制环节编码
	 */
	private String agingControlLinkTypeCode;
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 业务类型编码
	 */
	private String businessTypeCode;
	/**
	 * 合同总数量
	 */
	private String contractTotalQuantity;
	/**
	 * 合同执行数量
	 */
	private String numberContractExecution;
	/**
	 * 启用阶段
	 */
	private String enableStage;
	/**
	 * 启用阶段编码
	 */
	private String enableStageTypeCode;
	/**
	 * 合同阶段组
	 */
	private String contractStageGroup;
	/**
	 * 合同阶段组编码
	 */
	private String contractStageGroupTypeCode;
	/**
	 * 总金额
	 */
	private Double totalAmount;
	/**
	 * 预付金额
	 */
	private Double prepay;
	/**
	 * 收付款类型
	 */
	private String payrecieveType;
	/**
	 * 币种编码
	 */
	private String moneyTypeCode;
	/**
	 * 印花税
	 */
	private String stampTax;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 部门编码
	 */
	/*
	 * private String departmentCode;
	 */
	/**
	 * 部门名称
	 */
	private String departmentName;
	/**
	 * 对方单位名称
	 */
	private String nameEachOther;
	/**
	 * 对方联系人
	 */
	private String otherContacts;
	/**
	 * 联系电话
	 */
	private String otherPhone;
	/**
	 * 所属行业
	 */
	private String viewIndustry;
	/**
	 * 所属行业编码
	 */
	private String viewIndustryCode;
	/**
	 * 合同性质
	 */
	private String contractNature;
	/**
	 * 合同性质编码
	 */
	private String contractNatureCode;
	/**
	 * 所属部门
	 */
	private String viewDepartment;
	/**
	 * 所属部门编码
	 */
	private String viewDepartmentTypeCode;
	/**
	 * 签订日期
	 */
	private Date signDate;
	/**
	 * 合同起始时间
	 */
	private Date contractStartTime;
	/**
	 * 合同终止时间
	 */
	private Date contractEndTime;
	/**
	 * 合同是否通过审批
	 */
	private String isPassApprove;
	/**
	 * 拟稿人
	 */
	private String drafter;
	/**
	 * 经办人
	 */
	private String operator;
	/**
	 * 谈判人
	 */
	private String negotiator;
	/**
	 * 审批人
	 */
	private String approval;
	/**
	 * 合同标的
	 */
	/*
	 * private String contractSubject;
	 */
	/**
	 * 预付款金额
	 */
	private String prepaymentAmount;
	/**
	 * 未结金额
	 */
	private String outstandingAmounts;
	/**
	 * 主要内容
	 */
	private String mainContent;
	/**
	 * 是否拒投票
	 */
	private String isbid;
	/**
	 * 是否拒投票编码
	 */
	private String isbidtypecode;
	/**
	 * 是否历史合同
	 */
	private String isHistory;
	/** 是否归档 */
	private String isArchived;
	/** 备注 */
	private String remark;
	/** 主题 */
	private String theme;
	/** 状态 */
	private String mode;
	/** 状态编码 */
	private String modetypecode;
	/** 签订日期 */
	private Date signingDate;
	/** 审批日期 */
	private Date approvalDate;
	/** 合同预警 */
	private Set<ContractWarning> contractWarning = new HashSet<ContractWarning>();
	/** 合同子项 */
	private Set<ContractLine> contractLines = new HashSet<ContractLine>();
	/** 合同附件 */
	private Set<ContractTemplate> contractTemplate = new HashSet<ContractTemplate>();
	/** 合同审批单 */
	private Set<ApplicationForm> applicationForm = new HashSet<ApplicationForm>();
	/** 价格条件 */
	private Set<PriceConditions> priceConditions = new HashSet<PriceConditions>();
	/** 合同标的 */
	private Set<ContractSubject> contractSubject = new HashSet<ContractSubject>();
	/** 项目合同 */
	private Set<PmContract> pmContract = new HashSet<PmContract>();
	/** 销售定价条件 */
	private Set<ContractMarket> contractMarket = new HashSet<ContractMarket>();
	/** 合同采购定价条件 */
	private Set<ContractPricingConditions> contractPricingConditions = new HashSet<ContractPricingConditions>();
	/** 合同销售定价条件 */
	private Set<SalesPricing> salesPricing = new HashSet<SalesPricing>();
	/** 所属合同组 */
	private ContractGroupType contractGroupType;
	/** 合同类型集合 */
	private ContractTypeCombine contractTypeCombine;
	/** 合同性质 */
	private ContractNatureType contractNatureType;
	/** 合同性质 */
	private ViewIndustryType viewIndustryType;
	/** 合同履行状态 */
	private ModeType modeType;
	/** 启用阶段 */
	private EnableStageType enableStageType;
	/** 合同阶段组 */
	private ContractStageGroupType contractStageGroupType;
	/** 币种 */
	private CurrencyType currencyType;
	/** 汇率 */
	private ExchangeRate exchangeRate;
	/**
	 * 即将到期的合同剩余天数
	 */
	private Long dayTime;
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getBelongsContractGroupTypeCode() {
		return belongsContractGroupTypeCode;
	}

	public void setBelongsContractGroupTypeCode(String belongsContractGroupTypeCode) {
		this.belongsContractGroupTypeCode = belongsContractGroupTypeCode;
	}

	public String getContractApproveCode() {
		return contractApproveCode;
	}

	public String getViewDepartmentTypeCode() {
		return viewDepartmentTypeCode;
	}

	public void setViewDepartmentTypeCode(String viewDepartmentTypeCode) {
		this.viewDepartmentTypeCode = viewDepartmentTypeCode;
	}

	public String getAgingControlLinkTypeCode() {
		return agingControlLinkTypeCode;
	}

	public void setAgingControlLinkTypeCode(String agingControlLinkTypeCode) {
		this.agingControlLinkTypeCode = agingControlLinkTypeCode;
	}

	public String getContractStageGroupTypeCode() {
		return contractStageGroupTypeCode;
	}

	public void setContractStageGroupTypeCode(String contractStageGroupTypeCode) {
		this.contractStageGroupTypeCode = contractStageGroupTypeCode;
	}

	public String getAgingcontroltypecode() {
		return agingcontroltypecode;
	}

	public void setAgingcontroltypecode(String agingcontroltypecode) {
		this.agingcontroltypecode = agingcontroltypecode;
	}

	public String getIsbidtypecode() {
		return isbidtypecode;
	}

	public void setIsbidtypecode(String isbidtypecode) {
		this.isbidtypecode = isbidtypecode;
	}

	public String getModetypecode() {
		return modetypecode;
	}

	public void setModetypecode(String modetypecode) {
		this.modetypecode = modetypecode;
	}

	public void setContractApproveCode(String contractApproveCode) {
		this.contractApproveCode = contractApproveCode;
	}

	public String getMoneyTypeCode() {
		return moneyTypeCode;
	}

	public void setMoneyTypeCode(String moneyTypeCode) {
		this.moneyTypeCode = moneyTypeCode;
	}

	public String getViewIndustryCode() {
		return viewIndustryCode;
	}

	public void setViewIndustryCode(String viewIndustryCode) {
		this.viewIndustryCode = viewIndustryCode;
	}

	public String getIsOpenValueContract() {
		return isOpenValueContract;
	}

	public void setIsOpenValueContract(String isOpenValueContract) {
		this.isOpenValueContract = isOpenValueContract;
	}

	public String getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}

	public String getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(String secondParty) {
		this.secondParty = secondParty;
	}

	public Date getSignDate() {
		return signDate;
	}
	public String getSignDateStr() {
		if(signDate != null){
			return DateUtil.format(signDate);
		}
		return "";
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getContractStartTime() {
		return contractStartTime;
	}
	public String getContractStartTimeStr() {
		if(contractStartTime != null){
			return DateUtil.format(contractStartTime);
		}
		return "";
	}

	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public Date getContractEndTime() {
		return contractEndTime;
	}
	public String getContractEndTimeStr() {
		if(contractEndTime != null){
			return DateUtil.format(contractEndTime);
		}
		return "";
	}

	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	public String getDrafter() {
		return drafter;
	}

	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getNegotiator() {
		return negotiator;
	}

	public void setNegotiator(String negotiator) {
		this.negotiator = negotiator;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getIsbid() {
		return isbid;
	}

	public void setIsbid(String isbid) {
		this.isbid = isbid;
	}

	public String getIsHistory() {
		return isHistory;
	}

	public void setIsHistory(String isHistory) {
		this.isHistory = isHistory;
	}

	public String getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(String isArchived) {
		this.isArchived = isArchived;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getProjectCatalog() {
		return projectCatalog;
	}

	public void setProjectCatalog(String projectCatalog) {
		this.projectCatalog = projectCatalog;
	}

	public String getEnableStageTypeCode() {
		return enableStageTypeCode;
	}

	public void setEnableStageTypeCode(String enableStageTypeCode) {
		this.enableStageTypeCode = enableStageTypeCode;
	}

	public String getProjectSubCatalog() {
		return projectSubCatalog;
	}

	public void setProjectSubCatalog(String projectSubCatalog) {
		this.projectSubCatalog = projectSubCatalog;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getFirstPartyAddress() {
		return firstPartyAddress;
	}

	public void setFirstPartyAddress(String firstPartyAddress) {
		this.firstPartyAddress = firstPartyAddress;
	}

	public String getFirstPartyContact() {
		return firstPartyContact;
	}

	public void setFirstPartyContact(String firstPartyContact) {
		this.firstPartyContact = firstPartyContact;
	}

	public String getFirstPartyPhone() {
		return firstPartyPhone;
	}

	public void setFirstPartyPhone(String firstPartyPhone) {
		this.firstPartyPhone = firstPartyPhone;
	}

	public String getFirstPartyEmail() {
		return firstPartyEmail;
	}

	public void setFirstPartyEmail(String firstPartyEmail) {
		this.firstPartyEmail = firstPartyEmail;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public Double getPrepay() {
		return prepay;
	}

	public void setPrepay(Double prepay) {
		this.prepay = prepay;
	}

	public String getPayrecieveType() {
		return payrecieveType;
	}

	public void setPayrecieveType(String payrecieveType) {
		this.payrecieveType = payrecieveType;
	}

	public String getStampTax() {
		return stampTax;
	}

	public void setStampTax(String stampTax) {
		this.stampTax = stampTax;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getIsPassApprove() {
		return isPassApprove;
	}

	public void setIsPassApprove(String isPassApprove) {
		this.isPassApprove = isPassApprove;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public String getContractNatureCode() {
		return contractNatureCode;
	}

	public void setContractNatureCode(String contractNatureCode) {
		this.contractNatureCode = contractNatureCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Date getSigningDate() {
		return signingDate;
	}
	public String getSigningDateStr() {
		if(signingDate != null){
			return DateUtil.format(signingDate);
		}
		return "";
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameEachOther() {
		return nameEachOther;
	}

	public void setNameEachOther(String nameEachOther) {
		this.nameEachOther = nameEachOther;
	}

	public String getOtherContacts() {
		return otherContacts;
	}

	public void setOtherContacts(String otherContacts) {
		this.otherContacts = otherContacts;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getViewIndustry() {
		return viewIndustry;
	}

	public void setViewIndustry(String viewIndustry) {
		this.viewIndustry = viewIndustry;
	}

	public String getContractNature() {
		return contractNature;
	}

	public void setContractNature(String contractNature) {
		this.contractNature = contractNature;
	}

	public String getViewDepartment() {
		return viewDepartment;
	}

	public void setViewDepartment(String viewDepartment) {
		this.viewDepartment = viewDepartment;
	}

	public String getPrepaymentAmount() {
		return prepaymentAmount;
	}

	public void setPrepaymentAmount(String prepaymentAmount) {
		this.prepaymentAmount = prepaymentAmount;
	}

	public String getOutstandingAmounts() {
		return outstandingAmounts;
	}

	public void setOutstandingAmounts(String outstandingAmounts) {
		this.outstandingAmounts = outstandingAmounts;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<ContractWarning> getContractWarning() {
		return contractWarning;
	}

	public void setContractWarning(Set<ContractWarning> contractWarning) {
		this.contractWarning = contractWarning;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Set<ContractLine> getContractLines() {
		return contractLines;
	}

	public Set<ContractTemplate> getContractTemplate() {
		return contractTemplate;
	}

	public void setContractLines(Set<ContractLine> contractLines) {
		this.contractLines = contractLines;
	}

	public void setContractTemplate(Set<ContractTemplate> contractTemplate) {
		this.contractTemplate = contractTemplate;
	}

	public Set<ApplicationForm> getApplicationForm() {
		return applicationForm;
	}

	public Set<PriceConditions> getPriceConditions() {
		return priceConditions;
	}

	public void setPriceConditions(Set<PriceConditions> priceConditions) {
		this.priceConditions = priceConditions;
	}

	public Set<ContractSubject> getContractSubject() {
		return contractSubject;
	}

	public void setContractSubject(Set<ContractSubject> contractSubject) {
		this.contractSubject = contractSubject;
	}

	public void setApplicationForm(Set<ApplicationForm> applicationForm) {
		this.applicationForm = applicationForm;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getPartyUnitName() {
		return partyUnitName;
	}

	public void setPartyUnitName(String partyUnitName) {
		this.partyUnitName = partyUnitName;
	}

	public String getPartyCAddress() {
		return partyCAddress;
	}

	public void setPartyCAddress(String partyCAddress) {
		this.partyCAddress = partyCAddress;
	}

	public String getPartyCUnitName() {
		return partyCUnitName;
	}

	public void setPartyCUnitName(String partyCUnitName) {
		this.partyCUnitName = partyCUnitName;
	}

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getPartyCContact() {
		return partyCContact;
	}

	public void setPartyCContact(String partyCContact) {
		this.partyCContact = partyCContact;
	}

	public String getPartyCPhone() {
		return partyCPhone;
	}

	public void setPartyCPhone(String partyCPhone) {
		this.partyCPhone = partyCPhone;
	}

	public String getPartyCEmail() {
		return partyCEmail;
	}

	public void setPartyCEmail(String partyCEmail) {
		this.partyCEmail = partyCEmail;
	}

	public String getMasterContractCoding() {
		return masterContractCoding;
	}

	public void setMasterContractCoding(String masterContractCoding) {
		this.masterContractCoding = masterContractCoding;
	}

	public Double getContractExecutionMoney() {
		return contractExecutionMoney;
	}

	public void setContractExecutionMoney(Double contractExecutionMoney) {
		this.contractExecutionMoney = contractExecutionMoney;
	}

	public String getRetentionCalculation() {
		return retentionCalculation;
	}

	public void setRetentionCalculation(String retentionCalculation) {
		this.retentionCalculation = retentionCalculation;
	}

	public Date getRetentionsStartDate() {
		return retentionsStartDate;
	}
	public String getRetentionsStartDateStr() {
		if(retentionsStartDate != null){
			return DateUtil.format(retentionsStartDate);
		}
		return "";
	}

	public void setRetentionsStartDate(Date retentionsStartDate) {
		this.retentionsStartDate = retentionsStartDate;
	}

	public Date getRetentionEndDate() {
		return retentionEndDate;
	}
	public String getRetentionEndDateStr() {
		if(retentionEndDate != null){
			return DateUtil.format(retentionEndDate);
		}
		return "";
	}

	public void setRetentionEndDate(Date retentionEndDate) {
		this.retentionEndDate = retentionEndDate;
	}

	public String getRetentionRatio() {
		return retentionRatio;
	}

	public void setRetentionRatio(String retentionRatio) {
		this.retentionRatio = retentionRatio;
	}

	public Double getWarrantyAmount() {
		return warrantyAmount;
	}

	public void setWarrantyAmount(Double warrantyAmount) {
		this.warrantyAmount = warrantyAmount;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getAgingcontrol() {
		return agingcontrol;
	}

	public void setAgingcontrol(String agingcontrol) {
		this.agingcontrol = agingcontrol;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getContractTotalQuantity() {
		return contractTotalQuantity;
	}

	public void setContractTotalQuantity(String contractTotalQuantity) {
		this.contractTotalQuantity = contractTotalQuantity;
	}

	public String getNumberContractExecution() {
		return numberContractExecution;
	}

	public void setNumberContractExecution(String numberContractExecution) {
		this.numberContractExecution = numberContractExecution;
	}

	public String getEnableStage() {
		return enableStage;
	}

	public void setEnableStage(String enableStage) {
		this.enableStage = enableStage;
	}

	public String getContractStageGroup() {
		return contractStageGroup;
	}

	public void setContractStageGroup(String contractStageGroup) {
		this.contractStageGroup = contractStageGroup;
	}

	public String getBusinessTypeCode() {
		return businessTypeCode;
	}

	public void setBusinessTypeCode(String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}
	public String getApprovalDateStr() {
		if(null != approvalDate){
			return DateUtil.format(approvalDate);
		}
		return "";
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getBaddress() {
		return baddress;
	}

	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}

	public String getBunitName() {
		return bunitName;
	}

	public void setBunitName(String bunitName) {
		this.bunitName = bunitName;
	}

	public String getBcontact() {
		return bcontact;
	}

	public void setBcontact(String bcontact) {
		this.bcontact = bcontact;
	}

	public String getBphone() {
		return bphone;
	}

	public void setBphone(String bphone) {
		this.bphone = bphone;
	}

	public String getAgingControllink() {
		return agingControllink;
	}

	public void setAgingControllink(String agingControllink) {
		this.agingControllink = agingControllink;
	}

	public Set<ContractPricingConditions> getContractPricingConditions() {
		return contractPricingConditions;
	}

	public void setContractPricingConditions(Set<ContractPricingConditions> contractPricingConditions) {
		this.contractPricingConditions = contractPricingConditions;
	}

	public Set<SalesPricing> getSalesPricing() {
		return salesPricing;
	}

	public void setSalesPricing(Set<SalesPricing> salesPricing) {
		this.salesPricing = salesPricing;
	}

	public ContractGroupType getContractGroupType() {
		return contractGroupType;
	}

	public void setContractGroupType(ContractGroupType contractGroupType) {
		this.contractGroupType = contractGroupType;
	}

	public ContractTypeCombine getContractTypeCombine() {
		return contractTypeCombine;
	}

	public void setContractTypeCombine(ContractTypeCombine contractTypeCombine) {
		this.contractTypeCombine = contractTypeCombine;
	}

	public ContractNatureType getContractNatureType() {
		return contractNatureType;
	}

	public void setContractNatureType(ContractNatureType contractNatureType) {
		this.contractNatureType = contractNatureType;
	}

	public ViewIndustryType getViewIndustryType() {
		return viewIndustryType;
	}

	public void setViewIndustryType(ViewIndustryType viewIndustryType) {
		this.viewIndustryType = viewIndustryType;
	}

	public ModeType getModeType() {
		return modeType;
	}

	public void setModeType(ModeType modeType) {
		this.modeType = modeType;
	}

	public EnableStageType getEnableStageType() {
		return enableStageType;
	}

	public void setEnableStageType(EnableStageType enableStageType) {
		this.enableStageType = enableStageType;
	}

	public ContractStageGroupType getContractStageGroupType() {
		return contractStageGroupType;
	}

	public void setContractStageGroupType(ContractStageGroupType contractStageGroupType) {
		this.contractStageGroupType = contractStageGroupType;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Set<ContractMarket> getContractMarket() {
		return contractMarket;
	}

	public void setContractMarket(Set<ContractMarket> contractMarket) {
		this.contractMarket = contractMarket;
	}

	public Set<PmContract> getPmContract() {
		return pmContract;
	}

	public void setPmContract(Set<PmContract> pmContract) {
		this.pmContract = pmContract;
	}

	public long getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(long firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	public String getSecondPartyId() {
		return secondPartyId;
	}

	public void setSecondPartyId(String secondPartyId) {
		this.secondPartyId = secondPartyId;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public Long getDayTime() {
		return dayTime;
	}

	public void setDayTime(Long dayTime) {
		this.dayTime = dayTime;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}
}
