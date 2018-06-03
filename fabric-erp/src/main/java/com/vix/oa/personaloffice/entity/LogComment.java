package com.vix.oa.personaloffice.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: LogComment
 * @Description: 评论工作日志 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-21 下午2:04:18
 */
public class LogComment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 评论内容 */
	private String commentscontent;
	/** 评论条数 */
	public Long commentsnumber;
	
	/** 发布人id */
	private String uploadPersonId;	
	/** id发布人 */
	private String uploadPerson;
	
	private String uploadPersonName;	

	/** 新闻评论 */
	private WorkLog workLog;

	public LogComment() {
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getCommentscontent() {
		return commentscontent;
	}

	public void setCommentscontent(String commentscontent) {
		this.commentscontent = commentscontent;
	}

	public Long getCommentsnumber() {
		return commentsnumber;
	}

	public void setCommentsnumber(Long commentsnumber) {
		this.commentsnumber = commentsnumber;
	}

	public WorkLog getWorkLog() {
		return workLog;
	}

	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}
}
