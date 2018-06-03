package com.vix.crm.workbench.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.security.entity.UserGroup;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/** 内部公告 */
public class InnerBulletin extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 标题 */
	private String title;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 内容存放文件 */
	private String fileName;
	/** 是否顶置 */
	private String isTop;
	/** 公告分类 */
	private InnerBulletinCategory innerBulletinCategory;
	/** 发布人 */
	private Employee employee;
	/** 创建日期 */
	private Date createDate;
	/** 接收人 */
	private Set<Employee> acceptEmployees;
	/** 用户组 */
	private Set<UserGroup> userGroups;
	/** 内容 */
	private String content;

	public InnerBulletin() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public InnerBulletinCategory getInnerBulletinCategory() {
		return innerBulletinCategory;
	}

	public void setInnerBulletinCategory(InnerBulletinCategory innerBulletinCategory) {
		this.innerBulletinCategory = innerBulletinCategory;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<Employee> getAcceptEmployees() {
		return acceptEmployees;
	}

	public void setAcceptEmployees(Set<Employee> acceptEmployees) {
		this.acceptEmployees = acceptEmployees;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
