package com.vix.common.org.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: OrgJob
 * @Description: 职位
 * @author wangmingchen
 * @date 2013-5-4 下午8:04:55
 * 
 */
public class OrgJob extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 职位名称 */
	private String jobName;
	/** 职位级别 */
	private Integer jobLevel;

	/** 父职位 */
	private OrgJob parentOrgJob;
	/** 子职位集合 */
	private Set<OrgJob> subOrgJobs = new HashSet<OrgJob>();

	/** 所属公司 */
	private Organization organization;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}

	public OrgJob getParentOrgJob() {
		return parentOrgJob;
	}

	public void setParentOrgJob(OrgJob parentOrgJob) {
		this.parentOrgJob = parentOrgJob;
	}

	public Set<OrgJob> getSubOrgJobs() {
		return subOrgJobs;
	}

	public void setSubOrgJobs(Set<OrgJob> subOrgJobs) {
		this.subOrgJobs = subOrgJobs;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
