package com.vix.system.latestOperate.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 最近访问
 * 
 * com.vix.system.latestOperate.entity.LatestOperateEntity
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-24
 */
public class LatestOperateEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 业务对象类型（系统默认）
	 */
	private String objectType;
	/**
	 * 具体单据编码
	 */
	private String billCode;
	/**
	 * URL（action）
	 */
	private String url;
	/**
	 * 访问对象
	 */
	private String operateObject;
	/**
	 * 访问方法/操作
	 */
	private String operateMethod;
	/**
	 * 访问时间
	 */
	private Date operateTime;
	/**
	 * 操作账号
	 */
	private String operatePersonnel;
	/**
	 * 操作员工
	 */
	private String operateEmployee;
	/**
	 * 操作内容
	 */
	private String operate;

	public String getOperateObject() {
		return operateObject;
	}

	public void setOperateObject(String operateObject) {
		this.operateObject = operateObject;
	}

	public String getOperateMethod() {
		return operateMethod;
	}

	public void setOperateMethod(String operateMethod) {
		this.operateMethod = operateMethod;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperatePersonnel() {
		return operatePersonnel;
	}

	public void setOperatePersonnel(String operatePersonnel) {
		this.operatePersonnel = operatePersonnel;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOperateEmployee() {
		return operateEmployee;
	}

	public void setOperateEmployee(String operateEmployee) {
		this.operateEmployee = operateEmployee;
	}

}
