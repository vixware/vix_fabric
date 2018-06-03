package com.vix.mdm.fa.payables.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 收付款单据
 * @author ivan
 * @date 2014-01-10
 */
public class ArapClosingNote extends BaseEntity {

	private static final long serialVersionUID = -1260776191161395834L;
	/** 单据类型编码 */
	private String vouchType;
	/** 会计期间 */
	private String period;
	/** 业务员编码 */
	private String personCode;
	/** 项目编码 */
	private String itemCode;
	/** 票据号 */
	private String noteNO;
	/** 对应单据类型 */
	private String coVouchType;
	/** 摘要 */
	private String digest;
	/** 银行账号 */
	private String bankAccount;
	/** 币种名称 */
	private String currencyName;
	/** 汇率 */
	private Double exchangeRate;
	/** 本币金额 */
	private Double amount;
	/** 原币金额 */
	private Double originalAmount;
	/** 本币余额 */
	private Double remainSumAmount;
	/** 原币余额 */
	private Double origianlRemainSumAmount;
	/** 核销人 */
	private String cancelMan;
	/** 出纳签字人 */
	private String cashier;
	/** 预收预付标志 */
	private String isPrePay;
	/** 期初标志 */
	private String isStartFlag;
	/** 订单号 */
	private String orderNo;
	/** 预收付科目编码 */
	private String cprecode;
	/** 代付标志 */
	private String ipayforother;
	/** 结算单来源 */
	private String statementSource;
	/** 凭证线索号 */
	private String cpzid;
	/** 应收应付标志 */
	private String flag;
	/** 是否发送标志 */
	private String isSend;
	/** 是否接收 */
	private String isReceived;
	/** 结算科目项目 */
	private String clearingAccountItem;
	/** 现结线索 */
	private String ccancelno;
	/** 对方银行 */
	private String otherBank;
	/** 本单位银行名称 */
	private String nativeBank;
	/** 本单位银行账号 */
	private String nativeBankAccount;
	/** 银行导入标志 */
	private String isFromBank;
	/** 导出银行标志 */
	private String isExportToBank;
	/** 银行确认标志 */
	private String isBankSure;
	/** 单据模版号 */
	private Integer vt_id;
	/** 审核人 */
	private String checkMan;
	/** 时间戳 */
	private Date ufts;
	/** 项目名称 */
	private String citemname;
	/** 合同名称 */
	private String ccontract;
	/** 数量 */
	private Double iamount_s;
	/** 项目大类编码 */
	private String itemClass;
	/** 结算方式编码 */
	private String csscode;
	/** 结算科目编码 */
	private String clearingAccount;
	/** 明细 */
	private Set<ArapClosingNoteDetails> arapClosingNoteDetails = new HashSet<ArapClosingNoteDetails>();

	public String getVouchType() {
		return vouchType;
	}

	public void setVouchType(String vouchType) {
		this.vouchType = vouchType;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getNoteNO() {
		return noteNO;
	}

	public void setNoteNO(String noteNO) {
		this.noteNO = noteNO;
	}

	public String getCoVouchType() {
		return coVouchType;
	}

	public void setCoVouchType(String coVouchType) {
		this.coVouchType = coVouchType;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(Double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public Double getRemainSumAmount() {
		return remainSumAmount;
	}

	public void setRemainSumAmount(Double remainSumAmount) {
		this.remainSumAmount = remainSumAmount;
	}

	public Double getOrigianlRemainSumAmount() {
		return origianlRemainSumAmount;
	}

	public void setOrigianlRemainSumAmount(Double origianlRemainSumAmount) {
		this.origianlRemainSumAmount = origianlRemainSumAmount;
	}

	public String getCancelMan() {
		return cancelMan;
	}

	public void setCancelMan(String cancelMan) {
		this.cancelMan = cancelMan;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getIsPrePay() {
		return isPrePay;
	}

	public void setIsPrePay(String isPrePay) {
		this.isPrePay = isPrePay;
	}

	public String getIsStartFlag() {
		return isStartFlag;
	}

	public void setIsStartFlag(String isStartFlag) {
		this.isStartFlag = isStartFlag;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCprecode() {
		return cprecode;
	}

	public void setCprecode(String cprecode) {
		this.cprecode = cprecode;
	}

	public String getIpayforother() {
		return ipayforother;
	}

	public void setIpayforother(String ipayforother) {
		this.ipayforother = ipayforother;
	}

	public String getStatementSource() {
		return statementSource;
	}

	public void setStatementSource(String statementSource) {
		this.statementSource = statementSource;
	}

	public String getCpzid() {
		return cpzid;
	}

	public void setCpzid(String cpzid) {
		this.cpzid = cpzid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getIsReceived() {
		return isReceived;
	}

	public void setIsReceived(String isReceived) {
		this.isReceived = isReceived;
	}

	public String getClearingAccountItem() {
		return clearingAccountItem;
	}

	public void setClearingAccountItem(String clearingAccountItem) {
		this.clearingAccountItem = clearingAccountItem;
	}

	public String getCcancelno() {
		return ccancelno;
	}

	public void setCcancelno(String ccancelno) {
		this.ccancelno = ccancelno;
	}

	public String getOtherBank() {
		return otherBank;
	}

	public void setOtherBank(String otherBank) {
		this.otherBank = otherBank;
	}

	public String getNativeBank() {
		return nativeBank;
	}

	public void setNativeBank(String nativeBank) {
		this.nativeBank = nativeBank;
	}

	public String getNativeBankAccount() {
		return nativeBankAccount;
	}

	public void setNativeBankAccount(String nativeBankAccount) {
		this.nativeBankAccount = nativeBankAccount;
	}

	public String getIsFromBank() {
		return isFromBank;
	}

	public void setIsFromBank(String isFromBank) {
		this.isFromBank = isFromBank;
	}

	public String getIsExportToBank() {
		return isExportToBank;
	}

	public void setIsExportToBank(String isExportToBank) {
		this.isExportToBank = isExportToBank;
	}

	public String getIsBankSure() {
		return isBankSure;
	}

	public void setIsBankSure(String isBankSure) {
		this.isBankSure = isBankSure;
	}

	public Integer getVt_id() {
		return vt_id;
	}

	public void setVt_id(Integer vt_id) {
		this.vt_id = vt_id;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public Date getUfts() {
		return ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public String getCitemname() {
		return citemname;
	}

	public void setCitemname(String citemname) {
		this.citemname = citemname;
	}

	public String getCcontract() {
		return ccontract;
	}

	public void setCcontract(String ccontract) {
		this.ccontract = ccontract;
	}

	public Double getIamount_s() {
		return iamount_s;
	}

	public void setIamount_s(Double iamount_s) {
		this.iamount_s = iamount_s;
	}

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getCsscode() {
		return csscode;
	}

	public void setCsscode(String csscode) {
		this.csscode = csscode;
	}

	public String getClearingAccount() {
		return clearingAccount;
	}

	public void setClearingAccount(String clearingAccount) {
		this.clearingAccount = clearingAccount;
	}

	public Set<ArapClosingNoteDetails> getArapClosingNoteDetails() {
		return arapClosingNoteDetails;
	}

	public void setArapClosingNoteDetails(
			Set<ArapClosingNoteDetails> arapClosingNoteDetails) {
		this.arapClosingNoteDetails = arapClosingNoteDetails;
	}

}
