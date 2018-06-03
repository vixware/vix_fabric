package com.vix.oa.infoCenter.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: ManagementUploader
 * @Description: 消息附件
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-8-19 下午3:28:38
 */
public class ManagementUploader extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 文件名称 */
	private String fileName;
	
	/** 文件路径 */
	private String filePath;
	
	/** 上传时间 */
	private Date uploadTime;		
	
	/** 上传人id */
	private String uploadPersonId;	
	
	/** id上传人 */
	private String uploadPerson;
	
	private String uploadPersonName;
	
	/** id备注 */
	private String note;	
	
	/** 文档名称 */
	private String title;	
	
	/** 文档类型 */
	 private String fileType;
	

	/** 消息 */
	private MessageManagement messageManagement;

	public ManagementUploader() {
	}
	

	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public Date getUploadTime() {
		return uploadTime;
	}


	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
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


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public MessageManagement getMessageManagement() {
		return messageManagement;
	}


	public void setMessageManagement(MessageManagement messageManagement) {
		this.messageManagement = messageManagement;
	}

}
