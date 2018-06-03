package com.vix.system.changeManagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 变更记录
 * 
 * @author arron
 */
public class ChangeRecord extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7251788585814297600L;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 修改人
	 */
	private String modifier;
	/**
	 * 审核人
	 */
	private String checker;
	/**
	 * 批准人
	 */
	private String approver;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}