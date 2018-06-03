package com.vix.pm.projectfile.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 
 * @ClassName: ProjectFile
 * @Description: 项目文档 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-20 上午10:46:29
 */
public class ProjectFile extends BaseBOEntity {

	private static final long serialVersionUID = -3635381736924712330L;
	/** 子分类集合 */
	private Set<ProjectFile> subCategorys = new HashSet<ProjectFile>();
	/** 父分类 */
	private ProjectFile parentCategory;
	
	/** 联系人*/
	private String linkman;
	/** 电话*/
	private String phone;
	/** 等级 */
	private String rank;
	/** 创建时间 */
	private Date createDate;
	
	
	public Set<ProjectFile> getSubCategorys() {
		return subCategorys;
	}
	public void setSubCategorys(Set<ProjectFile> subCategorys) {
		this.subCategorys = subCategorys;
	}
	public ProjectFile getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(ProjectFile parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
