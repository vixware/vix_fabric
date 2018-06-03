package com.vix.common.org.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @ClassName: OrgPosition
 * @Description: 职位/岗位
 * @author wangmingchen
 * @date 2013-5-4 下午8:04:55
 * 
 */
public class OrgPosition extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 名称 */
	private java.lang.String posName;
	// 编码使用父类的
	/** 岗位层级 */
	private java.lang.Integer level;
	/** 编制人数 */
	private Integer personAmount;
	/** 职务 */
	private String job;
	/** 职务等级 */
	private String jobLevel;
	/** 职务类型 */
	private String jobType;
	/** 岗位序号 */
	private Integer postNumber;
	/** 集团系统内岗位编号 */
	// *************
	private String groupSystemPostNumber;
	/** 岗位性质 */
	private String jobNature;
	/** 岗位层次 */
	// *************
	private String positionLevel;
	/** 岗位一级分类 */
	// *************
	private String jobClassification1;
	/** 岗位二级分类 */
	// *************
	private String jobClassification2;
	/** 岗位三级分类 */
	// *************
	private String jobClassification3;
	/** 岗位所属职业工种一级分类 */
	// *************
	private String postTheOccupationTypeCategory;
	/** 岗位所属职业工种二级分类 */
	// *************
	private String postTheOccupationTypeTwoLevelClassification;
	/** 是否特殊工种岗位 */
	private String whetherParticularTypesOfWorkStatus;
	/** 岗位薪级 */
	private String postPayScale;
	/** 岗位薪级标准 */
	private String postSalaryScale;
	/** 标准薪点 */
	private String standardPayPoint;
	/** 标准点值 */
	private String standardPointValue;
	/** 是否有毒害岗位 */
	private String arePoisonedJobs;
	/** 有毒有害接害类型 */
	private String toxicAndHarmfulToDamageType;
	/** 干部职级 */
	private String ranksOfCadres;
	/** 有毒有害作业种类 */
	private String toxicAndHazardousTypes;
	/** 发放标准 */
	private String paymentStandards;
	/** 是否点检岗位 */
	private String whetherPointInspectionPosts;
	/** 是否虚构岗位 */
	private String whetherFictitiousJobs;
	/** 是否运行岗位 */
	private String whetherToRunTheJob;
	/** 是否定员岗位 */
	private String whetherCapacityPosts;
	/** 是否检修岗位 */
	private String whetherMaintenanceJobs;
	/** 是否班组长 */
	private String whetherTheTeamLeader;
	/** 定员编码 */
	private String capacityCoding;

	/** 是否部门负责岗位 */
	private String isChargeDep;

	private OrgPosition parentOrgPosition;

	private Set<OrgPosition> subOrgPositions = new HashSet<OrgPosition>();

	/**
	 * 岗位对应的职员的关系
	 */
	private Set<Employee> employees = new HashSet<Employee>();

	/** 职务 */
	private OrgJob orgJob;

	/** 所属组织单元 */
	private OrganizationUnit organizationUnit;

	private Organization organization;

	public OrgPosition() {
		super();
	}

	public OrgPosition(String id) {
		super();
		setId(id);
	}

	public java.lang.String getPosName() {
		return posName;
	}

	public void setPosName(java.lang.String posName) {
		this.posName = posName;
	}

	public java.lang.Integer getLevel() {
		return level;
	}

	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}

	public String getIsChargeDep() {
		return isChargeDep;
	}

	public void setIsChargeDep(String isChargeDep) {
		this.isChargeDep = isChargeDep;
	}

	public OrgJob getOrgJob() {
		return orgJob;
	}

	public void setOrgJob(OrgJob orgJob) {
		this.orgJob = orgJob;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public Integer getPersonAmount() {
		return personAmount;
	}

	public void setPersonAmount(Integer personAmount) {
		this.personAmount = personAmount;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getGroupSystemPostNumber() {
		return groupSystemPostNumber;
	}

	public void setGroupSystemPostNumber(String groupSystemPostNumber) {
		this.groupSystemPostNumber = groupSystemPostNumber;
	}

	public String getJobNature() {
		return jobNature;
	}

	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}

	public String getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}

	public String getJobClassification1() {
		return jobClassification1;
	}

	public void setJobClassification1(String jobClassification1) {
		this.jobClassification1 = jobClassification1;
	}

	public String getJobClassification2() {
		return jobClassification2;
	}

	public void setJobClassification2(String jobClassification2) {
		this.jobClassification2 = jobClassification2;
	}

	public String getJobClassification3() {
		return jobClassification3;
	}

	public void setJobClassification3(String jobClassification3) {
		this.jobClassification3 = jobClassification3;
	}

	public String getPostTheOccupationTypeCategory() {
		return postTheOccupationTypeCategory;
	}

	public void setPostTheOccupationTypeCategory(String postTheOccupationTypeCategory) {
		this.postTheOccupationTypeCategory = postTheOccupationTypeCategory;
	}

	public String getPostTheOccupationTypeTwoLevelClassification() {
		return postTheOccupationTypeTwoLevelClassification;
	}

	public void setPostTheOccupationTypeTwoLevelClassification(String postTheOccupationTypeTwoLevelClassification) {
		this.postTheOccupationTypeTwoLevelClassification = postTheOccupationTypeTwoLevelClassification;
	}

	public String getWhetherParticularTypesOfWorkStatus() {
		return whetherParticularTypesOfWorkStatus;
	}

	public void setWhetherParticularTypesOfWorkStatus(String whetherParticularTypesOfWorkStatus) {
		this.whetherParticularTypesOfWorkStatus = whetherParticularTypesOfWorkStatus;
	}

	public String getPostPayScale() {
		return postPayScale;
	}

	public void setPostPayScale(String postPayScale) {
		this.postPayScale = postPayScale;
	}

	public String getPostSalaryScale() {
		return postSalaryScale;
	}

	public void setPostSalaryScale(String postSalaryScale) {
		this.postSalaryScale = postSalaryScale;
	}

	public String getStandardPayPoint() {
		return standardPayPoint;
	}

	public void setStandardPayPoint(String standardPayPoint) {
		this.standardPayPoint = standardPayPoint;
	}

	public String getStandardPointValue() {
		return standardPointValue;
	}

	public void setStandardPointValue(String standardPointValue) {
		this.standardPointValue = standardPointValue;
	}

	public String getArePoisonedJobs() {
		return arePoisonedJobs;
	}

	public void setArePoisonedJobs(String arePoisonedJobs) {
		this.arePoisonedJobs = arePoisonedJobs;
	}

	public String getToxicAndHarmfulToDamageType() {
		return toxicAndHarmfulToDamageType;
	}

	public void setToxicAndHarmfulToDamageType(String toxicAndHarmfulToDamageType) {
		this.toxicAndHarmfulToDamageType = toxicAndHarmfulToDamageType;
	}

	public String getRanksOfCadres() {
		return ranksOfCadres;
	}

	public void setRanksOfCadres(String ranksOfCadres) {
		this.ranksOfCadres = ranksOfCadres;
	}

	public String getToxicAndHazardousTypes() {
		return toxicAndHazardousTypes;
	}

	public void setToxicAndHazardousTypes(String toxicAndHazardousTypes) {
		this.toxicAndHazardousTypes = toxicAndHazardousTypes;
	}

	public String getPaymentStandards() {
		return paymentStandards;
	}

	public void setPaymentStandards(String paymentStandards) {
		this.paymentStandards = paymentStandards;
	}

	public String getWhetherPointInspectionPosts() {
		return whetherPointInspectionPosts;
	}

	public void setWhetherPointInspectionPosts(String whetherPointInspectionPosts) {
		this.whetherPointInspectionPosts = whetherPointInspectionPosts;
	}

	public String getWhetherFictitiousJobs() {
		return whetherFictitiousJobs;
	}

	public void setWhetherFictitiousJobs(String whetherFictitiousJobs) {
		this.whetherFictitiousJobs = whetherFictitiousJobs;
	}

	public String getWhetherToRunTheJob() {
		return whetherToRunTheJob;
	}

	public void setWhetherToRunTheJob(String whetherToRunTheJob) {
		this.whetherToRunTheJob = whetherToRunTheJob;
	}

	public String getWhetherCapacityPosts() {
		return whetherCapacityPosts;
	}

	public void setWhetherCapacityPosts(String whetherCapacityPosts) {
		this.whetherCapacityPosts = whetherCapacityPosts;
	}

	public String getWhetherMaintenanceJobs() {
		return whetherMaintenanceJobs;
	}

	public void setWhetherMaintenanceJobs(String whetherMaintenanceJobs) {
		this.whetherMaintenanceJobs = whetherMaintenanceJobs;
	}

	public String getWhetherTheTeamLeader() {
		return whetherTheTeamLeader;
	}

	public void setWhetherTheTeamLeader(String whetherTheTeamLeader) {
		this.whetherTheTeamLeader = whetherTheTeamLeader;
	}

	public String getCapacityCoding() {
		return capacityCoding;
	}

	public void setCapacityCoding(String capacityCoding) {
		this.capacityCoding = capacityCoding;
	}

	public Integer getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(Integer postNumber) {
		this.postNumber = postNumber;
	}

	public OrgPosition getParentOrgPosition() {
		return parentOrgPosition;
	}

	public void setParentOrgPosition(OrgPosition parentOrgPosition) {
		this.parentOrgPosition = parentOrgPosition;
	}

	public Set<OrgPosition> getSubOrgPositions() {
		return subOrgPositions;
	}

	public void setSubOrgPositions(Set<OrgPosition> subOrgPositions) {
		this.subOrgPositions = subOrgPositions;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
