package com.vix.common.share.entity;


/**
 * 原因代码
 * @author Administrator
 *
 */
public class Reason extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 原因 */
	private String reason;
	/** 对应科目代码 */
	private String accountCode;
	/** 类型 */
	private String type;
	
	public Reason(){}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
