package com.vix.mdm.fa.certificates.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.fa.setting.entity.VocherType;

public class Vocher extends BaseEntity {

	private static final long serialVersionUID = 5517791646676466236L;
	/** 账套编码 */
	private String ledgerCode;
	/** 职员编码 */
	private String personCode;
	/** 客户编码 */
	private String customerCode;
	/** 供应商编码 */
	private String supplierCode;
	/** 会计期间 */
	private String period;
	/** 科目编码 */
	private String accountCode;
	/** 凭证类别字 */
	private String csign;
	/** 凭证类别排序号 */
	private String isignseq;
	/** 行号 */
	private String lineNo;
	/** 附单据数 */
	private String docNumber;
	/** 审核人 */
	private String checker;
	/** 记账人 */
	private String booker;
	/** 记账标志 */
	private String bookSign;
	/** 出纳签字人 */
	private String cashier;
	/** 凭证标志 */
	private String flag;
	/** 摘要 */
	private String digest;
	/** 币种名称 */
	private String currencyName;
	/** 借方金额 */
	private Double debitAmount;
	/** 贷方金额 */
	private Double creditAmount;
	/** 外币借方金额 */
	private Double foreignDebitAmount;
	/** 外币贷方金额 */
	private Double foreginCreditAmount;
	/** 汇率 */
	private Double exchangeRate;
	/** 数量借方 */
	private Double numberDebit;
	/** 数量贷方 */
	private Double nubmerCredit;
	/** 结算方式编码 */
	private String settleStyleCode;
	/** 票据号 */
	private String billCode;
	/** 票号发生日期 */
	private Date billCreateDate;
	/** 项目编码 */
	private String itemCode;
	/** 项目大类编码 */
	private String itemCatalogCode;
	/** 业务员 */
	private String personName;
	/** 对方科目编码 */
	private String ccode_equal;
	/** 银行账两清标志 */
	private String isBankClearFlag;
	/** 往来账两清标志 */
	private String isAcountCurrentClearFlag;
	/** 是否核销 */
	private String isCancelAfterVerify;
	/** 外部凭证账套号 */
	private String outAccountLedger;
	/** 外部凭证会计年度 */
	private String outAccountYear;
	/** 外部凭证系统名称 */
	private String outSystemName;
	/** 外部凭证系统版本号 */
	private String outSystemVersion;
	/** 外部凭证制单日期 */
	private Date outBillDate;
	/** 外部凭证会计期间 */
	private String outPeriod;
	/** 外部凭证业务类型 */
	private String outVocherStyle;
	/** 外部凭证业务号 */
	private String outVocherBizCode;
	/** 外部凭证单据日期 */
	private Date outDate;
	/** 外部凭证单据类型 */
	private String outBillSign;
	/** 外部凭证单据号 */
	private String outBillCode;
	/** 凭证是否可修改 */
	private String isVoucherEdit;
	/** 凭证是否可增加删除 */
	private String isVvoucherAddordele;
	/** 凭证合计金额是否保值 */
	private String isVoucherMoneyHold;
	/** 分录数值是否可修改 */
	private String isEntryValueEdit;
	/** 分录科目是否可修改 */
	private String isEntryAccountEdit;
	/** 分录受控科目可用状态 */
	private String ccodecontrol;
	/** 分录往来项是否可修改 */
	private String isEntryPCSEdit;
	/** 分录部门是否可修改 */
	private String isEntryDepartmentEdit;
	/** 分录项目是否可修改 */
	private String isItemEdit;
	/** 分录往来项是否必输 */
	private String isCusSupMustInput;
	/** 凭证类型 */
	private VocherType vocherType;
	/** 审核日期 */
	private Date checkTime;
	/** 借方金额合计 */
	private Double debitAmountTotal;
	/** 贷方金额合计*/
	private Double creditAmountTotal;
	/** 金额合计*/
	private Double amountTotal;

	public String getLedgerCode() {
		return ledgerCode;
	}

	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getCsign() {
		return csign;
	}

	public void setCsign(String csign) {
		this.csign = csign;
	}

	public String getIsignseq() {
		return isignseq;
	}

