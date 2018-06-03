package com.vix.common.org.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.constant.BizConstant;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.entity.OrganizationUnitType;

/**
 * @ClassName: Organization
 * @Description: 组织单元/运营单位
 * 
 *               公司实体部门
 * 
 *               组织单元类型：部门、运营单位、
 * 
 *               业务类型: 资产负债部门、损益部门、仓库、工作中心、作业、项目、分支工厂等，用户同时也可以自己定义部门类型。
 * @author wangmingchen
 * @date 2013-5-4 下午4:33:08
 * 
 */
public class OrganizationUnit extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 是否系统管理 */
	private Integer isSystem;

	/** 编码 */
	private String orgCode;

	/** 简称 */
	private String fs;

	/**
	 * 部门类型 （个人\供应商等） (作为类属性)销售渠道 销售经销商 基准部门 JZBM 销售办公室 XSBGS 销售组 XSZ 销售组织 XSZZ
	 * unitType
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
	private OrganizationUnit parentOrganizationUnit;

	/** 子机构集合 */
	private Set<OrganizationUnit> subOrganizationUnits = new HashSet<OrganizationUnit>();

	/** 所属公司 */
	private Organization organization;

	/** 岗位 */
	private Set<OrgPosition> orgPositions;

	/** 业务组织 */
	// private Set<BusinessOrg> businessOrgs;
	/** 业务组织明细 */
	private Set<BusinessOrgDetail> businessOrgDetails;

	/**
	 * 分销/渠道（部门）
	 */
	private ChannelDistributor channelDistributor;

	/** 分销/渠道（部门） */
	private Set<ChannelDistributor> channelDistributors = new HashSet<ChannelDistributor>(0);

	/** 负责人 */
	private Employee manager;
	private OrganizationUnitType organizationUnitType;

	// 企业号部门
	Integer isRoot; // 本site内是否为根节点
	// String id; //部门id，siteId+企业号部门id
	String parentId; // 父部门id
	Long syncId; // 与腾讯同步后的id，并以此判断是否同步到腾讯
	Integer sortOrder; // 排序，从小到大
	// 企业号部门

	public OrganizationUnit() {
	}

	public OrganizationUnit(String id) {
		setId(id);
	}

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

	public String getStartUsingDateStr() {
		if (null != startUsingDate) {
			return DateUtil.format(startUsingDate);
		}
		return "";
	}

	public void setStartUsingDate(Date startUsingDate) {
		this.startUsingDate = startUsingDate;
	}

	public Date getStopUsingDate() {
		return stopUsingDate;
	}

	public String getStopUsingDateStr() {
		if (null != stopUsingDate) {
			return DateUtil.format(stopUsingDate);
		}
		return "";
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

	public OrganizationUnit getParentOrganizationUnit() {
		return parentOrganizationUnit;
	}

	public void setParentOrganizationUnit(OrganizationUnit parentOrganizationUnit) {
		this.parentOrganizationUnit = parentOrganizationUnit;
	}

	public Set<OrganizationUnit> getSubOrganizationUnits() {
		return subOrganizationUnits;
	}

	public void setSubOrganizationUnits(Set<OrganizationUnit> subOrganizationUnits) {
		this.subOrganizationUnits = subOrganizationUnits;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<OrgPosition> getOrgPositions() {
		return orgPositions;
	}

	public void setOrgPositions(Set<OrgPosition> orgPositions) {
		this.orgPositions = orgPositions;
	}

	public Set<ChannelDistributor> getChannelDistributors() {
		return channelDistributors;
	}

	public void setChannelDistributors(Set<ChannelDistributor> channelDistributors) {
		this.channelDistributors = channelDistributors;
	}

	public Set<BusinessOrgDetail> getBusinessOrgDetails() {
		return businessOrgDetails;
	}

	public void setBusinessOrgDetails(Set<BusinessOrgDetail> businessOrgDetails) {
		this.businessOrgDetails = businessOrgDetails;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	/**
	 * 父亲类型 公司C 部门O
	 * 
	 * @return
	 */
	public String getParentType() {
		String type = "";
		if (organization != null) {
			type = BizConstant.COMMON_ORG_C;
		} else if (parentOrganizationUnit != null) {
			type = BizConstant.COMMON_ORG_O;
		}
		return type;
	}

	/**
	 * 父亲类型的id 公司 或者 部门
	 * 
	 * @return
	 */
	public String getParentTypeId() {
		String parentTypeId = null;
		if (organization != null) {
			parentTypeId = organization.getId();
		} else if (parentOrganizationUnit != null) {
			parentTypeId = parentOrganizationUnit.getId();
		}
		return parentTypeId;
	}

	public String getParentTypeName() {
		String parentTypeName = "";
		if (organization != null) {
			parentTypeName = organization.getBriefName();
		} else if (parentOrganizationUnit != null) {
			parentTypeName = parentOrganizationUnit.getFs();
		}
		return parentTypeName;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the syncId
	 */
	public Long getSyncId() {
		return syncId;
	}

	/**
	 * @param syncId
	 *            the syncId to set
	 */
	public void setSyncId(Long syncId) {
		this.syncId = syncId;
	}

	/**
	 * @return the isRoot
	 */
	public Integer getIsRoot() {
		return isRoot;
	}

	/**
	 * @param isRoot
	 *            the isRoot to set
	 */
	public void setIsRoot(Integer isRoot) {
		this.isRoot = isRoot;
	}

	/**
	 * @return the sortOrder
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the organizationUnitType
	 */
	public OrganizationUnitType getOrganizationUnitType() {
		return organizationUnitType;
	}

	/**
	 * @param organizationUnitType
	 *            the organizationUnitType to set
	 */
	public void setOrganizationUnitType(OrganizationUnitType organizationUnitType) {
		this.organizationUnitType = organizationUnitType;
	}

}
