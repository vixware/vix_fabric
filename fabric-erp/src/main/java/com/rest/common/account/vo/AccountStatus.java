package com.rest.common.account.vo;

import java.util.Date;

public class AccountStatus {

	private String account;
	
	/** 最后更新时间 */
	private Date lastUpdateTime;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}
