package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
/**
 * 
 * @ClassName: PmContract
 * @Description: 项目合同 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-1-23 下午4:09:13
 */
public class PmContract extends BaseEntity {

	private static final long serialVersionUID = 1482456870778679154L;
	/**合同当事人*/
	private String contractingParties;
	/**发包人*/
	private String employer;
	/**承包人*/
	private String contractor;
	/**监理人*/
	private String supervisor;
	/**工程和设备*/
	private String engineeringEquipment;
	/**单位工程*/
	private String unitProject;
	/**临时占地*/
	private String temporaryCovering;
	/**交公日期*/
	private Date jiaoGongTime;
	/**缺陷责任期*/
	private String defectsLiabilityPeriod;
	/**保修期*/
	private String warrantyPeriod;
	
	/** 合同 */
	private Contract contract;
	
	public PmContract() {
		super();
	}

	public PmContract(String id) {
		super();
		setId(id);
	}

	public String getContractingParties() {
		return contractingParties;
	}

	public void setContractingParties(String contractingParties) {
		this.contractingParties = contractingParties;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getEngineeringEquipment() {
		return engineeringEquipment;
	}

	public void setEngineeringEquipment(String engineeringEquipment) {
		this.engineeringEquipment = engineeringEquipment;
	}

	public String getUnitProject() {
		return unitProject;
	}

	public void setUnitProject(String unitProject) {
		this.unitProject = unitProject;
	}

	public String getTemporaryCovering() {
		return temporaryCovering;
	}

	public void setTemporaryCovering(String temporaryCovering) {
		this.temporaryCovering = temporaryCovering;
	}

	public Date getJiaoGongTime() {
		return jiaoGongTime;
	}

	public void setJiaoGongTime(Date jiaoGongTime) {
		this.jiaoGongTime = jiaoGongTime;
	}

	public String getDefectsLiabilityPeriod() {
		return defectsLiabilityPeriod;
	}

	public void setDefectsLiabilityPeriod(String defectsLiabilityPeriod) {
		this.defectsLiabilityPeriod = defectsLiabilityPeriod;
	}

	public String getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
