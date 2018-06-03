/**
 * 
 */
package com.vix.drp.retailPriceSurvey.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 零售价格调查
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class RetailPriceSurvey extends BaseEntity {
	private static final long serialVersionUID = -8333106238345413633L;
	/**
	 * 企业
	 */
	private String ownedEnterprise;
	/**
	 * 调查人
	 */
	private String investigator;
	/**
	 * 调查时间
	 */
	private Date investigationTime;
	/**
	 * 企业规模
	 */
	private String enterpriseScale;
	/**
	 * 业态形式
	 */
	private String formatForm;

	private Set<RetailPriceSurveyDetail> retailPriceSurveyDetails = new HashSet<RetailPriceSurveyDetail>();

	public String getOwnedEnterprise() {
		return ownedEnterprise;
	}

	public void setOwnedEnterprise(String ownedEnterprise) {
		this.ownedEnterprise = ownedEnterprise;
	}

	public String getInvestigator() {
		return investigator;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

	public Date getInvestigationTime() {
		return investigationTime;
	}

	public void setInvestigationTime(Date investigationTime) {
		this.investigationTime = investigationTime;
	}

	public String getEnterpriseScale() {
		return enterpriseScale;
	}

	public void setEnterpriseScale(String enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}

	public String getFormatForm() {
		return formatForm;
	}

	public void setFormatForm(String formatForm) {
		this.formatForm = formatForm;
	}

	public Set<RetailPriceSurveyDetail> getRetailPriceSurveyDetails() {
		return retailPriceSurveyDetails;
	}

	public void setRetailPriceSurveyDetails(Set<RetailPriceSurveyDetail> retailPriceSurveyDetails) {
		this.retailPriceSurveyDetails = retailPriceSurveyDetails;
	}

}
