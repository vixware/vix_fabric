/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目需求
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectRequirement extends BaseEntity {
	/** 项目编号 */
	private String projectCode;
	/** 需求主题 */
	private String title;
	/** 客户编码 */
	private String customerCode;
	/** 客户名称 */
	private String customerName;
	/** 需求内容 */
	private String content;
	/** 版本 */
	private String version;
	/** 状态 */
	private String status;
	/** 需求提出时间 */
	private Date prTime;
	/** 业务员 */
	private String person;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPrTime() {
		return prTime;
	}

	public void setPrTime(Date prTime) {
		this.prTime = prTime;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

}
