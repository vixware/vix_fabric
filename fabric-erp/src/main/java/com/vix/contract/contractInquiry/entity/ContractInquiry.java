package com.vix.contract.contractInquiry.entity;

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
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @ClassName: ContractInquiry
 * @Description: 合同查询
 * @author bobchen
 * @date 2015年12月25日 上午9:29:32
 *
 */
public class ContractInquiry extends BaseEntity {

	private static final long serialVersionUID = 131934666340770915L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 主合同编码 */
	private String masterContractCoding;
	/** 合同编号 */
	private String contractCode;
	/** 合同名称 */
	private String contractName;
	/** 项目代码 */
	private String projectCode;
	/** 项目名称 */
	private String projectName;
	/** 币种 */
	private String currency;
	/** 状态 */
	private String mode;
	/** 是否发布 0 待审批 1通过 */
	public Integer isPublish;
	/** 合同类型 */
	private Integer contractType;
	/** 类型编码 */
	private String typeCode;
	/** 签订日期 */
	private Date signDate;
	/** 合同起始时间 */
	private Date contractStartTime;
	/** 合同终止时间 */
	private Date contractEndTime;
	/** 经办人 */
	private String operator;
	/** 谈判人 */
	private String negotiator;
	/** 审批人 */
	private String approval;
	/** 审批日期 */
	private Date approvalDate;
	/** 部门名称 */
	private String departmentName;
	/** 合同总数量 */
	private String contractTotalQuantity;
	/** 合同执行数量 */
	private String numberContractExecution;
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
	/** 总金额 */
	private Double totalAmount;
	/** 合同执行金额 */
	private Double contractExecutionMoney;
	/** 预付款金额 */
	private String prepaymentAmount;
	/** 未结金额 */
	private String outstandingAmounts;
	/** 质保金计算方式 */
	private String retentionCalculation;
	/** 质保金开始日期 */
	private Date retentionsStartDate;
	/** 质保金结束日期 */
	private Date retentionEndDate;
	/** 质保金比例 */
	private String retentionRatio;
	/** 质保金额度 */
	private Double warrantyAmount;
	/** 保修期 */
	private String warranty;
	/** 主要内容 */
	private String mainContent;
	/** 备注 */
	private String remark;

	/** 合同子项明细 */
	private Set<ContractChildItem> contractChildItems = new HashSet<ContractChildItem>();
	/** 合同标的明细 */
	private Set<ContractSubject> contractSubjects = new HashSet<ContractSubject>();
	/** 合同预警明细 */
	private Set<ContractWarning> contractWarnings = new HashSet<ContractWarning>();
	/** 合同审批单 */
	private Set<ApplicationForm> applicationForms = new HashSet<ApplicationForm>();
	/** 价格条件 */
	private Set<PriceConditions> priceConditionss = new HashSet<PriceConditions>();
	/** 合同采购定价条件 */
	private Set<ContractPricingConditions> contractPricingConditionss = new HashSet<ContractPricingConditions>();
	/** 销售定价条件 */
	private Set<ContractMarket> contractMarkets = new HashSet<ContractMarket>();
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

	public Set<ContractChildItem> getContractChildItems() {
		return contractChildItems;
	}

	public void setContractChildItems(Set<ContractChildItem> contractChildItems) {
		this.contractChildItems = contractChildItems;
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

	public String getMasterContractCoding() {
		return masterContractCoding;
	}

	public void setMasterContractCoding(String masterContractCoding) {
		this.masterContractCoding = masterContractCoding;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
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

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
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

	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public Date getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
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

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}

	public String getFirstPartyAddress() {
		return firstPartyAddress;
	}

	public void setFirstPartyAddress(String firstPartyAddress) {
		this.firstPartyAddress = firstPartyAddress;
	}

	public String getPartyUnitName() {
		return partyUnitName;
	}

	public void setPartyUnitName(String partyUnitName) {
		this.partyUnitName = partyUnitName;
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

	public String getSecondPartyId() {
		return secondPartyId;
	}

	public void setSecondPartyId(String secondPartyId) {
		this.secondPartyId = secondPartyId;
	}

	public String getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(String secondParty) {
		this.secondParty = secondParty;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getContractExecutionMoney() {
		return contractExecutionMoney;
	}

	public void setContractExecutionMoney(Double contractExecutionMoney) {
		this.contractExecutionMoney = contractExecutionMoney;
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

	public String getRetentionCalculation() {
		return retentionCalculation;
	}

	public void setRetentionCalculation(String retentionCalculation) {
		this.retentionCalculation = retentionCalculation;
	}

	public Date getRetentionsStartDate() {
		return retentionsStartDate;
	}

	public void setRetentionsStartDate(Date retentionsStartDate) {
		this.retentionsStartDate = retentionsStartDate;
	}

	public Date getRetentionEndDate() {
		return retentionEndDate;
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

	public Set<ContractSubject> getContractSubjects() {
		return contractSubjects;
	}

	public void setContractSubjects(Set<ContractSubject> contractSubjects) {
		this.contractSubjects = contractSubjects;
	}

	public Set<ContractWarning> getContractWarnings() {
		return contractWarnings;
	}

	public void setContractWarnings(Set<ContractWarning> contractWarnings) {
		this.contractWarnings = contractWarnings;
	}

	public Set<PriceConditions> getPriceConditionss() {
		return priceConditionss;
	}

	public void setPriceConditionss(Set<PriceConditions> priceConditionss) {
		this.priceConditionss = priceConditionss;
	}

	public Set<ApplicationForm> getApplicationForms() {
		return applicationForms;
	}

	public void setApplicationForms(Set<ApplicationForm> applicationForms) {
		this.applicationForms = applicationForms;
	}

	public Set<ContractPricingConditions> getContractPricingConditionss() {
		return contractPricingConditionss;
	}

	public void setContractPricingConditionss(Set<ContractPricingConditions> contractPricingConditionss) {
		this.contractPricingConditionss = contractPricingConditionss;
	}

	public Set<ContractMarket> getContractMarkets() {
		return contractMarkets;
	}

	public void setContractMarkets(Set<ContractMarket> contractMarkets) {
		this.contractMarkets = contractMarkets;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

}
