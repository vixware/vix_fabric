/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目管理成本表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class PorjectCost extends BaseEntity {
	/** 会计期间 */
	private Integer accountPeriod;
	/** 责任中心编码 */
	private String rspCenterCode;
	/** 项目编码 */
	private String projectCode;
	/** 项目关键字1 */
	private String keyword1;
	/** 项目关键字2 */
	private String keyword2;
	/** 要素编码 */
	private String elementCode;
	/** 总费用 */
	private Double amount;
	/** 分配率 */
	private Double ratio;
	/** 完工产品总费用 */
	private Double iFINAmount;
	/** 在产品总费用 */
	private Double iWIPAmount;

	public Integer getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(Integer accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getRspCenterCode() {
		return rspCenterCode;
	}

	public void setRspCenterCode(String rspCenterCode) {
		this.rspCenterCode = rspCenterCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public String getElementCode() {
		return elementCode;
	}

	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Double getiFINAmount() {
		return iFINAmount;
	}

	public void setiFINAmount(Double iFINAmount) {
		this.iFINAmount = iFINAmount;
	}

	public Double getiWIPAmount() {
		return iWIPAmount;
	}

	public void setiWIPAmount(Double iWIPAmount) {
		this.iWIPAmount = iWIPAmount;
	}

}
