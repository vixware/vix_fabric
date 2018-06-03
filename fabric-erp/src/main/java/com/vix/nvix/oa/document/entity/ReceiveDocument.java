package com.vix.nvix.oa.document.entity;

import java.util.Date;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 收文管理 com.vix.oa.document.entity.ReceiveDocument
 *
 * @author yhl
 *
 * @date 2017年12月18日
 */

public class ReceiveDocument extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 来文标题 */
	private String title;
	/** 收文编号 code */
	/** 收文状态 status 0:待审核;1:已同意;2:未同意 */
	/** 收文日期 */
	private Date receiveDate;
	/** 来文字号 */
	private String word;
	/** 主题词 */
	private String subject;
	/** 内容  */
	private String mainContent;
	/** 来文单位 */
	private OrganizationUnit organizationUnit;
	/** 收文类型 */
	private ReceiveType receiveType;
	/** 秘密等级 */
	private SecretGrade secretGrade;
	/** 紧急程度 */
	private UrgentDegree urgentDegree;
	/** 审核人 */
	private String approver;
	/** 审核时间  */
	private Date checkDate;
	/** 审核意见  */
	private String checkMemo;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}
	
	public String getReceiveDateStr() {
		if(receiveDate != null){
			return DateUtil.format(receiveDate);
		}
		return "";
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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

	public ReceiveType getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(ReceiveType receiveType) {
		this.receiveType = receiveType;
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

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getCheckDate() {
		return checkDate;
	}
	
	public String getCheckDateStr() {
		if(checkDate != null){
			return DateUtil.format(checkDate);
		}
		return "";
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckMemo() {
		return checkMemo;
	}

	public void setCheckMemo(String checkMemo) {
		this.checkMemo = checkMemo;
	}

}
