package com.vix.oa.infoCenter.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
* @ClassName: NewsComment
* @Description: 消息评论
* @author bobchen
* @date 2016年3月26日 下午3:10:16
*
 */
public class NewsComment extends BaseEntity {

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

	/** 消息管理 */
	private MessageManagement messageManagement;

	public NewsComment() {
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

	public MessageManagement getMessageManagement() {
		return messageManagement;
	}

	public void setMessageManagement(MessageManagement messageManagement) {
		this.messageManagement = messageManagement;
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
