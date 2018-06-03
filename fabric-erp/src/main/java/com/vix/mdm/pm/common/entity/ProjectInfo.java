/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目信息其他信息
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectInfo extends BaseEntity {
	/** @pdOid 建设地点 */
	private String location;
	/** @pdOid 项目经理 */
	private String manager;
	/** @pdOid 控制经理 */
	private String controlManager;
	/** @pdOid 设计单位 */
	private String designUnit;
	/** @pdOid 土建施工单位 */
	private String constructionUnit;
	/** @pdOid 安装施工单位 */
	private String installUnit;
	/** @pdOid 监理单位 */
	private String supervisionUnit;
	/** @pdOid 院项目编号 */
	private String designInstituteProjectCode;
	/** @pdOid 项目类型 */
	private String projectType;
	/** @pdOid 专业类型 */
	private String disciplineType;
	/** @pdOid 主合同签订日期 */
	private String masterContractDate;
	/** @pdOid 主合同金额 */
	private String masterContractAmount;
	/** @pdOid 项目结束日期 */
	private String projectEndTime;
	/** @pdOid 工程规模 */
	private String scale;
	/** @pdOid 处理工艺 */
	private String treatmentProcess;
	/** @pdOid 投资规模 */
	private String investmentScale;
	/** @pdOid 资金来源 */
	private String fundSource;
	/** @pdOid 资金情况 */
	private String fundInfo;
	/** @pdOid 项目特点 */
	private String techPoint;
	/** @pdOid 进度要求 */
	private String scheduleTarget;
	/** @pdOid 支付条款 */
	private String payment;
	/** @pdOid 资质要求 */
	private String qualificationRequirements;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getControlManager() {
		return controlManager;
	}

	public void setControlManager(String controlManager) {
		this.controlManager = controlManager;
	}

	public String getDesignUnit() {
		return designUnit;
	}

	public void setDesignUnit(String designUnit) {
		this.designUnit = designUnit;
	}

	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public String getInstallUnit() {
		return installUnit;
	}

	public void setInstallUnit(String installUnit) {
		this.installUnit = installUnit;
	}

	public String getSupervisionUnit() {
		return supervisionUnit;
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit;
	}

	public String getDesignInstituteProjectCode() {
		return designInstituteProjectCode;
	}

	public void setDesignInstituteProjectCode(String designInstituteProjectCode) {
		this.designInstituteProjectCode = designInstituteProjectCode;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getDisciplineType() {
		return disciplineType;
	}

	public void setDisciplineType(String disciplineType) {
		this.disciplineType = disciplineType;
	}

	public String getMasterContractDate() {
		return masterContractDate;
	}

	public void setMasterContractDate(String masterContractDate) {
		this.masterContractDate = masterContractDate;
	}

	public String getMasterContractAmount() {
		return masterContractAmount;
	}

	public void setMasterContractAmount(String masterContractAmount) {
		this.masterContractAmount = masterContractAmount;
	}

	public String getProjectEndTime() {
		return projectEndTime;
	}

	public void setProjectEndTime(String projectEndTime) {
		this.projectEndTime = projectEndTime;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getTreatmentProcess() {
		return treatmentProcess;
	}

	public void setTreatmentProcess(String treatmentProcess) {
		this.treatmentProcess = treatmentProcess;
	}

	public String getInvestmentScale() {
		return investmentScale;
	}

	public void setInvestmentScale(String investmentScale) {
		this.investmentScale = investmentScale;
	}

	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getFundInfo() {
		return fundInfo;
	}

	public void setFundInfo(String fundInfo) {
		this.fundInfo = fundInfo;
	}

	public String getTechPoint() {
		return techPoint;
	}

	public void setTechPoint(String techPoint) {
		this.techPoint = techPoint;
	}

	public String getScheduleTarget() {
		return scheduleTarget;
	}

	public void setScheduleTarget(String scheduleTarget) {
		this.scheduleTarget = scheduleTarget;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getQualificationRequirements() {
		return qualificationRequirements;
	}

	public void setQualificationRequirements(String qualificationRequirements) {
		this.qualificationRequirements = qualificationRequirements;
	}

}
