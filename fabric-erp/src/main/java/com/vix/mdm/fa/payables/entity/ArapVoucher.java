package com.vix.mdm.fa.payables.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 应收应付单
 * @author ivan
 * @date 2014-01-09
 */
public class ArapVoucher extends BaseEntity {

	private static final long serialVersionUID = -7610876239311351934L;
	/** 联结号 */
	private String clink;
	/** 单据类型 */
	private String cvouchtype;
	/** 项目编码 */
	private String cdigest;
	/** 币种名称 */
	private String currencyNanem;
	/** 汇率 */
	private Double exchangeRate;
	/** 借贷方向 */
	private String bd_c;
	/** 本币金额 */
	private Double iamount;
	/** 原币金额 */
	private Double iamount_f;
	/** 本币余额 */
	private Double iramount;
	/** 原币余额 */
	private Double iramount_f;
	/** 录入人 */
	private String operator;
	/** 审核人 */
	private String checkerMan;
	/** 对应单据类型 */
	private String coVouchType;
	/** 去向号码 */
	private String destinationNo;
	/** 来源号码 */
	private String sourceNo;
	/** 期初标志 */
	private String isStartFlag;
	/** 凭证线索号 */
	private String cpzid;
	/** 应收应付标志 */
	private String cflag;
	/** 数量 */
	private Double amount;
	/** 数量余额 */
	private Double remainingAmount;
	/** 单据模版号 */
	private String vt_id;
	/** 时间戳 */
	private Date ufts;
	/** 结算单子表标识 */
	private String iclosesid;
	/** 对应结算单子表标识 */
	private String icoclosesid;
	/** 项目名称 */
	private String citemname;
	/** 业务员编码 */
	private String personCode;
	/** 科目编码 */
	private String accountCode;
	/** 付款条件编码 */
	private String paymentCode;
	/** 项目大类编码 */
	private String itemClass;
	/** 明细 */
	private Set<ArapVoucherDetails> arapVoucherDetails = new HashSet<ArapVoucherDetails>();

	public String getClink() {
		return clink;
	}

	public void setClink(String clink) {
		this.clink = clink;
	}

	public String getCurrencyNanem() {
		return currencyNanem;
	}

	public void setCurrencyNanem(String currencyNanem) {
		this.currencyNanem = currencyNanem;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getBd_c() {
		return bd_c;
	}

	public void setBd_c(String bd_c) {
		this.bd_c = bd_c;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCheckerMan() {
		return checkerMan;
	}

	public void setCheckerMan(String checkerMan) {
		this.checkerMan = checkerMan;
	}

	public String getCoVouchType() {
		return coVouchType;
	}

	public void setCoVouchType(String coVouchType) {
		this.coVouchType = coVouchType;
	}

	public String getDestinationNo() {
		return destinationNo;
	}

	public void setDestinationNo(String destinationNo) {
		this.destinationNo = destinationNo;
	}

	public String getSourceNo() {
		return sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	public String getIsStartFlag() {
		return isStartFlag;
	}

	public void setIsStartFlag(String isStartFlag) {
		this.isStartFlag = isStartFlag;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(Double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getCitemname() {
		return citemname;
	}

	public void setCitemname(String citemname) {
		this.citemname = citemname;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getCvouchtype() {
		return cvouchtype;
	}

	public void setCvouchtype(String cvouchtype) {
		this.cvouchtype = cvouchtype;
	}

	public String getCdigest() {
		return cdigest;
	}

	public void setCdigest(String cdigest) {
		this.cdigest = cdigest;
	}

	public Double getIamount() {
		return iamount;
	}

	public void setIamount(Double iamount) {
		this.iamount = iamount;
	}

	public Double getIamount_f() {
		return iamount_f;
	}

	public void setIamount_f(Double iamount_f) {
		this.iamount_f = iamount_f;
	}

	public Double getIramount() {
		return iramount;
	}

	public void setIramount(Double iramount) {
		this.iramount = iramount;
	}

	public Double getIramount_f() {
		return iramount_f;
	}

	public void setIramount_f(Double iramount_f) {
		this.iramount_f = iramount_f;
	}

	public String getCpzid() {
		return cpzid;
	}

	public void setCpzid(String cpzid) {
		this.cpzid = cpzid;
	}

	public String getCflag() {
		return cflag;
	}

	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

	public String getVt_id() {
		return vt_id;
	}

	public void setVt_id(String vt_id) {
		this.vt_id = vt_id;
	}

	public Date getUfts() {
		return ufts;
	}

	public void setUfts(Date ufts) {
		this.ufts = ufts;
	}

	public String getIclosesid() {
		return iclosesid;
	}

	public void setIclosesid(String iclosesid) {
		this.iclosesid = iclosesid;
	}

	public String getIcoclosesid() {
		return icoclosesid;
	}

	public void setIcoclosesid(String icoclosesid) {
		this.icoclosesid = icoclosesid;
	}

	public Set<ArapVoucherDetails> getArapVoucherDetails() {
		return arapVoucherDetails;
	}

	public void setArapVoucherDetails(Set<ArapVoucherDetails> arapVoucherDetails) {
		this.arapVoucherDetails = arapVoucherDetails;
	}

}
