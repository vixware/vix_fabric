package com.vix.common.org.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: OrgUnit
 * @Description: 公司和部门的树的展示类
 * @author wangmingchen
 * @date 2013-5-23 下午10:17:36
 * 
 */
public class OrgUnit implements Serializable {
	private static final long serialVersionUID = 2842549213729650031L;

	private String id;

	private String treeType;

	private String orgName;

	private String orgCode;

	private OrgUnit parentOrgUnit;

	private List<OrgUnit> subOrgUnits;

	public OrgUnit(String id, String treeType, String orgName) {
		super();
		this.id = id;
		this.treeType = treeType;
		this.orgName = orgName;
	}

	public OrgUnit(String id, String treeType, String orgName, String orgCode) {
		super();
		this.id = id;
		this.treeType = treeType;
		this.orgName = orgName;
		this.orgCode = orgCode;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public OrgUnit getParentOrgUnit() {
		return parentOrgUnit;
	}

	public void setParentOrgUnit(OrgUnit parentOrgUnit) {
		this.parentOrgUnit = parentOrgUnit;
	}

	public List<OrgUnit> getSubOrgUnits() {
		return subOrgUnits;
	}

	public void setSubOrgUnits(List<OrgUnit> subOrgUnits) {
		this.subOrgUnits = subOrgUnits;
	}

}
