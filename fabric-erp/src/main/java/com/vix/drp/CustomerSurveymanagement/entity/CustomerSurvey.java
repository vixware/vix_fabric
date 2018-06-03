package com.vix.drp.CustomerSurveymanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 客户调查
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class CustomerSurvey extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3452354596906647676L;
	/**
	 * 调查对象
	 */
	private String surveyObject;
	/**
	 * 调查人
	 */
	private String surveyPerson;
	/**
	 * 调查内容
	 */
	private String surveyContent;
	/**
	 * 调查时间
	 */
	private Date surveyDate;

	public String getSurveyObject() {
		return surveyObject;
	}

	public void setSurveyObject(String surveyObject) {
		this.surveyObject = surveyObject;
	}

	public String getSurveyPerson() {
		return surveyPerson;
	}

	public void setSurveyPerson(String surveyPerson) {
		this.surveyPerson = surveyPerson;
	}

	public String getSurveyContent() {
		return surveyContent;
	}

	public void setSurveyContent(String surveyContent) {
		this.surveyContent = surveyContent;
	}

	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

}
