package com.vix.common.share.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public BaseEntity() {
		// 2014-09-10 wl，设置初始有效期数据
		if (startTime == null)
			this.setStartTime(new Date());
		if (endTime == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				this.setEndTime(sdf.parse("9999-12-31"));
			} catch (Exception e) {
				System.out.println("WxpBaseIdEntityWithEndDate: date format exception:" + e.toString());
			}
		}
	}

	/** 自动编号 */
	private String id;
	/** 数据唯一编码 */
	private String uuid;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 业务主键标识 */
	private String primaryKey;
	/** 对象语言标识 */
	private String language;
	/** 组织(公司内部标识) */
	private String companyInnerCode;
	/** 公司标识 */
	private String companyCode;
	/** 帐套 */
	private String accountSet;
	/** 创建时间 */
	private Date createTime;
	/** 对象更新时间 */
	private Date updateTime;
	/** 企业编码 废弃 */
	private String enterpriseCode;
	/** 部门编码 */
	private String departmentCode;
	/** 部门名称 */
	private String department;
	/** 创建人编码 */
	private String creatorCode;
	/** 创建人 */
	private String creator;
	/** 状态 */
	private String status;
	/** 密级 */
	private String secretLevel;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 承租户标识 */
	private String tenantId;
	/** 语言编码 */
	private String langCode;
	/** 国家编码 */
	private String countryCode;
	/** 当事人编码 */
	private String interestedPartyPersonCode;
	/** 当事人 */
	private String interestedPartyPerson;
	/** 当事人角色编码 */
	private String interestedPartyRoleCode;
	/** 当事人角色 */
	private String interestedPartyRole;
	/** 版本 */
	private String version;
	/** 备注 */
	private String memo;
	/** 描述 */
	private String description;
	/**
	 * 是否为正式数据
	 */
	private String isTemp;
	private String searchText1;
	private String searchText2;

	// 非持久化属性
	/**
	 * 接收业务模块新建数据时 的代理部门编码 如果为空 则默认保存当前账户所在的部门编码
	 */
	private String proxyDepartmentCode;
	/**
	 * 接收业务模块新建数据时 的代理公司编码 如果为空 则默认保存当前账户所在的公司编码
	 */
	private String proxyCompanyInnerCode;
	/**
	 * 拼音 add by zhang
	 */
	private String chineseCharacter;
	private String isDeleted;

	private String appId;

	public Long orderBy;
	/** 创建人 */
	private Employee dataCreater;
	/**
	 * 来源单据class
	 */
	private String sourceClassName;
	/**
	 * 来源单据编码
	 */
	private String sourceBillCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getAccountSet() {
		return accountSet;
	}

	public void setAccountSet(String accountSet) {
		this.accountSet = accountSet;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateTimeStr() {
		if (null != createTime) {
			return DateUtil.format(createTime);
		}
		return "";
	}

	public String getCreateTimeTimeStr() {
		if (null != createTime) {
			return DateUtil.formatTime(createTime);
		}
		return "";
	}

	public String getUpdateTimeStr() {
		if (null != updateTime) {
			return DateUtil.format(updateTime);
		}
		return "";
	}

	public String getUpdateTimeTimeStr() {
		if (null != updateTime) {
			return DateUtil.formatTime(updateTime);
		}
		return "";
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public String getStartTimeStr() {
		if (null != startTime) {
			return DateUtil.format(startTime);
		}
		return "";
	}

	public String getStartTimeTimeStr() {
		if (null != startTime) {
			return DateUtil.formatTime(startTime);
		}
		return "";
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getEndTimeStr() {
		if (null != endTime) {
			return DateUtil.format(endTime);
		}
		return "";
	}

	public String getEndTimeTimeStr() {
		if (null != endTime) {
			return DateUtil.formatTime(endTime);
		}
		return "";
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getInterestedPartyPerson() {
		return interestedPartyPerson;
	}

	public void setInterestedPartyPerson(String interestedPartyPerson) {
		this.interestedPartyPerson = interestedPartyPerson;
	}

	public String getInterestedPartyRoleCode() {
		return interestedPartyRoleCode;
	}

	public void setInterestedPartyRoleCode(String interestedPartyRoleCode) {
		this.interestedPartyRoleCode = interestedPartyRoleCode;
	}

	public String getInterestedPartyRole() {
		return interestedPartyRole;
	}

	public void setInterestedPartyRole(String interestedPartyRole) {
		this.interestedPartyRole = interestedPartyRole;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getSearchText1() {
		return searchText1;
	}

	public void setSearchText1(String searchText1) {
		this.searchText1 = searchText1;
	}

	public String getSearchText2() {
		return searchText2;
	}

	public void setSearchText2(String searchText2) {
		this.searchText2 = searchText2;
	}

	public String getCreatorCode() {
		return creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	public String getInterestedPartyPersonCode() {
		return interestedPartyPersonCode;
	}

	public void setInterestedPartyPersonCode(String interestedPartyPersonCode) {
		this.interestedPartyPersonCode = interestedPartyPersonCode;
	}

	public String getProxyDepartmentCode() {
		return proxyDepartmentCode;
	}

	public void setProxyDepartmentCode(String proxyDepartmentCode) {
		this.proxyDepartmentCode = proxyDepartmentCode;
	}

	public String getProxyCompanyInnerCode() {
		return proxyCompanyInnerCode;
	}

	public void setProxyCompanyInnerCode(String proxyCompanyInnerCode) {
		this.proxyCompanyInnerCode = proxyCompanyInnerCode;
	}

	public String getChineseCharacter() {
		return chineseCharacter;
	}

	public void setChineseCharacter(String chineseCharacter) {
		this.chineseCharacter = chineseCharacter;
	}

	/**
	 * @return the isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Employee getDataCreater() {
		return dataCreater;
	}

	public void setDataCreater(Employee dataCreater) {
		this.dataCreater = dataCreater;
	}

	/**
	 * @return the sourceClassName
	 */
	public String getSourceClassName() {
		return sourceClassName;
	}

	/**
	 * @param sourceClassName
	 *            the sourceClassName to set
	 */
	public void setSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
	}

	/**
	 * @return the sourceBillCode
	 */
	public String getSourceBillCode() {
		return sourceBillCode;
	}

	/**
	 * @param sourceBillCode
	 *            the sourceBillCode to set
	 */
	public void setSourceBillCode(String sourceBillCode) {
		this.sourceBillCode = sourceBillCode;
	}

}
