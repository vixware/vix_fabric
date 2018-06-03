package com.vix.hr.job.entity;

import com.vix.common.org.entity.OrgJob;
import com.vix.common.org.entity.OrgPosition;
import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.hr.hrmgr.entity.PersonnelContract;
import com.vix.hr.personnelmgr.entity.HrBecomeRegular;
import com.vix.hr.trainning.entity.TrainingPlanning;

/**
 * @Description: 附件
 * @author 李大鹏
 * @date 2013-07-20
 */
public class HrAttachments extends BaseEntity {

	private static final long serialVersionUID = -4755153113719987847L;
	/** 类型 */
	private String bizObjectType;
	/** 地址路径 */
	private String path;
	/** 下载文件名 */
	private String titleName;
	/** 招聘计划 */
	private HrRecruitplan hrRecruitplan;
	/** 资料筛选*/
	private HrDataSelection hrDataSelection;
	/** 转正 */
	private HrBecomeRegular hrBecomeRegular;
	/** 岗位 x(作废) */
	private OrgJob orgJob;
	/** 职位 */
	private OrgPosition orgPosition;
	/** 培训计划 */
	private TrainingPlanning trainingPlanning;
	/** 合同查询 */
	private ContractInquiry contractInquiry;
	/** 合同管理 */
	private PersonnelContract personnelContract;

	public TrainingPlanning getTrainingPlanning() {
		return trainingPlanning;
	}

	public void setTrainingPlanning(TrainingPlanning trainingPlanning) {
		this.trainingPlanning = trainingPlanning;
	}

	public String getBizObjectType() {
		return bizObjectType;
	}

	public void setBizObjectType(String bizObjectType) {
		this.bizObjectType = bizObjectType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public HrRecruitplan getHrRecruitplan() {
		return hrRecruitplan;
	}

	public void setHrRecruitplan(HrRecruitplan hrRecruitplan) {
		this.hrRecruitplan = hrRecruitplan;
	}

	public HrBecomeRegular getHrBecomeRegular() {
		return hrBecomeRegular;
	}

	public void setHrBecomeRegular(HrBecomeRegular hrBecomeRegular) {
		this.hrBecomeRegular = hrBecomeRegular;
	}

	public OrgJob getOrgJob() {
		return orgJob;
	}

	public void setOrgJob(OrgJob orgJob) {
		this.orgJob = orgJob;
	}

	public OrgPosition getOrgPosition() {
		return orgPosition;
	}

	public void setOrgPosition(OrgPosition orgPosition) {
		this.orgPosition = orgPosition;
	}

	public PersonnelContract getPersonnelContract() {
		return personnelContract;
	}

	public void setPersonnelContract(PersonnelContract personnelContract) {
		this.personnelContract = personnelContract;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public ContractInquiry getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(ContractInquiry contractInquiry) {
		this.contractInquiry = contractInquiry;
	}

	public HrDataSelection getHrDataSelection() {
		return hrDataSelection;
	}

	public void setHrDataSelection(HrDataSelection hrDataSelection) {
		this.hrDataSelection = hrDataSelection;
	}
    
}
