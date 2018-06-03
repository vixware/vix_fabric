/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 里程碑报告
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class MileStoneReport extends BaseEntity {
	/** 里程碑编码 */
	private String bugTitle;
	/** 项目编号 */
	private String projectCode;
	/** 报告内容 */
	private String content;
	/** 报告时间 */
	private Date reportTime;
	/** 报告人 */
	private String reporter;

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
}
