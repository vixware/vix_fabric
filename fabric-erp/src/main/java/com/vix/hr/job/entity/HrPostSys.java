package com.vix.hr.job.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.initialSetup.entity.Grading;
import com.vix.hr.initialSetup.entity.PostsysTypes;
import com.vix.hr.initialSetup.entity.Powers;
import com.vix.hr.initialSetup.entity.Rank;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 职务体系
 * @author 李大鹏
 */
public class HrPostSys extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7470269485274693290L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 职务编码 */
	private String postsysCode;
	/** 职务名称 */
	private String postsysName;
	/** 所属部门 */
	private String theDepartment;
	/** 职务类型 */
	private PostsysTypes postsysTypes;
	/** 职级 */
	private Rank rank;
	/** 职等 */
	private Grading grading;
	/** 职权 */
	private Powers powers;
	/** 人员 */
	private Employee employee;

	public String getPostsysName() {
		return postsysName;
	}

	public void setPostsysName(String postsysName) {
		this.postsysName = postsysName;
	}

	public PostsysTypes getPostsysTypes() {
		return postsysTypes;
	}

	public void setPostsysTypes(PostsysTypes postsysTypes) {
		this.postsysTypes = postsysTypes;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Grading getGrading() {
		return grading;
	}

	public void setGrading(Grading grading) {
		this.grading = grading;
	}

	public Powers getPowers() {
		return powers;
	}

	public void setPowers(Powers powers) {
		this.powers = powers;
	}

	public String getTheDepartment() {
		return theDepartment;
	}

	public void setTheDepartment(String theDepartment) {
		this.theDepartment = theDepartment;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPostsysCode() {
		return postsysCode;
	}

	public void setPostsysCode(String postsysCode) {
		this.postsysCode = postsysCode;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

}
