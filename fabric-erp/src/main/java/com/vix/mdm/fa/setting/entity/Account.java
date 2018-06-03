package com.vix.mdm.fa.setting.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.fa.ledger.entity.Ledger;

/**
 * @Description: 会计科目
 * @author ivan
 * @date 2014-01-08
 */
public class Account extends BaseEntity {

	private static final long serialVersionUID = 6768999111700380952L;
	/** 账套编码 */
	private String ledgerCode;
	/** 科目类型 */
	private String cclass;
	/** 科目类型英文名称 */
	private String cclass_engl;
	/** 财务分析类型 */
	private String cclassany;
	/** 财务分析类型英文名称 */
	private String cclassany_engl;
	/** 科目编码 */
	private String ccode;
	/** 科目名称 */
	private String ccode_name;
	/** 科目英文名称 */
	private String ccode_engl;
	/** 编码级次 */
	private String igrade;
	/** 科目性质 */
	private String bproperty;
	/** 账页格式 */
	private String cbook_type;
	/** 账页格式英文名称 */
	private String cbook_type_engl;
	/** 科目助记码 */
	private String chelp;
	/** 币种名称 */
	private String cexch_name;
	/** 计量单位 */
	private String cmeasure;
	/** 是否个人往来核算 */
	private String bperson;
	/** 是否客户往来核算 */
	private String bcus;
	/** 是否供应商往来核算 */
	private String bsup;
	/** 是否部门核算 */
	private String bdept;
	/** 是否项目核算 */
	private String bitem;
	/** 项目大类编码 */
	private String cass_item;
	/** 是否日记账 */
	private String br;
	/** 是否银行账 */
	private String be;
	/** 是否汇总打印 */
	private String cgather;
	/** 是否末级 */
	private String bend;
	/** 是否参与汇兑损益计算 */
	private String bexchange;
	/** 是否现金科目 */
	private String bcash;
	/** 是否银行科目 */
	private String bbank;
	/** 银行账科目是否启用 */
	private String bused;
	/** 银行账科目对账方向 */
	private String bd_c;
	/** 银行账科目启用日期 */
	private Date dbegin;
	/** 银行账科目对账截止日期 */
	private Date dend;
	/** 特殊科目标志 */
	private String itrans;
	/** 是否封存 */
	private String bclose;
	/** 受控科目受控系统标识 */
	private String cother;
	/** 其它系统是否使用 */
	private String iotherused;
	/** 在建工程项目科目类型 */
	private String iViewItem;
	/** 是否工程结算科目 */
	private String bGCJS;
	/** 是否常用现金流量科目 */
	private String bCashItem;
	/** 时间戳 */
	private Date pubufts;
	/** 是否打印转账通知书 */
	private String bReport;
	/** 自定义类型 */
	private String cUserDefineType;
	/** 科目分类 */
	private AccountCategory accountCategory;
	/** 账套 */
	private Ledger ledger;

