package com.vix.inventory.warehouse.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 仓库树的展示类
 * 
 * @author zhanghaibing
 * 
 * @date 2013-12-8
 */
public class InvUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String treeType;

	private String invName;

	private String invCode;

	private InvUnit InvUnit;

	private List<InvUnit> subInvUnits;

	public InvUnit(String id, String treeType, String invName) {
		super();
		this.id = id;
		this.treeType = treeType;
		this.invName = invName;
	}

	public InvUnit(String id, String treeType, String invName, String invCode) {
		super();
		this.id = id;
		this.treeType = treeType;
		this.invName = invName;
		this.invCode = invCode;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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

	public String getInvName() {
		return invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
	}

	public String getInvCode() {
		return invCode;
	}

	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	public InvUnit getInvUnit() {
		return InvUnit;
	}

	public void setInvUnit(InvUnit invUnit) {
		InvUnit = invUnit;
	}

	public List<InvUnit> getSubInvUnits() {
		return subInvUnits;
	}

	public void setSubInvUnits(List<InvUnit> subInvUnits) {
		this.subInvUnits = subInvUnits;
	}

}
