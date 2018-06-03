package com.vix.wechat.base.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
/**
 * 
 * com.vix.wechat.base.entity.WxpQyContactDeptRel
 *
 * @author bjitzhang
 *
 * @date 2015年12月29日
 */
public class WxpQyContactDeptRel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	String contactId;
	String departmentId;
	Date createTime;

	WxpQyContacts wxpQyContacts;
	WxpQyDepartment wxpQyDepartment;

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the wxpQyContacts
	 */
	public WxpQyContacts getWxpQyContacts() {
		return wxpQyContacts;
	}

	/**
	 * @param wxpQyContacts
	 *            the wxpQyContacts to set
	 */
	public void setWxpQyContacts(WxpQyContacts wxpQyContacts) {
		this.wxpQyContacts = wxpQyContacts;
	}

	/**
	 * @return the wxpQyDepartment
	 */
	public WxpQyDepartment getWxpQyDepartment() {
		return wxpQyDepartment;
	}

	/**
	 * @param wxpQyDepartment
	 *            the wxpQyDepartment to set
	 */
	public void setWxpQyDepartment(WxpQyDepartment wxpQyDepartment) {
		this.wxpQyDepartment = wxpQyDepartment;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
}
