/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目管理预算明细主表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-22
 */
public class PMBudget extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8375446939666099629L;
	/** @pdOid 版本编号 */
	private Long iVerId;
	/** @pdOid 项目编码 */
	private String cItemCode;
	/** @pdOid 核算关键字1 */
	private String cKeyCode1;
	/** @pdOid 核算关键字2 */
	private String cKeyCode2;
	/** @pdOid 项目要素编码 */
	private String cElementCode;
	/** @pdOid 预算数量 */
	private Double iNumber;
	/** @pdOid 单价 */
	private Double iUPrice;
	/** @pdOid 预算金额 */
	private Double iBudget;
	/** @pdOid 期初发生金额 */
	private Double iMoneySum;
	/** @pdOid 累计发生金额 */
	private Double iMoneyCtl;

	public Long getiVerId() {
		return iVerId;
	}

	public void setiVerId(Long iVerId) {
		this.iVerId = iVerId;
	}

	public String getcItemCode() {
		return cItemCode;
	}

	public void setcItemCode(String cItemCode) {
		this.cItemCode = cItemCode;
	}

	public String getcKeyCode1() {
		return cKeyCode1;
	}

	public void setcKeyCode1(String cKeyCode1) {
		this.cKeyCode1 = cKeyCode1;
	}

	public String getcKeyCode2() {
		return cKeyCode2;
	}

	public void setcKeyCode2(String cKeyCode2) {
		this.cKeyCode2 = cKeyCode2;
	}

	public String getcElementCode() {
		return cElementCode;
	}

	public void setcElementCode(String cElementCode) {
		this.cElementCode = cElementCode;
	}

	public Double getiNumber() {
		return iNumber;
	}

	public void setiNumber(Double iNumber) {
		this.iNumber = iNumber;
	}

	public Double getiUPrice() {
		return iUPrice;
	}

	public void setiUPrice(Double iUPrice) {
		this.iUPrice = iUPrice;
	}

	public Double getiBudget() {
		return iBudget;
	}

	public void setiBudget(Double iBudget) {
		this.iBudget = iBudget;
	}

	public Double getiMoneySum() {
		return iMoneySum;
	}

	public void setiMoneySum(Double iMoneySum) {
		this.iMoneySum = iMoneySum;
	}

	public Double getiMoneyCtl() {
		return iMoneyCtl;
	}

	public void setiMoneyCtl(Double iMoneyCtl) {
		this.iMoneyCtl = iMoneyCtl;
	}

}
