package com.vix.common.operHistory.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: OperHistory
 * @Description: 最近使用
 * @author wangmingchen
 * @date 2012-7-21 上午9:27:27
 * 
 */
public class OperHistory extends BaseEntity {

	/** 使用时间参看父类的 createTime */
	private static final long serialVersionUID = -3312163722535516852L;
	/** 操作职员id */
	private String operEmployeeId;
	/** 操作模块名称 */
	private String operModelName;
	/** 操作模块的url */
	private String operUrl;

	public OperHistory() {
		super();
	}

	public String getOperEmployeeId() {
		return operEmployeeId;
	}

	public void setOperEmployeeId(String operEmployeeId) {
		this.operEmployeeId = operEmployeeId;
	}

	public String getOperModelName() {
		return operModelName;
	}

	public void setOperModelName(String operModelName) {
		this.operModelName = operModelName;
	}

	public String getOperUrl() {
		return operUrl;
	}

	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}

}
