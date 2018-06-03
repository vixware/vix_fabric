/**   
 * @Title: Subject.java 
 * @Package com.vix.contract.entity 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-8 下午3:37:36  
 */
package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: Subject
 * @Description: 所属课题
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-8 下午3:37:36
 */
public class Subject extends BaseEntity {

	private static final long serialVersionUID = -1088685502676200836L;
	/**
	 * 课题编号
	 **/
	private String subjectId;
	/**
	 * 审批单号
	 **/
	private String approveBillId;
	/** 
	 * 合同审批单
	 **//*
	private ApplicationForm applicationForm;
*/
	private Subject() {
		super();
	}

	private Subject(String id) {
		super();
		setId(id);
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getApproveBillId() {
		return approveBillId;
	}

	public void setApproveBillId(String approveBillId) {
		this.approveBillId = approveBillId;
	}

}
