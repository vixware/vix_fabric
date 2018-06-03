package com.vix.eam.entity;

import java.util.Date;

import com.vix.eam.common.entity.EamBaseEntity;

public class EqWorkOrder extends EamBaseEntity {
	private String wocode;	//工单编码
	private String woname;	//工单名称
	private String woApplyNo;	//工单申请号
	private Date applyDate;	//申请日期
	private String eqcode;	//设备编码
	private String eqname;	//设备名称
	private String priority;	//优先级
	private Integer wotype;	//工单类型
	private Integer woStatus;	//工单状态
	private String memo;	//描述
	private String reporter;	//工单报告人
	private String planMader;	//工单策划人
	private String issuer;	//工单签发人
	private String department;	//执行部门
	private String defendCenter;	//维护中心
	private String responsiblePerson;	//负责人
	private String applicent;	//申请人
	private String resDepartment;	//负责人部门
	private String appDepartment;	//申请人部门
	private Date hopeStartTime;	//期望开始时间
	private Date hopeEndTime;	//期望结束时间
	private Date planStartTime;	//计划开工时间
	private Date planEndTime;	//计划完工时间
	private Date actualStartTime;	//实际开始时间
	private Date actualEndTime;	//实际结束时间
	private String failCode;	//故障码
	private String modifyBy;	//更新人
	private Date modifyDate;	//更新日期
	private String budgetCost;	//预算成本
	private String actualCost;	//实际成本
	private String kitCode;	//工具包编码
	private String eqpositionCode;	//设备位置编码
	private String superWocode;	//父工单编码
	private String phone;	//电话
	private String costScope;	//成本控制范围
	private String costCenter;	//成本中心
	private Double costUpLimit;		//成本范围上限
	private Double costDownLimit;		//成本范围下限
	private String profitCenter;	//利润中心
	private String factory;	//工厂
	private Integer settleType;	//结算对象类型
	private String objCode;	//对象编号
	private String percent;	//百分比
	private Integer settleMode;	//结算方式
	
	
	public String getWocode() {
		return wocode;
	}
	public void setWocode(String wocode) {
		this.wocode = wocode;
	}
	public String getWoname() {
		return woname;
	}
	public void setWoname(String woname) {
		this.woname = woname;
	}
	public String getWoApplyNo() {
		return woApplyNo;
	}
	public void setWoApplyNo(String woApplyNo) {
		this.woApplyNo = woApplyNo;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getEqcode() {
		return eqcode;
	}
	public void setEqcode(String eqcode) {
		this.eqcode = eqcode;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Integer getWotype() {
		return wotype;
	}
	public void setWotype(Integer wotype) {
		this.wotype = wotype;
	}

	public String getEqname() {
		return eqname;
	}
	public void setEqname(String eqname) {
		this.eqname = eqname;
	}
	@Override
	public String getMemo() {
		return memo;
	}
	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getPlanMader() {
		return planMader;
	}
	public void setPlanMader(String planMader) {
		this.planMader = planMader;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	@Override
	public String getDepartment() {
		return department;
	}
	@Override
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDefendCenter() {
		return defendCenter;
	}
	public void setDefendCenter(String defendCenter) {
		this.defendCenter = defendCenter;
	}
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	public String getApplicent() {
		return applicent;
	}
	public void setApplicent(String applicent) {
		this.applicent = applicent;
	}
	public String getResDepartment() {
		return resDepartment;
	}
	public void setResDepartment(String resDepartment) {
		this.resDepartment = resDepartment;
	}
	public String getAppDepartment() {
		return appDepartment;
	}
	public void setAppDepartment(String appDepartment) {
		this.appDepartment = appDepartment;
	}
	public Date getHopeStartTime() {
		return hopeStartTime;
	}
	public void setHopeStartTime(Date hopeStartTime) {
		this.hopeStartTime = hopeStartTime;
	}
	public Date getHopeEndTime() {
		return hopeEndTime;
	}
	public void setHopeEndTime(Date hopeEndTime) {
		this.hopeEndTime = hopeEndTime;
	}
	public Date getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	public Date getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
	public Date getActualStartTime() {
		return actualStartTime;
	}
	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	public Date getActualEndTime() {
		return actualEndTime;
	}
	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	public String getFailCode() {
		return failCode;
	}
	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getBudgetCost() {
		return budgetCost;
	}
	public void setBudgetCost(String budgetCost) {
		this.budgetCost = budgetCost;
	}
	public String getActualCost() {
		return actualCost;
	}
	public void setActualCost(String actualCost) {
		this.actualCost = actualCost;
	}
	public String getKitCode() {
		return kitCode;
	}
	public void setKitCode(String kitCode) {
		this.kitCode = kitCode;
	}
	public String getEqpositionCode() {
		return eqpositionCode;
	}
	public void setEqpositionCode(String eqpositionCode) {
		this.eqpositionCode = eqpositionCode;
	}
	public String getSuperWocode() {
		return superWocode;
	}
	public void setSuperWocode(String superWocode) {
		this.superWocode = superWocode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCostScope() {
		return costScope;
	}
	public void setCostScope(String costScope) {
		this.costScope = costScope;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getProfitCenter() {
		return profitCenter;
	}
	public void setProfitCenter(String profitCenter) {
		this.profitCenter = profitCenter;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public Integer getSettleType() {
		return settleType;
	}
	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	public String getObjCode() {
		return objCode;
	}
	public void setObjCode(String objCode) {
		this.objCode = objCode;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public Integer getSettleMode() {
		return settleMode;
	}
	public void setSettleMode(Integer settleMode) {
		this.settleMode = settleMode;
	}
	public Integer getWoStatus() {
		return woStatus;
	}
	public void setWoStatus(Integer woStatus) {
		this.woStatus = woStatus;
	}
	public Double getCostUpLimit() {
		return costUpLimit;
	}
	public void setCostUpLimit(Double costUpLimit) {
		this.costUpLimit = costUpLimit;
	}
	public Double getCostDownLimit() {
		return costDownLimit;
	}
	public void setCostDownLimit(Double costDownLimit) {
		this.costDownLimit = costDownLimit;
	}
	
}
