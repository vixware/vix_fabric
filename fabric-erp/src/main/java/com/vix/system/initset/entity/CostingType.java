package com.vix.system.initset.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 核算类型
 * 
 * com.vix.system.initset.entity.CostingType
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-19
 */
public class CostingType extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 企业类型
	 */
	private String companyType;
	/**
	 * 行业性质
	 */
	private String businessNature;
	/**
	 * 科目预置语言
	 */
	private String presetLanguageSubjects;
	/**
	 * 账套主管
	 */
	private String accountDirector;

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getBusinessNature() {
		return businessNature;
	}

	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}

	public String getPresetLanguageSubjects() {
		return presetLanguageSubjects;
	}

	public void setPresetLanguageSubjects(String presetLanguageSubjects) {
		this.presetLanguageSubjects = presetLanguageSubjects;
	}

	public String getAccountDirector() {
		return accountDirector;
	}

	public void setAccountDirector(String accountDirector) {
		this.accountDirector = accountDirector;
	}

}