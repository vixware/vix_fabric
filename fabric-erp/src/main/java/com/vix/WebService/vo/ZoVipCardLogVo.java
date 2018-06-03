/**
 * 
 */
package com.vix.WebService.vo;

import java.util.Date;

/**
 * @author zhanghaibing
 * 
 * @date 2014-4-30
 */
public class ZoVipCardLogVo {
	/** 自动编号 */
	private Long id;
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
	/**
	 * 卡结记账编号
	 */
	private String logid;
	/**
	 * 会员卡号
	 */
	private String vipcardid;
	/**
	 * 应用编码
	 */
	private String appid;
	/**
	 * 等级日期
	 */
	private Date regdate;
	/**
	 * 等级时间
	 */
	private Date regtime;
	/**
	 * 创建用户账号
	 */
	private String reguserid;
	/**
	 * 创建用户姓名
	 */
	private String regusername;
	/**
	 * 日志标题编码
	 */
	private String logtitleid;
	/**
	 * 日志主题
	 */
	private String logtitle;
	/**
	 * 日志内容
	 */
	private String logtext;
	/**
	 * 日志备注
	 */
	private String logremark;
	/**
	 * 金额
	 */
	private Double amount;
	/**
	 * 账单号
	 */
	private String billid;
	/**
	 * 用户账号
	 */
	private String respuserid;
	/**
	 * 用户姓名
	 */
	private String respusername;
	/**
	 * 菜单项编码
	 */
	private String menuid;
	/**
	 * 菜单项名称
	 */
	private String menuname;
	/**
	 * 应收金额
	 */
	private Double sumtopay;
	/**
	 * 实收金额
	 */
	private Double sumtorealpay;
	/**
	 * 积分类型:1,增加.2,减少
	 */
	private String pointType;
	/**
	 * 积分值
	 */
	private Double pointValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
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

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getVipcardid() {
		return vipcardid;
	}

	public void setVipcardid(String vipcardid) {
		this.vipcardid = vipcardid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public String getReguserid() {
		return reguserid;
	}

	public void setReguserid(String reguserid) {
		this.reguserid = reguserid;
	}

	public String getRegusername() {
		return regusername;
	}

	public void setRegusername(String regusername) {
		this.regusername = regusername;
	}

	public String getLogtitleid() {
		return logtitleid;
	}

	public void setLogtitleid(String logtitleid) {
		this.logtitleid = logtitleid;
	}

	public String getLogtitle() {
		return logtitle;
	}

	public void setLogtitle(String logtitle) {
		this.logtitle = logtitle;
	}

	public String getLogtext() {
		return logtext;
	}

	public void setLogtext(String logtext) {
		this.logtext = logtext;
	}

	public String getLogremark() {
		return logremark;
	}

	public void setLogremark(String logremark) {
		this.logremark = logremark;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public String getRespuserid() {
		return respuserid;
	}

	public void setRespuserid(String respuserid) {
		this.respuserid = respuserid;
	}

	public String getRespusername() {
		return respusername;
	}

	public void setRespusername(String respusername) {
		this.respusername = respusername;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public Double getSumtopay() {
		return sumtopay;
	}

	public void setSumtopay(Double sumtopay) {
		this.sumtopay = sumtopay;
	}

	public Double getSumtorealpay() {
		return sumtorealpay;
	}

	public void setSumtorealpay(Double sumtorealpay) {
		this.sumtorealpay = sumtorealpay;
	}

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	public Double getPointValue() {
		return pointValue;
	}

	public void setPointValue(Double pointValue) {
		this.pointValue = pointValue;
	}

}
