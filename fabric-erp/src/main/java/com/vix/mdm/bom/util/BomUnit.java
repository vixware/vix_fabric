package com.vix.mdm.bom.util;

import java.io.Serializable;
import java.util.List;

/**
 * BOM树展示类
 * 
 * @ClassFullName com.vix.nvix.project.util.ProjectUnit
 *
 * @author bjitzhang
 *
 * @date 2016年4月19日
 *
 */
public class BomUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String treeCode;

	private String treeName;

	private String treeType;

	private BomUnit bomUnit;

	private List<BomUnit> subBomUnits;

	public BomUnit(String id, String treeType, String treeName, String treeCode) {
		super();
		this.id = id;
		this.treeType = treeType;
		this.treeName = treeName;
		this.treeCode = treeCode;
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

	/**
	 * @return the treeCode
	 */
	public String getTreeCode() {
		return treeCode;
	}

	/**
	 * @param treeCode
	 *            the treeCode to set
	 */
	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	/**
	 * @return the treeName
	 */
	public String getTreeName() {
		return treeName;
	}

	/**
	 * @param treeName
	 *            the treeName to set
	 */
	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	/**
	 * @return the treeType
	 */
	public String getTreeType() {
		return treeType;
	}

	/**
	 * @param treeType
	 *            the treeType to set
	 */
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public BomUnit getBomUnit() {
		return bomUnit;
	}

	public void setBomUnit(BomUnit bomUnit) {
		this.bomUnit = bomUnit;
	}

	public List<BomUnit> getSubBomUnits() {
		return subBomUnits;
	}

	public void setSubBomUnits(List<BomUnit> subBomUnits) {
		this.subBomUnits = subBomUnits;
	}

	

}
