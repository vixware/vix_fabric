/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目招投标管理
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectTender extends BaseEntity {
	/** 项目标书名 */
	private String tenderProjectTitle;
	/** 标书类型 */
	private String tenderType;
	/** 标号 */
	private String tenderCode;
	/** 标包名称 */
	private String tenderPackageName;
	/** 招标代理 */
	private String tenderAgent;
	/** 招标开始时间 */
	private Date tenderBeginTime;
	/** 投标结束时间 */
	private Date tenderEndTime;
	/** 总价 */
	private Double totalAmount;
	/** 总价调整 */
	private Double amountAdjust;
	/** 最终价格 */
	private Double finalAmount;
	/** @备注 */
	private String memo;

	public String getTenderProjectTitle() {
		return tenderProjectTitle;
	}

	public void setTenderProjectTitle(String tenderProjectTitle) {
		this.tenderProjectTitle = tenderProjectTitle;
	}

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public String getTenderCode() {
		return tenderCode;
	}

	public void setTenderCode(String tenderCode) {
		this.tenderCode = tenderCode;
	}

	public String getTenderPackageName() {
		return tenderPackageName;
	}

	public void setTenderPackageName(String tenderPackageName) {
		this.tenderPackageName = tenderPackageName;
	}

	public String getTenderAgent() {
		return tenderAgent;
	}

	public void setTenderAgent(String tenderAgent) {
		this.tenderAgent = tenderAgent;
	}

	public Date getTenderBeginTime() {
		return tenderBeginTime;
	}

	public void setTenderBeginTime(Date tenderBeginTime) {
		this.tenderBeginTime = tenderBeginTime;
	}

	public Date getTenderEndTime() {
		return tenderEndTime;
	}

	public void setTenderEndTime(Date tenderEndTime) {
		this.tenderEndTime = tenderEndTime;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getAmountAdjust() {
		return amountAdjust;
	}

	public void setAmountAdjust(Double amountAdjust) {
		this.amountAdjust = amountAdjust;
	}

	public Double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
