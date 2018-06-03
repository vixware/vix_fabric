package com.vix.common.share.entity;

/** 成组码对象 */
public class BillGroupCode extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/** 单据类型 */
	private String billType;
	/** 成组码 */
	private String groupCode;
	/** 单据编码 */
	private String billCode;
	
	public BillGroupCode(){}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
}
