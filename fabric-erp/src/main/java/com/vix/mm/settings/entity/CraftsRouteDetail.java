package com.vix.mm.settings.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description:设置-工艺路线明细
 * @author 李大鹏
 */
public class CraftsRouteDetail extends BaseEntity {

	private static final long serialVersionUID = 7345986234012512068L;
	/** 工序编号 */
	private String processCode;
	/** 标准工序 */
	private String standardProcedure;
	/** 工序说明 */
	private String processExplain;
	/** 报告点 */
	private String repPoint;
	/** 倒冲工序 */
	private String reverseBlankingProcess;
	/** 工作中心 */
	private String org;
	private WorkCenter workCenter;
	/** 委外工序 */
	private String qutsourcingProcess;
	/** 厂商名称 */
	private String tradeName;
	/** 工艺路线 */
	private CraftsRoute craftsRoute;

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getStandardProcedure() {
		return standardProcedure;
	}

	public void setStandardProcedure(String standardProcedure) {
		this.standardProcedure = standardProcedure;
	}

	public String getProcessExplain() {
		return processExplain;
	}

	public void setProcessExplain(String processExplain) {
		this.processExplain = processExplain;
	}

	public String getRepPoint() {
		return repPoint;
	}

	public void setRepPoint(String repPoint) {
		this.repPoint = repPoint;
	}

	public String getReverseBlankingProcess() {
		return reverseBlankingProcess;
	}

	public void setReverseBlankingProcess(String reverseBlankingProcess) {
		this.reverseBlankingProcess = reverseBlankingProcess;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getQutsourcingProcess() {
		return qutsourcingProcess;
	}

	public void setQutsourcingProcess(String qutsourcingProcess) {
		this.qutsourcingProcess = qutsourcingProcess;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public CraftsRoute getCraftsRoute() {
		return craftsRoute;
	}

	public void setCraftsRoute(CraftsRoute craftsRoute) {
		this.craftsRoute = craftsRoute;
	}

	public WorkCenter getWorkCenter() {
		return workCenter;
	}
	
	public String getWorkCenterName() {
		if(workCenter != null){
			return workCenter.getOrgName();
		}
		return "";
	}

	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}

}
