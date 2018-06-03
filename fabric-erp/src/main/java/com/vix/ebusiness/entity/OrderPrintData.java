/**
 * 
 */
package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author zhanghaibing
 * @date 2012-3-25
 */
public class OrderPrintData extends BaseEntity {

	private static final long serialVersionUID = 2941717051929905685L;

	/**
	 * 配送站点名称
	 */
	private String partnerName;
	/**
	 * 分拣代码
	 */
	private String sortingCode;
	/**
	 * 配送中心名称
	 */
	private String distributionCenterName;
	/**
	 * 配送中心编码
	 */
	private String distributionCenterCode;

	public String getDistributionCenterName() {
		return distributionCenterName;
	}

	public void setDistributionCenterName(String distributionCenterName) {
		this.distributionCenterName = distributionCenterName;
	}

	public String getDistributionCenterCode() {
		return distributionCenterCode;
	}

	public void setDistributionCenterCode(String distributionCenterCode) {
		this.distributionCenterCode = distributionCenterCode;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getSortingCode() {
		return sortingCode;
	}

	public void setSortingCode(String sortingCode) {
		this.sortingCode = sortingCode;
	}
}
