package com.vix.drp.installation.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 安装单
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class InstallationOrder extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3924250218722288302L;
	/**
	 * 安装日期
	 */
	private Date installdate;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 安装地点
	 */
	private String installationLocation;
	/**
	 * 安装内容
	 */
	private String content;
	/**
	 * 安装要求
	 */
	private String serviceRequirements;
	/**
	 * 安装工时
	 */
	private Integer workingHours;
	/**
	 * 负责人
	 */
	private String transactor;
	/**
	 * 物料
	 */
	private String itemname;
	/**
	 * 工具
	 */
	private String tool;
	/**
	 * 安装费用
	 */
	private Double installCost;

	public Date getInstalldate() {
		return installdate;
	}

	public void setInstalldate(Date installdate) {
		this.installdate = installdate;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInstallationLocation() {
		return installationLocation;
	}

	public void setInstallationLocation(String installationLocation) {
		this.installationLocation = installationLocation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getServiceRequirements() {
		return serviceRequirements;
	}

	public void setServiceRequirements(String serviceRequirements) {
		this.serviceRequirements = serviceRequirements;
	}

	public Integer getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Integer workingHours) {
		this.workingHours = workingHours;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public Double getInstallCost() {
		return installCost;
	}

	public void setInstallCost(Double installCost) {
		this.installCost = installCost;
	}

}
