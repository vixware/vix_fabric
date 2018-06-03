package com.vix.hr.personnelmgr.entity;

import com.vix.common.share.entity.BaseEntity;

public class HrApprovalOpinion extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 审批意见 */
	private String content;
	/** 审批人ID */
	private String checkPersonId;
	/** 审批人 */
	private String checkPerson;
	/** 转正 */
	private HrBecomeRegular hrBecomeRegular;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public HrBecomeRegular getHrBecomeRegular() {
		return hrBecomeRegular;
	}

	public void setHrBecomeRegular(HrBecomeRegular hrBecomeRegular) {
		this.hrBecomeRegular = hrBecomeRegular;
	}
	
}
