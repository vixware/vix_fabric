/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目管理成本要素汇总表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-22
 */
public class PMElementStat extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4376715743619768589L;
	/** @pdOid 会计期间 */
	private Integer iPeriod;
	/** @pdOid 责任中心编码 */
	private String cCenterCode;
	/** @pdOid 要素编码 */
	private String cElementCode;
	/** @pdOid 总费用 */
	private Double iAmount;

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

}
