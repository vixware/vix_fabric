package com.vix.wechat.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassFullName   com.vix.wechat.base.entity.WxpTrendsAndEmployee
 *
 * @author bjitzhang
 *
 * @date  2016年5月9日
 *
 */
public class WxpTrendsAndEmployee extends BaseEntity {
	private static final long serialVersionUID = 1L;
	String trendsId;
	String employeeId;
	String isPointPraise;
	String employeeName;
	String headImgUrl;
	String trendsIdAndEmployeeId;

	/**
	 * @return the trendsId
	 */
	public String getTrendsId() {
		return trendsId;
	}

	/**
	 * @param trendsId
	 *            the trendsId to set
	 */
	public void setTrendsId(String trendsId) {
		this.trendsId = trendsId;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the isPointPraise
	 */
	public String getIsPointPraise() {
		return isPointPraise;
	}

	/**
	 * @param isPointPraise
	 *            the isPointPraise to set
	 */
	public void setIsPointPraise(String isPointPraise) {
		this.isPointPraise = isPointPraise;
	}

	/**
	 * @return the trendsIdAndEmployeeId
	 */
	public String getTrendsIdAndEmployeeId() {
		return trendsIdAndEmployeeId;
	}

	/**
	 * @param trendsIdAndEmployeeId
	 *            the trendsIdAndEmployeeId to set
	 */
	public void setTrendsIdAndEmployeeId(String trendsIdAndEmployeeId) {
		this.trendsIdAndEmployeeId = trendsIdAndEmployeeId;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName
	 *            the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the headImgUrl
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * @param headImgUrl
	 *            the headImgUrl to set
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

}