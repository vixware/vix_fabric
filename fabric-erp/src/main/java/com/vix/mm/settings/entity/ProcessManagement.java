package com.vix.mm.settings.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description:设置-工序管理
 * @author 李大鹏
 */
public class ProcessManagement extends BaseEntity {

	private static final long serialVersionUID = 6150131182882107976L;
	/** 工序编码 */
	private String processCode;
	/** 工序说明 */
	private String processDescription;
	/** 类型 */
	private String types;
	/** 创建时间 */
	private Date creDate;
	/** 报告点 */
	private String repPoint;
	/** 工作中心名称 */
	private String orgName;
	/** 倒冲工序 */
	private String eeverseBlankingProcess;
	/** 委外工序 */
	private String outsourcingProcess;
	/** 厂商编码 */
	private String manufacturerCode;
	/** 厂商名称 */
	private String manufacturerName;
	/** 计划委外工序 */
	private String planOutsourcingProcess;
	/** 交货天数 */
	private String deliveryPeriod;
	/** 检验方式 */
	private String inspectionMethods;
	/** 抽检规则 */
	private String samplingRule;
	/** 抽检率 */
	private String samplingRate;
	/** 抽检数量 */
	private String samplingNumber;
	/** 检验严格度 */
	private String inspectionStrictDegree;
	/** 质量检验方案 */
	private String qualityInspectionPlan;
	/** 自定义抽检规则 */
	private String customInspectionRules;
	/** 检验规则 */
	private String inspectionRules;
	/** 检验水平 */
	private String testLevel;
	/** AQL */
	private String aqlCode;
	/** 工作中心编码 */
	private String orgid;
	/** 工序管理明细 */
	private Set<ProcessManagementDetail> processManagementDetails = new HashSet<ProcessManagementDetail>();
	/** 资源资料 */
	private Set<ResourceiIformation> resourceiIformations = new HashSet<ResourceiIformation>();
	/** 工艺路线 */
	private CraftsRoute craftsRoute;

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getProcessDescription() {
		return processDescription;
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}

	public String getRepPoint() {
		return repPoint;
	}

	public void setRepPoint(String repPoint) {
		this.repPoint = repPoint;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEeverseBlankingProcess() {
		return eeverseBlankingProcess;
	}

	public void setEeverseBlankingProcess(String eeverseBlankingProcess) {
		this.eeverseBlankingProcess = eeverseBlankingProcess;
	}

	public String getOutsourcingProcess() {
		return outsourcingProcess;
	}

	public void setOutsourcingProcess(String outsourcingProcess) {
		this.outsourcingProcess = outsourcingProcess;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getPlanOutsourcingProcess() {
		return planOutsourcingProcess;
	}

	public void setPlanOutsourcingProcess(String planOutsourcingProcess) {
		this.planOutsourcingProcess = planOutsourcingProcess;
	}

	public String getDeliveryPeriod() {
		return deliveryPeriod;
	}

	public void setDeliveryPeriod(String deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}

	public String getInspectionMethods() {
		return inspectionMethods;
	}

	public void setInspectionMethods(String inspectionMethods) {
		this.inspectionMethods = inspectionMethods;
	}

	public String getSamplingRule() {
		return samplingRule;
	}

	public void setSamplingRule(String samplingRule) {
		this.samplingRule = samplingRule;
	}

	public String getSamplingRate() {
		return samplingRate;
	}

	public void setSamplingRate(String samplingRate) {
		this.samplingRate = samplingRate;
	}

	public String getSamplingNumber() {
		return samplingNumber;
	}

	public void setSamplingNumber(String samplingNumber) {
		this.samplingNumber = samplingNumber;
	}

	public String getInspectionStrictDegree() {
		return inspectionStrictDegree;
	}

	public void setInspectionStrictDegree(String inspectionStrictDegree) {
		this.inspectionStrictDegree = inspectionStrictDegree;
	}

	public String getQualityInspectionPlan() {
		return qualityInspectionPlan;
	}

	public void setQualityInspectionPlan(String qualityInspectionPlan) {
		this.qualityInspectionPlan = qualityInspectionPlan;
	}

	public String getCustomInspectionRules() {
		return customInspectionRules;
	}

	public void setCustomInspectionRules(String customInspectionRules) {
		this.customInspectionRules = customInspectionRules;
	}

	public String getInspectionRules() {
		return inspectionRules;
	}

	public void setInspectionRules(String inspectionRules) {
		this.inspectionRules = inspectionRules;
	}

	public String getTestLevel() {
		return testLevel;
	}

	public void setTestLevel(String testLevel) {
		this.testLevel = testLevel;
	}

	public String getAqlCode() {
		return aqlCode;
	}

	public void setAqlCode(String aqlCode) {
		this.aqlCode = aqlCode;
	}

	public Set<ProcessManagementDetail> getProcessManagementDetails() {
		return processManagementDetails;
	}

	public void setProcessManagementDetails(Set<ProcessManagementDetail> processManagementDetails) {
		this.processManagementDetails = processManagementDetails;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Set<ResourceiIformation> getResourceiIformations() {
		return resourceiIformations;
	}

	public void setResourceiIformations(Set<ResourceiIformation> resourceiIformations) {
		this.resourceiIformations = resourceiIformations;
	}

	public CraftsRoute getCraftsRoute() {
		return craftsRoute;
	}

	public void setCraftsRoute(CraftsRoute craftsRoute) {
		this.craftsRoute = craftsRoute;
	}

}