	public String getLedgerCode() {
		return ledgerCode;
	}

	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}

	public String getCclass() {
		return cclass;
	}

	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

	public String getCclass_engl() {
		return cclass_engl;
	}

	public void setCclass_engl(String cclass_engl) {
		this.cclass_engl = cclass_engl;
	}

	public String getCclassany() {
		return cclassany;
	}

	public void setCclassany(String cclassany) {
		this.cclassany = cclassany;
	}

	public String getCclassany_engl() {
		return cclassany_engl;
	}

	public void setCclassany_engl(String cclassany_engl) {
		this.cclassany_engl = cclassany_engl;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCcode_name() {
		return ccode_name;
	}

	public void setCcode_name(String ccode_name) {
		this.ccode_name = ccode_name;
	}

	public String getCcode_engl() {
		return ccode_engl;
	}

	public void setCcode_engl(String ccode_engl) {
		this.ccode_engl = ccode_engl;
	}

	public String getIgrade() {
		return igrade;
	}

	public void setIgrade(String igrade) {
		this.igrade = igrade;
	}

	public String getBproperty() {
		return bproperty;
	}

	public void setBproperty(String bproperty) {
		this.bproperty = bproperty;
	}

	public String getCbook_type() {
		return cbook_type;
	}

	public void setCbook_type(String cbook_type) {
		this.cbook_type = cbook_type;
	}

	public String getCbook_type_engl() {
		return cbook_type_engl;
	}

	public void setCbook_type_engl(String cbook_type_engl) {
		this.cbook_type_engl = cbook_type_engl;
	}

	public String getChelp() {
		return chelp;
	}

	public void setChelp(String chelp) {
		this.chelp = chelp;
	}

	public String getCexch_name() {
		return cexch_name;
	}

	public void setCexch_name(String cexch_name) {
		this.cexch_name = cexch_name;
	}

	public String getCmeasure() {
		return cmeasure;
	}

	public void setCmeasure(String cmeasure) {
		this.cmeasure = cmeasure;
	}

	public String getBperson() {
		return bperson;
	}

	public void setBperson(String bperson) {
		this.bperson = bperson;
	}

	public String getBcus() {
		return bcus;
	}

	public void setBcus(String bcus) {
		this.bcus = bcus;
	}

	public String getBsup() {
		return bsup;
	}

	public void setBsup(String bsup) {
		this.bsup = bsup;
	}

	public String getBdept() {
		return bdept;
	}

	public void setBdept(String bdept) {
		this.bdept = bdept;
	}

	public String getBitem() {
		return bitem;
	}

	public void setBitem(String bitem) {
		this.bitem = bitem;
	}

	public String getCass_item() {
		return cass_item;
	}

	public void setCass_item(String cass_item) {
		this.cass_item = cass_item;
	}

	public String getBr() {
		return br;
	}

	public void setBr(String br) {
		this.br = br;
	}

	public String getBe() {
		return be;
	}

	public void setBe(String be) {
		this.be = be;
	}

	public String getCgather() {
		return cgather;
	}

	public void setCgather(String cgather) {
		this.cgather = cgather;
	}

	public String getBend() {
		return bend;
	}

	public void setBend(String bend) {
		this.bend = bend;
	}

	public String getBexchange() {
		return bexchange;
	}

	public void setBexchange(String bexchange) {
		this.bexchange = bexchange;
	}

	public String getBcash() {
		return bcash;
	}

	public void setBcash(String bcash) {
		this.bcash = bcash;
	}

	public String getBbank() {
		return bbank;
	}

	public void setBbank(String bbank) {
		this.bbank = bbank;
	}

	public String getBused() {
		return bused;
	}

	public void setBused(String bused) {
		this.bused = bused;
	}

	public String getBd_c() {
		return bd_c;
	}

	public void setBd_c(String bd_c) {
		this.bd_c = bd_c;
	}

	public Date getDbegin() {
		return dbegin;
	}

	public void setDbegin(Date dbegin) {
		this.dbegin = dbegin;
	}

	public Date getDend() {
		return dend;
	}

	public void setDend(Date dend) {
		this.dend = dend;
	}

	public String getItrans() {
		return itrans;
	}

	public void setItrans(String itrans) {
		this.itrans = itrans;
	}

	public String getBclose() {
		return bclose;
	}

	public void setBclose(String bclose) {
		this.bclose = bclose;
	}

	public String getCother() {
		return cother;
	}

	public void setCother(String cother) {
		this.cother = cother;
	}

	public String getIotherused() {
		return iotherused;
	}

	public void setIotherused(String iotherused) {
		this.iotherused = iotherused;
	}

	public String getiViewItem() {
		return iViewItem;
	}

	public void setiViewItem(String iViewItem) {
		this.iViewItem = iViewItem;
	}

	public String getbGCJS() {
		return bGCJS;
	}

	public void setbGCJS(String bGCJS) {
		this.bGCJS = bGCJS;
	}

	public String getbCashItem() {
		return bCashItem;
	}

	public void setbCashItem(String bCashItem) {
		this.bCashItem = bCashItem;
	}

	public Date getPubufts() {
		return pubufts;
	}

	public void setPubufts(Date pubufts) {
		this.pubufts = pubufts;
	}

	public String getbReport() {
		return bReport;
	}

	public void setbReport(String bReport) {
		this.bReport = bReport;
	}

	public String getcUserDefineType() {
		return cUserDefineType;
	}

	public void setcUserDefineType(String cUserDefineType) {
		this.cUserDefineType = cUserDefineType;
	}

	public AccountCategory getAccountCategory() {
		return accountCategory;
	}

	public void setAccountCategory(AccountCategory accountCategory) {
		this.accountCategory = accountCategory;
	}

	public Ledger getLedger() {
		return ledger;
	}

	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}

}
