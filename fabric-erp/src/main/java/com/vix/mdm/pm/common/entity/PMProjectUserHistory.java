/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目访问记录
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class PMProjectUserHistory extends BaseEntity {
	/** 访问用户 */
	private String visitUser;
	/** 访问时间 */
	private Date visitTime;
	/** 操作 */
	private String operation;

	public String getVisitUser() {
		return visitUser;
	}

	public void setVisitUser(String visitUser) {
		this.visitUser = visitUser;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
