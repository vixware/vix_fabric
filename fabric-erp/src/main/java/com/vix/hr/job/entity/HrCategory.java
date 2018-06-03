package com.vix.hr.job.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrgJob;
import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 招聘弹出窗体树
 * @author 李大鹏
 * @date 2013-07-31
 */
public class HrCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 名称 */
	private String name;
	/** 是否禁用 0：未禁用 1：已禁用 */
	private String status;
	/** 备注 */
	private String memo;
	/** 子分类集合 */
	private Set<HrCategory> subCategorys = new HashSet<HrCategory>();
	/** 父分类 */
	private HrCategory parentCategory;

	private Set<OrgJob> orgJobs = new HashSet<OrgJob>();

	public HrCategory() {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<HrCategory> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(Set<HrCategory> subCategorys) {
		this.subCategorys = subCategorys;
	}

	public HrCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(HrCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<OrgJob> getOrgJobs() {
		return orgJobs;
	}

	public void setOrgJobs(Set<OrgJob> orgJobs) {
		this.orgJobs = orgJobs;
	}

}
