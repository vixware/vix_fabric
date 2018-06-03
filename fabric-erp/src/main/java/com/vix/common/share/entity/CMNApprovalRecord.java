package com.vix.common.share.entity;


/**
 * 审批意见记录
 * @author Administrator
 *
 */
public class CMNApprovalRecord extends BaseEntity{
 
	private static final long serialVersionUID = 1L;
	/** 业务对象名称 */
	private String boCode;
	/** 业务对象类型 */
	private String boName;
	/** 业务对象类型 */
	private String boType;
	/** 审批意见 */
	private String content;
	/** 审批人ID */
	private String checkPersonId;
	/** 审批人 */
	private String checkPerson;
	/** 部门ID */
	private String departmentCode;
	/** 流程编码 */
	private String bizFlowCode;
	/** 流程名称 */
	private String bizFlowName;
	
	public CMNApprovalRecord(){}

	public String getBoCode() {
		return boCode;
	}

	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	@Override
	public String getDepartmentCode() {
		return departmentCode;
	}

	@Override
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getBizFlowCode() {
		return bizFlowCode;
	}

	public void setBizFlowCode(String bizFlowCode) {
		this.bizFlowCode = bizFlowCode;
	}

	public String getBizFlowName() {
		return bizFlowName;
	}

	public void setBizFlowName(String bizFlowName) {
		this.bizFlowName = bizFlowName;
	}
}
