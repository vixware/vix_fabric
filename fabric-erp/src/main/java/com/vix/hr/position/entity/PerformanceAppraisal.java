package com.vix.hr.position.entity;

import java.util.Date;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.share.entity.BaseEntity;

/**
 * 绩效考核
 * 
 * @author 李大鹏
 * 
 */
public class PerformanceAppraisal extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 编码 */
	private String perCode;
	/** 指标名称 */
	private String perName;
	/** 指标分类 */
	private String perType;
	/** 考核标准 */
	private String perContent;
	/** 评分结果 */
	private String perScore;
	/** 考核开始日期 */
	private Date perStartDatePer;
	/** 考核结束日期 */
	private Date perEndDatePer;
	/** 备注 */
	private String remarks;

	/** 岗位 */
	private OrgPosition orgPosition;

	public String getPerCode() {
		return perCode;
	}

	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPerType() {
		return perType;
	}

	public void setPerType(String perType) {
		this.perType = perType;
	}

	public String getPerContent() {
		return perContent;
	}

	public void setPerContent(String perContent) {
		this.perContent = perContent;
	}

	public String getPerScore() {
		return perScore;
	}

	public void setPerScore(String perScore) {
		this.perScore = perScore;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public OrgPosition getOrgPosition() {
		return orgPosition;
	}

	public void setOrgPosition(OrgPosition orgPosition) {
		this.orgPosition = orgPosition;
	}

	public Date getPerStartDatePer() {
		return perStartDatePer;
	}

	public void setPerStartDatePer(Date perStartDatePer) {
		this.perStartDatePer = perStartDatePer;
	}

	public Date getPerEndDatePer() {
		return perEndDatePer;
	}

	public void setPerEndDatePer(Date perEndDatePer) {
		this.perEndDatePer = perEndDatePer;
	}

}
