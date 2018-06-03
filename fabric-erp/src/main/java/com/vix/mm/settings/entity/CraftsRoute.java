package com.vix.mm.settings.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description:设置-工艺路线
 * @author 李大鹏
 */
public class CraftsRoute extends BaseEntity {

	private static final long serialVersionUID = 2840755547730364043L;

	/** 工艺路线编码 */
	private String craftsCode;
	/** 工艺路线名称 */
	private String craftsName;
	/** 物料编码 */
	private String materialCode;
	/** 物料名称 */
	private String materialName;
	/** 工艺路线类型 */
	private String types;
	/** 规格 */
	private String specifications;
	/** 工艺路线规则 */
	private String processRules;
	/** 固定提前期(天) */
	private String fixedLeadTime;
	/** 计量单位 */
	private String unitsMeasurement;
	/** 版本号 */
	private String versionNumber;
	/** 版本说明 */
	private String versionDescription;
	/** 变更单号 */
	private String changeNumber;
	/** 状态 */
	private String state;
	/** 创建时间 */
	private Date craDate;
	/** 工艺路线明细 */
	private Set<CraftsRouteDetail> craftsRouteDetails = new HashSet<CraftsRouteDetail>();
	/** 资源资料 */
	private Set<ProcessManagement> processManagements = new HashSet<ProcessManagement>();

	public String getCraftsCode() {
		return craftsCode;
	}

	public void setCraftsCode(String craftsCode) {
		this.craftsCode = craftsCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getProcessRules() {
		return processRules;
	}

	public void setProcessRules(String processRules) {
		this.processRules = processRules;
	}

	public String getFixedLeadTime() {
		return fixedLeadTime;
	}

	public void setFixedLeadTime(String fixedLeadTime) {
		this.fixedLeadTime = fixedLeadTime;
	}

	public String getUnitsMeasurement() {
		return unitsMeasurement;
	}

	public void setUnitsMeasurement(String unitsMeasurement) {
		this.unitsMeasurement = unitsMeasurement;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public String getChangeNumber() {
		return changeNumber;
	}

	public void setChangeNumber(String changeNumber) {
		this.changeNumber = changeNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCraDate() {
		return craDate;
	}
	
	public String getCraDateStr() {
		if(craDate != null){
			return DateUtil.format(craDate);
		}
		return "";
	}

	public void setCraDate(Date craDate) {
		this.craDate = craDate;
	}

	public Set<CraftsRouteDetail> getCraftsRouteDetails() {
		return craftsRouteDetails;
	}

	public void setCraftsRouteDetails(Set<CraftsRouteDetail> craftsRouteDetails) {
		this.craftsRouteDetails = craftsRouteDetails;
	}

	public String getCraftsName() {
		return craftsName;
	}

	public void setCraftsName(String craftsName) {
		this.craftsName = craftsName;
	}

	public Set<ProcessManagement> getProcessManagements() {
		return processManagements;
	}

	public void setProcessManagements(Set<ProcessManagement> processManagements) {
		this.processManagements = processManagements;
	}

}
