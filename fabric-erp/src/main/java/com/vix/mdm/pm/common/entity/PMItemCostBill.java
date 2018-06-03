/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目管理成本计算结果生成单据预备表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class PMItemCostBill extends BaseEntity {
	/** 会计期间 */
	private Integer iPeriod;
	/** 责任中心编号 */
	private String cCenterCode;
	/** 项目编号 */
	private String cItemCode;
	/** 核算关键字1 */
	private String cKeyCode1;
	/** 核算关键字2 */
	private String cKeyCode2;
	/** 合计数量 */
	private Double iNumSum;
	/** 合计金额 */
	private Double iMoneySum;
	/** 是否制单 */
	private byte bBuild;
	/** 单据编码 */
	private String cVouchCode;

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

	public Double getiNumSum() {
		return iNumSum;
	}

	public void setiNumSum(Double iNumSum) {
		this.iNumSum = iNumSum;
	}

	public Double getiMoneySum() {
		return iMoneySum;
	}

	public void setiMoneySum(Double iMoneySum) {
		this.iMoneySum = iMoneySum;
	}

	public byte getbBuild() {
		return bBuild;
	}

	public void setbBuild(byte bBuild) {
		this.bBuild = bBuild;
	}

	public String getcVouchCode() {
		return cVouchCode;
	}

	public void setcVouchCode(String cVouchCode) {
		this.cVouchCode = cVouchCode;
	}

}
