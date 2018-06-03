package com.vix.common.share.entity;

/**
 * 数据修改记录
 * @author Administrator
 *
 */
public class BaseChangeLog extends BaseEntity {
 
	private static final long serialVersionUID = 1L;
	/** 属性名称 */
	private String attributeName;
	/** 旧数据 */
	private String oldValue;
	/** 新数据 */
	private String newValue;
	/** 修改人姓名 */
	private String updatePersonName;
	/** 修改人ID */
	private String updatePersonId;
	/** 业务对象ID */
	private String entityId;
	/** 业务对象 */
	private String entityFullName;
	/** 修改人部门电话 */
	private String departmentPhone;
	/** 修改人所属部门 */
	private String department;
	
	public BaseChangeLog(){}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getUpdatePersonName() {
		return updatePersonName;
	}

	public void setUpdatePersonName(String updatePersonName) {
		this.updatePersonName = updatePersonName;
	}

	public String getUpdatePersonId() {
		return updatePersonId;
	}

	public void setUpdatePersonId(String updatePersonId) {
		this.updatePersonId = updatePersonId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityFullName() {
		return entityFullName;
	}

	public void setEntityFullName(String entityFullName) {
		this.entityFullName = entityFullName;
	}

	public String getDepartmentPhone() {
		return departmentPhone;
	}

	public void setDepartmentPhone(String departmentPhone) {
		this.departmentPhone = departmentPhone;
	}

	@Override
	public String getDepartment() {
		return department;
	}

	@Override
	public void setDepartment(String department) {
		this.department = department;
	}
}
