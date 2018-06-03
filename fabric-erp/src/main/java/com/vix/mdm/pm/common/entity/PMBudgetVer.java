/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目管理预算版本控制表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-22
 */
public class PMBudgetVer extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7753591673361189943L;
	/** @pdOid 版本编号 */
	private int iVerId;
	/** @pdOid 版本名称 */
	private String cVerName;
	/** @pdOid 控制版本标志 */
	private String bControl;

	public int getiVerId() {
		return iVerId;
	}

	public void setiVerId(int iVerId) {
		this.iVerId = iVerId;
	}

	public String getcVerName() {
		return cVerName;
	}

	public void setcVerName(String cVerName) {
		this.cVerName = cVerName;
	}

	public String getbControl() {
		return bControl;
	}

	public void setbControl(String bControl) {
		this.bControl = bControl;
	}

}
