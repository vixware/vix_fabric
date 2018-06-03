package com.vix.system.billTypeManagement.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 单据类型展示类
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class BillTypeUnit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3493854179005908527L;

	private String id;

	private String treeType;

	private String treeCode;

	private String treeName;

	private BillTypeUnit billTypeUnit;

	private List<BillTypeUnit> subBillTypeUnits;

	public BillTypeUnit(String id, String treeType, String treeName,
			String treeCode) {
		super();
		this.id = id;
		this.treeType = treeType;
		this.treeName = treeName;
		this.treeCode = treeCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public BillTypeUnit getBillTypeUnit() {
		return billTypeUnit;
	}

	public void setBillTypeUnit(BillTypeUnit billTypeUnit) {
		this.billTypeUnit = billTypeUnit;
	}

	public List<BillTypeUnit> getSubBillTypeUnits() {
		return subBillTypeUnits;
	}

	public void setSubBillTypeUnits(List<BillTypeUnit> subBillTypeUnits) {
		this.subBillTypeUnits = subBillTypeUnits;
	}

}
