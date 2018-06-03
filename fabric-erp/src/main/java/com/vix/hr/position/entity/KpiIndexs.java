package com.vix.hr.position.entity;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.share.entity.BaseEntity;

/**
 * 岗位KPI指标
 * 
 * @author 李大鹏
 * 
 */
public class KpiIndexs extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 编码 */
	private String kpiCode;
	/** 名称 */
	private String kpiName;
	/** 名称描述 */
	private String kpiNameDepict;
	/** 公式 */
	private String formula;
	/** 备注 */
	private String remarks;

	/** 岗位 */
	private OrgPosition orgPosition;

	public String getKpiCode() {
		return kpiCode;
	}

	public void setKpiCode(String kpiCode) {
		this.kpiCode = kpiCode;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public String getKpiNameDepict() {
		return kpiNameDepict;
	}

	public void setKpiNameDepict(String kpiNameDepict) {
		this.kpiNameDepict = kpiNameDepict;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public OrgPosition getOrgPosition() {
		return orgPosition;
	}

	public void setOrgPosition(OrgPosition orgPosition) {
		this.orgPosition = orgPosition;
	}

}
