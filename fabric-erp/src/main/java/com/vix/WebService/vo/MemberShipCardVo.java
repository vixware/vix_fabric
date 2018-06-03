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
public class MemberShipCardVo {

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
	 * 会员卡号
	 */
	private String vipcardid;
	/**
	 * 会员卡类型编码
	 */
	private String viptypeid;
	/**
	 * 余额
	 */
	private Double balance_amount;
	/**
	 * 累计消费金额
	 */
	private Double consume_amount;
	/**
	 * 结算次数
	 */
	private Integer settle_count;
	/**
	 * 发卡日期
	 */
	private Date opencarddate;
	/**
	 * 发卡时间
	 */
	private Date opencardtime;
	/**
	 * 是否销售
	 */
	private String issale;
	/**
	 * 销售顾问编码
	 */
	private String repsid;
	/**
	 * 销售顾问姓名
	 */
	private String repsname;
	/**
	 * 销售金额
	 */
	private Double saleprice;
	/**
	 * 销售日期
	 */
	private Date sale_date;
	/**
	 * 销售时间
	 */
	private Date sale_time;
	/**
	 * 是否启用
	 */
	private String isstartuse;
	/**
	 * 开始日期
	 */
	private Date start_date;
	/**
	 * 开始时间
	 */
	private Date start_time;
	/**
	 * 有效日期
	 */
	private Date invalid_date;
	/**
	 * 有效时间
	 */
	private Date invalid_time;
	/**
	 * 上次使用日期
	 */
	private Date lastuse_date;
	/**
	 * 上次使用时间
	 */
	private Date lastuse_time;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否停用 1,停用 2,启用
	 */
	private String isstop;
	/**
	 * 是否销卡
	 */
	private String isdestruct;
	/**
	 * 请假开始日期
	 */
	private Date leave_begindate;
	/**
	 * 请假截止日期
	 */
	private Date leave_enddate;
	/**
	 * 会员编号
	 */
	private String vipid;
	/**
	 * 原有卡号
	 */
	private String oldcardid;
	/**
	 * 磁道数据
	 */
	private String trackdata;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否作废
	 */
	private String isinvalid;
	/**
	 * 验证码
	 */
	private String checkNo;
	/**
	 * 开卡类型
	 */
	private String opentype;
	/** 积分历史 */
	private Long pointHistory;
	/** 冻结积分 */
	private Long pointFreeze;
	/** 可用积分 */
	private Long point;

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

	public String getVipcardid() {
		return vipcardid;
	}

	public void setVipcardid(String vipcardid) {
		this.vipcardid = vipcardid;
	}

	public String getOpentype() {
		return opentype;
	}

	public void setOpentype(String opentype) {
		this.opentype = opentype;
	}

	public String getViptypeid() {
		return viptypeid;
	}

	public void setViptypeid(String viptypeid) {
		this.viptypeid = viptypeid;
	}

	public Double getBalance_amount() {
		return balance_amount;
	}

	public void setBalance_amount(Double balance_amount) {
		this.balance_amount = balance_amount;
	}

	public Double getConsume_amount() {
		return consume_amount;
	}

	public void setConsume_amount(Double consume_amount) {
		this.consume_amount = consume_amount;
	}

	public Integer getSettle_count() {
		return settle_count;
	}

	public void setSettle_count(Integer settle_count) {
		this.settle_count = settle_count;
	}

	public Date getOpencarddate() {
		return opencarddate;
	}

	public void setOpencarddate(Date opencarddate) {
		this.opencarddate = opencarddate;
	}

	public Date getOpencardtime() {
		return opencardtime;
	}

	public void setOpencardtime(Date opencardtime) {
		this.opencardtime = opencardtime;
	}

	public String getIssale() {
		return issale;
	}

	public void setIssale(String issale) {
		this.issale = issale;
	}

	public String getRepsid() {
		return repsid;
	}

	public void setRepsid(String repsid) {
		this.repsid = repsid;
	}

	public String getRepsname() {
		return repsname;
	}

	public void setRepsname(String repsname) {
		this.repsname = repsname;
	}

	public Double getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}

	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}

	public Date getSale_time() {
		return sale_time;
	}

	public void setSale_time(Date sale_time) {
		this.sale_time = sale_time;
	}

	public String getIsstartuse() {
		return isstartuse;
	}

	public void setIsstartuse(String isstartuse) {
		this.isstartuse = isstartuse;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getInvalid_date() {
		return invalid_date;
	}

	public void setInvalid_date(Date invalid_date) {
		this.invalid_date = invalid_date;
	}

	public Date getInvalid_time() {
		return invalid_time;
	}

	public void setInvalid_time(Date invalid_time) {
		this.invalid_time = invalid_time;
	}

	public Date getLastuse_date() {
		return lastuse_date;
	}

	public void setLastuse_date(Date lastuse_date) {
		this.lastuse_date = lastuse_date;
	}

	public Date getLastuse_time() {
		return lastuse_time;
	}

	public void setLastuse_time(Date lastuse_time) {
		this.lastuse_time = lastuse_time;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}

	public String getIsdestruct() {
		return isdestruct;
	}

	public void setIsdestruct(String isdestruct) {
		this.isdestruct = isdestruct;
	}

	public Date getLeave_begindate() {
		return leave_begindate;
	}

	public void setLeave_begindate(Date leave_begindate) {
		this.leave_begindate = leave_begindate;
	}

	public Date getLeave_enddate() {
		return leave_enddate;
	}

	public void setLeave_enddate(Date leave_enddate) {
		this.leave_enddate = leave_enddate;
	}

	public String getVipid() {
		return vipid;
	}

	public void setVipid(String vipid) {
		this.vipid = vipid;
	}

	public String getOldcardid() {
		return oldcardid;
	}

	public void setOldcardid(String oldcardid) {
		this.oldcardid = oldcardid;
	}

	public String getTrackdata() {
		return trackdata;
	}

	public void setTrackdata(String trackdata) {
		this.trackdata = trackdata;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsinvalid() {
		return isinvalid;
	}

	public void setIsinvalid(String isinvalid) {
		this.isinvalid = isinvalid;
	}

	public Long getPointHistory() {
		return pointHistory;
	}

	public void setPointHistory(Long pointHistory) {
		this.pointHistory = pointHistory;
	}

	public Long getPointFreeze() {
		return pointFreeze;
	}

	public void setPointFreeze(Long pointFreeze) {
		this.pointFreeze = pointFreeze;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

}