	public void setIsignseq(String isignseq) {
		this.isignseq = isignseq;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getBooker() {
		return booker;
	}

	public void setBooker(String booker) {
		this.booker = booker;
	}

	public String getBookSign() {
		return bookSign;
	}

	public void setBookSign(String bookSign) {
		this.bookSign = bookSign;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Double getForeignDebitAmount() {
		return foreignDebitAmount;
	}

	public void setForeignDebitAmount(Double foreignDebitAmount) {
		this.foreignDebitAmount = foreignDebitAmount;
	}

	public Double getForeginCreditAmount() {
		return foreginCreditAmount;
	}

	public void setForeginCreditAmount(Double foreginCreditAmount) {
		this.foreginCreditAmount = foreginCreditAmount;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Double getNumberDebit() {
		return numberDebit;
	}

	public void setNumberDebit(Double numberDebit) {
		this.numberDebit = numberDebit;
	}

	public Double getNubmerCredit() {
		return nubmerCredit;
	}

	public void setNubmerCredit(Double nubmerCredit) {
		this.nubmerCredit = nubmerCredit;
	}

	public String getSettleStyleCode() {
		return settleStyleCode;
	}

	public void setSettleStyleCode(String settleStyleCode) {
		this.settleStyleCode = settleStyleCode;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public Date getBillCreateDate() {
		return billCreateDate;
	}

	public void setBillCreateDate(Date billCreateDate) {
		this.billCreateDate = billCreateDate;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemCatalogCode() {
		return itemCatalogCode;
	}

	public void setItemCatalogCode(String itemCatalogCode) {
		this.itemCatalogCode = itemCatalogCode;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getCcode_equal() {
		return ccode_equal;
	}

	public void setCcode_equal(String ccode_equal) {
		this.ccode_equal = ccode_equal;
	}

	public String getIsBankClearFlag() {
		return isBankClearFlag;
	}

	public void setIsBankClearFlag(String isBankClearFlag) {
		this.isBankClearFlag = isBankClearFlag;
	}

	public String getIsAcountCurrentClearFlag() {
		return isAcountCurrentClearFlag;
	}

	public void setIsAcountCurrentClearFlag(String isAcountCurrentClearFlag) {
		this.isAcountCurrentClearFlag = isAcountCurrentClearFlag;
	}

	public String getIsCancelAfterVerify() {
		return isCancelAfterVerify;
	}

	public void setIsCancelAfterVerify(String isCancelAfterVerify) {
		this.isCancelAfterVerify = isCancelAfterVerify;
	}

	public String getOutAccountLedger() {
		return outAccountLedger;
	}

	public void setOutAccountLedger(String outAccountLedger) {
		this.outAccountLedger = outAccountLedger;
	}

	public String getOutAccountYear() {
		return outAccountYear;
	}

	public void setOutAccountYear(String outAccountYear) {
		this.outAccountYear = outAccountYear;
	}

	public String getOutSystemName() {
		return outSystemName;
	}

	public void setOutSystemName(String outSystemName) {
		this.outSystemName = outSystemName;
	}

	public String getOutSystemVersion() {
		return outSystemVersion;
	}

	public void setOutSystemVersion(String outSystemVersion) {
		this.outSystemVersion = outSystemVersion;
	}

	public Date getOutBillDate() {
		return outBillDate;
	}

	public void setOutBillDate(Date outBillDate) {
		this.outBillDate = outBillDate;
	}

	public String getOutPeriod() {
		return outPeriod;
	}

	public void setOutPeriod(String outPeriod) {
		this.outPeriod = outPeriod;
	}

	public String getOutVocherStyle() {
		return outVocherStyle;
	}

	public void setOutVocherStyle(String outVocherStyle) {
		this.outVocherStyle = outVocherStyle;
	}

	public String getOutVocherBizCode() {
		return outVocherBizCode;
	}

	public void setOutVocherBizCode(String outVocherBizCode) {
		this.outVocherBizCode = outVocherBizCode;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutBillSign() {
		return outBillSign;
	}

	public void setOutBillSign(String outBillSign) {
		this.outBillSign = outBillSign;
	}

	public String getOutBillCode() {
		return outBillCode;
	}

	public void setOutBillCode(String outBillCode) {
		this.outBillCode = outBillCode;
	}

	public String getIsVoucherEdit() {
		return isVoucherEdit;
	}

	public void setIsVoucherEdit(String isVoucherEdit) {
		this.isVoucherEdit = isVoucherEdit;
	}

	public String getIsVvoucherAddordele() {
		return isVvoucherAddordele;
	}

	public void setIsVvoucherAddordele(String isVvoucherAddordele) {
		this.isVvoucherAddordele = isVvoucherAddordele;
	}

	public String getIsVoucherMoneyHold() {
		return isVoucherMoneyHold;
	}

	public void setIsVoucherMoneyHold(String isVoucherMoneyHold) {
		this.isVoucherMoneyHold = isVoucherMoneyHold;
	}

	public String getIsEntryValueEdit() {
		return isEntryValueEdit;
	}

	public void setIsEntryValueEdit(String isEntryValueEdit) {
		this.isEntryValueEdit = isEntryValueEdit;
	}

	public String getIsEntryAccountEdit() {
		return isEntryAccountEdit;
	}

	public void setIsEntryAccountEdit(String isEntryAccountEdit) {
		this.isEntryAccountEdit = isEntryAccountEdit;
	}

	public String getCcodecontrol() {
		return ccodecontrol;
	}

	public void setCcodecontrol(String ccodecontrol) {
		this.ccodecontrol = ccodecontrol;
	}

	public String getIsEntryPCSEdit() {
		return isEntryPCSEdit;
	}

	public void setIsEntryPCSEdit(String isEntryPCSEdit) {
		this.isEntryPCSEdit = isEntryPCSEdit;
	}

	public String getIsEntryDepartmentEdit() {
		return isEntryDepartmentEdit;
	}

	public void setIsEntryDepartmentEdit(String isEntryDepartmentEdit) {
		this.isEntryDepartmentEdit = isEntryDepartmentEdit;
	}

	public String getIsItemEdit() {
		return isItemEdit;
	}

	public void setIsItemEdit(String isItemEdit) {
		this.isItemEdit = isItemEdit;
	}

	public String getIsCusSupMustInput() {
		return isCusSupMustInput;
	}

	public void setIsCusSupMustInput(String isCusSupMustInput) {
		this.isCusSupMustInput = isCusSupMustInput;
	}

	public VocherType getVocherType() {
		return vocherType;
	}

	public void setVocherType(VocherType vocherType) {
		this.vocherType = vocherType;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Double getDebitAmountTotal() {
		return debitAmountTotal;
	}

	public void setDebitAmountTotal(Double debitAmountTotal) {
		this.debitAmountTotal = debitAmountTotal;
	}

	public Double getCreditAmountTotal() {
		return creditAmountTotal;
	}

	public void setCreditAmountTotal(Double creditAmountTotal) {
		this.creditAmountTotal = creditAmountTotal;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

}
