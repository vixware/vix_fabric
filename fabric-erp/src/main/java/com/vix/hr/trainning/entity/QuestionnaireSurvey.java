package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @Description:问卷调查
 * @author luchuan
 * @date 2018-4-11 上午11:08:33
 */
public class QuestionnaireSurvey extends BaseEntity {

	private static final long serialVersionUID = -7634079115193989315L;
	
	/** 问卷名称*/
	private String questionName;
	/** 问卷部门*/
	private String questionOrg;
	/** 问卷内容*/
	private String questionContent;
	/** 开始日期 */
	private Date effectiveStartDate;
	/** 结束日期 */
	private Date effectiveEndDate;
	/** 问卷分数*/
	private String scole;
	/** 问卷来源*/
	private String resourse;
	/** 是否使用*/
	private String isUse;
	/** 题目数量*/
	private String titleNumber;
	/** 建立时间*/
	private Date builtDate;

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}
    
	public String getEffectiveStartDateStr() {
		if (null != effectiveStartDate) {
			return DateUtil.format(effectiveStartDate);
		}
		return "";
	}
	
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
    
	public String getEffectiveEndDateStr() {
		if (null != effectiveEndDate) {
			return DateUtil.format(effectiveEndDate);
		}
		return "";
	}
		
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionOrg() {
		return questionOrg;
	}

	public void setQuestionOrg(String questionOrg) {
		this.questionOrg = questionOrg;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getScole() {
		return scole;
	}

	public void setScole(String scole) {
		this.scole = scole;
	}

	public String getResourse() {
		return resourse;
	}

	public void setResourse(String resourse) {
		this.resourse = resourse;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getTitleNumber() {
		return titleNumber;
	}

	public void setTitleNumber(String titleNumber) {
		this.titleNumber = titleNumber;
	}

	public Date getBuiltDate() {
		return builtDate;
	}

	public void setBuiltDate(Date builtDate) {
		this.builtDate = builtDate;
	}
    
	public String getBuiltDateStr() {
		if (null != builtDate) {
			return DateUtil.format(builtDate);
		}
		return "";
	}
	
}
