package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpFileUpload
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpFileUpload extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static final int image = 1;
	public static final int text = 2;
	public static final int ps = 3;
	public static final int voice = 4;
	public static final int other = 10;

	String filePath; // 文件路径
	String title; // 文件标题
	String note; // 文件备注
	Integer fileType; // 文件类型,1图片素材，2文案素材，3设计素材，4音频素材，10其他素材
	String fileExt; // 文件后缀格式,如jpg
	String uploadUserId; // 上传人id
	Date uploadTime; // 上传时间
	String uploadMark; // 上传标记，区分同一批次上传的文件
	String fileName; // 文件原名称
	WxpFileUploadFolder fileUploadFolder;// 文件夹id

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadMark() {
		return uploadMark;
	}

	public void setUploadMark(String uploadMark) {
		this.uploadMark = uploadMark;
	}

	public WxpFileUploadFolder getFileUploadFolder() {
		return fileUploadFolder;
	}

	public void setFileUploadFolder(WxpFileUploadFolder fileUploadFolder) {
		this.fileUploadFolder = fileUploadFolder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
