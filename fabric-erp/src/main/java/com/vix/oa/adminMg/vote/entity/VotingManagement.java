package com.vix.oa.adminMg.vote.entity;

import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
/**
 * 
 * @ClassName: VotingManagement
 * @Description:  投票管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-10 下午2:36:56
 */
public class VotingManagement extends BaseEntity {

	private static final long serialVersionUID = 375069367357967245L;
	
	/** 中文首字母 */
	private String chineseFirstLetter;
	
	/** 发布人  */
	private String releasePeople;
	
	/** 选举人  */
	private String electionWorkers;
	
	/** 被推荐人  */
	private String recommended;
	
	/** 标题  */
	private String title;
	
	/** 发布部门  */
	private String publishingDepartment;
	
	/** 发布角色  */
	private String releaseRole;
	
	/** 发布人id */
	private String uploadPersonId;	
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	
	 /**
     * 发布人员类型
     * "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
     */
    private String pubType;
    
    /**
     * 按部门发布
     */
    private Set<OrganizationUnit> organizationUnits;
    
    /**
     * 按角色发布
     */
    private Set<Role> roles;
    
    /**
     * 按人员发布
     */
    private Set<Employee> employees;
    
    /** 发布对象的id集合 */
    private String pubIds;
    
    /** 发布对象的名称集合 */
    private String pubNames;
	
	/** 投票描述  */
	private String voteDescribe;
	
	/** 备注  */
	private String remarks;
	
	/** 类型  0 单选  1 多选  2 文本输入 */
    public Integer votingType;
    
    /** 查看投票结果  0 投票后也许查看  1 投票前允许查看  2不允许查看*/
    public Integer voteType;
    
    /** 是否匿名投票  1 实名投票 0 匿名投票 */
    public Integer anonymity;
    
    /** 发布类型  0 待发布 1 发布   2 终止 */
    public Integer publishType;
	
	

	public String getReleasePeople() {
		return releasePeople;
	}

	public void setReleasePeople(String releasePeople) {
		this.releasePeople = releasePeople;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishingDepartment() {
		return publishingDepartment;
	}

	public void setPublishingDepartment(String publishingDepartment) {
		this.publishingDepartment = publishingDepartment;
	}

	public String getReleaseRole() {
		return releaseRole;
	}

	public void setReleaseRole(String releaseRole) {
		this.releaseRole = releaseRole;
	}

	public String getVoteDescribe() {
		return voteDescribe;
	}

	public void setVoteDescribe(String voteDescribe) {
		this.voteDescribe = voteDescribe;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getElectionWorkers() {
		return electionWorkers;
	}

	public void setElectionWorkers(String electionWorkers) {
		this.electionWorkers = electionWorkers;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	public Integer getVotingType() {
		return votingType;
	}

	public void setVotingType(Integer votingType) {
		this.votingType = votingType;
	}

	public Integer getVoteType() {
		return voteType;
	}

	public void setVoteType(Integer voteType) {
		this.voteType = voteType;
	}

	public Integer getAnonymity() {
		return anonymity;
	}

	public void setAnonymity(Integer anonymity) {
		this.anonymity = anonymity;
	}

	public Integer getPublishType() {
		return publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getPubNames() {
		return pubNames;
	}

	public void setPubNames(String pubNames) {
		this.pubNames = pubNames;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

}
