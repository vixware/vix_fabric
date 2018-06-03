package com.vix.nvix.oa.document.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: DocumentUploader
 * @Description: 公文附件 
 * @author yhl
 * @date 2017/12/19
 */
public class DocumentUploader extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 *//*
	private String name;*/
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 时间
	 *//*
	private Date createDate;*/
	/**
	 * 文件大小
	 */
	private String fileSize;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 下载次数
	 */
	private Integer downloadNum;
	
	/** 收文 */
	private ReceiveDocument receiveDocument;
	/** 发文  */
	private SendDocument sendDocument;

	public DocumentUploader() {
		super();
	}
	
	public DocumentUploader(String id) {
		super();
		setId(id);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}

	public ReceiveDocument getReceiveDocument() {
		return receiveDocument;
	}

	public void setReceiveDocument(ReceiveDocument receiveDocument) {
		this.receiveDocument = receiveDocument;
	}

	public SendDocument getSendDocument() {
		return sendDocument;
	}

	public void setSendDocument(SendDocument sendDocument) {
		this.sendDocument = sendDocument;
	}
	
}
