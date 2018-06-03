package com.vix.system.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 
 * 系统内所有附件信息，不与系统内其他实体在HIBERNATE层面关联
 * @author arron
 */
public class Attachment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 标题 */
	private String name;
	/** 附件类型 
	 * 
	 * EML			电子邮件eml
	 * EMAIL_ATT		电子邮件附件
	 * 
	 * */
	private String attType;
	/** 
	 * 路径
	 * 存储的是系统配置文件业务路径的相对路径,不包含附件名称
	 *  */
	private String path;
	/**
	 * 真实文件名称
	 */
	private String realName;
	/** 介绍 */
	private String description;
	/** 业务对象标识 */
	private String businessTag;
	
	/** 业务对象id */
	private String dataId;
	
	/**
	 * 如果是临时文件则为0
	 * 非临时文件是1
	 */
	private Integer attTmpStatus;
	private CustomerAccount customerAccount;
	public Attachment() {}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public String getBusinessTag() {
		return businessTag;
	}

	public void setBusinessTag(String businessTag) {
		this.businessTag = businessTag;
	}

	public Integer getAttTmpStatus() {
		return attTmpStatus;
	}

	public void setAttTmpStatus(Integer attTmpStatus) {
		this.attTmpStatus = attTmpStatus;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
}