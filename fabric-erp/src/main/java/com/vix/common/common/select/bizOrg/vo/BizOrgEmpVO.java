package com.vix.common.common.select.bizOrg.vo;

public class BizOrgEmpVO {

	/** businessOrgId_empId
	 * 如  1O_12
	 *   2O_3
	 */
	private String treeId;
	
	/** 职员姓名  */
	private String treeName;
	
	/** 职员ID */
	private String empId;
	
	/** 业务组织ID */
    private String bizOrgId;
    
    /** bizViewId+"V" */
    private String bizViewIdStr = "";
    
    /**子数据数量 */
    private Long subCount;

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getBizOrgId() {
		return bizOrgId;
	}

	public void setBizOrgId(String bizOrgId) {
		this.bizOrgId = bizOrgId;
	}

	public String getBizViewIdStr() {
		return bizViewIdStr;
	}

	public void setBizViewIdStr(String bizViewIdStr) {
		this.bizViewIdStr = bizViewIdStr;
	}

	public Long getSubCount() {
		return subCount;
	}

	public void setSubCount(Long subCount) {
		this.subCount = subCount;
	}
	
}
