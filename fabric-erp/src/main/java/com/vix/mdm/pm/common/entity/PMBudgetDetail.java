/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目管理预算明细子表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-22
 */
public class PMBudgetDetail extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2481631428156049207L;
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
	/** @pdOid 所属明细编码(存货 or 材料) */
	private Double cCodeMx;
	/** @pdOid 预算数量 */
	private Double iNumber;
	/** @pdOid 期初发生数量 */
	private Double iNumberSum;
	/** @pdOid 存货(材料)累计发生(实际)量 */
	private Double iNumberCtl;
	/** @pdOid 单价 */
	private Double iUPrice;
	/** @pdOid 预算金额 */
	private Double iBudget;
	/** @pdOid 存货(材料)期初发生(实际)金额 */
	private Double iMoneySum;
	/** @pdOid 存货(材料)累计发生(实际)金额 */
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

	public Double getcCodeMx() {
		return cCodeMx;
	}

	public void setcCodeMx(Double cCodeMx) {
		this.cCodeMx = cCodeMx;
	}

	public Double getiNumber() {
		return iNumber;
	}

	public void setiNumber(Double iNumber) {
		this.iNumber = iNumber;
	}

	public Double getiNumberSum() {
		return iNumberSum;
	}

	public void setiNumberSum(Double iNumberSum) {
		this.iNumberSum = iNumberSum;
	}

	public Double getiNumberCtl() {
		return iNumberCtl;
	}

	public void setiNumberCtl(Double iNumberCtl) {
		this.iNumberCtl = iNumberCtl;
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
