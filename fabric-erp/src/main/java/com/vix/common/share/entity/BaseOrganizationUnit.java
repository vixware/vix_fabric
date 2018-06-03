package com.vix.common.share.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BaseOrganizationUnit extends BaseEntity {


    /** 是否系统管理 */
    private Integer isSystem;

    /** 编码 */
    private String orgCode;

    /** 简称 */
    private String fs;

    /** 部门类型				（个人\供应商等）
     *  										(作为类属性)销售渠道 销售经销商
     *  基准部门		JZBM
     *  销售办公室	XSBGS
     * 	销售组		XSZ
	 *  销售组织	XSZZ
     *  unitType
     */
    private String unitType;

    /** 业务类型 */
    private String bizUnitType;

    /** 全称 */
    private String fullName;

    /** 描述 */
    private String description;

    /** 启用时间 */
    private Date startUsingDate;

    /** 停用时间 */
    private Date stopUsingDate;

    /** 创建人 */
    private String createUser;

    /** 是否是行政组织 */
    private String isAdminOrg;

    /** 是否是HR组织 */
    private String isHrOrg;

    /** 是否是销售组织 */
    private String isSellOrg;

    /** 是否是管理单元 */
    private String isAdmin;

    /** 是否是采购组织 */
    private String isPurchaseOrg;

    /** 是否是责任中心 */
    private String isResponCenter;

    /** 备注 */
    private String memo;

    /** 父机构 */
    private BaseOrganizationUnit parentOrganizationUnit;

    /** 子机构集合 */
    private Set<BaseOrganizationUnit> subOrganizationUnits = new HashSet<BaseOrganizationUnit>();
    
    /** 所属公司 */
    private BaseOrganization organization;
    
    /** 岗位 */
    //private Set<OrgPosition> orgPositions;
    
    /** 业务组织  */
    //private Set<BusinessOrg> businessOrgs;
    /** 业务组织明细*/
    //private Set<BusinessOrgDetail> businessOrgDetails;
    
    /**
     * 分销/渠道（部门）
     */
    //private ChannelDistributor channelDistributor;
    
    /** 分销/渠道（部门） */
   // private Set<ChannelDistributor> channelDistributors = new HashSet<ChannelDistributor>(0);
    
    
    /** 负责人 */
    private BaseEmployee manager;
    
    

	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getBizUnitType() {
		return bizUnitType;
	}

	public void setBizUnitType(String bizUnitType) {
		this.bizUnitType = bizUnitType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartUsingDate() {
		return startUsingDate;
	}

	public void setStartUsingDate(Date startUsingDate) {
		this.startUsingDate = startUsingDate;
	}

	public Date getStopUsingDate() {
		return stopUsingDate;
	}

	public void setStopUsingDate(Date stopUsingDate) {
		this.stopUsingDate = stopUsingDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getIsAdminOrg() {
		return isAdminOrg;
	}

	public void setIsAdminOrg(String isAdminOrg) {
		this.isAdminOrg = isAdminOrg;
	}

	public String getIsHrOrg() {
		return isHrOrg;
	}

	public void setIsHrOrg(String isHrOrg) {
		this.isHrOrg = isHrOrg;
	}

	public String getIsSellOrg() {
		return isSellOrg;
	}

	public void setIsSellOrg(String isSellOrg) {
		this.isSellOrg = isSellOrg;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getIsPurchaseOrg() {
		return isPurchaseOrg;
	}

	public void setIsPurchaseOrg(String isPurchaseOrg) {
		this.isPurchaseOrg = isPurchaseOrg;
	}

	public String getIsResponCenter() {
		return isResponCenter;
	}

	public void setIsResponCenter(String isResponCenter) {
		this.isResponCenter = isResponCenter;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BaseOrganizationUnit getParentOrganizationUnit() {
		return parentOrganizationUnit;
	}

	public void setParentOrganizationUnit(BaseOrganizationUnit parentOrganizationUnit) {
		this.parentOrganizationUnit = parentOrganizationUnit;
	}

	public Set<BaseOrganizationUnit> getSubOrganizationUnits() {
		return subOrganizationUnits;
	}

	public void setSubOrganizationUnits(Set<BaseOrganizationUnit> subOrganizationUnits) {
		this.subOrganizationUnits = subOrganizationUnits;
	}

	public BaseOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(BaseOrganization organization) {
		this.organization = organization;
	}

	public BaseEmployee getManager() {
		return manager;
	}

	public void setManager(BaseEmployee manager) {
		this.manager = manager;
	}
    
}
