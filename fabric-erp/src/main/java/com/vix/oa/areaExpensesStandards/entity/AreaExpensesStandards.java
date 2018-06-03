package com.vix.oa.areaExpensesStandards.entity;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.claimsmanagement.areaLevel.entity.AreaLevel;
import com.vix.oa.claimsmanagement.vehicle.entity.Transport;

/**
 * 报销标准 com.vix.oa.areaExpensesStandards.entity.AreaExpensesStandards
 *
 * @author bjitzhang
 *
 * @date 2015年6月12日
 */

public class AreaExpensesStandards extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 */
	private String standardName;
	/**
	 * 标准类型 1,交通 2,住宿 3,日常
	 */
	private String type;
	/**
	 * 区域
	 */
	private AreaLevel areaLevel;
	/**
	 * 交通工具
	 */
	private Transport transport;
	/**
	 * 岗位
	 * 
	 * @return
	 */
	private OrgPosition orgPosition;
	/**
	 * 部门
	 */
	private OrganizationUnit organizationUnit;
	/**
	 * 报销标准
	 */
	private Double reimbursementAmount;

	public String getAreaLevelName() {
		if (areaLevel != null) {
			return areaLevel.getName();
		}
		return "";
	}

	public String getOrgPositionName() {
		if (orgPosition != null) {
			return orgPosition.getPosName();
		}
		return "";
	}

	public String getTransportName() {
		if (transport != null) {
			return transport.getName();
		}
		return "";
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public AreaLevel getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(AreaLevel areaLevel) {
		this.areaLevel = areaLevel;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the organizationUnit
	 */
	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	/**
	 * @param organizationUnit
	 *            the organizationUnit to set
	 */
	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public OrgPosition getOrgPosition() {
		return orgPosition;
	}

	public void setOrgPosition(OrgPosition orgPosition) {
		this.orgPosition = orgPosition;
	}

	public Double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(Double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

}