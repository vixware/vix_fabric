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
 * @date 2014-1-22
 */
public class PMItemCost extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1841882469417464262L;
	/** @pdOid 会计期间 */
	private Integer iPeriod;
	/** @pdOid 责任中心编码 */
	private String cCenterCode;
	/** @pdOid 项目编码 */
	private String cItemCode;
	/** @pdOid 项目关键字1 */
	private String cKeyCode1;
	/** @pdOid 项目关键字2 */
	private String cKeyCode2;
	/** @pdOid 要素编码 */
	private String cElementCode;
	/** @pdOid 总费用 */
	private Double iAmount;
	/** @pdOid 分配率 */
	private Double iRation;
	/** @pdOid 完工产品总费用 */
	private Double iFINAmount;
	/** @pdOid 在产品总费用 */
	private Double iWIPAmount;

	public Integer getiPeriod() {
		return iPeriod;
	}

	public void setiPeriod(Integer iPeriod) {
		this.iPeriod = iPeriod;
	}

	public String getcCenterCode() {
		return cCenterCode;
	}

	public void setcCenterCode(String cCenterCode) {
		this.cCenterCode = cCenterCode;
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

	public Double getiAmount() {
		return iAmount;
	}

	public void setiAmount(Double iAmount) {
		this.iAmount = iAmount;
	}

	public Double getiRation() {
		return iRation;
	}

	public void setiRation(Double iRation) {
		this.iRation = iRation;
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
