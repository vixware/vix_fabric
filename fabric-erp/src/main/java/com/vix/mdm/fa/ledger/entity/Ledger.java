package com.vix.mdm.fa.ledger.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.fa.setting.entity.Account;

/**
 * @Description: 帐套
 * @author ivan
 * @date 2014-01-17
 */
public class Ledger extends BaseEntity {

	private static final long serialVersionUID = 8133810344433516935L;
	/** 帐套路径 */
	private String cacc_path;
	/** 启用会计期年 */
	private String iyear;
	/** 启用会计期月 */
	private String imonth;
	/** 账套主管 */
	private String cacc_master;
	/** 本币代码 */
	private String ccurcode;
	/** 本币名称 */
	private String ccurname;
	/** 单位名称 */
	private String cunitname;
	/** 单位简称 */
	private String cunitabbre;
	/** 单位地址 */
	private String cunitaddr;
	/** 邮政编码 */
	private String cunitzap;
	/** 联系电话 */
	private String cunittel;
	/** 传真 */
	private String cunitfax;
	/** 电子邮件 */
	private String cunitemail;
	/** 税号 */
	private String cunittaxno;
	/** 法人 */
	private String cunitlp;
	/** 企业类型 */
	private String centtype;
	/** 行业类型 */
	private String ctradekind;
	/** 是否集团版 */
	private String ciscompanyver;
	/** 域名 */
	private String cdomain;
	/** 会计科目 */
	private Set<Account> accounts = new HashSet<Account>();

	public String getCacc_path() {
		return cacc_path;
	}

	public void setCacc_path(String cacc_path) {
		this.cacc_path = cacc_path;
	}

	public String getIyear() {
		return iyear;
	}

	public void setIyear(String iyear) {
		this.iyear = iyear;
	}

	public String getImonth() {
		return imonth;
	}

	public void setImonth(String imonth) {
		this.imonth = imonth;
	}

	public String getCacc_master() {
		return cacc_master;
	}

	public void setCacc_master(String cacc_master) {
		this.cacc_master = cacc_master;
	}

	public String getCcurcode() {
		return ccurcode;
	}

	public void setCcurcode(String ccurcode) {
		this.ccurcode = ccurcode;
	}

	public String getCcurname() {
		return ccurname;
	}

	public void setCcurname(String ccurname) {
		this.ccurname = ccurname;
	}

	public String getCunitname() {
		return cunitname;
	}

	public void setCunitname(String cunitname) {
		this.cunitname = cunitname;
	}

	public String getCunitabbre() {
		return cunitabbre;
	}

	public void setCunitabbre(String cunitabbre) {
		this.cunitabbre = cunitabbre;
	}

	public String getCunitaddr() {
		return cunitaddr;
	}

	public void setCunitaddr(String cunitaddr) {
		this.cunitaddr = cunitaddr;
	}

	public String getCunitzap() {
		return cunitzap;
	}

	public void setCunitzap(String cunitzap) {
		this.cunitzap = cunitzap;
	}

	public String getCunittel() {
		return cunittel;
	}

	public void setCunittel(String cunittel) {
		this.cunittel = cunittel;
	}

	public String getCunitfax() {
		return cunitfax;
	}

	public void setCunitfax(String cunitfax) {
		this.cunitfax = cunitfax;
	}

	public String getCunitemail() {
		return cunitemail;
	}

	public void setCunitemail(String cunitemail) {
		this.cunitemail = cunitemail;
	}

	public String getCunittaxno() {
		return cunittaxno;
	}

	public void setCunittaxno(String cunittaxno) {
		this.cunittaxno = cunittaxno;
	}

	public String getCunitlp() {
		return cunitlp;
	}

	public void setCunitlp(String cunitlp) {
		this.cunitlp = cunitlp;
	}

	public String getCenttype() {
		return centtype;
	}

	public void setCenttype(String centtype) {
		this.centtype = centtype;
	}

	public String getCtradekind() {
		return ctradekind;
	}

	public void setCtradekind(String ctradekind) {
		this.ctradekind = ctradekind;
	}

	public String getCiscompanyver() {
		return ciscompanyver;
	}

	public void setCiscompanyver(String ciscompanyver) {
		this.ciscompanyver = ciscompanyver;
	}

	public String getCdomain() {
		return cdomain;
	}

	public void setCdomain(String cdomain) {
		this.cdomain = cdomain;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

}
