package com.vix.nvix.oa.document.entity;

import java.util.Date;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 发文管理 com.vix.oa.document.entity.SendDocument
 *
 * @author yhl
 *
 * @date 2017年12月18日
 */

public class SendDocument extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 发文标题 */
	private String title;
	/** 发文日期 */
	private Date sendDate;
	/** 发文字号 */
	private String word;
	/** 打印份数 */
	private Integer printNum;
	/** 主题词 */
	private String subject;
	/** 内容  */
	private String mainContent;
	/** 主送单位 */
	private OrganizationUnit lordSendUnit;
	/** 抄送单位 */
	private OrganizationUnit copySendUnit;
	/** 发文单位  */
	private OrganizationUnit organizationUnit;
	/** 发文类型 */
	private SendType sendType;
	/** 秘密等级 */
	private SecretGrade secretGrade;
	/** 紧急程度 */
	private UrgentDegree urgentDegree;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public Date getSendDate() {
		return sendDate;
	}
	
	public String getSendDateStr() {
		if(sendDate != null){
			return DateUtil.format(sendDate);
		}
		return "";
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getPrintNum() {
		return printNum;
	}

	public void setPrintNum(Integer printNum) {
		this.printNum = printNum;
	}

	public OrganizationUnit getLordSendUnit() {
		return lordSendUnit;
	}

	public void setLordSendUnit(OrganizationUnit lordSendUnit) {
		this.lordSendUnit = lordSendUnit;
	}

	public OrganizationUnit getCopySendUnit() {
		return copySendUnit;
	}

	public void setCopySendUnit(OrganizationUnit copySendUnit) {
		this.copySendUnit = copySendUnit;
	}

	public SendType getSendType() {
		return sendType;
	}

	public void setSendType(SendType sendType) {
		this.sendType = sendType;
	}

	public SecretGrade getSecretGrade() {
		return secretGrade;
	}

	public void setSecretGrade(SecretGrade secretGrade) {
		this.secretGrade = secretGrade;
	}

	public UrgentDegree getUrgentDegree() {
		return urgentDegree;
	}

	public void setUrgentDegree(UrgentDegree urgentDegree) {
		this.urgentDegree = urgentDegree;
	}

}
